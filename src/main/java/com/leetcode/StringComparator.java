package com.leetcode;

public class StringComparator {

	public int compare(String s1, String s2) {
		int pos1 = 0;
		int pos2 = 0;

		while (pos1 < s1.length() && pos2 < s2.length()) {
			char c1 = s1.charAt(pos1);
			char c2 = s2.charAt(pos2);

			if (Character.isDigit(c1) && Character.isDigit(c2)) {
				// Compare 2 numbers
				int num1 = c1 - '0';
				int num2 = c2 - '0';

				pos1++;
				while (pos1 < s1.length() && Character.isDigit(s1.charAt(pos1))) {
					num1 = num1 * 10 + (s1.charAt(pos1) - '0');
					pos1++;
				} // Eventually, pos1 will be alpha or end of s1

				pos2++;
				while (pos2 < s2.length() && Character.isDigit(s2.charAt(pos2))) {
					num2 = num2 * 10 + (s2.charAt(pos2) - '0');
					pos2++;
				} // Eventually, pos2 will be alpha or end of s2

				// Compare the 2 numbers
				if (num1 != num2) {
					return num1 > num2 ? 1 : -1;
				}
			} else if (c1 == c2) {
				pos1++;
				pos2++;
			} else {
				return c1 > c2 ? 1 : -1;
			}
		}

		if (pos1 == s1.length() && pos2 == s2.length()) {
			return 0;
		} else if (pos2 == s2.length()) {
			return 1;
		} else {
			return -1;
		}
	}

	public static void main(String[] args) {
		StringComparator sc = new StringComparator();
		System.out.println(sc.compare("foo", "abc1000"));
	}
}
