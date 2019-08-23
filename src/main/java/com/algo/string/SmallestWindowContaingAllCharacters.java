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

 */
import java.util.HashMap;
import java.util.Map;

public class SmallestWindowContaingAllCharacters {

	// Brute force solution:
	// 1- Generate all substrings of string1 (“this is a test string”)
	// 2- For each substring, check whether the substring contains all characters of string2 (“tist”)
	// 3- Finally, print the smallest substring containing all characters of string2.

	/**
	 * Optimized solution
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public String minWindow(String source, String pattern) {

		// update count map for chars in pattern
		Map<Character, Integer> countMap = new HashMap<>();
		for (char ch : pattern.toCharArray()) {
			Integer val = countMap.get(ch);
			if (val == null) {
				val = 0;
			}
			countMap.put(ch, val + 1);
		}

		int start = 0;
		int patternLength = pattern.length();
		int minWindow = Integer.MAX_VALUE;
		int minStart = 0;
		int i = 0;
		while (i < source.length()) {
			Integer val = countMap.get(source.charAt(i));
			if (val == null) {
				i++;
				continue;
			}
			if (val > 0) {
				patternLength--; // shorten t
			}
			val--; // reduce count of that char by 1
			countMap.put(source.charAt(i), val);

			// this means all chars in t are covered. now check the segment length
			while (patternLength == 0) {
				if (minWindow > i - start + 1) {
					minWindow = i - start + 1;
					minStart = start;
				}
				Integer val1 = countMap.get(source.charAt(start));
				if (val1 != null) {
					if (val1 == 0) {
						break;
					} else {
						val1++;
						countMap.put(source.charAt(start), val1);
					}
				}
				start++;
			}
			i++;
		}

		return minWindow != Integer.MAX_VALUE ? source.substring(minStart, minStart + minWindow) : "";
	}

	public static void main(String args[]) {

		String str = "Tsuaosyogrlmnsluuorjkoruost";
		String subString = "soor";
		SmallestWindowContaingAllCharacters swcac = new SmallestWindowContaingAllCharacters();
		String result = swcac.minWindow(str, subString);
		System.out.println(result);
	}

}
