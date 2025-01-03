### 7. Advanced I/O and NIO.2 Features

#### 7.1 Asynchronous File Operations
Example of a high-performance file processor:

```java
public class AsyncFileProcessor {
    private final AsynchronousFileChannel fileChannel;
    private final int bufferSize;
    private final ExecutorService executorService;
    private final Flow.Processor<ByteBuffer, ByteBuffer> processor;

    public AsyncFileProcessor(Path path, int bufferSize) throws IOException {
        this.bufferSize = bufferSize;
        this.executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        );
        this.fileChannel = AsynchronousFileChannel.open(
            path, 
            StandardOpenOption.READ,
            StandardOpenOption.WRITE,
            StandardOpenOption.CREATE
        );
        this.processor = new SubmissionPublisher<>();
    }

    public CompletableFuture<Void> processLargeFile(Consumer<ByteBuffer> processor) {
        AtomicLong position = new AtomicLong(0);
        ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);

        return CompletableFuture.supplyAsync(() -> {
            try {
                while (position.get() < fileChannel.size()) {
                    Future<Integer> readOperation = fileChannel.read(
                        buffer, 
                        position.get()
                    );
                    
                    int bytesRead = readOperation.get();
                    if (bytesRead == -1) {
                        break;
                    }

                    buffer.flip();
                    processor.accept(buffer);
                    buffer.clear();
                    position.addAndGet(bytesRead);
                }
                return null;
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    // File watching service
    public void watchDirectory(Path dir, Consumer<WatchEvent<?>> eventHandler) {
        try (WatchService watchService = FileSystems.getDefault()
                .newWatchService()) {
            
            dir.register(watchService, 
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

            CompletableFuture.runAsync(() -> {
                while (true) {
                    try {
                        WatchKey key = watchService.take();
                        key.pollEvents().forEach(eventHandler);
                        if (!key.reset()) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }, executorService);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

// Usage example
@Service
public class FileProcessingService {
    private final AsyncFileProcessor processor;
    private final MetricRegistry metrics;

    @PostConstruct
    public void startFileWatching() {
        Path watchDir = Paths.get("/data/incoming");
        processor.watchDirectory(watchDir, event -> {
            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                processNewFile((Path) event.context());
            }
        });
    }

    private void processNewFile(Path file) {
        Timer.Context timer = metrics.timer("file.processing").time();
        try {
            processor.processLargeFile(buffer -> {
                // Process each chunk
                processFileChunk(buffer);
                metrics.histogram("chunk.size").update(buffer.remaining());
            }).thenAccept(v -> {
                timer.stop();
                metrics.counter("files.processed").inc();
            });
        } catch (Exception e) {
            metrics.counter("files.errors").inc();
            throw e;
        }
    }
}
```

#### 7.2 Memory-Mapped Files
Example of using memory-mapped files for large data processing:

```java
public class MemoryMappedFileProcessor {
    private final Path filePath;
    private final int chunkSize;

    public MemoryMappedFileProcessor(Path filePath, int chunkSize) {
        this.filePath = filePath;
        this.chunkSize = chunkSize;
    }

    public void processLargeFile(Consumer<ByteBuffer> processor) 
            throws IOException {
        try (FileChannel channel = FileChannel.open(filePath, 
                StandardOpenOption.READ)) {
            
            long size = channel.size();
            long position = 0;

            while (position < size) {
                long remainingSize = size - position;
                long mappingSize = Math.min(chunkSize, remainingSize);

                MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_ONLY, 
                    position, 
                    mappingSize
                );

                processor.accept(buffer);
                position += mappingSize;

                // Force unmap to prevent memory leaks
                unmap(buffer);
            }
        }
    }

    // Unsafe but necessary for unmapping
    private void unmap(MappedByteBuffer buffer) {
        try {
            Method cleanerMethod = buffer.getClass().getMethod("cleaner");
            cleanerMethod.setAccessible(true);
            Object cleaner = cleanerMethod.invoke(buffer);
            Method cleanMethod = cleaner.getClass().getMethod("clean");
            cleanMethod.setAccessible(true);
            cleanMethod.invoke(cleaner);
        } catch (Exception e) {
            throw new RuntimeException("Failed to unmap buffer", e);
        }
    }
}
```

### 8. Java Module System Patterns

#### 8.1 Custom Module Implementation
Example of a modular application architecture:

```java
// module-info.java for core module
module com.example.core {
    exports com.example.core.api;
    exports com.example.core.model;
    requires transitive com.example.common;
    
    provides com.example.core.api.ServiceProvider 
        with com.example.core.impl.ServiceProviderImpl;
    uses com.example.core.api.Plugin;
}

// Service Provider interface
public interface ServiceProvider {
    <T> T getService(Class<T> serviceType);
    void registerService(Class<?> serviceType, Object implementation);
}

// Implementation with dynamic loading
public class ModularServiceLoader {
    private final ModuleLayer moduleLayer;
    private final ConcurrentMap<String, ServiceProvider> providers = 
        new ConcurrentHashMap<>();

    public ModularServiceLoader() {
        // Create module layer configuration
        ModuleFinder finder = ModuleFinder.of(getModulePaths());
        ModuleLayer parent = ModuleLayer.boot();
        
        Configuration cf = parent.configuration()
            .resolve(finder, ModuleFinder.of(), 
                Set.of("com.example.core"));
                
        moduleLayer = parent.defineModulesWithOneLoader(cf, 
            ClassLoader.getSystemClassLoader());
    }

    public <T> Optional<T> loadService(Class<T> serviceType) {
        return ServiceLoader
            .load(moduleLayer, serviceType)
            .findFirst();
    }

    public void loadPlugins() {
        ServiceLoader<Plugin> plugins = ServiceLoader.load(
            moduleLayer, 
            Plugin.class
        );
        
        plugins.forEach(plugin -> {
            String name = plugin.getName();
            providers.computeIfAbsent(name, k -> {
                plugin.initialize();
                return plugin.getServiceProvider();
            });
        });
    }

    private Path[] getModulePaths() {
        return Stream.of("modules", "plugins")
            .map(dir -> Paths.get(System.getProperty("app.home"), dir))
            .filter(Files::exists)
            .toArray(Path[]::new);
    }
}
```

### 9. Testing Patterns and Best Practices

#### 9.1 Advanced Testing Framework
Example of a comprehensive testing framework:

```java
public class TestFramework {
    // Custom test runner with parallel execution
    public class ParallelTestRunner extends BlockJUnit4ClassRunner {
        public ParallelTestRunner(Class<?> klass) throws InitializationError {
            super(klass);
        }

        @Override
        protected void runChild(FrameworkMethod method, RunNotifier notifier) {
            CompletableFuture.runAsync(() -> 
                super.runChild(method, notifier));
        }
    }

    // Test context management
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface TestData {
        String value();
    }

    public class TestContextManager {
        private final Map<String, Object> testData = new ConcurrentHashMap<>();

        public void setupTest(Description description) {
            TestData annotation = description.getAnnotation(TestData.class);
            if (annotation != null) {
                loadTestData(annotation.value());
            }
        }

        private void loadTestData(String resource) {
            try (InputStream is = getClass().getResourceAsStream(resource)) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> data = mapper.readValue(is, Map.class);
                testData.putAll(data);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load test data", e);
            }
        }
    }

    // Performance test utilities
    public class PerformanceTestUtils {
        private final DescriptiveStatistics statistics = 
            new DescriptiveStatistics();

        public void measureLatency(Runnable operation, int iterations) {
            for (int i = 0; i < iterations; i++) {
                long start = System.nanoTime();
                operation.run();
                long end = System.nanoTime();
                statistics.addValue((end - start) / 1_000_000.0); // Convert to ms
            }
        }

        public PerformanceReport generateReport() {
            return new PerformanceReport(
                statistics.getMean(),
                statistics.getPercentile(95),
                statistics.getPercentile(99),
                statistics.getMax(),
                statistics.getStandardDeviation()
            );
        }
    }
}

// Example usage
@RunWith(ParallelTestRunner.class)
public class ServiceTest {
    @Rule
    public TestContextManager context = new TestContextManager();
    
    private PerformanceTestUtils perfUtils = new PerformanceTestUtils();

    @Test
    @TestData("/testdata/orders.json")
    public void testOrderProcessing() {
        perfUtils.measureLatency(() -> {
            // Test implementation
        }, 1000);

        PerformanceReport report = perfUtils.generateReport();
        assertThat(report.getP95Latency()).isLessThan(100.0);
    }
}
```

Would you like me to continue with:
1. Exception Handling Patterns
2. Design Patterns in Java
3. Advanced Collection Implementations
4. Debugging and Profiling Techniques

Let me know which topics you'd like to explore next!### 4.2 JMX and Advanced Monitoring

#### Custom MBean Implementation
Example of a comprehensive application monitoring system:

```java
// MBean interface
public interface ApplicationMonitorMBean {
    // Metrics
    long getTotalRequests();
    long getAverageResponseTime();
    Map<String, Long> getEndpointStatistics();
    
    // Operations
    void resetStatistics();
    void setAlertThreshold(long milliseconds);
    
    // Notifications
    void enableAlerts(boolean enabled);
}

// MBean implementation with notifications
@MXBean
public class ApplicationMonitor extends NotificationBroadcasterSupport 
        implements ApplicationMonitorMBean {
    private final AtomicLong totalRequests = new AtomicLong();
    private final AtomicLong totalResponseTime = new AtomicLong();
    private final ConcurrentMap<String, EndpointStats> endpointStats = 
        new ConcurrentHashMap<>();
    private volatile long alertThreshold = 1000;
    private volatile boolean alertsEnabled = true;
    private final AtomicLong notificationSequence = new AtomicLong();

    private static class EndpointStats {
        AtomicLong count = new AtomicLong();
        AtomicLong totalTime = new AtomicLong();
        AtomicLong maxTime = new AtomicLong();

        void record(long responseTime) {
            count.incrementAndGet();
            totalTime.addAndGet(responseTime);
            maxTime.accumulateAndGet(responseTime, Math::max);
        }
    }

    public void recordRequest(String endpoint, long responseTime) {
        totalRequests.incrementAndGet();
        totalResponseTime.addAndGet(responseTime);
        
        endpointStats.computeIfAbsent(endpoint, 
            k -> new EndpointStats()).record(responseTime);

        if (alertsEnabled && responseTime > alertThreshold) {
            Notification n = new AttributeChangeNotification(
                this,
                notificationSequence.incrementAndGet(),
                System.currentTimeMillis(),
                String.format("Slow request on %s: %dms", endpoint, responseTime),
                "ResponseTime",
                "long",
                alertThreshold,
                responseTime
            );
            sendNotification(n);
        }
    }

    @Override
    public long getTotalRequests() {
        return totalRequests.get();
    }

    @Override
    public long getAverageResponseTime() {
        long requests = totalRequests.get();
        return requests > 0 ? totalResponseTime.get() / requests : 0;
    }

    @Override
    public Map<String, Long> getEndpointStatistics() {
        return endpointStats.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().count.get()
            ));
    }

    @Override
    public void resetStatistics() {
        totalRequests.set(0);
        totalResponseTime.set(0);
        endpointStats.clear();
    }

    @Override
    public void setAlertThreshold(long milliseconds) {
        this.alertThreshold = milliseconds;
    }

    @Override
    public void enableAlerts(boolean enabled) {
        this.alertsEnabled = enabled;
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[]{
            new MBeanNotificationInfo(
                new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE},
                AttributeChangeNotification.class.getName(),
                "Response time threshold exceeded"
            )
        };
    }
}

// Spring Boot integration
@Configuration
public class MonitoringConfig {
    @Bean
    public ApplicationMonitor applicationMonitor() {
        ApplicationMonitor monitor = new ApplicationMonitor();
        
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName(
                "com.example:type=ApplicationMonitor");
            mbs.registerMBean(monitor, name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register MBean", e);
        }
        
        return monitor;
    }
}
```

### 5. Security Implementation Patterns

#### 5.1 Secure Password Storage
Example of secure password handling:

```java
public class PasswordService {
    private static final int ITERATIONS = 210000;
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;
    private final SecureRandom random;

    public PasswordService() {
        this.random = new SecureRandom();
    }

    public String hashPassword(String password) throws Exception {
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(),
            salt,
            ITERATIONS,
            HASH_LENGTH * 8
        );

        SecretKeyFactory factory = SecretKeyFactory.getInstance(
            "PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        // Format: iterations:salt:hash
        return ITERATIONS + ":" + 
               Base64.getEncoder().encodeToString(salt) + ":" +
               Base64.getEncoder().encodeToString(hash);
    }

    public boolean verifyPassword(String password, String storedHash) 
            throws Exception {
        String[] parts = storedHash.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = Base64.getDecoder().decode(parts[1]);
        byte[] hash = Base64.getDecoder().decode(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(),
            salt,
            iterations,
            hash.length * 8
        );

        SecretKeyFactory factory = SecretKeyFactory.getInstance(
            "PBKDF2WithHmacSHA256");
        byte[] testHash = factory.generateSecret(spec).getEncoded();

        return MessageDigest.isEqual(hash, testHash);
    }
}
```

#### 5.2 JWT Token Handler
Secure JWT implementation:

```java
public class JWTService {
    private final Key signingKey;
    private final long tokenValidityInMilliseconds;

    public JWTService(@Value("${security.jwt.secret}") String secret,
                     @Value("${security.jwt.validity}") long validityInSeconds) {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = validityInSeconds * 1000;
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        long now = System.currentTimeMillis();

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim("auth", authorities)
            .setIssuedAt(new Date(now))
            .setExpiration(new Date(now + tokenValidityInMilliseconds))
            .signWith(signingKey, SignatureAlgorithm.HS512)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
            .stream(claims.get("auth").toString().split(","))
            .filter(auth -> !auth.trim().isEmpty())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}
```

### 6. Reactive Programming Patterns

#### 6.1 Reactive Service Implementation
Example of a reactive service using Project Reactor:

```java
@Service
public class ReactiveOrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;

    public Flux<OrderStatus> processOrders(Flux<Order> orders) {
        return orders
            .flatMap(this::validateOrder)
            .flatMap(this::checkInventory)
            .flatMap(this::processPayment)
            .flatMap(this::updateInventory)
            .flatMap(this::notifyUser)
            .onErrorResume(this::handleError)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                .filter(this::isRetryableError))
            .doOnNext(this::metrics);
    }

    private Mono<Order> validateOrder(Order order) {
        return Mono.just(order)
            .filter(this::isValidOrder)
            .switchIfEmpty(Mono.error(
                new OrderValidationException("Invalid order")));
    }

    private Mono<Order> checkInventory(Order order) {
        return inventoryService.checkStock(order.getItems())
            .flatMap(available -> available ? 
                Mono.just(order) : 
                Mono.error(new OutOfStockException()));
    }

    private Mono<Order> processPayment(Order order) {
        return paymentService.processPayment(order)
            .timeout(Duration.ofSeconds(5))
            .doOnSuccess(result -> 
                log.info("Payment processed for order {}", order.getId()))
            .doOnError(error -> 
                log.error("Payment failed for order {}", order.getId(), error));
    }

    private boolean isRetryableError(Throwable error) {
        return error instanceof TimeoutException ||
               error instanceof ConnectionException;
    }

    private Mono<OrderStatus> handleError(Throwable error) {
        if (error instanceof OutOfStockException) {
            return Mono.just(OrderStatus.OUT_OF_STOCK);
        }
        if (error instanceof PaymentFailedException) {
            return Mono.just(OrderStatus.PAYMENT_FAILED);
        }
        return Mono.just(OrderStatus.ERROR);
    }

    private void metrics(OrderStatus status) {
        MetricsRegistry.counter("order.status", "status", status.name())
            .increment();
    }
}
```

Would you like me to continue with:
1. Advanced I/O and NIO.2 Features
2. Java Module System Patterns
3. Testing Patterns and Best Practices
4. Advanced Exception Handling Patterns

Let me know which section you'd like me to cover next!# Java Principal Engineer Interview Guide

## Table of Contents
1. Core Java Features
   - Java 8+ Features
   - Generics and Type System
   - Reflection and Annotations
2. Memory Management
   - Garbage Collection
   - Memory Model
   - Memory Leaks
3. Concurrency and Threading
   - Thread Pools
   - Synchronization
   - Lock-Free Programming
4. Performance and Monitoring
   - JFR and Profiling
   - JMX
   - Tuning
5. Advanced Topics
   - Module System
   - Class Loading
   - Serialization
   - Security

## 1. Core Java Features

### 1.1 Java 8+ Features

#### What is Stream API and how does it work internally?
The Stream API provides a functional approach to processing collections of objects. It works through a pipeline architecture that enables lazy evaluation and optimization.

Key Concepts:
- Lazy evaluation
- Intermediate vs terminal operations
- Parallel stream processing
- Short-circuiting operations

```java
// Example demonstrating Stream internals
List<String> names = Arrays.asList("John", "Jane", "Bob");
Stream<String> stream = names.stream()
    .filter(name -> {
        System.out.println("Filtering " + name); // Lazy evaluation demonstration
        return name.startsWith("J");
    })
    .map(name -> {
        System.out.println("Mapping " + name);   
        return name.toUpperCase();
    });

// No output yet - lazy evaluation
List<String> result = stream.collect(Collectors.toList()); // Now pipeline executes
```

#### What are the advanced patterns in CompletableFuture?
CompletableFuture is Java's implementation of Promise pattern for asynchronous programming.

Key Features:
- Composition of async operations
- Error handling
- Thread pool management
- Timeouts and cancellation

```java
public class AsyncPatterns {
    public CompletableFuture<Order> processOrder(long orderId) {
        return CompletableFuture
            .supplyAsync(() -> fetchOrder(orderId))
            .thenCombine(
                CompletableFuture.supplyAsync(() -> fetchUserProfile(orderId)),
                (order, profile) -> enrichOrderWithProfile(order, profile)
            )
            .thenComposeAsync(order -> 
                validateInventory(order)
                    .thenCompose(this::processPayment)
            )
            .exceptionally(ex -> {
                logError(ex);
                throw new OrderProcessingException(ex);
            });
    }

    // Timeout pattern
    public <T> CompletableFuture<T> withTimeout(CompletableFuture<T> future, 
            Duration timeout) {
        CompletableFuture<T> timeoutFuture = new CompletableFuture<>();
        Executors.newScheduledThreadPool(1).schedule(
            () -> timeoutFuture.completeExceptionally(
                new TimeoutException("Operation timed out")),
            timeout.toMillis(), 
            TimeUnit.MILLISECONDS);
        return CompletableFuture.anyOf(future, timeoutFuture)
            .thenApply(obj -> (T) obj);
    }
}
```

#### How does the Optional class work and what are its anti-patterns?
Optional is a container object used to represent nullable values and avoid null pointer exceptions.

Common Anti-patterns:
- Using Optional as method parameter
- Creating Optional.of(null)
- Optional of primitive
- Optional.get() without checking

