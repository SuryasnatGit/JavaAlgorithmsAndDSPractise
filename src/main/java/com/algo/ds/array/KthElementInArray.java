package com.algo.ds.array;

import java.util.Arrays;

import com.algo.ds.graph.BinaryMaxHeap;
import com.algo.ds.graph.BinaryMinHeap;
import com.algo.ds.queue.PriorityQueueAsArray;

/**
 * Kth largest element in an array. Use quick select of quicksort to find the solution in hopefully O(nlogn) time. Test
 * cases Sorted array Reverse sorted array
 */
public class KthElementInArray {

	/**
	 * Time - O(nlogn)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthLargestSimple(int[] arr, int k) {
		// sort array
		Arrays.sort(arr);
		return arr[arr.length - k - 1];
	}

	public int kthLargestUsingPriorityQueue(int[] arr, int k) {
		PriorityQueueAsArray queue = new PriorityQueueAsArray(arr.length);
	}

	public int kthLargestUsingMinHeap(int[] arr, int k) {
		BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i], arr[i]);
		}
		int num = heap.extractMin();
	}

	/**
	 * 1) Build a Max Heap tree in O(n) 2) Use Extract Max k times to get k maximum elements from the Max Heap O(klogn)
	 * 
	 * Time complexity: O(n + klogn)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthLargestUsingMaxHeap(int[] arr, int k) {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i], arr[i]);
		}
		int res = 0;
		for (int i = 0; i < k; i++) {
			res = heap.max();
		}
		return res;
	}

	public int kthElement(int arr[], int k) {
		int low = 0;
		int high = arr.length - 1;
		int pos = 0;
		while (true) {
			pos = quickSelect(arr, low, high);
			if (pos == (k + low)) {
				return arr[pos];
			}
			if (pos > k + low) {
				high = pos - 1;
			} else {
				k = k - (pos - low + 1);
				low = pos + 1;
			}
		}
	}

	private int quickSelect(int arr[], int low, int high) {
		int pivot = low;
		low++;
		while (low <= high) {
			if (arr[pivot] > arr[low]) {
				low++;
				continue;
			}
			if (arr[pivot] <= arr[high]) {
				high--;
				continue;
			}
			swap(arr, low, high);
		}
		if (arr[high] < arr[pivot]) {
			swap(arr, pivot, high);
		}
		return high;
	}

	private void swap(int arr[], int low, int high) {
		int temp = arr[low];
		arr[low] = arr[high];
		arr[high] = temp;
	}

	public static void main(String args[]) {
		int arr[] = { 6, 2, 1, 6, 8, 9, 6 };
		KthElementInArray kthElement = new KthElementInArray();
		System.out.print(kthElement.kthElement(arr, arr.length / 2));
		System.out.print(Arrays.toString(arr));
	}

}
