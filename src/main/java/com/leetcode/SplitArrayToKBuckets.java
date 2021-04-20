package com.leetcode;

import java.util.Arrays;

/**
 * given a list of numbers, see if you can separate them in to k groups such that each group has the same sum. Give a
 * number k. Can you divide the numbers in the array into k buckets so that all the numbers in each bucket are the same.
 * 
 * DFS solution. Learn and use DFS backtracking to find various solutions
 *
 * New: Ask whether num array can be evenly placed in k buckets. This should be NP. It said that there may be negative
 * numbers in the middle, and there are two bugs
 */
public class SplitArrayToKBuckets {

	public static void main(String[] args) {
		SplitArrayToKBuckets s = new SplitArrayToKBuckets();
		int[] arr = { 1, 2, 4, 5, 3, 8, 9, 4, 1, 3 };
		int k = 5;
		System.out.println(s.separate(arr, k));
		System.out.println(s.separate(new int[] { 4, 3, 2, 3, 5, 2, 1 }, 4));
	}

	// Bruce
	// Time complexity: O((2^n)*k) n is nums.length
	// Space complexity: O(k) for keeping track of visited
	public boolean separate(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
			return false;
		}

		int sum = Arrays.stream(nums).sum();
		if (sum % k != 0) {
			return false;
		}

		int j = 1;
		boolean[] isUsed = new boolean[nums.length];

		while (j++ <= k) {
			if (!canSeparateAGroup(isUsed, nums, sum / k, 0)) {
				return false; // K groups, as long as one is not satisfied, it won’t work
			}
		}
		return true;
	}

	private boolean canSeparateAGroup(boolean[] isUsed, int[] nums, int target, int ith) {
		if (target == 0) { // Current group is satisfied
			return true;
		}

		if (ith == nums.length) { // Current group is not satisfied, and index goes over the range
			return false;
		}

		// Not using cur item for this group; skip current, go to next. Randomly pick elements, as long as it could form
		// target
		if (canSeparateAGroup(isUsed, nums, target, ith + 1)) {
			return true;
		}

		if (isUsed[ith]) { // If current item is used, don't pick again
			return false;
		}

		// include the cur element for current group
		isUsed[ith] = true;

		if (canSeparateAGroup(isUsed, nums, target - nums[ith], ith + 1)) {
			return true;
		}

		// back track
		isUsed[ith] = false;

		return false;
	}

}
