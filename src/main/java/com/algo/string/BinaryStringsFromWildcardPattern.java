package com.algo.string;

import java.util.Stack;

/**
 * https://www.techiedelight.com/find-binary-strings-can-formed-given-wildcard-pattern/
 * 
 * Given a binary pattern that contains ‘?’ wildcard character at few positions, find all possible combinations of
 * binary strings that can be formed by replacing the wildcard character by either 0 or 1.
 * 
 * Time complexity. Worst case - exponential. best case - O(N)
 */
public class BinaryStringsFromWildcardPattern {

	public void printBinaryStrings(String pattern) {
		Stack<String> stack = new Stack<String>();
		stack.push(pattern);

		int index;
		while (!stack.isEmpty()) {
			pattern = stack.pop();
			if ((index = pattern.indexOf("?")) != -1) {
				// replace ? with 0 and 1
				for (char ch = '0'; ch <= '1'; ch++) {
					pattern = pattern.substring(0, index) + ch + pattern.substring(index + 1);
					stack.push(pattern);
				}
			} else {
				// if wild card not found then print the pattern
				System.out.println(pattern);
			}
		}
	}

	public static void main(String[] args) {
		BinaryStringsFromWildcardPattern b = new BinaryStringsFromWildcardPattern();
		b.printBinaryStrings("10?11?");
	}
}
