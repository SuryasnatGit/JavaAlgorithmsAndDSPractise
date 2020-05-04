package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string with digits and non-digits, find the sum of numbers in the string without pattern-matching, substring
 * or built in convert string to integer functions. Consecutive digits form a single number. Except - any other
 * character is a separator. '.' is a separator. any number Preceded with '-' will be considered as a negative number.
 * 
 * 
 * Example : in = abc123def4 , result = 123 + 4 = 127
 * 
 * in = abc12def-4 , result = 12 - 4 = 8
 * 
 * Category : Hard
 *
 */
public class SumOfNumbersInString {

	public static void main(String[] args) {
		SumOfNumbersInString sum = new SumOfNumbersInString();
		System.out.println(sum.sumOfNumbers("abc123def4"));
		System.out.println(sum.sumOfNumbers("abc123def-4c10"));
		System.out.println(sum.sumOfNumbers("abc-123def.40"));
	}

	public int sumOfNumbers(String in) {

		if (in == null)
			return 0;

		int len = in.length();
		if (len < 1)
			return 0;

		List<String> posList = new ArrayList<>();
		List<String> negList = new ArrayList<>();
		String posStr = "";
		String negStr = "";
		boolean isDigit = false;
		boolean isNegDigit = false;

		for (int i = 0; i < len; i++) {
			char ch = in.charAt(i);
			if (Character.isDigit(ch)) { // check for digit

				if (!isDigit) { // if moving from alphabet to digit
					isDigit = true;
				}

				if (isNegDigit) {
					negStr += ch;
				} else {
					posStr += ch;
				}
			} else { // check for alphabet

				if (isDigit) { // if moving from digit to alphabet
					isDigit = false;
					posList.add(posStr);

					// reset positive string
					posStr = "";
				}

				if (isNegDigit) {
					isNegDigit = false;
					negList.add(negStr);

					// reset positive string
					negStr = "";
				}

				if (ch == '-') {
					isNegDigit = true;
					continue;
				}
			}
		}

		if (posStr.length() > 0)
			posList.add(posStr);

		if (negStr.length() > 0)
			negList.add(negStr);

		System.out.println("posList :" + posList);
		System.out.println("negList :" + negList);

		int result = 0;
		for (String str : posList) {
			result += getIntValue(str);
		}
		for (String str : negList) {
			result -= getIntValue(str);
		}

		return result;
	}

	private int getIntValue(String in) {
		int sum = 0;
		int j = 1;
		for (int i = in.length() - 1; i >= 0; i--) {
			int c = in.charAt(i) - '0';
			// System.out.println(c);
			sum += j * c;
			j *= 10;
		}
		return sum;
	}

}
