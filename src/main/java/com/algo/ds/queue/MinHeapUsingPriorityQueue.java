package com.algo.ds.queue;

import java.util.PriorityQueue;

public class MinHeapUsingPriorityQueue {

	private PriorityQueue<Integer> pq;

	public MinHeapUsingPriorityQueue() {
		pq = new PriorityQueue<>();
	}

	/**
	 * complexity - n log n
	 * 
	 * @param input
	 */
	public void insert(int[] input) {
		for (int i = 0; i < input.length; i++) {
			pq.offer(input[i]); // log n
		}
	}

	public int peek() {
		return pq.peek();
	}

	/**
	 * log n
	 * 
	 * @return
	 */
	public int extractMin() {
		return pq.poll();
	}

	public int getSize() {
		return pq.size();
	}

	public static void main(String[] args) {
		MinHeapUsingPriorityQueue min = new MinHeapUsingPriorityQueue();
		int[] input = new int[] { 1, 3, 7, 2, 4, 8, 9 };
		min.insert(input);
		System.out.println(min.extractMin());
		System.out.println(min.extractMin());
		// System.out.println(min.extractMin());
		System.out.println(min.getSize());
	}
}
