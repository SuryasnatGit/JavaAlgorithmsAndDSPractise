package com.companyprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a multiset (set that allows for multiple instances of same value), partition it into two multisets A and B such
 * that the sum of A is greater than that of B. Return A. If more than one such As exists, return the one with minimal
 * size.
 * 
 * Examples Example 1: Input: nums = [4, 5, 2, 3, 1, 2]
 * 
 * Output: [4, 5]
 * 
 * Explanation: We can divide the numbers into two subsets A = [4, 5] and B = [1, 2, 2, 3]. The sum of A is 9 which is
 * greater than the sum of B which is 8. There are other ways to divide but A = [4, 5] is of minimal size of 2.
 * 
 * @author surya
 *
 */
public class OptimizingBoxWeights {

	public List<Integer> minimalHeaviestSetA(List<Integer> arr) {
		// sort the array
		// Collections.sort(arr, (a,b) -> b-a);
		Collections.sort(arr);

		int sum = 0;
		for (int weight : arr) {
			sum += weight;
		}

		List<Integer> result = new ArrayList<>();

		int sumOfA = 0;
		for (int i = arr.size() - 1; i >= 0; i--) {
			sumOfA += arr.get(i);
			result.add(arr.get(i));

			if (sumOfA > sum / 2) {
				break;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		OptimizingBoxWeights obw = new OptimizingBoxWeights();
		System.out.println(obw.minimalHeaviestSetA(Arrays.asList(3, 7, 5, 6, 2)));
	}
}
