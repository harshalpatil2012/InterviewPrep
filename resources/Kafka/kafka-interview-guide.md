#### 8.13 Advanced Troubleshooting Scenarios

1. **High Latency Investigation**:
```java
public class LatencyInvestigator {
    private final MetricsCollector metrics;
    private final KafkaAdmin admin;
    
    public LatencyReport investigateLatency(String topic) {
        // 1. Collect metrics
        Map<String, Double> producerLatencies = 
            metrics.getProducerLatencies(topic);
        Map<String, Double> brokerLatencies = 
            metrics.getBrokerLatencies(topic);
        Map<String, Double> consumerLatencies = 
            metrics.getConsumerLatencies(topic);
            
        // 2. Analyze bottlenecks
        List<LatencyHotspot> hotspots = findHotspots(
            producerLatencies,
            brokerLatencies,
            consumerLatencies
        );
        
        // 3. Generate recommendations
        return generateRecommendations(hotspots);
    }
    
    private List<LatencyHotspot> findHotspots(
            Map<String, Double> producerLatencies,
            Map<String, Double> brokerLatencies,
            Map<String, Double> consumerLatencies) {
        List<LatencyHotspot> hotspots = new ArrayList<>();
        
        // Check producer bottlenecks
        if (hasProducerBottlenecks(producerLatencies)) {
            hotspots.add(analyzeProducerBottlenecks());
        }
        
        // Check broker bottlenecks
        if (hasBrokerBottlenecks(brokerLatencies)) {
            hotspots.add(analyzeBrokerBottlenecks());
        }
        
        // Check consumer bottlenecks
        if (hasConsumerBottlenecks(consumerLatencies)) {
            hotspots.add(analyzeConsumerBottlenecks());
        }
        
        return hotspots;
    }
}

2. **Data Loss Investigation**:
```java
public class DataLossInvestigator {
    private final TopicManager topicManager;
    private final OffsetManager offsetManager;
    
    public DataLossReport investigateDataLoss(
            String topic,
            long startOffset,
            long endOffset) {
        // 1. Check broker logs
        List<LogSegment> segments = 
            topicManager.getLogSegments(topic);
            
        // 2. Check replication status
        Map<Integer, ReplicationStatus> replication = 
            topicManager.getReplicationStatus(topic);
            
        // 3. Check consumer offsets
        Map<String, Long> consumerOffsets = 
            offsetManager.getConsumerOffsets(topic);
            
        // 4. Analyze gaps
        List<OffsetGap> gaps = findOffsetGaps(
            segments,
            startOffset,
            endOffset
        );
        
        return new DataLossReport(gaps, replication);
    }
    
    private List<OffsetGap> findOffsetGaps(
            List<LogSegment> segments,
            long startOffset,
            long endOffset) {
        List<OffsetGap> gaps = new ArrayList<>();
        long currentOffset = startOffset;
        
        for (LogSegment segment : segments) {
            if (segment.getStartOffset() > currentOffset) {
                gaps.add(new OffsetGap(
                    currentOffset,
                    segment.getStartOffset()
                ));
            }
            currentOffset = segment.getEndOffset() + 1;
        }
        
        if (currentOffset < endOffset) {
            gaps.add(new OffsetGap(
                currentOffset,
                endOffset
            ));
        }
        
        return gaps;
    }
}
```

3. **Network Partition Handling**:
```java
public class NetworkPartitionHandler {
    private final ZookeeperClient zk;
    private final BrokerManager brokerManager;
    
    public void handleNetworkPartition(
            Set<Integer> isolatedBrokers) {
        // 1. Detect partition
        if (isNetworkPartitioned(isolatedBrokers)) {
            // 2. Handle split-brain
            handleSplitBrain(isolatedBrokers);
            
            // 3. Recover when partition heals
            monitorAndRecover(isolatedBrokers);
        }
    }
    
