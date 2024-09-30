URL Shortening Service:
System Scale and Growth

Initial user base: 100 million active users
Projected annual growth: 20% year-over-year
Expected daily active users (DAU): 50 million
Average URLs shortened per user per day: 5
Total new URLs per day: 250 million
Average URL length: 100 characters
Retention period for URLs: 5 years

Storage Requirements

Storage for URL mapping: ~5 TB per year

Calculation: 250 million URLs/day * 365 days * 5 years * (8 bytes for short code + 100 bytes for long URL) ≈ 25 TB for 5 years


Storage for user data and analytics: ~10 TB per year
Total storage requirement: ~35 TB for 5 years, with 20% annual growth

Functional Requirements

URL Shortening:

Users can input a long URL and receive a shortened URL in return.
Generate a unique short code for each long URL.
Short code should be 6-8 characters long, supporting up to 64^8 ≈ 281 trillion unique URLs.
Support batch URL shortening for enterprise users.


URL Redirection:

Redirect users to the original long URL when accessing a shortened URL.
Support HTTP and HTTPS protocols.


Custom Short URLs:

Allow users to create custom short URLs if available.
Implement a reservation system for premium short codes.


URL Management:

Provide a dashboard for users to manage their shortened URLs.
Support editing, deleting, and disabling shortened URLs.
Implement bulk operations for enterprise users.


Analytics and Reporting:

Track detailed analytics: clicks, unique visitors, geographic locations, devices, referral sources, and timestamps.
Provide real-time analytics with a maximum delay of 30 seconds.
Generate scheduled reports for enterprise users.


API Access:

Offer a RESTful API for programmatic URL shortening and management.
Provide SDKs for popular programming languages.


Link Expiration and TTL:

Allow users to set expiration dates or time-to-live (TTL) for URLs.
Implement automatic purging of expired links.


QR Code Generation:

Generate QR codes for shortened URLs.
Support customization of QR codes (color, logo) for premium users.


Security Features:

Implement link preview to protect users from malicious URLs.
Provide password protection option for sensitive URLs.
Support DMCA takedown requests and copyright protection.


User Authentication and Authorization:

Support OAuth 2.0 and multi-factor authentication.
Implement role-based access control for enterprise accounts.



Non-Functional Requirements

Performance:

URL shortening operation: < 50ms for 99.9% of requests.
URL redirection: < 20ms for 99.9% of requests.
API response time: < 100ms for 99.9% of requests.
Support 100,000 URL shortening requests per second.
Handle 1 million redirects per second during peak times.


Scalability:

Horizontal scalability to handle 10x current load without significant architecture changes.
Ability to scale to 1 billion users within 5 years.
Support for multi-region deployment and data replication.


Availability and Reliability:

99.999% uptime (5 minutes of downtime per year).
Implement fault tolerance with no single point of failure.
Support for multiple data centers with active-active configuration.
Automatic failover with recovery time objective (RTO) < 10 seconds.


Data Integrity and Consistency:

Ensure strong consistency for URL creation and updates.
Implement eventual consistency with a maximum lag of 1 second for analytics data.
Zero data loss tolerance for URL mappings.


Latency:

Global latency for URL redirection: < 100ms for 99.9% of requests.
API latency: < 200ms for 99.9% of requests from anywhere in the world.


Security:

Use TLS 1.3 for all communications.
Implement rate limiting: 10 requests per second per IP for anonymous users, customizable for authenticated users.
DDoS protection to handle up to 10 million requests per second of malicious traffic.
Regular security audits and penetration testing.
Compliance with SOC 2, ISO 27001, and other relevant standards.


Caching:

Implement multi-layer caching (CDN, in-memory, database).
Cache hit ratio > 95% for URL redirections.
Use consistent hashing for cache key distribution.


Database:

Support for distributed NoSQL databases for URL mappings.
Use relational databases for user data and structured information.
Implement database sharding to distribute data across multiple servers.


Monitoring and Logging:

Real-time monitoring with alerting for system health, performance, and security.
Distributed tracing for all requests with retention of 30 days.
Centralized logging with a retention period of 1 year.
Implement anomaly detection for proactive issue identification.


Disaster Recovery:

