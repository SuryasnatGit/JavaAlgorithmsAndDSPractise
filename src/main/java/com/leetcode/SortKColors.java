package com.leetcode;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are
 * adjacent, with the colors in the order red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this problem.
 * 
 * Example:
 * 
 * Input: [2,0,2,1,1,0]
 * 
 * Output: [0,0,1,1,2,2]
 * 
 * Follow up:
 * 
 * A rather straight forward solution is a two-pass algorithm using counting sort. First, iterate the array counting
 * number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's. Could you
 * come up with a one-pass algorithm using only constant space?
 *
 */
public class SortKColors {

	public void sortKColors(int[] nums, int k) {
		int left = 0;
		int right = nums.length - 1;
		int minColor = 1;
		int maxColor = k;

		while (minColor < maxColor) {
			for (int i = left; i <= right; i++) {
				while (nums[i] == maxColor && i < right) {
					swap(nums, i, right--);
				}

				if (nums[i] == minColor) {
					swap(nums, i, left++);
				}
			}

			minColor++;
			maxColor--;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String args[]) {
		SortKColors sc = new SortKColors();
		int[] nums = new int[] { 2, 3, 4, 1, 2, 3, 4, 5, 7, 2, 5, 2, 6, 2, 7, 5, 4, 2, 3, 4, 5 };
		sc.sortKColors(nums, 7);
		for (int num : nums) {
			System.out.print(num + " ");
		}
	}
}
