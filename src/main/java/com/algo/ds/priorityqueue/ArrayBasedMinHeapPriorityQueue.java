package com.algo.ds.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.algo.ds.linkedlist.PositionalList;

/**
 * Heap-Order Property: In a heap T, for every position p other than the root, the key stored at p is greater than or
 * equal to the key stored at ps parent.
 * 
 * Complete Binary Tree Property: A heap T with height h is a complete binary tree if levels 0,1,2, . . . ,h-1 of T have
 * the maximal number of nodes possible (namely, level i has 2i nodes, for 0 <= i <= h-1) and the remaining nodes at
 * level h reside in the leftmost possible positions at that level.
 * 
 * Min Heap implementation.
 * 
 * @author Suryasnat
 *
 * @param <K>
 * @param <V>
 */
public class ArrayBasedMinHeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	// binary heap represented by array list
	private List<Entry<K, V>> heap = new ArrayList<>();

	public ArrayBasedMinHeapPriorityQueue() {
		super();
	}

	public ArrayBasedMinHeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * create a priority queue initialized with given key, value pairs
	 * 
	 * @param keys
	 * @param values
	 */
	public ArrayBasedMinHeapPriorityQueue(K[] keys, V[] values) {
		super();
		for (int i = 0; i < Math.min(keys.length, values.length); i++) {
			heap.add(new PQEntry(keys[i], values[i]));
		}
		heapify();
	}

	/**
	 * Time complexity - O(1)
	 */
	@Override
	public int size() {
		return heap.size();
	}

	/**
	 * Time complexity - O(1) for array representation and O(log N) for linked list representation
	 */
	@Override
	public Entry<K, V> insert(K key, V value) {
		checkKey(key);
		Entry<K, V> entry = new PQEntry(key, value);
		heap.add(entry);
		upheap(size() - 1);
		return entry;
	}

	/**
	 * Time complexity - O(1)
	 */
	@Override
	public Entry<K, V> min() {
		if (heap.isEmpty())
			return null;
		return heap.get(0);
	}

	/**
	 * Time complexity - O(1) for array representation and O(log N) for linked list representation
	 */
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

	public List<Entry<K, V>> getHeap() {
		return heap;
	}

	public <E> void pqSort(PositionalList<E> S, PriorityQueue<E, V> P) {
		int n = S.size();
		for (int i = 0; i < n; i++) {
			E elem = S.remove(S.first());
			P.insert(elem, null);
		}
		// key added to S in increasing order
		for (int i = 0; i < n; i++) {
			E key = P.removeMin().getKey();
			S.addLast(key);
		}
	}

	/**
	 * performs bottom-up heap construction in linear time. start at parent of last entry
	 */
	private void heapify() {
		int rootIndex = parent(size() - 1);
		for (int j = rootIndex; j >= 0; j--) {
			downheap(j);
		}
	}

	protected int parent(int index) {
		// if index is already a root node
		if (index == 0)
			return 0;

		return (index - 1) / 2;
	}

	protected int left(int index) {
		return 2 * index + 1;
	}

	protected int right(int index) {
		return 2 * index + 2;
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

	public static void main(String[] args) {
		ArrayBasedMinHeapPriorityQueue<Integer, Integer> pq = new ArrayBasedMinHeapPriorityQueue<>();
		pq.insert(5, 5);
		pq.insert(4, 4);
		pq.insert(7, 7);
		pq.insert(1, 1);
		System.out.println(pq.min());
		System.out.println(pq.removeMin());
		System.out.println(pq.min());
		System.out.println(pq.size());
	}
}
