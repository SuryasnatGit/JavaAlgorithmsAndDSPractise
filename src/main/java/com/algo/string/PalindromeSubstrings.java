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

	public void substrings(String s) {
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
		ps.substrings("apple");
	}

}
