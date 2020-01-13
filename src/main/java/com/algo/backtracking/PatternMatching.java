package com.algo.backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.techiedelight.com/determine-pattern-matches-string-not/
 * 
 */
public class PatternMatching {

	public boolean patternMatcher(String s, String p) {
		Map<Character, String> map = new HashMap<>();
		return recurse(s, 0, p, 0, map);
	}

	private boolean recurse(String s, int i, String p, int j, Map<Character, String> map) {
		int m = s.length();
		int n = p.length();

		if (m < n)
			return false;

		if (i == m && j == n)
			return true;

		if (i == m || j == n)
			return false;

		char curr = p.charAt(j);
		if (map.containsKey(curr)) {
			String s1 = map.get(curr);
			int l = s1.length();

			String substr = null;
			if (i + l < m) {
				substr = s.substring(i, i + l);
			} else {
				substr = s.substring(i);
			}

			if (!s1.equals(substr))
				return false;

			return recurse(s, i + l, p, j + 1, map);
		}

		for (int k = 1; k <= m - i; k++) {
			// try with this option
			map.put(curr, s.substring(i, i + k));

			// check if it matches
			if (recurse(s, i + k, p, j + 1, map))
				return true;

			// if it does not match then back track
			map.remove(curr);
		}

		return false;
	}

	public static void main(String[] args) {
		PatternMatching pm = new PatternMatching();
		System.out.println(pm.patternMatcher("abcdefabc", "xyx"));
		System.out.println(pm.patternMatcher("abcdefabcc", "xyx"));
	}
}
