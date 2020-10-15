package com.algo.string;

/**
 * References
 * 
 * https://leetcode.com/problems/minimum-window-substring/
 * 
 * http://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 * 
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"

Output: "BANC"

Note:

If there is no such window in S that covers all characters in T, return the empty string "".

If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

Category : Hard

 */
import java.util.HashMap;
import java.util.Map;

public class SmallestWindowContaingAllCharacters {

	// Brute force solution:
	// 1- Generate all substrings of string1 (“this is a test string”)
	// 2- For each substring, check whether the substring contains all characters of string2 (“tist”)
	// 3- Finally, print the smallest substring containing all characters of string2.

	/**
	 * Optimized solution. T - O(S + T) S - O(S + T)
	 * 
	 */
	public String minWindow(String s, String t) {

		if (s == null || t == null || s.length() < t.length()) {
			return "";
		}

		// update ap for chars in pattern t
		Map<Character, Integer> charCountMap = new HashMap<>();
		for (char ch : t.toCharArray()) {
			charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
		}

		int start = 0;
		int minWindowLength = Integer.MAX_VALUE;
		int minStart = 0;
		int end = 0;
		int patternLength = t.length();

		while (end < s.length()) {
			Integer val = charCountMap.get(s.charAt(end));
			if (val == null) {
				end++;
				continue;
			}
			if (val > 0) {
				patternLength--;
			}
			val--;
			charCountMap.put(s.charAt(end), val);

			// window found
			while (patternLength == 0) {
				// check minimum window
				if (minWindowLength > (end - start + 1)) {
					minWindowLength = end - start + 1;
					minStart = start;
				}

				Integer val1 = charCountMap.get(s.charAt(start));
				if (val1 != null) {
					if (val1 == 0) {
						break;
					} else {
						val1++;
						charCountMap.put(s.charAt(start), val1);
					}
				}
				start++;
			}

			end++;
		}

		return minWindowLength != Integer.MAX_VALUE ? s.substring(minStart, minStart + minWindowLength) : "";
	}

	public static void main(String args[]) {

		SmallestWindowContaingAllCharacters swcac = new SmallestWindowContaingAllCharacters();
		System.out.println(swcac.minWindow("appleal", "al"));
		System.out.println(swcac.minWindow("ADOBECODEBANC", "ABC"));
	}

}
