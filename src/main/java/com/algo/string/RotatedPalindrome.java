package com.algo.string;

/**
 * given a string check if it is a rotated palindrome or not.
 * 
 * for example, CBAABCD is rotated palindrome as it is rotation of a palindrome ABCDCBA
 * 
 * BAABCC is a rotated palindrome as it is rotation of a palindrome ABCCBA
 * 
 * @author M_402201
 *
 */
public class RotatedPalindrome {

	/**
	 * Solution 1 - consider all rotation of the given string and check if any rotation is palindrome or
	 * not. If we found a rotation which is a palindrom we return true else false.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isRotatedPalindrome_naive(String input) {
		int len = input.length();
		// check for all rotations of the string is palindrome or not
		for (int i = 0; i < len; i++) {
			// rotate 1 character at a time
			input = input.substring(1) + input.charAt(0);
			if (isPalindrome(input, 0, len - 1))
				return true;
		}
		return false;
	}

	private boolean isPalindrome(String input, int low, int high) {
		return (low >= high) || (input.charAt(low) == input.charAt(high)) || isPalindrome(input, low + 1, high - 1);
	}

	/**
	 * Solution 2 - Similar to finding longest palindrome substring problem. Let the given string S be
	 * of length m. Idea is to concatenate the given string with itself(S + S) and find palindromic
	 * substring of length m in modified S + s . if palindromix substring of length m exists we return
	 * true else false.
	 * 
	 * time - O(n^2)
	 * 
	 * @param args
	 */
	// expand in both directions of low and high to find
	// palindrome of length k
	private boolean expand(String str, int low, int high, int k) {
		while (low >= 0 && high < str.length() && (str.charAt(low) == str.charAt(high))) {
			// return true palindrome of length k is found
			if (high - low + 1 == k) {
				return true;
			}

			// expand in both directions
			low--;
			high++;
		}

		// return false if palindrome of length k is not found
		return false;
	}

	// Function to check if palindromic substring of length k exists or not
	private boolean longestPalindromicSubString(String str, int k) {
		for (int i = 0; i < str.length() - 1; i++) {
			// check if odd length or even length palindrome of length k
			// having str.charAt(i) as its mid point exists
			if (expand(str, i, i, k) || expand(str, i, i + 1, k)) {
				return true;
			}
		}

		return false;
	}

	// Function to check if given string is a rotated palindrome or not
	public boolean isRotatedPalindrome_1(String str) {
		// length of given string
		int m = str.length();

		// return true if longest palindromic substring of length m
		// exists in the string (str + str)
		return longestPalindromicSubString(str + str, m);
	}

	public static void main(String[] args) {
		RotatedPalindrome rp = new RotatedPalindrome();
		String s = "ABCDCBA";
		s = "CBAABCD";
		System.out.println(rp.isRotatedPalindrome_naive(s));
		System.out.println(rp.isRotatedPalindrome_1(s + s));
	}

}
