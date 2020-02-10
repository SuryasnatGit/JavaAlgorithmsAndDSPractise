package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequentElements {

	// Solution 1 - Using priority queue
	public List<Integer> topKFrequent1(int[] nums, int k) {
		Map<Integer, Integer> dic = new HashMap<>();
		for (int num : nums) {
			dic.put(num, dic.getOrDefault(num, 0) + 1);
		}

		PriorityQueue<int[]> queue = new PriorityQueue<>((q1, q2) -> q2[1] - q1[1]);
		for (int n : dic.keySet()) {
			queue.offer(new int[] { n, dic.get(n) });
		}
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			res.add(queue.poll()[0]);
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
}
