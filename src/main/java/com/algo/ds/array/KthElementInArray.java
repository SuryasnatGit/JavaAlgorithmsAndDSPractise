package com.algo.ds.array;

import java.util.Arrays;

import com.algo.ds.graph.BinaryMaxHeap;
import com.algo.ds.graph.BinaryMinHeap;

/**
 * Kth largest element in an array.
 * 
 * Given an array and a number k where k is smaller than size of array, we need to find the k’th
 * smallest element in the given array. It is given that ll array elements are distinct.
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
	 * Approach 1 - Simple. using simple sort. Time complexity - O(nlogn)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthLargestUsingSort(int[] arr, int k) {
		// sort array
		Arrays.sort(arr); // O(n log n)
		return arr[arr.length - k - 1]; // returns kth largest.
	}

	/**
	 * Approach 2 - Using min heap - heap select. time complexity - O(n + k log n) .
	 * 
	 * We can find k’th smallest element in time complexity better than O(nLogn). A simple optimization
	 * is to create a Min Heap of the given n elements and call extractMin() k times.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthSmallestUsingMinHeap(int[] arr, int k) {
		BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i], arr[i]);
		}
		for (int i = 0; i < k; i++) {
			int num = heap.extractMin();
		}
		return heap.min();
	}

	/**
	 * We can also use Max Heap for finding the k’th smallest element.
	 * 
	 * Following is algorithm.
	 * 
	 * 1) Build a Max-Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)
	 * 
	 * 2) For each element, after the k’th element (arr[k] to arr[n-1]), compare it with root of MH.
	 * 
	 * a) If the element is less than the root then make it root and call heapify for MH <br/>
	 * b) Else ignore it. // The step 2 is O((n-k)*logk)
	 * 
	 * 3) Finally, root of the MH is the kth smallest element.
	 * 
	 * Time complexity of this solution is O(k + (n-k)*Logk)
	 * 
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthSmallestUsingMaxHeap(int[] arr, int k) {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		for (int i = 0; i < k; i++) {
			heap.add(arr[i], arr[i]);
		}
		int n = arr.length;
		for (int i = k; i < n; i++) {
			if (arr[i] < heap.max()) {
				heap.replaceMax(arr[i]);
			}
		}
		return heap.max();
	}

	/**
	 * 1) Build a Max Heap tree in O(n) 2) Use Extract Max k times to get k maximum elements from the
	 * Max Heap O(klogn)
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

	/**
	 * Using quick select. The idea is, not to do complete quicksort, but stop at the point where pivot
	 * itself is k’th smallest element. Also, not to recur for both left and right sides of pivot, but
	 * recur for one of them according to the position of pivot. The worst case time complexity of this
	 * method is O(n2), but it works in O(n) on average.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
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
		int arr[] = { 6, 2, 1, 3, 8, 9, 4 };
		KthElementInArray kthElement = new KthElementInArray();
		System.out.print(kthElement.kthElement(arr, 5));
		// System.out.print(Arrays.toString(arr));
		// System.out.println(kthElement.kthSmallestUsingMinHeap(arr, 5));
	}

}
