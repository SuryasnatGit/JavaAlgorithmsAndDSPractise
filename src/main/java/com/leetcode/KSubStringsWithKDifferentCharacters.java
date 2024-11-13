package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string S and an integer K. Calculate the number of substrings of length K and containing K different
 * characters
 * 
 * Example:
 * 
 * String: "abcabc" K: 3
 * 
 * Answer: 3 substrings: ["abc", "bca", "cab"]
 * 
 * String: "abacab" K: 3
 * 
 * Answer: 2 substrings: ["bac", "cab"]
 * 
 * Category : Medium
 */
public class KSubStringsWithKDifferentCharacters {

	/**
	 * @param stringIn:
	 *            The original string.
	 * @param K:
	 *            The length of substrings.
	 * @return: return the count of substring of length K and exactly K distinct characters.
	 */
	public int KSubstring(String stringIn, int K) {
		if (stringIn == null || stringIn.length() == 0 || K <= 0) {
			return 0;
		}
		Map<Character, Integer> charMap = new HashMap<>();
		Set<String> resultSet = new HashSet<String>();
		int len = stringIn.length();
		int j = 0;
		for (int i = 0; i < len; i++) {
			while (j < len && charMap.size() < K) {
				char c = stringIn.charAt(j);
				if (charMap.containsKey(c)) {
					break;
				}
				charMap.put(c, 1);
				j++;
				if (charMap.size() == K) {
					resultSet.add(stringIn.substring(i, j));
				}
			}
			charMap.remove(stringIn.charAt(i));
		}
		return resultSet.size();
	}

	public static void main(String[] args) {
		KSubStringsWithKDifferentCharacters k = new KSubStringsWithKDifferentCharacters();
		System.out.println(k.KSubstring("abcabc", 3));
		System.out.println(k.KSubstring("abacab", 3));
	}
}
