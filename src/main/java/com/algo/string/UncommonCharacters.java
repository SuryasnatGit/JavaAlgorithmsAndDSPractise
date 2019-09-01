package com.algo.string;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Find and print the uncommon characters of the two given strings in sorted
 * order. Here uncommon character means that either the character is present in
 * one string or it is present in other string but not in both. The strings
 * contain only lowercase characters and can contain duplicates.
 * 
 * Examples:
 * 
 * Input : str1 = "characters" str2 = "alphabets" Output : b c l p r
 * 
 * Input : str1 = "geeksforgeeks" str2 = "geeksquiz" Output : f i o q r u z
 * 
 * @author surya
 *
 */
public class UncommonCharacters {

	/**
	 * Time complexity - O(m * n/2) + O(n * m/2) ~= O(mn) Space complexity - O(m +
	 * n)
	 * 
	 * @param s1
	 * @param s2
	 */
	public void findUncommonCharacters_naive(String s1, String s2) {
		Set<Character> result = new TreeSet<>();
		for (char c : s1.toCharArray()) {
			// search each c in s2
			if (!isNotFound(c, s2))
				result.add(c);
		}

		// similarly repeat for s2
		for (char c : s2.toCharArray()) {
			// search each c in s1
			if (!isNotFound(c, s1))
				result.add(c);
		}

		System.out.println(result);
	}

	/**
	 * time - T(n/2) + c space - O(1)
	 * 
	 * @param c
	 * @param s
	 * @return
	 */
	private boolean isNotFound(char c, String s) {
		int l = 0;
		int r = s.length() - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (s.charAt(mid) == c)
				return true;
			else if (s.indexOf(c) > mid)
				l = mid + 1;
			else
				r = mid - 1;
		}
		return false;
	}

	/**
	 * Time Complexity: O(m + n), where m and n are the sizes of the two strings
	 * respectively
	 * 
	 * @param s1
	 * @param s2
	 */
	public void findUncommonCharacters_hashing(String s1, String s2) {
		int[] arr = new int[26]; // to contain the 26 small case letters
		Arrays.fill(arr, 0);

		for (int i = 0; i < s1.length(); i++) {
			arr[s1.charAt(i) - 'a'] = 1; // means this char is not present in s2
		}

		for (int i = 0; i < s2.length(); i++) {
			if (arr[s2.charAt(i) - 'a'] == 1 || arr[s2.charAt(i) - 'a'] == -1)
				arr[s2.charAt(i) - 'a'] = -1;
			else
				arr[s2.charAt(i) - 'a'] = 2; // means this char is not present in s1
		}

		for (int i = 0; i < 26; i++) {
			if (arr[i] == 1 || arr[i] == 2)
				System.out.print((char) (i + 'a') + " ");
		}
	}

	public static void main(String[] args) {
		UncommonCharacters uc = new UncommonCharacters();

		String s1 = "characters";
		String s2 = "alphabets";

		uc.findUncommonCharacters_naive(s1, s2);
		uc.findUncommonCharacters_naive("geeksforgeeks", "geeksquiz");
		uc.findUncommonCharacters_hashing(s1, s2);
	}

}
