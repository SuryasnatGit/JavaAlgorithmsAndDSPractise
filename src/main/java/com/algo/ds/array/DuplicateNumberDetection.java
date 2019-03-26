package com.algo.ds.array;

/**
 * 
 *
 * Given an array of size n + 1 with elements from 1 to n. One element is
 * duplicated multiple times. Find that element in O(1) space. Array cannot be
 * changed.
 * 
 * Note: You must not modify the array (assume the array is read only). You must
 * use only constant, O(1) extra space. Your runtime complexity should be less
 * than O(n2). There is only one duplicate number in the array, but it could be
 * repeated more than once
 *
 * 
 * Reference https://leetcode.com/problems/find-the-duplicate-number/
 */
public class DuplicateNumberDetection {

	/**
	 * time - O(n log n)
	 * 
	 * @param nums
	 * @return
	 */
	public int duplicateNumber(int[] nums) {
		// base
		if (nums.length == 0 || nums.length == 1) {
			return -1;
		}

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			// do binary search for num in rest of array
			int left = i + 1;
			int right = nums.length - 1;
			while (left <= right) {
				int mid = left + (right - left) / 2;
				if (nums[mid] == num) {
					return num;
				} else if (nums[mid] < num) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}

	public static void main(String args[]) {
		int[] input = { 2, 9, 5, 4, 3, 5 };
		DuplicateNumberDetection dd = new DuplicateNumberDetection();
		System.out.println(dd.duplicateNumber(input));
	}
}
