# Functional Requirements

### 1. One-on-One and Group Messaging
- Users should be able to send text messages in both individual and group conversations.
- Users should be able to create and manage group chats, including adding or removing members.
  
### 2. Media Sharing (Text, Images, Videos, Documents)
- Users should be able to share images, videos, and documents in chats.
- The system should support file uploads and generate URLs for media resources.

### 3. Real-Time Message Delivery and Read Receipts
- Messages should be delivered in real-time, with delivery and read receipts.
- Read receipts should reflect when a message is read by the recipient(s).

### 4. User Presence Indicators
- The system should display online/offline status indicators for users.

### 5. Message Search and History
- Users should be able to search through their message history.
- The system should support retrieval of past conversations and messages.

---

# Non-Functional Requirements

### 1. High Availability (99.99% Uptime)
- The system should be designed to be highly available across multiple regions and data centers to handle failures.

### 2. Low Latency Message Delivery (< 100ms)
- The system should deliver messages within 100ms under normal load conditions.

### 3. Scalability for Millions of Concurrent Users
- The architecture should support millions of users concurrently sending and receiving messages.

### 4. End-to-End Encryption
- All messages should be encrypted from the sender to the receiver to ensure privacy.

### 5. Offline Message Queueing and Synchronization
- Messages sent while a user is offline should be queued and delivered once the user comes back online.

---

## 2. Microservices Design

### Service Breakdown
- **Authentication Service**:
    - **Endpoints**:
        - `/login`
        - `/signup`
        - `/refresh-token`
    - **Technologies**: Spring Security for OAuth2/JWT token generation.

- **Chat Service**:
    - **Endpoints**:
        - `/send-message`
        - `/get-messages`
        - `/get-chat-history`
    - **Event Publishing**: Publishes events to the `message-events` Kafka topic for sent messages.
    - **Message Retrieval**: Fetches pending messages from the message queue or database.

- **User Presence Service**:
    - **Endpoints**:
        - `/update-status`
        - `/get-status`
    - **Event Publishing**: Publishes user status changes (online/offline) to `user-status-updates` Kafka topic.

- **Group Management Service**:
    - **Endpoints**:
        - `/create-group`
        - `/add-member`
        - `/remove-member`
    - **Data Storage**: Stores group metadata in **PostgreSQL**.

- **Media Service**:
    - **Endpoints**:
        - `/upload`
        - `/download`
    - **Event Publishing**: Publishes media-related events to `media-events` Kafka topic.

- **Notification Service**:
    - **Endpoints**:
        - `/send-notification`
    - **Event Handling**: Listens to `notification-events` for delivering notifications to users.

- **Search Service**:
    - **Endpoints**:
        - `/search-messages`
    - **Technologies**: **Elasticsearch** for full-text search capabilities.

- **Audit/Logging Service**:
    - **Endpoints**: Internal, captures logs from other services.
    - **Technologies**: **ELK Stack** for centralized logging and monitoring.

---

## 3. Event-Driven Architecture with Kafka

### Kafka Topics
- **`message-events`**: Sent, delivered, read events.
- **`user-status-updates`**: Online/offline presence updates.
- **`media-events`**: Upload and processing events for media.
- **`notification-events`**: For notifications related to unread messages and alerts.

### Event Flow
1. **Message Sent**: When a user sends a message, it is processed by the Chat Service, stored in the database, and a "sent" event is published to `message-events`.
2. **Status Updates**: Presence updates are published to `user-status-updates` whenever users log in or change their status.
3. **Media Handling**: Media uploads trigger an event to `media-events`, which initiates processing (e.g., compression, thumbnail creation).
4. **Notifications**: Unread message notifications are triggered via the Notification Service listening on the `notification-events` topic.

---

## 4. Database Design

### Database Choices
- **Cassandra**: High throughput for storing messages. Each message is a row indexed by `chat_id`, allowing quick retrieval based on time.
- **PostgreSQL**: Structured user and group data for easy querying and management.
- **Redis**: Caching for frequently accessed data and pending messages for users who are currently offline.

### Schema Example
1. **Cassandra (Messages Table)**:
   ```sql
   CREATE TABLE messages (
       chat_id UUID,
       message_id UUID,
       sender_id UUID,
       content TEXT,
       timestamp TIMESTAMP,
       status TEXT, // sent, delivered, read
       PRIMARY KEY (chat_id, timestamp)
   );

CREATE TABLE users (
    user_id UUID PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255),
    status VARCHAR(20),
    profile_pic_url VARCHAR(255),
    created_at TIMESTAMP,
    last_seen_at TIMESTAMP
);
2. Message Table (Cassandra)
```sql

CREATE TABLE messages (
    conversation_id UUID,
    message_id UUID,
    sender_id UUID,
    receiver_id UUID,
    message_body TEXT,
    media_url TEXT,
    created_at TIMESTAMP,
    read BOOLEAN,
    PRIMARY KEY (conversation_id, created_at)
);
3. Group Table (PostgreSQL)
```sql

CREATE TABLE groups (
    group_id UUID PRIMARY KEY,
    group_name VARCHAR(100),
    creator_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
4. Group Members Table (PostgreSQL)
```sql

CREATE TABLE group_members (
    group_id UUID,
    user_id UUID,
    joined_at TIMESTAMP,
    role VARCHAR(20),
    PRIMARY KEY (group_id, user_id)
);
5. Media Table (PostgreSQL)
```sql

CREATE TABLE media (
    media_id UUID PRIMARY KEY,
    uploader_id UUID,
    media_type VARCHAR(20),
    media_url VARCHAR(255),
    message_id UUID,
    created_at TIMESTAMP
);
6. User Conversations Table (Cassandra)
```sql

CREATE TABLE user_conversations (
    user_id UUID,
    conversation_id UUID,
    last_message_id UUID,
    last_message_preview TEXT,
    last_message_at TIMESTAMP,
    PRIMARY KEY (user_id, last_message_at)
);

# Event Stream Design

## Kafka Topics Overview

| Topic Name             | Event Types                                      | Purpose                                                       |
|------------------------|-------------------------------------------------|---------------------------------------------------------------|
| `user-status-updates`   | `USER_STATUS_CHANGED`                           | Notify users when someone's online/offline status changes.     |
| `message-events`        | `MESSAGE_SENT`, `MESSAGE_DELIVERED`, `MESSAGE_READ` | Handle real-time message flow and delivery updates.            |
| `group-events`          | `GROUP_CREATED`, `GROUP_UPDATED`                | Track group chat creation and metadata updates.                |
| `group-member-events`   | `MEMBER_JOINED_GROUP`, `MEMBER_LEFT_GROUP`      | Track user membership in groups.                               |
| `media-events`          | `MEDIA_UPLOADED`                                | Handle media uploads and delivery.                             |
| `conversation-events`   | `CONVERSATION_UPDATED`                          | Update conversation status when new messages are sent.         |
