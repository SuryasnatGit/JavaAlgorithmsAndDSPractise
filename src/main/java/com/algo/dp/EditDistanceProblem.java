package com.algo.dp;

import java.util.Iterator;

/**
 * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits
 * (operations) required to convert str1 to str2
 * 
 * Insert Remove Replace
 * 
 * All of the above operations are of equal cost.
 * 
 * Examples:
 * 
 * Input: str1 = "geek", str2 = "gesek" Output: 1 We can convert str1 into str2 by inserting a 's'.
 * 
 * Input: str1 = "cat", str2 = "cut" Output: 1 We can convert str1 into str2 by replacing 'a' with 'u'.
 * 
 * Input: str1 = "sunday", str2 = "saturday" Output: 3 Last three and first characters are same. We basically need to
 * convert "un" to "atur". This can be done using below three operations. Replace 'n' with 'r', insert t, insert a
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

	/**
	 * Problem 2:One edit distance problem
	 */
	// This is one edit distance
	public boolean isOneEditDistance(String s, String t) {
		if (s == null || t == null) {
			return false;
		}

		if (s.length() == t.length()) {
			return change(s, t);
		} else {
			if (Math.abs(s.length() - t.length()) > 1) {
				return false;
			}
			return add(s, t);
		}
	}

	private boolean add(String s, String t) {
		if (s.length() < t.length()) {
			return add(t, s);
		}

		int pos1 = 0, pos2 = 0;
		while (pos1 < s.length() && pos2 < t.length()) {
			if (s.charAt(pos1) != t.charAt(pos2)) {
				return s.substring(pos1 + 1).equals(t.substring(pos2));
			}
			pos1++;
			pos2++;
		}

		return true;
	}

	private boolean change(String s, String t) {
		boolean found = false;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (found) {
					return false;
				}
				found = true;
			}
		}

		return found;
	}

	// For the above "One Edit Distance", what if input is a stream

	/**
	 * The second question is a variant of "one edit distance", but in fact this question is a bit difficult. The input
	 * is not given two strings, but two custom interfaces, which only have the next() method. When next() returns 0, it
	 * is end. My approach is to implement another peek() method, and then I am a little confused when calling these two
	 * methods. In the end, I almost didn’t finish writing, but I still finished writing. The interviewer said Is work
	 * 
	 * 3 scenarios, 1. Once unequal is found, all the following must be equal. 2. If s is long, throw away S and compare
	 * them one by one 3. If t is long, throw away T, then compare one by one
	 */

	public boolean isOneEditDistance(Iterator<Character> s, Iterator<Character> t) {
		if (s == null || t == null) {
			return false;
		}

		boolean findUnequal = false;

		while (s.hasNext() && t.hasNext()) {
			char c1 = s.next();
			char c2 = t.next();

			if (c1 == c2) {
				continue;
			} else {
				if (findUnequal) {
					return false;
				}
				findUnequal = true;
			}
		}

		return findUnequal;
	}
}
