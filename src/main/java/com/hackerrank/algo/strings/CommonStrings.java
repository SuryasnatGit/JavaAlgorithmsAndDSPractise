package com.hackerrank.algo.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * A string is said to be a child of a another string if it can be formed by deleting 0 or more
 * characters from the other string. Given two strings of equal length, what's the longest string
 * that can be constructed such that it is a child of both?
 * 
 * For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD. They can be
 * formed by eliminating either the D or C from both strings. Note that we will not consider ABCD as
 * a common child because we can't rearrange characters and ABCD ABDC.
 * 
 * Function Description
 * 
 * Complete the commonChild function in the editor below. It should return the longest string which
 * is a common child of the input strings.
 * 
 * commonChild has the following parameter(s):
 * 
 * s1, s2: two equal length strings Input Format
 * 
 * There is one line with two space-separated strings, and .
 * 
 * Constraints
 * 
 * All characters are upper case in the range ascii[A-Z]. Output Format
 * 
 * Print the length of the longest string , such that is a child of both and .
 * 
 * Sample Input
 * 
 * HARRY SALLY Sample Output
 * 
 * 2 Explanation
 * 
 * The longest string that can be formed by deleting zero or more characters from and is , whose
 * length is 2.
 * 
 * Sample Input 1
 * 
 * AA BB Sample Output 1
 * 
 * 0 Explanation 1
 * 
 * and have no characters in common and hence the output is 0.
 * 
 * Sample Input 2
 * 
 * SHINCHAN NOHARAAA Sample Output 2
 * 
 * 3 Explanation 2
 * 
 * The longest string that can be formed between and while maintaining the order is .
 * 
 * Sample Input 3
 * 
 * ABCDEF FBDAMN Sample Output 3
 * 
 * 2 Explanation 3 is the longest child of the given strings.
 * 
 * This is a Longest Common Subsequence problem.
 * 
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 * 
 * Category - Hard
 * 
 * @author M_402201
 *
 */
public class CommonStrings {

	/**
	 * In order to find out the complexity of brute force approach, we need to first know the number of
	 * possible different subsequences of a string with length n, i.e., find the number of subsequences
	 * with lengths ranging from 1,2,..n-1. Recall from theory of permutation and combination that
	 * number of combinations with 1 element are nC1. Number of combinations with 2 elements are nC2 and
	 * so forth and so on. We know that nC0 + nC1 + nC2 + … nCn = 2n. So a string of length n has 2n-1
	 * different possible subsequences since we do not consider the subsequence with length 0. This
	 * implies that the time complexity of the brute force approach will be O(n * 2n). Note that it
	 * takes O(n) time to check if a subsequence is common to both the strings. This time complexity can
	 * be improved using dynamic programming.
	 * 
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int commonChild_naive(String s1, String s2) {

		// for testing
		List<Character> list = new ArrayList<Character>();

		int size1 = 0;
		for (int index1 = 0; index1 < s1.length(); index1++) {
			char char1 = s1.charAt(index1);
			for (int index2 = index1; index2 < s2.length(); index2++) {
				char char2 = s2.charAt(index2);
				if (char1 == char2 && index2 >= index1) {
					// found result
					size1++;
					list.add(char1);
					break;
				}
			}
		}
		System.out.println(list);
		list.clear();

		// other way
		int size2 = 0;
		for (int index2 = 0; index2 < s2.length(); index2++) {
			char char2 = s2.charAt(index2);
			for (int index1 = index2; index1 < s1.length(); index1++) {
				char char1 = s1.charAt(index1);
				if (char1 == char2 && index1 >= index2) {
					// found result
					size2++;
					list.add(char1);
					break;
				}
			}
		}
		System.out.println(list);

		return Math.max(size1, size2);
	}

	/**
	 * Time complexity of the above naive recursive approach is O(2^n) in worst case and worst case
	 * happens when all characters of X and Y mismatch i.e., length of LCS is 0.
	 * 
	 * this problem has Overlapping Substructure property and recomputation of same subproblems can be
	 * avoided by either using Memoization or Tabulation.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int commonChild_recursive(String a, String b) {
		int m = a.length();
		int n = b.length();
		char[] arra = a.toCharArray();
		char[] arrb = b.toCharArray();
		return commonChild_recursiveUtil(arra, arrb, m, n);
	}

	private int commonChild_recursiveUtil(char[] arra, char[] arrb, int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		else if (arra[m - 1] == arrb[n - 1])
			return 1 + commonChild_recursiveUtil(arra, arrb, m - 1, n - 1);
		else
			return Math.max(commonChild_recursiveUtil(arra, arrb, m - 1, n),
					commonChild_recursiveUtil(arra, arrb, m, n - 1));
	}

	/**
	 * DP - using tabulation. complexity - O(m * n)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int commonChild_tabulation(String a, String b) {
		int m = a.length();
		int n = b.length();
		char[] arra = a.toCharArray();
		char[] arrb = b.toCharArray();

		int[][] table = new int[m + 1][n + 1];

		// following builds table[][] in bottom up fashion
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else if (arra[i - 1] == arrb[j - 1]) {
					table[i][j] = 1 + table[i - 1][j - 1];
				} else {
					table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
				}
			}
		}

		return table[m][n];
	}

	static final int maximum = 1000;

	/**
	 * DP - using memoization
	 * 
	 * Time Complexity: O(N * M), where N and M is length of the first and second string respectively.
	 * Auxiliary Space: (N * M)
	 * 
	 * @return
	 */
	// Returns length of LCS for X[0..m-1], Y[0..n-1] memoization applied in recursive solution
	static int lcs(String X, String Y, int m, int n, int dp[][]) {
		// base case
		if (m == 0 || n == 0) {
			return 0;
		}

		// if the same state has already been
		// computed
		if (dp[m - 1][n - 1] != -1) {
			return dp[m - 1][n - 1];
		}

		// if equal, then we store the value of the
		// function call
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {

			// store it in arr to avoid further repetitive
			// work in future function calls
			dp[m - 1][n - 1] = 1 + lcs(X, Y, m - 1, n - 1, dp);

			return dp[m - 1][n - 1];
		} else {

			// store it in arr to avoid further repetitive
			// work in future function calls
			dp[m - 1][n - 1] = Math.max(lcs(X, Y, m, n - 1, dp), lcs(X, Y, m - 1, n, dp));

			return dp[m - 1][n - 1];
		}
	}

	public static void main(String[] args) {
		CommonStrings cs = new CommonStrings();
		System.out.println(cs.commonChild_naive("ABCAD", "ABDCA"));
		System.out.println(cs.commonChild_naive("HARRY", "SALLY"));
		System.out.println(cs.commonChild_naive("AA", "BB"));
		System.out.println(cs.commonChild_naive("SHINCHAN", "NOHARAAA"));
		System.out.println(cs.commonChild_naive("ABCDEF", "FBDAMN"));
		System.out.println(cs.commonChild_recursive("ABCAD", "ABDCA"));
		System.out.println(cs.commonChild_tabulation("ABCAD", "ABDCA"));
	}

}
