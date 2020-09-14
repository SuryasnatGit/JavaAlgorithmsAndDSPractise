package com.leetcode;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example 1:
 * 
 * Input: [3,4,5,1,2] Output: 1 Example 2:
 * 
 * Input: [4,5,6,7,0,1,2] Output: 0
 * 
 * Category : Medium
 * 
 * Tags : BS
 *
 */
public class FindMinimumInSortedRotatedArray {

	// T - O(n)
	public int findMin(int[] nums) {
		int min = nums[0];
		for (int i = 1; i < nums.length; i++) {
			min = Math.min(min, nums[i]);
		}
		return min;
	}

	/**
	 * T - O(log n). Worst case complexity if duplicates present is O(n)
	 */
	public int findMin1(int[] nums) {
		int L = 0;
		int R = nums.length - 1;
		while (L < R && nums[L] >= nums[R]) {
			int M = (L + R) / 2;
			if (nums[M] > nums[R]) {
				L = M + 1;
			} else if (nums[M] < nums[R]) {
				R = M;
			} else { // if arr[L] == arr[M] == arr[R]. if duplicates are present.
				L = L + 1;
			}
		}
		return nums[L];
	}

	public static void main(String[] args) {
		FindMinimumInSortedRotatedArray min = new FindMinimumInSortedRotatedArray();
		System.out.println(min.findMin(new int[] { 7, 6, 3, 4, 5 }));
		System.out.println(min.findMin1(new int[] { 7, 6, 3, 4, 5 }));
	}
}
