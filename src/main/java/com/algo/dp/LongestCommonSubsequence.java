package com.algo.string;

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

	// The LCS problem has an optimal substructure property. . That means the problem can be broken down into smaller,
	// simple “subproblems”, which can be broken down into yet simpler subproblems, and so on, until, finally, the
	// solution becomes trivial.
	public int lcs(String x, String y) {
		return lcs(x, y, x.length(), y.length());
	}

	private int lcs(String x, String y, int xlen, int ylen) {
		// if we reached end of either sequence
		if (xlen == 0 || ylen == 0)
			return 0;

		// if last character of each seq matches
		if (x.charAt(xlen - 1) == y.charAt(ylen) - 1)
			return lcs(x, y, xlen - 1, ylen - 1);

		// if last character does not match
		else
			return Integer.max(lcs(x, y, xlen, ylen - 1), lcs(x, y, xlen - 1, ylen));
	}

}
