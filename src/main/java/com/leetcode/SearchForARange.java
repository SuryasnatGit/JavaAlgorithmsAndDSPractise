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

	public int[] searchRange(int[] num, int target) {
		if (num == null || num.length == 0)
			return new int[] { -1, -1 };

		int left = binarySearchLeft(num, target);
		if (left == -1)
			return new int[] { -1, -1 };

		int right = binarySearchRight(num, target, left);

		return new int[] { left, right };
	}

	private int binarySearchLeft(int[] num, int target) {
		int left = 0, right = num.length - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (target <= num[mid]) {
				right = mid;
			} else {
				left = mid;
			}
		}

		if (target == num[left])
			return left;
		else if (target == num[right])
			return right;
		else
			return -1;
	}

	private int binarySearchRight(int[] num, int target, int start) {
		int left = start, right = num.length - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (target <= num[mid]) {
				right = mid;
			} else {
				left = mid;
			}
		}

		if (target == num[right])
			return right;
		else if (target == num[left])
			return left;
		else
			return -1;
	}

	public static void main(String[] args) {
		SearchForARange se = new SearchForARange();
		System.out.println(Arrays.toString(se.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8)));

		System.out.println(Arrays.toString(se.searchRange(new int[] { 5, 6, 7, 8, 9, 10 }, 9)));

		System.out.println(Arrays.toString(se.searchRange(new int[] { 5, 6, 7, 8, 9, 10 }, 90)));
	}
}
