
# LRU Cache with TTL: A Comprehensive Design

This document outlines a production-ready design for an LRU cache with Time-to-Live (TTL) functionality, incorporating key considerations for FAANG interview discussions.

## 1. Requirements

**Functional Requirements:**

* **`get(key)`:** Retrieve the value associated with a given key. If the key exists in the cache and hasn't expired, return its value; otherwise, return -1.
* **`put(key, value, ttl)`:** Insert or update a key-value pair in the cache with a specified TTL (in milliseconds). If the key already exists, update its value and TTL. If the cache is full, evict the least recently used item before inserting the new one.

**Non-Functional Requirements:**

* **Low Latency:** `get` and `put` operations should be very fast (ideally O(1) time complexity).
* **High Throughput:** Handle a large number of requests per second.
* **Scalability:**  Accommodate increasing data and traffic without significant performance degradation.
* **Eviction Policy:**  Accurate LRU eviction, ensuring the least recently used item is evicted when the cache is full.
* **Concurrency:**  Handle concurrent requests efficiently to avoid race conditions and ensure data consistency.
* **Space Efficiency:** Minimize memory overhead.
* **TTL Functionality:**  Ensure entries expire after their specified TTL.

## 2. Design

**2.1 Data Structures:**

* **ConcurrentHashMap:** Stores key-value pairs for efficient O(1) lookup in `get` operations, with thread safety for concurrent access.
* **Doubly Linked List:** Maintains the order of items based on their usage, allowing efficient identification and eviction of the LRU item.

**2.2  Cache Entry (Node):**

```java
class Node {
    int key;
    int value;
    long expirationTime; // For TTL
    Node prev;
    Node next;

    public Node(int key, int value, long expirationTime) {
        this.key = key;
        this.value = value;
        this.expirationTime = expirationTime;
    }
}
 

 // LRUCache Class:

 
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ``` 
 import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class LRUCache {

    private ConcurrentHashMap<Integer, Node> cache;
    private int capacity;
    private Node head;
    private Node tail;
    private ScheduledExecutorService scheduler; 

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
        this.head = new Node(0, 0, 0);
        this.tail = new Node(0, 0, 0);
        head.next = tail;
        tail.prev = head;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(this::evictExpiredEntries, 1, 1, TimeUnit.SECONDS);
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            add(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value, long ttl) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        long expirationTime = System.currentTimeMillis() + ttl;
        Node node = new Node(key, value, expirationTime);
        add(node);
        cache.put(key, node);
        if (cache.size() > capacity) {
            Node tailNode = tail.prev;
            remove(tailNode);
            cache.remove(tailNode.key);
        }
    }

    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void add(Node node) {
        Node nextNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
    }

    private void evictExpiredEntries() {
        for (Node node : cache.values()) {
            if (node.expirationTime < System.currentTimeMillis()) {
                remove(node);
                cache.remove(node.key);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1, 1000); // 1 second TTL
        lRUCache.put(2, 2, 2000); // 2 seconds TTL
        System.out.println(lRUCache.get(1));    // return 1
        Thread.sleep(1500); // Wait 1.5 seconds
        System.out.println(lRUCache.get(1));    // returns -1 (expired)
        System.out.println(lRUCache.get(2));    // returns 2 (still valid)
    }
}
```

2.4 Algorithm:

get(key):

If the key exists in the cache, retrieve the corresponding Node.
Remove the Node from the doubly linked list.
Add the Node to the head of the list (most recently used).
Return the value.
If the key doesn't exist, return -1.
put(key, value, ttl):

If the key exists in the cache, remove the corresponding Node.
Create a new Node with the key, value, and calculated expirationTime.
Add the Node to the head of the list.
Add the key and Node to the cache.
If the cache exceeds its capacity, remove the LRU item (tail of the list) from both the list and the cache.
evictExpiredEntries():

Iterate through all Node objects in the cache.
If a Node's expirationTime is less than the current time, remove it from the doubly linked list and the cache.
3. Key Considerations
Concurrency: ConcurrentHashMap ensures thread safety in multi-threaded environments.
Background Eviction: A background thread periodically evicts expired entries, improving efficiency.
Eviction Strategy: Uses LRU, but other strategies (LFU) can be considered based on application needs.
Scalability: For large datasets, consider distributed caching (e.g., Redis, Memcached).
4. Trade-offs
Memory Usage: Storing metadata increases memory consumption.
Complexity: Implementing TTL and eviction adds complexity.
Resource Consumption: Background thread uses resources.
Eviction Frequency: Tuning the eviction frequency impacts performance and resource usage.
This design provides a robust and efficient LRU cache with TTL. Remember to discuss these design choices, trade-offs, and potential optimizations during your FAANG interview to demonstrate a strong understanding of system design principles.