    private void handleSplitBrain(
            Set<Integer> isolatedBrokers) {
        // Determine quorum
        if (hasQuorum(isol### 8. Advanced Kafka Topics and Message Flow Tracing

#### 8.1 End-to-End Message Flow Tracing

Let's trace a message from production to consumption with all internal mechanics:

```java
// 1. Message Production
public class OrderProcessor {
    private final KafkaProducer<String, Order> producer;
    
    public CompletableFuture<RecordMetadata> processOrder(Order order) {
        // Add tracing headers
        Headers headers = new RecordHeaders();
        headers.add("trace-id", UUID.randomUUID().toString().getBytes());
        headers.add("timestamp", String.valueOf(System.currentTimeMillis()).getBytes());
        headers.add("origin-service", "order-service".getBytes());

        // Create record with partition key
        ProducerRecord<String, Order> record = new ProducerRecord<>(
            "orders",                    // topic
            null,                       // partition (null for key-based)
            order.getCustomerId(),      // key
            order,                      // value
            headers                     // headers
        );

        // Internal Flow:
        // 1. Serialization
        // 2. Partitioning
        // 3. Compression
        // 4. Batching
        // 5. Sending to broker
        return new CompletableFuture<RecordMetadata>()
            .completeAsync(() -> producer.send(record));
    }
}

// 2. Broker Processing
public class BrokerMessageFlow {
    /*
    Broker Steps:
    1. Receive request
    2. Validate message
    3. Assign offset
    4. Write to commit log
    5. Update indexes
    6. Replicate to followers
    7. Send acknowledgment
    */
    
    public class LogManager {
        public void appendToLog(
                TopicPartition partition, 
                ByteBuffer message) {
            // 1. Write to segment file
            long offset = segment.append(message);
            
            // 2. Update offset index
            offsetIndex.append(offset, segment.position());
            
            // 3. Update timestamp index
            timestampIndex.append(
                message.timestamp(), 
                offset
            );
            
            // 4. Trigger replication if needed
            if (config.minInSyncReplicas > 1) {
                replicateToFollowers(partition, offset);
            }
        }
    }
}

// 3. Consumer Processing
public class OrderConsumer {
    private final KafkaConsumer<String, Order> consumer;
    private final OrderService orderService;
    
    public void consumeOrders() {
        while (true) {
            // Fetch records with timeout
            ConsumerRecords<String, Order> records = 
                consumer.poll(Duration.ofMillis(100));
            
            for (ConsumerRecord<String, Order> record : records) {
                // Extract tracing information
                String traceId = new String(
                    record.headers().lastHeader("trace-id").value()
                );
                
                // Process record
                try {
                    processOrder(record, traceId);
                    // Commit offset after successful processing
                    consumer.commitSync(
                        Collections.singletonMap(
                            new TopicPartition(
                                record.topic(), 
                                record.partition()
                            ),
                            new OffsetAndMetadata(record.offset() + 1)
                        )
                    );
                } catch (Exception e) {
                    handleError(record, e, traceId);
                }
            }
        }
    }
}
```

#### 8.2 Advanced Replication Patterns

1. **Multi-Region Replication Strategies**:
```java
public class ReplicationManager {
    public enum ReplicationType {
        ACTIVE_ACTIVE,
        ACTIVE_PASSIVE,
        ACTIVE_STANDBY
    }
    
    public class ReplicationConfig {
        private final ReplicationType type;
        private final Map<String, Priority> regionPriorities;
        private final ConflictResolutionStrategy conflictStrategy;
        
        // Active-Active Configuration
        public static ReplicationConfig activeActive() {
            return new ReplicationConfig(
                ReplicationType.ACTIVE_ACTIVE,
                Map.of(
                    "us-east", Priority.PRIMARY,
                    "eu-west", Priority.PRIMARY,
                    "ap-south", Priority.PRIMARY
                ),
                new LastWriteWinsStrategy()
            );
        }
        
        // Active-Passive Configuration
        public static ReplicationConfig activePassive() {
            return new ReplicationConfig(
                ReplicationType.ACTIVE_PASSIVE,
                Map.of(
                    "us-east", Priority.PRIMARY,
                    "eu-west", Priority.SECONDARY,
                    "ap-south", Priority.SECONDARY
                ),
                new PrimaryWinsStrategy()
            );
        }
    }
}
```

2. **Conflict Resolution**:
```java
public interface ConflictResolutionStrategy {
    Message resolveConflict(Message message1, Message message2);
}

// Vector Clock Implementation
public class VectorClockStrategy implements ConflictResolutionStrategy {
    private final Map<String, Long> vectorClock = new ConcurrentHashMap<>();
    
    public Message resolveConflict(
            Message message1, 
            Message message2) {
        VectorClock vc1 = message1.getVectorClock();
        VectorClock vc2 = message2.getVectorClock();
        
        if (vc1.happenedBefore(vc2)) {
            return message2;
        } else if (vc2.happenedBefore(vc1)) {
            return message1;
        } else {
            // Concurrent modifications
            return mergeMessages(message1, message2);
        }
    }
}
```

#### 8.3 Advanced Partitioning Strategies

1. **Custom Partitioner Implementation**:
```java
public class GeoAwarePartitioner implements Partitioner {
    private final Map<String, Integer> regionToPartition;
    
    @Override
    public int partition(
            String topic,
            Object key,
            byte[] keyBytes,
            Object value,
            byte[] valueBytes,
            Cluster cluster) {
        
        // Extract region from key or value
        String region = extractRegion(key, value);
        
        // Get preferred partition for region
        Integer preferredPartition = 
            regionToPartition.get(region);
        
        if (preferredPartition != null) {
            return preferredPartition;
        }
        
        // Fallback to default partitioning
        return defaultPartition(
            topic, 
            key, 
            keyBytes, 
            cluster
        );
    }
}
```

2. **Dynamic Partition Assignment**:
```java
public class DynamicPartitionAssignor 
        implements ConsumerPartitionAssignor {
    
    @Override
    public Map<String, Assignment> assign(
            Map<String, Subscription> subscriptions,
            Map<String, Cluster> clusters) {
        
        // Consider factors like:
        // - Consumer capacity
        // - Network topology
        // - Data locality
        // - Current load
        Map<String, Assignment> assignments = 
            new HashMap<>();
            
        for (Map.Entry<String, Subscription> entry : 
                subscriptions.entrySet()) {
            
            String consumerId = entry.getKey();
            Subscription subscription = entry.getValue();
            
            // Calculate optimal assignment
            List<TopicPartition> assignedPartitions = 
                calculateOptimalAssignment(
                    consumerId,
                    subscription,
                    clusters
                );
                
            assignments.put(
                consumerId,
                new Assignment(assignedPartitions)
            );
        }
        
        return assignments;
    }
}
```

#### 8.4 Advanced Monitoring and Troubleshooting

1. **Custom Metrics Collection**:
```java
public class KafkaMetricsCollector {
    private final MetricRegistry metrics;
    private final Map<String, Counter> counters;
    private final Map<String, Timer> timers;
    
    public void recordLatency(
            String operation, 
            long startTime) {
        Timer timer = timers.get(operation);
        timer.update(
            Duration.ofMillis(
                System.currentTimeMillis() - startTime
            )
        );
    }
    
    public void recordError(
            String operation, 
            Exception e) {
        Counter errorCounter = 
            counters.get(operation + ".error");
        errorCounter.inc();
        
        // Record error details
        metrics.counter(
            operation + ".error." + e.getClass().getSimpleName()
        ).inc();
    }
}
```

2. **Automated Recovery Procedures**:
```java
public class AutoRecoveryManager {
    private final KafkaAdminClient adminClient;
    private final Map<String, RecoveryStrategy> strategies;
    
    public void handleBrokerFailure(int brokerId) {
        // 1. Check broker status
        if (isBrokerDown(brokerId)) {
            // 2. Trigger leader election
            triggerLeaderElection(brokerId);
            
            // 3. Rebalance partitions
            rebalancePartitions();
            
            // 4. Verify cluster health
            verifyClusterHealth();
        }
    }
    
    private void triggerLeaderElection(int brokerId) {
        // Find all partitions where failed broker was leader
        Set<TopicPartition> affectedPartitions = 
            getAffectedPartitions(brokerId);
            
        // Elect new leaders
        adminClient.electLeaders(
            ElectionType.PREFERRED,
            affectedPartitions
        );
    }
}
```

#### 8.5 Advanced Security Patterns

1. **Fine-grained Authorization**:
```java
public class CustomAuthorizer implements Authorizer {
    private final Map<String, ACL> resourceAcls;
    
    @Override
    public boolean authorize(
            RequestContext context,
            List<Action> actions) {
        
        for (Action action : actions) {
            // Check resource ACLs
            ACL acl = resourceAcls.get(
                action.resourcePattern().name()
            );
            
            if (!acl.allows(
                    context.principal(), 
                    action.operation())) {
                return false;
            }
            
            // Check additional conditions
            if (!checkAdditionalConditions(
                    context, 
                    action)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean checkAdditionalConditions(
            RequestContext context,
            Action action) {
        // Check time-based access
        if (!isWithinAllowedTime(context)) {
            return false;
        }
        
        // Check rate limits
        if (isRateLimitExceeded(context)) {
            return false;
        }
        
        return true;
    }
}
```

2. **Encryption at Rest**:
```java
public class EncryptionManager {
    private final KeyManager keyManager;
    private final EncryptionProvider provider;
    
    public byte[] encryptMessage(
            byte[] message,
            String topic) {
        // Get encryption key for topic
        Key key = keyManager.getKey(topic);
        
        // Encrypt message
        byte[] encryptedMessage = provider.encrypt(
            message,
            key
        );
        
        // Add encryption metadata
        return addMetadata(
            encryptedMessage,
            key.getVersion()
        );
    }
    
    public byte[] decryptMessage(
            byte[] encryptedMessage,
            String topic) {
        // Extract key version
        KeyVersion version = extractKeyVersion(
            encryptedMessage
        );
        
        // Get decryption key
        Key key = keyManager.getKey(
            topic,
            version
        );
        
        // Decrypt message
        return provider.decrypt(
            removeMetadata(encryptedMessage),
            key
        );
    }
}
```

#### 8.6 Performance Optimization Techniques

1. **Producer Optimizations**:
```java
public class OptimizedProducer {
    public Properties getOptimizedConfig() {
        Properties props = new Properties();
        
        // Batching configuration
        props.put(
            "linger.ms",
            "5"  // Wait up to 5ms for batching
        );
        props.put(
            "batch.size",
            "100000"  // 100KB batch size
        );
        
        // Compression
        props.put(
            "compression.type",
            "lz4"  // Fast compression
        );
        
        // Buffer memory
        props.put(
            "buffer.memory",
            "67108864"  // 64MB buffer
        );
        
        // Retries and timeout
        props.put("retries", "3");
        props.put(
            "retry.backoff.ms",
            "100"
        );
        
        return props;
    }
}
```

2. **Consumer Optimizations**:
```java
public class OptimizedConsumer {
    public Properties getOptimizedConfig() {
        Properties props = new Properties();
        
        // Fetch configuration
        props.put(
            "fetch.min.bytes",
            "1048576"  // 1MB minimum fetch
        );
        props.put(
            "fetch.max.wait.ms",
            "500"  // Max wait time for fetch
        );
        
        // Consumer thread configuration
        props.put(
            "max.poll.records",
            "500"  // Max records per poll
        );
        
        // Socket configuration
        props.put(
            "receive.buffer.bytes",
            "65536"  // 64KB receive buffer
        );
        
        return props;
    }
    
    public void processRecordsParallel(
            ConsumerRecords<String, String> records) {
        // Process records in parallel
        ExecutorService executor = 
            Executors.newFixedThreadPool(
                Runtime.getRuntime()
                    .availableProcessors()
            );
            
        // Create tasks for parallel processing
        List<CompletableFuture<Void>> futures = 
            new ArrayList<>();
            
        for (ConsumerRecord<String, String> record : 
                records) {
            futures.add(
                CompletableFuture.runAsync(
                    () -> processRecord(record),
                    executor
                )
            );
        }
        
        // Wait for all processing to complete
        CompletableFuture.allOf(
            futures.toArray(new CompletableFuture[0])
        ).join();
    }
}
```

#### 8.7 Disaster Recovery and Backup Strategies

1. **Backup Management**:
```java
public class BackupManager {
    public class BackupConfig {
        private final Duration backupInterval;
        private final int retentionCount;
        private final String backupLocation;
        
        public static BackupConfig production() {
            return new BackupConfig(
                Duration.ofHours(6),
                5,  // Keep last 5 backups
                "s3://backup-bucket/kafka"
            );
        }
    }
    
    public void performBackup() {
        // 1. Create consistent snapshot
        String snapshotId = createSnapshot();
        
        // 2. Upload to backup location
        uploadSnapshot(snapshotId);
        
        // 3. Clean up old backups
        cleanupOldBackups();
    }
    
    public void restore(String snapshotId) {
        // 1. Stop cluster
        stop# Apache Kafka Principal Engineer Interview Guide

## Core Concepts and Architecture

### 1. Globally Distributed Kafka Architecture and Message Flow

#### 1.1 Architecture Components

A globally distributed Kafka deployment consists of multiple interconnected components designed for high availability and fault tolerance:

1. **Multi-Datacenter Setup**:
   - US East (Primary)
   - Europe (Secondary)
   - Asia Pacific (Secondary)
   - Each DC runs independent Kafka clusters

2. **Per-Datacenter Components**:
   ```
   - 3+ Kafka Brokers (min n+1 for fault tolerance)
   - ZooKeeper Ensemble (3 or 5 nodes)
   - Producer Applications
   - Consumer Groups
   - Monitoring Infrastructure
   ```

3. **Cross-DC Replication**:
   - MirrorMaker 2.0 for async replication
   - Active-Active or Active-Passive setup
   - Conflict resolution strategies

#### 1.2 End-to-End Message Flow

Let's follow a message from production to consumption in a global setup:

```java
// 1. Producer writes message
public class GlobalProducer {
    private final KafkaProducer<String, String> producer;
    
    public void sendMessage(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(
            "global-topic",
            calculatePartitionKey(message),  // Ensures geographic affinity
            message
        );
        
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                logSuccess(metadata.partition(), 
                    metadata.offset(),
                    metadata.timestamp());
            } else {
                handleFailure(exception);
            }
        });
    }
    
    private String calculatePartitionKey(String message) {
        // Implement routing logic based on message content
        return LocationBasedRouter.getPartitionKey(message);
    }
}

// 2. Broker Processing
// broker-config.properties
inter.broker.protocol.version=3.0
replica.lag.time.max.ms=30000
min.insync.replicas=2
unclean.leader.election.enable=false

// 3. Consumer Processing
public class GlobalConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final ProcessingService processor;
    
