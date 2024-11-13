package com.leetcode;

/**
 * Given an integer n, find the closest integer (not including itself), which is a palindrome. The 'closest' is defined
 * as absolute difference minimized between two integers.
 * 
 * Example 1:
 * 
 * Input: "123"
 * 
 * Output: "121"
 * 
 * Example 2:
 * 
 * Input: n = "1"
 * 
 * Output: "0"
 * 
 * Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 * 
 * Note:
 * 
 * The input n is a positive integer represented by string, whose length will not exceed 18.
 * 
 * If there is a tie, return the smaller one as answer.
 * 
 * Category : Hard
 * 
 * 3 steps:
 * 
 * I -- The given number itself is a palindrome
 * 
 * II -- The given number is a not palindrome
 * 
 * III -- Construct the whole palindrome from its left part
 * 
 * TODO : to understand
 */
public class ClosestPalindrome {

	public String nearestPalindromic(String n) {
		if (n == null || n.isEmpty()) {
			return "";
		}
		char[] p = n.toCharArray();
		for (int i = 0, j = p.length - 1; i < j; i++, j--) {
			p[j] = p[i];
		}

		String curP = String.valueOf(p);
		String prevP = nearestPalindromeOf(curP, false);
		String nextP = nearestPalindromeOf(curP, true);

		long num = Long.valueOf(n);
		long cur = Long.valueOf(curP);
		long prev = Long.valueOf(prevP);
		long next = Long.valueOf(nextP);

		long d1 = Math.abs(num - cur);
		long d2 = Math.abs(num - prev);
		long d3 = Math.abs(num - next);

		if (num == cur) {
			return d2 <= d3 ? prevP : nextP;
		} else if (num > cur) {
			return d1 <= d3 ? curP : nextP;
		} else {
			return d2 <= d1 ? prevP : curP;
		}
	}

	/**
	 * @param s
	 *            - input palindrome string
	 * @param dir
	 *            true if finding larger, false if finding smaller
	 * @return nearest palindrome string of input palindrome
	 */
	private String nearestPalindromeOf(String s, boolean dir) {
		int len = s.length();
		int k = len / 2;
		// make sure left include the mid point if len is odd number
		int base = Integer.valueOf(s.substring(0, len - k));
		base += dir ? 1 : -1;

		// handle "11", "1" case
		if (base == 0) {
			if (k == 0) {
				return "0";
			}
			return "9";
		}

		StringBuilder left = new StringBuilder(String.valueOf(base));
		StringBuilder right = new StringBuilder(left).reverse();
		// handle "1001", "10001" type of case
		if (k > left.length()) {
			right.append("9");
		}

		return left.append(right.substring(right.length() - k)).toString();
	}

	public static void main(String[] args) {
		ClosestPalindrome cp = new ClosestPalindrome();
		System.out.println(cp.nearestPalindromic("123"));
		System.out.println(cp.nearestPalindromic("1"));
	}
}
