package com.practice.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheString {
	private int capacity;
	private Map<String, String> cache;
	private LinkedList<String> recentlyUsed;

	public LRUCacheString(int capacity) {
	        this.capacity = capacity;
	        this.cache = new HashMap<>();
	        this.recentlyUsed = new LinkedList<>();
	    }

	public String get(String key) {
		if (cache.containsKey(key)) {
			// If the key is found in the cache, move it to the front of recentlyUsed list
			// (most recently used)
			recentlyUsed.remove(key);
			recentlyUsed.addFirst(key);
			return cache.get(key);
		}
		return null;
	}

	public void put(String key, String value) {
		if (cache.containsKey(key)) {
			// If the key is already present in the cache, update the value and move it to
			// the front of recentlyUsed list
			cache.put(key, value);
			recentlyUsed.remove(key);
			recentlyUsed.addFirst(key);
		} else {
			if (cache.size() >= capacity) {
				// If cache is full, evict the least recently used item (last element in
				// recentlyUsed list)
				String lruKey = recentlyUsed.removeLast();
				cache.remove(lruKey);
			}
			// Add the new key-value pair to the cache and the front of recentlyUsed list
			cache.put(key, value);
			recentlyUsed.addFirst(key);
		}
	}

	public static void main(String[] args) {
		LRUCacheString lruCache = new LRUCacheString(3);
		lruCache.put("1", "One");
		lruCache.put("2", "Two");
		lruCache.put("3", "Three");

		System.out.println(lruCache.get("1")); // Output: One

		lruCache.put("4", "Four");

		System.out.println(lruCache.get("2")); // Output: null (evicted due to LRU)
	}
}
