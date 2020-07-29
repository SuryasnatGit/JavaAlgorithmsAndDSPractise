package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * find the number of fragments of an array such that sum is equals to 0 (that is, pairs(P,Q) such that P'<"Q and the
 * sum A[P]+A[P+1]...+A[Q] equals to 0).
 *
 */
public class SubArrayFragmentSum {

	/**
	 * T - O(n) S - O(n)
	 * 
	 */
	public int subArraySumCount(int[] a) {
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

	// Utility function to insert <key, value> into the Multimap
	private void insert(Map<Integer, List<Integer>> hashMap, Integer key, Integer value) {
		// if the key is seen for the first time, initialize the list
		if (!hashMap.containsKey(key)) {
			hashMap.put(key, new ArrayList<>());
		}

		hashMap.get(key).add(value);
	}

	// Function to print all sub-arrays with 0 sum present
	// in the given array
	public void printallSubarrays(int[] A) {
		// create an empty Multi-map to store ending index of all
		// sub-arrays having same sum
		Map<Integer, List<Integer>> hashMap = new HashMap<>();

		// insert (0, -1) pair into the map to handle the case when
		// sub-array with 0 sum starts from index 0
		insert(hashMap, 0, -1);

		int sum = 0;

		// traverse the given array
		for (int i = 0; i < A.length; i++) {
			// sum of elements so far
			sum += A[i];

			// if sum is seen before, there exists at-least one
			// sub-array with 0 sum
			if (hashMap.containsKey(sum)) {
				List<Integer> list = hashMap.get(sum);

				// find all sub-arrays with same sum
				for (Integer value : list) {
					System.out.println("Subarray [" + (value + 1) + ".." + i + "]");
				}
			}

			// insert (sum so far, current index) pair into the Multi-map
			insert(hashMap, sum, i);
		}
	}

	public static void main(String[] args) {
		SubArrayFragmentSum sf = new SubArrayFragmentSum();
		System.out.println(sf.subArraySumCount(new int[] { 2, -2, 3, 0, 4, -7 }));
		sf.printallSubarrays(new int[] { 2, -2, 3, 0, 4, -7 });
	}
}
