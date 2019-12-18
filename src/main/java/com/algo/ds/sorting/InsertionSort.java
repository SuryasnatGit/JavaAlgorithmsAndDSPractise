package com.algo.ds.sorting;

import java.util.Arrays;

/**
 * Time Complexity: Worst case : O(n^2) Best case : O(n) Average case : O(n^2)
 * 
 * Space complexity: Worst case : O(1) auxiliary
 * 
 * Auxiliary Space: O(1)
 * 
 * Benefits: <br/>
 * It is faster than most O(nlogn) sorting algorithms for small lists. <br/>
 * It is very memory efficient requiring only O(1) auxiliary space for the single item that is being moved. <br/>
 * It is a stable sort; equal elements appear in the same order in the sorted list. <br/>
 * It is an adaptive sort; its fast when sorting mostly sorted lists or when adding items to an already sorted list.
 * <br/>
 * It is really easy to implement.
 * 
 * Boundary Cases: Worst case O(n^2) happens when array is sorted in reverse order. Best case O(n) happens when elements
 * are already sorted.
 * 
 * Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost
 * sorted, only few elements are misplaced in complete big array.
 * 
 * @author Suryasnat
 *
 */
public class InsertionSort {

	public void sort_iterative(int[] arr) {
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
