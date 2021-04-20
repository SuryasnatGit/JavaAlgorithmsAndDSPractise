package com.leetcode;

/**
 * Implement atoi which converts a string to an integer.
 * 
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is
 * found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical
 * digits as possible, and interprets them as a numerical value.
 * 
 * The string can contain additional characters after those that form the integral number, which are ignored and have no
 * effect on the behavior of this function.
 * 
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence
 * exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * 
 * If no valid conversion could be performed, a zero value is returned.
 * 
 * Note:
 * 
 * Only the space character ' ' is considered as whitespace character. Assume we are dealing with an environment which
 * could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. If the numerical value is out of
 * the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * 
 * Example 1:
 * 
 * Input: "42"
 * 
 * Output: 42
 * 
 * Example 2:
 * 
 * Input: " -42"
 * 
 * Output: -42
 * 
 * Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits
 * as possible, which gets 42.
 * 
 * Example 3:
 * 
 * Input: "4193 with words"
 * 
 * Output: 4193
 * 
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * 
 * Example 4:
 * 
 * Input: "words and 987"
 * 
 * Output: 0
 * 
 * Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no
 * valid conversion could be performed.
 * 
 * Example 5:
 * 
 * Input: "-91283472332"
 * 
 * Output: -2147483648
 * 
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Thefore INT_MIN (−231) is
 * returned.
 *
 * Category : Medium
 */
public class StringIntConversion {

	public int stringToInt(String input) {
		System.out.println("input :" + input);
		if (input == null || input.trim().length() < 1) {
			return 0;
		} else {
			// trim white space
			input = input.trim();
		}

		// check negative or positive
		boolean isNegative = false;
		int i = 0;
		if (input.charAt(0) == '-') {
			isNegative = true;
			i++;
		} else if (input.charAt(0) == '+') {
			i++;
		}

		// calculate value
		double res = 0;
		while (input.length() > i && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
			res = res * 10 + (input.charAt(i) - '0');
			i++;
		}

		// check sign
		if (isNegative) {
			res = -res;
		}

		// check int limits
		if (res > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (res < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) res;
	}

	public String intToString(int num) {
		boolean isNegative = false;

		if (num < 0) {
			isNegative = true;
		}

		StringBuilder sb = new StringBuilder();
		while (num != 0) {
			char c = (char) (Math.abs(num % 10) + '0');
			sb.append(c);
			num /= 10;
		}

		if (isNegative) {
			sb.append('-');
		}

		sb.reverse();

		return sb.toString();
	}

	public static void main(String[] args) {
		StringIntConversion as = new StringIntConversion();

		System.out.println(as.stringToInt("42"));
		System.out.println(as.stringToInt("   -42"));
		System.out.println(as.stringToInt("4193 with words"));
		System.out.println(as.stringToInt("words and 987"));
		System.out.println(as.stringToInt("-91283472332"));
		System.out.println(as.stringToInt(" "));

		System.out.println(as.intToString(500));
		System.out.println(as.intToString(-500));
	}
}
