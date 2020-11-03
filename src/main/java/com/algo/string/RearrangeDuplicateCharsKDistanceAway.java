package com.algo.string;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Category : Hard
 */
public class RearrangeDuplicateCharsKDistanceAway {

	class CharCount implements Comparable<CharCount> {
		char ch;
		int count;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ch;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CharCount other = (CharCount) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (ch != other.ch)
				return false;
			return true;
		}

		private RearrangeDuplicateCharsKDistanceAway getOuterType() {
			return RearrangeDuplicateCharsKDistanceAway.this;
		}

		@Override
		public String toString() {
			return "CharCount [ch=" + ch + ", count=" + count + "]";
		}

		@Override
		public int compareTo(CharCount cc) {
			if (this.count >= cc.count) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public boolean rearrangeExactKDistanceAway(char input[], int d) {

		PriorityQueue<CharCount> heap = new PriorityQueue<CharCount>();
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < input.length; i++) {
			int count = 1;
			if (map.containsKey(input[i])) {
				count = map.get(input[i]);
				count++;
			}
			map.put(input[i], count);
			input[i] = 0;
		}

		for (Character ch : map.keySet()) {
			CharCount cc = new CharCount();
			cc.ch = ch;
			cc.count = map.get(ch);
			heap.add(cc);
		}

		while (heap.size() > 0) {
			CharCount cc = heap.poll();
			int i;
			for (i = 0; i < input.length && input[i] != 0; i++)
				;
			if (i == input.length) {
				return false;
			}
			while (cc.count > 0 && i < input.length) {
				input[i] = cc.ch;
				i = i + d;
				cc.count--;
			}
			if (cc.count > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * https://leetcode.com/problems/rearrange-string-k-distance-apart/
	 * 
	 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least
	 * distance k from each other.
	 * 
	 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty
	 * string "".
	 * 
	 * Example 1: Input: s = "aabbcc", k = 3 Output: "abcabc" Explanation: The same letters are at least distance 3 from
	 * each other.
	 * 
	 * Example 2: Input: s = "aaabc", k = 3 Output: "" Explanation: It is not possible to rearrange the string.
	 * 
	 * Example 3: Input: s = "aaadbbcc", k = 2 Output: "abacabcd" Explanation: The same letters are at least distance 2
	 * from each other.
	 * 
	 * https://leetcode.com/problems/reorganize-string/ --> The simplified version is equivalent to considering only the
	 * case of k=2
	 * 
	 */
	public String rearrangeAtleastkDistanceAway(char input[], int k) {

		StringBuilder sb = new StringBuilder();

		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (char c : input) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(map.size(),
				new Comparator<Map.Entry<Character, Integer>>() {
					@Override
					public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
						return entry2.getValue() - entry1.getValue();
					}
				});

		heap.addAll(map.entrySet());

		Queue<Map.Entry<Character, Integer>> queue = new LinkedList<Map.Entry<Character, Integer>>();

		while (!heap.isEmpty()) {
			Map.Entry<Character, Integer> now = heap.poll(); // Current largest occurance
			sb.append(now.getKey());

			now.setValue(now.getValue() - 1);
			queue.offer(now);

			if (queue.size() < k) {
				continue;
			}

			Map.Entry<Character, Integer> entry = queue.poll();
			if (entry.getValue() != 0) {
				heap.offer(entry);
			}
		}

		return sb.length() == input.length ? sb.toString() : "";
	}

	public static void main(String args[]) {
		String str = "ABBACCCCDD";
		char input[] = str.toCharArray();
		RearrangeDuplicateCharsKDistanceAway rdc = new RearrangeDuplicateCharsKDistanceAway();
		System.out.println(rdc.rearrangeAtleastkDistanceAway(input, 3));

		System.out.println();

		char[] input1 = "ABBACCCCDD".toCharArray();
		boolean res = rdc.rearrangeExactKDistanceAway(input1, 3);
		if (res) {
			for (char ch : input1) {
				System.out.print(ch + " ");
			}
		} else {
			System.out.println("Not possible");
		}
	}

}
