package com.leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 * 
 * Example 1:
 * 
 * Input: "(()" Output: 2
 * 
 * Explanation: The longest valid parentheses substring is "()"
 * 
 * Example 2:
 * 
 * Input: ")()())" Output: 4
 * 
 * Explanation: The longest valid parentheses substring is "()()"
 *
 */
public class LongestValidParenthesis {

	// T - O(n) S - O(n)
	public int longestValidParenthesesUsingStack(String s) {
		Stack<Integer> stack = new Stack<Integer>(); // Stack contains indexes
		stack.push(-1);
		int res = 0;

		for (int i = 0; i < s.length(); i++) {
			char now = s.charAt(i);

			if (now == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
				stack.pop(); // Throw the left (
				res = Math.max(res, i - stack.peek());
			} else { // Could be ( and )
				stack.push(i);
			}
		}

		return res;
	}

	// T - O(n) S - O(n)
	public int longestValidParenthesesUsingDP(String s) {
		int[] hash = new int[s.length()];
		hash[0] = 0;
		int res = 0;

		for (int i = 1; i < s.length(); i++) {
			char now = s.charAt(i);

			if (now == '(') {
				hash[i] = 0;
			} else { // ')'
				if (s.charAt(i - 1) == '(') {
					hash[i] = (i - 2 >= 0 ? hash[i - 2] : 0) + 2;
					res = Math.max(res, hash[i]);
				} else { // Previous is also )
					if (i - hash[i - 1] - 1 >= 0 && s.charAt(i - hash[i - 1] - 1) == '(') {
						// Can form
						hash[i] = hash[i - 1] + 2 + (i - hash[i - 1] - 2 >= 0 ? hash[i - hash[i - 1] - 2] : 0);
						res = Math.max(res, hash[i]);
					}
				}
			}
		}

		return hash[s.length() - 1];
	}

	// T - O(n) S - O(1)
	public int longestValidParenthesesWithoutExtraSpace(String s) {
		int left = 0, right = 0, maxlength = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * right);
			} else if (right >= left) {
				left = right = 0;
			}
		}
		left = right = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * left);
			} else if (left >= right) {
				left = right = 0;
			}
		}
		return maxlength;
	}

	public static void main(String[] args) {
		LongestValidParenthesis lvp = new LongestValidParenthesis();
		String s = "((())";
		System.out.println(lvp.longestValidParenthesesUsingStack(s));
		System.out.println(lvp.longestValidParenthesesUsingDP(s));
		System.out.println(lvp.longestValidParenthesesWithoutExtraSpace(s));
	}
}
