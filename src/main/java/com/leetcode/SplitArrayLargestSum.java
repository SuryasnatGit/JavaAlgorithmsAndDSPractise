package com.leetcode;

import java.util.Arrays;

/**
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m
 * non-empty continuous subarrays.
 * 
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * 
 * Example 1:
 * 
 * Input: nums = [7,2,5,10,8], m = 2
 * 
 * Output: 18
 * 
 * Explanation:
 * 
 * There are four ways to split nums into two subarrays. The best way is to split it into [7,2,5] and [10,8], where the
 * largest sum among the two subarrays is only 18.
 * 
 * Example 2:
 * 
 * Input: nums = [1,2,3,4,5], m = 2
 * 
 * Output: 9
 * 
 * Example 3:
 * 
 * Input: nums = [1,4,4], m = 3
 * 
 * Output: 4
 *
 * Category : Hard
 */
public class SplitArrayLargestSum {

	public static void main(String[] args) {
		SplitArrayLargestSum split = new SplitArrayLargestSum();
		System.out.println(Arrays.toString(split.kWindowSum(new int[] { 10, 30, 20, 50, 60, 40, 40 }, 2)));
		System.out.println(Arrays.toString(split.kWindowSumCleaner(new int[] { 10, 30, 20, 50, 60, 40, 40 }, 2)));
		System.out.println(split.splitArray(new int[] { 7, 2, 5, 10, 8 }, 2));
		System.out.println(split.splitArray(new int[] { 1, 2, 3, 4, 5 }, 2));
	}

	public int splitArray(int[] nums, int m) {
		int maxVal = 0;
		long sum = 0;

		for (int num : nums) {
			maxVal = Math.max(maxVal, num);
			sum += num;
		}

		long left = maxVal, right = sum;
		while (left + 1 < right) {
			long mid = left + (right - left) / 2;

			// The mid used here may not be a legal value, but this binary search will always go to the left and search
			// in a small direction until a boundary value is found, which is exactly the result
			if (isValid(mid, nums, m)) {
				right = mid;
			} else {
				left = mid;
			}
		}

		if (isValid(left, nums, m)) {
			return (int) left;
		} else {
			return (int) right;
		}
	}

	private boolean isValid(long val, int[] nums, int m) {
		int count = 1; // It is 1 by default, why?
		int total = 0;

		for (int num : nums) {
			total += num;

			if (total > val) {
				total = num;
				count++;

				if (count > m) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * K-window Max-Sum Find the subarray with the largest sum and length k in an array, and return sum. This question
	 * is too simple, it should not count, but let me write it. Given a num array, find a window of size k, that has a
	 * maximum sum of the k entries. follow-up: Find three non-overlap windows of size k, that together has a maximum
	 * sum of the 3k entries, time complexity O(n^2)
	 */
	int[] kWindowSum(int[] arr, int k) {
		int len = arr.length;
		if (arr == null || arr.length < k || k <= 0) {
			return null;
		}

		int[] res = new int[len - k + 1];

		for (int i = 0; i < k; i++) {
			res[0] += arr[i];
		}

		for (int i = k; i < len; i++) {
			res[i - k + 1] = res[i - k] - arr[i - k] + arr[i];
		}

		// If you want to get the largest sum, just sort the res array
		return res;
	}

	// Will this be cleaner, which is easier to understand
	int[] kWindowSumCleaner(int[] arr, int k) {
		int len = arr.length;
		if (arr == null || arr.length < k || k <= 0) {
			return null;
		}

		int[] res = new int[len - k + 1];

		int sum = 0;
		for (int i = 0; i < len; i++) {
			if (i - k >= 0) {
				sum -= arr[i - k];
			}
			sum += arr[i];
			if (i - k + 1 >= 0) {
				res[i - k + 1] = sum;
			}
		}

		return res;
	}

	/**
	 * The second question is to find three subarrays with the largest sum and each length of k. Three subarrays cannot
	 * have overlap. Give a chestnut 1,2,1,2,6,7,5,1. k = 2. The one found in this should be [1,2], 1,[2,6],[7,5],1 also
	 * return a sum. Lou Zhu stupidly wrote a N^3 solution. Kneeling firmly. When I went back to school, I asked the
	 * great god. The great god said dp. I also understand how dp is written, O(N).
	 *
	 * This can actually be optimized to O(n) time. Build the largest window array from the left end to each subscript,
	 * and then build the largest window array from the right end to each subscript. Go from left to right again for all
	 * the size k window, add it and the two arrays created in the previous step. Traverse it once and get the maximum
	 * value.
	 *
	 * It's a bit similar to buying and selling stocks 3, one left and one right, three windows are maintained here
	 *
	 * The array is divided into 3 consecutive small arrays, the goal is to maximize the sum of the small arrays
	 */
	public static int find(int[] arr, int k) {
		int res = Integer.MIN_VALUE;
		int len = arr.length;

		int leftSum = 0;
		int[] left = new int[len];
		for (int i = 0; i < len; i++) {
			leftSum += arr[i];
			if (i >= k) {
				leftSum -= arr[i - k];
			}
			if (i - k + 1 >= 0) {
				left[i] = Math.max(leftSum, i >= 1 ? left[i - 1] : 0); // left的下标从0开始
			}
		}

		int rightSum = 0;
		int[] right = new int[len];
		for (int i = len - 1; i >= 0; i--) {
			rightSum += arr[i];
			if (i + k < len) {
				rightSum -= arr[i + k];
			}
			if (i + k - len <= 0) {
				right[i] = Math.max(rightSum, i < len - 1 ? right[i + 1] : 0);
			}
		}

		int midSum = 0;
		for (int i = k; i <= len - k - 1; i++) {
			midSum += arr[i];
			if (i - k >= k) {
				midSum -= arr[i - k];
			}

			if (i >= k + k - 1) {
				res = Math.max(res, midSum + left[i - k] + right[i + 1]); // 注意下标again
			}
		}

		return res;
	}
}