```java
public class OptionalPatterns {
    // Good patterns
    public Optional<User> findUser(String id) {
        return Optional.ofNullable(userRepository.findById(id))
            .filter(User::isActive)
            .map(this::enrichUser);
    }

    // Handling multiple Optionals
    public Optional<String> getDisplayName(String userId) {
        return findUser(userId)
            .flatMap(User::getDisplayName)
            .or(() -> findUser(userId).map(User::getEmail))
            .or(() -> Optional.of("Anonymous"));
    }
}
```

### 1.2 Generics and Type System

#### What is type erasure and how does it affect generic code?
Type erasure is Java's implementation of generics where type information is removed at runtime.

Impact:
- Runtime type checking limitations
- Bridge methods
- Type parameter bounds
- Array creation with generics

```java
public class GenericExample<T> {
    // Type erasure example
    public static <T> List<T> createList(Class<T> type) {
        // Can't create generic array directly due to type erasure
        // T[] array = new T[10]; // Won't compile
        
        // Workaround using reflection
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(type, 10);
        return Arrays.asList(array);
    }

    // Bridge method demonstration
    public class StringContainer extends GenericExample<String> {
        // Bridge method created by compiler:
        // public Object getValue() { return (Object) getValue(); }
        public String getValue() {
            return "value";
        }
    }
}
```

#### What is PECS principle and when should it be used?
PECS (Producer Extends, Consumer Super) is a guideline for using bounded wildcards in generic types.

Usage:
- Use extends when reading (Producer)
- Use super when writing (Consumer)
- Avoid wildcards when both reading and writing

```java
public class PECSExample {
    // Producer extends example
    public void readOnly(List<? extends Number> numbers) {
        Number first = numbers.get(0);    // OK - reading
        // numbers.add(new Integer(1));   // Won't compile - can't write
    }

    // Consumer super example
    public void writeOnly(List<? super Integer> numbers) {
        numbers.add(42);                  // OK - writing
        // Integer first = numbers.get(0);// Won't compile - can't read specific type
    }

    // Practical example
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }
}
```

### 1.3 Reflection and Annotations

#### How does the reflection API work and what are its performance implications?
Reflection allows inspection and modification of classes, methods, and fields at runtime.

Considerations:
- Performance overhead
- Security implications
- Access control
- Cache metadata when possible

```java
public class ReflectionExample {
    // Performance optimized reflection
    private static final Map<Class<?>, Method[]> methodCache = 
        new ConcurrentHashMap<>();

    public static Method[] getMethods(Class<?> clazz) {
        return methodCache.computeIfAbsent(clazz, Class::getDeclaredMethods);
    }

    // Custom annotation processor
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Cached {
        long timeoutMs() default 1000;
    }

    public class CacheAspect {
        public Object invokeCached(Method method, Object[] args) 
                throws Exception {
            Cached cached = method.getAnnotation(Cached.class);
            if (cached != null) {
                String key = generateCacheKey(method, args);
                return cache.computeIfAbsent(key, k -> {
                    try {
                        return method.invoke(this, args);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            return method.invoke(this, args);
        }
    }
}
```

## 2. Memory Management

### Real-World Use Cases

#### 1. Stream API in Microservices

Consider a microservice processing order data:
```java
@Service
public class OrderAnalyticsService {
    public OrderAnalytics processOrders(List<Order> orders) {
        return orders.stream()
            .filter(order -> order.getStatus() != OrderStatus.CANCELLED)
            .collect(groupingBy(
                Order::getCustomerId,
                collectingAndThen(
                    mapping(Order::getTotal, toList()),
                    this::calculateCustomerMetrics
                )
            )).entrySet().stream()
            .map(entry -> new CustomerOrderAnalytics(
                entry.getKey(), 
                entry.getValue()
            ))
            .collect(toCollection(OrderAnalytics::new));
    }

    private CustomerMetrics calculateCustomerMetrics(List<BigDecimal> totals) {
        BigDecimal sum = totals.stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avg = sum.divide(
            BigDecimal.valueOf(totals.size()), 
            RoundingMode.HALF_UP
        );
        return new CustomerMetrics(sum, avg, totals.size());
    }
}
```

Key Benefits:
- Declarative data processing
- Easy parallelization
- Reduced cognitive complexity
- Better maintainability

#### 2. CompletableFuture in Event-Driven Systems