Regular backups with a recovery point objective (RPO) of 5 minutes.
Implement a hot standby system in a different geographic region.
Conduct quarterly disaster recovery drills.


Compliance and Data Protection:

GDPR, CCPA, and other relevant data protection regulations compliance.
Implement data encryption at rest and in transit.
Provide tools for users to export their data and delete their account.


Internationalization and Localization:

Support Unicode in URLs for international character sets.
Provide multi-language support for the user interface (at least 20 languages).
Implement geo-based content delivery for faster access in different regions.


API Design and Developer Experience:

RESTful API with OpenAPI (Swagger) specification.
Implement GraphQL API for flexible data querying.
Provide comprehensive API documentation and interactive API explorer.
Offer sandbox environment for developers to test integration.


Load Testing and Capacity Planning:

Regular load testing to ensure the system can handle 2x the expected peak load.
Capacity planning to accommodate 3 years of projected growth without major architecture changes.


Sustainability:

Optimize for energy efficiency in data centers.
Implement carbon-aware computing practices where possible.

Database Selection
For our URL shortening service, we'll use a combination of databases to handle different aspects of the system:

Primary Database: Apache Cassandra (NoSQL)

Reason: Cassandra is excellent for write-heavy workloads and can handle the high volume of URL creations and redirects. It offers linear scalability, high availability, and good read performance.


Secondary Database: PostgreSQL (Relational)

Reason: For user management, analytics, and other structured data that requires ACID compliance.


Caching Layer: Redis

Reason: To cache frequently accessed URLs for faster redirects and reduce load on the primary database.



Cassandra Schema Design
## Database Schema

### Users Table

```sql
CREATE TABLE users (
  id UUID PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  last_login TIMESTAMP WITH TIME ZONE
);

-- User Roles Table
CREATE TABLE user_roles (
  user_id UUID REFERENCES users(id),
  role VARCHAR(50) NOT NULL,
  PRIMARY KEY (user_id, role)
);

-- API Keys Table
CREATE TABLE api_keys (
  id UUID PRIMARY KEY,
  user_id UUID REFERENCES users(id),
  api_key VARCHAR(64) UNIQUE NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  last_used TIMESTAMP WITH TIME ZONE,
  is_active BOOLEAN DEFAULT TRUE
);

-- Aggregated Analytics Table

CREATE TABLE aggregated_analytics (
  short_code VARCHAR(10) PRIMARY KEY,
  total_clicks BIGINT DEFAULT 0,
  unique_visitors BIGINT DEFAULT 0,
  last_accessed TIMESTAMP WITH TIME ZONE
);
```
## Redis Cache Design
We'll use Redis to cache the most frequently accessed URL mappings:

Key: url:short_code
Value: JSON object containing {long_url, expires_at}
TTL: Set based on the URL's expiration or a default value (e.g., 24 hours)

Example:
SET url:abc123 '{"long_url": "https://example.com", "expires_at": 1672531200}' EX 86400

## Detailed Design and Implementation
### AWS Architecture Overview

- Amazon API Gateway: Entry point for all API requests
- AWS Lambda: Serverless compute for API handlers
- Amazon DynamoDB: Primary database for URL mappings
- Amazon ElastiCache for Redis: Caching layer for frequently accessed URLs
- Amazon RDS (PostgreSQL): Relational database for user management and aggregated analytics
- Amazon S3: Storage for static assets and logs
- Amazon CloudFront: CDN for global content delivery and caching
- AWS Cognito: User authentication and authorization
- Amazon SQS: Message queue for asynchronous processing
-  Elasticache: Caching layer for API responses
- AWS CloudWatch: Monitoring and logging
- AWS X-Ray: Distributed tracing
- Amazon Kinesis Data Firehose: Real-time analytics data ingestion
- Amazon Athena: Query service for analyzing log data

