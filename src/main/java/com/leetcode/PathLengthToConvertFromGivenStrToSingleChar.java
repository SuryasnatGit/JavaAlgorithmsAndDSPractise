package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * III. Given three letters ABC, where AB->C, AC->B, BC->A (sequence doesn’t matter). Get the length of the path to
 * convert from a given string to a single character.
 * 
 * For example, “ABACB” goes to “ACCB” (based on AB ->C, convert s[1] and s[2] to C) “ACCB” goes to “BCB” (based on
 * AC->B) “BCB” goes to “AB” “AB” goes to “C” So it takes 4 steps to change the given string into a single character. If
 * a given string cannot be resized to 1 character, such as “AAA” or "ABACABB", return -1.
 *
 */
public class PathLengthToConvertFromGivenStrToSingleChar {

	// SOlution 1 - using back tracking
	private List<Character> str = new ArrayList<>();

	public PathLengthToConvertFromGivenStrToSingleChar(String s) {
		for (char c : s.toCharArray()) {
			str.add(c);
		}
	}

	public int pathLen() {

		if (str.size() == 0)
			return -1;

		if (str.size() == 1)
			return 0;

		if (invalidToConvert())
			return -1;

		for (int i = 0; i < str.size() - 1; i++) {

			char curr = str.get(i), next = str.get(i + 1);

			if (curr != next) {

				// backtracking, try to convert any two adjacent chars in str
				str.set(i, convert(curr, next));
				str.remove(i + 1);

				int steps = pathLen();

				if (steps >= 0)
					return steps + 1;

				// recover the str after the recursive calls
				str.set(i, curr);
				str.add(i + 1, next);

			}
		}

		return -1;
	}

	// check if given string is invalid like "AAAAA..." or "BBBBB..." or "CCCCC..."
	private boolean invalidToConvert() {

		for (int i = 0; i < str.size() - 1; i++) {
			if (str.get(i) != str.get(i + 1))
				return false;
		}
		return true;
	}

	private char convert(char a, char b) {

		if (a != 'C' && b != 'C')
			return 'C';

		if (a != 'B' && b != 'B')
			return 'B';

		return 'A';
	}

	// Solution 2
	static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("AB", "C");
		map.put("BA", "C");
		map.put("AC", "B");
		map.put("CA", "B");
		map.put("BC", "A");
		map.put("CB", "A");
	}

	public static String convert(String str) {
		if (map.containsKey(str)) {
			return map.get(str);
		} else {
			return null;
		}
	}

	public static int getPath(String str, int i, int steps) {

		// Conversion done
		if (str.length() == 1) {
			return steps;
		}

		// Check if all chars are same, cannot convert // Imp
		int ctr = 0;
		for (int k = 1; k <= str.length() - 1; k++) {
			if (str.charAt(k - 1) != str.charAt(k)) {
				break; // Optimization
			} else {
				ctr++;
			}
		}
		if (ctr == str.length() - 1) {
			return -1;
		}

		// we kept increasing i and it is beyond length-1
		if (i > (str.length() - 1) - 1) {
			i = 0;
		}

		String result = convert(str.substring(i, i + 2));

		int res = 0;
		if (result != null) {
			res = getPath(str.substring(0, i) + result + str.substring(i + 2, str.length()), i, steps + 1);
		} else {
			res = getPath(str, i + 1, steps);
		}

		if (res < 0) {
			return -1;
		} else {
			return res;
		}
	}

	public static void main(String[] args) {
		PathLengthToConvertFromGivenStrToSingleChar p = new PathLengthToConvertFromGivenStrToSingleChar("ABACB");
		System.out.println(p.pathLen());

		System.out.println(getPath("ABACB", 0, 0));
		System.out.println(getPath("AAA", 0, 0));
		System.out.println(getPath("AAC", 0, 0));
		System.out.println(getPath("BB", 0, 0));
	}
}
