package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't
 * one, return 0 instead.
 * 
 * Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * 
 * Example 1: Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the
 * longest)
 * 
 * Example 2: Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * 
 * Follow Up: Can you do it in O(n) time?
 * 
 * Round2: longest subarray sum to target longest subarray has equal zeros and ones
 */
public class MaxSizeSubarray {
	public int maxSubArrayLenN2(int[] nums, int k) {
		int len = nums.length;
		int[] sum = new int[len + 1];

		for (int i = 1; i < len + 1; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}

		int res = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len + 1; j++) {
				if (sum[j] - sum[i] == k) {
					res = Math.max(res, j - i);
				}
			}
		}

		return res;
	}

	// longest subarray sum to target
	public static int maxSubArrayLenMyStyle(int[] nums, int target) {
		int max = 0;
		int len = nums.length;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		int[] sum = new int[len + 1];

		for (int i = 1; i < len + 1; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}

		for (int i = 0; i <= len; i++) {
			int now = sum[i];
			int toFind = target - now;

			if (map.containsKey(toFind)) {
				max = Math.max(max, i - map.get(toFind));
			}

			// If sum exists before, it must be a better one, so don't overwrite
			if (!map.containsKey(now)) {
				map.put(now, i);
			}
		}

		return max;
	}

	// Another one, my style
	public int maxSubArrayLenOneForLoop(int[] nums, int k) {
		int max = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (sum == k) {
				max = i + 1;
			} else {
				int toFind = sum - k;
				if (map.containsKey(toFind)) {
					max = Math.max(max, i - map.get(toFind));
				}
			}

			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}

		return max;
	}

	public static int maxSubArrayLen(int[] nums, int k) {
		int max = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (sum == k) {
				max = i + 1;
			} else if (map.containsKey(sum - k)) {
				max = Math.max(max, i - map.get(sum - k));
			}

			// If sum exists before, it must be a better one, so don't overwrite
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}

		return max;
	}

	// longest subarray has equal zeros and ones
	// Array contains only 0 and 1
	public static int maxSubArrayLen(int[] nums) {
		int max = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// map.put(0, -1);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				sum -= 1;
			} else if (nums[i] == 1) {
				sum += 1;
			}

			if (sum == 0) {
				max = i + 1;
			} else if (map.containsKey(-sum)) { // -sum还是sum?
				max = Math.max(max, i - map.get(-sum));
			}

			// If sum exists before, it must be a better one, so don't overwrite
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int[] nums = { 1, -1, 5, -2, 3 };
		int res = maxSubArrayLen(nums, 3);
		System.out.println(res);

		int res2 = maxSubArrayLenMyStyle(nums, 3);
		System.out.println(res2);
	}
}
