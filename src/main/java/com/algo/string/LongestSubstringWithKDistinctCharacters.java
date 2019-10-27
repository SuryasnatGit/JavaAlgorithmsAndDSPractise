package com.algo.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * 
 * Example:
 * 
 * "aabbcc", k = 1 Max substring can be any one from {"aa" , "bb" , "cc"}.
 * 
 * "aabbcc", k = 2 Max substring can be any one from {"aabb" , "bbcc"}.
 * 
 * "aabbcc", k = 3 There are substrings with exactly 3 unique characters {"aabbcc" , "abbcc" , "aabbc" , "abbc" } Max is
 * "aabbcc" with length 6.
 * 
 * "aaabbb", k = 3 There are only two unique characters, thus show error message.
 *
 * Time complexity O(n) Space complexity O(n)
 *
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * 
 * Category : Hard
 * 
 */
public class LongestSubstringWithKDistinctCharacters {

	// T - O(n) S - O(1) constant
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (k == 0 || s.length() == 0) {
			return 0;
		}
		int[] ascii = new int[256];// for ascii 256 characters which includes standard(0-127) and extended(128-255)
		int count = 0; // count of unique chars
		int start = 0; // start of sliding window
		int max = 0; // max length
		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			if (count < k) {
				if (ascii[ch] == 0) {
					count++;
				}
			} else if (ascii[ch] == 0) {
				while (start < i) { // backtrack
					char ch1 = s.charAt(start++);
					ascii[ch1]--;
					if (ascii[ch1] == 0) {
						break;
					}
				}
			}
			ascii[ch]++;
			max = Math.max(max, i - start + 1);
		}
		return max;
	}

	public int lengthOfLongestSubstringKDistinctUsingMap(String s, int k) {
		if (k == 0 || s.length() == 0) {
			return 0;
		}
		Map<Character, Integer> countMap = new HashMap<>();
		int max = 0;
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			// if countmap does not contain ch and size of countmap is greater than k
			if (!countMap.containsKey(ch) && countMap.size() >= k) {
				while (start < i) {
					countMap.compute(s.charAt(start), (key, val) -> {
						if (val == 1) {
							return null;
						} else {
							return val - 1;
						}
					});
					start++; // increment start
					if (countMap.size() < k) {
						break;
					}
				}
			}
			// updates countmap with incremented count of the char
			countMap.compute(ch, (key, val) -> {
				if (val == null) {
					return 1;
				} else {
					return val + 1;
				}
			});
			max = Math.max(max, i - start + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		LongestSubstringWithKDistinctCharacters lo = new LongestSubstringWithKDistinctCharacters();
		System.out.println(lo.lengthOfLongestSubstringKDistinct("aabbcc", 2));
		System.out.println(lo.lengthOfLongestSubstringKDistinctUsingMap("aabbcc", 2));

	}
}
