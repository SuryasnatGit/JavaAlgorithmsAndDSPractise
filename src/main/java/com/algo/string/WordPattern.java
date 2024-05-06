package com.algo.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring
 * in str.
 * 
 * Examples:
 * 
 * pattern = "abab", str = "redblueredblue" should return true.
 * 
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * 
 * pattern = "aabb", str = "xyzabcxzyabc" should return false
 * 
 * Category : Hard
 *
 * TODO: find time complexity and improve it
 */
public class WordPattern {

	public boolean wordPatternMatch(String str, String pattern) {
		if (pattern.length() == 0 && str.length() == 0)
			return true;
		if (pattern.length() == 0)
			return false;

		HashMap<Character, String> map = new HashMap<Character, String>();
		Set<String> set = new HashSet<String>();
		return helper(pattern, str, 0, 0, map, set);
	}

	private boolean helper(String pattern, String str, int i, int j, Map<Character, String> map, Set<String> set) {
		if (i == pattern.length() && j == str.length()) {
			return true;
		}

		if (i >= pattern.length() || j >= str.length())
			return false;

		char c = pattern.charAt(i);
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(c) && !set.contains(sub)) {
				map.put(c, sub);
				set.add(sub);
				if (helper(pattern, str, i + 1, k, map, set)) {
					return true;
				}
				// else backtrack
				map.remove(c);
				set.remove(sub);
			} else if (map.containsKey(c) && map.get(c).equals(sub)) {
				if (helper(pattern, str, i + 1, k, map, set))
					return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		WordPattern wp = new WordPattern();
		System.out.println(wp.wordPatternMatch("redblueredblue", "abab"));
		System.out.println(wp.wordPatternMatch("xyzabcxzyabc", "aabb"));
	}

}
