package com.algo.ds.sorting;

import java.util.Arrays;

/**
 * Time Complexity: O(n*n)
 * 
 * Auxiliary Space: O(1)
 * 
 * Boundary Cases: Insertion sort takes maximum time to sort if elements are sorted in reverse order. And it takes
 * minimum time (Order of n) when elements are already sorted.
 * 
 * Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost
 * sorted, only few elements are misplaced in complete big array.
 * 
 * @author Suryasnat
 *
 */
public class InsertionSort {

	public void sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;

			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	public void sort_recursive(int[] arr, int n) {
		if (n <= 1)
			return;

		sort_recursive(arr, n - 1);

		int last = arr[n - 1];
		int j = n - 2;
		while (j >= 0 && arr[j] > last) {
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = last;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 1, 2, 4, 6, 8 };
		InsertionSort s = new InsertionSort();
		s.sort_recursive(arr, arr.length);
		System.out.println(Arrays.toString(arr));

	}

}
