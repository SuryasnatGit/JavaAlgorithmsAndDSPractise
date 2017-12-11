package com.algo.ds.priorityqueue;

import java.util.Arrays;

public class UnsortedArrayPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	private PQEntry<K, V>[] entryArr; // elements
	private int n; // number of elements

	public UnsortedArrayPriorityQueue(int capacity) {
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
		entryArr[n++] = entry;
		return entry;
	}

	@Override
	public Entry<K, V> min() {
		int minIndex = 0;
		for (int i = 1; i < n; i++) {
			if (compare(entryArr[minIndex], entryArr[i]) > 0)
				minIndex = i;
		}
		return entryArr[minIndex];
	}

	@Override
	public Entry<K, V> removeMin() {
		int minIndex = 0;
		for (int i = 1; i < n; i++) {
			if (compare(entryArr[minIndex], entryArr[i]) > 0)
				minIndex = i;
		}
		swap(minIndex, n - 1);
		return entryArr[--n];
	}

	private void swap(int i, int j) {
		PQEntry<K, V> temp = entryArr[i];
		entryArr[i] = entryArr[j];
		entryArr[j] = temp;
	}

	private void printPQ() {
		System.out.println(Arrays.toString(entryArr));
	}

	public static void main(String[] args) {
		UnsortedArrayPriorityQueue<Integer, Integer> pq = new UnsortedArrayPriorityQueue<>(10);
		pq.insert(3, 3);
		pq.insert(1, 1);
		pq.insert(5, 5);
		pq.insert(4, 4);
		System.out.println(pq.min());
		pq.printPQ();
		System.out.println(pq.removeMin());
		pq.printPQ();
	}

}
