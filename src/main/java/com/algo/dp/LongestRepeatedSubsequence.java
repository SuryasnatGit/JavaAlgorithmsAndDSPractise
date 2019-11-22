package com.algo.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.techiedelight.com/longest-repeated-subsequence-problem/
 * 
 * The longest repeated subsequence (LRS) problem is the problem of finding the longest subsequences of a string that
 * occurs at least twice. The problem differs from problem of finding common substrings. Unlike substrings, subsequences
 * are not required to occupy consecutive positions within the original sequences.
 * 
 * For example, consider the sequence ATACTCGGA
 * 
 * Length of Longest Repeating Subsequence is 4. Longest Repeating Subsequence is ATCG
 * 
 * Note that repeated characters holds different index in the input string.
 * 
 * It is a classic variation of Longest Common Subseqeunce problem. problem. The idea is to find LCS of given string
 * with itself i.e. call LCS(X, X) and excluding the cases when index are same (i = j) as repeated characters holds
 * different index in the input string.
 *
 */
public class LongestRepeatedSubsequence {

	/**
	 * Worst case time - O(2^n) S - O(1). worst case happens when there are no repeating chars.
	 * 
	 * @param s
	 * @param i
	 * @param j
	 * @return
	 */
	public int lrs_recur(String s, int i, int j) {
		// base
		if (i == 0 || j == 0)
			return 0;

		if ((s.charAt(i - 1) == s.charAt(j - 1)) && i != j) {
			return lrs_recur(s, i - 1, j - 1) + 1;
		} else {
			return Math.max(lrs_recur(s, i, j - 1), lrs_recur(s, i - 1, j));
		}
	}

	/**
	 * T - O(n^2) S - O(n^2)
	 * 
	 * @param s
	 * @param i
	 * @param j
	 * @param lookup
	 * @return
	 */
	public int lrs_dp(String s, int i, int j, Map<String, Integer> lookup) {
		// base
		if (i == 0 || j == 0)
			return 0;

		String key = i + "|" + j;
		if (!lookup.containsKey(key)) {
			if ((s.charAt(i - 1) == s.charAt(j - 1)) && i != j) {
				lookup.put(key, lrs_dp(s, i - 1, j - 1, lookup) + 1);
			} else {
				lookup.put(key, Math.max(lrs_dp(s, i - 1, j, lookup), lrs_dp(s, i, j - 1, lookup)));
			}
		}

		return lookup.get(key);
	}

	// To print the LRS.
	// Function to find LRS of substrings X[0..m-1], X[0..n-1]
	public static String LRS(String X, int m, int n, int[][] T) {
		// if we have reached the end of either sequence
		// return empty string
		if (m == 0 || n == 0) {
			return new String("");
		}

		// if characters at index m and n matches and index is different
		if (X.charAt(m - 1) == X.charAt(n - 1) && m != n) {
			return LRS(X, m - 1, n - 1, T) + X.charAt(m - 1);
		} else
		// else if characters at index m and n don't match
		{
			if (T[m - 1][n] > T[m][n - 1]) {
				return LRS(X, m - 1, n, T);
			} else {
				return LRS(X, m, n - 1, T);
			}
		}
	}

	// Function to fill lookup table by finding the length of LRS
	// of substring X[0..n-1]
	public static void LRSLength(String X, int[][] T) {
		// first row and first column of the lookup table
		// are already 0 as T[][] is globally declared

		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= X.length(); i++) {
			for (int j = 1; j <= X.length(); j++) {
				// if characters at index i and j matches
				// and the index is different
				if (X.charAt(i - 1) == X.charAt(j - 1) && i != j) {
					T[i][j] = T[i - 1][j - 1] + 1;
				}
				// else if characters at index i and j are different
				else {
					T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
				}
			}
		}

		// Uncomment below code to print the lookup table
		/*
		 * for (int i = 0; i <= X.length(); i++) { System.out.println(Arrays.toString(T[i])); }
		 */
	}

	public static void main(String[] args) {
		LongestRepeatedSubsequence lrs = new LongestRepeatedSubsequence();
		String s = "ATACTCGGA";
		int l = s.length();
		System.out.println(lrs.lrs_recur(s, l, l));

		Map<String, Integer> lookup = new HashMap<String, Integer>();
		System.out.println(lrs.lrs_dp(s, l, l, lookup));

		// T[i][j] stores the length of LRS of substring X[0..i-1], X[0..j-1]
		int[][] T = new int[l + 1][l + 1];

		// fill lookup table
		LRSLength(s, T);

		// find Longest Repeating Subsequence
		System.out.print(LRS(s, l, l, T));
	}

}
