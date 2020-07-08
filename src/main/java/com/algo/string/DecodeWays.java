package com.algo.string;

/**
 * Category : Hard
 *
 */
public class DecodeWays {

	/**
	 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
	 * 
	 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given a non-empty string containing only digits, determine the total number of
	 * ways to decode it.
	 * 
	 * Example 1:
	 * 
	 * Input: "12" Output: 2 Explanation: It could be decoded as "AB" (1 2) or "L" (12). Example 2:
	 * 
	 * Input: "226" Output: 3 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
	 * 
	 */
	public int numDecodings(String s) {
		int[] hash = new int[s.length() + 1];

		hash[0] = 1;
		hash[1] = 1;

		for (int i = 2; i <= s.length(); i++) {
			int digit1 = Integer.valueOf(s.substring(i - 1, i));
			int digit2 = Integer.valueOf(s.substring(i - 2, i));

			if (digit1 >= 1 && digit1 <= 9) {
				hash[i] += hash[i - 1];
			}

			if (digit2 >= 10 && digit2 <= 26) {
				hash[i] += hash[i - 2];
			}
		}

		return hash[s.length()];
	}

	/**
	 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
	 * 
	 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26
	 * 
	 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the
	 * numbers from 1 to 9.
	 * 
	 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
	 * 
	 * Also, since the answer may be very large, you should return the output mod 109 + 7.
	 * 
	 * Example 1: Input: "*" Output: 9 Explanation: The encoded message can be decoded to the string: "A", "B", "C",
	 * "D", "E", "F", "G", "H", "I".
	 * 
	 * Example 2: Input: "1*" Output: 9 + 9 = 18
	 * 
	 * https://leetcode.com/problems/decode-ways-ii/
	 * 
	 * TODO : To complete
	 */

	public static void main(String[] args) {
		DecodeWays dw = new DecodeWays();
		System.out.println(dw.numDecodings("12"));
		System.out.println(dw.numDecodings("226"));
	}
}