    public void startConsuming() {
        while (true) {
            ConsumerRecords<String, String> records = 
                consumer.poll(Duration.ofMillis(100));
            
            for (ConsumerRecord<String, String> record : records) {
                // Check message origin
                String originDC = 
                    record.headers().lastHeader("origin-dc").value();
                
                // Apply DC-specific processing logic
                processor.processMessage(record.value(), originDC);
                
                // Commit offset after successful processing
                consumer.commitSync(
                    Collections.singletonMap(
                        new TopicPartition(
                            record.topic(), 
                            record.partition()
                        ),
                        new OffsetAndMetadata(record.offset() + 1)
                    )
                );
            }
        }
    }
}
```

#### 1.3 Replication and High Availability

1. **Cross-DC Replication Configuration**:
```properties
# MirrorMaker 2.0 config
clusters=us-east,europe,asia
us-east.bootstrap.servers=kafka1:9092,kafka2:9092,kafka3:9092
europe.bootstrap.servers=eu-kafka1:9092,eu-kafka2:9092
asia.bootstrap.servers=asia-kafka1:9092,asia-kafka2:9092

# Replication policies
replication.factor=3
sync.topic.acls.enabled=true
refresh.topics.enabled=true
refresh.groups.enabled=true
```

2. **Failure Handling**:
```java
public class FailoverManager {
    private static final int FAILOVER_TIMEOUT_MS = 30000;
    
