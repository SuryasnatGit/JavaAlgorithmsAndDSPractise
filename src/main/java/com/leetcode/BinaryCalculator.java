package com.leetcode;

// TODO: complete the other portions
// Category : Medium
public class BinaryCalculator {

	// Binary value :: 11 + 10 = 0101 Decimal value :: 3 + 2 = 5
	public String add(String a, String b) {
		int lenA = a.length();
		int lenB = b.length();

		StringBuffer sb = new StringBuffer();
		int carry = 0;
		while (lenA > 0 && lenB > 0) {
			int charA = a.charAt(lenA - 1) - '0'; // this is important
			int charB = b.charAt(lenB - 1) - '0';

			int sum = charA + charB + carry;
			carry = sum / 2;
			int currDigit = sum % 2;
			sb.append(currDigit);

			lenA--;
			lenB--;
		}

		while (lenA > 0) {
			int charA = a.charAt(lenA - 1) - '0';

			int sum = charA + carry;
			carry = sum / 2;
			int currDigit = sum % 2;
			sb.append(currDigit);

			lenA--;
		}

		while (lenB > 0) {
			int charB = b.charAt(lenB - 1) - '0';

			int sum = charB + carry;
			carry = sum / 2;
			int currDigit = sum % 2;
			sb.append(currDigit);

			lenB--;
		}

		// add left over carry
		if (carry != 0)
			sb.append(carry);

		return sb.reverse().toString();
	}

	public String add_simple(String a, String b) {
		if (a == null)
			return b;
		if (b == null)
			return a;

		int i = a.length() - 1, j = b.length() - 1, carry = 0;
		StringBuilder sb = new StringBuilder();
		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (j >= 0)
				sum += b.charAt(j--) - '0';
			if (i >= 0)
				sum += a.charAt(i--) - '0';
			sb.append(sum % 2);
			carry = sum / 2;
		}
		if (carry != 0)
			sb.append(carry);

		return sb.reverse().toString();
	}

	/*
	 * Rules: 0 - 0 = 0 , 1 - 0 = 1 , 1 - 1 = 0 , 0 - 1 = 1 with 1 burrow
	 */
	public String subtract(String a, String b) {
		return null;
	}

	public String multiply(String a, String b) {
		return null;
	}

	public String divide(String a, String b) {
		return null;
	}

	public static void main(String[] args) {
		BinaryCalculator bc = new BinaryCalculator();
		System.out.println(bc.add("11", "1"));
		System.out.println(bc.add("11001", "1001"));
		System.out.println(bc.add_simple("11", "1"));
		System.out.println(bc.add_simple("11001", "1001"));
	}
}
