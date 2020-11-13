package com.algo.string;

import java.util.Arrays;

/**
 * String replacement is to give you an original string s, a target string p, a replacement string t, and replace all p
 * in s with t, for example, s="abcdefg", p="bc", // t="x", return "axdefg" // What the interviewer expects is an O(n)
 * solution. For example, use a sliding window to record the appearance frequency of each character in the current
 * window. Only when the appearance frequency matches the character frequency in the target string, p will string
 * comparison be performed.
 * 
 */
public class ReplaceString {

	public String replaceString(String original, String from, String to) {
		if (original.length() < from.length() || from.equals(to)) {
			return original;
		}

		int[] count = new int[26];
		for (char c : from.toCharArray()) {
			count[c - 'a']++;
		}

		int[] curCount = new int[26]; // number of records
		int left = 0, right = 0;
		while (right < original.length()) {
			if (right - left < from.length()) {
				curCount[original.charAt(right) - 'a']++;
			} else {
				boolean isSame = true;
				for (int i = 0; i < 26; i++) {
					if (count[i] != curCount[i]) {
						isSame = false;
					}
				}

				if (isSame && original.substring(left, right).equals(from)) { // Comparison order
					original = original.substring(0, left) + to + original.substring(right);
					Arrays.fill(curCount, 0);
					left = left + to.length(); // Move
					right = left;
					continue;
				}

				curCount[original.charAt(left) - 'a']--;
				left++;
				curCount[original.charAt(right) - 'a']++;
			}

			right++;
		}

		return original;
	}
}
