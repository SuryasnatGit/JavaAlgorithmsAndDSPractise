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
		// Initialize the maximum, minimum, and answer with the first element.
		// The max value 'maxProduct' represents the largest product found so far and
		// could be the maximum product of a subarray ending at the current element.
		// The min value 'minProduct' represents the smallest product found so far and
		// could be the minimum product of a subarray ending at the current element.
		// This is required because a negative number could turn the smallest value into
		// the largest when multiplied by another negative number.
		int maxProduct = nums[0];
		int minProduct = nums[0];
		int answer = nums[0];

		// Iterate through the array starting from the second element.
		for (int i = 1; i < nums.length; ++i) {
			// Store the current max and min before updating them.
			int currentMax = maxProduct;
			int currentMin = minProduct;

			// Update the maxProduct to be the maximum between the current number, currentMax multiplied by the current
			// number, and currentMin multiplied by the current number. This accounts for both positive and negative
			// numbers.
			maxProduct = Math.max(nums[i], Math.max(currentMax * nums[i], currentMin * nums[i]));

			// Update the minProduct similarly by choosing the minimum value.
			minProduct = Math.min(nums[i], Math.min(currentMax * nums[i], currentMin * nums[i]));

			// Update the answer if the newly found maxProduct is greater than the previous answer.
			answer = Math.max(answer, maxProduct);
		}

		// Return the largest product of any subarray found.
		return answer;
	}

	public static void main(String args[]) {
		MaxProductSubarray mps = new MaxProductSubarray();
		System.out.println(mps.maxProduct(new int[] { -6, -3, 8, -9, -1, -1, 3, 6, 9, 0, 3, -1 }));
		System.out.println(mps.maxProduct(new int[] { 6, -3, -10, 0, 2 }));
		System.out.println(mps.maxProduct(new int[] { -1, -3, -10, 0, 60 }));
		System.out.println(mps.maxProduct(new int[] { -2, -3, 0, -2, -40 }));
	}
}