    public void handleBrokerFailure(String brokerId) {
        // 1. Detect failure through monitoring
        if (isLeaderBroker(brokerId)) {
            triggerLeaderElection();
        }
        
        // 2. Rebalance partitions
        rebalancePartitions();
        
        // 3. Update routing metadata
        updateRoutingMetadata();
    }
    
    public void handleDataCenterFailure(String dcId) {
        // 1. Switch traffic to backup DC
        routeTrafficToBackupDC(dcId);
        
        // 2. Initialize failover procedures
        startFailoverProcedures(dcId);
        
        // 3. Monitor recovery
        monitorRecovery(dcId);
    }
}
```

#### 1.4 Production Monitoring

1. **Key Metrics to Monitor**:
```yaml
Broker Health:
  - Under-replicated partitions
  - ISR shrink/expansion rate
  - Leader election rate
  
Cross-DC Replication:
  - Replication lag by DC
  - MirrorMaker lag
  - Cross-DC latency
  
Application Metrics:
  - Producer success rate by DC
  - Consumer lag by DC
  - End-to-end latency
```

2. **Alerting Configuration**:
```yaml
# Prometheus Alert Rules
groups:
- name: kafka_alerts
  rules:
  - alert: KafkaUnderReplicatedPartitions
    expr: kafka_server_replicamanager_underreplicatedpartitions > 0
    for: 5m
    labels:
      severity: critical
    annotations:
      summary: Under replicated partitions detected
      
  - alert: CrossDCReplicationLag
    expr: kafka_mirrormaker_replication_lag_seconds > 300
    for: 10m
    labels:
      severity: warning
    annotations:
      summary: Cross-DC replication lag detected
