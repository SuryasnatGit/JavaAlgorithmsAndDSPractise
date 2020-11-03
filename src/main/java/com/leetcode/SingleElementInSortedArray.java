package com.leetcode;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one
 * element which appears exactly once. Find this single element that appears only once.
 * 
 * Follow up: Your solution should run in O(log n) time and O(1) space.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,2,3,3,4,4,8,8] Output: 2 Example 2:
 * 
 * Input: nums = [3,3,7,7,10,11,11] Output: 10
 * 
 * Category : Medium
 */
public class SingleElementInSortedArray {

	public int singleNonDuplicate(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (mid + 1 < nums.length && mid - 1 >= 0) {// for edge cases
				if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
					return nums[mid];
				} else if (nums[mid] == nums[mid + 1] && mid % 2 == 0) {
					left = mid;
				} else if (nums[mid] == nums[mid - 1] && mid % 2 == 1) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				left++;
				right--;
			}
		}

		return nums[right];
	}

	public static void main(String[] args) {
		SingleElementInSortedArray sl = new SingleElementInSortedArray();
		System.out.println(sl.singleNonDuplicate(new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 }));
		System.out.println(sl.singleNonDuplicate(new int[] { 3, 3, 7, 7, 10, 11, 11 }));
		System.out.println(sl.singleNonDuplicate(new int[] { 1, 2, 2, 3, 3 }));
	}
}
