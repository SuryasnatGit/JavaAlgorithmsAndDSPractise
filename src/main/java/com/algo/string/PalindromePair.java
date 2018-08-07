package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so
 * that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 * 
 * Input: ["abcd","dcba","lls","s","sssll"] Output: [[0,1],[1,0],[3,2],[2,4]] Explanation: The
 * palindromes are ["dcbaabcd","abcddcba","slls","llssssll"] Example 2:
 * 
 * Input: ["bat","tab","cat"] Output: [[0,1],[1,0]] Explanation: The palindromes are
 * ["battab","tabbat"]
 * 
 * Reference https://leetcode.com/problems/palindrome-pairs/
 */
public class PalindromePair {

	/**
	 * Solution 1 - brute force
	 * 
	 * Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are
	 * palindrome.
	 * 
	 * Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
	 * 
	 * Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] ,
	 * then s2+s1 is palindrome.
	 * 
	 * Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing
	 * string of s1[0:cut] , then s1+s2 is palindrome.
	 * 
	 * To make the search faster, build a HashMap to store the String-idx pairs.
	 * 
	 * Complexity - O(nk) + O(nk) + O(nk^2) = O(nk^2)
	 * 
	 * @param words
	 * @return
	 */
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (words == null || words.length == 0) {
			return res;
		}
		// build the map save the key-val pairs: String - idx
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) { // O(n) where n is number of words
			map.put(words[i], i);
		}

		// special cases: "" can be combine with any palindrome string
		if (map.containsKey("")) {
			int blankIdx = map.get("");
			for (int i = 0; i < words.length; i++) {// O(n * k) where n is number of words
				if (isPalindrome(words[i])) {
					if (i == blankIdx)
						continue;
					res.add(Arrays.asList(blankIdx, i)); // O(1)
					res.add(Arrays.asList(i, blankIdx)); // O(1)
				}
			}
		}

		// find all string and reverse string pairs
		for (int i = 0; i < words.length; i++) { // O(n * k) where n is number of words
			String cur_r = reverseStr(words[i]);
			if (map.containsKey(cur_r)) {// O(1)
				int found = map.get(cur_r);// O(1)
				if (found == i)
					continue;
				res.add(Arrays.asList(i, found));// O(1)
			}
		}

		// complexity - O(n * k^2)
		// find the pair s1, s2 that
		// case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
		// case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
		for (int i = 0; i < words.length; i++) { // O(n) where n is number of words
			String cur = words[i];
			for (int cut = 1; cut < cur.length(); cut++) {// O(k) where k is length of word
				if (isPalindrome(cur.substring(0, cut))) { // O(k + k) = O(2k) = O(k) where k is length of word
					String cut_r = reverseStr(cur.substring(cut));// O(2k) = O(k)
					if (map.containsKey(cut_r)) {
						int found = map.get(cut_r);
						if (found == i)
							continue;
						res.add(Arrays.asList(found, i));
					}
				}
				if (isPalindrome(cur.substring(cut))) {
					String cut_r = reverseStr(cur.substring(0, cut));
					if (map.containsKey(cut_r)) {
						int found = map.get(cut_r);
						if (found == i)
							continue;
						res.add(Arrays.asList(i, found));
					}
				}
			}
		}

		return res;
	}

	/**
	 * O(k) where k is length of string
	 * 
	 * @param str
	 * @return
	 */
	private String reverseStr(String str) {
		java.lang.StringBuilder sb = new java.lang.StringBuilder(str);
		return sb.reverse().toString();
	}

	/**
	 * O(k) where k is length of string s
	 * 
	 * @param s
	 * @return
	 */
	private boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i <= j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	/**
	 * Brute force solution.Complexity - O(n^2 * k). Here n is the number of the words in the list and k
	 * is the maximum length that is checked for a palindrome.
	 * 
	 * 
	 * @param words
	 * @return
	 */
	public List<List<Integer>> palindromePairs_brute(String[] words) {
		List<List<Integer>> result = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i != j) {
					String s = "";
					s += words[i] + words[j];
					if (isPalindrome(s)) {
						List<Integer> pair = new ArrayList<>();
						pair.add(i);
						pair.add(j);
						result.add(pair);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Solution 2 - using manacher algorithm. TODO: implement later
	 * 
	 * @return
	 */
	public List<List<Integer>> palindromePairs_1() {
		return null;
	}

	/**
	 * Solution 3 - Using trie approach
	 * 
	 * @author surya
	 *
	 */
	class Trie {
		Map<Character, Trie> children = new HashMap<>();
		int idx = -1;
		List<Integer> pals = new ArrayList<>();

		@Override
		public String toString() {
			return "Trie [children=" + children + ", idx=" + idx + ", pals=" + pals + "]";
		}

		// insert words inverted
		public void insert(String word, int idx) {
			Trie curr = this;
			for (int i = word.length() - 1; i >= 0; i--) {
				// at each node in trie, mark if remaining part of the word is palindromic
				if (isPal(word, 0, i)) {
					curr.pals.add(idx);
				}
				if (!curr.children.containsKey(word.charAt(i))) {
					curr.children.put(word.charAt(i), new Trie());
				}
				curr = curr.children.get(word.charAt(i));
			}
			curr.idx = idx;
		}

	}

	private boolean isPal(String word, int i, int j) {
		while (i < j) {
			if (word.charAt(i++) != word.charAt(j--)) {
				return false;
			}
		}
		return true;
	}

	public List<List<Integer>> palindromePairs_trie(String[] words) {
		if (words == null || words.length < 2) {
			return Collections.emptyList();
		}

		Trie root = new Trie();
		List<List<Integer>> pals = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			root.insert(words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			match(root, words[i], i, pals);
		}
		return pals;
	}

	private void match(Trie root, String key, int i, List<List<Integer>> pals) {
		for (int j = 0; j < key.length(); j++) {
			// word shorter than key present, and remaining part of key is palindromic
			if (root.idx >= 0 && root.idx != i && isPal(key, j, key.length() - 1)) {
				pals.add(Arrays.asList(i, root.idx));
			}
			root = root.children.get(key.charAt(j));
			if (root == null) {
				return;
			}
		}
		// word of equal length of key present, and pals with the key
		if (root.idx >= 0 && root.idx != i) {
			pals.add(Arrays.asList(i, root.idx));
		}
		// words longer than key, and remaining parts of them are palindromic
		for (int j : root.pals) {
			if (i != j) {
				pals.add(Arrays.asList(i, j));
			}
		}
	}

    public static void main(String args[]) {
        PalindromePair palindromePair = new PalindromePair();
        String[] words = {"bat", "tab"};
        List<List<Integer>> result = palindromePair.palindromePairs(words);
        System.out.println(result);
        String[] words1 = {"abcd", "dcba", "lls", "s", "sssll"};
		result = palindromePair.palindromePairs_trie(words1);
        System.out.println(result);
        String[] words2 = {"", "abcd", "abba"};
        result = palindromePair.palindromePairs(words2);
        System.out.println(result);
        String[] words3 = {"a","abc","aba",""};
        result = palindromePair.palindromePairs(words3);
        System.out.println(result);
        String[] words4 = {"abcd","dcba","lls","s","sssll"};
		result = palindromePair.palindromePairs_brute(words4);
        System.out.println(result);
    }
}
