package com.ctci.arraysnstrings;

import java.util.Arrays;

/**
 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use
 * additional data structures?
 * 
 * CTCI : 1.1
 *
 */
public class IsUnique {

	/**
	 * using sorting and linear traversal. Time - O(n log n) + O(n)
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueChars(String s) {
		// sort the string
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray); // O(n log n)
		for (int i = 0; i < charArray.length - 1; i++) { // O(n)
			if (charArray[i] == charArray[i + 1])
				return false;
		}

		return true;
	}

	/**
	 * assume string consists of 256 ASCII chars. Time - O(n) Space - O(1)
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueChars1(String s) {
		if (s.length() > 256)
			return false;

		boolean[] visited = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			if (visited[ch]) // already found this char in string
				return false;
			visited[ch] = true;
		}
		return true;
	}

	/**
	 * Using bit vector. Time - O(n)
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueChars2(String s) {
		int checker = 0;
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;

			checker |= 1 << val;
		}
		return true;
	}

	public static void main(String[] args) {
		IsUnique iu = new IsUnique();
		System.out.println(iu.isUniqueChars("APPLE"));
		System.out.println(iu.isUniqueChars("PALP"));
		System.out.println(iu.isUniqueChars1("APPLE"));
		System.out.println(iu.isUniqueChars1("PALE"));
		System.out.println(iu.isUniqueChars2("APPLE"));
	}

}
