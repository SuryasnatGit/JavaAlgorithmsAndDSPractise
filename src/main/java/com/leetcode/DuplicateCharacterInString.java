package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 */
public class DuplicateCharacterInString {

	/**
	 * Given a string, find the first repeated character in it. We need to find the character that occurs more than once
	 * and whose index of second occurrence is smallest.
	 * 
	 * T - O(n) S - O(n)
	 * 
	 */
	public char firstDuplicateCharHashing(String s) {
		char[] arr = s.toCharArray();
		Set<Character> set = new HashSet<>();

		for (char c : arr) {
			if (set.contains(c)) {
				return c;
			}
			set.add(c);
		}
		return '\0';
	}

	/**
	 * T - O(n) S - O(1)
	 * 
	 * @param s
	 * @return
	 */
	public char firstDuplicateCharOptimized(String s) {
		int[] arr = new int[26];
		for (char c : s.toCharArray()) {
			if (arr[c - 'a'] != 0) {
				return c;
			}
			arr[c - 'a']++;
		}
		return '\0';
	}

	/**
	 * Given a string, find the repeated character present first in the string. leftmost
	 * 
	 * T - O(n) S - O(1)
	 * 
	 * @param s
	 * @return
	 */
	public char firstDuplicateLeftMost(String s) {
		LinkedHashMap<Character, Integer> charCount = new LinkedHashMap<>();

		for (char c : s.toCharArray()) {
			charCount.put(c, charCount.get(c) != null ? charCount.get(c) + 1 : 1);
		}

		for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
			if (entry.getValue() > 1) {
				return entry.getKey();
			}
		}

		return '\0';
	}

	/**
	 * The function returns index of the first repeating character in a string. If all characters are repeating then
	 * returns -1
	 * 
	 * T - O(n) S - O(1)
	 * 
	 * @param s
	 * @return
	 */
	public int getIndexOfFirstDuplicateCharacter(String s) {
		int[] indexArr = new int[256]; // 256 chars

		// populate index array with -1
		Arrays.fill(indexArr, -1);

		int result = Integer.MAX_VALUE;
		for (int index = 0; index < s.length(); index++) {
			if (indexArr[s.charAt(index)] == -1) {
				indexArr[s.charAt(index)] = index;
			} else {
				result = Math.min(result, indexArr[s.charAt(index)]);
			}
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	public static void main(String[] args) {
		DuplicateCharacterInString fir = new DuplicateCharacterInString();

		System.out.println(fir.firstDuplicateCharHashing("abccfegbaed"));// c
		System.out.println(fir.firstDuplicateCharHashing("abcbafeged"));// b
		System.out.println(fir.firstDuplicateCharHashing("abcde"));// blank

		System.out.println(fir.firstDuplicateCharOptimized("abccfegbaed"));// c
		System.out.println(fir.firstDuplicateCharOptimized("abcbafeged"));// b
		System.out.println(fir.firstDuplicateCharOptimized("abcde"));// blank

		System.out.println(fir.firstDuplicateLeftMost("abccfegbaed"));// a
		System.out.println(fir.firstDuplicateLeftMost("abccfeged")); // c
		System.out.println(fir.firstDuplicateLeftMost("abcd"));// blank

		System.out.println(fir.getIndexOfFirstDuplicateCharacter("abccfegbaed"));// a
		System.out.println(fir.getIndexOfFirstDuplicateCharacter("abccfeged")); // c
		System.out.println(fir.getIndexOfFirstDuplicateCharacter("abcd"));// blank
	}
}
