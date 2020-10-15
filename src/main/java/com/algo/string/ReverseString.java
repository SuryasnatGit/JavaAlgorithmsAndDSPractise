package com.algo.string;

public class ReverseString {

	/**
	 * Given a string, that contains special character together with alphabets (‘a’ to ‘z’ and ‘A’ to ‘Z’), reverse the
	 * string in a way that special characters are not affected.
	 * 
	 * Examples:
	 * 
	 * Input: str = "a,b$c" Output: str = "c,b$a" Note that $ and , are not moved anywhere. Only subsequence "abc" is
	 * reversed
	 * 
	 * Input: str = "Ab,c,de!$" Output: str = "ed,c,bA!$"
	 */
	public String reverseStringWithSpecialChars(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}

		int left = 0;
		int right = s.length() - 1;
		char[] ch = s.toCharArray();

		while (left <= right) {
			if (!Character.isAlphabetic(ch[left])) {
				left++;
			} else if (!Character.isAlphabetic(ch[right])) {
				right--;
			} else {
				swap(ch, left, right);
				left++;
				right--;
			}
		}

		return new String(ch);
	}

	private void swap(char[] ch, int left, int right) {
		char temp = ch[left];
		ch[left] = ch[right];
		ch[right] = temp;
	}

	public String reverseString(String s) {
		char[] ch = s.toCharArray();
		int c = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			ch[c++] = s.charAt(i);
		}
		return new String(ch);
	}

	public static void main(String[] args) {
		ReverseString rs = new ReverseString();
		System.out.println(rs.reverseStringWithSpecialChars("a,b$c"));
		System.out.println(rs.reverseStringWithSpecialChars("Ab,c,de!$"));
		System.out.println(rs.reverseString("Apple"));
	}
}
