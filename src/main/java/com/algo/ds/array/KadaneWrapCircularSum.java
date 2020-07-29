package com.algo.ds.array;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/maximum-contiguous-circular-sum/.
 * 
 * https://www.techiedelight.com/maximum-sum-circular-subarray/
 * 
 * Given n numbers (both +ve and -ve), arranged in a circle, fnd the maximum sum of consecutive number.
 * 
 * Examples:
 * 
 * Input: a[] = {8, -8, 9, -9, 10, -11, 12} Output: 22 (12 + 8 - 8 + 9 - 9 + 10)
 * 
 * Input: a[] = {10, -3, -4, 7, 6, 5, -4, -1} Output: 23 (7 + 6 + 5 - 4 -1 + 10)
 * 
 * Input: a[] = {-1, 40, -14, 7, 6, 5, -4, -1} Output: 52 (7 + 6 + 5 - 4 - 1 - 1 + 40)
 * 
 * Test cases:<br/>
 * All negative<br/>
 * All positives <br/>
 * all 0s
 */

public class KadaneWrapCircularSum {

	public int circularSum(int[] arr) {
		int arraySum = Arrays.stream(arr).sum();

		// kadane sum of original array
		int originalKadaneSum = kadaneSum(arr);

		// reverse the sign of array elements
		for (int i = 0; i < arr.length; i++) {
			arr[i] = -arr[i];
		}

		// kadane sum of negative array
		int negativeKadaneSum = kadaneSum(arr);

		return Integer.max(originalKadaneSum, negativeKadaneSum + arraySum);
	}

	private int kadaneSum(int[] arr) {
		int maxEndingHere = 0;
		int maxSoFar = 0;

		for (int i = 0; i < arr.length; i++) {
			maxEndingHere += arr[i];
			// if max ending here is negative then reset to 0
			maxEndingHere = Integer.max(maxEndingHere, 0);
			maxSoFar = Integer.max(maxSoFar, maxEndingHere);
		}

		return maxSoFar;
	}

	public static void main(String args[]) {
		KadaneWrapCircularSum kwa = new KadaneWrapCircularSum();
		System.out.println(kwa.circularSum(new int[] { 8, -8, 9, -9, 10, -11, 12 }));
		System.out.println(kwa.circularSum(new int[] { 10, -3, -4, 7, 6, 5, -4, -1 }));
		System.out.println(kwa.circularSum(new int[] { -1, 40, -14, 7, 6, 5, -4, -1 }));

	}
}
