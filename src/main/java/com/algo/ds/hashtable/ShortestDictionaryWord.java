package com.algo.ds.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * There are a set of dictionary words and a set of license plate numbers. Write a code/algorithm to find the shortest
 * dictionary word which contains all the characters in the license plate. Ex: RC101 is the license plate number. The
 * shortest word that can be found in the dictionary is CAR.
 * 
 * Category : Hard
 */
public class ShortestDictionaryWord {

	private static List<String> dictionary = Arrays.asList("CAT", "MAN", "BAD", "GATE", "MARS");

	/**
	 * Brute force. Time - O(n) and Space - O(n) where n = num of words in dictionary
	 * 
	 * @param lpn
	 * @return
	 */
	public String getShortestWord(String lpn) {

		// make hashset from lpn
		Set<Character> lpnCharSet = new HashSet<>();
		for (char ch : lpn.toCharArray()) {
			if (!Character.isDigit(ch)) {
				lpnCharSet.add(ch);
			}
		}

		// make hashset for each word in dictionary
		String minWord = "";
		for (String dictWord : dictionary) {
			Set<Character> charSet = new HashSet<>();
			char[] charArray = dictWord.toCharArray();
			for (char ch : charArray) {
				charSet.add(ch);
			}

			if (charSet.containsAll(lpnCharSet)) {
				if (minWord.isEmpty() || dictWord.length() < minWord.length()) {
					minWord = dictWord;
				}
			}
		}

		return minWord;
	}

	/**
	 * Solution 2 - Using trie. Time - O(mn) where n = number of words in dictionary and m = max word length.
	 * 
	 * @param head
	 * @param word
	 * @return
	 */
	// Function to insert a String in trie
	public static TrieNode insert(TrieNode head, String word) {
		if (head == null) {
			head = new ShortestDictionaryWord().new TrieNode();
		}

		// start from head node
		TrieNode curr = head;
		for (char c : word.toCharArray()) {
			// insert only uppercase characters
			if (Character.isUpperCase(c)) {
				// create a new node if path doesn't exists
				if (!curr.map.containsKey(c)) {
					curr.map.put(c, new ShortestDictionaryWord().new TrieNode());
				}

				// go to next node
				curr = curr.map.get(c);
			}
		}

		// mark current node as leaf
		curr.isLeaf = true;

		// push current word into the set associated with leaf node
		(curr.word).add(word);

		return head;
	}

	// Function to print all children of given trie node
	public static void printAllWords(TrieNode root) {
		// if current node is leaf, print all words associated with it
		if (root.isLeaf) {
			System.out.println(root.word);
		}

		// recur for all children of the root node
		for (Map.Entry<Character, TrieNode> pair : root.map.entrySet()) {
			TrieNode child = pair.getValue();
			if (child != null) {
				printAllWords(child);
			}
		}
	}

	// Function to print all words in the CamelCase dictionary which
	// matches with the given pattern
	public static void findAllWords(List<String> dictionary, String pattern) {
		// Trie head node
		TrieNode head = null;

		// construct trie from the given dictionary
		for (String s : dictionary) {
			head = insert(head, s);
		}

		// search for the given pattern in trie
		TrieNode curr = head;
		for (char c : pattern.toCharArray()) {
			// move to the child node
			curr = curr.map.get(c);

			// if the given pattern is not found (reached end of path in trie)
			if (curr == null) {
				return;
			}
		}

		// print all words matching given pattern
		printAllWords(curr);
	}

	// A Trie node
	class TrieNode {
		// each node stores a map to its child nodes
		Map<Character, TrieNode> map = new HashMap<>();

		// true when node is a leaf node
		boolean isLeaf = false;

		// collection to store complete list of words in the leaf node
		Set<String> word = new HashSet<>();
	};

	public static void main(String[] args) {
		ShortestDictionaryWord s = new ShortestDictionaryWord();
		// System.out.println(getShortestWordOptimized("AT1234"));
		System.out.println(s.getShortestWord("DA1234"));

		String pattern = "MA";
		findAllWords(dictionary, pattern);
	}
}
