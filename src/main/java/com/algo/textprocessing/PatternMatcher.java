package com.algo.textprocessing;

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

	public static void main(String[] args) {
		PatternMatcher pm = new PatternMatcher();
		pm.bruteForce("Suryasnat".toCharArray(), "yas".toCharArray());
	}
}