```

#### 1.5 Scalability Considerations

1. **Partition Strategy**:
```java
public class PartitionStrategy {
    public static int calculateOptimalPartitions(
            long messageRate,
            long messageSize,
            int retentionHours) {
        
        // Calculate based on:
        // - Target partition size (500MB-2GB)
        // - Message rate per partition (<4000/sec)
        // - Total topic throughput
        long dailyData = messageRate * messageSize * 86400;
        long retentionData = dailyData * (retentionHours / 24);
        
        return Math.max(
            (int) Math.ceil(retentionData / (2L * 1024 * 1024 * 1024)),
            (int) Math.ceil(messageRate / 4000)
        );
    }
}
```

2. **Scaling Rules**:
   - Partition count = max(throughput_based, size_based)
   - Leader/Follower ratio per broker < 60%
   - Network utilization < 70%
   - Disk utilization < 85%

### 2. Deep dive into Kafka's storage mechanisms

The storage layer is critical for performance:

- **Segment Files**: Topics are divided into segments:
  ```
  segment.bytes=1GB (default)
  segment.ms=7 days (default)
  ```

- **Index Files**: Kafka maintains two types of indexes:
  ```
  .index: Maps offset to position in log file
  .timeindex: Maps timestamp to offset
  ```

Production Optimization Example:
```
# For high-throughput systems:
segment.bytes=2GB
segment.ms=24h
file.delete.delay.ms=60000
```

### 3. Explain Kafka's Message Delivery Semantics

Kafka provides three message delivery guarantees, each with specific use cases and trade-offs. Understanding these is crucial for designing reliable distributed systems.

#### 3.1 At-most-once Delivery

**What it means**: Messages may be lost but will never be redelivered. Once a producer sends a message, it's "fire and forget" - no retry attempts are made.

**Implementation**:
```java
Properties props = new Properties();
props.put("enable.auto.commit", "true");
props.put("auto.commit.interval.ms", "1000");
props.put("acks", "0");  // Fire and forget
```

**Real-world Use Case**: Log aggregation for system metrics where some data loss is acceptable.
```java
// Example: System metrics producer
public class MetricsProducer {
    private final KafkaProducer<String, String> producer;
    
    public void sendMetric(String metric) {
        ProducerRecord<String, String> record = 
            new ProducerRecord<>("system-metrics", metric);
        // Fire and forget - no callback handling
        producer.send(record);
    }
}
```

**Production Scenario**: 
- A large e-commerce platform's product view tracking system
- Handles 1M+ events per second
- Some loss of view counts acceptable for better performance
- Reduced latency critical for user experience

#### 3.2 At-least-once Delivery

**What it means**: Messages will never be lost but may be redelivered. This is achieved through message acknowledgment and retry mechanisms.

**Implementation**:
```java
Properties props = new Properties();
props.put("enable.auto.commit", "false");
props.put("acks", "all");
props.put("retries", "3");
props.put("max.in.flight.requests.per.connection", "1");
```

**Real-world Use Case**: Payment processing system where missing a transaction is unacceptable.
```java
public class PaymentProcessor {
    private final KafkaConsumer<String, Payment> consumer;
    private final PaymentService paymentService;

    public void processPayments() {
        while (true) {
            ConsumerRecords<String, Payment> records = 
                consumer.poll(Duration.ofMillis(100));
            
            for (ConsumerRecord<String, Payment> record : records) {
                try {
                    // Process payment
                    paymentService.processPayment(record.value());
                    
                    // Manual commit after successful processing
                    Map<TopicPartition, OffsetAndMetadata> offsets = 
                        Collections.singletonMap(
                            new TopicPartition(record.topic(), 
                                record.partition()),
                            new OffsetAndMetadata(record.offset() + 1)
                        );
                    consumer.commitSync(offsets);
                } catch (Exception e) {
                    // Payment will be reprocessed due to no commit
                    handleError(e);
                }
            }
        }
    }
}
```

**Production Scenario**:
- Banking transaction processing system
- Processes 10K transactions per second
- Implements idempotency using transaction IDs
- Handles retries with exponential backoff
- Monitors duplicate processing rate

#### 3.3 Exactly-once Delivery

**What it means**: Each message is delivered exactly once, even in case of producer or consumer failures. This is achieved using transactional messaging.

**Implementation**:
```java
// Producer configuration
Properties producerProps = new Properties();
producerProps.put("transactional.id", "prod-1");
producerProps.put("processing.guarantee", "exactly_once");
producerProps.put("isolation.level", "read_committed");

// Consumer configuration
Properties consumerProps = new Properties();
consumerProps.put("isolation.level", "read_committed");
consumerProps.put("enable.auto.commit", "false");
```

**Real-world Use Case**: Financial ledger system requiring strict consistency.
```java
public class LedgerProcessor {
    private final KafkaProducer<String, Transaction> producer;
    private final TransactionManager txManager;

    public void processTransaction(String accountId, 
        Transaction transaction) {
        producer.initTransactions();
        
        try {
            producer.beginTransaction();
            
            // 1. Read current balance
            Balance balance = txManager.getBalance(accountId);
            
            // 2. Apply transaction
            Balance newBalance = balance.apply(transaction);
            
            // 3. Update balance in database
            txManager.updateBalance(accountId, newBalance);
            
            // 4. Send confirmation message
            ProducerRecord<String, Transaction> record = 
                new ProducerRecord<>("tx-confirmations", 
                    accountId, 
                    transaction);
            producer.send(record);
            
            // 5. Commit the transaction
            producer.commitTransaction();
        } catch (Exception e) {
            producer.abortTransaction();
            throw new TransactionFailedException(e);
        }
    }
}
```

**Production Scenario**:
- Stock trading platform
- Processes market orders in real-time
- Cannot tolerate duplicate or lost orders
- Implements transaction fencing for failover
- Monitors transaction completion rate
- Uses dead letter queues for failed transactions
- Implements compensating transactions for rollbacks

#### 3.4 Production Considerations

When choosing delivery semantics, consider:

1. **Performance Impact**:
   - At-most-once: Lowest latency
   - At-least-once: Moderate latency
   - Exactly-once: Highest latency (2-3x overhead)

2. **Resource Requirements**:
   - Transaction coordinator overhead
   - Additional storage for transaction logs
   - Network bandwidth for coordination

3. **Monitoring Metrics**:
   ```java
   // Example monitoring setup
   Metrics metrics = new Metrics();
   metrics.addMetric("duplicate_messages", 
       new DuplicateMessageCounter());
   metrics.addMetric("lost_messages", 
       new LostMessageCounter());
   metrics.addMetric("transaction_latency", 
       new LatencyTimer());
   ```

4. **Failure Handling**:
   - Transaction timeout configuration
   - Retry policies
   - Dead letter queues
   - Alert thresholds
```

