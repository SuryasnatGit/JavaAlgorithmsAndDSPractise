package com.leetcode;

/**
 * Given an input string s, reverse the string word by word. For example, given
 * s = "the sky is blue", return "blue is sky the".
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
 * A: Yes. However, your reversed string should not contain leading or trailing
 * spaces.
 * 
 * Q: How about multiple spaces between two words?
 * 
 * A: Reduce them to a single space in the reversed string.
 * 
 * @author surya
 *
 */
public class ReverseWordsInString {

	/**
	 * One simple approach is a two-pass solution: First pass to split the string by
	 * spaces into an array of words, then second pass to extract the words in
	 * reversed order. Time - O(n) Space - O(n)
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

	/**
	 * Better approach - using single pass . While iterating the string in reverse
	 * order, we keep track of a word’s begin and end position. When we are at the
	 * beginning of a word, we append it. Time - O(n) Space - O(n)
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
	 * “The input string does not contain leading or trailing spaces and the words
	 * are always separated by a single space.” Could you do it in-place without
	 * allocating extra space?
	 * 
	 * Solution: O(n) runtime, O(1) space – In-place reverse: Let us indicate the
	 * ith word by wi and its reversal as wi′. Notice that when you reverse a word
	 * twice, you get back the original word. That is, (wi′)′ = wi. The input string
	 * is w1 w2 ... wn. If we reverse the entire string, it becomes wn′ ... w2′ w1′.
	 * Finally, we reverse each individual word and it becomes wn ... w2 w1.
	 * Similarly, the same result could be reached by reversing each individual word
	 * first, and then reverse the entire string.
	 * 
	 * @param input
	 */
	public String reverseWords2(String input) {
		char[] ch = input.toCharArray();
		// reverse the whole string
		reverse(ch, 0, input.length());
		for (int i = 0, j = 0; j <= input.length(); j++) {
			if (j == input.length() || input.charAt(j) == ' ') {
				reverse(ch, i, j);
				i = j + 1;
			}
		}
		return new String(ch);
	}

	private void reverse(char[] ch, int start, int end) {
		for (int i = 0; i < (end - start) / 2; i++) {
			char temp = ch[start + i];
			ch[start + i] = ch[end - i - 1];
			ch[end - i - 1] = temp;
		}
	}

	public static void main(String[] args) {
		ReverseWordsInString rev = new ReverseWordsInString();
		System.out.println(rev.reverseWords("the sky is falling"));
		System.out.println(rev.reverseWords1("the sky is falling"));
		System.out.println(rev.reverseWords2("the sky is falling"));
	}
}
