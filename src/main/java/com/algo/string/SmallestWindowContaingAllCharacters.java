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

	public String minWindow(String s, String t) {

		Map<Character, Integer> countMap = new HashMap<>();
		for (char ch : t.toCharArray()) {
			Integer val = countMap.get(ch);
			if (val == null) {
				val = 0;
			}
			countMap.put(ch, val + 1);
		}

		int start = 0;
		int currLen = t.length();
		int minWindow = Integer.MAX_VALUE;
		int minStart = 0;
		int i = 0;
		while (i < s.length()) {
			Integer val = countMap.get(s.charAt(i));
			if (val == null) {
				i++;
				continue;
			}
			if (val > 0) {
				currLen--; // shorten t
			}
			val--; // reduce count of that char by 1
			countMap.put(s.charAt(i), val);

			while (currLen == 0) { // this means all chars in t are covered. now check the segment length
				if (minWindow > i - start + 1) {
					minWindow = i - start + 1;
					minStart = start;
				}
				Integer val1 = countMap.get(s.charAt(start));
				if (val1 != null) {
					if (val1 == 0) {
						break;
					} else {
						val1++;
						countMap.put(s.charAt(start), val1);
					}
				}
				start++;
			}
			i++;
		}

		return minWindow != Integer.MAX_VALUE ? s.substring(minStart, minStart + minWindow) : "";
	}

	public static void main(String args[]) {

		String str = "Tsuaosyogrlmnsluuorjkoruost";
		String subString = "soor";
		SmallestWindowContaingAllCharacters swcac = new SmallestWindowContaingAllCharacters();
		String result = swcac.minWindow(str, subString);
		System.out.println(result);
	}

}
