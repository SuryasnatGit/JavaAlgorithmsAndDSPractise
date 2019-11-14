package com.algo.dp;

/**
 * Given two strings, write a function that returns the longest common substring.
 * 
 * eg.
 * 
 * longestSubstring("ABAB", "BABA") = "ABA"
 *
 * https://algorithms.tutorialhorizon.com/dynamic-programming-longest-common-substring/
 * 
 * Complexity - Medium
 */
public class LongestCommonSubstring {

	/*
	 * Approach 1 - Naive Approach
	 * 
	 * Check all the substrings from first string with second string anxd keep track of the maximum.
	 * 
	 * Time Complexity: O(n2*m), O(n2) for the substring and O(m) for check all the substrings with second string
	 * 
	 */

	/*
	 * Approach 2 - Using DP.
	 * 
	 * we will solve this problem in bottom-up manner. Create a matrix of size of m*n and store the solutions of
	 * substrings to use them later.
	 * 
	 * Base Cases: If any of the string is null then LCS will be 0.
	 * 
	 * Check if ith character in one string A is equal to jth character in string B
	 * 
	 * Case 1: both characters are same
	 * 
	 * LCS[i][j] = 1 + LCS[i-1][j-1] (add 1 to the result and remove the last character from both the strings and check
	 * the result for the smaller string.)
	 * 
	 * Case 2: both characters are not same.
	 * 
	 * LCS[i][j] = 0
	 * 
	 * At the end, traverse the matrix and find the maximum element in it, This will the length of Longest Common
	 * Substring.
	 * 
	 * T - O(n^2) S - O(n^2).
	 * 
	 * Improvement -Only non zero values can be stored in the rows. This can be done using hash map instead of array.
	 * Also time complexity can be improved to O(m + n) using generalized suffix tree.
	 */
	public String longestSubstring(String a, String b) {
		String out = "";
		if (a.length() == 0 || b.length() == 0)
			return out;

		int len = 0;
		int[][] cache = new int[a.length()][b.length()];

		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < b.length(); j++) {
				if (a.charAt(i) == b.charAt(j)) {
					if (i == 0 || j == 0) {
						cache[i][j] = 1;
					} else {
						cache[i][j] = cache[i - 1][j - 1] + 1;
					}
					if (cache[i][j] > len) {
						len = cache[i][j];
						out = a.substring(i - len + 1, i + 1);
					}
				}
			}
		}
		return out;
	}

	public static void main(String[] args) {
		LongestCommonSubstring lcs = new LongestCommonSubstring();
		System.out.println(lcs.longestSubstring("plea", "apple"));
	}

}
