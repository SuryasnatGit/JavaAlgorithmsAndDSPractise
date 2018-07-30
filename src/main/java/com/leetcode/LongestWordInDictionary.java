package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string and a string dictionary, find the longest string in the
 * dictionary that can be formed by deleting some characters of the given
 * string. If there are more than one possible results, return the longest word
 * with the smallest lexicographical order. If there is no possible result,
 * return the empty string.
 * 
 * LeetCode 524 - Longest Word in Dictionary through Deleting
 * 
 * Example 1: Input: s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 
 * Output: "apple" Example 2: Input: s = "abpcplea", d = ["a","b","c"]
 * 
 * Output: "a" Note: All the strings in the input will only contain lower-case
 * letters. The size of the dictionary won't exceed 1,000. The length of all the
 * strings in the input won't exceed 1,000.
 *
 * 
 */
public class LongestWordInDictionary {

	/**
	 * Approach 1: We sort the input dictionary by longest length and lexicography.
	 * Then, we iterate through the dictionary exactly once. In the process, the
	 * first dictionary word in the sorted dictionary which appears as a subsequence
	 * in the input string s must be the desired solution.
	 * 
	 * Not efficient.
	 * 
	 * @return
	 */
	public String findLongestWord(String s, List<String> d) {
		// sorting by longest length and lexicographically
		Collections.sort(d,
				(a, b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) : a.compareTo(b));
		for (String dictWord : d) {
			int i = 0;
			for (char c : s.toCharArray()) {
				if (i < dictWord.length() && c == dictWord.charAt(i))
					i++;
			}
			if (i == dictWord.length())
				return dictWord;
		}
		return "";
	}

	/**
	 * Approach 2: An alternate, more efficient solution which avoids sorting the
	 * dictionary. Time Complexity: O(nk), where n is the length of string s and k
	 * is the number of words in the dictionary.
	 * 
	 * @param s
	 * @param d
	 * @return
	 */
	public String findLongestWord_alternative(String s, List<String> d) {
		String longest = "";
		for (String dictWord : d) {
			int i = 0;
			for (char c : s.toCharArray())
				if (i < dictWord.length() && c == dictWord.charAt(i))
					i++;

			if (i == dictWord.length() && dictWord.length() >= longest.length())
				if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0)
					longest = dictWord;
		}
		return longest;

	}

	/**
	 * Approach 3: This problem reduces to finding if a string is subsequence of
	 * another string or not. We traverse all dictionary words and for every word,
	 * we check if it is subsequence of given string and is largest of all such
	 * words. We finally return the longest word with given string as subsequence.
	 * 
	 * Time Complexity : O(N*K*n) Here N is the length of dictionary and n is the
	 * length of given string ‘str’ and K – maximum length of words in the
	 * dictionary.
	 * 
	 * Auxiliary Space : O(1)
	 * 
	 * 
	 * @param dict
	 * @param str
	 * @return
	 */
	public String findLongestString(List<String> dict, String str) {
		int len = 0;
		String longest = null;
		for (String s : dict) {
			if (len < s.length() && isSubSequence(s, str)) {
				len = s.length();
				longest = s;
			}
		}
		return longest;
	}

	/**
	 * returns true if s1 is a substring of s2
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean isSubSequence(String s1, String s2) {
		int l1 = s1.length();
		int l2 = s2.length();
		int i = 0; // track length of s1
		for (int j = 0; j < l2 && i < l1; j++) {
			if (s1.charAt(i) == s2.charAt(j))
				i++;
		}
		return i == l1;
	}

	/**
	 * Approach 4: Using Trie
	 *
	 */
	public static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
		}

		public void add(String s) {
			Node current = root;
			for (int i = 0; i < s.length(); i++) {
				add(current, s.charAt(i));
				current = current.children.get(s.charAt(i));
			}
		}

		public void add(Node root, char c) {
			if (root == null)
				return;
			if (!root.children.containsKey(c))
				root.children.put(c, new Node());
		}

		public String findLongestString(String s) {
			String currentLongest = "";
			int index = 0;
			while (index + currentLongest.length() < s.length()) {
				Node current = root;
				StringBuilder sb = new StringBuilder();
				for (int i = index; i < s.length(); i++) {
					if (current.children.containsKey(s.charAt(i))) {
						sb.append(s.charAt(i));
						current = current.children.get(s.charAt(i));
					}
				}
				if (sb.length() > currentLongest.length()) {
					currentLongest = sb.toString();
				}
				index++;
			}
			return currentLongest;
		}
	}

	public static class Node {
		HashMap<Character, Node> children;

		public Node() {
			this.children = new HashMap<>();
		}
	}

	public static void main(String[] args) {
		LongestWordInDictionary lon = new LongestWordInDictionary();
		List<String> dict = new ArrayList<>();
		dict.add("ale");
		dict.add("apple");
		dict.add("monkey");
		dict.add("plea");
		String str = "abpcpla";
		System.out.println(lon.findLongestString(dict, str));
		System.out.println(lon.findLongestWord(str, dict));
		System.out.println(lon.findLongestWord_alternative(str, dict));
	}

}