Detailed API Design and Implementation
1. URL Shortening API
   Endpoint: POST /api/v1/urls
   ```
   public class CreateShortUrlHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
   private final DynamoDBMapper dynamoDBMapper;
   private final AmazonSQS sqsClient;
   private final JedisPool redisPool;

   public CreateShortUrlHandler() {
   this.dynamoDBMapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
   this.sqsClient = AmazonSQSClientBuilder.defaultClient();
   this.redisPool = new JedisPool(new JedisPoolConfig(), System.getenv("REDIS_HOST"));
   }

   @Override
   public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
   try {
   CreateUrlRequest request = new Gson().fromJson(input.getBody(), CreateUrlRequest.class);
   String shortCode = generateShortCode();
   UrlMapping urlMapping = new UrlMapping(shortCode, request.getLongUrl(), request.getUserId());

            // Save to DynamoDB
            dynamoDBMapper.save(urlMapping);
            
            // Cache in Redis
            try (Jedis jedis = redisPool.getResource()) {
                jedis.setex("url:" + shortCode, 3600, request.getLongUrl());
            }
            
            // Send message to SQS for analytics processing
            sqsClient.sendMessage(System.getenv("SQS_QUEUE_URL"), new Gson().toJson(urlMapping));
            
            return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(new Gson().toJson(new CreateUrlResponse(shortCode)));
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("Internal Server Error");
        }
   }

   private String generateShortCode() {
   // Implementation of short code generation
   }
   }```
2. URL Redirection API
   Endpoint: GET /{shortCode}
   ```
   public class RedirectHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
   private final DynamoDBMapper dynamoDBMapper;
   private final JedisPool redisPool;
   private final AmazonSQS sqsClient;

   public RedirectHandler() {
   this.dynamoDBMapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
   this.redisPool = new JedisPool(new JedisPoolConfig(), System.getenv("REDIS_HOST"));
   this.sqsClient = AmazonSQSClientBuilder.defaultClient();
   }

   @Override
   public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
   String shortCode = input.getPathParameters().get("shortCode");
   String longUrl = null;

        // Check Redis cache
        try (Jedis jedis = redisPool.getResource()) {
            longUrl = jedis.get("url:" + shortCode);
        }

        // If not in cache, check DynamoDB
        if (longUrl == null) {
            UrlMapping urlMapping = dynamoDBMapper.load(UrlMapping.class, shortCode);
            if (urlMapping != null) {
                longUrl = urlMapping.getLongUrl();
                // Cache the result
                try (Jedis jedis = redisPool.getResource()) {
                    jedis.setex("url:" + shortCode, 3600, longUrl);
                }
            }
        }

        if (longUrl != null) {
            // Send click event to SQS for analytics processing
            ClickEvent clickEvent = new ClickEvent(shortCode, input.getRequestContext().getIdentity().getSourceIp());
            sqsClient.sendMessage(System.getenv("SQS_QUEUE_URL"), new Gson().toJson(clickEvent));

            return new APIGatewayProxyResponseEvent()
                .withStatusCode(301)
                .withHeaders(Collections.singletonMap("Location", longUrl));
        } else {
            return new APIGatewayProxyResponseEvent().withStatusCode(404).withBody("Not Found");
        }
   }
   }
   ```
3. User Registration API
   Endpoint: POST /api/v1/users
   ```
   public class UserRegistrationHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
   private final AmazonCognitoIdentityProvider cognitoClient;
   private final JdbcTemplate jdbcTemplate;

   public UserRegistrationHandler() {
   this.cognitoClient = AmazonCognitoIdentityProviderClientBuilder.defaultClient();
   this.jdbcTemplate = new JdbcTemplate(getDataSource());
   }

   @Override
   public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
   try {
   RegistrationRequest request = new Gson().fromJson(input.getBody(), RegistrationRequest.class);

            // Register user in Cognito
            SignUpResult signUpResult = cognitoClient.signUp(new SignUpRequest()
                .withClientId(System.getenv("COGNITO_CLIENT_ID"))
                .withUsername(request.getEmail())
                .withPassword(request.getPassword()));
            
            // Save additional user details in RDS
            String userId = signUpResult.getUserSub();
            jdbcTemplate.update("INSERT INTO users (id, email, created_at) VALUES (?, ?, ?)",
                userId, request.getEmail(), new Timestamp(System.currentTimeMillis()));
            
            return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(new Gson().toJson(new RegistrationResponse(userId)));
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("Internal Server Error");
        }
   }

   private DataSource getDataSource() {
   // Implementation to create and return a DataSource for RDS connection
   }
   }
   ```
