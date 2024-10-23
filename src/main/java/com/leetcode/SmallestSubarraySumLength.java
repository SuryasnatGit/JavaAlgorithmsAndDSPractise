package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO : to check
 */
public class SmallestSubarraySumLength {

	/**
	 * Problem : Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous
	 * subarray of which the sum ≥ s. If there isn't one, return 0 instead.
	 * 
	 * Example:
	 * 
	 * Input: s = 7, nums = [2,3,1,2,4,3] Output: 2 Explanation: the subarray [4,3] has the minimal length under the
	 * problem constraint. Follow up: If you have figured out the O(n) solution, try coding another solution of which
	 * the time complexity is O(n log n).
	 * 
	 */
	// T - O(n)
	public int smallestSubarraySumLength(int[] arr, int sum) {
		int windowSum = 0;
		int length = Integer.MAX_VALUE;

		int left = 0;
		for (int right = 0; right < arr.length; right++) {
			windowSum += arr[right];
			while (windowSum >= sum && left <= right) {
				length = Math.min(length, right - left + 1);
				// remove element from window left side till it becomes stable egain
				windowSum -= arr[left];
				// move left
				left++;
			}
		}

		return length == Integer.MAX_VALUE ? -1 : length;
	}

	// T - O(N LogN) S - O(N).
	// TODO: not working need to check.
	public int smallestSubarraySumLength1(int[] arr, int sum) {
		// Stores the frequency of prefix sums in the array
		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();

		mp.put(arr[0], 0);

		for (int i = 1; i < arr.length; i++) {
			arr[i] = arr[i] + arr[i - 1];
			mp.put(arr[i], i);
		}

		// Initialize len as Integer.MAX_VALUE
		int len = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {
			// If sum of array till i-th index is less than K
			if (arr[i] < sum) {
				// No possible subarray exists till i-th index
				continue;
			} else {

				// Find the exceeded value
				int x = sum - arr[i];

				// If exceeded value is zero
				if (x == 0) {
					len = Math.min(len, i);
				}

				if (mp.containsValue(x)) {
					continue;
				} else {
					len = Math.min(len, i);
				}
			}
		}

		return len == Integer.MAX_VALUE ? -1 : len;
	}

}
