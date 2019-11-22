package com.algo.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The longest common subsequence (LCS) problem is the problem of finding the longest subsequence that is present in
 * given two sequences in the same order. i.e. find a longest sequence which can be obtained from the first original
 * sequence by deleting some items, and from the second original sequence by deleting other items.
 * 
 * 
 * The problem differs from problem of finding common substrings. Unlike substrings, subsequences are not required to
 * occupy consecutive positions within the original sequences.
 * 
 * For example, consider the two following sequences X and Y X = ABCBDAB Y = BDCABA
 * 
 * The length of LCS is 4. They are BDAB, BCAB, BCBA
 * 
 * Category : Hard
 *
 */
public class LongestCommonSubsequence {

	/**
	 * The LCS problem has an optimal substructure property. . That means the problem can be broken down into smaller,
	 * simple “subproblems”, which can be broken down into yet simpler subproblems, and so on, until, finally, the
	 * solution becomes trivial.
	 * 
	 * time complexity - O(2 ^ (m + n)). worst case happens when there is no common subsequence between X and Y.
	 */
	public int lcs1(String x, String y) {
		return lcs(x, y, x.length(), y.length());
	}

	private int lcs(String x, String y, int xlen, int ylen) {
		// if we reached end of either sequence
		if (xlen == 0 || ylen == 0)
			return 0;

		// if last character of each seq matches
		if (x.charAt(xlen - 1) == y.charAt(ylen - 1))
			return lcs(x, y, xlen - 1, ylen - 1) + 1;

		// if last character does not match
		else
			return Integer.max(lcs(x, y, xlen, ylen - 1), lcs(x, y, xlen - 1, ylen));
	}

