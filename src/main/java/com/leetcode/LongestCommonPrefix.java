package com.leetcode;

import com.algo.ds.trie.Trie;

/**
 * Problem Statement: Given a set of strings, find the longest common prefix.
 * 
 * Examples:
 * 
 * Input: {"geeksforgeeks", "geeks", "geek", "geezer"} Output: "gee"
 * 
 * Input: {"apple", "ape", "april"} Output: "ap"
 *
 * The longest common prefix for an array of strings is the common prefix between 2 most dissimilar
 * strings. For example, in the given array {“apple”, “ape”, “zebra”}, there is no common prefix
 * because the 2 most dissimilar strings of the array “ape” and “zebra” do not share any starting
 * characters.
 * 
 * 
 */
public class LongestCommonPrefix {

	/**
	 * We start with an example. Suppose there are two strings- “geeksforgeeks” and “geeks”. What is the
	 * longest common prefix in both of them? It is “geeks”.
	 * 
	 * Now let us introduce another word “geek”. So now what is the longest common prefix in these three
	 * words ? It is “geek”
	 * 
	 * We can see that the longest common prefix holds the associative property, i.e-
	 * 
	 * LCP(string1, string2, string3) = LCP (LCP (string1, string2), string3)
	 * 
	 * Like here
	 * 
	 * LCP (“geeksforgeeks”, “geeks”, “geek”) = LCP (LCP (“geeksforgeeks”, “geeks”), “geek”) = LCP
	 * (“geeks”, “geek”) = “geek” So we can make use of the above associative property to find the LCP
	 * of the given strings. We one by one calculate the LCP of each of the given string with the LCP so
	 * far. The final result will be our longest common prefix of all the strings.
	 * 
	 * Note that it is possible that the given strings have no common prefix. This happens when the
	 * first character of all the strings are not same.
	 * 
	 * Time Complexity : Since we are iterating through all the strings and for each string we are
	 * iterating though each characters, so we can say that the time complexity is O(N M) where,
	 * 
	 * N = Number of strings M = Length of the largest string string
	 * 
	 * Auxiliary Space : To store the longest prefix string we are allocating space which is O(M).
	 * 
	 * 
	 * 
	 * @param input
	 * @return
	 */
	public String lcpWordByWordMatching(String[] input) {
		String prefix = input[0];
		for (int i = 1; i < input.length; i++) {
			prefix = lcpWordByWordMatchingUtil(prefix, input[i]);
		}
		return prefix;
	}

	private String lcpWordByWordMatchingUtil(String prefix, String s) {
		String res = "";
		for (int i = 0, j = 0; i < prefix.length() && j < s.length(); i++, j++) {
			if (prefix.charAt(i) != s.charAt(j))
				break;
			res += prefix.charAt(i);
		}
		return res;
	}

	/**
	 * In this algorithm, instead of going through the strings one by one, we will go through the
	 * characters one by one.
	 * 
	 * Time Complexity : Since we are iterating through all the characters of all the strings, so we can
	 * say that the time complexity is O(N M) where,
	 * 
	 * N = Number of strings M = Length of the largest string string Auxiliary Space : To store the
	 * longest prefix string we are allocating space which is O(M).
	 * 
	 * 
	 * 
	 * @param input
	 * @return
	 */
	public String lcpCharByCharMatching(String[] input) {
		int minLength = findMinLength(input);
		String res = "";
		for (int i = 0; i < minLength; i++) {
			char c = input[0].charAt(i);
			for (int j = 1; j < input.length; j++) {
				if (input[j].charAt(i) != c)
					return res;
			}
			res += c;
		}
		return res;
	}

