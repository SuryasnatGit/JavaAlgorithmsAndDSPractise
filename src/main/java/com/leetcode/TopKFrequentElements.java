package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2
 * 
 * Output: [1,2]
 * 
 * Example 2:
 * 
 * Input: nums = [1], k = 1
 * 
 * Output: [1]
 * 
 * Note:
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements. <br/>
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.<br/>
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.<br/>
 * You can return the answer in any order.<br/>
 *
 */
public class TopKFrequentElements {

	// Solution 1 - Using priority queue
	// T - O(n log k) if k < n and O(n) if k == n. S - O(n + k).
	public List<Integer> topKFrequentPQ(List<Integer> nums, int k) {

		List<Integer> res = new ArrayList<>();

		if (nums == null || nums.size() == 0)
			return res;

		if (k == nums.size())
			return nums;

		Map<Integer, Integer> count = new HashMap<>();
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		// using custom descending order comparator
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((q1, q2) -> count.get(q1) - count.get(q2));
		// T - O(n log k) < O(n log n)
		for (int n : count.keySet()) {
			queue.offer(n);
			if (queue.size() > k) {
				queue.poll();
			}
		}

		// T - O(k log k)
		while (k-- > 0) {
			res.add(queue.poll());
		}

		return res;
	}

	// Solution 2 - using inverted index
	public List<Integer> topKFrequent2(int[] nums, int k) {
		int length = nums.length;

		// Build a frequency map
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < length; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], 1);
			} else {
				map.put(nums[i], map.get(nums[i]) + 1);
			}
		}

		// Build an inverted index of the frequency map
		java.util.TreeMap<Integer, List<Integer>> invertedIndex = new java.util.TreeMap<>();
		for (Integer key : map.keySet()) {
			int freq = map.get(key);
			if (!invertedIndex.containsKey(freq)) {
				List<Integer> keys = new ArrayList<>();
				keys.add(key);
				invertedIndex.put(freq, keys);
			} else {
				invertedIndex.get(freq).add(key);
			}
		}

		// Output the top K
		int i = 0;
		List<Integer> result = new ArrayList<>();
		for (Integer freq : invertedIndex.descendingKeySet()) {
			for (Integer key : invertedIndex.get(freq)) {
				if (i >= k) {
					return result;
				}
				result.add(key);
				i++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		TopKFrequentElements top = new TopKFrequentElements();
		System.out.println(top.topKFrequentPQ(Arrays.asList(1, 1, 1, 3, 2, 2), 2));
		System.out.println(top.topKFrequentPQ(Arrays.asList(1, 1, 1, 3, 2, 2, 4, 4, 4), 2));
		System.out.println(top.topKFrequent2(new int[] { 1, 1, 1, 3, 2, 2 }, 2));
		System.out.println(top.topKFrequent2(new int[] { 1, 1, 1, 3, 2, 2, 4, 4, 4 }, 2));
	}
}
