# Correlation ID System Design

## 1. System Components

### 1.1 Generator Interface
```java
public interface CorrelationIdGenerator {
    String generateCorrelationId();
}
```

### 1.2 Implementation Strategies

#### UUID Generator
- Provides globally unique IDs
- No coordination required
- Used for general-purpose correlation

#### Time-Based Generator
- Format: timestamp-nodeId-sequence-random
- Handles clock skew and synchronization
- Suitable for temporal analysis

#### Snowflake Generator
- 64-bit IDs combining:
  - 41 bits timestamp
  - 10 bits node ID
  - 12 bits sequence
- Optimized for distributed systems

### 1.3 Core Services

#### Generation Service
- Creates correlation IDs
- Manages different generation strategies
- Handles throughput requirements

#### Propagation Service
- Passes IDs across service boundaries
- Maintains context across async operations
- Implements retry and fallback logic

## 2. Technical Design

### 2.1 Generation Strategies

#### Time-Based Generation
```java
class TimeBasedGenerator {
    private final String nodeId;
    private final AtomicLong sequence;
    private volatile long lastTimestamp;
    
    public String generate() {
        long timestamp = getUniqueTimestamp();
        long seq = sequence.getAndIncrement();
        return format("%d-%s-%d", timestamp, nodeId, seq);
    }
}
```

#### Snowflake Generation
```java
class SnowflakeGenerator {
    private static final int NODE_BITS = 10;
    private static final int SEQ_BITS = 12;
    
    public long generate() {
        long timestamp = System.currentTimeMillis() - EPOCH;
        return (timestamp << (NODE_BITS + SEQ_BITS)) 
               | (nodeId << SEQ_BITS) 
               | sequence;
    }
}
```

### 2.2 Clock Synchronization

#### Handling Clock Skew
- Monitor timestamp differences
- Use wait strategies for backwards clock
- Maintain sequence numbers for same millisecond

#### Node Identification
- Hardware address-based node IDs
- Fallback to random IDs
- Coordinate through configuration service

### 2.3 Context Propagation

#### HTTP Headers
- X-Correlation-ID
- X-Request-ID
- X-Trace-ID

#### Messaging Systems
- Message headers/properties
- Correlation ID preservation
- Parent-child relationship tracking

## 3. Implementation Patterns

### 3.1 Factory Pattern
```java
class CorrelationIdFactory {
    public CorrelationIdGenerator getGenerator(Strategy strategy) {
        return switch(strategy) {
            case UUID -> new UUIDGenerator();
            case TIME -> new TimeBasedGenerator();
            case SNOWFLAKE -> new SnowflakeGenerator();
        };
    }
}
```

### 3.2 Builder Pattern
```java
class CorrelationIdBuilder {
    private String prefix;
    private Strategy strategy;
    private Clock clock;
    
    public CorrelationIdGenerator build() {
        // Build and configure generator
    }
}
```

## 4. Error Handling

### 4.1 Clock Issues
- Detect clock drift
- Handle backwards movement
- Implement recovery strategies

### 4.2 Sequence Exhaustion
- Monitor sequence numbers
- Implement backpressure
- Use fallback strategies

## 5. Performance Considerations

### 5.1 Throughput
- 4096 IDs per millisecond per node
- Lock-free operations where possible
- Efficient bit manipulation

### 5.2 Memory Usage
- Minimal state maintenance
- Efficient string operations
- Proper cleanup of resources

## 6. Integration Guidelines

### 6.1 Service Integration
- Add correlation middleware
- Configure ID propagation
- Set up monitoring

### 6.2 Logging Integration
- Include correlation IDs
- Define log formats
- Configure log aggregation

## 7. Monitoring and Operations

### 7.1 Metrics
- Generation rate
- Collision detection
- Clock drift monitoring

### 7.2 Alerts
- Sequence exhaustion
- Clock synchronization issues
- Generation failures
