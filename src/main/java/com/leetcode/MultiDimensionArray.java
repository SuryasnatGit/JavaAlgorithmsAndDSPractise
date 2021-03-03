package com.leetcode;

import java.util.Arrays;

/**
 * // Given a multi-dimensional arrays, compute the sum of all values. // Given API getValue(dn, dn-1.... d0) dn = index
 * at dimension.
 *
 */
public class MultiDimensionArray {

	// provided function
	public static int getValue(int... indexOfDimension) {
		int value = 1; // e.g.
		System.out.println(Arrays.toString(indexOfDimension));
		return value;
	}

	// lengthOfDeminsion: each dimension's length, assume it is valid: lengthOfDeminsion[i]>0.
	public static Integer sum(MultiDimensionArray mArray, int[] lengthOfDeminsion) {
		if (lengthOfDeminsion == null || lengthOfDeminsion.length == 0) {
			return null;
		}
		// O(N)solution. only iterator, no recursion, no extra space
		final int dims = lengthOfDeminsion.length;

		for (int i = 0; i < dims; i++) {
			lengthOfDeminsion[i]--;
		}
		int[] cur = new int[dims], max = lengthOfDeminsion;

		int sum = 0;
		int rightIdx;

		while (true) {
			sum += mArray.getValue(cur);
			if (Arrays.equals(cur, max))
				break;

			rightIdx = dims - 1;
			cur[rightIdx]++;

			while (cur[rightIdx] > max[rightIdx]) {
				cur[rightIdx] = 0;
				rightIdx--;
				cur[rightIdx]++;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(sum(new MultiDimensionArray(), new int[] { 3, 4, 2 }));
	}
}
