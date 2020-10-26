package com.leetcode;

/**
 * Validate if a given string can be interpreted as a decimal number.
 * 
 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false "2e10" => true " -90e3 " => true " 1e" =>
 * false "e3" => false " 6e-1" => true " 99e2.5 " => false "53.5e93" => true " --6 " => false "-+3" => false "95a54e53"
 * => false
 * 
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before
 * implementing one. However, here is a list of characters that can be in a valid decimal number:
 * 
 * Numbers 0-9 Exponent - "e" Positive/negative sign - "+"/"-" Decimal point - "." Of course, the context of these
 * characters also matters in the input.
 * 
 * Category : Hard
 *
 */
public class ValidNumber {

	public boolean isNumber(String s) {

		if (s == null || s.length() == 0) {
			return true;
		}

		s = s.trim();

		if (s.length() == 0) {
			return false;
		}

		if (s.length() == 1 && (hasDecimal(s) || Character.isLetter(s.charAt(0)))) {
			return false;
		}

		if (s.charAt(0) == 'e' || s.charAt(s.length() - 1) == 'e' || s.contains(" ")) {
			return false;
		}

		if (s.contains("e")) {
			String[] split = s.split("e");
			if (split.length > 2 || split.length < 2) {
				return false;
			}
			return isNumber(split[0]) && !hasDecimal(split[1]) && isNumber(split[1]);
		}

		if (hasTwoSigns(s) || hasTwoDecimals(s)) {
			return false;
		}

		if (s.contains("+") || s.contains("-")) {
			char c = s.charAt(0);
			if (c != '+' && c != '-') {
				return false;
			}
		}

		if (hasAnyOtherCharacter(s) || noNumber(s)) {
			return false;
		}

		return true;
	}

	private boolean hasTwoSigns(String s) {
		int count = 0;
		for (char ch : s.toCharArray()) {
			if (ch == '+' || ch == '-') {
				count++;
			}
		}

		return count >= 2 ? true : false;
	}

	private boolean hasTwoDecimals(String s) {
		int count = 0;
		for (char ch : s.toCharArray()) {
			if (ch == '.') {
				count++;
			}
		}

		return count >= 2 ? true : false;
	}

	private boolean hasAnyOtherCharacter(String s) {
		for (char ch : s.toCharArray()) {
			if (Character.isLetter(ch) && !(ch == '+' || ch == '-' || ch == '.')) {
				return true;
			}
		}
		return false;
	}

	private boolean hasDecimal(String s) {
		for (char ch : s.toCharArray()) {
			if (ch == '.') {
				return true;
			}
		}
		return false;
	}

	private boolean noNumber(String s) {
		for (char c : s.toCharArray()) {
			if (c >= '0' && c <= '9')
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		ValidNumber vn = new ValidNumber();
		System.out.println(vn.isNumber("+eo")); // false
		System.out.println(vn.isNumber("-1.")); // true
		System.out.println(vn.isNumber("6+1")); // false
		System.out.println(vn.isNumber(" ")); // false
		System.out.println(vn.isNumber(".")); // false
		System.out.println(vn.isNumber("e")); // false
		System.out.println(vn.isNumber("0")); // true
		System.out.println(vn.isNumber(" 0.1 ")); // true
		System.out.println(vn.isNumber("abc")); // false
		System.out.println(vn.isNumber("1 a")); // false
		System.out.println(vn.isNumber("2e10")); // true
		System.out.println(vn.isNumber(" -90e3  ")); // true
		System.out.println(vn.isNumber(" 1e")); // false
		System.out.println(vn.isNumber("e3")); // false
		System.out.println(vn.isNumber(" 6e-1")); // true
		System.out.println(vn.isNumber(" 99e2.5 ")); // false
		System.out.println(vn.isNumber("53.5e93")); // true
		System.out.println(vn.isNumber("--6")); // false
		System.out.println(vn.isNumber("-+3")); // false
		System.out.println(vn.isNumber("99a34e23")); // false
	}
}
