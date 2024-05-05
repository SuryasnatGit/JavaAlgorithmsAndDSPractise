package com.leetcode;

/**
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * 
 * We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of
 * the numbers to the right of the index.
 * 
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most
 * pivot index.
 * 
 * Example 1:
 * 
 * Input:
 * 
 * nums = [1, 7, 3, 6, 5, 6] Output: 3
 * 
 * Explanation: The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right
 * of index 3. Also, 3 is the first index where this occurs.
 * 
 * Example 2:
 * 
 * Input:
 * 
 * nums = [1, 2, 3] Output: -1
 * 
 * Explanation: There is no index that satisfies the conditions in the problem statement
 * 
 */
public class PivotIndex {

	public static void main(String[] args) {
		PivotIndex pi = new PivotIndex();
		System.out.println(pi.getPivotIndex1(new int[] { 1, 7, 3, 6, 5, 6 }));
		System.out.println(pi.getPivotIndex1(new int[] { 1, 2, 3 }));
		System.out.println(pi.getPivotIndex1(new int[] { 1, 2, 5, 7, 8 }));
		System.out.println(pi.getPivotIndex2(new int[] { 1, 7, 3, 6, 5, 6 }));
		System.out.println(pi.getPivotIndex2(new int[] { 1, 2, 3 }));
		System.out.println(pi.getPivotIndex2(new int[] { 1, 2, 5, 7, 8 }));
	}

	/*
	 * T - O(n) S - O(n)
	 */
	public int getPivotIndex1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int[] prefixSum = new int[nums.length + 1];

		// populate prefix sum
		prefixSum[0] = 0;
		for (int i = 0; i < nums.length; i++) {
			prefixSum[i + 1] = prefixSum[i] + nums[i];
		}

		for (int i = 1; i < prefixSum.length; i++) {
			if (prefixSum[i] == prefixSum[prefixSum.length - 1] - prefixSum[i - 1]) {
				return i - 1;
			}
		}

		return -1;
	}

	/*
	 * T - O(n) S - O(1)
	 */
	public int getPivotIndex2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int sum = 0, leftSum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		for (int i = 0; i < nums.length; i++) {
			if (leftSum == sum - leftSum - nums[i]) {
				return i;
			}
			leftSum += nums[i];
		}
		return -1;
	}

}
