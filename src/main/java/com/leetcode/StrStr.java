package com.leetcode;

public class StrStr {

	/**
	 * Brute force. T - O(nm) , S - O(1) .
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

	/**
	 * Using KMP algorithm. T - O(n) in worst case.
	 * 
	 * The KMP matching algorithm uses degenerating property (pattern having same sub-patterns appearing more than once
	 * in the pattern) of the pattern and improves the worst case complexity to O(n). The basic idea behind KMP’s
	 * algorithm is: whenever we detect a mismatch (after some matches), we already know some of the characters in the
	 * text of the next window. We take advantage of this information to avoid matching the characters that we know will
	 * anyway match. Let us consider below example to understand this.
	 * 
	 * Matching Overview:
	 * 
	 * txt = "AAAAABAAABA"
	 * 
	 * pat = "AAAA"
	 * 
	 * We compare first window of txt with pat
	 * 
	 * txt = "AAAAABAAABA"
	 * 
	 * pat = "AAAA" [Initial position]
	 * 
	 * We find a match. This is same as Naive String Matching.
	 * 
	 * In the next step, we compare next window of txt with pat.
	 * 
	 * txt = "AAAAABAAABA"
	 * 
	 * pat = "AAAA" [Pattern shifted one position]
	 * 
	 * This is where KMP does optimization over Naive. In this second window, we only compare fourth A of pattern with
	 * fourth character of current window of text to decide whether current window matches or not. Since we know first
	 * three characters will anyway match, we skipped matching first three characters.
	 * 
	 * Need of Preprocessing?
	 * 
	 * An important question arises from the above explanation, how to know how many characters to be skipped. To know
	 * this, we pre-process pattern and prepare an integer array lps[] that tells us the count of characters to be
	 * skipped
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public int strStr2(String source, String pattern) {
		int[] arr = getKMP(pattern);

		int pos1 = 0;
		int pos2 = 0;
		while (pos1 < source.length() && pos2 < pattern.length()) {
			if (source.charAt(pos1) == pattern.charAt(pos2)) {
				pos1++;
				pos2++;
			} else {
				if (pos2 == 0) {
					pos1++;
				} else {
					pos2 = arr[pos2 - 1];
				}
			}
		}

		return pos2 == pattern.length() ? pos1 - pos2 : -1;
	}

	private int[] getKMP(String pattern) {
		int[] arr = new int[pattern.length()];
		int pos = 0;

		for (int i = 1; i < pattern.length();) {
			if (pattern.charAt(i) == pattern.charAt(pos)) {
				arr[i] = pos + 1; // Plus One!
				pos++;
				i++;
			} else {
				if (pos == 0) {
					arr[i] = 0;
					i++;
				} else {
					pos = arr[pos - 1];
				}
			}
		}

		return arr;
	}

	public static void main(String[] args) {
		StrStr str = new StrStr();
		System.out.println(str.strStr("needle in haystack", "needle"));
		System.out.println(str.strStr("needle in haystack", "in"));
		System.out.println(str.strStr2("needle in haystack", "in"));
		System.out.println(str.strStr("needle in haystack", "needle needle in haystack"));
		System.out.println(str.strStr("needle in haystack", "man"));
		System.out.println(str.strStr("needle in haystack", ""));
		System.out.println(str.strStr("", "needle"));
		System.out.println(str.strStr("", ""));
	}

}
