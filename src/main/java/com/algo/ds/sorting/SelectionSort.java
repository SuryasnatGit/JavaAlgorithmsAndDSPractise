package com.algo.ds.sorting;

import java.util.Arrays;

/**
 * The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order)
 * from unsorted part and putting it at the beginning. The algorithm maintains two subarrays in a given array.
 * 
 * 1) The subarray which is already sorted. 2) Remaining subarray which is unsorted.
 * 
 * In every iteration of selection sort, the minimum element (considering ascending order) from the unsorted subarray is
 * picked and moved to the sorted subarray.
 * 
 * Time Complexity: O(n2) as there are two nested loops.
 * 
 * Auxiliary Space: O(1)
 * 
 * The good thing about selection sort is it never makes more than O(n) swaps and can be useful when memory write is a
 * costly operation.
 * 
 * @author Suryasnat
 *
 */
public class SelectionSort {

	public static void main(String[] args) {
		SelectionSort s = new SelectionSort();
		int[] arr = { 6, 3, 2, 4, 5, 9 };
		s.sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort(int[] arr) {
		int l = arr.length;

		for (int i = 0; i < l - 1; i++) {
			int min_index = i;
			// find the minimum index in unsorted array
			for (int j = i + 1; j < l; j++) {
				if (arr[j] < arr[min_index])
					min_index = j;
			}
			// swap element of min_index with i
			int temp = arr[min_index];
			arr[min_index] = arr[i];
			arr[i] = temp;
		}
	}

}
