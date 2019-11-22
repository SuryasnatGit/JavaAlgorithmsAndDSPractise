package com.algo.ds.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomHashMap<K, V> {

	@SuppressWarnings("hiding")
	class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		public Entry(K key, V val) {
			this.key = key;
			this.value = val;
		}
	}

	private Entry<K, V>[] array; // to hold the list of values for a bucket
	private int capacity;
	private List<ReentrantReadWriteLock> locks; // provide locking mechanism for each bucket

	public CustomHashMap() {
		this(16);
	}

	@SuppressWarnings("unchecked")
	public CustomHashMap(int capacity) {
		this.array = (Entry<K, V>[]) new Object[capacity];
		this.capacity = capacity;
		this.locks = new ArrayList<ReentrantReadWriteLock>(capacity);
		for (int i = 0; i < capacity; i++) {
			locks.add(new ReentrantReadWriteLock());
		}
	}

	public void put(K key, V val) {
		if (isFull()) {
			System.out.println("Map is full.. resizing");
			resize();
		}

		int index = key.hashCode() % capacity;
		Entry<K, V> newentry = new Entry<K, V>(key, val);

		try {
			locks.get(index).writeLock().lock();
			if (array[index] == null) {
				array[index] = newentry;
			} else {
				Entry<K, V> curr = array[index];
				Entry<K, V> prev = null;
				while (curr != null) {
					if (curr.key.equals(key)) {
						curr.value = val;
						return;
					}
					prev = curr;
					curr = curr.next;
				}
				prev.next = newentry;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locks.get(index).writeLock().unlock();
		}
	}

	public V get(K key) {
		if (isEmpty()) {
			System.out.println("map is empty");
			return null;
		}

		int index = key.hashCode() % capacity;

		try {
			locks.get(index).readLock().lock();
			Entry<K, V> curr = array[index];
			while (curr != null) {
				if (curr.key.equals(key)) {
					return curr.value;
				}
				curr = curr.next;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locks.get(index).readLock().unlock();
		}
		return null;
	}

	public boolean remove(K key) {
		if (isEmpty()) {
			System.out.println("map is empty");
			return false;
		}

		int index = key.hashCode() % capacity;

		if (array[index] == null) {
			return false;
		} else {
			Entry<K, V> curr = array[index];
			Entry<K, V> prev = null;
			while (curr != null) {
				if (curr.key.equals(key)) {
					if (prev == null) {
						array[index] = curr.next;
					} else {
						prev.next = curr.next;
					}
					return true;
				}

				prev = curr;
				curr = curr.next;
			}
		}
		return false;
	}

	private void resize() {
		int oldSize = capacity;
		capacity = capacity * 2;
		Entry<K, V>[] oldArray = array; // copy from old array
		array = (Entry<K, V>[]) new Object[capacity];

		for (int i = 0; i < oldSize; i++) {
			Entry<K, V> entry = oldArray[i];
			while (entry != null) {
				K key = entry.key;
				V val = entry.value;

				put(key, val);
				entry = entry.next;
			}
		}
	}

	private boolean isEmpty() {
		return this.capacity == 0;
	}

	private boolean isFull() {
		return this.capacity == array.length;
	}

	public static void main(String[] args) {
		CustomHashMap<Integer, String> map = new CustomHashMap<>(3);
		map.put(1, "a");
		map.put(2, "a");
		map.put(3, "a");
		map.put(4, "a");
		System.out.println(map.get(3));
	}
}
