package com.algo.textprocessing;

import java.util.HashMap;
import java.util.Map;

public class PatternMatcher {

	/**
	 * worst case running time - O(m * n)
	 * 
	 * @param text
	 * @param pattern
	 */
	public void bruteForce(char[] text, char[] pattern) {
		int m = text.length; // length of text
		int n = pattern.length; // length of pattern
		// loop for each possible starting position of pattern
		boolean matchFound = false;
		for (int i = 0; i <= m - n; i++) {
			int k = 0; // counter for pattern
			while (k < n && text[i + k] == pattern[k])
				k++;
			if (k == n) {
				matchFound = true;
				break;
			}
		}
		System.out.println(matchFound ? "match found " : "not found");
	}

	/**
	 * performance- O(nm)
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public int boyerMooreAlgo(char[] text, char[] pattern) {
		int n = text.length; // text length
		int m = pattern.length; // pattern length
		if (n == 0)
			return -1;
		// declare last position hashmap
		Map<Character, Integer> last = new HashMap<>();
		// set all alphabets position in text to -1
		for (int i = 0; i < n; i++)
			last.put(text[i], -1);
		// set all alphabets position in pattern to corresponding index
		for (int j = 0; j < m; j++)
			last.put(pattern[j], j);
		// start with the end of the pattern which is m -1
		int i = m - 1;// index for text
		int j = m - 1;// index for pattern
		while (i < n) {
			if (text[i] == pattern[j]) {
				if (j == 0)
					return i; // this means match found, return index i for text
				i--;
				j--;
			} else {
				i += m - Math.min(j, 1 + last.get(text[i]));
				j = m - 1;
			}
		}
		return -1;
	}

	/**
	 * best case performance - O(m + n) worst case - O(mn) like brute force or naive approach
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public int KNPAlgorithm(char[] text, char[] pattern) {
		int n = text.length;// text length
		int m = pattern.length;// pattern length
		if (m == 0)
			return -1;
		int[] fail = findKNPFailure(pattern);
		int j = 0; // index for text
		int k = 0;// index for pattern
		while (j < n) {
			if (text[j] == pattern[k]) {
				if (k == m - 1)
					return j - m + 1; // found match. now return the position
				j++;
				k++;
			} else if (k > 0)
				k = fail[k - 1];
			else
				j++;
		}
		return -1;
	}

	private int[] findKNPFailure(char[] pattern) {
		int m = pattern.length;
		int[] fail = new int[m]; // initialize all elements of fail array to 0(skip positions)
		int j = 1;
		int k = 0;
		while (j < m) {
			if (pattern[j] == pattern[k]) {
				fail[j] = k + 1;
				j++;
				k++;
			} else if (k > 0) {
				k = fail[k - 1];
			} else
				j++;
		}
		return fail;
	}

	public static void main(String[] args) {
		PatternMatcher pm = new PatternMatcher();
		pm.bruteForce("Suryasnat".toCharArray(), "yas".toCharArray());
		System.out.println(pm.boyerMooreAlgo("apple".toCharArray(), "pl".toCharArray()));
		System.out.println(pm.KNPAlgorithm("apple".toCharArray(), "la".toCharArray()));
	}
}
