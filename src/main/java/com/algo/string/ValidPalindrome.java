
package com.algo.string;

/**
 *
 */
public class ValidPalindrome {

	/**
	 * Problem 1 - Given a string, determine if it is a palindrome, considering only alphanumeric characters and
	 * ignoring cases. For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a palindrome.
	 *
	 * https://leetcode.com/problems/valid-palindrome/
	 * 
	 * time complexity - O(n), Space complexity - O(1)
	 * 
	 * @param s
	 * @return
	 */
	// iterative
	public boolean isPalindromeIterative(String s) {
		int start = 0;
		int end = s.length() - 1;
		while (start < end) {
			if (!isAlphaNum(s.charAt(start))) {
				start++;
			} else if (!isAlphaNum(s.charAt(end))) {
				end--;
			} else {
				if (Character.toLowerCase(s.charAt(start++)) != Character.toLowerCase(s.charAt(end--))) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isAlphaNum(char ch) {
		if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
			return true;
		}
		return false;
	}

	// recursive
	public boolean isPalindromeRecursive(String s) {
		int l = s.length();
		// empty string considered as palindrome
		if (l == 0)
			return true;

		return isPalindromeRec(s, 0, l - 1);
	}

	private boolean isPalindromeRec(String s, int start, int end) {
		// if there is only 1 char
		if (start == end)
			return true;

		// if first and last char do not match
		if (s.charAt(start) != s.charAt(end))
			return false;

		// if there are more then 2 characters check if middle substring is also
		// palindrome or not.
		if (start < end + 1)
			return isPalindromeRec(s, start + 1, end - 1);

		return true;
	}

	/**
	 * Problem 2 - Given a non-empty string s, you may delete at most one character. Judge whether you can make it a
	 * palindrome.
	 * 
	 * Example 1: Input: "aba" Output: True
	 * 
	 * Example 2: Input: "abca" Output: True Explanation: You could delete the character 'c'.
	 * 
	 * Note: The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
	 * 
	 * https://leetcode.com/problems/valid-palindrome-ii/
	 * 
	 * T - O(n) S - O(1)
	 */
	public boolean validPalindrome(String s) {
		if (s == null) {
			return true;
		}

		for (int i = 0; i < s.length() / 2; i++) {
			int j = s.length() - i - 1;
			if (s.charAt(i) != s.charAt(j)) {
				return isPalindromInRange(s, i + 1, j) || isPalindromInRange(s, i, j - 1);
			}
		}
		return true;
	}

	private boolean isPalindromInRange(String s, int left, int right) {
		for (int i = left; i <= left + (right - left) / 2; i++) {
			if (s.charAt(i) != s.charAt(right - i + left)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		ValidPalindrome vp = new ValidPalindrome();
		System.out.println(vp.isPalindromeIterative("abc"));
		System.out.println(vp.isPalindromeIterative("abcba"));
		System.out.println(vp.isPalindromeIterative("abcdab"));
		System.out.println(vp.isPalindromeRecursive("abcba"));
		System.out.println(vp.isPalindromeRecursive("abcdab"));

		System.out.println(vp.validPalindrome("abc"));
		System.out.println(vp.validPalindrome("abca"));
		System.out.println(vp.validPalindrome("abcad"));
		System.out.println(vp.validPalindrome("abcabcdba"));
	}
}