Real-world scenario of order processing system:
```java
@Service
public class OrderFulfillmentService {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final NotificationService notificationService;

    public CompletableFuture<OrderResult> processOrder(Order order) {
        return validateOrder(order)
            .thenCompose(validOrder -> 
                CompletableFuture.allOf(
                    reserveInventory(validOrder),
                    processPayment(validOrder)
                ).thenApply(v -> validOrder)
            )
            .thenCompose(this::arrangeShipping)
            .thenCompose(this::sendNotifications)
            .handle((result, ex) -> {
                if (ex != null) {
                    compensateFailedOrder(order, ex);
                    throw new OrderProcessingException(ex);
                }
                return result;
            });
    }

    private void compensateFailedOrder(Order order, Throwable ex) {
        // Compensation logic
        CompletableFuture.allOf(
            inventoryService.releaseReservation(order),
            paymentService.refund(order)
        ).exceptionally(compensationEx -> {
            log.error("Compensation failed", compensationEx);
            return null;
        });
    }
}
```

Advantages:
- Non-blocking operations
- Better resource utilization
- Automatic error handling
- Easy composition of async operations

#### 3. Memory Management in Caching Systems

Real-world cache implementation with memory considerations:
```java
public class MemoryAwareCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new ConcurrentHashMap<>();
    private final ReferenceQueue<V> refQueue = new ReferenceQueue<>();
    private final int maxEntries;
    private final AtomicLong evictionCount = new AtomicLong(0);

    public MemoryAwareCache(int maxEntries) {
        this.maxEntries = maxEntries;
        startCleanupThread();
    }

    private void startCleanupThread() {
        Thread cleanupThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Reference<?> ref = refQueue.remove();
                    cleanup(ref);
                    evictionCount.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        cleanupThread.setDaemon(true);
        cleanupThread.start();
    }

    public V get(K key) {
        SoftReference<V> ref = cache.get(key);
        if (ref != null) {
            V value = ref.get();
            if (value != null) {
                return value;
            } else {
                cache.remove(key);
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (cache.size() >= maxEntries) {
            evictOldest();
        }
        cache.put(key, new SoftReference<>(value, refQueue));
    }

    private void evictOldest() {
        // LRU eviction logic
        if (!cache.isEmpty()) {
            K firstKey = cache.keySet().iterator().next();
            cache.remove(firstKey);
        }
    }

    public CacheStats getStats() {
        return new CacheStats(
            cache.size(),
            evictionCount.get()
        );
    }
}
```

Memory Management Considerations:
- Soft references for memory sensitivity
- Reference queue for cleanup
- Eviction policies
- Statistics tracking
- Thread safety

## 2. Memory Management (continued)

### 2.1 Memory Leaks and Memory Model

#### Memory Leaks in Java Applications

Common causes of memory leaks:
1. Unclosed Resources
2. Static Collections
3. Improper equals/hashCode
4. ThreadLocal variables
5. Inner class references

Real-world memory leak detector:
```java
public class MemoryLeakDetector {
    private static final Logger log = LoggerFactory.getLogger(MemoryLeakDetector.class);
    
    // Track object allocations
    private final Map<String, WeakHashMap<Object, AllocationInfo>> trackedObjects = 
        new ConcurrentHashMap<>();
    
    static class AllocationInfo {
        final long timestamp;
        final Thread thread;
        final StackTraceElement[] stackTrace;
        
        AllocationInfo() {
            this.timestamp = System.currentTimeMillis();
            this.thread = Thread.currentThread();
            this.stackTrace = thread.getStackTrace();
        }
    }
    
    public void trackObject(String category, Object obj) {
        trackedObjects.computeIfAbsent(category, 
            k -> new WeakHashMap<>()).put(obj, new AllocationInfo());
    }
    
    public void analyzePotentialLeaks() {
        System.gc(); // Request GC to help identify leaks
        
        trackedObjects.forEach((category, objects) -> {
            if (!objects.isEmpty()) {
                log.warn("Potential memory leak in category: {} - {} objects", 
                    category, objects.size());
                    
                objects.forEach((obj, info) -> {
                    log.warn("Leaked object allocated by thread: {} at time: {}", 
                        info.thread.getName(), 
                        new Date(info.timestamp));
                    
                    log.warn("Stack trace:");
                    Arrays.stream(info.stackTrace)
                        .forEach(frame -> log.warn("\tat {}", frame));
                });
            }
        });
    }
}

// Usage example in a web application
@Component
public class ResourceTracker {
    private final MemoryLeakDetector detector = new MemoryLeakDetector();
    
    @Autowired
    private MetricRegistry metrics;
    
    public void trackResource(Object resource) {
        detector.trackObject("APP_RESOURCES", resource);
        metrics.counter("resource.allocation").inc();
    }
    
    @Scheduled(fixedRate = 3600000) // Run hourly
    public void checkForLeaks() {
        detector.analyzePotentialLeaks();
    }
}
```

#### Java Memory Model (JMM)

