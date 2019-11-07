package com.algo.ds.sorting;

import java.util.Arrays;

/**
 * Selection sort is a unstable, in-place sorting algorithm known for its simplicity, and it has performance advantages
 * over more complicated algorithms in certain situations, particularly where auxiliary memory is limited. It can be
 * implemented as a stable sort. It has O(n^2) time complexity, making it inefficient to use on large lists. Among
 * simple average-case O(n^2) algorithms, selection sort almost always outperforms bubble sort and generally performs
 * worse than the similar insertion sort.
 * 
 * The biggest advantage of using selection sort is that we only requires maximum n swaps (memory write) where n is the
 * length of the input. insertion sort, on the other hand, takes O(n^2) number of writes. This can be very important if
 * memory write operation is significantly more expensive than memory read operation, such as with Flash memory, where
 * every write lessens the lifespan of the memory.
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
		// s.sort_iterative(arr);
		s.sort_recursive(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	public void sort_iterative(int[] arr) {
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

	public void sort_recursive(int[] arr, int i, int n) {
		int min = i;
		for (int j = i + 1; j < n; j++) {
			if (arr[j] < arr[min])
				min = j;
		}

		swap(arr, min, i);

		if (i + 1 < n)
			sort_recursive(arr, i + 1, n);
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
