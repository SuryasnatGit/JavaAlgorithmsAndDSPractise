package com.misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringProblems {

	public static void main(String[] args) {
		StringProblems p = new StringProblems();
		// p.stringPermutations("TABLE");
		// System.out.println(p.isUniqueString("surya"));
		// System.out.println(p.isUniqueString("suryasnat"));
		// System.out.println(p.isUniqueString("tableta"));

		// System.out.println(p.isUniqueString1("surya"));
		System.out.println(p.isUniqueString3("a"));
		// System.out.println(p.isUniqueString1("table"));

	}

	private void stringPermutations(String str) {
		permutations(str, "");
	}

	private void permutations(String string, String prefix) {
		if (string.length() == 0)
			System.out.println(prefix);
		else {
			for (int i = 0; i < string.length(); i++) {
				String rem = string.substring(0, i) + string.substring(i + 1);
				permutations(rem, prefix + string.charAt(i));
			}
		}
	}

	/**
	 * Algorithm to find if a string has unique characters. Time complexity - O(N) as hashset operations is constant
	 * time O(1)
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString(String s) {
		Set<Character> charSet = new HashSet<>();
		char[] charArray = s.toCharArray();
		for (char ch : charArray) {
			if (charSet.contains(ch))
				return false;
			else
				charSet.add(ch);
		}
		return true;
	}

	/**
	 * Algorithm to find if a string has unique characters using a temp array. Time complexity is O(N^2) for worst case
	 * , where N is the length of the string
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString1(String s) {
		int l = s.length();
		// temp array with length l as length of string
		char[] tempArr = new char[l];
		for (int i = 0; i < l; i++) {
			char charAt = s.charAt(i);
			// for each iteration check if char is present in the temp array.
			// if so return false
			for (int j = 0; j < l; j++) {
				System.out.println("charAt -->" + charAt + " . tempArr -->" + Arrays.toString(tempArr));
				if (charAt == tempArr[j]) {
					return false;
				}
			}
			// if not then add char to temp arr
			tempArr[i] = charAt;
		}
		// when we reach this point we know that string is unique
		return true;
	}

	/**
	 * Time complexity - O(N^2) as for each iteration of N, the indexOf check runs N times.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString2(String s) {
		boolean isUnique = false;
		for (char c : s.toCharArray()) {
			System.out.println("s.indexOf(c) -->" + s.indexOf(c) + " . s.lastIndexOf(c)-->" + s.lastIndexOf(c));
			if (s.indexOf(c) == s.lastIndexOf(c)) {
				isUnique = true;
			} else {
				isUnique = false;
				break;
			}
		}
		return isUnique;
	}

	/**
	 * Time complexity - O(N)
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString3(String s) {
		// boolean array representing each character in set
		boolean[] arr = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			// store the char as int to determine the ASCII value
			int pos = s.charAt(i);
			// if the boolean array element at the pos index returns true then there is a duplicate.
			if (arr[pos])
				return false;
			// else set the array element at pos index to true
			arr[pos] = true;
		}
		return true;
	}

}