Understanding happens-before relationships and memory visibility:
```java
public class MemoryModelExample {
    // Volatile ensures happens-before relationship
    private volatile boolean flag;
    private int data;
    
    public void writer() {
        data = 42;         // Write to data
        flag = true;       // Volatile write creates happens-before
    }
    
    public int reader() {
        if (flag) {        // Volatile read creates happens-before
            return data;   // Guaranteed to see 42
        }
        return -1;
    }
    
    // Double-Checked Locking pattern
    private static volatile MemoryModelExample instance;
    
    public static MemoryModelExample getInstance() {
        MemoryModelExample result = instance;
        if (result == null) {
            synchronized (MemoryModelExample.class) {
                result = instance;
                if (result == null) {
                    instance = result = new MemoryModelExample();
                }
            }
        }
        return result;
    }
}
```

Key Memory Model Concepts:
1. Happens-Before Relationship
   - Program order
   - Monitor lock/unlock
   - Volatile read/write
   - Thread start/join

2. Memory Barriers
   - LoadLoad barriers
   - StoreStore barriers
   - LoadStore barriers
   - StoreLoad barriers

## 3. Advanced Concurrency Patterns

### 3.1 Thread Pool Patterns and Custom Implementations

#### Custom Thread Pool with Monitoring
Real-world example of a monitored thread pool:

```java
public class MonitoredThreadPool extends ThreadPoolExecutor {
    private final ConcurrentMap<Runnable, Long> taskStartTimes;
    private final MetricRegistry metrics;
    private final Timer taskTimer;
    private final Counter activeTasksCounter;
    private final Counter completedTasksCounter;
    private final Counter rejectedTasksCounter;

    public MonitoredThreadPool(int corePoolSize, 
                             int maximumPoolSize,
                             long keepAliveTime,
                             TimeUnit unit,
                             BlockingQueue<Runnable> workQueue,
                             MetricRegistry metrics) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.taskStartTimes = new ConcurrentHashMap<>();
        this.metrics = metrics;
        this.taskTimer = metrics.timer("thread-pool.task.duration");
        this.activeTasksCounter = metrics.counter("thread-pool.tasks.active");
        this.completedTasksCounter = metrics.counter("thread-pool.tasks.completed");
        this.rejectedTasksCounter = metrics.counter("thread-pool.tasks.rejected");
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        taskStartTimes.put(r, System.nanoTime());
        activeTasksCounter.inc();
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            Long startTime = taskStartTimes.remove(r);
            if (startTime != null) {
                long duration = System.nanoTime() - startTime;
                taskTimer.update(duration, TimeUnit.NANOSECONDS);
            }
            activeTasksCounter.dec();
            completedTasksCounter.inc();
            
            if (t != null) {
                metrics.meter("thread-pool.tasks.errors").mark();
            }
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        metrics.remove("thread-pool.task.duration");
        metrics.remove("thread-pool.tasks.active");
        metrics.remove("thread-pool.tasks.completed");
        metrics.remove("thread-pool.tasks.rejected");
        super.terminated();
    }
}

// Usage example
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService monitoredExecutor(MetricRegistry metrics) {
        return new MonitoredThreadPool(
            10,                 // core pool size 
            20,                // max pool size
            60L,               // keep alive
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            metrics
        );
    }
}
```

### 3.2 Advanced Lock Patterns

#### Custom Lock Implementation with Fairness
Example of a custom lock with timeout and fairness:

```java
public class TimeoutFairLock {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waitQueue = new ConcurrentLinkedQueue<>();
    private final ThreadLocal<Integer> recursionCount = ThreadLocal.withInitial(() -> 0);
    
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        Thread current = Thread.currentThread();
        int count = recursionCount.get();
        
        if (count > 0) {
            // Handle reentrant case
            recursionCount.set(count + 1);
            return true;
        }
        
        waitQueue.offer(current);
        
        long deadline = System.nanoTime() + unit.toNanos(timeout);
        while (true) {
            if (waitQueue.peek() == current && 
                locked.compareAndSet(false, true)) {
                waitQueue.poll();
                recursionCount.set(1);
                return true;
            }
            
            long remainingNanos = deadline - System.nanoTime();
            if (remainingNanos <= 0) {
                waitQueue.remove(current);
                return false;
            }
            
            LockSupport.parkNanos(Math.min(remainingNanos, 1000000));
            
            if (Thread.interrupted()) {
                waitQueue.remove(current);
                throw new InterruptedException();
            }
        }
    }
    
    public void unlock() {
        int count = recursionCount.get();
        if (count == 0) {
            throw new IllegalMonitorStateException();
        }
        
        if (count > 1) {
            recursionCount.set(count - 1);
            return;
        }
        
        recursionCount.remove();
        locked.set(false);
        
        // Wake up next waiting thread
        Thread next = waitQueue.peek();
        if (next != null) {
            LockSupport.unpark(next);
        }
    }
}
```

### 3.3 Non-Blocking Algorithms

#### Lock-Free Queue Implementation
Example of a lock-free queue with memory reclamation:

```java
public class LockFreeQueue<T> {
    private static class Node<T> {
        final T value;
        volatile Node<T> next;
        
        Node(T value) {
            this.value = value;
        }
    }
    
    private final Node<T> dummy = new Node<>(null);
    private final AtomicReference<Node<T>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<T>> tail = new AtomicReference<>(dummy);
    
    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        while (true) {
            Node<T> curTail = tail.get();
            Node<T> tailNext = curTail.next;
            
            if (curTail == tail.get()) {
                if (tailNext == null) {
                    if (curTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(curTail, newNode);
                        return;
                    }
                } else {
                    tail.compareAndSet(curTail, tailNext);
                }
            }
        }
    }
    
    public T dequeue() {
        while (true) {
            Node<T> curHead = head.get();
            Node<T> curTail = tail.get();
            Node<T> headNext = curHead.next;
            
            if (curHead == head.get()) {
                if (curHead == curTail) {
                    if (headNext == null) {
                        return null;
                    }
                    tail.compareAndSet(curTail, headNext);
                } else {
                    T value = headNext.value;
                    if (head.compareAndSet(curHead, headNext)) {
                        return value;
                    }
                }
            }
        }
    }
}
```

## 4. Performance Monitoring

### 4.1 Java Flight Recorder (JFR)

#### Custom JFR Events
Example of custom JFR events for application monitoring:

```java
@Name("com.example.RequestEvent")
@Label("HTTP Request")
@Category("Application")
@Description("HTTP request processing event")
public class RequestEvent extends Event {
    @Label("URI")
    private final String uri;
    
    @Label("Duration")
    @Timespan(Timespan.MILLISECONDS)
    private final long duration;
    
    @Label("Status")
    private final int status;
    
    public RequestEvent(String uri, long duration, int status) {
        this.uri = uri;
        this.duration = duration;
        this.status = status;
    }
    
    public static class RequestEventFactory {
        private static final AtomicLong slowRequestCounter = 
            new AtomicLong();
        
        public static void recordRequest(HttpServletRequest request,
                                      HttpServletResponse response,
                                      long duration) {
            if (RequestEvent.isEnabled()) {
                RequestEvent event = new RequestEvent(
                    request.getRequestURI(),
                    duration,
                    response.getStatus()
                );
                
                if (duration > 1000) { // 1 second threshold
                    slowRequestCounter.incrementAndGet();
                    event.commit();
                }
            }
        }
    }
}

@Component
public class RequestMonitoringFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, 
                        ServletResponse response,
                        FilterChain chain) throws IOException, ServletException {
        long startTime = System.nanoTime();
        try {
            chain.doFilter(request, response);
        } finally {
            long duration = TimeUnit.NANOSECONDS.toMillis(
                System.nanoTime() - startTime);
            RequestEvent.RequestEventFactory.recordRequest(
                (HttpServletRequest) request,
                (HttpServletResponse) response,
                duration
            );
        }
    }
}
```

Would you like me to continue with:
1. JMX and Advanced Monitoring
2. Security Implementation Patterns
3. Reactive Programming Patterns
4. Advanced I/O and NIO.2 Features

Let me know which section you'd like me to cover next!

#### What are the different GC algorithms and their use cases?
Java provides multiple garbage collection algorithms optimized for different scenarios.

GC Types:
1. Serial GC
   - Single-threaded
   - Small heaps
   - Simple application

2. Parallel GC
   - Multi-threaded
   - Batch processing
   - Focus on throughput

3. G1GC
   - Region-based
   - Large heaps
   - Low pause times

4. ZGC
   - Scalable
   - Ultra-low latency
   - Large heaps

```java
public class GCExample {
    // GC tuning parameters
    public static void main(String[] args) {
        // G1GC
        // -XX:+UseG1GC
        // -XX:MaxGCPauseMillis=200
        // -XX:G1HeapRegionSize=16M
        
        // ZGC
        // -XX:+UseZGC
        // -XX:ZCollectionInterval=5
        
        // Memory allocation pattern
        List<byte[]> largeAllocations = new ArrayList<>();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        
        executor.scheduleAtFixedRate(() -> {
            try {
                largeAllocations.add(new byte[1024 * 1024]); // 1MB
                if (largeAllocations.size() > 100) {
                    largeAllocations.subList(0, 50).clear();
                }
            } catch (OutOfMemoryError e) {
                handleOOME(e);
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    // GC monitoring
    public static void monitorGC() {
        List<GarbageCollectorMXBean> gcBeans = 
            ManagementFactory.getGarbageCollectorMXBeans();
        
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.printf(
                "GC %s - Count: %d, Time: %dms%n",
                gcBean.getName(),
                gcBean.getCollectionCount(),
                gcBean.getCollectionTime()
            );
        }
    }
}
```

[Continued in next message due to length...]

Would you like me to continue with:
1. Memory Model and Memory Leaks
2. Concurrency and Threading
3. Performance Monitoring
4. Advanced Topics

Let me know which section you'd like me to expand next!