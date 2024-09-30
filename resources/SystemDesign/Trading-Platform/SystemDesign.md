# Designing a Global-Scale Trading Platform (like Robinhood)

## Introduction

We are tasked with designing a highly scalable, resilient, fault-tolerant, and distributed trading platform. This
platform will allow users to place orders, view their portfolio, and access real-time market data. The platform should
handle **100 million+ users globally**, with provisions for rapid growth. Given the nature of trading, the system must
meet stringent requirements for low-latency, availability, fault-tolerance, and scalability, particularly during peak
trading hours. We will start by addressing the core functionality, non-functional requirements (NFRs), infrastructure,
and scaling requirements, then proceed with database selection and design, followed by an AWS-based Spring Boot
microservices architecture.

---

## 1. Functional Requirements

# Global-Scale Trading Platform System Design

## 1. Functional Requirements

1. User Account Management
    - User registration and login
    - Profile management
    - Two-factor authentication
    - Password reset functionality

2. Portfolio Management
    - View current portfolio holdings
    - Track portfolio performance over time
    - Set up watchlists for specific assets

3. Order Management
    - Place market and limit orders
    - View order status and history
    - Cancel pending orders

4. Market Data
    - Real-time asset price updates
    - Historical price data and charts
    - Market news and analysis

5. Trading Functionality
    - Buy and sell assets
    - Support for various order types (market, limit, stop-loss)
    - Fractional share trading

6. Funds Management
    - Deposit funds into the trading account
    - Withdraw funds from the trading account
    - View transaction history

7. Notifications
    - Price alerts for watched assets
    - Order execution notifications
    - Account activity alerts

8. Reporting
    - Generate account statements
    - Provide tax documents
    - Offer performance analytics

9. Customer Support
    - In-app chat support
    - Knowledge base and FAQs
    - Ticket-based issue resolution

10. Security Features
    - Encryption of sensitive data
    - Fraud detection mechanisms
    - Regular security audits and penetration testing

## 2. Non-Functional Requirements (NFRs)

1. Performance
    - Latency: 100 milliseconds or less for API responses
    - Throughput: Support at least 10,000 transactions per second
    - Real-time data updates: Less than 500 milliseconds delay

2. Scalability
    - Support for 100 million+ global users
    - Ability to handle sudden spikes in traffic (e.g., during market opening)
    - Horizontal scalability for all system components

