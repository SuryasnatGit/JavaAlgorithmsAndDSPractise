package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Category : Medium
 */
public class ShortestWordDistance {

	private HashMap<String, ArrayList<Integer>> map;

	public ShortestWordDistance(String[] words) {
		map = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < words.length; i++) {
			if (map.containsKey(words[i])) {
				map.get(words[i]).add(i);
			} else {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(i);
				map.put(words[i], list);
			}
		}
	}

	/**
	 * Problem 1: Given a list of words and two words word1 and word2, return the shortest distance between these two
	 * words in the list.
	 * 
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	 * 
	 * Given word1 = “coding”, word2 = “practice”, return 3.
	 * 
	 * Given word1 = "makes", word2 = "coding", return 1.
	 * 
	 * 
	 */
	public int shortestDistance(String[] words, String word1, String word2) {
		int m = -1;
		int n = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (word1.equals(s)) {
				m = i;
				if (n != -1)
					min = Math.min(min, m - n);
			} else if (word2.equals(s)) {
				n = i;
				if (m != -1)
					min = Math.min(min, n - m);
			}
		}
		return min;
	}

	/**
	 * 
	 * Problem 2 : This is a follow up of Shortest Word Distance. The only difference is now you are given the list of
	 * words and your method will be called repeatedly many times with different parameters. How would you optimize it?
	 * 
	 * Design a class which receives a list of words in the constructor, and implements a method that takes two words
	 * word1 and word2 and return the shortest distance between these two words in the list
	 * 
	 * For example,
	 * 
	 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	 * 
	 * Given word1 = “coding”, word2 = “practice”, return 3.
	 * 
	 * Given word1 = "makes", word2 = "coding", return 1.
	 * 
	 * The time complexity of the shortest method is now O(M+N). Since M+N < size of word list, the time is O(K) where k
	 * is the list size
	 * 
	 */
	public int shortest(String word1, String word2) {
		ArrayList<Integer> l1 = map.get(word1);
		ArrayList<Integer> l2 = map.get(word2);

		int result = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		while (i < l1.size() && j < l2.size()) {
			result = Math.min(result, Math.abs(l1.get(i) - l2.get(j)));
			if (l1.get(i) < l2.get(j)) {
				i++;
			} else {
				j++;
			}
		}

		return result;
	}

	/**
	 * This is a follow-up problem of Shortest Word Distance. The only difference is now word1 could be the same as
	 * word2.
	 * 
	 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the
	 * list.
	 * 
	 * word1 and word2 may be the same and they represent two individual words in the list.
	 * 
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	 * 
	 */
	public int shortestWordDistance3(String[] words, String word1, String word2) {
		if (words == null || words.length == 0)
			return -1;

		if (word1 == null || word2 == null)
			return -1;

		boolean isSame = false;

		if (word1.equals(word2))
			isSame = true;

		int shortest = Integer.MAX_VALUE;

		int prev = -1;
		int i1 = -1;
		int i2 = -1;

		for (int i = 0; i < words.length; i++) {
			if (isSame) {
				if (words[i].equals(word1)) {
					if (prev != -1) {
						shortest = Math.min(shortest, i - prev);
					}
					prev = i;
				}
			} else {
				if (word1.equals(words[i])) {
					i1 = i;
					if (i2 != -1) {
						shortest = Math.min(shortest, i - i2);
					}
				} else if (word2.equals(words[i])) {
					i2 = i;
					if (i1 != -1) {
						shortest = Math.min(shortest, i - i1);
					}
				}
			}
		}

		return shortest;
	}

}
