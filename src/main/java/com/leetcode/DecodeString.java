package com.leetcode;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 * 
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * 
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * 
 * Example 1:
 * 
 * Input: s = "3[a]2[bc]"
 * 
 * Output: "aaabcbc"
 * 
 * Example 2:
 * 
 * Input: s = "3[a2[c]]"
 * 
 * Output: "accaccacc"
 * 
 * Example 3:
 * 
 * Input: s = "2[abc]3[cd]ef"
 * 
 * Output: "abcabccdcdcdef"
 * 
 * Example 4:
 * 
 * Input: s = "abc3[cd]xyz"
 * 
 * Output: "abccdcdcdxyz"
 *
 * Category : Medium
 */
public class DecodeString {

	public String decodeString(String s) {
		Stack<String> stack1 = new Stack<String>();
		Stack<Integer> stack2 = new Stack<Integer>();

		int pos = 0;
		stack1.push("");

		while (pos < s.length()) {
			char now = s.charAt(pos);

			if (Character.isDigit(now)) {
				int num = 0;
				while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
					num = num * 10 + (s.charAt(pos) - '0');
					pos++;
				}
				pos--;
				stack2.push(num);
			} else if (now == '[') {
				stack1.push("");
			} else if (now == ']') {
				int count = stack2.pop();
				String pattern = stack1.pop();
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < count; i++) {
					sb.append(pattern);
				}

				stack1.push(stack1.pop() + sb.toString());
			} else {
				stack1.push(stack1.pop() + now);
			}

			pos++;
		}

		return stack1.pop();
	}

	public static void main(String[] args) {
		DecodeString ds = new DecodeString();
		System.out.println(ds.decodeString("3[a]2[bc]"));
		System.out.println(ds.decodeString("3[a2[c]]"));
		System.out.println(ds.decodeString("2[abc]3[cd]ef"));
		System.out.println(ds.decodeString("abc3[cd]xyz"));
	}
}
