package com.companyprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given a string S of lowercase English alphabets. Let X, be the number of substrings of S consisting of at
 * least I distinct characters.
 * 
 * Task
 * 
 * Determine the value of X[i], for all, where i can take values in the range [1, 26] (both inclusive).
 * 
 * Example
 * 
 * Assumptions
 * 
 * N=4
 * 
 * S = aabc
 * 
 * Approach
 * 
 * The value of X[i] can be calculated as follows:
 * 
 * <br/>
 * For i = 1. Every substring of S have at least 1 distinct characters, hence X[i] = num of substrings = 4*(5)/2 = 10.
 * <br/>
 * For i = 2. Substrings [aab, aabc, bc, abc, ab] have at least 2 distinct characters, hence X[i] = 5. <br/>
 * For i = 3. Substrings [aabc, abc] have at least 3 distinct characters, hence X[i] = 2. <br/>
 * For i >= 4. No substrings can have more than 3 distinct characters, hence X[i] = 0. <br/>
 * Therefore, the output array is [10 5 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
 * 
 * Function Description
 * 
 * Complete the DistinctChars function provided in the editor. The function takes the following 2 parameters and returns
 * an array denoting the values of X[i].
 * 
 * N: Represents the length of string S .
 * 
 * S: Represents the string S
 * 
 * Input format
 * 
 * Note: This is the input format that you must use to provide custom input (available above the Compile and Test
 * button). The first line contains an integer N representing the length of the string. The second line contains the
 * string S.
 * 
 * Output format Print a single line containing 26 space-separated integers. The ith integer representing the value of
 * X[i], in the same order.
 *
 * Category : Medium
 * 
 * Tags : Sliding Window, Hashing
 */
public class SubstringsAndDistinctCharacters {

	public static void main(String[] args) {
		SubstringsAndDistinctCharacters sdc = new SubstringsAndDistinctCharacters();
		System.out.println(Arrays.toString(sdc.distinctChars1(4, "aabc")));

		System.out.println(Arrays.toString(sdc.distinctChars2(4, "aabc")));
	}

	/**
	 * Brute force approach : The simplest approach to solve the given problem is to generate all substrings of the
	 * given string and count those substrings that have at least K distinct characters in them. After checking for all
	 * the substrings, print the total count obtained as the result.
	 * 
	 * Time Complexity: O(N^3) Auxiliary Space: O(256)
	 * 
	 */
	public int[] distinctChars1(int N, String S) {

		List<String> ss = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				ss.add(S.substring(i, j));
			}
		}

		int[] result = new int[26];

		for (int i = 1; i <= 26; i++) {
			for (String subStr : ss) {
				Set<Character> set = new HashSet<>();
				for (char c : subStr.toCharArray()) {
					set.add(c);
				}
				if (set.size() >= i) {
					result[i - 1]++;
				}
				set.clear();
			}
		}

		return result;
	}

	/**
	 * Efficient approach : Using sliding window and hashing. T - O(N) S - O(1)
	 * 
	 */
	public int[] distinctChars2(int N, String S) {

		int[] result = new int[26];

		for (int i = 1; i <= 26; i++) {
			result[i - 1] = distinctChars2(N, S, i);
		}

		return result;
	}

	private int distinctChars2(int N, String S, int k) {
		Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();

		// start and end of sliding window
		int start = 0, end = 0;

		int result = 0;

		while (end < N) {
			char endChar = S.charAt(end);
			charCountMap.put(endChar, charCountMap.getOrDefault(endChar, 0) + 1);
			end++;

			while (charCountMap.size() >= k) {
				// remove char from beginning of window
				char beginChar = S.charAt(start);
				charCountMap.put(beginChar, charCountMap.get(beginChar) - 1);

				// if frequency is 0 remove from map
				if (charCountMap.get(beginChar) == 0) {
					charCountMap.remove(beginChar);
				}

				result += N - end + 1;
				start++;
			}
		}

		return result;
	}
}
