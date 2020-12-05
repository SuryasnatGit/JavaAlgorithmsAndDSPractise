package com.algo.ds.array;

import java.util.Arrays;

/**
 * https://www.techiedelight.com/smallest-window-sorting-which-make-array-sorted/
 *
 * Given an unsorted array arr[0..n-1] of size n, find the minimum length subarray arr[s..e] such that sorting this
 * subarray makes the whole array sorted.
 * 
 * Examples: 1) If the input array is [10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60], your program should be able to find
 * that the subarray lies between the indexes 3 and 8.
 * 
 * 2) If the input array is [0, 1, 15, 25, 6, 7, 30, 40, 50], your program should be able to find that the subarray lies
 * between the indexes 2 and 5.
 * 
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 * 
 * Category : Medium
 */
public class MinimumSortedWhichSortsEntireArray {

	// T - O(n log n) S - O(n)
	public int findUnsortedSubarray(int[] nums) {
		int[] temp = nums.clone();
		Arrays.sort(temp);

		int min = nums.length;
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != temp[i]) {
				min = Math.min(min, i);
				max = Math.max(max, i);
			}
		}

		return max - min >= 0 ? max - min + 1 : 0;
	}

	// T - O(n) S - O(1)
	public int findUnsortedSubarray1(int nums[]) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		boolean flag = false;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[i - 1]) {
				flag = true;
			}
			if (flag) {
				min = Math.min(min, nums[i]);
			}
		}

		// reset flag
		flag = false;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] > nums[i + 1]) {
				flag = true;
			}
			if (flag) {
				max = Math.max(max, nums[i]);
			}
		}

		int left, right;
		for (left = 0; left < nums.length; left++) {
			if (min < nums[left]) {
				break;
			}
		}

		for (right = nums.length - 1; right >= 0; right--) {
			if (max > nums[right]) {
				break;
			}
		}

		return (right - left >= 0) ? right - left + 1 : 0;
	}

	public static void main(String args[]) {

		MinimumSortedWhichSortsEntireArray msw = new MinimumSortedWhichSortsEntireArray();

		System.out.println(msw.findUnsortedSubarray(new int[] { 4, 5, 6, 12, 11, 15 }));
		System.out.println(msw.findUnsortedSubarray(new int[] { 1, 3, 7, 2, 5, 6, 4, 8 }));
		System.out.println(msw.findUnsortedSubarray(new int[] { 4, 5, 10, 21, 18, 23, 7, 8, 19, 34, 38 }));
		System.out.println(msw.findUnsortedSubarray(new int[] { 1, 2, 3, 3, 3 }));

		System.out.println(msw.findUnsortedSubarray1(new int[] { 4, 5, 6, 12, 11, 15 }));
		System.out.println(msw.findUnsortedSubarray1(new int[] { 1, 3, 7, 2, 5, 6, 4, 8 }));
		System.out.println(msw.findUnsortedSubarray1(new int[] { 4, 5, 10, 21, 18, 23, 7, 8, 19, 34, 38 }));
		System.out.println(msw.findUnsortedSubarray1(new int[] { 1, 2, 3, 3, 3 }));
	}

}
