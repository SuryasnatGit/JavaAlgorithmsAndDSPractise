package com.algo.string;

/**
 * Date 07/29/2015
 * 
 * @author Tushar Roy
 *
 *         Given a string find longest palindromic substring in this string.
 *
 *         References http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 *         http://www.geeksforgeeks.org/longest-palindromic-substring-set-2/
 *         http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 *         http://www.akalin.cx/longest-palindrome-linear-time http://tarokuriyama.com/projects/palindrome2.php
 */
public class LongestPalindromeSubstring {

	/**
	 * We can find the longest palindrome substring in O(n^2) time with O(1) extra space. The idea is to generate all
	 * even length and odd length palindromes and keep track of the longest palindrome seen so far.
	 * 
	 * Step to generate odd length palindrome: Fix a centre and expand in both directions for longer palindromes.
	 * 
	 * Step to generate even length palindrome Fix two centre ( low and high ) and expand in both directions for longer
	 * palindromes.
	 * 
	 * @param arr
	 * @return
	 */
	public int longestPalindromeSubstringEasy(char arr[]) {

		int longest_substring = 1;
		int x, y;
		int palindrome;
		// One by one consider every character as center point of even and length palindromes
		for (int i = 0; i < arr.length; i++) {
			// Find the longest even length palindrome with center points as i and i+1.
			x = i;
			y = i + 1;
			palindrome = 0;
			while (x >= 0 && y < arr.length && arr[x] == arr[y]) {
				x--;
				y++;
				palindrome += 2;
			}
			longest_substring = Math.max(longest_substring, palindrome);

			// Find the longest odd length palindrome with center point as i-1 and i+1.
			x = i - 1;
			y = i + 1;
			palindrome = 1;
			while (x >= 0 && y < arr.length && arr[x] == arr[y]) {
				x--;
				y++;
				palindrome += 2;
			}
			longest_substring = Math.max(longest_substring, palindrome);
		}
		return longest_substring;
	}

	/**
	 * Linear time Manacher's algorithm to find longest palindromic substring. There are 4 cases to handle Case 1 :
	 * Right side palindrome is totally contained under current palindrome. In this case do not consider this as center.
	 * Case 2 : Current palindrome is proper suffix of input. Terminate the loop in this case. No better palindrom will
	 * be found on right. Case 3 : Right side palindrome is proper suffix and its corresponding left side palindrome is
	 * proper prefix of current palindrome. Make largest such point as next center. Case 4 : Right side palindrome is
	 * proper suffix but its left corresponding palindrome is be beyond current palindrome. Do not consider this as
	 * center because it will not extend at all.
	 *
	 * To handle even size palindromes replace input string with one containing $ between every input character and in
	 * start and end.
	 */
	public int longestPalindromicSubstringLinear(char input[]) {
		int index = 0;
		// preprocess the input to convert it into type abc -> $a$b$c$ to handle even length case.
		// Total size will be 2*n + 1 of this new array.
		char newInput[] = new char[2 * input.length + 1];
		for (int i = 0; i < newInput.length; i++) {
			if (i % 2 != 0) {
				newInput[i] = input[index++];
			} else {
				newInput[i] = '$';
			}
		}

		// create temporary array for holding largest palindrome at every point. There are 2*n + 1 such points.
		int T[] = new int[newInput.length];
		int start = 0;
		int end = 0;
		// here i is the center.
		for (int i = 0; i < newInput.length;) {
			// expand around i. See how far we can go.
			while (start > 0 && end < newInput.length - 1 && newInput[start - 1] == newInput[end + 1]) {
				start--;
				end++;
			}
			// set the longest value of palindrome around center i at T[i]
			T[i] = end - start + 1;

			// this is case 2. Current palindrome is proper suffix of input. No need to proceed. Just break out of loop.
			if (end == T.length - 1) {
				break;
			}
			// Mark newCenter to be either end or end + 1 depending on if we dealing with even or old number input.
			int newCenter = end + (i % 2 == 0 ? 1 : 0);

			for (int j = i + 1; j <= end; j++) {

				// i - (j - i) is left mirror. Its possible left mirror might go beyond current center palindrome. So
				// take minimum
				// of either left side palindrome or distance of j to end.
				T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
				// Only proceed if we get case 3. This check is to make sure we do not pick j as new center for case 1
				// or case 4
				// As soon as we find a center lets break out of this inner while loop.
				if (j + T[i - (j - i)] / 2 == end) {
					newCenter = j;
					break;
				}
			}
			// make i as newCenter. Set right and left to atleast the value we already know should be matching based of
			// left side palindrome.
			i = newCenter;
			end = i + T[i] / 2;
			start = i - T[i] / 2;
		}

		// find the max palindrome in T and return it.
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < T.length; i++) {
			int val;
			/*
			 * if(i%2 == 0) { val = (T[i] -1)/2; } else { val = T[i]/2; }
			 */
			val = T[i] / 2;
			if (max < val) {
				max = val;
			}
		}
		return max;
	}

	/**
	 * Let s be the input string, i and j are two indices of the string. Define a 2-dimension array "table" and let
	 * table[i][j] denote whether a substring from i to j is palindrome.
	 * 
	 * Changing condition:
	 * 
	 * table[i+1][j-1] == 1/true && s.charAt(i) == s.charAt(j) => table[i][j] == 1/true
	 * 
	 * Time - O(n^2) space O(n^2)
	 * 
	 * @param str
	 * @return
	 */
	public int longestPalindromeDynamic(char[] str) {
		// table[i][j] will be false if substring str[i..j] is not palindrome else table[i][j] will be true
		boolean T[][] = new boolean[str.length][str.length];

		// All substrings of length 1 are palindromes
		for (int i = 0; i < T.length; i++) {
			T[i][i] = true;
		}

		// pointer for start
		int start = 0;
		// counter for max length
		int max = 1;
		// Check for lengths greater than 2. l is length of substring
		for (int l = 2; l <= str.length; l++) {
			int len = 0;
			// fix the starting index
			for (int i = 0; i < str.length - l + 1; i++) {
				// get the ending index j from starting index i and length l
				int j = i + l - 1;
				len = 0;
				// checking for length == 2
				if (l == 2) {
					if (str[i] == str[j]) {
						T[i][j] = true;
						start = i;
						len = 2;
					}
				} else { // checking for length > 2
					if (str[i] == str[j] && T[i + 1][j - 1]) {
						T[i][j] = true;
						len = j - i + 1;
					}
				}
				if (len > max) {
					start = i;
					max = len;
				}
			}
		}
		// print max substring which is palindrome
		System.out.println(new String(str).substring(start, start + max));
		return max;
	}

	public static void main(String args[]) {
		LongestPalindromeSubstring lps = new LongestPalindromeSubstring();
		System.out.println(lps.longestPalindromeDynamic("abba".toCharArray()));
		System.out.println(lps.longestPalindromeSubstringEasy("abbababbacd".toCharArray()));
		System.out.println(lps.longestPalindromeDynamic("abbababbacd".toCharArray()));
		// System.out.println(lps.longestPalindromicSubstringLinear("cdbabcbabdab".toCharArray()));
	}

}
