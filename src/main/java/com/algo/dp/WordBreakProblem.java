package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated
 * sequence of dictionary words. See following examples for more details. This is a famous Google interview question,
 * also being asked by many other companies now a days.
 * 
 * Consider the following dictionary { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
 * 
 * Input: ilike Output: Yes . The string can be segmented as "i like".
 * 
 * Input: ilikesamsung Output: Yes. The string can be segmented as "i like samsung" or "i like sam sung".
 * 
 * Category : Hard
 * 
 * @author M_402201
 *
 */
public class WordBreakProblem {

	// set to hold dictionary values
	private static Set<String> dictionary = new HashSet<>();

	/**
	 * Sol 1 : returns true if the word can be segmented into parts such that each part is contained in dictionary.
	 * 
	 * time complexity - So to generalize this, for a string with length n, the recursion tree will have 2^(n-1) nodes,
	 * i.e., the time complexity is O(2^n).
	 */
	public static boolean wordBreakRecursive(String word) {
		int size = word.length();

		// base case
		if (size == 0)
			return true;

		// else check for all words
		for (int i = 1; i <= size; i++) {
			// Now we will first divide the word into two parts ,
			// the prefix will have a length of i and check if it is
			// present in dictionary ,if yes then we will check for
			// suffix of length size-i recursively. if both prefix and
			// suffix are present the word is found in dictionary.

			if (dictionary.contains(word.substring(0, i)) && wordBreakRecursive(word.substring(i, size)))
				return true;
		}

		// if all cases failed then return false
		return false;
	}

	/**
	 * Sol 2 - Returns true if string can be segmented into space separated words, otherwise returns false.
	 * 
	 * time complexity - O(m * n) where m = length of string and n = size of dictionary
	 * 
	 * @param str
	 * @return
	 */
	public boolean wordBreakDP(String str) {
		int size = str.length();
		if (size == 0)
			return true;

		boolean[] t = new boolean[size + 1];
		t[0] = true; // set first to be true, why?
		// Because we need initial state

		for (int i = 0; i < size; i++) {
			// should continue from match position
			if (!t[i])
				continue;

			for (String dictWord : dictionary) {
				int len = dictWord.length();
				int end = i + len;
				if (end > size)
					continue;

				if (t[end])
					continue;

				if (str.substring(i, end).equals(dictWord)) {
					t[end] = true;
				}
			}
		}

		return t[size];
	}

	/**
	 * Sol 3 - In Solution 2, if the size of the dictionary is very large, the time is bad. Instead we can solve the
	 * problem in O(n^2) time (n is the length of the string).
	 * 
	 * 
	 * @param input
	 * @return
	 */
	public boolean wordBreakDP_optimized(String input) {
		int size = input.length();
		if (size == 0)
			return true;

		int[] arr = new int[size + 1];
		// fill
		Arrays.fill(arr, -1);

		// initialize
		arr[0] = 0;

		for (int i = 0; i < size; i++) {
			if (arr[i] != -1) {
				for (int j = i + 1; j <= size; j++) {
					if (dictionary.contains(input.substring(i, j))) {
						arr[j] = i;
					}
				}
			}
		}

		return arr[size] != -1 ? true : false;
	}

	/**
	 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a
	 * valid dictionary word. Return all such possible sentences. For example, given s = "catsanddog", dict = ["cat",
	 * "cats", "and", "sand", "dog"], the solution is ["cats and dog", "cat sand dog"].
	 * 
	 * This problem is very similar to Word Break. Instead of using a boolean array to track the matched positions, we
	 * need to track the actual matched words. Then we can use depth first search to get all the possible paths, i.e.,
	 * the list of strings.
	 * 
	 * TODO : revisit
	 * 
	 * @param input
	 * @return
	 */
	public List<String> returnAllSequencesDP(String s) {
		// create an array of ArrayList<String>
		List<String> dp[] = new ArrayList[s.length() + 1];
		dp[0] = new ArrayList<String>();

		for (int i = 0; i < s.length(); i++) {
			if (dp[i] == null)
				continue;

			for (String word : dictionary) {
				int len = word.length();
				int end = i + len;
				if (end > s.length())
					continue;

				if (s.substring(i, end).equals(word)) {
					if (dp[end] == null) {
						dp[end] = new ArrayList<String>();
					}
					dp[end].add(word);
				}
			}
		}

		List<String> result = new ArrayList<String>();
		if (dp[s.length()] == null)
			return result;

		List<String> temp = new ArrayList<String>();
		dfs(dp, s.length(), result, temp);

		return result;
	}

	private void dfs(List<String> dp[], int end, List<String> result, List<String> tmp) {
		if (end <= 0) {
			String path = tmp.get(tmp.size() - 1);
			for (int i = tmp.size() - 2; i >= 0; i--) {
				path += " " + tmp.get(i);
			}

			result.add(path);
			return;
		}

		for (String str : dp[end]) {
			tmp.add(str);
			dfs(dp, end - str.length(), result, tmp);
			tmp.remove(tmp.size() - 1);
		}
	}

	public static void main(String[] args) {

		WordBreakProblem word = new WordBreakProblem();

		// array of strings to be added in dictionary set.
		String temp_dictionary[] = { "mobile", "samsung", "sam", "sung", "man", "mango", "icecream", "and", "go", "i",
				"like", "ice", "cream" };

		// loop to add all strings in dictionary set
		for (String temp : temp_dictionary) {
			dictionary.add(temp);
		}

		// sample input cases
		System.out.println(word.wordBreakDP("ilikesamsung"));
		System.out.println(word.wordBreakDP("iiiiiiii"));
		System.out.println(word.wordBreakDP(""));
		System.out.println(word.wordBreakDP("ilikelikeimangoiii"));
		System.out.println(word.wordBreakDP("samsungandmango"));
		System.out.println(word.wordBreakDP("samsungandmangok"));

		System.out.println(word.wordBreakDP_optimized("ilikesamsung"));
		System.out.println(word.wordBreakDP_optimized("samsungandmangok"));

		System.out.println(word.returnAllSequencesDP("ilikesamsung"));
	}
}
