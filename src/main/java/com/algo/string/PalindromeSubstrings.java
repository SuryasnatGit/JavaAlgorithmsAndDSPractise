package com.algo.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * We can solve this problem in O(n^2) time and O(1) space. The idea is inspired from longest palindromic substring
 * problem. For each character in the given string, we consider it as mid point of a palindrome and expand in both
 * directions to find all palindromes that have it as mid-point. For even length palindrome, we consider every adjacent
 * pair of characters as mid point. We use a set to store all unique palindromic substrings..
 * 
 */
public class PalindromeSubstrings {

	public int countPalindromicSubstrings(String s) {
		int N = s.length();
		boolean[][] hash = new boolean[N][N]; // Good, you got the idea to use a 2D boolean array

		for (int i = 0; i < N; i++) {
			hash[i][i] = true;
		}

		for (int i = 0; i < N - 1; i++) { // 把length为2的也算出来
			if (s.charAt(i) == s.charAt(i + 1)) {
				hash[i][i + 1] = true;
			}
		}

		for (int len = 3; len <= N; len++) { // 用length!
			for (int i = 0; i + len <= N; i++) {
				char left = s.charAt(i);
				char right = s.charAt(i + len - 1);

				if (left == right) {
					hash[i][i + len - 1] = hash[i + 1][i + len - 2]; // Go inside
				} else {
					hash[i][i + len - 1] = false;
				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j && hash[i][j]) {
					count++;
				}
			}
		}

		return count;
	}

	public void printPalindromicSubstrings(String s) {
		Set<String> set = new HashSet<String>();

		for (int i = 0; i < s.length(); i++) {
			// for even length palindrom string start with s.charAt(i)
			expand(s, i, i, set);

			// for odd length palindrom string start with s.charAt(i) and s.charAt(i + 1)
			expand(s, i, i + 1, set);
		}

		set.forEach(c -> System.out.println(c));
	}

	private void expand(String s, int low, int high, Set<String> set) {
		while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
			set.add(s.substring(low, high + 1));

			// expand on both directions
			low--;
			high++;
		}
	}

	public static void main(String[] args) {
		PalindromeSubstrings ps = new PalindromeSubstrings();
		// ps.printPalindromicSubstrings("apple");
		System.out.println(ps.countPalindromicSubstrings("abbaeae"));
	}

}
