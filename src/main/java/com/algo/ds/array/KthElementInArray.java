package com.algo.ds.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Kth largest element in an array.
 * 
 * Given an array and a number k where k is smaller than size of array, we need to find the kth smallest element in the
 * given array. It is given that all array elements are distinct.
 * 
 * Examples:
 * 
 * Input: arr[] = {7, 10, 4, 3, 20, 15} k = 3 Output: 7
 * 
 * Input: arr[] = {7, 10, 4, 3, 20, 15} k = 4 Output: 10
 * 
 * Test cases.
 * 
 * Sorted array Reverse sorted array
 */
public class KthElementInArray {

	/**
	 * Approach 1 - Simple. using simple sort. Time complexity - O(n log n)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthSmallestUsingSort(int[] arr, int k) {
		// sort array
		Arrays.sort(arr); // O(n log n)
		return arr[k - 1]; // returns kth smallest.
	}

	/**
	 * Approach 2 - Using min heap - heap select.
	 * 
	 * We can find kth smallest element in time complexity better than O(n Log n). A simple optimization is to create a
	 * Min Heap of the given n elements and call extractMin() k times.
	 * 
	 * time complexity - O(n + k log n) .
	 * 
	 */
	public int kthSmallestUsingMinHeap(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k > arr.length)
			return -1;

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// O(n)
		for (int num : arr) {
			pq.add(num);
		}

		int res = 0;
		while (!pq.isEmpty() && k-- > 0) { // k times
			res = pq.poll(); // O(log n)
		}

		return res;
	}

	/**
	 * Approach 3 : We can also use Max Heap for finding the kth smallest element.
	 * 
	 * Time complexity of this solution is O(k + (n-k)*Logk)
	 */
	public int kthSmallestUsingMaxHeap(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k > arr.length)
			return -1;

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

		// add first k elements into queue. O(k)
		for (int i = 0; i < k; i++) {
			pq.add(arr[i]);
		}

		// heapify will happen to reorder. O((n-k) * log k)
		for (int i = k; i < arr.length; i++) {
			if (arr[i] < pq.peek()) {
				pq.poll();
				pq.add(arr[i]);
			}
		}

		return pq.peek();
	}

	/**
	 * 1) Build a Max Heap tree in O(n) 2) Use Extract Max k times to get k maximum elements from the Max Heap O(klogn)
	 * 
	 * Time complexity: O(n + k log n)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthLargestUsingMaxHeap(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k > arr.length)
			return -1;

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

		for (int num : arr) {
			pq.add(num);
		}

		int res = 0;
		while (!pq.isEmpty() && k-- > 0) {
			res = pq.poll();
		}

		return res;
	}

	public static void main(String args[]) {
		int arr[] = { 6, 2, 1, 3, 8, 9, 4 };
		KthElementInArray kthElement = new KthElementInArray();
		System.out.println(kthElement.kthSmallestUsingSort(arr, 5));// 6
		System.out.println(kthElement.kthSmallestUsingMinHeap(arr, 5)); // 6
		System.out.println(kthElement.kthSmallestUsingMaxHeap(arr, 5)); // 6
		System.out.println(kthElement.kthLargestUsingMaxHeap(arr, 5)); // 3
	}

}
