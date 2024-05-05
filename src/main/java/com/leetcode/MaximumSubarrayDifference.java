package com.leetcode;

/**
 * Given an array with integers.
 * 
 * Find twonon-overlapping_subarrays_A_and_B, which|SUM(A) - SUM(B)|is the largest.
 * 
 * Return the largest difference.
 * 
 * The subarray should contain at least one number
 * 
 * Example
 * 
 * For[1, 2, -3, 1], return 6.
 * 
 * Challenge
 * 
 * O(n) time and O(n) space.
 * 
 * Analysis
 * 
 * Like the Maximum Subarray series of problems, here we only use the transformation problem, that is, to find max
 * |SUM(A) - SUM(B)|, which is equivalent to
 * 
 * Math.max(ans, Math.max(Math.abs(leftMax(A) - rightMin(B)), Math.abs(leftMin(A) - rightMax(B))))
 * 
 * That is to say, it is basically the same as the previous idea of ​​finding maximum non-overlap subarrays, except that
 * the required arrays become four: leftMax, leftMin, rightMaxandrightMin
 * 
 * Although the final code is very long, the principle is very simple. There are 5 passes in total, and 4 passes are
 * used to generate leftMax, leftMin, rightMaxandrightMin
 * 
 * The last pass is used to find the final answer. Therefore, the time complexity is O(n) and the space complexity is
 * O(n).
 * 
 * Category : Hard
 * 
 * TODO : understand properly
 */
public class MaximumSubarrayDifference {

	public int maxDiffSubArrays(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int n = nums.length;
		int[] leftMax = new int[n];
		int[] leftMin = new int[n];
		int[] rightMax = new int[n];
		int[] rightMin = new int[n];

		leftMax[0] = nums[0];
		leftMin[0] = nums[0];
		rightMax[n - 1] = nums[n - 1];
		rightMin[n - 1] = nums[n - 1];

		int localMax = leftMax[0];
		int globalMax = leftMax[0];
		for (int i = 1; i < n; i++) {
			localMax = Math.max(localMax + nums[i], nums[i]);
			globalMax = Math.max(localMax, globalMax);
			leftMax[i] = globalMax;
		}

		int localMin = leftMin[0];
		int globalMin = leftMin[0];
		for (int i = 1; i < n; i++) {
			localMin = Math.min(localMin + nums[i], nums[i]);
			globalMin = Math.min(localMin, globalMin);
			leftMin[i] = globalMin;
		}

		localMax = rightMax[n - 1];
		globalMax = rightMax[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			localMax = Math.max(localMax + nums[i], nums[i]);
			globalMax = Math.max(localMax, globalMax);
			rightMax[i] = globalMax;
		}

		localMin = rightMin[n - 1];
		globalMin = rightMin[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			localMin = Math.min(localMin + nums[i], nums[i]);
			globalMin = Math.min(localMin, globalMin);
			rightMin[i] = globalMin;
		}

		int result = Integer.MIN_VALUE;
		for (int i = 0; i < n - 1; i++) {
			int temp = Math.max(Math.abs(leftMax[i] - rightMin[i + 1]), Math.abs(leftMin[i] - rightMax[i + 1]));
			result = Math.max(temp, result);
		}

		return result;
	}
}
