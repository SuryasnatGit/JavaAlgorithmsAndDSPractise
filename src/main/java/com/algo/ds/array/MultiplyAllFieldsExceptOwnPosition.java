package com.algo.ds.array;

import java.util.Arrays;

/**
 * http://www.careercup.com/question?id=5179916190482432.
 * 
 * input [2,3,1,4] output [12,8,24,6]
 * 
 * Multiply all fields except it's own position.
 * 
 * Restrictions: 1. no use of division 2. complexity in O(n)
 * 
 * Look out for division by 0
 */
public class MultiplyAllFieldsExceptOwnPosition {

	// T - O(n) S - O(n)
	public int[] multiply(int nums[]) {
		int l = nums.length;
		if (l == 0) {
			return new int[0];
		}

		int[] left = new int[l];
		int[] right = new int[l];
		left[0] = 1;
		// go from left to right
		for (int i = 1; i < l; i++) {
			left[i] = left[i - 1] * nums[i - 1];
		}

		right[l - 1] = 1;
		// go from right to left
		for (int i = l - 2; i >= 0; i--) {
			right[i] = right[i + 1] * nums[i + 1];
		}

		for (int i = 0; i < l; i++)
			nums[i] = left[i] * right[i];

		return nums;
	}

	public static void main(String[] args) {
		MultiplyAllFieldsExceptOwnPosition m = new MultiplyAllFieldsExceptOwnPosition();
		System.out.println(Arrays.toString(m.multiply(new int[] { 2, 3, 1, 4 })));
		System.out.println(Arrays.toString(m.multiply(new int[] { 1, 2, 3, 4, 5 })));
	}
}
