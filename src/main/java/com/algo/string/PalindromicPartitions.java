package com.algo.string;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Category : Hard
 * 
 * Tags : Recursion, DP
 *
 */
public class PalindromicPartitions {

	/**
	 * Problem : Given a string, a partitioning of the string is a palindrome partitioning if every substring of the
	 * partition is a palindrome. For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
	 * Determine the fewest cuts needed for a palindrome partitioning of a given string. For example, minimum of 3 cuts
	 * are needed for “ababbbabbababa”. The three cuts are “a|babbbab|b|ababa”. If a string is a palindrome, then
	 * minimum 0 cuts are needed. If a string of length n containing all different characters, then minimum n-1 cuts are
	 * needed.
	 */

	/**
	 * This problem is a variation of Matrix Chain Multiplication problem. If the string is a palindrome, then we simply
	 * return 0. Else, like the Matrix Chain Multiplication problem, we try making cuts at all possible places,
	 * recursively calculate the cost for each cut and return the minimum value.
	 * 
	 * Let the given string be str and minPalPartion() be the function that returns the fewest cuts needed for
	 * palindrome partitioning. following is the optimal substructure property.
	 * 
	 * Using Recursion
	 * 
	 * // i is the starting index and j is the ending index. i must be passed as 0 and j as n-1 minPalPartion(str, i, j)
	 * = 0 if i == j. // When string is of length 1. minPalPartion(str, i, j) = 0 if str[i..j] is palindrome.
	 * 
	 * // If none of the above conditions is true, then minPalPartion(str, i, j) can be // calculated recursively using
	 * the following formula. minPalPartion(str, i, j) = Min { minPalPartion(str, i, k) + 1 + minPalPartion(str, k+1, j)
	 * } where k varies from i to j-1
	 * 
	 */
	public int minPalindromePartitionsRecursive(String s) {
		return recurse(s, 0, s.length() - 1);
	}

	private int recurse(String s, int left, int right) {
		if (left >= right || isPalindrome(s, left, right)) {
			return 0;
		}

		int min = Integer.MAX_VALUE;
		for (int i = left; i < right; i++) {
			int count = recurse(s, left, i) + 1 + recurse(s, i + 1, right);
			min = Math.min(min, count);
		}

		return min;
	}

	private boolean isPalindrome(String s, int left, int right) {
		while (left <= right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			} else {
				left++;
				right--;
			}
		}
		return true;
	}

	// T - O(n^2)
	public int minPalindromePartitionsDP(String str) {
		// Get the length of the string
		int n = str.length();

		/*
		 * Create two arrays to build the solution in bottom up manner C[i] = Minimum number of cuts needed for
		 * palindrome partitioning of substring str[0..i] P[i][j] = true if substring str[i..j] is palindrome, else
		 * false Note that C[i] is 0 if P[0][i] is true
		 */
		int[] C = new int[n];
		boolean[][] P = new boolean[n][n];

		int i, j, L; // different looping variables

		// Every substring of length 1 is a palindrome
		for (i = 0; i < n; i++) {
			P[i][i] = true;
		}

		/*
		 * L is substring length. Build the solution in bottom up manner by considering all substrings of length
		 * starting from 2 to n.
		 */
		for (L = 2; L <= n; L++) {
			// For substring of length L, set different
			// possible starting indexes
			for (i = 0; i < n - L + 1; i++) {
				j = i + L - 1; // Set ending index

				// If L is 2, then we just need to
				// compare two characters. Else need to
				// check two corner characters and value
				// of P[i+1][j-1]
				if (L == 2)
					P[i][j] = (str.charAt(i) == str.charAt(j));
				else
					P[i][j] = (str.charAt(i) == str.charAt(j)) && P[i + 1][j - 1];
			}
		}

		for (i = 0; i < n; i++) {
			if (P[0][i] == true)
				C[i] = 0;
			else {
				C[i] = Integer.MAX_VALUE;
				for (j = 0; j < i; j++) {
					if (P[j + 1][i] == true && 1 + C[j] < C[i])
						C[i] = 1 + C[j];
				}
			}
		}

		// Return the min cut value for complete
		// string. i.e., str[0..n-1]
		return C[n - 1];
	}

	/**
	 * To print all palindromic partitions
	 * 
	 * @return
	 */
	public List<List<String>> printAllPalindromicPartitions(String input) {
		int n = input.length();

		// To Store all palindromic partitions
		List<List<String>> allPart = new ArrayList<>();

		// To store current palindromic partition
		Deque<String> currPart = new LinkedList<String>();

		// Call recursive function to generate
		// all partiions and store in allPart
		allPalPartitonsUtil(allPart, currPart, 0, n, input);

		return allPart;
	}

	/*
	 * Recursive function to find all palindromic partitions of input[start..n-1] allPart --> A ArrayList of Deque of
	 * strings. Every Deque inside it stores a partition currPart --> A Deque of strings to store current partition
	 */
	private void allPalPartitonsUtil(List<List<String>> allPart, Deque<String> currPart, int start, int n,
			String input) {
		// If 'start' has reached len
		if (start >= n) {
			allPart.add(new ArrayList<>(currPart));
			return;
		}

		// Pick all possible ending points for substrings
		for (int i = start; i < n; i++) {

			// If substring str[start..i] is palindrome
			if (isPalindrome(input, start, i)) {

				// Add the substring to result
				currPart.addLast(input.substring(start, i + 1));

				// Recur for remaining remaining substring
				allPalPartitonsUtil(allPart, currPart, i + 1, n, input);

				// Remove substring str[start..i] from current
				// partition
				currPart.removeLast();
			}
		}
	}

	public static void main(String[] args) {
		PalindromicPartitions pp = new PalindromicPartitions();
		System.out.println(pp.minPalindromePartitionsRecursive("ababbbabbababa"));
		System.out.println(pp.minPalindromePartitionsDP("ababbbabbababa"));
		pp.printAllPalindromicPartitions("nitin").forEach(a -> System.out.println(a));
	}
}
