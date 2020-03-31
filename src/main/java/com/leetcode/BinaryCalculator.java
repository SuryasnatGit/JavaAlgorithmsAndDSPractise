package com.leetcode;

public class BinaryCalculator {

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
	}
}
