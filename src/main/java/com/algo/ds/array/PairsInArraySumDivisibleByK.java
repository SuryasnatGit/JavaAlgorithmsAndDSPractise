package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array, write a function that checks if all the numbers in
 * that array form pairs divisible by k
 * 
 * Example: int arr[] = {5, 7, 95, 1, 193, 99, ....} int k = 100
 * 
 * 5+95 = 100 7+193 = 200 1+99 = 100
 * 
 * @author M_402201
 *
 */
public class PairsInArraySumDivisibleByK {

	/**
	 * time complexity - O(n^2 + n) = O(n^2) space complexity - O(n)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public boolean checkSumPairs_simple(int[] arr, int k) {
		int length = arr.length;
		if (length % 2 != 0) // if array length is odd
			return false;

		boolean[] visited = new boolean[length]; // O(n) space
		for (int i = 0; i < length; i++) {
			for (int j = 1 + 1; j < length; j++) {
				if (!visited[i] && !visited[j]) {
					if ((arr[i] + arr[j]) % k == 0) {
						visited[i] = true;
						visited[j] = true;
					}
				}
			}
		}

		for (boolean check : visited) {
			if (!check) // if something is not visited, then still some unvisited num exist which cannot
						// form a pair.
				return false;
		}
		return true;
	}

	/**
	 * time complexity - O(n) space complexity - O(n)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public boolean checkSumPairs_hashing(int[] arr, int k) {
		int length = arr.length;
		if (length % 2 != 0)
			return false;

		// Preprocessing - contains frequency of occurrence of remainder.
		Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
		for (int num : arr) {
			int rem = num % k;
			if (!frequency.containsKey(rem))
				frequency.put(rem, 0);

			frequency.put(rem, frequency.get(rem) + 1);
		}

		for (int num : arr) {
			int rem = num % k;
			// if k is twice the remainder of current number
			if (k == (2 * rem)) {
				// there will be even occurances of rem
				if (frequency.get(rem) % 2 != 0)
					return false;
			}
			// if rem is 0. there must be 2 elements with 0 remainder
			if (rem == 0) {
				// there will be even occurance of rem
				if (frequency.get(rem) % 2 != 0)
					return false;
			}
			// else frequency of occurance of rem should match frequency of occurance of (k
			// - rem)
			if (frequency.get(rem) != frequency.get(k - rem))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		PairsInArraySumDivisibleByK p = new PairsInArraySumDivisibleByK();
		// TC1 - valid. Even number of elements forming pairs divisible by k
		System.out.println(p.checkSumPairs_simple(new int[] { 5, 7, 95, 1, 193, 99 }, 100));
		// TC2 - invalid. odd num of elements
		System.out.println(p.checkSumPairs_simple(new int[] { 5, 7, 95, 1, 193, 99, 100 }, 100));
		// TC3 - invalid. pair sum not divisible by k
		System.out.println(p.checkSumPairs_simple(new int[] { 5, 7, 95, 1, 193, 95 }, 100));

		// TC1 - valid. Even number of elements forming pairs divisible by k
		System.out.println(p.checkSumPairs_hashing(new int[] { 5, 7, 95, 1, 193, 99 }, 100));
		// TC2 - invalid. odd num of elements
		System.out.println(p.checkSumPairs_hashing(new int[] { 5, 7, 95, 1, 193, 99, 100 }, 100));
		// TC3 - invalid. pair sum not divisible by k
		System.out.println(p.checkSumPairs_hashing(new int[] { 5, 7, 95, 1, 193, 95 }, 100));

	}
}
