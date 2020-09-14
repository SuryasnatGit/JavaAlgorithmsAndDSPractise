package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubArraySumProblems {

	/**
	 * find the number of fragments of an array such that sum is equals to 0 (that is, pairs(P,Q) such that P'<"Q and
	 * the sum A[P]+A[P+1]...+A[Q] equals to 0). T - O(n) S - O(n)
	 * 
	 */
	public int subArraysNumCountWithZeroSum(int[] a) {
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

	/**
	 * Function to print all sub-arrays with 0 sum present in the given array
	 * 
	 * @param A
	 */
	public void printallSubarraysWithZeroSum(int[] A) {
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

	// Utility function to insert <key, value> into the Multimap
	private void insert(Map<Integer, List<Integer>> hashMap, Integer key, Integer value) {
		// if the key is seen for the first time, initialize the list
		if (!hashMap.containsKey(key)) {
			hashMap.put(key, new ArrayList<>());
		}

		hashMap.get(key).add(value);
	}

	/**
	 * Function to check if sub-array with given sum exists in the array or not. This works for both positive and
	 * negative integers.
	 * 
	 * T - O(n) S - O(n)
	 * 
	 */
	public void checkSubArrayWithGivenSumExists(int[] A, int sum) {
		int start = 0, end = -1, currSum = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < A.length; i++) {
			currSum += A[i];

			if (currSum - sum == 0) {
				start = 0;
				end = i;
				System.out.println("Subarray found from " + start + " to " + end);
				break;
			}

			if (map.containsKey(currSum - sum)) {
				start = map.get(currSum - sum) + 1;
				end = i;
				System.out.println("Subarray found from " + start + " to " + end);
				break;
			}

			map.put(currSum, i);
		}
		if (end == -1) {
			System.out.println("subarray not found");
		}
	}

	/**
	 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
	 * equals to k.
	 * 
	 * Example 1:
	 * 
	 * Input:nums = [1,1,1], k = 2 Output: 2
	 */
	public int numOfSubArraysWithSumK(int[] nums, int k) {
		int currSum = 0, count = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			currSum += nums[i];

			if (currSum == k) {
				count++;
			}

			if (map.containsKey(currSum - k)) {
				count += map.get(currSum - k);
			}

			Integer c = map.get(currSum);
			map.put(currSum, c == null ? 1 : c + 1);
		}

		return count;
	}

	public static void main(String[] args) {
		SubArraySumProblems sf = new SubArraySumProblems();
		System.out.println(sf.subArraysNumCountWithZeroSum(new int[] { 2, -2, 3, 0, 4, -7 }));
		sf.printallSubarraysWithZeroSum(new int[] { 2, -2, 3, 0, 4, -7 });

		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, -4);
		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, -6);
		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, 2);
		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, -8);
		sf.checkSubArrayWithGivenSumExists(new int[] { 3, -1, -5, 8 }, 5);

		System.out.println(sf.numOfSubArraysWithSumK(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0));
	}
}
