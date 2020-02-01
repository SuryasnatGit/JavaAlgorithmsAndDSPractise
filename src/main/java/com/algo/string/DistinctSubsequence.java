package com.algo.string;

/**
 * https://leetcode.com/problems/distinct-subsequences/
 * 
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of
 * "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 * 
 * Category : Hard
 */
public class DistinctSubsequence {

	public int numDistinct(String s, String t) {
		int m = s.length();
		int n = t.length();
		int[][] hash = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			hash[i][0] = 1;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				hash[i][j] = hash[i - 1][j];
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					hash[i][j] += hash[i - 1][j - 1]; //
				}
			}
		}

		return hash[m][n];
	}

	public static void main(String[] args) {
		DistinctSubsequence ds = new DistinctSubsequence();
		System.out.println(ds.numDistinct("rabbbit", "rabbit"));
	}
}
