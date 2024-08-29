package com.algo.ds.array;

/**
 * How to append minimum numbers of characters in front of string to make it a palindrome.
 * 
 * Example 1:
 * 
 * Input: "aacecaaa" Output: "aaacecaaa"
 * 
 * Example 2:
 * 
 * Input: "abcd" Output: "dcbabcd"
 *
 * 
 * Idea is to create a new string which is original string + $ + reverse of original string. Get value of suffix which
 * is also prefix using KMP. This part of string is good. Rest needs to be copied in the front.
 *
 * Time complexity is O(n) Space complexity is O(n)
 *
 * https://leetcode.com/problems/shortest-palindrome/
 * 
 * Category : Hard
 */
public class ShortestPalindrome {

	/**
	 * According to the question, we are allowed to insert the characters only at the beginning of the string. Hence, we
	 * can find the largest segment from the beginning that is a palindrome, and we can then easily reverse the
	 * remaining segment and append to the beginning. This must be the required answer as no shorter palindrome could be
	 * found than this by just appending at the beginning.
	 * 
	 * For example: Take the string \text{"abcbabcab"}"abcbabcab". Here, the largest palindrome segment from beginning
	 * is \text{"abcba"}"abcba", and the remaining segment is \text{"bcab"}"bcab". Hence the required string is reverse
	 * of \text{"bcab"}"bcab"( = \text{"bacb"}"bacb") + original string( = \text{"abcbabcab"}"abcbabcab") =
	 * \text{"bacbabcbabcab"}"bacbabcbabcab".
	 * 
	 * Time complexity: O(n^2)
	 * 
	 * We iterate over the entire length of string ss. In each iteration, we compare the substrings which is linear in
	 * size of substrings to be compared. Hence, the total time complexity is O(n*n)
	 * 
	 * Space complexity: O(n) extra space for the reverse string \text{rev}rev.
	 * 
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public String shortestPalindrome_bruteForce(String s) {
		int l = s.length();
		String rev = "";
		for (int i = l; i > 0; i--) {
			rev += s.charAt(i - 1);
		}
		System.out.println(rev);
		for (int i = 0; i < l; i++) {
			if (s.substring(0, l - i).equals(rev.substring(i)))
				return rev.substring(0, i) + s;
		}
		return "";
	}

	/**
	 * TODO : to understand properly
	 * 
	 * Time complexity: O(n)
	 * 
	 * In every iteration of the inner while loop, t decreases until it reaches 0 or until it matches. After that, it is
	 * incremented by one. Therefore, in the worst case, t can only be decreased up to n times and increased up to n
	 * times. Hence, the algorithm is linear with maximum (2 * n) * 2(2∗n)∗2 iterations. Space complexity: O(n).
	 * Additional space for the reverse string and the concatenated string.
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public String shortestPalindrome(String s) {
		char[] input = createInput(s);
		int val = kmp(input);

		StringBuffer sb = new StringBuffer();
		int remaining = s.length() - val;
		int i = s.length() - 1;
		while (remaining > 0) {
			sb.append(s.charAt(i));
			i--;
			remaining--;
		}
		sb.append(s);
		return sb.toString();

	}

	private int kmp(char[] input) {
		int T[] = new int[input.length];

		int j = 1;
		int i = 0;

		T[0] = 0;

		while (j < input.length) {
			if (input[i] == input[j]) {
				T[j] = i + 1;
				i++;
			} else {
				while (i != 0) {
					i = T[i - 1];
					if (input[j] == input[i]) {
						T[j] = i + 1;
						i++;
						break;
					}
				}
			}
			j++;
		}
		return T[input.length - 1];
	}

	private char[] createInput(String s) {
		char[] input = new char[2 * s.length() + 1];
		int index = 0;
		for (char ch : s.toCharArray()) {
			input[index++] = ch;
		}
		input[index++] = '$';

		for (int i = s.length() - 1; i >= 0; i--) {
			input[index++] = s.charAt(i);
		}
		return input;
	}

	public static void main(String args[]) {
		ShortestPalindrome sp = new ShortestPalindrome();
		System.out.println(sp.shortestPalindrome("aacecaaa"));
		System.out.println(sp.shortestPalindrome_bruteForce("aacecaaa"));
	}
}
