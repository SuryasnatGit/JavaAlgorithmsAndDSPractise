package com.algo.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * The Longest Palindromic Subsequence (LPS) problem is the problem of finding the longest subsequences of a string that
 * is also a palindrome.
 * 
 * For example, Consider the sequence ABBDCACB . The length of Longest Palindromic Subsequence is 5. The Longest
 * Palindromic Subsequence is BCACB
 * 
 * https://www.techiedelight.com/longest-palindromic-subsequence-using-dynamic-programming/
 * 
 */
public class LongestPalindromicSubsequence {

	/**
	 * Worst case time complexity - O(2^n) Space - O(1)
	 * 
	 * @param s
	 * @param i
	 * @param j
	 * @return
	 */
	public int lps(String s, int i, int j) {
		// base
		if (i > j)
			return 0;

		// if s is single char
		if (i == j)
			return 1;

		// if last and first chars match
		if (s.charAt(i) == s.charAt(j)) {
			// increment the lps length and check the next boundry chars
			return lps(s, i + 1, j - 1) + 2;
		} else {
			// return the max of lps of s minus first char and s minus last char and recur for the remaining substring.
			return Integer.max(lps(s, i + 1, j), lps(s, i, j - 1));
		}
	}

	/**
	 * this problem displays optimal substructure and overlapping subproblem property. time - O(n^2) S - O(n^2)
	 * 
	 * @param s
	 * @param i
	 * @param j
	 * @param map
	 * @return
	 */
	public int lps_dynamic(String s, int i, int j, Map<String, Integer> map) {
		// base
		if (i > j)
			return 0;

		if (i == j)
			return 1;

		String key = i + "|" + j;

		if (!map.containsKey(key)) {
			if (s.charAt(i) == s.charAt(j)) {
				map.put(key, lps_dynamic(s, i + 1, j - 1, map) + 2);
			} else {
				map.put(key, Math.max(lps_dynamic(s, i, j - 1, map), lps_dynamic(s, i + 1, j, map)));
			}
		}

		return map.get(key);
	}

	/**
	 * 
	 * Idea is to find longest common subsequence of given string with its reverse and the longest common subsequence
	 * will be the longest palindrom subsequence.
	 */
	// Function to find LCS of String X[0..m-1] and Y[0..n-1]
	public String printLongestPalindrome(String X, String Y, int m, int n, int[][] T) {
		// return empty string if we have reached the end of
		// either sequence
		if (m == 0 || n == 0) {
			return "";
		}

		// if last character of X and Y matches
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {
			// append current character (X[m-1] or Y[n-1]) to LCS of
			// substring X[0..m-2] and Y[0..n-2]
			return printLongestPalindrome(X, Y, m - 1, n - 1, T) + X.charAt(m - 1);
		}

		// else when the last character of X and Y are different

		// if top cell of current cell has more value than the left
		// cell, then drop current character of String X and find LCS
		// of substring X[0..m-2], Y[0..n-1]

		if (T[m - 1][n] > T[m][n - 1]) {
			return printLongestPalindrome(X, Y, m - 1, n, T);
		}

		// if left cell of current cell has more value than the top
		// cell, then drop current character of String Y and find LCS
		// of substring X[0..m-1], Y[0..n-2]

		return printLongestPalindrome(X, Y, m, n - 1, T);
	}

	public static void main(String[] args) {
		LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
		String s = "ABBDCACB";
		System.out.println(lps.lps(s, 0, s.length() - 1));

		// map to contain solutions to sub problems
		Map<String, Integer> map = new HashMap<String, Integer>();
		System.out.println(lps.lps_dynamic(s, 0, s.length() - 1, map));
	}

}
