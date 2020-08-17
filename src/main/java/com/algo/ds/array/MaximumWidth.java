package com.algo.ds.array;

import java.util.TreeMap;

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
 * https://leetcode.com/problems/maximum-width-ramp/
 * 
 * Category : Medium
 *
 */
public class MaximumWidth {

	/**
	 * Time Complexity : O(n^2)
	 * 
	 * @param input
	 * @return
	 */
	public int maxWidthInefficient(int[] input) {
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
	 * T - O(n log n) S - O(n)
	 */
	public int maxWidthEfficient(int[] input) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int maxWidth = -1;

		for (int i = 0; i < input.length; i++) {
			if (map.floorKey(input[i]) == null) {
				map.put(input[i], i);
			} else {
				maxWidth = Math.max(maxWidth, i - map.get(map.floorKey(input[i])));
			}
		}

		return maxWidth;
	}

	public static void main(String[] args) {
		MaximumWidth mi = new MaximumWidth();
		System.out.println(mi.maxWidthInefficient(new int[] { 34, 8, 10, 3, 2, 80, 30, 33, 1 })); // 6
		System.out.println(mi.maxWidthEfficient(new int[] { 34, 8, 10, 3, 2, 80, 30, 33, 1 })); // 6
		System.out.println(mi.maxWidthEfficient(new int[] { 9, 2, 3, 4, 5, 6, 7, 8, 18, 0 })); // 8
		System.out.println(mi.maxWidthEfficient(new int[] { 1, 2, 3, 4, 5, 6 })); // 5
		System.out.println(mi.maxWidthEfficient(new int[] { 6, 5, 4, 3, 2, 1 })); // -1
	}

}
