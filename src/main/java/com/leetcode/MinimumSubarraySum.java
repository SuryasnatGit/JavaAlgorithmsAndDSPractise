package com.leetcode;

public class MinimumSubarraySum {

	/**
	 * Problem 1 :
	 * 
	 * Description Given an array of integers, find the subarray with smallest sum.
	 * 
	 * Return the sum of the subarray.
	 * 
	 * Note:
	 * 
	 * The subarray should contain one integer at least.
	 * 
	 * Example For [1, -1, -2, 1], return -3.
	 * 
	 * The idea of ​​Maximum Subarray Sum and Minimum Subarray Sum is exactly the same, the only difference is max and
	 * min. In various discussions and problem-solving reports, an algorithm often appears: Kadane's Algorithm .
	 * 
	 * Kadane's Algorithm actually maintains a sliding window. Every time the window is changed, the current element is
	 * guaranteed to be the last element.
	 * 
	 * The starting point of this sliding window is uncertain, but the end point is the current element, so it is a
	 * local optimal solution. Therefore, it is necessary to compare the local optimal solution with the global optimal
	 * solution in each iteration to update the global optimal solution.
	 * 
	 * T - O(n) S - O(1)
	 */
	public int minimumSubarraySum(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int minimum = nums[0], current = nums[0];
		for (int i = 1; i < nums.length; i++) {
			current = Math.min(current + nums[i], nums[i]);
			minimum = Math.min(minimum, current);
		}
		return minimum;
	}

	public static void main(String[] args) {
		MinimumSubarraySum ms = new MinimumSubarraySum();
		System.out.println(ms.minimumSubarraySum(new int[] { 1, -1, -2, 1 }));
		System.out.println(ms.minimumSubarraySum(new int[] { 1, -1, -2, 1, 2, -4 }));
	}
}
