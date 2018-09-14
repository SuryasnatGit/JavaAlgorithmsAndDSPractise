package com.algo.ds.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This gets bit tricky here. By default the Priority Queue works as min-Heap.
 * To implement the max-Heap we need to change the way priority queue works
 * internally by overriding the Comparator.
 *
 * 
 */
public class MaxHeapUsingPriorityQueue {

	PriorityQueue<Integer> pq;

	public MaxHeapUsingPriorityQueue() {
		pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
	}

	public void insert(int[] input) {
		for (int i = 0; i < input.length; i++) {
			pq.offer(input[i]);
		}
	}

	public int peek() {
		return pq.peek();
	}

	public int extractMax() {
		return pq.poll();
	}

	public int getSize() {
		return pq.size();
	}

	public static void main(String[] args) {
		MaxHeapUsingPriorityQueue min = new MaxHeapUsingPriorityQueue();
		int[] input = new int[] { 1, 3, 7, 2, 4, 8, 9 };
		min.insert(input);
		System.out.println(min.extractMax());
		System.out.println(min.extractMax());
		System.out.println(min.extractMax());
		System.out.println(min.getSize());
	}
}
