package com.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write an autocomplete class that returns all dictionary words with a given prefix.
 * 
 * dict: {"abc", "acd", "bcd", "def", "a", "aba"}
 * 
 * prefix: "a" ‑> "abc", "acd", "a", "aba"
 * 
 * prefix: "b" ‑> "bcd"
 * 
 * Complexity - Hard
 * 
 * Tags : Trie
 */
public class AutoComplete {

	// Trie node class
	private class TrieNode {
		String prefix;
		Map<Character, TrieNode> children;

		// Does this node represent the last character in a word?
		boolean isWord;

		private TrieNode(String prefix) {
			this.prefix = prefix;
			this.children = new HashMap<>();
		}

		@Override
		public String toString() {
			return "Node [prefix=" + prefix + ", children=" + children + ", isWord=" + isWord + "]";
		}
	}

	// The trie
	private TrieNode trie;

	// Construct the trie from the dictionary
	public AutoComplete(String[] dict) {
		trie = new TrieNode("");
		for (String s : dict)
			insertWord(s);
	}

	// Insert a word into the trie
	private void insertWord(String s) {
		// Iterate through each character in the string. If the character is not
		// already in the trie then add it
		TrieNode curr = trie;
		for (int i = 0; i < s.length(); i++) {
			if (!curr.children.containsKey(s.charAt(i))) {
				curr.children.put(s.charAt(i), new TrieNode(s.substring(0, i + 1)));
			}
			curr = curr.children.get(s.charAt(i));
			if (i == s.length() - 1)
				curr.isWord = true;
		}
	}

	// Find all words in trie that start with prefix
	public List<String> getWordsForPrefix(String pre) {
		List<String> results = new ArrayList<>();

		// Iterate to the end of the prefix
		TrieNode curr = trie;
		for (char c : pre.toCharArray()) {
			if (curr.children.containsKey(c)) {
				curr = curr.children.get(c);
			} else {
				return results;
			}
		}

		// At the end of the prefix, find all child words
		findAllChildWords(curr, results);
		return results;
	}

	// Recursively find every child word
	private void findAllChildWords(TrieNode n, List<String> results) {
		if (n.isWord) {
			results.add(n.prefix);
		}

		for (Character c : n.children.keySet()) {
			findAllChildWords(n.children.get(c), results);
		}
	}

	public static void main(String[] args) {
		String[] dict = { "abc", "acd", "bcd", "def", "a", "aba" };
		AutoComplete ac = new AutoComplete(dict);
		ac.getWordsForPrefix("a").forEach(a -> System.out.print(a + " "));
		System.out.println();
		ac.getWordsForPrefix("b").forEach(a -> System.out.print(a + " "));
		System.out.println();
		ac.getWordsForPrefix("ab").forEach(a -> System.out.print(a + " "));
	}
}