4. Analytics API
   Endpoint: GET /api/v1/analytics/{shortCode}
   ```
   public class AnalyticsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
   private final JdbcTemplate jdbcTemplate;
   private final AmazonAthena athenaClient;

   public AnalyticsHandler() {
   this.jdbcTemplate = new JdbcTemplate(getDataSource());
   this.athenaClient = AmazonAthenaClientBuilder.defaultClient();
   }

   @Override
   public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
   String shortCode = input.getPathParameters().get("shortCode");

        try {
            // Get aggregated analytics from RDS
            Map<String, Object> aggregatedData = jdbcTemplate.queryForMap(
                "SELECT total_clicks, unique_visitors FROM aggregated_analytics WHERE short_code = ?",
                shortCode);
            
            // Get detailed analytics from Athena
            String query = "SELECT country, COUNT(*) as clicks " +
                           "FROM click_events " +
                           "WHERE short_code = '" + shortCode + "' " +
                           "GROUP BY country " +
                           "ORDER BY clicks DESC " +
                           "LIMIT 10";
            
            String queryExecutionId = submitAthenaQuery(query);
            List<Map<String, Object>> detailedData = getAthenaQueryResults(queryExecutionId);
            
            AnalyticsResponse response = new AnalyticsResponse(
                (Long) aggregatedData.get("total_clicks"),
                (Long) aggregatedData.get("unique_visitors"),
                detailedData
            );
            
            return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(new Gson().toJson(response));
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("Internal Server Error");
        }
   }

   private String submitAthenaQuery(String query) {
   // Implementation to submit Athena query
   }

   private List<Map<String, Object>> getAthenaQueryResults(String queryExecutionId) {
   // Implementation to get Athena query results
   }

   private DataSource getDataSource() {
   // Implementation to create and return a DataSource for RDS connection
   }
   }
   ```
5. URL Update API
   Endpoint: PUT /api/v1/urls/{shortCode}
   ```
   public class UpdateUrlHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
   private final DynamoDBMapper dynamoDBMapper;
   private final JedisPool redisPool;

   public UpdateUrlHandler() {
   this.dynamoDBMapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
   this.redisPool = new JedisPool(new JedisPoolConfig(), System.getenv("REDIS_HOST"));
   }

   @Override
   public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
   String shortCode = input.getPathParameters().get("shortCode");
   UpdateUrlRequest request = new Gson().fromJson(input.getBody(), UpdateUrlRequest.class);

        try {
            // Update in DynamoDB
            UrlMapping urlMapping = dynamoDBMapper.load(UrlMapping.class, shortCode);
            if (urlMapping == null) {
                return new APIGatewayProxyResponseEvent().withStatusCode(404).withBody("Not Found");
            }
            urlMapping.setLongUrl(request.getLongUrl());
            dynamoDBMapper.save(urlMapping);

            // Update Redis cache
            try (Jedis jedis = redisPool.getResource()) {
                jedis.setex("url:" + shortCode, 3600, request.getLongUrl());
            }

            return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(new Gson().toJson(new UpdateUrlResponse("URL updated successfully")));
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("Internal Server Error");
        }
   }
   }
```
### Additional Considerations

API Gateway Configuration:

Set up request throttling and API key management.
Configure custom domain names and SSL certificates.


Lambda Function Configuration:

Set appropriate memory and timeout settings.
Use environment variables for configuration.
Implement proper error handling and logging.


DynamoDB Configuration:

Enable auto-scaling for read and write capacity units.
Implement Time-To-Live (TTL) for URL expiration.


CloudFront Configuration:

Set up custom error pages.
Configure geo-restrictions if needed.


Monitoring and Alerting:

Set up CloudWatch alarms for key metrics (e.g., API latency, error rates).
Use X-Ray for tracing and performance analysis.


Security:

Implement AWS WAF for additional security.
Use AWS Secrets Manager for managing sensitive information.


Cost Optimization:

Use AWS Cost Explorer to monitor and optimize costs.
Implement auto-scaling policies to handle traffic spikes efficiently.


Compliance:

Ensure GDPR compliance by implementing proper data handling and deletion policies.
Use AWS CloudTrail for auditing and compliance purposes.