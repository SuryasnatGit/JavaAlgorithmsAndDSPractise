package com.algo.ds.array;

/**
 * https://leetcode.com/problems/first-missing-positive/.
 * 
 * Given an unsorted integer array, find the smallest missing positive integer. Your algorithm should run in O(n) time
 * and uses constant space.
 * 
 * 
 * Example 1: Input: [1,2,0] Output: 3 <br/>
 * Example 2:
 * 
 * Input: [3,4,-1,1] Output: 2 <br/>
 * Example 3:
 * 
 * Input: [7,8,9,11,12] Output: 1
 * 
 * We use array elements as index. To mark presence of an element x, we change the value at the index x to negative. But
 * this approach doesn’t work if there are non-positive (-ve and 0) numbers. So we segregate positive from negative
 * numbers as first step and then apply the approach.
 * 
 * Category : Hard
 * 
 */
public class FirstPositiveMissing {
	public int firstMissingPositive(int[] nums) {
		int positiveStartIndex = segregate(nums);

		// 1 is subtracted because index start at 0 and positive number start at 1
		for (int i = positiveStartIndex; i < nums.length; i++) {
			int index = Math.abs(nums[i]) + positiveStartIndex - 1;

			// check boundry conditions as well.
			if (index > Integer.MIN_VALUE && index < Integer.MAX_VALUE && Math.abs(index) < nums.length) {
				nums[index] = -Math.abs(nums[index]);
			}
		}

		// return first index value which is positive.
		for (int i = positiveStartIndex; i < nums.length; i++) {
			if (nums[i] > 0) {
				return i - positiveStartIndex + 1;
			}
		}
		return nums.length - positiveStartIndex + 1;
	}

	private int segregate(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		while (start <= end) {
			if (nums[start] <= 0) {
				start++;
			} else if (nums[end] > 0) {
				end--;
			} else {
				swap(nums, start, end);
			}
		}
		return start;
	}

	private void swap(int[] nums, int start, int end) {
		int t = nums[start];
		nums[start] = nums[end];
		nums[end] = t;
	}

	public static void main(String[] args) {
		FirstPositiveMissing pos = new FirstPositiveMissing();
		System.out.println(pos.firstMissingPositive(new int[] { 3, 4, -1, 1 }));
		System.out.println(pos.firstMissingPositive(new int[] { -1, -2, 100, 101 }));
		System.out.println(pos.firstMissingPositive(new int[] { 1, 2, 0 }));
		System.out.println(pos.firstMissingPositive(new int[] { 7, 8, 9, 11, 12 }));
		System.out.println(pos
				.firstMissingPositive(new int[] { 2147483647, 2147483646, 2147483645, 3, 2, 1, -1, 0, -2147483648 }));
	}
}
