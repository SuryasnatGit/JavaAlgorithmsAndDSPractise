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
 * 
 * Explanation:
 * 
 * If you are sensitive enough, "two strings" and "subsequence" will guide you to DP immediately. From some previous
 * experience like Edit Distance, you know it is a good idea to construct an m by n matrix, where m = T.length() and n =
 * S.length(). Each element in the matrix, say matrix[i][j], represents the number of distinct subsequences of string
 * T[0:i+1] and S[0:j+1]. Now comes the hard part: how to fill in the matrix?
 * 
 * The matrix can be filled row by row, i.e. each time we add a single character in T and evaluate the number of
 * distinct subsequences for all prefix of S. If T[i] != S[j], since T[0:i+1] is a subsequence of S[0:j+1] (if exists),
 * the new character T[i] should be matched before S[j]. As a result we have the equation matrix[i][j] = matrix[i][j-1].
 * 
 * If T[i] == S[j], there are two possible cases: (1) T[i] is the character that matches to S[j], so we should throw
 * away both T[i] and S[j], and the number of subsequences should equal to matrix[i-1][j-1]; or (2) T[i] is matched to a
 * character before S[j], where we can get the number from matrix[i][j-1], as we have illustrated before. The total
 * number of distinct subsequences is the sum of those two cases, so the equation should be matrix[i][j] =
 * matrix[i-1][j-1] + matrix[i][j-1].
 * 
 * To summarize, the core logic of this DP problem is shown in the following code block
 * 
 * if (S[j] != T[i])
 * 
 * matrix[i][j] = matrix[i][j-1];
 * 
 * else
 * 
 * matrix[i][j] = matrix[i-1][j-1] + matrix[i][j-1];
 * 
 * Since we only use two adjacent rows in the matrix at any time, the memory consumption can be cut down to $O(n)$
 * instead of $O(mn)$.
 * 
 * 
 */
public class DistinctSubsequence {

	public int numDistinct(String s, String t) {
		int m = s.length();
		int n = t.length();

		if (m < n)
			return 0;

		// hash[i][j] stores the count of occuarances of T[0..i] in S[0..j]
		int[][] hash = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			hash[i][0] = 1;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					// if last char match then consider all substring without last char in s and without last char in
					// both
					hash[i][j] += hash[i - 1][j - 1] + hash[i - 1][j];
				} else {
					// if last chars dont match then value is same as value without last char in s
					hash[i][j] += hash[i - 1][j];
				}
			}
		}

		return hash[m][n];
	}

	public static void main(String[] args) {
		DistinctSubsequence ds = new DistinctSubsequence();
		// System.out.println(ds.numDistinct("rabbbit", "rabbit"));
		System.out.println(ds.numDistinct("apple", "aplt"));
		System.out.println(ds.numDistinct("apple", "aple"));
	}
}
