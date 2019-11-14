package com.algo.ds.array;

import java.util.Arrays;

/**
 * 
 * Given an array containing only 0’s, 1’s and 2’s, sort the array in linear time and using constant space.
 * 
 * Input: { 0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0 }
 * 
 * output : { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2 }
 * 
 * 
 */
public class DutchNationalFlagProblem {

	// Solution 1 - Simple solution would be to perform count sort. We count the number of 0’s, 1’s and 2’s and then put
	// them in the array in their correct order. The time complexity of above solution is O(n) but it requires two
	// traversal of the array.

	// Solution 2 - 3 way partition

	public void partition(int[] arr) {
		int start = 0, mid = 0, pivot = 1;
		int end = arr.length - 1;

		while (mid <= end) {
			if (arr[mid] < pivot) {
				swap(arr, start, mid);
				++start;
				++mid;
			} else if (arr[mid] > pivot) {
				swap(arr, mid, end);
				--end;
			} else
				++mid;
		}
	}

	private void swap(int[] arr, int start, int end) {
		int temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
	}

	public static void main(String[] args) {
		DutchNationalFlagProblem d = new DutchNationalFlagProblem();
		int[] arr = { 0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0 };
		System.out.println(Arrays.toString(arr));
		d.partition(arr);
		System.out.println(Arrays.toString(arr));
	}
}
