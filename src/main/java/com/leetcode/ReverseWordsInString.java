package com.leetcode;

/**
 * Given an input string s, reverse the string word by word. For example, given s = "the sky is
 * blue", return "blue is sky the".
 * 
 * @author surya
 *
 */
public class ReverseWordsInString {

	/**
	 * One simple approach is a two-pass solution: First pass to split the string by spaces into an
	 * array of words, then second pass to extract the words in reversed order.
	 * 
	 * @param input
	 * @return
	 */
	public String reverseWords(String input) {
		StringBuilder sb = new StringBuilder();
		String[] split = input.split(" ");
		for (int i = split.length - 1; i >= 0; i--) {
			String s = (i == 0) ? split[i] : split[i] + " ";
			sb.append(s);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		ReverseWordsInString rev = new ReverseWordsInString();
		System.out.println(rev.reverseWords("the sky is falling"));
	}
}
