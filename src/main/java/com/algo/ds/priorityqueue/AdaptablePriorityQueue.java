package com.algo.ds.priorityqueue;

public interface AdaptablePriorityQueue<K, V> {

	void remove(Entry<K, V> entry);

	void replaceKey(Entry<K, V> entry, K key);

	void replaceValue(Entry<K, V> entry, V value);

}
