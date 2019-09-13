package com.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction
 * in string format. If the fractional part is repeating, enclose the repeating part in parentheses.
 * 
 * For example,
 * 
 * Given numerator = 1, denominator = 2, return "0.5". <br/>
 * Given numerator = 2, denominator = 1, return "2". <br/>
 * Given numerator = 2, denominator = 3, return "0.(6)". <br/>
 * 
 * Category : Hard
 * 
 * @author M_402201
 *
 */
public class FractionToRecurringDecimal {

	public String recurringDecimal(int numerator, int denominator) {

		// base check
		if (numerator == 0)
			return "0";

		if (denominator == 0)
			return "";

		String result = "";

		// check the sign
		if (numerator < 0 || denominator < 0)
			result += "-";

		int quotient = numerator / denominator;
		result += quotient;

		int remainder = (numerator % denominator) * 10;
		if (remainder == 0)
			return result;

		// key is remainder, value is length of decimal part
		Map<Integer, Integer> map = new HashMap<>();
		result += ".";
		while (remainder != 0) {
			System.out.println(map);
			// if digits repeat
			if (map.containsKey(remainder)) {
				int beg = map.get(remainder);
				String part1 = result.substring(0, beg);
				String part2 = result.substring(beg, result.length());
				result = part1 + "(" + part2 + ")";
				return result;
			}

			// continue
			map.put(remainder, result.length());
			quotient = remainder / denominator;
			result += quotient;
			remainder = (remainder % denominator) * 10;
		}

		return result;
	}

	public static void main(String[] args) {
		FractionToRecurringDecimal f = new FractionToRecurringDecimal();
		System.out.println(f.recurringDecimal(11, 6));
		System.out.println(f.recurringDecimal(10, 3));
		System.out.println(f.recurringDecimal(1, 2));
		System.out.println(f.recurringDecimal(110, 6));
	}

}
