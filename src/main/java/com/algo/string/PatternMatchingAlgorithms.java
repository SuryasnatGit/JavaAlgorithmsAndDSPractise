package com.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints all
 * occurrences of pat[] in txt[]. You may assume that n > m.
 * 
 * @author surya
 *
 */
public class PatternMatchingAlgorithms {

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

	/**
	 * Z algorithm to pattern matching
	 *
	 * Time complexity - O(n + m) Space complexity - O(n + m)
	 *
	 * http://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/
	 * http://www.utdallas.edu/~besp/demo/John2010/z-algorithm.htm
	 * 
	 * Returns list of all indices where pattern is found in text.
	 */
	public List<Integer> matchPattern(char text[], char pattern[]) {
		char newString[] = new char[text.length + pattern.length + 1];
		int i = 0;
		for (char ch : pattern) {
			newString[i] = ch;
			i++;
		}
		newString[i] = '$';
		i++;
		for (char ch : text) {
			newString[i] = ch;
			i++;
		}
		List<Integer> result = new ArrayList<>();
		int Z[] = calculateZ(newString);

		for (i = 0; i < Z.length; i++) {
			if (Z[i] == pattern.length) {
				result.add(i - pattern.length - 1);
			}
		}
		return result;
	}

	private int[] calculateZ(char input[]) {
		int Z[] = new int[input.length];
		int left = 0;
		int right = 0;
		for (int k = 1; k < input.length; k++) {
			if (k > right) {
				left = right = k;
				while (right < input.length && input[right] == input[right - left]) {
					right++;
				}
				Z[k] = right - left;
				right--;
			} else {
				// we are operating inside box
				int k1 = k - left;
				// if value does not stretches till right bound then just copy it.
				if (Z[k1] < right - k + 1) {
					Z[k] = Z[k1];
				} else { // otherwise try to see if there are more matches.
					left = k;
					while (right < input.length && input[right] == input[right - left]) {
						right++;
					}
					Z[k] = right - left;
					right--;
				}
			}
		}
		return Z;
	}

	public static void main(String[] args) {
		PatternMatchingAlgorithms pm = new PatternMatchingAlgorithms();
		pm.bruteForce("Suryasnat".toCharArray(), "yas".toCharArray());
		System.out.println(pm.boyerMooreAlgo("apple".toCharArray(), "pl".toCharArray()));
		System.out.println(pm.KNPAlgorithm("apple".toCharArray(), "la".toCharArray()));

		String text = "aaabcxyzaaaabczaaczabbaaaaaabc";
		String pattern = "aaabc";
		List<Integer> result = pm.matchPattern(text.toCharArray(), pattern.toCharArray());
		result.forEach(System.out::println);
	}
}
