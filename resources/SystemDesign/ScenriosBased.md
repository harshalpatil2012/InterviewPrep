
# Problem Statement

You are working on a clone of Facebook and want to add a feature that displays a numeric count of how many friends the post's author has at the time the post is viewed. The system's database contains two tables: `USER` and `USER_RELATIONSHIP`. The `USER` table holds basic information about users, while the `USER_RELATIONSHIP` table tracks friendships between users. The friend count should be shown alongside each post, like this:

```
Marie McWilliams (165 friends)
I had a great day today, feeling good!
```

## Database Structure:
- **USER** table:
  - `user_id` (primary key): Unique identifier for each user.
  - `name`: User's name.
  - `created_date`: Date the user was created.
  
- **USER_RELATIONSHIP** table:
  - `friendship_id` (primary key): Unique identifier for each friendship.
  - `user1_id` (indexed): One user in the friendship.
  - `user2_id` (indexed): The other user in the friendship.
  - `start_date`: Date the friendship was created.

## Requirements:
- The solution must be efficient and able to scale to millions or billions of users as the platform grows in popularity.
- The friend count displayed next to each post must be accurate and up-to-date at the moment the post is viewed.

## Question:
Focusing on the database design and query optimization, how would you implement the friend-count feature in a way that scales well and ensures real-time accuracy for each post’s author?

# Step-by-Step Solution:
## 1. Database Tables Overview
- **USER** table:
  - `user_id` (primary key): Unique identifier for each user.
  - `name`: User's name.
  - `created_date`: Date the user was created.

- **USER_RELATIONSHIP** table:
  - `friendship_id` (primary key): Unique identifier for each friendship.
  - `user1_id` (indexed): One user in the friendship.
  - `user2_id` (indexed): The other user in the friendship.
  - `start_date`: Date the friendship was created.

## 2. Key Considerations
- **Performance and Scalability**: As the platform grows, the number of friendships will increase significantly. We need a solution that scales with millions or billions of users.
- **Real-time Friend Count**: The count should be accurate and up-to-date at the moment the post is viewed.

## 3. Approach

### A. Counting Friends in Real-Time (Direct Query)
To get the number of friends for a given `user_id`, you can use the following SQL query:

```sql
SELECT COUNT(*) AS friend_count
FROM USER_RELATIONSHIP
WHERE user1_id = :user_id OR user2_id = :user_id;
```

This query counts all relationships where the user is either `user1_id` or `user2_id`.

**Pros**:
- Always returns the most up-to-date friend count.

**Cons**:
- The query can become slow as the database grows, especially if the user has many friends. Indexes help, but for billions of records, real-time counting may not scale well.

### B. Precomputing and Caching Friend Counts
Instead of calculating the friend count on each post view, we can precompute and store the friend count in a separate table and update it whenever a new friendship is created or deleted.

Create a new table to store the friend count:

```sql
CREATE TABLE USER_FRIEND_COUNT (
  user_id BIGINT PRIMARY KEY,
  friend_count INT
);
```

When a new friendship is created:

```sql
UPDATE USER_FRIEND_COUNT
SET friend_count = friend_count + 1
WHERE user_id = :user1_id OR user_id = :user2_id;
```

When a friendship is deleted:

```sql
UPDATE USER_FRIEND_COUNT
SET friend_count = friend_count - 1
WHERE user_id = :user1_id OR user_id = :user2_id;
```

To display the friend count for a post, you can simply join this table with the post's author `user_id`:

```sql
SELECT u.name, ufc.friend_count
FROM USER u
JOIN USER_FRIEND_COUNT ufc ON u.user_id = ufc.user_id
WHERE u.user_id = :post_author_id;
```

**Pros**:
- Very fast retrieval of friend count (no need to count relationships each time).
- Scales well with millions of users and friendships.

**Cons**:
- Requires additional storage.
- Needs careful handling of concurrency, especially if many friendships are created or deleted simultaneously.

### C. Hybrid Approach (Cache + Real-Time Fallback)
You can use a hybrid approach where you cache the friend count and update it periodically (e.g., using Redis or another in-memory cache). For real-time accuracy, you can fall back to querying the database if the cached value is stale.

On a friendship change (create/delete), the cache can be invalidated or updated, ensuring the count remains accurate.

**Pros**:
- Balances real-time accuracy and scalability.
- Reduces database load by leveraging caching for frequent lookups.

**Cons**:
- Slight complexity in managing cache invalidation and updating.

## 4. Conclusion
For a scalable solution, the precomputing and caching approach (Option B) is the most efficient. It ensures quick access to the friend count while avoiding costly real-time calculations, and it scales better as the platform grows.
