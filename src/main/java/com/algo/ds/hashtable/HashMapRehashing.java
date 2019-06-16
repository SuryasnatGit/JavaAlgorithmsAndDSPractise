package com.algo.ds.hashtable;

import java.util.ArrayList;
import java.util.List;

public class HashMapRehashing<K, V> {

	// class level variables
	private int listSize; // number of K,V pairs stored
	private int bucketSize; // size of bucket array
	private List<MapNode<K, V>> buckets;
	private final double DEFAULT_LOAD_FACTOR = 0.75;

	public HashMapRehashing() {
		listSize = 0;
		bucketSize = 5;
		buckets = new ArrayList<>(bucketSize);
		for (int i = 0; i < bucketSize; i++) {
			// initialize to null
			buckets.add(null);
		}
	}

	private int getBucketIndicator(K key) {
		int hashCode = key.hashCode();
		return hashCode % bucketSize;
	}

	public void insert(K key, V value) {
		int bucketInd = getBucketIndicator(key);
		MapNode<K, V> head = buckets.get(bucketInd);
		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		// if no match found
		// new node with K and V
		MapNode<K, V> newNode = new MapNode<>(key, value);
		// get the head
		head = buckets.get(bucketInd);
		newNode.next = head;
		buckets.set(bucketInd, newNode);

		// increment list size as new <k,V> pair is added to list
		listSize++;

		double loadFactor = listSize / bucketSize;
		if (loadFactor > DEFAULT_LOAD_FACTOR)
			rehash();
	}

	private void rehash() {
		List<MapNode<K, V>> tempList = buckets;
		buckets = new ArrayList<>(2 * bucketSize); // this becomes the new bucket list
		for (int i = 0; i < 2 * bucketSize; i++) {
			buckets.add(null);
		}

		// Now size is made zero
		// and we loop through all the nodes in the original bucket list(temp)
		// and insert it into the new list
		listSize = 0;
		bucketSize *= 2;
		for (MapNode<K, V> mapNode : tempList) {
			MapNode<K, V> head = mapNode;
			while (head != null) {
				K key = head.key;
				V value = head.value;
				insert(key, value);
				head = head.next;
			}
		}
	}

	class MapNode<K, V> {
		K key;
		V value;
		MapNode<K, V> next;

		public MapNode(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}
}
