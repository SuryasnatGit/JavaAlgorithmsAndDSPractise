package com.algo.ds.hashtable;

import java.util.ArrayList;
import java.util.List;

public class HashTableChainingLinkedList<K, V> {

	private List<Node<K, V>> buckets;
	private int bucketSize;
	private int size;

	// initialize in constructor
	public HashTableChainingLinkedList(int num) {
		buckets = new ArrayList<>();
		this.bucketSize = num;
		for (int i = 0; i < bucketSize; i++)
			buckets.add(null);
	}

	public int size() {
		return buckets.size();
	}

	public boolean isEmpty() {
		return buckets.size() == 0;
	}

	public void put(K key, V value) {
		int index = hashFunction(key);
		Node<K, V> head = buckets.get(index);
		// check if key is already present.
		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return; // return from here if key is already present, then just update value
			}
			head = head.next;
		}

		// if key is not present
		Node<K, V> newNode = new Node<K, V>(key, value);
		newNode.next = head;
		buckets.set(index, newNode);
	}

	public V get(K key) {
		int index = hashFunction(key);
		Node<K, V> head = buckets.get(index);
		while (head != null) {
			if (head.key.equals(key))
				return head.value;
			head = head.next;
		}
		return null;
	}

	public V remove(K key) {
		int index = hashFunction(key);
		Node<K, V> head = buckets.get(index);
		Node<K, V> prev = null;
		while (head != null) {
			if (head.key.equals(key))
				break;
			prev = head;
			head = head.next;
		}
		if (head == null)
			return null;

		// now head has to be removed
		if (prev != null) {
			prev.next = head.next;
		} else {
			buckets.set(index, head.next);
		}
		return head.value;
	}

	private int hashFunction(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % bucketSize;
		return index;
	}

	class Node<K, V> {
		public K key;
		public V value;
		public Node<K, V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
