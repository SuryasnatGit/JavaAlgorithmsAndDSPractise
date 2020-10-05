package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given 3 unsorted arrays A, B and C you need to find all possible combinations such that A[i] + B[j] = B[k] + C[l].
 *
 */
public class ArraySumEqualityFinder {

	public static void main(String[] args) {
		ArraySumEqualityFinder sum = new ArraySumEqualityFinder();
		System.out.println(sum.arraySum(new int[] { 1, 2 }, new int[] { 1, 1 }, new int[] { 2, 1 }));
	}

	public int arraySum(int[] a, int[] b, int[] c) {
		Map<Integer, Integer> abSumMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> bcSumMap = new HashMap<Integer, Integer>();

		for (int numA : a) {
			for (int numB : b) {
				int sum = numA + numB;
				int count = 0;
				if (abSumMap.containsKey(sum)) {
					count = abSumMap.get(sum) + 1;
				}
				abSumMap.put(sum, count);
			}
		}

		for (int numB : b) {
			for (int numC : c) {
				int sum = numB + numC;
				int count = 0;
				if (bcSumMap.containsKey(sum)) {
					count = bcSumMap.get(sum) + 1;
				}
				bcSumMap.put(sum, count);
			}
		}

		int result = 0;
		for (Map.Entry<Integer, Integer> abEntry : abSumMap.entrySet()) {
			if (bcSumMap.containsKey(abEntry.getKey())) {
				result += abEntry.getValue() * bcSumMap.get(abEntry.getKey());
			}
		}

		return result;
	}
}
