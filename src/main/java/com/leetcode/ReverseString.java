package com.leetcode;

public class ReverseString {

	// 1
	public void reverseString1(char[] s) {
		for (int i = 0; i < s.length / 2; i++) {
			swap(s, i, s.length - i - 1);
		}
	}

	private void swap(char[] s, int i, int j) {
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}

	// 2
	public void reverseString2(char[] s) {
		char[] res = new char[s.length];

		for (int i = 0; i < s.length; i++) {
			res[i] = s[s.length - i - 1];
		}

		System.out.println(res);
	}

	public static void main(String[] args) {
		ReverseString rev = new ReverseString();
		char[] s = "apple".toCharArray();
		rev.reverseString1(s);
		System.out.println(s);
		rev.reverseString2(s);
	}
}
