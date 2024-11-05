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
 * Category : Medium
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

	/**
	 * Given a non-empty list of words, return the k most frequent elements.
	 * 
	 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the
	 * word with the lower alphabetical order comes first.
	 * 
	 * Example 1:
	 * 
	 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	 * 
	 * Output: ["i", "love"]
	 * 
	 * Explanation: "i" and "love" are the two most frequent words.
	 * 
	 * Note that "i" comes before "love" due to a lower alphabetical order.
	 * 
	 * Example 2:
	 * 
	 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
	 * 
	 * Output: ["the", "is", "sunny", "day"]
	 * 
	 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being
	 * 4, 3, 2 and 1 respectively.
	 * 
	 * Time Complexity: O(N log k), where N is the length of words. We count the frequency of each word in O(N) time,
	 * then we add N words to the heap, each in O(log k) time. Finally, we pop from the heap up to k times. As k ≤ N,
	 * this is O(N log k) in total.
	 * 
	 * Space Complexity: O(N), the space used to store our count.
	 * 
	 * 
	 * 
	 * @param words
	 * @param k
	 * @return
	 */
	public List<String> topKFrequentWords(String[] words, int k) {
		if (words == null || words.length == 0)
			return new ArrayList<>();

		// calculate frequency
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}

		PriorityQueue<String> pq = new PriorityQueue<String>(
				(s1, s2) -> map.get(s2).equals(map.get(s1)) ? s1.compareTo(s2) : map.get(s2) - map.get(s1));
		for (String word : map.keySet()) {
			pq.offer(word);
		}

		List<String> res = new ArrayList<>();
		while (!pq.isEmpty() && k-- > 0) {
			res.add(pq.poll());
		}

		return res;
	}

	public static void main(String[] args) {
		TopKFrequentElements top = new TopKFrequentElements();
		System.out.println(top.topKFrequentWords(new String[] { "i", "love", "leetcode", "i", "love", "coding" }, 2));
		System.out.println(top.topKFrequentWords(
				new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" }, 4));

		System.out.println(top.topKFrequentPQ(Arrays.asList(1, 1, 1, 3, 2, 2), 2));
		System.out.println(top.topKFrequentPQ(Arrays.asList(1, 1, 1, 3, 2, 2, 4, 4, 4), 2));
		System.out.println(top.topKFrequent2(new int[] { 1, 1, 1, 3, 2, 2 }, 2));
		System.out.println(top.topKFrequent2(new int[] { 1, 1, 1, 3, 2, 2, 4, 4, 4 }, 2));
	}
}
