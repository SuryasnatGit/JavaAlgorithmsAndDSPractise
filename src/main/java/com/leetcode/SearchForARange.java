package com.leetcode;

import java.util.Arrays;

/**
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * 
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * 
 * return [3, 4].
 * 
 * Category : Medium
 *
 */
public class SearchForARange {

	// T - O(log n)
	public int[] searchRange(int[] nums, int target) {

		int[] result = new int[] { -1, -1 };

		if (nums == null || nums.length == 0) {
			return result;
		}

		int left = 0, right = nums.length - 1;
		// binary search for left boundry
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (target <= nums[mid]) {
				right = mid;
			} else {
				left = mid;
			}
		}

		if (nums[left] == target) {
			result[0] = left;
		} else if (nums[right] == target) {
			result[0] = right;
		}

		// binary search for right boundry
		left = 0;
		right = nums.length - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (target >= nums[mid]) {
				left = mid;
			} else {
				right = mid;
			}
		}

		if (nums[right] == target) {
			result[1] = right;
		} else if (nums[left] == target) {
			result[1] = left;
		}

		return result;
	}

	// T - O(n)
	public int[] searchRange1(int[] nums, int target) {

		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;

		for (int left = 0; left < nums.length; left++) {
			if (nums[left] == target) {
				result[0] = left;
				break;
			}
		}
		for (int right = nums.length - 1; right >= 0; right--) {
			if (nums[right] == target) {
				result[1] = right;
				break;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		SearchForARange se = new SearchForARange();
		System.out.println(Arrays.toString(se.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8)));

		System.out.println(Arrays.toString(se.searchRange(new int[] { 5, 6, 7, 8, 9, 10 }, 9)));
		//
		System.out.println(Arrays.toString(se.searchRange(new int[] { 5, 6, 7, 8, 9, 10 }, 90)));

		System.out.println(Arrays.toString(se.searchRange(new int[] { 3, 3, 3 }, 3)));

		System.out.println(Arrays.toString(se.searchRange1(new int[] { 5, 7, 7, 8, 8, 10 }, 8)));

		System.out.println(Arrays.toString(se.searchRange1(new int[] { 5, 6, 7, 8, 9, 10 }, 9)));
		//
		System.out.println(Arrays.toString(se.searchRange1(new int[] { 5, 6, 7, 8, 9, 10 }, 90)));

		System.out.println(Arrays.toString(se.searchRange1(new int[] { 3, 3, 3 }, 3)));
	}
}
