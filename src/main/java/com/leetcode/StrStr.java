package com.leetcode;

/**
 * Return the index of the first occurrence of needle in haystack, or-1if needle is not part of haystack.
 * 
 * Example 1:
 * 
 * Input: haystack = "hello", needle = "ll"
 * 
 * Output: 2
 * 
 * Example 2:
 * 
 * Input: haystack = "aaaaa", needle = "bba"
 * 
 * Output: -1
 * 
 * Category : Hard
 *
 */
public class StrStr {

	/*
	 * Using subString()
	 */
	public int strStr1(String haystack, String needle) {
		if (needle.equals("")) {
			return 0;
		}
		int needleLength = needle.length();
		for (int i = 0; i <= haystack.length() - needleLength; i++) {
			if (haystack.substring(i, i + needleLength).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Brute force. T - O(nm) , S - O(1) .
	 * 
	 * @param input
	 * @param pattern
	 * @return
	 */
	public int strStrNaive(String input, String pattern) {
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
	 * KMP algorithm preprocesses pat[] and constructs an auxiliary lps[] of size m (same as size of pattern) which is
	 * used to skip characters while matching.
	 * 
	 * name lps indicates longest proper prefix which is also suffix.. A proper prefix is prefix with whole string not
	 * allowed. For example, prefixes of “ABC” are “”, “A”, “AB” and “ABC”. Proper prefixes are “”, “A” and “AB”.
	 * Suffixes of the string are “”, “C”, “BC” and “ABC”.
	 * 
	 * We search for lps in sub-patterns. More clearly we focus on sub-strings of patterns that are either prefix and
	 * suffix.
	 * 
	 * For each sub-pattern pat[0..i] where i = 0 to m-1, lps[i] stores length of the maximum matching proper prefix
	 * which is also a suffix of the sub-pattern pat[0..i].
	 * 
	 * lps[i] = the longest proper prefix of pat[0..i] which is also a suffix of pat[0..i].
	 * 
	 * Note : lps[i] could also be defined as longest prefix which is also proper suffix. We need to use properly at one
	 * place to make sure that the whole substring is not considered.
	 * 
	 * 
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public int strStrKMP(String source, String pattern) {
		int[] lps = computeLPSArray(pattern);

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
					pos2 = lps[pos2 - 1];
				}
			}
		}

		return pos2 == pattern.length() ? pos1 - pos2 : -1;
	}

	private int[] computeLPSArray(String pattern) {
		int[] lps = new int[pattern.length()];
		int pos = 0;

		// index starts from 1 because first element of lps is always 0
		for (int i = 1; i < pattern.length();) {
			if (pattern.charAt(i) == pattern.charAt(pos)) {
				lps[i] = pos + 1; // Plus One!
				pos++;
				i++;
			} else {
				if (pos == 0) {
					lps[i] = 0;
					i++;
				} else {
					pos = lps[pos - 1];
				}
			}
		}

		return lps;
	}

	public static void main(String[] args) {
		StrStr str = new StrStr();
		System.out.println("Usng naive approach..");
		System.out.println(str.strStrNaive("needle in haystack", "needle"));
		System.out.println(str.strStrNaive("needle in haystack", "in"));
		System.out.println(str.strStrNaive("needle in haystack", "needle needle in haystack"));
		System.out.println(str.strStrNaive("needle in haystack", "man"));
		System.out.println(str.strStrNaive("needle in haystack", ""));
		System.out.println(str.strStrNaive("", "needle"));
		System.out.println(str.strStrNaive("", ""));
		System.out.println("Using kmp approach..");
		System.out.println(str.strStrKMP("needle in haystack", "needle"));
		System.out.println(str.strStrKMP("needle in haystack", "in"));
		System.out.println(str.strStrKMP("needle in haystack", "needle needle in haystack"));
		System.out.println(str.strStrKMP("needle in haystack", "man"));
		System.out.println(str.strStrKMP("needle in haystack", ""));
		System.out.println(str.strStrKMP("", "needle"));
		System.out.println(str.strStrKMP("", ""));
		System.out.println("Using substring() approach..");
		System.out.println(str.strStr1("needle in haystack", "needle"));
		System.out.println(str.strStr1("needle in haystack", "in"));
		System.out.println(str.strStr1("needle in haystack", "needle needle in haystack"));
		System.out.println(str.strStr1("needle in haystack", "man"));
		System.out.println(str.strStr1("needle in haystack", ""));
	}

}
