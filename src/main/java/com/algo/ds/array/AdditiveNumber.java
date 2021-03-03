package com.algo.ds.array;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

	// T - O(n ^ 2)
	public boolean isAdditiveNumberIterative(String num) {
		int n = num.length();

		for (int i = 1; i <= n / 2; i++) {// for substring index i(first num) starts at 1
			for (int j = 1; Math.max(i, j) <= n - i - j; j++) {// if rest of string can contain the sum
				if (isValid(i, j, num)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isValid(int i, int j, String num) {
		// numbers in additive sequence cannot have leading zeros
		if (num.charAt(0) == '0' && i > 1) {
			return false;
		}

		if (num.charAt(i) == '0' && j > 1) {
			return false;
		}

		String sum;

		// extract x1 and x2, i & j are length of first and 2nd num
		BigInteger x1 = new BigInteger(num.substring(0, i));
		BigInteger x2 = new BigInteger(num.substring(i, i + j));

		for (int start = i + j; start != num.length(); start += sum.length()) {
			x2 = x2.add(x1);
			x1 = x2.subtract(x1);
			sum = x2.toString();

			// check if the number string contains the sum starting at the specified offset(start)
			if (!num.startsWith(sum, start)) {
				return false;
			}
		}

		return true;
	}

	// TODO : this is not working. check later.
	// Method returns additive sequence from string as a list
	public List<String> getAdditiveNumberSequence(String num) {
		List<String> res = new ArrayList<>();
		int l = num.length();

		// loop untill l/2 only, because if first
		// number is larger,then no possible sequence
		// later
		for (int i = 1; i <= l / 2; i++) {
			for (int j = 1; j <= (l - i) / 2; j++) {
				if (checkAddition(res, num.substring(0, i), num.substring(i, j), num.substring(i + j))) {
					// adding first and second number at
					// front of result list
					res.add(0, num.substring(i, j));
					res.add(0, num.substring(0, i));
					return res;
				}
			}
		}

		// If code execution reaches here, then string
		// doesn't have any additive sequence
		res.clear();
		return res;
	}

	// Recursive method to check c = a + b
	private boolean checkAddition(List<String> res, String a, String b, String c) {
		// both first and second number should be valid
		if (!isValid(a) || !isValid(b))
			return false;
		String sum = addString(a, b);

		// if sum is same as c then direct return
		if (sum == c) {
			res.add(sum);
			return true;
		}

		/*
		 * if sum size is greater than c, then no possible sequence further OR if c is not prefix of sum string, then no
		 * possible sequence further
		 */
		if (c.length() <= sum.length() || sum != c.substring(0, sum.length()))
			return false;
		else {
			res.add(sum);

			// next recursive call will have b as first
			// number, sum as second number and string
			// c as third number after removing prefix
			// sum string from c
			return checkAddition(res, b, sum, c.substring(sum.length()));
		}
	}

	// Checks whether num is valid or not, by
	// checking first character and size
	private boolean isValid(String num) {
		if (num.length() > 1 && num.charAt(0) == '0')
			return false;
		return true;
	}

	// returns int value at pos string, if pos is
	// out of bound then returns 0
	private int val(String a, int pos) {
		if (pos >= a.length())
			return 0;

		// converting character to integer
		return (a.charAt(pos) - '0');
	}

	// add two number in string form and return
	// result as a string
	private String addString(String a, String b) {
		String sum = "";
		int i = a.length() - 1;
		int j = b.length() - 1;
		int carry = 0;

		// loop untill both string get processed
		while (i >= 0 || j >= 0) {
			int t = val(a, i) + val(b, j) + carry;
			sum += (t % 10 + '0');
			carry = t / 10;
			i--;
			j--;
		}

		// not sure what this does in cpp code
		// if (carry)
		// sum += (carry + '0');
		// reverse(sum.begin(), sum.end());
		return sum;
	}

	public static void main(String[] args) {
		AdditiveNumber an = new AdditiveNumber();
		System.out.println(an.isAdditiveNumberIterative("112358"));
		System.out.println(an.isAdditiveNumberIterative("991100102"));
		// System.out.println(an.getAdditiveNumberSequence("112358"));

		System.out.println(an.isAdditiveNumber("112358"));
		// System.out.println(an.isAdditiveNumber("112358"));
		System.out.println(an.isAdditiveNumber("991100102"));
		// System.out.println(an.isAdditiveNumber("991100102"));
	}
}
