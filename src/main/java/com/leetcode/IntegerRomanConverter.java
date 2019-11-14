package com.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/integer-to-roman/
 * 
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * 
 * Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000 For example, two is written as II in Roman numeral, just two one's
 * added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which
 * is XX + V + II.
 * 
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 * Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same
 * principle applies to the number nine, which is written as IX.
 * 
 * There are six instances where subtraction is used:
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900. Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * Example 1:
 * 
 * Input: 3 Output: "III"
 * 
 * Example 2:
 * 
 * Input: 4 Output: "IV"
 * 
 * Example 3:
 * 
 * Input: 9 Output: "IX"
 * 
 * Example 4:
 * 
 * Input: 58 Output: "LVIII" Explanation: L = 50, V = 5, III = 3.
 * 
 * Example 5:
 * 
 * Input: 1994 Output: "MCMXCIV" Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * 
 * 
 * @author surya
 *
 */
public class IntegerRomanConverter {

	public enum Type {
		M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40), X(10), IX(9), V(5), IV(4), I(1);
		private final int value;

		Type(int value) {
			this.value = value;
		}
	};

	public String intToRoman(int num) {
		StringBuilder output = new StringBuilder();
		for (Type t : Type.values()) {
			while (num >= t.value) {
				output.append(t);
				num -= t.value;
			}
		}
		return output.toString();
	}

	public int romanToInt(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('M', 1000);
		map.put('D', 500);
		map.put('C', 100);
		map.put('L', 50);
		map.put('X', 10);
		map.put('V', 5);
		map.put('I', 1);

		int res = 0, i = 0;
		while (i < s.length()) {
			switch (s.charAt(i)) {
			case 'C':
			case 'X':
			case 'I':
				// basically for CM, CD, XC, XL, IX and IV
				if (i + 1 < s.length() && map.get(s.charAt(i + 1)) > map.get(s.charAt(i))) {
					res += (map.get(s.charAt(i + 1)) - map.get(s.charAt(i)));
					i += 2;
				} else {
					res += map.get(s.charAt(i));
					++i;
				}
				break;
			default:
				res += map.get(s.charAt(i));
				++i;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		IntegerRomanConverter i = new IntegerRomanConverter();
		System.out.println(i.intToRoman(2040));
		System.out.println(i.romanToInt("XVIIX"));
	}
}