3. Availability
    - 99.999% uptime (Five 9's)
    - Fault-tolerant architecture with no single point of failure
    - Graceful degradation of non-critical services during peak loads

4. Reliability
    - Data consistency across all transactions
    - No data loss for critical operations (e.g., trades, fund transfers)
    - Automated failover and disaster recovery mechanisms

5. Security
    - End-to-end encryption for all data in transit and at rest
    - Compliance with financial industry regulations (e.g., PCI DSS, SOC 2)
    - Regular security audits and vulnerability assessments

6. Maintainability
    - Modular architecture for easy updates and maintenance
    - Comprehensive logging and monitoring
    - Automated deployment and rollback capabilities

7. Usability
    - Intuitive user interface across web and mobile platforms
    - Response time of less than 2 seconds for UI interactions
    - Support for multiple languages and currencies

8. Compliance
    - Adherence to financial regulations in all operating jurisdictions
    - Implementation of Know Your Customer (KYC) and Anti-Money Laundering (AML) processes
    - Data privacy compliance (e.g., GDPR, CCPA)

9. Interoperability
    - RESTful APIs for third-party integrations
    - Support for standard financial data formats (e.g., FIX protocol)
    - Ability to integrate with external market data providers

10. Disaster Recovery
    - Recovery Time Objective (RTO) of less than 1 hour
    - Recovery Point Objective (RPO) of less than 5 minutes
    - Regular disaster recovery drills and testing

11. Capacity
    - Ability to store and process petabytes of historical market data
    - Support for millions of concurrent user sessions
    - Elastic resource allocation based on demand

12. Observability
    - Real-time monitoring of system health and performance
    - Detailed transaction tracing for troubleshooting
    - Predictive analytics for capacity planning

# Key Components and Industry-Standard Practices

Multi-Tier Architecture: This design follows a multi-tier architecture, separating client-facing components, application logic, and data storage.
Microservices: The application is divided into distinct services (User Service, Order Management Service, Market Data Service), aligning with microservices architecture principles.
High Availability and Fault Tolerance:

Use of multiple Availability Zones (AZ1, AZ2)
Auto Scaling Groups for each service
Multi-AZ deployments for databases (RDS)


Load Balancing:

Global load balancing (Route 53, CloudFront, Global Accelerator)
Application-level load balancing (ALB)
Network-level load balancing (NLB)
Internal load balancers for each service


Caching:

ElastiCache (Redis) for each service
CloudFront for content delivery
DAX for DynamoDB acceleration


Security:

Web Application Firewall (WAF)
Key Management Service (KMS)
Secrets Manager
Macie for data security


API Management: API Gateway for managing and securing APIs
Event-Driven Architecture: Kafka for event streaming, integrated with Kinesis Data Firehose
Observability:

CloudWatch for monitoring and alerting
CloudWatch Logs for centralized logging
Elasticsearch and Kibana for log analysis and visualization


Data Storage:

RDS for relational data
DynamoDB for NoSQL data
S3 for object storage


Service Mesh: AWS App Mesh for service-to-service communication
Compliance and Governance: AWS Config for compliance monitoring

# Trading Platform Microservices Design

## 1. User Management Microservice

### Responsibility:

Handles user registration, authentication, and profile management.

### API Endpoints:

1. Register User
    - `POST /api/users/register`
    - Request Body:
      ```json
      {
        "username": "string",
        "email": "string",
        "password": "string"
      }
      ```
    - Response:
      ```json
      {
        "userId": "string",
        "message": "User registered successfully"
      }
      ```

2. User Login
    - `POST /api/users/login`
    - Request Body:
      ```json
      {
        "email": "string",
      "password": "string"
      }
      ```
    - Response:
      ```json
      {
        "token": "string",
        "userId": "string"
      }
      ```

3. Get User Profile
    - `GET /api/users/profile`
    - Headers: `Authorization: Bearer <token>`
    - Response:
      ```json
      {
        "userId": "string",
        "username": "string",
        "email": "string",
        "createdAt": "datetime"
      }
      ```

4. Update User Profile
    - `PUT /api/users/profile`
    - Headers: `Authorization: Bearer <token>`
    - Request Body:
      ```json
      {
        "username": "string",
        "email": "string"
      }
      ```
    - Response:
      ```json
      {
        "message": "Profile updated successfully"
      }
      ```

### Database:

- Uses PostgreSQL for storing user information
- Table: `users`

## 2. Portfolio Management Microservice

### Responsibility:

Manages user portfolios, including adding/removing assets and tracking performance.

### API Endpoints:

1. Get User Portfolio
    - `GET /api/portfolio/{userId}`
    - Headers: `Authorization: Bearer <token>`
    - Response:
      ```json
      {
        "userId": "string",
        "assets": [
          {
            "assetId": "string",
            "quantity": "number",
            "averageCost": "number",
            "currentValue": "number"
          }
        ],
        "totalValue": "number"
      }
      ```

2. Add Asset to Portfolio
    - `POST /api/portfolio/{userId}/assets`
    - Headers: `Authorization: Bearer <token>`
    - Request Body:
      ```json
      {
        "assetId": "string",
        "quantity": "number",
        "purchasePrice": "number"
      }
      ```
    - Response:
      ```json
      {
        "message": "Asset added successfully"
      }
      ```

3. Remove Asset from Portfolio
    - `DELETE /api/portfolio/{userId}/assets/{assetId}`
    - Headers: `Authorization: Bearer <token>`
    - Response:
      ```json
      {
        "message": "Asset removed successfully"
      }
      ```

4. Get Portfolio Performance
    - `GET /api/portfolio/{userId}/performance`
    - Headers: `Authorization: Bearer <token>`
    - Query Parameters: `startDate`, `endDate`
    - Response:
      ```json
      {
        "userId": "string",
        "startValue": "number",
        "endValue": "number",
        "percentageChange": "number"
      }
      ```

### Database:

- Uses PostgreSQL for storing portfolio information
- Table: `portfolios`

## 3. Order Management Microservice

### Responsibility:

Handles the creation, execution, and management of trading orders.

### API Endpoints:

1. Place Order
    - `POST /api/orders`
    - Headers: `Authorization: Bearer <token>`
    - Request Body:
      ```json
      {
        "userId": "string",
        "assetId": "string",
        "orderType": "BUY|SELL",
        "quantity": "number",
        "price": "number",
        "orderSubType": "MARKET|LIMIT"
      }
      ```
    - Response:
      ```json
      {
        "orderId": "string",
        "status": "PENDING"
      }
      ```

2. Get Order Status
    - `GET /api/orders/{orderId}`
    - Headers: `Authorization: Bearer <token>`
    - Response:
      ```json
      {
        "orderId": "string",
        "status": "PENDING|EXECUTED|CANCELLED",
        "filledQuantity": "number",
        "remainingQuantity": "number"
      }
      ```

3. Cancel Order
    - `DELETE /api/orders/{orderId}`
    - Headers: `Authorization: Bearer <token>`
    - Response:
      ```json
      {
        "message": "Order cancelled successfully"
      }
      ```

4. Get User Orders
    - `GET /api/orders/user/{userId}`
    - Headers: `Authorization: Bearer <token>`
    - Query Parameters: `status`, `startDate`, `endDate`
    - Response:
      ```json
      {
        "orders": [
          {
            "orderId": "string",
            "assetId": "string",
            "orderType": "BUY|SELL",
            "quantity": "number",
            "price": "number",
            "status": "PENDING|EXECUTED|CANCELLED",
            "createdAt": "datetime"
          }
        ]
      }
      ```

### Database:

- Uses PostgreSQL for storing order information
- Table: `orders`

## 4. Market Data Microservice

### Responsibility:

Provides real-time and historical market data for assets.

### API Endpoints:

1. Get Real-time Price
    - `GET /api/market/price/{assetId}`
    - Response:
      ```json
      {
        "assetId": "string",
        "price": "number",
        "timestamp": "datetime"
      }
      ```

2. Get Historical Data
    - `GET /api/market/history/{assetId}`
    - Query Parameters: `startDate`, `endDate`, `interval`
    - Response:
      ```json
      {
        "assetId": "string",
        "data": [
          {
            "timestamp": "datetime",
            "open": "number",
            "high": "number",
            "low": "number",
            "close": "number",
            "volume": "number"
          }
        ]
      }
      ```

3. Get Market Summary
    - `GET /api/market/summary`
    - Response:
      ```json
      {
        "topGainers": [
          {
            "assetId": "string",
            "percentageChange": "number"
          }
        ],
        "topLosers": [
          {
            "assetId": "string",
            "percentageChange": "number"
          }
        ],
        "marketIndex": "number",
        "tradingVolume": "number"
      }
      ```

### Database:

- Uses Amazon DynamoDB for storing real-time market data
- Uses Amazon Timestream for historical time-series data

## 5. Transaction Management Microservice

### Responsibility:

Manages financial transactions, including deposits and withdrawals.

### API Endpoints:

1. Deposit Funds
    - `POST /api/transactions/deposit`
    - Headers: `Authorization: Bearer <token>`
    - Request Body:
      ```json
      {
        "userId": "string",
        "amount": "number",
        "currency": "string",
        "paymentMethod": "string"
      }
      ```
    - Response:
      ```json
      {
        "transactionId": "string",
        "status": "PENDING|COMPLETED"
      }
      ```

2. Withdraw Funds
    - `POST /api/transactions/withdraw`
    - Headers: `Authorization: Bearer <token>`
    - Request Body:
      ```json
      {
        "userId": "string",
        "amount": "number",
        "currency": "string",
        "withdrawalMethod": "string"
      }
      ```
    - Response:
      ```json
      {
        "transactionId": "string",
        "status": "PENDING|COMPLETED"
      }
      ```

3. Get Transaction History
    - `GET /api/transactions/history/{userId}`
    - Headers: `Authorization: Bearer <token>`
    - Query Parameters: `startDate`, `endDate`, `type`
    - Response:
      ```json
      {
        "transactions": [
          {
            "transactionId": "string",
            "type": "DEPOSIT|WITHDRAWAL",
            "amount": "number",
            "currency": "string",
            "status": "PENDING|COMPLETED",
            "timestamp": "datetime"
          }
        ]
      }
      ```

### Database:

- Uses PostgreSQL for storing transaction information
- Table: `transactions`

Database Design (PostgreSQL)

1. Users Table

SQL
CREATE TABLE users (
id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
username VARCHAR(255) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,   

    password_hash VARCHAR(255) NOT NULL,
                                       -- Additional fields for KYC/AML compliance
                                       first_name VARCHAR(255),
                                       last_name VARCHAR(255),
                                       date_of_birth DATE,
                                       address TEXT,
                                       phone_number VARCHAR(20),
                                       -- For account verification and security
                                       is_verified BOOLEAN DEFAULT FALSE,
                                       verification_code VARCHAR(255),
                                       verification_code_expiry TIMESTAMP,
                                       -- For two-factor authentication
                                       two_factor_enabled BOOLEAN DEFAULT FALSE,
                                       two_factor_secret VARCHAR(255),
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for frequently queried columns
CREATE INDEX idx_users_email ON users(email);
Use code with caution.

2. Portfolios Table

SQL
CREATE TABLE portfolios (
id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
user_id UUID REFERENCES users(id) ON DELETE CASCADE,
-- Additional fields for portfolio-level attributes
name VARCHAR(255),
description TEXT,
is_default BOOLEAN DEFAULT FALSE, -- For multiple portfolios per user
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index on user_id for efficient portfolio lookups
CREATE INDEX idx_portfolios_user_id ON portfolios(user_id);
Use code with caution.

3. Portfolio_Assets Table

SQL
CREATE TABLE portfolio_assets (
id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
portfolio_id UUID REFERENCES portfolios(id) ON DELETE CASCADE,
asset_id VARCHAR(255) NOT NULL,
quantity NUMERIC(18, 8) NOT NULL,
average_cost NUMERIC(18, 8) NOT NULL,
-- Additional fields for tracking asset-specific details
purchase_date TIMESTAMP,
last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- For real-time updates
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for efficient querying
CREATE INDEX idx_portfolio_assets_portfolio_id ON portfolio_assets(portfolio_id);
CREATE INDEX idx_portfolio_assets_asset_id ON portfolio_assets(asset_id);
Use code with caution.

4. Orders Table

SQL
CREATE TABLE orders (
id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
user_id UUID REFERENCES users(id) ON DELETE CASCADE,
portfolio_id UUID REFERENCES portfolios(id) ON DELETE CASCADE, -- Link to specific portfolio
asset_id VARCHAR(255) NOT NULL,
order_type VARCHAR(10) NOT NULL CHECK (order_type IN ('BUY', 'SELL')),
order_sub_type VARCHAR(10) NOT NULL CHECK (order_sub_type IN ('MARKET', 'LIMIT', 'STOP_LOSS', 'STOP_LIMIT')),
quantity NUMERIC(18, 8) NOT NULL,
price NUMERIC(18, 8), -- NULL for market orders
-- Additional fields for order execution and tracking
stop_price NUMERIC(18, 8), -- For stop-loss and stop-limit orders
time_in_force VARCHAR(20) CHECK (time_in_force IN ('GTC', 'DAY', 'IOC', 'FOK')), -- Good 'til Cancelled, Day, Immediate or Cancel, Fill or Kill
status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'EXECUTED', 'CANCELLED', 'REJECTED', 'PARTIALLY_FILLED')),
filled_quantity NUMERIC(18, 8) DEFAULT 0,
execution_price NUMERIC(18, 8), -- Price at which the order was executed
executed_at TIMESTAMP, -- Timestamp of order execution
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for efficient querying
CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_portfolio_id ON orders(portfolio_id);
CREATE INDEX idx_orders_asset_id ON orders(asset_id);
CREATE INDEX idx_orders_status ON orders(status);
Use code with caution.

5. Transactions Table

SQL
CREATE TABLE transactions (
id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
user_id UUID REFERENCES users(id) ON DELETE CASCADE,
type VARCHAR(10) NOT NULL CHECK (type IN ('DEPOSIT', 'WITHDRAWAL')),
amount NUMERIC(18, 2) NOT NULL,
currency VARCHAR(3) NOT NULL,
payment_method VARCHAR(255), -- Can be NULL for withdrawals
withdrawal_method VARCHAR(255), -- Can be NULL for deposits
status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED')),
-- Additional fields for transaction details
transaction_fee NUMERIC(18, 2),
reference_number VARCHAR(255), -- For external payment gateway references
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for efficient querying
CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_status ON transactions(status);
Use code with caution.

Database Design (DynamoDB)

Market Data Table

Partition Key: asset_id
Sort Key: timestamp
Attributes:
price (Number)
open (Number)
high (Number)
low (Number)
close (Number)
volume (Number)
bid_price (Number)
ask_price (Number)
bid_size (Number)
ask_size (Number)
Considerations:

Global Secondary Index (GSI): Create a GSI on timestamp to efficiently query market data within a specific time range across multiple assets.
Data Expiration: Set a Time to Live (TTL) on items to automatically delete older market data, reducing storage costs and improving query performance.
DynamoDB Streams: Enable DynamoDB Streams to capture real-time changes to market data, facilitating updates to downstream systems and analytics.
Database Design (Timestream)

Historical Market Data Table

Measures:
price (DOUBLE)
open (DOUBLE)
high (DOUBLE)
low (DOUBLE)
close (DOUBLE)
volume (BIGINT)
Dimensions:
asset_id (VARCHAR)
exchange (VARCHAR)
Time Column: time (TIMESTAMP)
Considerations:

Magnetic Store Retention: Configure appropriate retention periods for the magnetic store (hot data) and cold store (historical data) to optimize storage costs.
Data Ingestion: Utilize Timestream's high-throughput ingestion capabilities to efficiently write large volumes of historical market data.
Query Performance: Leverage Timestream's optimized query engine and built-in time-series functions for fast and flexible analysis of historical data.
Database Selection

PostgreSQL:

Suitable for structured data with complex relationships and ACID compliance requirements (e.g., users, portfolios, orders, transactions).
Provides strong data consistency and integrity.
Consider using PostgreSQL extensions like pg_trgm for full-text search and hstore or jsonb for flexible data storage.
DynamoDB:

Ideal for high-performance, low-latency access to key-value data with flexible schemas (e.g., market_data).
Offers horizontal scalability and high availability.
Consider using DynamoDB Accelerator (DAX) for in-memory caching to further improve read performance.
Timestream:

Purpose-built for time-series data with automatic scaling and optimized query performance (e.g., historical_market_data).
Efficiently handles large volumes of time-stamped data.