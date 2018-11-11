package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Find longest consecutive subsequence in unsorted array.
 *
 * Time complexity O(n) Space complexity O(n)
 *
 * Reference: https://leetcode.com/problems/longest-consecutive-sequence/.
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements
 * sequence.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * Example:
 * 
 * Input: [100, 4, 200, 1, 3, 2] Output: 4 Explanation: The longest consecutive elements sequence is
 * [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSubsequence {
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int result = 1;
		for (int i : nums) {
			if (map.containsKey(i)) {
				continue;
			}
			int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
			int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;

			int sum = left + right + 1;
			map.put(i, sum);
			result = Math.max(sum, result);
			map.put(i - left, sum);
			map.put(i + right, sum);
		}
		return result;
	}

	public static void main(String args[]) {
		LongestConsecutiveSubsequence lcs = new LongestConsecutiveSubsequence();
		int[] input = { 100, 4, 200, 1, 3, 2 };
		System.out.println(lcs.longestConsecutive(input));
	}
}