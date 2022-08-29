package com.algo.ds.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Find longest consecutive subsequence in unsorted array.
 *
 * Time complexity O(n) Space complexity O(n)
 *
 * Reference: https://leetcode.com/problems/longest-consecutive-sequence/.
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * Example:
 * 
 * Input: [100, 4, 200, 1, 3, 2] Output: 4 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

	// T - O(n) S - O(n)
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int result = 1;
		for (int i : nums) {
			if (map.containsKey(i)) {
				continue;
			}
			int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
			int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;

			// sum is length of sequence
			int sum = left + right + 1;
			map.put(i, sum);

			// keep track of max length
			result = Math.max(sum, result);

			// extending the length to the boundry of the sequence will do nothing if n has no neighbors
			map.put(i - left, sum);
			map.put(i + right, sum);
		}
		return result;
	}

	// T - O(n) S - O(n)
	public int longestConsecutive1(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			set.add(num);
		}

		int max = 0;
		for (int num : nums) {

			int down = num - 1; // Going down
			while (set.contains(down)) {
				set.remove(down); // This will speed up
				down--;
			}

			int up = num + 1; // Going up
			while (set.contains(up)) {
				set.remove(up);
				up++;
			}

			max = Math.max(max, up - down - 1);
		}

		return max;
	}

	public static void main(String args[]) {
		LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
		int[] input = { 100, 4, 200, 1, 3, 2 };
		System.out.println(lcs.longestConsecutive(input));
		System.out.println(lcs.longestConsecutive1(input));
	}
}