	private int findMinLength(String[] input) {
		int minLength = Integer.MAX_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (minLength > input[i].length())
				minLength = input[i].length();
		}
		return minLength;
	}

	/**
	 * In this algorithm, a divide and conquer approach is discussed. We first divide the arrays of
	 * string into two parts. Then we do the same for left part and after that for the right part. We
	 * will do it until and unless all the strings become of length 1. Now after that, we will start
	 * conquering by returning the common prefix of the left and the right strings.
	 *
	 * Time Complexity : Since we are iterating through all the characters of all the strings, so we can
	 * say that the time complexity is O(N M) where,
	 * 
	 * N = Number of strings M = Length of the largest string string Auxiliary Space : To store the
	 * longest prefix string we are allocating space which is O(M Log N).
	 * 
	 * 
	 * @param input
	 * @param low
	 * @param high
	 * @return
	 */
	public String lcpDivideAndConqour(String[] input, int low, int high) {
		// exit criteria
		if (low == high)
			return input[low];
		if (low < high) {
			int mid = low + (high - low) / 2; // to avoid int overflow for large values
			String s1 = lcpDivideAndConqour(input, low, mid);
			String s2 = lcpDivideAndConqour(input, mid + 1, high);
			return lcpWordByWordMatchingUtil(s1, s2);
		}
		return null;
	}

	/**
	 * Time Complexity : The recurrence relation is
	 * 
	 * T(M) = T(M/2) + O(MN) where
	 * 
	 * N = Number of strings M = Length of the largest string string So we can say that the time
	 * complexity is O(NM log M)
	 * 
	 * Auxiliary Space: To store the longest prefix string we are allocating space which is O(N) where,
	 * N = length of the largest string among all the strings
	 * 
	 * @param input
	 * @return
	 */
	public String lcpBinarySearch(String[] input) {
		int index = findMinLength(input);
		String prefix = "";
		int low = 0, high = index;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (allContainsPrefix(input, input.length, input[0], low, mid)) {
				// if all strings in input array contains the prefix then append the substring to answer
				prefix += input[0].substring(low, mid + 1);
				// go for the right part
				low = mid + 1;
			} else {
				// go for the left part
				high = mid - 1;
			}
		}
		return prefix;
	}

	private boolean allContainsPrefix(String[] input, int length, String str, int start, int end) {
		for (int i = 0; i < length; i++) {
			for (int j = start; j < end; j++) {
				if (input[i].charAt(j) != str.charAt(j))
					return false;
			}
		}
		return true;
	}

	/**
	 * In this article, an approach using Trie date structure is discussed. Steps:
	 * 
	 * Insert all the words one by one in the trie. After inserting we perform a walk on the trie. In
	 * this walk, go deeper until we find a node having more than 1 children(branching occurs) or 0
	 * children (one of the string gets exhausted). This is because the characters (nodes in trie) which
	 * are present in the longest common prefix must be the single child of its parent, i.e- there
	 * should not be a branching in any of these nodes.
	 * 
	 * Time Complexity : Inserting all the words in the trie takes O(MN) time and performing a walk on
	 * the trie takes O(M) time, where-
	 * 
	 * N = Number of strings M = Length of the largest string string Auxiliary Space: To store all the
	 * strings we need to allocate O(26*M*N) ~ O(MN) space for the Trie.
	 * 
	 * @param input
	 * @return
	 */
	public String lcpTrie(String[] input) {
		Trie trie = new Trie();
		for (String s : input) {
			trie.insert(s);
		}
		return trie.lcpFromTrie();
	}

	public static void main(String[] args) {
		LongestCommonPrefix lcp = new LongestCommonPrefix();
		String[] input = new String[] { "geeksforgeeks", "geeks", "geek", "geezer" };
		System.out.println("Word by word matching :" + lcp.lcpWordByWordMatching(input));
		System.out.println("Char by char matching :" + lcp.lcpCharByCharMatching(input));
		System.out.println("Divide and conqour :" + lcp.lcpDivideAndConqour(input, 0, input.length - 1));
		System.out.println("binary search :" + lcp.lcpBinarySearch(input));
		System.out.println("Trie :" + lcp.lcpTrie(input));
	}

}
