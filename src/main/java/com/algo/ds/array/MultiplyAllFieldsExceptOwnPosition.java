package com.algo.ds.array;

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

	public int[] multiply(int nums[]) {
		if (nums.length == 0) {
			return new int[0];
		}
		int[] output = new int[nums.length];
		int product = nums[0];
		for (int i = 1; i < nums.length; i++) {
			output[i] = product;
			product *= nums[i];
		}

		output[0] = 1;
		product = nums[nums.length - 1];
		for (int i = nums.length - 2; i >= 0; i--) {
			output[i] *= product;
			product *= nums[i];
		}
		return output;
	}
}
