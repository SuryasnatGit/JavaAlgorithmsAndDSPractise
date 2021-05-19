package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * 
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * 
 * Example 1:
 * 
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * 
 * Output: 7
 * 
 * Explanation: There are 7 subarrays with a sum divisible by K = 5: [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3],
 * [0], [0, -2, -3], [-2, -3]
 * 
 * Category : Medium
 */
public class SubarraySumsDivisibleByK {

	public static void main(String[] args) {
		SubarraySumsDivisibleByK kSub = new SubarraySumsDivisibleByK();

		System.out.println(kSub.subarraysDivByK1(5, new int[] { 4, 5, 0, -2, -3, 1 }));
		System.out.println(kSub.subarraysDivByK2(5, new int[] { 4, 5, 0, -2, -3, 1 }));
	}

	// T - O(n^2) S - O(1) TLE on LC
	public int subarraysDivByK1(int k, int[] arr) {
		int count = 0;

		for (int i = 0; i < arr.length; i++) { // Start Point
			int sum = 0;
			for (int j = i; j < arr.length; j++) { // End Point
				sum += arr[j];

				if (sum % k == 0) {
					count++;
				}
			}
		}

		return count;
	}

	/**
	 * Better solution: Idea is whenever we encounter same remainder at two different points, the sum off elements
	 * between the two points will be divisible by K
	 * 
	 * T - O(N) S - O(N)
	 */
	public int subarraysDivByK2(int K, int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int currSum = 0;
		int cnt = 0;
		int remainder = 0;
		for (int i = 0; i < arr.length; i++) {
			currSum += arr[i];
			remainder = currSum % K;
			while (remainder < 0)
				remainder += K;
			if (map.containsKey(remainder)) {
				cnt += map.get(remainder);
			}
			map.put(remainder, map.getOrDefault(remainder, 0) + 1);
		}
		return cnt;
	}
}
