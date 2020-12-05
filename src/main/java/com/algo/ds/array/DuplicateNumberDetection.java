package com.algo.ds.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Given an array of size n + 1 with elements from 1 to n. One element is duplicated multiple times. Find that element
 * in O(1) space. Array cannot be changed.
 * 
 * Note: You must not modify the array (assume the array is read only). You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n ^ 2). There is only one duplicate number in the array, but it could
 * be repeated more than once.
 *
 * CTCI - Find duplicates
 * 
 * Reference https://leetcode.com/problems/find-the-duplicate-number/
 * 
 * Category : Medium
 */
public class DuplicateNumberDetection {

	/**
	 * Solution 1 - time - O(n log n). using binary search after sorting the array
	 * 
	 * @param nums
	 * @return
	 */
	public int findDuplicate1(int[] nums) {
		// base
		if (nums.length == 0 || nums.length == 1) {
			return -1;
		}

		Arrays.sort(nums);

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

	/**
	 * Solution 2 - Using auxillary boolean array. time - O(n) space - O(n)
	 * 
	 * @param A
	 * @return
	 */
	public int findDuplicate2(int[] nums) {
		Set<Integer> set = new HashSet<>();

		int result = -1;
		for (int num : nums) {
			if (set.contains(num)) {
				result = num;
				break;
			}
			set.add(num);
		}

		return result;
	}

	/**
	 * Solution 3 - Floy's algorithm. turtle and hare cycle detection algo. T - O(n) S - O(1)
	 */
	public int findDuplicate3(int[] nums) {
		// find intersection point of 2 runners
		int tortoise = nums[0];
		int hare = nums[0];

		do {
			tortoise = nums[tortoise]; // move slow by 1 step
			hare = nums[nums[hare]]; // move fast by 2 steps
		} while (tortoise != hare);

		// find entry point of cycle
		tortoise = nums[0];
		while (tortoise != hare) {
			tortoise = nums[tortoise]; // move slow by 1 step
			hare = nums[hare]; // move slow by 1 step
		}

		return hare;
	}

	public static void main(String args[]) {

		DuplicateNumberDetection dd = new DuplicateNumberDetection();
		System.out.println(dd.findDuplicate1(new int[] { 1, 2, 3, 4, 5, 2 }));
		System.out.println(dd.findDuplicate2(new int[] { 1, 2, 3, 4, 5, 2 }));
		System.out.println(dd.findDuplicate3(new int[] { 1, 2, 3, 4, 5, 2 }));

		System.out.println(dd.findDuplicate1(new int[] { 2, 2, 2, 2, 2 }));
		System.out.println(dd.findDuplicate2(new int[] { 2, 2, 2, 2, 2 }));
		System.out.println(dd.findDuplicate3(new int[] { 2, 2, 2, 2, 2 }));
	}
}
