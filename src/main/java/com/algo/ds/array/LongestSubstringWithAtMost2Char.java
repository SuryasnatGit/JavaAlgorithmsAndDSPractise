package com.algo.ds.array;

/**
 * Longest Substring with At Most Two Distinct Characters
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/.
 * 
 * Similar to http://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
 */
public class LongestSubstringWithAtMost2Char {

	/**
	 * If the length of string is n, then there can be n*(n+1)/2 possible substrings. A simple way is to generate all
	 * the substring and check each one whether it has exactly k unique characters or not. If we apply this brute force,
	 * it would take O(n2) to generate all substrings and O(n) to do a check on each one. Thus overall it would go
	 * O(n3).
	 * 
	 * @param input
	 */
	public void bruteForce(String input) {

	}

	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int count1 = 0;
		int count2 = 0;
		char c1 = 0;
		char c2 = 0;
		int start = 0;
		int current = 0;
		int max = 0;
		for (char ch : s.toCharArray()) {
			if (ch == c1 || ch == c2) {
				if (ch == c1) {
					count1++;
				} else {
					count2++;
				}
			} else {
				if (count1 != 0 && count2 != 0) {
					while (start < current) {
						if (s.charAt(start) == c1) {
							count1--;
						} else if (s.charAt(start) == c2) {
							count2--;
						}
						start++;
						if (count1 == 0 || count2 == 0) {
							break;
						}
					}
				}
				if (count1 == 0) {
					c1 = ch;
					count1 = 1;
				} else {
					c2 = ch;
					count2 = 1;
				}
			}
			max = Math.max(max, current - start + 1);
			current++;
		}
		return max;
	}

	public static void main(String args[]) {
		LongestSubstringWithAtMost2Char lc = new LongestSubstringWithAtMost2Char();
		System.out.print(lc.lengthOfLongestSubstringTwoDistinct("eceba"));
	}
}