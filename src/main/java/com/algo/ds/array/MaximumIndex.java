package com.algo.ds.array;

/**
 * Given an array A[] of N positive integers. The task is to find the maximum of j - i subjected to the constraint of
 * A[i] <= A[j].
 * 
 * Examples :
 * 
 * Input: {34, 8, 10, 3, 2, 80, 30, 33, 1} Output: 6 (j = 7, i = 1)
 * 
 * Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0} Output: 8 ( j = 8, i = 0)
 * 
 * Input: {1, 2, 3, 4, 5, 6} Output: 5 (j = 5, i = 0)
 * 
 * Input: {6, 5, 4, 3, 2, 1} Output: -1
 * 
 * 
 * @author surya
 *
 */
public class MaximumIndex {

	/**
	 * Time Complexity : O(n^2)
	 * 
	 * @param input
	 * @return
	 */
	public int maxIndexInefficient(int[] input) {
		int maxDiff = Integer.MIN_VALUE;
		for (int i = 0; i < input.length; i++) {
			for (int j = input.length - 1; j > i; j--) { // do this the reverse way
				if (input[i] <= input[j] && maxDiff < (j - i))
					maxDiff = j - i;
			}
		}
		return maxDiff;
	}

	/**
	 * To solve this problem, we need to get two optimum indexes of arr[]: left index i and right index j. For an
	 * element arr[i], we do not need to consider arr[i] for left index if there is an element smaller than arr[i] on
	 * left side of arr[i]. Similarly, if there is a greater element on right side of arr[j] then we do not need to
	 * consider this j for right index. So we construct two auxiliary arrays LMin[] and RMax[] such that LMin[i] holds
	 * the smallest element on left side of arr[i] including arr[i], and RMax[j] holds the greatest element on right
	 * side of arr[j] including arr[j]. After constructing these two auxiliary arrays, we traverse both of these arrays
	 * from left to right. While traversing LMin[] and RMax[] if we see that LMin[i] is greater than RMax[j], then we
	 * must move ahead in LMin[] (or do i++) because all elements on left of LMin[i] are greater than or equal to
	 * LMin[i]. Otherwise we must move ahead in RMax[j] to look for a greater j  i value.
	 * 
	 * @param input
	 * @return
	 */
	public int maxDiffEfficient(int[] input) {
		int l = input.length;
		int[] lMin = new int[l];
		int[] rMax = new int[l];

		// construct lMin so that it stores min value from input[0] to input[i-1]
		lMin[0] = input[0];
		for (int i = 1; i < l; i++) {
			lMin[i] = Math.min(lMin[i], input[i - 1]);
		}

		// construct rMax so that it stores max value from input[j] to input[l-1]
		rMax[l - 1] = input[l - 1];
		for (int j = l - 2; j >= 0; --j)
			rMax[j] = Math.max(input[j], rMax[j + 1]);

		/*
		 * Traverse both arrays from left to right to find optimum j - i This process is similar to merge() of MergeSort
		 */
		int i = 0;
		int j = 0;
		int maxDiff = -1;
		while (j < l && i < l) {
			if (lMin[i] < rMax[j]) {
				maxDiff = Math.max(maxDiff, j - i);
				j = j + 1;
			} else
				i = i + 1;
		}
		return maxDiff;
	}

	public static void main(String[] args) {
		MaximumIndex mi = new MaximumIndex();
		int[] input = { 34, 8, 10, 3, 2, 80, 30, 33, 1 };
		System.out.println(mi.maxIndexInefficient(input));
		System.out.println(mi.maxDiffEfficient(input));
	}

}
