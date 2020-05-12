package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/find-if-a-string-is-interleaved-of-two-other-strings-dp-33/
 * 
 * https://www.techiedelight.com/find-interleavings-of-given-strings/
 * 
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, Given:
 * 
 * s1 = "aabcc", s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true.
 * 
 * When s3 = "aadbbbaccc", return false.
 *
 */
public class StringInterleavingProblems {

	private List<String> res = new ArrayList<String>();

	public void printInterleavingString(String s1, String s2, String now) {
		if (s1 == null) {
			System.out.println(s2);
			return;
		}
		if (s2 == null) {
			System.out.println(s1);
			return;
		}

		if (s1.length() == 0 && s2.length() == 0) {
			System.out.println(now);
			res.add(now);
			return;
		}

		if (s1.length() > 0) {
			// Pick from S1
			printInterleavingString(s1.substring(1), s2, now + s1.charAt(0));
		}

		if (s2.length() > 0) {
			printInterleavingString(s1, s2.substring(1), now + s2.charAt(0));
		}
	}

	// T - O(m * n)
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		// s1's leading i and s2's leading j can form s3's leading (i + j)?
		boolean[][] hash = new boolean[s1.length() + 1][s2.length() + 1];
		hash[0][0] = true;

		for (int i = 1; i <= s1.length(); i++) {
			if (hash[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1)) {
				hash[i][0] = true;
			}
		}

		for (int i = 1; i <= s2.length(); i++) {
			if (hash[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1)) {
				hash[0][i] = true;
			}
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if ((hash[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1 + j))
						|| (hash[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i - 1 + j))) {
					hash[i][j] = true;
				}
			}
		}

		return hash[s1.length()][s2.length()];
	}

	public static void main(String[] args) {
		StringInterleavingProblems sip = new StringInterleavingProblems();
		sip.printInterleavingString("aabcc", "dbbca", "aadbbcbcac");
		System.out.println(sip.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println(sip.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
	}

}
