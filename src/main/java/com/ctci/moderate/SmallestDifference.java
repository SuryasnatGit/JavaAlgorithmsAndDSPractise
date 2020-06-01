package com.ctci.moderate;

import java.util.Arrays;

/**
 * Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative)
 * difference. Return the difference.
 * 
 * EXAMPLE
 * 
 * Input {1,3,5,11,2} and {23,127,235,19,8}
 * 
 * Output 3. That is, the pair (11, 8).
 * 
 * This algorithm takes O(A log A + B log B) time to sort and O(A + B) time to find the minimum difference. Therefore,
 * the overall runtime is 0 (A log A + B log B).
 *
 * CTCI: 16.6
 */
public class SmallestDifference {

	public int smallestDiff(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);

		int diff = Integer.MAX_VALUE;
		int i = 0, j = 0;
		int[] res = new int[2];

		while (i < a1.length && j < a2.length) {
			if (Math.abs(a1[i] - a2[j]) < diff) {
				diff = Math.abs(a1[i] - a2[j]);
				res[0] = a1[i];
				res[1] = a2[j];
			}

			// move the smaller one
			if (a1[i] < a2[j]) {
				i++;
			} else {
				j++;
			}
		}

		System.out.println(Arrays.toString(res));
		return diff;
	}

	public static void main(String[] args) {
		SmallestDifference sd = new SmallestDifference();
		System.out.println(sd.smallestDiff(new int[] { 1, 3, 5, 11, 2 }, new int[] { 23, 127, 235, 19, 8 }));
	}
}
