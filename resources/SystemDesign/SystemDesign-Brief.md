Read a few research papers:
– Amazon Dynamo: Amazon’s Highly Available Key-Value Store
– The Google File System
– Spanner: Google’s Globally Distributed Database
– Cassandra — A Decentralized Structured Storage System
– MapReduce: Simplified Data Processing on Large Clusters
– Bigtable: A Distributed Storage System for Structured Data


Implement a cache with following property:
https://leetcode.com/discuss/interview-question/3949761/British-Petroleum-or-Coding-Round
Cache will have a max capacity.
Cache will store an item with a key, value, priority and the expires at time.
While inserting any item, if the cache is not full item will get inserted in cache. Also if the item with same key is already there, it will get replaced with updated value, priority and expiry.
On getting an item it will become most recently used item.
If cache is full while inserting, the following eviction policy will apply.
a. Get the expiration time threshold by calling api GetExpiryThreshold(), the item with lower expiry time than threshold will get removed.
b. if there are more than one item, then the oldest expiry item gets removed.
c. if there are multiple items with oldest expiry, then the least priority item gets removed
d. otherwise if there are multiple items with same priority, remove item which was least recently used.
Ex-
GetExpiryThreshold() => returns a random value in range [0, 100000]

====================================================================
Design Bike Rental System (again a standard question of wayfair). Focus was on class design, API and DB Schema. 
====================================================================

Practiced popular system design problems like designing a URL shortening service, Pastebin, Instagram, Dropbox, Facebook Messenger, Twitter, YouTube/Netflix, Typeahead Suggestion, API Rate Limiter, Twitter Search.
Practiced system design for some Google products, including Google Search, YouTube, Google Photo Sharing and Storage, Google Docs, Google Drive.
Revised system design concepts such as CAP and PACELC theroem, SQL vs No-SQL, Types of No-SQL databases and their applications, Consistent Hashing, Bloom Filters, Load Balancers, Horizontal Scaling, Caching, Database Partitioning/Sharding, Indexes, Rate Limiting, Distributed Queues, Request Deduplication.


https://github.com/dipjul/Grokking-the-Coding-Interview-Patterns-for-Coding-Questions

ALL leetcode solutions:
https://github.com/doocs/leetcode/tree/main

The interviewer asked me to design an algorithm to sort a dataset that was too large to fit in the RAM of any one server. Most of the discussion focused on the O() tradeoffs if data transfer speed was orders of magnitude slower than an optimal O(n log n) sort on a given server

Functional and Non Functional Requirements 
=========================
Infrastructure Metrics :
Memory Utilization: Track how much memory is being used and how much is free. Set thresholds alert 
Disk Usage: Monitor disk read/write operations and the overall disk usage. Alert if disk usage approaches capacity limits
CPU Utilization: Measure the CPU usage percentage to identify periods of high usage and potential bottlenecks and add Alerts


Performance Metrics for diff. component
=========================
API Performance:
=========================
 Monitor  latency, throughput, and error rates of API calls, alerts for anomalies for high response times , increased failure rates alert

Data Injection:
=========================
 Track the volume of data being ingested, 
 processing times, and any failures or delays in data ingestion processes.
=========================
Data Transformation:  
Monitor the performance of data transformation processes, 
including execution time and success/failure rates

Tools & Architecture:
=========================
Custom Implementation:
Distributed architecture with client running on the individual nodes calling centralized API to feed in required stats for infra and component monitoring

Using OpenSource Tool
=========================
Prometheus for metric collection
Grafana for visualization 
ELK Stack for logs

Scalability and Reliability:
=========================
Multi region and AZ

Securtiy & Compliance:
=========================
Sensitve data masking

Docs and training/user guides
=========================


short substring 
all the char present in the string

all the char should be present
String str= "BCDAAABCCDABCCDDAC";
16.426073, 73.982923
16.425258, 73.979087


Implement the load balancer service registry. The registry should only hold unique servers and no more than 10.

Database indexes, isolation levels, partition vs sharding, cqrs, stability patterns, types of deployments

Write simple Load balancer class in Java

write a URL shortener in java using TDD.
Write thread safe Java class that transfers money from account A to account B


The choice of processing semantic depends on the application's requirements for data consistency, fault tolerance, and performance:

At Most Once is suitable for scenarios where occasional data loss is acceptable, and low latency is critical.
At Least Once is suitable for scenarios where data loss is not acceptable, and processing duplicated messages is tolerable.
Exactly Once is suitable for scenarios where both data loss and duplication are unacceptable, and strong consistency is required.
By understanding and selecting the appropriate processing semantic, Kafka applications can be designed to meet the desired balance between consistency, fault tolerance, and performance.

give practical example in banking or payments for above 


Let's consider a practical example in the context of a banking or payments system to illustrate each of the processing semantics:

At Most Once:
Scenario: A banking system is processing transactions from customers. Each transaction involves debiting funds from one account and crediting them to another account.

Processing Semantics: At most once

Description:

When a transaction message is received from a customer, the system immediately commits the offset in Kafka.
Funds are debited from the sender's account and credited to the recipient's account.
If the processing fails midway or the system crashes, the transaction message may not be processed.
In case of failure, the customer may need to retry the transaction, potentially leading to data loss if the original transaction was not processed.
Example: A customer initiates a fund transfer, but the system crashes before the transaction is fully processed. The funds are neither debited from the sender's account nor credited to the recipient's account, resulting in potential data loss.

At Least Once:
Scenario: A payment processing system is handling transactions for online purchases. Each transaction involves authorizing payment from the customer's account.

Processing Semantics: At least once

Description:

When a payment authorization message is received, the system processes the payment and then commits the offset in Kafka.
If the system crashes after processing the payment but before committing the offset, the message will be redelivered and processed again.
Duplicate processing of payment authorizations may occur, but the system ensures that payments are never missed or lost.
Example: A customer makes an online purchase, and the payment authorization message is processed successfully. However, the system crashes before committing the offset. Upon recovery, the payment authorization message is redelivered and processed again, resulting in duplicate payment processing.

Exactly Once:
Scenario: A financial institution is processing stock trades for its clients. Each trade involves buying or selling stocks on behalf of the client.

Processing Semantics: Exactly once

Description:

The system ensures that each stock trade message is processed exactly once, without duplicates or losses.
This is achieved by using Kafka transactions, where both the producer and consumer participate in transactions to ensure atomicity and isolation of trade processing.
Funds are debited or credited only if the trade is successfully processed and committed within a transaction.
Example: A client places a buy order for stocks, and the trade is processed successfully within a Kafka transaction. Even if the system crashes during processing, the trade will not be duplicated or lost. Upon recovery, the trade will not be processed again, ensuring exactly-once processing semantics.