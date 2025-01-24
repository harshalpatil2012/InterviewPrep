package com.practice.ds.map;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheImpl<K, V> {
	private final int maxSize;
	private final Map<K, V> cache;
	private int hits;
	private int misses;

	public LRUCacheImpl(int maxSize) {
		this.maxSize = maxSize;
		this.cache = new LinkedHashMap<K, V>(16, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return size() > maxSize;
			}
		};
	}

	public V get(K key) {
		V value = cache.get(key);
		if (value != null) {
			hits++;
		} else {
			misses++;
		}
		return value;
	}

	public void put(K key, V value) {
		cache.put(key, value);
	}

	public int getHits() {
		return hits;
	}

	public int getMisses() {
		return misses;
	}
}

public class LRUCache {

	public static void main(String[] args) {

		LRUCacheImpl<String, byte[]> cache = new LRUCacheImpl<>(1000000);
		cache.put("key1", new byte[100]);
		cache.put("key2", new byte[200]);
		byte[] value1 = cache.get("key1");
		byte[] value2 = cache.get("key2");
		System.out.println("Hits: " + cache.getHits());
		System.out.println("Misses: " + cache.getMisses());
	}

}
