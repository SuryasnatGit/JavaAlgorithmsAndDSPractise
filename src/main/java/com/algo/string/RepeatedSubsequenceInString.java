package com.algo.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find if there is any subsequence of length 2 or more that repeats itself such that the two
 * subsequences doesn’t have the same character at the same position, i.e., any 0’th or 1st character in the two
 * subsequences shouldn’t have the same index in the original string.
 * 
 * Example:
 * 
 * Input: ABCABD
 * 
 * Output: Repeated Subsequence Exists (A B is repeated)
 * 
 * Input: ABBB(Special case. palindrom)
 * 
 * Output: Repeated Subsequence Exists (B B is repeated)
 * 
 * Input: AAB
 * 
 * Output: Repeated Subsequence Doesn't Exist (Note that A B cannot be considered as repeating because B is at same
 * position in two subsequences).
 * 
 * Input: AABBC
 * 
 * Output: Repeated Subsequence Exists (A B is repeated)
 * 
 * Input: ABCDACB
 * 
 * Output: Repeated Subsequence Exists (A B is repeated)
 * 
 * Input: ABCD
 * 
 * Output: Repeated Subsequence Doesn't Exist
 * 
 * The idea is to remove all the non-repeated characters from the string and check if the resultant string is palindrome
 * or not. If the remaining string is palindrome then it is not repeated, else there is a repetition. One special case
 * we need to handle for inputs like “AAA”, which are palindrome but their repeated subsequence exists. Repeated
 * subsequence exists for a palindrome string if it is of odd length and its middle letter is same as left(or right)
 * character.
 * 
 * Time - O(n) Space - O(n)
 *
 */
public class RepeatedSubsequenceInString {

	public boolean isRepeated(String in) {
		int len = in.length();
		Map<Character, Integer> charCount = new HashMap<>();

		// count the frequency of each character in string
		for (char ch : in.toCharArray()) {
			int count = 0;
			if (!charCount.containsKey(ch)) {
				count++;
			} else {
				count = charCount.get(ch) + 1;
			}
			charCount.put(ch, count);
		}

		// remove non-repeated chars
		String str = "";
		for (int i = 0; i < len; i++) {
			if (charCount.get(in.charAt(i)) > 1) {
				str += in.charAt(i);
			}
		}

		int l = str.length();
		if (isPalindrome(str, 0, l - 1)) {

			// special case(if odd number of same char exist)
			if ((l & 1) == 1) {
				// to check so that string out of bounds can be avoided
				if (l / 2 >= 1) {
					return str.charAt(l / 2) == str.charAt(l / 2 - 1);
				}
			}

			return false;
		}
		return true;
	}

	private boolean isPalindrome(String in, int start, int end) {
		while (start < end) {
			if (in.charAt(start++) != in.charAt(end--))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		RepeatedSubsequenceInString rep = new RepeatedSubsequenceInString();
		System.out.println(rep.isRepeated("ABCABD"));
		System.out.println(rep.isRepeated("ABBB"));
		System.out.println(rep.isRepeated("AAB"));
		System.out.println(rep.isRepeated("AABBC"));
		System.out.println(rep.isRepeated("ABCDACB"));
		System.out.println(rep.isRepeated("ABCD"));
	}

}
