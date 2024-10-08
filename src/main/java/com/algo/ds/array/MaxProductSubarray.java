package com.algo.ds.array;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 
 * Examples:
 * 
 * Input: arr[] = {6, -3, -10, 0, 2} Output: 180 // The subarray is {6, -3, -10}
 * 
 * Input: arr[] = {-1, -3, -10, 0, 60} Output: 60 // The subarray is {60}
 * 
 * Input: arr[] = {-2, -3, 0, -2, -40} Output: 80 // The subarray is {-2, -40}
 *
 * Time complexity is O(n) Space complexity is O(1)
 *
 * http://www.geeksforgeeks.org/maximum-product-subarray/ https://leetcode.com/problems/maximum-product-subarray/
 * 
 * Category : Medium
 */
public class MaxProductSubarray {

	public int maxProduct(int[] nums) {
		int min = 1;
		int max = 1;
		int maxSoFar = nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				max = max * nums[i];
				min = Math.min(min * nums[i], 1);
				maxSoFar = Math.max(maxSoFar, max);
			} else if (nums[i] == 0) {
				min = 1;
				max = 1;
				maxSoFar = Math.max(maxSoFar, 0);
			} else {
				int t = max * nums[i];
				maxSoFar = Math.max(maxSoFar, min * nums[i]);
				max = Math.max(1, min * nums[i]);
				min = t;
			}
		}
		return maxSoFar;
	}

	public static void main(String args[]) {
		MaxProductSubarray mps = new MaxProductSubarray();
		int input[] = { -6, -3, 8, -9, -1, -1, 3, 6, 9, 0, 3, -1 };
		System.out.println(mps.maxProduct(input));
	}
}