	/**
	 * Overlapping subproblem. T - O(mn) S - O(mn).
	 * 
	 * This memoized version follows a top-down approach since we break the problem into sub-problems and then calculate
	 * and store values.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int lcs2(String x, String y) {
		int xl = x.length();
		int yl = y.length();

		Map<String, Integer> map = new HashMap<>();

		return lcs(x, y, xl, yl, map);
	}

	private int lcs(String x, String y, int xl, int yl, Map<String, Integer> map) {
		// base case
		if (xl == 0 || yl == 0)
			return 0;

		String key = xl + "|" + yl;
		if (!map.containsKey(key)) {
			if (x.charAt(xl - 1) == y.charAt(yl - 1)) {
				map.put(key, lcs(x, y, xl - 1, yl - 1, map) + 1);
			} else {
				map.put(key, Integer.max(lcs(x, y, xl - 1, yl, map), lcs(x, y, xl, yl - 1, map)));
			}
		}
		return map.get(key);
	}

	/**
	 * Bottom up approach. Calculate small values of lcs(i,j) then build large values from them.
	 * 
	 * T - O(mn) S - O(mn).
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int lcs3(String x, String y) {
		int m = x.length();
		int n = y.length();

		int[][] arr = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					arr[i][j] = 0;
				} else if (x.charAt(i - 1) == y.charAt(j - 1)) {
					arr[i][j] = arr[i - 1][j - 1] + 1;
				} else {
					arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j]);
				}
			}
		}

		return arr[m][n];
	}

	/**
	 * If only the length of the LCS is required, the space complexity of the solution can be improved up to O(min(m,n))
	 * as we’re reading only from previous row to current row.
	 * 
	 * T - O(mn) S - O(2n)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int lcs_optimized(String x, String y) {
		int m = x.length();
		int n = y.length();

		int[] curr = new int[n + 1];
		int[] prev = new int[n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i > 0 && j > 0) {
					if (x.charAt(i - 1) == y.charAt(j - 1)) {
						curr[j] = prev[j - 1] + 1;
					} else {
						curr[j] = Integer.max(prev[j], curr[j - 1]);
					}
				}
			}

			// replace contents of prev arr with curr arr
			System.arraycopy(curr, 0, prev, 0, n + 1);
		}

		return curr[n];
	}

	/**
	 * Function to find length of Longest Common Subsequence of sequences X[0..m-1], Y[0..n-1] and Z[0..o-1]
	 * 
	 * T - O(mno) S - O(mno)
	 * 
	 * @param X
	 * @param Y
	 * @param Z
	 * @return
	 */
	public int LCSLength(String X, String Y, String Z) {
		int m = X.length(), n = Y.length(), o = Z.length();

		// lookup table stores solution to already computed sub-problems
		// i.e. T[i][j][k] stores the length of LCS of substring
		// X[0..i-1], Y[0..j-1], Z[0..k-1]
		int[][][] T = new int[m + 1][n + 1][o + 1];

		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= o; k++) {
					// if current character of X, Y and Z matches
					if (X.charAt(i - 1) == Y.charAt(j - 1) && Y.charAt(j - 1) == Z.charAt(k - 1)) {
						T[i][j][k] = T[i - 1][j - 1][k - 1] + 1;
					}
					// else if current character of X, Y and Z don't match
					else {
						T[i][j][k] = Math.max(T[i - 1][j][k], Math.max(T[i][j - 1][k], T[i][j][k - 1]));
					}
				}
			}
		}

		// LCS will be last entry in the lookup table
		return T[m][n][o];
	}

	/**
	 * https://www.techiedelight.com/longest-common-subsequence-finding-lcs/
	 * 
	 * Function to find LCS of String X[0..m-1] and Y[0..n-1]
	 * 
	 * @param X
	 * @param Y
	 * @param m
	 * @param n
	 * @param T
	 * @return
	 */
	public String LCS(String X, String Y, int m, int n, int[][] T) {
		// return empty string if we have reached the end of
		// either sequence
		if (m == 0 || n == 0) {
			return new String();
		}

		// if last character of X and Y matches
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {
			// append current character (X[m-1] or Y[n-1]) to LCS of
			// substring X[0..m-2] and Y[0..n-2]
			return LCS(X, Y, m - 1, n - 1, T) + X.charAt(m - 1);
		}

		// else when the last character of X and Y are different

		// if top cell of current cell has more value than the left
		// cell, then drop current character of String X and find LCS
		// of substring X[0..m-2], Y[0..n-1]

		if (T[m - 1][n] > T[m][n - 1]) {
			return LCS(X, Y, m - 1, n, T);
		} else {
			// if left cell of current cell has more value than the top
			// cell, then drop current character of String Y and find LCS
			// of substring X[0..m-1], Y[0..n-2]

			return LCS(X, Y, m, n - 1, T);
		}
	}

	// Function to fill lookup table by finding the length of LCS
	// of substring X[0..m-1] and Y[0..n-1]
	public void LCSLength(String X, String Y, int m, int n, int[][] T) {
		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// if current character of X and Y matches
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					T[i][j] = T[i - 1][j - 1] + 1;
				}
				// else if current character of X and Y don't match
				else {
					T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
				}
			}
		}
	}

	/**
	 * Function to return all LCS of sub-strings X[0..m-1], Y[0..n-1]
	 * 
	 * @param X
	 * @param Y
	 * @param m
	 * @param n
	 * @param T
	 * @return
	 */
	public static List<String> LCSList(String X, String Y, int m, int n, int[][] T) {
		// if we have reached the end of either sequence
		if (m == 0 || n == 0) {
			// create a List with 1 empty string and return
			return new ArrayList<>(Collections.nCopies(1, ""));
		}

		// if last character of X and Y matches
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {
			// ignore last characters of X and Y and find all LCS of
			// substring X[0..m-2], Y[0..n-2] and store it in a List
			List<String> lcs = LCSList(X, Y, m - 1, n - 1, T);

			// append current character X[m - 1] or Y[n - 1]
			// to all LCS of substring X[0..m-2] and Y[0..n-2]
			for (int i = 0; i < lcs.size(); i++) {
				lcs.set(i, lcs.get(i) + (X.charAt(m - 1)));
			}

			return lcs;
		}

		// we reach here when the last character of X and Y don't match

		// if top cell of current cell has more value than the left cell,
		// then ignore current character of String X and find all LCS of
		// substring X[0..m-2], Y[0..n-1]
		if (T[m - 1][n] > T[m][n - 1])
			return LCSList(X, Y, m - 1, n, T);

		// if left cell of current cell has more value than the top cell,
		// then ignore current character of String Y and find all LCS of
		// substring X[0..m-1], Y[0..n-2]
		if (T[m][n - 1] > T[m - 1][n])
			return LCSList(X, Y, m, n - 1, T);

		// if top cell has equal value to the left cell,
		// then consider both character

		List<String> top = LCSList(X, Y, m - 1, n, T);
		List<String> left = LCSList(X, Y, m, n - 1, T);

		// merge two Lists and return
		top.addAll(left);

		return top;
	}

	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		System.out.println(lcs.lcs1("ABCBDAB", "BDCABA"));
		System.out.println(lcs.lcs2("ABCBDAB", "BDCABA"));
		System.out.println(lcs.lcs3("ABCBDAB", "BDCABA"));
		System.out.println(lcs.lcs_optimized("ABCBDAB", "BDCABA"));
	}

}
