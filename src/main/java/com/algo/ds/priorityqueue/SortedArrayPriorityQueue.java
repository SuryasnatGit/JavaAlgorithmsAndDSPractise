package com.algo.ds.priorityqueue;

public class SortedArrayPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	private PQEntry<K, V>[] entryArr; // elements
	private int n; // number of elements

	public SortedArrayPriorityQueue(int capacity) {
		entryArr = new PQEntry[capacity];
		n = 0;
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		PQEntry<K, V> entry = new PQEntry<>(key, value);
		int i = n - 1;
		while (i >= 0 && compare(entry, entryArr[i]) < 0) {
			entryArr[i + 1] = entryArr[i];
			i--;
		}
		entryArr[i + 1] = entry;
		n++;
		return entry;
	}

	@Override
	public Entry<K, V> min() {
		return entryArr[n];
	}

	@Override
	public Entry<K, V> removeMin() {
		return entryArr[--n];
	}

}
