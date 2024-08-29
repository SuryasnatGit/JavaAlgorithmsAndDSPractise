package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array arr of unique nonnegative integers, implement a functiongetDifferentNumberthat finds the smallest
 * nonnegative integer that is NOT in the array.
 * 
 * Even if your programming language of choice doesn’t have that restriction (like Python), assume that the maximum
 * value an integer can have isMAX_INT = 2^31-1. So, for instance, the operationMAX_INT + 1would be undefined in our
 * case.
 * 
 * Your algorithm should be efficient, both from a time and a space complexity perspectives.
 * 
 * Solve first for the case when you’re NOT allowed to modify the input arr. If successful and still have time, see if
 * you can come up with an algorithm with an improved space complexity when modifying arr is allowed. Do so without
 * trading off the time complexity
 * 
 * Category : Hard
 */
public class SmallestNonNegativeIntegerNotPresentInArray {

	/**
	 * input array not modified.
	 * 
	 * T - O(n) S - O(n)
	 * 
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive1(int[] nums) {
		Map<Integer, Boolean> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], true);
		}
		for (int i = 1; i <= nums.length + 1; i++) {
			if (!map.containsKey(i)) {
				return i;
			}
		}
		return nums.length;
	}

	// T - O(n) S - O(1)
	// Ref : https://www.enjoyalgorithms.com/blog/first-missing-positive
	public int firstMissingPositive2(int[] nums) {
		int length = nums.length;
		int i = 0;

		while (i < length) {
			int j = nums[i] - 1;
			if (nums[i] > 0 && nums[i] <= length && nums[j] != nums[i]) {
				swap(nums, i, j);
			} else {
				i++;
			}
		}

		for (int j = 0; j < length; j++) {
			if (nums[j] != j + 1) {
				return j + 1;
			}
		}

		return length + 1;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		System.out.println("1st");
		SmallestNonNegativeIntegerNotPresentInArray pos = new SmallestNonNegativeIntegerNotPresentInArray();
		System.out.println(pos.firstMissingPositive1(new int[] { 0, 1, 2, 3 }));
		System.out.println(pos.firstMissingPositive1(new int[] { 3, 4, -1, 1 }));
		System.out.println(pos.firstMissingPositive1(new int[] { -1, -2, 100, 101 }));
		System.out.println(pos.firstMissingPositive1(new int[] { 1, 2, 0 }));
		System.out.println(pos.firstMissingPositive1(new int[] { 7, 8, 9, 11, 12 }));
		System.out.println(pos
				.firstMissingPositive1(new int[] { 2147483647, 2147483646, 2147483645, 3, 2, 1, -1, 0, -2147483648 }));
		System.out.println("2nd");
		System.out.println(pos.firstMissingPositive2(new int[] { 0, 1, 2, 3 }));
		System.out.println(pos.firstMissingPositive2(new int[] { 3, 4, -1, 1 }));
		System.out.println(pos.firstMissingPositive2(new int[] { -1, -2, 100, 101 }));
		System.out.println(pos.firstMissingPositive2(new int[] { 1, 2, 0 }));
		System.out.println(pos.firstMissingPositive2(new int[] { 7, 8, 9, 11, 12 }));
		System.out.println(pos
				.firstMissingPositive2(new int[] { 2147483647, 2147483646, 2147483645, 3, 2, 1, -1, 0, -2147483648 }));
	}
}
