package com.algo.dp;

/**
 * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum
 * number of edits (operations) required to convert �str1� into �str2�.
 * 
 * Insert Remove Replace
 * 
 * All of the above operations are of equal cost.
 * 
 * Examples:
 * 
 * Input: str1 = "geek", str2 = "gesek" Output: 1 We can convert str1 into str2 by inserting a 's'.
 * 
 * Input: str1 = "cat", str2 = "cut" Output: 1 We can convert str1 into str2 by replacing 'a' with
 * 'u'.
 * 
 * Input: str1 = "sunday", str2 = "saturday" Output: 3 Last three and first characters are same. We
 * basically need to convert "un" to "atur". This can be done using below three operations. Replace
 * 'n' with 'r', insert t, insert a
 * 
 * @author surya
 *
 */
public class EditDistanceProblem {

	/**
	 * Complexity - O(3^n) if 3 is the number of operations (insert, remove, replace)
	 * 
	 * @param s1
	 *            source string 1
	 * @param s2
	 *            target string 2
	 * @param m
	 *            length of s1
	 * @param n
	 *            length of s2
	 * @return min distance
	 */
	public int editDistanceRecursive(String s1, String s2, int m, int n) {
		// If any of the string if empty then number of operations
		// needed would be equal to the length of other string
		// (Either all characters will be removed or inserted)
		if (m == 0) // all elements inserted
			return n;
		if (n == 0)// all elements removed
			return m;
		// If last characters are matching, ignore the last character
		// and make a recursive call with last character removed.
		if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
			return editDistanceRecursive(s1, s2, m - 1, n - 1);
		}
		// If nothing above worked then we need to try all 3 operations
		// and choose the minimum among them
		return 1 + Math.min(editDistanceRecursive(s1, s2, m, n - 1),
				Math.min(editDistanceRecursive(s1, s2, m - 1, n), editDistanceRecursive(s1, s2, m - 1, n - 1)));
	}

	/**
	 * Time Complexity: O(m x n)
	 * 
	 * Auxiliary Space: O(m x n)
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int editDistanceDP(String s1, String s2) {
		// holder to hold the solution so as to prevent recursion
		int[][] solution = new int[s1.length() + 1][s2.length() + 1];
		// base case - if s1 is empty then all elements would be inserted
		for (int i = 0; i <= s2.length(); i++) {
			solution[0][i] = i;
		}
		// base case - if s2 is empty, then all elements would be removed
		for (int i = 0; i <= s1.length(); i++) {
			solution[i][0] = i;
		}
		int m = s1.length();
		int n = s2.length();
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				// If last characters are matching, ignore the last character
				// then the solution will be same as without the last character.
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					solution[i][j] = solution[i - 1][j - 1];
				} else {
					solution[i][j] = 1
							+ Math.min(solution[i][j - 1], Math.min(solution[i - 1][j], solution[i - 1][j - 1]));
				}
			}
		}
		return solution[s1.length()][s2.length()];
	}
}
