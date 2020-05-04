package com.algo.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * http://blog.gainlo.co/index.php/2016/04/29/minimum-number-of-deletions-of-a-string/
 * 
 * Question : Given a string and a dictionary HashSet, write a function to determine the minimum number of characters to
 * delete to make a word.
 * 
 * dictionary: [“a”, “aa”, “aaa”]
 * 
 * query: “abc”
 * 
 * output: 2
 * 
 * For example, string “catn” needs one deletion to make it a valid word “cat” in the dictionary. And string “bcatn”
 * needs two deletions.
 * 
 * Questions : 1. will the characters that need to be deleted need to be contiguous or they can be random located
 * 
 * Category : Hard
 */
public class StringDeletion {

	// BFS approach. T - O(2^N) where n = number of chars in string
	public int delete(String query, Set<String> dictionary) {
		Queue<String> queue = new LinkedList<>();
		Set<String> queueElements = new HashSet<>();

		queue.add(query);
		queueElements.add(query);

		while (!queue.isEmpty()) {
			String s = queue.poll();
			queueElements.remove(s);

			if (dictionary.contains(s))
				return query.length() - s.length();

			for (int i = 0; i < s.length(); i++) {
				String sub = s.substring(0, i) + s.substring(i + 1, s.length());
				if (sub.length() > 0 && !queueElements.contains(sub)) {
					queue.add(sub);
					queueElements.add(sub);
				}
			}
		}
		return -1;
	}

	// Complexity=len(inputString)*len(dict)*len(wordsinDict) ==> n*m*k O(nmk)
	public int delete_dp(String s, Set<String> dictionary) {
		if (s == null || s.isEmpty()) {
			return -1;
		}
		int max = Integer.MIN_VALUE;
		for (String a : dictionary) {
			if (s.length() < a.length()) {
				continue;
			}

			int[][] arr = new int[a.length() + 1][s.length() + 1];

			for (int i = 1; i <= a.length(); i++) {
				for (int j = 1; j <= s.length(); j++) {
					if (a.charAt(i - 1) == s.charAt(j - 1)) {
						arr[i][j] = Math.max(arr[i - 1][j - 1] + 1, arr[i - 1][j]);
					} else {
						arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j]);
					}
				}
			}
			if (max < arr[a.length()][s.length()]) {
				max = arr[a.length()][s.length()];
			}
		}
		return s.length() - max < 0 ? -1 : s.length() - max;
	}

	// TODO: trie approach. Ref: https://www.careercup.com/question?id=5123159217930240

	public static void main(String[] args) {
		StringDeletion sd = new StringDeletion();

		Set<String> dictionary = new HashSet<>(Arrays.asList("can", "bat"));

		System.out.println("bfs approach -->");
		System.out.println(sd.delete("bcatn", dictionary)); // 2
		System.out.println(sd.delete("can", dictionary)); // 0
		System.out.println(sd.delete("", dictionary)); // -1
		System.out.println(sd.delete("ba", dictionary)); // -1
		System.out.println(sd.delete("pqrst", dictionary)); // -1

		System.out.println("dp approach -->");
		System.out.println(sd.delete_dp("bcatn", dictionary)); // 2
		System.out.println(sd.delete_dp("can", dictionary)); // 0
		System.out.println(sd.delete_dp("", dictionary)); // -1
		System.out.println(sd.delete_dp("ba", dictionary)); // -1
		System.out.println(sd.delete_dp("pqrst", dictionary)); // 4(should be -1) --> wrong.
	}

}
