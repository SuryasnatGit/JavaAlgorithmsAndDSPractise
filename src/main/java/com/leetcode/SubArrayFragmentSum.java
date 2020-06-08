package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * find the number of fragments of an array that sum is equals to 0 (that is, pairs(P,Q) such that P'<"Q and the sum
 * A[P]+A[P+1]...+A[Q] equals to 0).
 *
 */
public class SubArrayFragmentSum {

	public int sum(int[] a) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();

		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (a[i] == 0)
				count++;
			if (map.containsKey(-a[i])) {
				count++;
			} else {
				map.put(sum, sum);
			}

		}

		if (sum == 0)
			count++;

		return count;
	}

	public int[] zeroSumSubarray(int[] arr) {
		Map<Integer, Integer> sums = new HashMap<Integer, Integer>();

		int sum = 0;
		for (int i = 0; i <= arr.length; i++) {
			Integer oldIndex = sums.get(sum);
			if (oldIndex == null && i == arr.length) {
				return null;
			} else if (oldIndex == null) {
				sums.put(sum, i);
				sum += arr[i];
			} else {
				return Arrays.copyOfRange(arr, oldIndex, i);
			}
		}

		return null;
	}

	public static void main(String[] args) {
		SubArrayFragmentSum sf = new SubArrayFragmentSum();
		System.out.println(sf.sum(new int[] { 2, -2, 3, 0, 4, -7 }));
		System.out.println(Arrays.toString(sf.zeroSumSubarray(new int[] { 1, 2, -5, 1, 2, -1 })));
	}
}
