package com.algo.ds.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Heap-Order Property: In a heap T, for every position p other than the root,
 * the key stored at p is greater than or equal to the key stored at p’s parent.
 * 
 * Complete Binary Tree Property: A heap T with height h is a complete binary
 * tree if levels 0,1,2, . . . ,h-1 of T have the maximal number of nodes
 * possible (namely, level i has 2i nodes, for 0 <= i <= h-1) and the remaining
 * nodes at level h reside in the leftmost possible positions at that level.
 * 
 * @author Suryasnat
 *
 * @param <K>
 * @param <V>
 */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	// binary heap represented by array list
	private List<Entry<K, V>> heap = new ArrayList<>();

	public HeapPriorityQueue() {
		super();
	}

	public HeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	protected int parent(int j) {
		return (j - 1) / 2;
	}

	protected int left(int j) {
		return 2 * j + 1;
	}

	protected int right(int j) {
		return 2 * j + 2;
	}

	protected boolean hasLeft(int j) {
		return left(j) < heap.size();
	}

	protected boolean hasRight(int j) {
		return right(j) < heap.size();
	}

	protected void swap(int i, int j) {
		Entry<K, V> temp = heap.get(j);
		heap.set(i, temp);
		heap.set(j, heap.get(i));
	}

	protected void upheap(int j) {
		while (j > 0) {
			int p = parent(j);
			if (compare(heap.get(j), heap.get(p)) >= 0)
				break;
			swap(j, p);
			j = p;
		}
	}

	protected void downheap(int j) {
		while (hasLeft(j)) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			if (hasRight(j)) {
				int rightIndex = right(j);
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
					smallChildIndex = rightIndex;
			}
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
				break;
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		checkKey(key);
		Entry<K, V> entry = new PQEntry(key, value);
		heap.add(entry);
		upheap(size() - 1);
		return entry;
	}

	@Override
	public Entry<K, V> min() {
		if (heap.isEmpty())
			return null;
		return heap.get(0);
	}

	@Override
	public Entry<K, V> removeMin() {
		if (heap.isEmpty())
			return null;
		Entry<K, V> entry = heap.get(0);
		swap(0, size() - 1);
		heap.remove(size() - 1);
		downheap(0);
		return entry;
	}

}
