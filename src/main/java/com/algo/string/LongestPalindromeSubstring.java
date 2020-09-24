package com.algo.string;

/**
 * Given a string find longest palindromic substring in this string.
 *
 * References: http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * 
 * http://www.geeksforgeeks.org/longest-palindromic-substring-set-2/
 * 
 * http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 * 
 * http://www.akalin.cx/longest-palindrome-linear-time
 * 
 * http://tarokuriyama.com/projects/palindrome2.php
 * 
 * Category : Hard
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
	 * Since expanding a palindrome around its center could take O(n) time, the overall time complexity is O(n ^ 2).
	 * 
	 * space - O(1)
	 * 
	 * @param arr
	 * @return
	 */
	public int longestPalindromeSubstringlength(char arr[]) {

		int longest_substring = 1;
		int x, y;
		int palindrome;
		// One by one consider every character as center point of even and odd length palindromes
		for (int i = 0; i < arr.length; i++) {
			// Find the longest even length palindrome with center points as i and i+1.
			x = i;
			y = i + 1;
			palindrome = 1;
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

	public String longestPalindromeSubstring(String s) {

		if (s == null || s.length() == 0) {
			return s;
		}

		int x = 0, y = 0;

		for (int i = 0; i < s.length(); i++) {
			int length1 = expandAroundCentre(s, i, i);
			int length2 = expandAroundCentre(s, i, i + 1);
			int length = Math.max(length1, length2);

			if (length > y - x) {
				x = i - (length - 1) / 2;
				y = i + length / 2;
			}
		}
		return s.substring(x, y + 1);
	}

	private int expandAroundCentre(String s, int i, int j) {
		int left = i, right = j;
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}

	/**
	 * Linear time Manacher's algorithm to find longest palindromic substring.
	 * 
	 * Time - O(n), Space - O(n)
	 * 
	 * There are 4 cases to handle:
	 * 
	 * Case 1 : Right side palindrome is totally contained under current palindrome. In this case do not consider this
	 * as center. <br/>
	 * Case 2 : Current palindrome is proper suffix of input. Terminate the loop in this case. No better palindrom will
	 * be found on right. <br/>
	 * Case 3 : Right side palindrome is proper suffix and its corresponding left side palindrome is proper prefix of
	 * current palindrome. Make largest such point as next center. <br/>
	 * Case 4 : Right side palindrome is proper suffix but its left corresponding palindrome is be beyond current
	 * palindrome. Do not consider this as center because it will not extend at all.
	 *
	 * To handle even size palindromes replace input string with one containing $ between every input character and in
	 * start and end.
	 */
	public int longestPalindromicSubstringLengthLinear(char input[]) {
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

		// create temporary array for holding largest palindrome at every point. There are 2*n + 1 such
		// points.
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

			// this is case 2. Current palindrome is proper suffix of input. No need to proceed. Just break out
			// of loop.
			if (end == T.length - 1) {
				break;
			}
			// Mark newCenter to be either end or end + 1 depending on if we dealing with even or old number
			// input.
			int newCenter = end + (i % 2 == 0 ? 1 : 0);

			for (int j = i + 1; j <= end; j++) {

				// i - (j - i) is left mirror. Its possible left mirror might go beyond current center palindrome.
				// So
				// take minimum
				// of either left side palindrome or distance of j to end.
				T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
				// Only proceed if we get case 3. This check is to make sure we do not pick j as new center for case
				// 1
				// or case 4
				// As soon as we find a center lets break out of this inner while loop.
				if (j + T[i - (j - i)] / 2 == end) {
					newCenter = j;
					break;
				}
			}
			// make i as newCenter. Set right and left to atleast the value we already know should be matching
			// based of
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

	public static void main(String args[]) {
		LongestPalindromeSubstring lps = new LongestPalindromeSubstring();
		System.out.println(lps.longestPalindromeSubstringlength("abbababbacd".toCharArray()));
		System.out.println(lps.longestPalindromicSubstringLengthLinear("abbababbacd".toCharArray()));
		System.out.println(lps.longestPalindromeSubstring("babad"));
	}

}
