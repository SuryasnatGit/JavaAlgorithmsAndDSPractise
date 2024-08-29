package com.algo.ds.array;

import java.math.BigInteger;

/**
 *
 * Additive number is a string whose digits can form additive sequence. A valid additive sequence should contain at
 * least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the
 * preceding two.<br/>
 * 
 * For example: "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8 "199100199" is also an additive number, the additive sequence is: 1, 99,
 * 100, 199.
 * 
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * https://leetcode.com/problems/additive-number/
 * 
 * Category : Medium
 */
public class AdditiveNumber {

	public boolean isAdditiveNumber(String num) {
		for (int i = 1; i < num.length(); i++) {
			for (int j = i + 1; j < num.length() - i + 1; j++) {
				String first = num.substring(0, i);
				String second = num.substring(i, j);
				if (isValid(j, first, second, num)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isValid(int start, String first, String second, String num) {
		if (start == num.length()) {
			return true;
		}

		// check for leading zero
		if ((first.startsWith("0") && first.length() > 1) || (second.startsWith("0") && second.length() > 1)) {
			return false;
		}

		BigInteger one = new BigInteger(first);
		BigInteger two = new BigInteger(second);
		BigInteger sum = one.add(two);
		String sumStr = sum.toString();

		if (sumStr.length() + start > num.length()) {
			return false;
		}

		if (!num.substring(start, sumStr.length() + start).equals(sumStr)) {
			return false;
		}

		return isValid(sumStr.length() + start, second, sumStr, num);
	}

	public static void main(String[] args) {
		AdditiveNumber an = new AdditiveNumber();

		System.out.println(an.isAdditiveNumber("112358"));
		System.out.println(an.isAdditiveNumber("112359"));
		System.out.println(an.isAdditiveNumber("991100191"));
		System.out.println(an.isAdditiveNumber("991100102"));
	}
}