## Infrastructure and Operations

### 4. Capacity Planning and Cluster Sizing

Key metrics for capacity planning:

1. **Network Bandwidth**:
   ```
   Daily Data Volume × Replication Factor × Safety Factor
   Example: 1TB/day × 3 replicas × 1.5 = 4.5TB/day network capacity
   ```

2. **Disk Space**:
   ```
   (Daily Data Volume × Retention Period × Replication Factor) + 30% headroom
   Example: (1TB × 7 days × 3) × 1.3 = 27.3TB
   ```

3. **Memory Requirements**:
   ```
   Page Cache: 15-20% of daily data volume
   Heap: 4-6GB per broker (avoid large heaps)
   ```

### 5. Production Monitoring and Alerting

Essential metrics to monitor:

```yaml
# Critical Metrics
- Under Replicated Partitions: Alert if > 0
- Offline Partitions: Alert if > 0
- Active Controller Count: Alert if != 1
- Request Handler CPU Time: Alert if > 80%

# Performance Metrics
- Produce/Fetch Request Latency: p99 < 500ms
- Log Flush Latency: p95 < 1000ms
- Network Processor Idle %: > 40%

# Consumer Metrics
- Consumer Lag: Alert if > threshold
- Consumer Group Rebalances: Alert if frequent
```

Example Prometheus Alert:
```yaml
alert: KafkaUnderReplicatedPartitions
expr: kafka_server_replicamanager_underreplicatedpartitions > 0
for: 5m
labels:
  severity: critical
annotations:
  summary: Kafka under-replicated partitions
```

### 6. Disaster Recovery and Data Migration

Recovery Point Objective (RPO) and Recovery Time Objective (RTO) strategies:

1. **MirrorMaker 2.0 Setup**:
```properties
# Source cluster config
source.cluster.bootstrap.servers=source-kafka:9092
source.cluster.security.protocol=SSL

# Target cluster config
target.cluster.bootstrap.servers=target-kafka:9092
target.cluster.security.protocol=SSL

# Replication policies
replication.policy.class=org.apache.kafka.connect.mirror.DefaultReplicationPolicy
sync.topic.acls.enabled=true
```

2. **Cross-DC Replication**:
```yaml
# Active-Active Configuration
- Use separate topic naming: DC1_topic, DC2_topic
- Configure producer/consumer client IDs with DC
- Implement conflict resolution strategy
```

## Advanced Development Topics

### 7. Stream Processing: Concepts, Architecture, and Implementation

#### 7.1 What is Stream Processing and Why Do We Need It?

Stream Processing is a data processing paradigm that treats data as an infinite, continuous flow of events rather than a bounded dataset. It's essential in modern architectures for several reasons:

1. **Real-time Processing Requirements**:
   - Instant fraud detection in payment systems
   - Real-time recommendations in e-commerce
   - Live monitoring and alerting systems
   - Dynamic pricing in ride-sharing apps

2. **Data Volume Challenges**:
   - Traditional batch processing can't handle continuous high-volume data
   - Need for immediate insights from data
   - Resource efficiency through incremental processing

3. **Business Value**:
   ```plaintext
   Traditional Batch Processing:
   Data Collection (1 hr) → Processing (2 hrs) → Results
   Time to Insight: 3+ hours

   Stream Processing:
   Event → Processing → Results
   Time to Insight: Milliseconds to seconds
   ```

#### 7.2 Kafka Streams Architecture

Kafka Streams provides a powerful framework for stream processing:

1. **Core Components**:
```java
// Stream Processing Topology
public class PaymentProcessingTopology {
    public Topology createTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        
        // 1. Source Stream
        KStream<String, Payment> payments = builder
            .stream("incoming-payments", 
                Consumed.with(Serdes.String(), 
                    PaymentSerde.instance()));
        
        // 2. Stream Processing
        KStream<String, Payment> validPayments = payments
            // Stateless operations
            .filter((key, payment) -> payment.amount() > 0)
            .mapValues(payment -> enrichPayment(payment))
            
            // Stateful operations
            .groupByKey()
            .windowedBy(TimeWindows.of(Duration.ofMinutes(5)))
            .aggregate(
                // Initialize aggregation
                AccountActivity::new,
                // Aggregation logic
                (key, payment, activity) -> 
                    activity.addPayment(payment),
                // State store config    
                Materialized.as("payment-activity-store")
            )
            .toStream()
            .filter((key, activity) -> 
                !activity.isFraudulent());
        
        // 3. Sink Stream
        validPayments.to("processed-payments");
        
        return builder.build();
    }
}
```

2. **State Management**:
```java
// State Store Configuration
public class StateStoreConfig {
    public StoreBuilder<?> createStateStore() {
        return Stores.keyValueStoreBuilder(
            Stores.persistentKeyValueStore("payment-store"),
            Serdes.String(),
            PaymentSerde.instance())
            .withCachingEnabled()
            .withLoggingEnabled(Map.of(
                "retention.ms", "604800000",  // 7 days
                "cleanup.policy", "compact"
            ));
    }
}
```

#### 7.3 Real-World Implementation Patterns

1. **Event-Time Processing**:
```java
public class EventTimeProcessor {
    public Topology createTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        
        // Configure with event-time semantics
        builder.stream("events")
            .selectKey((k, v) -> v.getEventTime())
            .groupByKey()
            .windowedBy(
                TimeWindows.of(Duration.ofMinutes(5))
                    .grace(Duration.ofMinutes(1))
            )
            .aggregate(
                EventStats::new,
                (key, event, stats) -> stats.add(event),
                Materialized.as("event-stats-store")
            );
            
        return builder.build();
    }
}
```

