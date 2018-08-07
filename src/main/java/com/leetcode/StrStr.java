package com.leetcode;

public class StrStr {

	/**
	 * O(nm) runtime, O(1) space – Brute force
	 * 
	 * @param input
	 * @param pattern
	 * @return
	 */
	public int strStr(String input, String pattern) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == pattern.length())
					return i;
				if (i + j == input.length())
					return -1;
				if (pattern.charAt(j) != input.charAt(i + j))
					break;
			}
		}
	}

	public static void main(String[] args) {
		StrStr str = new StrStr();
		System.out.println(str.strStr("needle in haystack", "needle"));
		System.out.println(str.strStr("needle in haystack", "in"));
		System.out.println(str.strStr("needle in haystack", "needle needle in haystack"));
		System.out.println(str.strStr("needle in haystack", "man"));
		System.out.println(str.strStr("needle in haystack", ""));
		System.out.println(str.strStr("", "needle"));
		System.out.println(str.strStr("", ""));
	}

}
