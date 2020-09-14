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
		hash[1] = s.charAt(0) == '0' ? 0 : 1;

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
	public int decodeWays(String s) {
		int len = s.length();
		if (s == null || len == 0) {
			return 0;
		}

		int[] hash = new int[len + 1];
		hash[0] = s.charAt(0) == '0' ? 0 : 1;
		hash[1] = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 9 : 1);

		for (int i = 2; i <= len; i++) {
			char cur = s.charAt(i - 1);
			char prev = s.charAt(i - 2);

			if (cur == '*') { // 0 - 9
				// single digit, 9 possibilities
				hash[i] += hash[i - 1] * 9;

				// Try 2 digits
				if (prev == '*') { // 2 consecutive stars could stand for 10 - 19, 20 - 26
					hash[i] += hash[i - 2] * 17;
				} else if (prev == '1') {
					hash[i] += hash[i - 2] * 10; // 10 - 19
				} else if (prev == '2') { // 20 - 26
					hash[i] += hash[i - 2] * 7;
				}
			} else {
				// single digit
				if (cur != '0') { // 只要当前一位不是0
					hash[i] += hash[i - 1]; // 不要加一啥的
				}

				// Try 2 digits
				if (prev == '*') { // 前边一位是*
					if (cur >= '0' && cur <= '6') { // 前边一位是*， 当前位是 0 - 6， 能组队
						hash[i] += hash[i - 2] * 2; // * can stand for 2 cases, 1 or 2
					} else {
						hash[i] += hash[i - 2]; // * can stand for only 1 case, 1
					}
				} else { // 也可以分开讨论， prev==1, prev == 2
					boolean lessThan26 = prev != '0' && (prev - '0') * 10 + (cur - '0') <= 26;
					if (lessThan26) {
						hash[i] += hash[i - 2];
					}
				}

			}
		}

		return hash[len];
	}

	public static void main(String[] args) {
		DecodeWays dw = new DecodeWays();
		System.out.println(dw.numDecodings("12"));
		System.out.println(dw.numDecodings("226"));
		System.out.println(dw.numDecodings("0"));
		System.out.println(dw.decodeWays("1**2"));
	}
}