2. **Fault Tolerance and Recovery**:
```java
// Implementing a fault-tolerant processor
public class ResilientProcessor {
    private final ProcessorContext context;
    private KeyValueStore<String, Long> stateStore;
    
    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        this.stateStore = context.getStateStore("resilient-store");
        
        // Register for restoration callbacks
        context.schedule(
            Duration.ofSeconds(30),
            PunctuationType.WALL_CLOCK_TIME,
            timestamp -> checkpointState()
        );
    }
    
    private void checkpointState() {
        try {
            stateStore.flush();
            // Record checkpoint
            context.commit();
        } catch (Exception e) {
            // Handle checkpoint failure
            handleCheckpointFailure(e);
        }
    }
}
```

#### 7.4 Production Considerations

1. **Scaling Strategy**:
```java
Properties config = new Properties();
// Configure for optimal scaling
config.put(
    StreamsConfig.NUM_STREAM_THREADS_CONFIG,
    Runtime.getRuntime().availableProcessors()
);
config.put(
    StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG,
    32 * 1024 * 1024L  // 32MB per thread
);
config.put(
    StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,
    1000  // Commit every second
);
```

2. **Monitoring and Metrics**:
```java
// Custom metrics for stream processing
public class StreamMetrics {
    private final Sensor processLatency;
    private final Sensor recordsProcessed;
    
    public StreamMetrics(StreamsMetricsImpl metrics) {
        this.processLatency = metrics.addLatencyMetric(
            "process-latency",
            "stream-task"
        );
        
        this.recordsProcessed = metrics.addThroughputMetric(
            "records-processed",
            "stream-task"
        );
    }
    
    public void recordProcessing(long startTime) {
        processLatency.record(
            System.nanoTime() - startTime
        );
        recordsProcessed.record(1.0);
    }
}
```

#### 7.5 Common Challenges and Solutions

1. **Late Events Handling**:
```java
public class LateEventHandler {
    public Topology handleLateEvents() {
        StreamsBuilder builder = new StreamsBuilder();
        
        builder.stream("events")
            .groupByKey()
            .windowedBy(
                TimeWindows.of(Duration.ofMinutes(5))
                    // Allow late events up to 1 hour
                    .grace(Duration.ofHours(1))
            )
            .aggregate(
                WindowedStats::new,
                (key, value, stats) -> stats.add(value),
                Materialized.as("windowed-stats")
            )
            // Handle late events separately
            .suppress(
                Suppressed.untilWindowCloses(
                    Suppressed.BufferConfig.unbounded()
                )
            );
            
        return builder.build();
    }
}
```

2. **Performance Optimization**:
```java
// Optimizing stream processing performance
public class OptimizedProcessor {
    public Properties getOptimizedConfig() {
        Properties props = new Properties();
        
        // Memory management
        props.put(
            StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG,
            Math.max(
                Runtime.getRuntime().maxMemory() / 4,
                64 * 1024 * 1024L  // Minimum 64MB
            )
        );
        
        // Batching for better throughput
        props.put(
            StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,
            100  // 100ms batching
        );
        
        // Parallel processing
        props.put(
            StreamsConfig.NUM_STREAM_THREADS_CONFIG,
            Math.max(
                Runtime.getRuntime().availableProcessors() - 1,
                1
            )
        );
        
        return props;
    }
}
```

3. **Error Handling and Recovery**:
```java
public class ErrorHandlingProcessor {
    private final DeadLetterQueue dlq;
    private final ErrorHandler errorHandler;
    
    public void process(Record record) {
        try {
            processRecord(record);
        } catch (TemporaryException e) {
            // Retry with backoff
            handleRetryableError(record, e);
        } catch (PermanentException e) {
            // Send to DLQ
            dlq.send(record, e);
        } catch (Exception e) {
            // Unknown error
            errorHandler.handle(record, e);
        }
    }
    
    private void handleRetryableError(
            Record record, 
            Exception e) {
        RetryPolicy policy = new ExponentialBackoff(
            Duration.ofMillis(100),  // Initial delay
            Duration.ofSeconds(30),  // Max delay
            3  // Max attempts
        );
        
        policy.execute(() -> processRecord(record));
    }
}

### 8. Performance Optimization

Critical performance tuning parameters:

```properties
# Producer Optimizations
buffer.memory=67108864
batch.size=100000
linger.ms=5
compression.type=lz4
acks=1

# Consumer Optimizations
fetch.min.bytes=1048576
fetch.max.wait.ms=500
max.partition.fetch.bytes=1048576
```

Real-world optimization case:
```java
// High-throughput producer configuration
Properties props = new Properties();
props.put("batch.size", 100000);
props.put("linger.ms", 5);
props.put("compression.type", "lz4");
props.put("buffer.memory", 67108864);
props.put("max.in.flight.requests.per.connection", 5);

// Parallel consumer processing
ExecutorService executor = Executors.newFixedThreadPool(
    Runtime.getRuntime().availableProcessors()
);
ConsumerRecords<String, String> records = consumer.poll(
    Duration.ofMillis(100)
);
CompletableFuture.allOf(
    StreamSupport.stream(records.spliterator(), true)
        .map(record -> CompletableFuture.runAsync(
            () -> processRecord(record), 
            executor
        ))
        .toArray(CompletableFuture[]::new)
).join();
```

### 9. Security Implementation

Comprehensive security setup:

1. **SSL Configuration**:
```properties
# Broker Config
ssl.keystore.location=/var/private/ssl/kafka.server.keystore.jks
ssl.keystore.password=${KEYSTORE_PASSWORD}
ssl.key.password=${KEY_PASSWORD}
ssl.truststore.location=/var/private/ssl/kafka.server.truststore.jks
ssl.truststore.password=${TRUSTSTORE_PASSWORD}
ssl.client.auth=required

