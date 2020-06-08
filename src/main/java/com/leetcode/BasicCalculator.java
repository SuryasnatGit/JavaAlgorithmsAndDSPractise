package com.leetcode;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer
 * division should truncate toward zero.
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * 
 * "3+2*2" = 7
 * 
 * " 3/2 " = 1
 * 
 * " 3+5 / 2 " = 5
 * 
 * Also support parenthesis.
 *
 */
public class BasicCalculator {

	public int calculator(String s) {

		if (s == null || s.length() == 0)
			return 0;

		while (s.indexOf("(") != -1) {
			int open = s.lastIndexOf("(");
			int close = s.indexOf(")", open);
			int result = calculateSubSection(s.substring(open + 1, close).replaceAll("--", "+"));
			s = s.substring(0, open) + result + s.substring(close + 1);
		}

		s = s.replaceAll("--", "+");
		return calculateSubSection(s);
	}

	private int calculateSubSection(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0; // To be prepared
		char sign = '+'; // This sign is the sign before num.

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (Character.isDigit(c)) {
				num = num * 10 + (c - '0');
			}

			// It is an operator or it is the last digit
			if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
				switch (sign) {
				case '+':
					stack.push(num);
					break;
				case '-':
					stack.push(-num);
					break;
				case '*':
					stack.push(stack.pop() * num);
					break;
				case '/':
					stack.push(stack.pop() / num);
					break;
				}

				sign = c;
				num = 0;
			}
		}

		int res = 0;
		while (!stack.isEmpty()) {
			res += stack.pop();
		}

		return res;
	}

	public static void main(String[] args) {
		BasicCalculator bc = new BasicCalculator();

		System.out.println(bc.calculator("3+2*2"));
		System.out.println(bc.calculator("3/2"));
		System.out.println(bc.calculator("3*(2*2+1)"));
		System.out.println(bc.calculator("2*3+5/6*3+15"));
		System.out.println(bc.calculator("(2*3+4)-3*5"));
		System.out.println(bc.calculator("((2*3+4)-3)*5"));
	}
}
