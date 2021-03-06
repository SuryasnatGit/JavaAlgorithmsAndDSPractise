package com.algo.ds.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays: arr1[0..m-1] and arr2[0..n-1]. Find whether arr2[] is a subset of arr1[] or not. Both the arrays
 * are not in sorted order. It may be assumed that elements in both array are distinct. Examples: Input: arr1[] = {11,
 * 1, 13, 21, 3, 7}, arr2[] = {11, 3, 7, 1} Output: arr2[] is a subset of arr1[]
 * 
 * Input: arr1[] = {1, 2, 3, 4, 5, 6}, arr2[] = {1, 2, 4} Output: arr2[] is a subset of arr1[]
 * 
 * Input: arr1[] = {10, 5, 2, 23, 19}, arr2[] = {19, 5, 3} Output: arr2[] is not a subset of arr1[]
 * 
 * @author surya
 *
 */
public class ArraySubsetOfAnotherArray {

	/**
	 * Solution 1 - Using 2 loops. Time Complexity: O(m*n)
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public boolean isArraySubSet1(int[] arr1, int[] arr2) {
		int index2 = 0;
		int index1 = 0;
		for (index2 = 0; index2 < arr2.length; index2++) {
			for (index1 = 0; index1 < arr1.length; index1++) {
				if (arr2[index2] == arr1[index1])
					break;
			}
			// if nothing matches
			if (index1 == arr1.length)
				return false;
		}
		return true;
	}

	/**
	 * Solution 2 - Using sorting + binary search. time complexity - O(m log m + n log m)
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public boolean isArraySubset2(int[] arr1, int[] arr2) {
		Arrays.sort(arr1); // m log m
		for (int num2 : arr2) { // n
			if (!binarySearch(arr1, num2)) // log m
				return false;
		}
		return true;
	}

	private boolean binarySearch(int[] arr, int i) {
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (arr[m] == i)
				return true;
			else if (arr[m] < i)
				l = m + 1;
			else
				r = m - 1;
		}
		return false;
	}

	/**
	 * Solution 4 - using hashing. time complexity - O(m + n) space - O(m)
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public boolean isArraySubset4(int[] arr1, int[] arr2) {
		// base
		int m = arr1.length;
		int n = arr2.length;

		if (m < n)
			return false;

		Set<Integer> set = new HashSet<>();
		for (int num1 : arr1) {
			set.add(num1);
		}

		for (int num2 : arr2) {
			if (!set.contains(num2))
				return false;
		}

		return true;
	}

	public static void main(String[] args) {
		ArraySubsetOfAnotherArray su = new ArraySubsetOfAnotherArray();
		int[] arr1 = { 2, 1, 3, 4, 5, 6 };
		int[] arr2 = { 1, 2, 4, 6 };
		System.out.println(su.isArraySubSet1(arr1, arr2));
		System.out.println(su.isArraySubset2(arr1, arr2));
		System.out.println(su.isArraySubset4(arr1, arr2));
	}
}
