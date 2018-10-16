package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form
 * f(x) = ax2 +bx + c to each element x in the array. The returned array must be in sorted order.
 * Expected time complexity: O(n) .
 * 
 * Example: nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 * 
 * Result: [3, 9, 15, 33]
 * 
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 * 
 * Result: [-23, -5, 1, 7]
 *
 * https://leetcode.com/problems/sort-transformed-array/
 */
public class SortedArrayTransformation {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int start = 0;
		int end = nums.length - 1;
		int[] result = new int[nums.length];
		int index = (a >= 0 ? nums.length - 1 : 0);
		while (start <= end) {
			int x = apply(nums[start], a, b, c);
			int y = apply(nums[end], a, b, c);
			boolean condition = (a >= 0 ? x >= y : x <= y);
			if (condition) {
				result[index] = x;
				start++;
			} else {
				result[index] = y;
				end--;
			}
			index = index + (a >= 0 ? -1 : 1);
		}
		return result;
	}

	/**
	 * We know that the transformation function forms a parabola, which has a minimum/maximum in the
	 * middle, if a != 0, or a line, if a == 0. So we can start from two ends, for a > 0, fill the
	 * result array from end to start, for a < 0, fill the result array from start to end.
	 * 
	 * @param nums
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public int[] sortTransformedArray1(int[] nums, int a, int b, int c) {
		int len = nums.length - 1;
		int pos1 = 0;
		int pos2 = len - 1;
		int start = 0;
		int end = len - 1;
		int[] res = new int[len];
		while (start <= end) {
			int first = apply(nums[start], a, b, c);
			int second = apply(nums[end], a, b, c);
			if (a >= 0) { // fill result array from end to start
				if (first > second) {
					res[pos2--] = first;
					start++;
				} else {
					res[pos2--] = second;
					end--;
				}
			} else { // fill result array from start to end
				if (first < second) {
					res[pos1++] = first;
					start++;
				} else {
					res[pos1++] = second;
					end--;
				}
			}
		}
		return res;
	}

	private int apply(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}

	public static void main(String[] args) {
		SortedArrayTransformation tr = new SortedArrayTransformation();
		int[] nums = new int[] { -4, -2, 2, 4 };
		int[] ar = tr.sortTransformedArray1(nums, 1, 3, 5);
		int[] ar1 = tr.sortTransformedArray(nums, 1, 3, 5);
		System.out.println(Arrays.toString(ar));
		System.out.println(Arrays.toString(ar1));
	}
}
