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
 * Tags : BinarySearch
 *
 */
public class FindMinimumInSortedRotatedArray {

	/**
	 * T - O(n)
	 * 
	 * @param nums
	 * @return
	 */
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
			int M = L + (R - L) / 2;
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

	/*
	 * Regardless of Template #1, #2, #3, the essence is to find a non-monotonic increasing interval, which contains the
	 * minimum value.
	 * 
	 * Inflection Point:
	 * 
	 * All the elements to the left of inflection point > first element of the array.
	 * 
	 * All the elements to the right of inflection point < first element of the array.
	 * 
	 */
	public int findMin2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		int left = 0, right = nums.length - 1;

		// if last element is greater than first element then there is no rotation
		if (nums[right] > nums[0]) {
			return nums[0];
		}

		while (left <= right) {
			int mid = left + (right - left) / 2;

			// when final inflection point is reached
			// right of inflection point
			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			}
			// left of inflection point
			if (nums[mid - 1] > nums[mid]) {
				return nums[mid];
			}

			if (nums[mid] > nums[0]) {
				// search in right side
				left = mid + 1;
			} else {
				// search in left side
				right = mid - 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		FindMinimumInSortedRotatedArray min = new FindMinimumInSortedRotatedArray();
		System.out.println(min.findMin(new int[] { 6, 7, 3, 4, 5 }));
		System.out.println(min.findMin1(new int[] { 6, 7, 3, 4, 5 }));
		System.out.println(min.findMin1(new int[] { 1, 2, 3, 4, 5 }));
		System.out.println(min.findMin1(new int[] { 9, 8, 7, 6, 5 }));
		System.out.println(min.findMin2(new int[] { 6, 7, 3, 4, 5 }));
		System.out.println(min.findMin2(new int[] { 6, 7, 3, 3, 3, 4, 5 }));
		System.out.println(min.findMin2(new int[] { 6, 7, 7, 7, 3, 4, 5 }));
	}
}
