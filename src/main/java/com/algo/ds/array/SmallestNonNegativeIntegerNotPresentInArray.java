package com.algo.ds.array;

import java.util.HashSet;
import java.util.Set;

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
		int length = nums.length;
		Set<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			set.add(num);
		}
		// since first positive is 0
		for (int i = 0; i < length; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}
		return length;
	}

	// T - O(n) S - O(1)
	// TODO : not working. need to check.
	public int firstMissingPositive2(int[] nums) {
		int length = nums.length;
		int temp = 0;
		for (int i = 0; i < length; i++) {
			temp = nums[i];
			while (temp > 0 && temp < length && nums[temp] != temp) {
				int t = nums[temp];
				nums[temp] = temp;
				temp = t;
			}
		}

		for (int i = 0; i < length; i++) {
			if (nums[i] != i) {
				return i;
			}
		}

		return length;
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
