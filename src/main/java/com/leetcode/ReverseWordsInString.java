package com.leetcode;

/**
 *
 * 
 * Category : Medium
 *
 */
public class ReverseWordsInString {

	/*
	 * Problem 1 : Given an input string s, reverse the string word by word. For example, given s = "the sky is blue",
	 * return "blue is sky the".
	 * 
	 * Example Questions Candidate Might Ask:
	 * 
	 * Q: What constitutes a word?
	 * 
	 * A: A sequence of non-space characters constitutes a word.
	 * 
	 * Q: Does tab or newline character count as space characters?
	 * 
	 * A: Assume the input does not contain any tabs or newline characters.
	 * 
	 * Q: Could the input string contain leading or trailing spaces?
	 * 
	 * A: Yes. However, your reversed string should not contain leading or trailing spaces.
	 * 
	 * Q: How about multiple spaces between two words?
	 * 
	 * A: Reduce them to a single space in the reversed string.
	 */
	/**
	 * One simple approach is a two-pass solution: First pass to split the string by spaces into an array of words, then
	 * second pass to extract the words in reversed order. Time - O(n) Space - O(n)
	 * 
	 * @param input
	 * @return
	 */
	public String reverseWords(String input) {
		StringBuilder sb = new StringBuilder();
		String[] split = input.split("\\s+");
		for (int i = split.length - 1; i >= 0; i--) {
			String s = (i == 0) ? split[i] : split[i] + " ";
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * Better approach - using single pass . While iterating the string in reverse order, we keep track of a word’s
	 * begin and end position. When we are at the beginning of a word, we append it. Time - O(n) Space - O(n)
	 * 
	 * @param input
	 * @return
	 */
	public String reverseWords1(String input) {
		StringBuilder sb = new StringBuilder();

		int j = input.length(); // signifies end of string
		for (int i = input.length() - 1; i >= 0; i--) {
			if (input.charAt(i) == ' ') {
				j = i;// reset j
			} else if (i == 0 || input.charAt(i - 1) == ' ') {
				// adjust the spaces in between words
				if (sb.length() != 0)
					sb.append(" ");

				sb.append(input.substring(i, j));
			}
		}
		return sb.toString();
	}

	/**
	 * “The input string does not contain leading or trailing spaces and the words are always separated by a single
	 * space.” Could you do it in-place without allocating extra space?
	 * 
	 * T - O(n) S - O(1)
	 * 
	 */
	public String reverseWords2(String input) {
		char[] arr = input.toCharArray();

		// reverse the whole string
		reverse(arr, 0, input.length() - 1);

		int left = 0;
		int right = 0;
		while (left < arr.length && right < arr.length) {
			while (left < arr.length && arr[left] == ' ') {
				left++; // left is first non-space
			}

			right = left + 1;
			while (right < arr.length && arr[right] != ' ') {
				right++; // right is first space
			}

			reverse(arr, left, right - 1);
			left = right;
		}

		return new String(arr);
	}

	private void reverse(char[] arr, int start, int end) {
		while (start < end) {
			char temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;

			start++;
			end--;
		}
	}

	/*
	 * Problem 2 : Given a string, you need to reverse the order of characters in each word within a sentence while
	 * still preserving whitespace and initial word order.
	 * 
	 * Example 1:
	 * 
	 * Input: "Let's take LeetCode contest"
	 * 
	 * Output: "s'teL ekat edoCteeL tsetnoc"
	 * 
	 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
	 * 
	 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
	 */
	public String reverseWordsLC3(String s) {
		StringBuilder sb = new StringBuilder();
		String[] splits = s.split(" ");
		for (String split : splits) {
			char[] ch = split.toCharArray();
			int start = 0;
			int end = ch.length - 1;
			while (start <= end) {
				swap(ch, start, end);
				start++;
				end--;
			}
			sb.append(ch).append(" ");
		}

		return sb.toString().trim();
	}

	private void swap(char[] ch, int start, int end) {
		char temp = ch[start];
		ch[start] = ch[end];
		ch[end] = temp;
	}

	public static void main(String[] args) {
		ReverseWordsInString rev = new ReverseWordsInString();
		System.out.println(rev.reverseWords("the sky is falling"));
		System.out.println(rev.reverseWords1("the sky is falling"));
		System.out.println(rev.reverseWords2("the sky is falling"));

		System.out.println(rev.reverseWordsLC3("Let's take LeetCode contest"));
	}
}
