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
 */
public class SearchForARange {

	// T - O(log n)
	public int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return new int[] { -1, -1 };

		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {

			// This is responsible to handle when array has all dupes. Quiet simple.
			if (nums[left] == nums[right] && nums[left] == target) {
				return new int[] { left, right };
			}

			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				// When i find the target i expand the window from centre till i get the start and end of the repeated
				// numbers
				int start = mid;
				int end = mid;

				// STart should be greater than zero, its quite obvious right!!
				while (start >= 0 && nums[start] == target) {
					start--;
				}
				while (end < nums.length && nums[end] == target) {
					end++;
				}

				start++;
				end--;
				return new int[] { start, end };
			} else if (target < nums[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return new int[] { -1, -1 };
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
	}
}
