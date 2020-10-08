package com.algo.ds.array;

public class BalancedArray {

	/**
	 * An array is balanced if the sum of the left half of the array elements is equal to the sum of right half.
	 * 
	 */
	public int findBalancedIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}

		int len = arr.length;
		int[] leftSum = new int[len];
		int[] rightSum = new int[len];

		leftSum[0] = arr[0];
		for (int i = 1; i < len; i++) {
			leftSum[i] = leftSum[i - 1] + arr[i];
		}

		rightSum[len - 1] = 0;
		for (int i = len - 2; i >= 0; i--) {
			rightSum[i] = rightSum[i + 1] + arr[i + 1];
		}

		for (int i = 0; i < len; i++) {
			int left = leftSum[i];
			int right = rightSum[i];
			if (left == right) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		BalancedArray ba = new BalancedArray();
		System.out.println(ba.findBalancedIndex(new int[] { 1, 2, 1, 3, 1 }));
		System.out.println(ba.findBalancedIndex(new int[] { 17, 3, 5, 0, 4, 2, 3 }));
	}
}