# Client Config
security.protocol=SSL
ssl.truststore.location=/var/private/ssl/client.truststore.jks
ssl.truststore.password=${CLIENT_TRUSTSTORE_PASSWORD}
```

2. **SASL/SCRAM Configuration**:
```properties
# Broker Config
listeners=SASL_SSL://localhost:9093
security.inter.broker.protocol=SASL_SSL
sasl.mechanism.inter.broker.protocol=SCRAM-SHA-512
sasl.enabled.mechanisms=SCRAM-SHA-512

# ACL Configuration
bin/kafka-acls.sh --authorizer-properties \
    zookeeper.connect=localhost:2181 \
    --add --allow-principal User:user1 \
    --operation Read --operation Write \
    --topic my-topic
```

## System Design and Architecture

### 10. Event-Driven Architecture Patterns

Example of implementing Event Sourcing with Kafka:

```java
// Event store implementation
public class KafkaEventStore {
    private final KafkaProducer<String, Event> producer;
    private final String eventTopic;

    public void store(String aggregateId, Event event) {
        ProducerRecord<String, Event> record = 
            new ProducerRecord<>(eventTopic, aggregateId, event);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                // Handle failure
                throw new EventStoreException(exception);
            }
        });
    }

    public List<Event> getEvents(String aggregateId) {
        // Implementation to read all events for an aggregate
    }
}

// Aggregate reconstruction
public class AggregateStore {
    private final KafkaEventStore eventStore;

    public Aggregate getAggregate(String aggregateId) {
        List<Event> events = eventStore.getEvents(aggregateId);
        return events.stream()
            .reduce(new Aggregate(), 
                (aggregate, event) -> aggregate.apply(event),
                (a1, a2) -> a1);
    }
}
```

### 11. Scalability and High Availability

Production-grade high availability configuration:

```properties
# Broker Configuration for HA
num.network.threads=8
num.io.threads=16
socket.send.buffer.bytes=102400
socket.receive.buffer.bytes=102400
socket.request.max.bytes=104857600
num.replica.fetchers=4
replica.fetch.max.bytes=1048576
num.recovery.threads.per.data.dir=2

# Topic Configuration
min.insync.replicas=2
unclean.leader.election.enable=false
auto.create.topics.enable=false
```

Multi-DC setup example:
```yaml
# DC1 Configuration
broker.rack=dc1
replica.selector.class=org.apache.kafka.common.replica.RackAwareReplicaSelector

# DC2 Configuration
broker.rack=dc2
replica.selector.class=org.apache.kafka.common.replica.RackAwareReplicaSelector

# Topic Creation
bin/kafka-topics.sh --create \
    --bootstrap-server localhost:9092 \
    --topic multi-dc-topic \
    --partitions 32 \
    --replication-factor 3 \
    --config min.insync.replicas=2
```

### 12. Troubleshooting and Problem Solving

Common production issues and solutions:

1. **High Latency Investigation**:
```bash
# Check broker metrics
$ kafka-run-class.sh kafka.tools.JmxTool \
    --object-name kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec

# Analyze consumer group lag
$ kafka-consumer-groups.sh --bootstrap-server localhost:9092 \
    --describe --group my-group

# Monitor network metrics
$ netstat -np tcp | grep 9092
```

2. **Recovery from Broker Failure**:
```bash
# Check under-replicated partitions
$ kafka-topics.sh --describe --under-replicated \
    --bootstrap-server localhost:9092

# Trigger leader election
$ kafka-leader-election.sh --bootstrap-server localhost:9092 \
    --election-type preferred \
    --all-topic-partitions
```

## Best Practices and Guidelines

### 13. Production Deployment Checklist

Essential pre-production checks:

```markdown
1. Hardware Requirements:
   - RAID10 for data directories
   - XFS filesystem
   - Separate mount points for logs and ZK data

2. OS Configuration:
   - Increase file descriptors: ulimit -n 100000
   - Disable swapping
   - Configure transparent huge pages
   - Set vm.swappiness=1

3. JVM Configuration:
   - Use G1GC
   - Set heap size appropriately
   - Enable GC logging

4. Monitoring Setup:
   - JMX metrics exported
   - Log aggregation configured
   - Alerting rules tested

5. Security:
   - SSL certificates verified
   - ACLs implemented
   - Network security groups configured
```

### 14. Kafka Connect and Integration Patterns

Example of a robust Kafka Connect configuration:

```properties
# Worker Configuration
bootstrap.servers=kafka1:9092,kafka2:9092,kafka3:9092
group.id=connect-cluster
key.converter=org.apache.kafka.connect.json.JsonConverter
value.converter=org.apache.kafka.connect.json.JsonConverter
key.converter.schemas.enable=true
value.converter.schemas.enable=true
offset.storage.topic=connect-offsets
config.storage.topic=connect-configs
status.storage.topic=connect-status
offset.flush.interval.ms=10000

# Connector Configuration
connector.class=io.debezium.connector.postgresql.PostgresConnector
database.hostname=postgresql
database.port=5432
database.user=postgres
database.password=${POSTGRESQL_PASSWORD}
database.dbname=mydb
table.whitelist=public.users,public.orders
transforms=unwrap
transforms.unwrap.type=io.debezium.transforms.ExtractNewRecordState
```

This guide covers the most important aspects of Kafka that a Principal Engineer should be familiar with. Each section includes practical examples and real-world scenarios that demonstrate both theoretical knowledge and hands-on experience.
