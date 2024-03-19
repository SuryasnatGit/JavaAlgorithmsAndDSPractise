package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
 * Return the difference between the sum of the two integers and the target.
 * 
 * Example: Given array nums = [-1, 2, 1, -4], and target = 4. The minimum difference is 1. (4 - (2 + 1) = 1).
 * 
 * Analysis : Similar to the 3 sum closest problem, by sorting the array first and then using two pointers, the time
 * complexity requirement of O(n log n) + O(n) ~ O(n log n) can be met The difference is that what is returned here is
 * diff, so just record minDiif.
 */
public class TwoSumClosest {

	public int findMinDiff(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return -1;
		}

		if (nums.length == 2) {
			return target - nums[0] - nums[1];
		}

		Arrays.sort(nums);

		int left = 0, right = nums.length - 1;
		int minDiff = Integer.MAX_VALUE;
		while (left < right) { // this is imp. should be < and not <=
			int sum = nums[left] + nums[right];
			int diff = Math.abs(sum - target);
			if (diff == 0) {
				return diff;
			}
			if (diff < minDiff) {
				minDiff = diff;
			}
			if (sum > target) {
				right--;
			} else {
				left++;
			}
		}
		return minDiff;
	}

	public static void main(String[] args) {
		TwoSumClosest c = new TwoSumClosest();
		System.out.println(c.findMinDiff(new int[] { -1, 2, 1, -4 }, 4));
	}
}
