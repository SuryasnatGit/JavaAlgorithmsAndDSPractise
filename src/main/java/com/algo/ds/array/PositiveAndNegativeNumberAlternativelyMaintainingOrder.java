package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array of positive and negative integers arrange them alternatively maintaining initial
 * order. If there are more +ve or -ve integer then push them to the end together.
 *
 * Time complexity is O(n) Space complexity is O(1)
 *
 * http://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-items-o1-extra-space/
 * 
 * Examples :
 * 
 * Input: arr[] = {1, 2, 3, -4, -1, 4} Output: arr[] = {-4, 1, -1, 2, 3, 4}
 * 
 * Input: arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8} output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8,
 * 0}
 */
public class PositiveAndNegativeNumberAlternativelyMaintainingOrder {

	/**
	 * The idea is to process array from left to right.
	 * 
	 * 1. While processing, find the first out of place element in the remaining unprocessed array. An
	 * element is out of place if it is negative and at odd index, or it is positive and at even index.
	 * 
	 * 2. Once we find an out of place element, we find the first element after it with opposite sign.
	 * 
	 * 3. We right rotate the subarray between these two elements (including these two).
	 * 
	 * @param input
	 */
	public void rearrange(int input[]) {

		for (int i = 0; i < input.length; i++) {
			if (i % 2 == 0 && input[i] >= 0) { // even index and positive is out of place
				int indexOfNextNegative = findNext(input, i + 1, false);
				if (indexOfNextNegative == -1) {
					return;
				} else {
					rightRotate(input, i, indexOfNextNegative);
				}
			} else if (i % 2 != 0 && input[i] < 0) { // odd index and negative is out of place
				int indexOfNextPositive = findNext(input, i + 1, true);
				if (indexOfNextPositive == -1) {
					return;
				} else {
					rightRotate(input, i, indexOfNextPositive);
				}
			}
		}
	}

	private int findNext(int input[], int start, boolean isPositive) {
		for (int i = start; i < input.length; i++) {
			if ((isPositive && input[i] >= 0) || (!isPositive && input[i] < 0)) {
				return i;
			}
		}
		return -1;
	}

	private void rightRotate(int input[], int start, int end) {
		int t = input[end];
		for (int i = end; i > start; i--) {
			input[i] = input[i - 1];
		}
		input[start] = t;
	}

	public static void main(String args[]) {
		int input[] = { -5, -2, 5, 2, 4, 7, 1, 8, 0, -8 };
		PositiveAndNegativeNumberAlternativelyMaintainingOrder pss = new PositiveAndNegativeNumberAlternativelyMaintainingOrder();
		pss.rearrange(input);
		Arrays.stream(input).forEach(i -> System.out.print(i + " "));
	}
}
