package com.leetcode;

public class ReverseString {

	// 1
	public void reverseString1(char[] s) {
		for (int i = 0; i < s.length / 2; i++) {
			swap(s, i, s.length - i - 1);
		}
		System.out.println(s);
	}

	private void swap(char[] s, int i, int j) {
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}

	public static void main(String[] args) {
		ReverseString rev = new ReverseString();
		rev.reverseString1("apple".toCharArray());
		rev.reverseString1("mithun".toCharArray());
	}
}
