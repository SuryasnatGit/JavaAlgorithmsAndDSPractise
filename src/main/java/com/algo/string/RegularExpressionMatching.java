package com.algo.string;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * 
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character. '*' Matches zero or more of the preceding element. The matching should cover the
 * entire input string (not partial).
 * 
 * Note:
 * 
 * s could be empty and contains only lowercase letters a-z. p could be empty and contains only lowercase letters a-z,
 * and characters like . or *.
 * 
 * Example 1:
 * 
 * Input: s = "aa" p = "a" Output: false. Explanation: "a" does not match the entire string "aa".
 * 
 * Example 2:
 * 
 * Input: s = "aa" p = "a*" Output: true. Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore,
 * by repeating 'a' once, it becomes "aa".
 * 
 * Example 3:
 * 
 * Input: s = "ab" p = ".*" Output: true Explanation: ".*" means "zero or more (*) of any character (.)".
 * 
 * Example 4:
 * 
 * Input: s = "aab" p = "c*a*b" Output: true Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore
 * it matches "aab".
 * 
 * Example 5:
 * 
 * Input: s = "mississippi" p = "mis*is*p*." Output: false
 * 
 * Category : Hard
 * 
 * Tags : DP
 *
 */
public class RegularExpressionMatching {

	/**
	 * Here are some conditions to figure out, then the logic can be very straightforward.
	 * 
	 * 1, If p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1];
	 * 
	 * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	 * 
	 * 3, If p.charAt(j) == '*': here are two sub conditions: 1 if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]
	 * //in this case, a* only counts as empty 2 if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.': dp[i][j] =
	 * dp[i-1][j] //in this case, a* counts as multiple a or dp[i][j] = dp[i][j-1] // in this case, a* counts as single
	 * a or dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
	 * 
	 * 
	 * Complexity Analysis
	 * 
	 * Time Complexity: Let T, P be the lengths of the text and the pattern respectively. The work for every call to
	 * dp(i, j) for i=0, ... ,Ti=0,...,T; j=0, ... ,Pj=0,...,P is done once, and it is O(1) work. Hence, the time
	 * complexity is O(TP).
	 * 
	 * Space Complexity: The only memory we use is the O(TP) boolean entries in our cache. Hence, the space complexity
	 * is O(TP)
	 * 
	 * 
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch_DP(String s, String p) {

		if (s == null || p == null) {
			return false;
		}
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*' && dp[0][i - 1]) {
				dp[0][i + 1] = true;
			}
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) == '.') {
					dp[i + 1][j + 1] = dp[i][j];
				}
				if (p.charAt(j) == s.charAt(i)) {
					dp[i + 1][j + 1] = dp[i][j];
				}
				if (p.charAt(j) == '*') {
					if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					} else {
						dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
					}
				}
			}
		}
		return dp[s.length()][p.length()];
	}

	public boolean isMatch(String s, String p) {
		return helper(s, p, 0, 0);
	}

	private boolean helper(String s, String p, int i, int j) {
		if (i >= s.length() && j >= p.length()) {
			return true;
		}

		if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
			if (helper(s, p, i, j + 2)) {
				return true;
			}

			if (p.charAt(j) == '.') {
				for (int k = i; k < s.length(); k++) {
					if (helper(s, p, k + 1, j + 2)) {
						return true;
					}
				}
			} else {
				for (int k = i; k < s.length(); k++) {
					if (s.charAt(k) == p.charAt(j)) {
						if (helper(s, p, k + 1, j + 2)) {
							return true;
						}
					} else {
						break;
					}
				}
			}
		} else if (i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
			return helper(s, p, i + 1, j + 1);
		}

		return false;
	}

	public static void main(String[] args) {
		RegularExpressionMatching reg = new RegularExpressionMatching();
		System.out.println(reg.isMatch_DP("mississippi", "mis*is*p*."));
		System.out.println(reg.isMatch_DP("misssissipppi", "..s*is*.p*."));
		System.out.println(reg.isMatch("mississippi", "mis*is*p*."));
		System.out.println(reg.isMatch("misssissipppi", "..s*is*.p*."));
	}

}
