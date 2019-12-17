package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.techiedelight.com/shortest-common-supersequence-introduction-scs-length/
 * 
 */
public class ShortestCommonSupersequence {

	/**
	 * Recursive solution. T - (2^(m+n)) in worst case. S - O(1)
	 */
	public int scsLengthRecur(String x, String y, int m, int n) {
		// if end of either seqeunce is reached
		if (m == 0 || n == 0)
			return (m + n);

		// if last char of both sequence match
		if (x.charAt(m - 1) == y.charAt(n - 1))
			return scsLengthRecur(x, y, m - 1, n - 1) + 1;

		return Math.min(scsLengthRecur(x, y, m - 1, n) + 1, scsLengthRecur(x, y, m, n - 1) + 1);
	}

	public int scsLengthDP1(String x, String y, int m, int n) {
		// map to hold pre-computed values
		Map<String, Integer> lookup = new HashMap<String, Integer>();

		return scsLengthDPTopDown(x, y, m, n, lookup);
	}

	/**
	 * 
	 * T - O(mn) S - O(mn)
	 */
	private int scsLengthDPTopDown(String x, String y, int m, int n, Map<String, Integer> lookup) {
		// if end of either seqeunce is reached
		if (m == 0 || n == 0)
			return (m + n);

		String key = m + "|" + n;

		if (!lookup.containsKey(key)) {
			int val = 0;
			if (x.charAt(m - 1) == y.charAt(n - 1)) {
				val = scsLengthDPTopDown(x, y, m - 1, n - 1, lookup) + 1;
			} else {
				val = Math.min(scsLengthDPTopDown(x, y, m - 1, n, lookup) + 1,
						scsLengthDPTopDown(x, y, m, n - 1, lookup) + 1);
			}
			lookup.put(key, val);
		}

		return lookup.get(key);
	}

	/**
	 * T - O(mn) S - O(mn)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int scsLengthDP2(String x, String y) {
		int m = x.length();
		int n = y.length();

		// to hold the computed values
		int[][] lookup = new int[m + 1][n + 1];

		// initialize first row
		for (int i = 0; i <= m; i++) {
			lookup[i][0] = i;
		}

		// initialize first column
		for (int j = 0; j <= n; j++) {
			lookup[0][j] = j;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					lookup[i][j] = lookup[i - 1][j - 1] + 1;
				} else {
					lookup[i][j] = Math.min(lookup[i][j - 1] + 1, lookup[i - 1][j] + 1);
				}
			}
		}

		// scs will be in last element
		return lookup[m][n];
	}

	// define maximum possible length of X and Y
	private int N;

	// lookup[i][j] stores the length of SCS of substring X[0..i-1], Y[0..j-1]
	private static int[][] lookup;

	// Function to fill lookup table by finding length of SCS of
	// sequences X[0..m-1] and Y[0..n-1]
	public void SCSLength(String X, String Y, int m, int n) {
		// first row and first column of the lookup table are already 0

		// initialize first column of the lookup table
		for (int i = 0; i <= m; i++) {
			lookup[i][0] = i;
		}

		// initialize first row of the lookup table
		for (int j = 0; j <= n; j++) {
			lookup[0][j] = j;
		}

		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// if current character of X and Y matches
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					lookup[i][j] = lookup[i - 1][j - 1] + 1;
				}
				// else if current character of X and Y don't match
				else {
					lookup[i][j] = Integer.min(lookup[i - 1][j] + 1, lookup[i][j - 1] + 1);
				}
			}
		}
	}

	// Function to return all SCS of substrings X[0..m-1], Y[0..n-1]
	public List<String> allSCS(String X, String Y, int m, int n) {
		// if we have reached the end of first string, create a list
		// containing second substring and return
		if (m == 0) {
			return Arrays.asList(Y.substring(0, n));
		}

		// if we have reached the end of second string, create a list
		// containing first substring and return
		else if (n == 0) {
			return Arrays.asList(X.substring(0, m));
		}

		// if last character of X and Y is same, it should occur
		// only one time in SSC
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {
			// find all SCS of substring X[0..m-2], Y[0..n-2]
			List<String> scs = allSCS(X, Y, m - 1, n - 1);

			// append current character X[m - 1] or Y[n - 1] to all SCS of
			// substring X[0..m-2] and Y[0..n-2]

			List<String> res = new ArrayList<>();
			for (String str : scs) {
				res.add(str + X.charAt(m - 1));
			}

			return res;
		}

		// we reach here when the last character of X and Y don't match

		// if top cell of current cell has less value than the left cell,
		// then append current character of string X to all SCS of
		// substring X[0..m-2], Y[0..n-1]

		if (lookup[m - 1][n] < lookup[m][n - 1]) {
			List<String> scs = allSCS(X, Y, m - 1, n);

			List<String> res = new ArrayList<>();
			for (String str : scs) {
				res.add(str + X.charAt(m - 1));
			}

			return res;
		}

		// if left cell of current cell has less value than the top cell,
		// then append current character of string Y to all SCS of
		// substring X[0..m-1], Y[0..n-2]

		if (lookup[m][n - 1] < lookup[m - 1][n]) {
			List<String> scs = allSCS(X, Y, m, n - 1);

			List<String> res = new ArrayList<>();
			for (String str : scs) {
				res.add(str + Y.charAt(n - 1));
			}

			return res;
		}

		// if top cell value is same as left cell, then go in both
		// top and left directions

		// append current character of string X to all SCS of
		// substring X[0..m-2], Y[0..n-1]
		List<String> top = allSCS(X, Y, m - 1, n);

		List<String> res = new ArrayList<>();
		for (String str : top) {
			res.add(str + X.charAt(m - 1));
		}

		// append current character of string Y to all SCS of
		// substring X[0..m-1], Y[0..n-2]
		List<String> left = allSCS(X, Y, m, n - 1);
		for (String str : left) {
			res.add(str + Y.charAt(n - 1));
		}

		return res;
	}

	public static void main(String[] args) {
		ShortestCommonSupersequence scs = new ShortestCommonSupersequence();
		String x = "ABCBDAB";
		String y = "BDCABA";
		int m = x.length();
		int n = y.length();
		System.out.println(scs.scsLengthRecur(x, y, m, n));
		System.out.println(scs.scsLengthDP1(x, y, m, n));
		System.out.println(scs.scsLengthDP2(x, y));

		lookup = new int[x.length() + 1][y.length() + 1];
		scs.SCSLength(x, y, m, n);
		System.out.println(scs.allSCS(x, y, m, n));
	}
}
