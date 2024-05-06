package com.leetcode;

import java.util.Stack;

/**
 * <P>
 * Easy Given two strings S and T, return if they are equal when both are typed into empty text editors.# means a
 * backspace character.
 * 
 * Example 1: Input: S = "ab#c", T = "ad#c" Output: true
 * 
 * Explanation: Both S and T become "ac".
 * 
 * Example 2: Input: S = "ab##", T = "c#d#" Output: true
 * 
 * Explanation: Both S and T become "".
 * 
 * Example 3: Input: S = "a##c", T = "#a#c" Output: true
 * 
 * Explanation: Both S and T become "c".
 * 
 * Example 4: Input: S = "a#c", T = "b" Output: false
 * 
 * Explanation: S becomes "c" while T becomes "b".
 * 
 * Note: 1 <= S.length <= 200 1 <= T.length <= 200 S and T only contain lowercase letters and '#' characters.
 * 
 * Follow up: Can you solve it in O(N) time and O(1) space?
 * </P>
 * 
 * @author surya
 *
 */
public class BackspaceStringCompare {

	public static void main(String[] args) {
		BackspaceStringCompare back = new BackspaceStringCompare();
		System.out.println(back.isEqualUsingStack("ab#c", "ad#c"));
		System.out.println(back.isEqualUsingStack("ab##", "c#d#"));
		System.out.println(back.isEqualUsingStack("a##c", "#a#c"));
		System.out.println(back.isEqualUsingStack("a#c", "b"));
		System.out.println(back.isEqualUsingPointers("ab#c", "ad#c"));
		System.out.println(back.isEqualUsingPointers("ab##", "c#d#"));
		System.out.println(back.isEqualUsingPointers("a##c", "#a#c"));
		System.out.println(back.isEqualUsingPointers("a#c", "b"));
	}

	/**
	 * Approach 1 : Using stack : T - O(N), S - O(N)
	 * 
	 */
	public boolean isEqualUsingStack(String s, String t) {
		if (s != null && t != null && s.length() == t.length()) {
			String resultS = navigateString(s);
			String resultT = navigateString(t);
			return (resultS.equals(resultT));
		}
		return false;
	}

	private String navigateString(String s) {
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '#' && !stack.isEmpty()) {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		return String.valueOf(stack);
	}

	/**
	 * Approach 2 : Using 2 pointer : T - O(N), S - O(1)
	 * 
	 */
	public boolean isEqualUsingPointers(String s, String t) {
		if (s != null && t != null && s.length() == t.length()) {
			int indexS = s.length() - 1;
			int indexT = t.length() - 1;
			int skipS = 0, skipT = 0;

			while (indexS >= 0 || indexT >= 0) {
				while (indexS >= 0) {
					if (s.charAt(indexS) == '#') {
						indexS--;
						skipS++;
					} else if (skipS > 0) {
						indexS--;
						skipS--;
					} else {
						break;
					}
				}

				while (indexT >= 0) {
					if (t.charAt(indexT) == '#') {
						indexT--;
						skipT++;
					} else if (skipT > 0) {
						indexT--;
						skipT--;
					} else {
						break;
					}
				}

				if (indexS >= 0 && indexT >= 0 && s.charAt(indexS) != s.charAt(indexT)) {
					return false;
				}
				if ((indexS >= 0 && indexT < 0) || (indexS < 0 && indexT >= 0)) {
					return false;
				}

				indexS--;
				indexT--;
			}
			return true;
		}
		return false;
	}

}
