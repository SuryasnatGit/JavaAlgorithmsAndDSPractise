package com.algo.string;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Category : Hard
 */
public class LexicographicStringProblems {

	/**
	 * Solution 1 :
	 * 
	 * Given a string, find its rank among all its permutations sorted lexicographically. For example, rank of “abc” is
	 * 1, rank of “acb” is 2, and rank of “cba” is 6. Assume that string does not contain duplicate characters.
	 * 
	 * Examples:
	 * 
	 * Input : str[] = "acb" Output : Rank = 2
	 * 
	 * Input : str[] = "string" Output : Rank = 598
	 * 
	 * Input : str[] = "cba" Output : Rank = 6
	 * 
	 * rank of given string is count of smaller strings plus 1. Time - O(n^2)
	 * 
	 * @param str
	 * @return
	 */
	public int rank(char[] str) {
		int rank = 0;
		for (int i = 0; i < str.length; i++) {
			int num = findNumberOfSmallerCharactersOnRight(i, str);
			rank += factorial(str.length - i - 1) * num;
		}
		return rank + 1;
	}

	private int findNumberOfSmallerCharactersOnRight(int index, char[] str) {
		int count = 0;
		for (int i = index + 1; i < str.length; i++) {
			if (str[i] < str[index]) {
				count++;
			}
		}
		return count;
	}

	private int factorial(int n) {
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact = i * fact;
		}
		return fact;
	}

	/**
	 * Solution 2 : optimal approach in O(n)
	 */
	private int MAX_CHAR = 256;

	/**
	 * A function to find rank of a string in all permutations of characters
	 */
	public int findRank(char[] str) {
		int len = str.length;
		int mul = fact(len);
		int rank = 1, i;

		// all elements of count[] are initialized with 0
		int count[] = new int[MAX_CHAR];

		// Populate the count array such that count[i] contains count of characters which are present in str
		// and are smaller than i
		populateAndIncreaseCount(count, str);

		for (i = 0; i < len; ++i) {
			mul /= len - i;

			// count number of chars smaller than str[i] fron str[i+1] to str[len-1]
			rank += count[str[i] - 1] * mul;

			// Reduce count of characters greater than str[i]
			updatecount(count, str[i]);
		}

		return rank;
	}

	/**
	 * A utility function to find factorial of n
	 * 
	 */
	private int fact(int n) {
		return (n <= 1) ? 1 : n * fact(n - 1);
	}

	/**
	 * Construct a count array where value at every index contains count of smaller characters in whole string
	 */
	private void populateAndIncreaseCount(int[] count, char[] str) {
		int i;

		for (i = 0; i < str.length; ++i)
			++count[str[i]];

		for (i = 1; i < MAX_CHAR; ++i)
			count[i] += count[i - 1];
	}

	/**
	 * Removes a character ch from count[] array constructed by populateAndIncreaseCount()
	 */
	private void updatecount(int[] count, char ch) {
		int i;
		for (i = ch; i < MAX_CHAR; ++i)
			--count[i];
	}

	// TODO: Lexicographic rank of a string with duplicate characters -
	// https://www.geeksforgeeks.org/lexicographic-rank-string-duplicate-characters/

	/**
	 * 
	 * https://www.geeksforgeeks.org/powet-set-lexicographic-order/
	 * 
	 * Input : abc
	 * 
	 * Output : a ab abc ac b bc c
	 * 
	 * The idea is to sort array first. After sorting, one by one fix characters and recursively generates all subsets
	 * starting from them. After every recursive call, we remove last character so that next permutation can be
	 * generated
	 * 
	 * @param input
	 */
	public void printStringPowerSetInLexicographicOrder(String input) {
		char[] arr = input.toCharArray();
		Arrays.sort(arr);
		powerSet(arr, input.length(), -1, "");
	}

	/**
	 * 
	 * @param arr
	 *            stores input string
	 * @param size
	 *            length of string
	 * @param index
	 *            index in current permutation
	 * @param curr
	 *            stores current permutation
	 */
	private void powerSet(char[] arr, int size, int index, String curr) {
		if (index == size)
			return;

		System.out.println(curr);
		for (int i = index + 1; i < size; i++) {
			curr += arr[i];
			powerSet(arr, size, i, curr);
			// after recursion backtrack
			curr = curr.substring(0, curr.length() - 1);
		}
	}

	/**
	 * https://www.geeksforgeeks.org/lexicographic-permutations-of-string/
	 */
	private TreeSet<String> combination = new TreeSet<String>();

	public void generateAllPermutationsInLexicographicOrder(String input) {
		permutation("", input);
	}

	private void permutation(String prefix, String input) {
		int length = input.length();
		if (length == 0)
			combination.add(prefix);
		else {
			for (int i = 0; i < length; i++) {
				String p = prefix + input.charAt(i);
				String s = input.substring(0, i) + input.substring(i + 1, length);
				permutation(p, s);
			}
		}
	}

	private void printAllCombination() {
		for (String str : combination) {
			System.out.println(str);
		}
	}

	public static void main(String args[]) {
		LexicographicStringProblems lrp = new LexicographicStringProblems();
		int rank = lrp.rank("STRING".toCharArray());
		// System.out.println(rank);
		// System.out.println(lrp.rank("ABC".toCharArray()));
		// System.out.println(lrp.rank("CBA".toCharArray()));

		System.out.println(lrp.findRank("cab".toCharArray()));

		// lrp.printStringPowerSetInLexicographicOrder("cab");
		// lrp.generateAllPermutationsInLexicographicOrder("abcd");
		// lrp.printAllCombination();
	}
}
