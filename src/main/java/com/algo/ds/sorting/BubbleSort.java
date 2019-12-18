package com.algo.ds.sorting;

/**
 * Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in
 * wrong order.
 * 
 * Worst and Average Case Time Complexity: O(n*n). Worst case occurs when array is reverse sorted.
 * 
 * Best Case Time Complexity: O(n). Best case occurs when array is already sorted.
 * 
 * Auxiliary Space: O(1)
 * 
 * @author Suryasnat
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * This function always runs O(n^2) time even if the array is sorted.
	 * 
	 * @param arr
	 */
	public void doBubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * It can be optimized by stopping the algorithm if inner loop didnt cause any swap
	 * 
	 * @param arr
	 */
	public void bubbleSortOptimized(int[] arr) {
		int n = arr.length;
		boolean swapped = false;
		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr[j], arr[j + 1]);
					swapped = true;
				}
			}
			// IF no two elements were swapped by inner loop, then break
			if (!swapped)
				break;
		}
	}

	private void swap(int i, int j) {
		int temp = i;
		i = j;
		j = temp;
	}

	public void bubbleSortRecursion(int[] arr, int n) {
		// base case
		if (n == 1)
			return;

		// one pass
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}

		// largest element is fixed. recur for remaining array
		bubbleSortRecursion(arr, n - 1);
	}

}
