
package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/anagrams/. <br/>
 * Given an array of words, print all anagrams together. For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return: [ ["ate", "eat","tea"], ["nat","tan"], ["bat"] ]
 */
public class GroupAnagramsTogether {

	/**
	 * A simple method is to create a Hash Table. Calculate the hash value of each word in such a way that all anagrams
	 * have the same hash value. Populate the Hash Table with these hash values. Finally, print those words together
	 * with same hash values. A simple hashing mechanism can be modulo sum of all characters. With modulo sum, two
	 * non-anagram words may have same hash value. This can be handled by matching individual characters.
	 * 
	 * @param strs
	 * @return
	 */
	public Collection<List<String>> groupAnagrams_hashing(String[] strs) {
		Map<Integer, List<String>> map = new HashMap<>();
		for (String s : strs) {
			int hash = ascii(s);
			// int hash = s.hashCode();
			if (map.containsKey(hash)) {
				map.get(hash).add(s);
			} else {
				List<String> l = new ArrayList<>();
				l.add(s);
				map.put(hash, l);
			}
		}
		// System.out.println(map);
		System.out.println(map.values());
		return map.values();
	}

	private int ascii(String str) {
		int m = 10;
		int i, sum;
		for (i = 0, sum = 0; i < str.length(); i++) {
			sum += str.charAt(i);
		}
		return sum % m;
	}

	/**
	 * If there are m strings and each string is of n characters, then time complexity is O(m * n log n) in best case.
	 * Space complexity - O(m)
	 * 
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupAnagrams_usingHashMap(String[] strs) {
		if (strs == null || strs.length == 0)
			return Collections.emptyList();

		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> anagramGroup = new HashMap<>();

		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String sorted = new String(chars);
			if (anagramGroup.containsKey(sorted)) {
				anagramGroup.get(sorted).add(str);
			} else {
				List<String> list = new ArrayList<>();
				list.add(str);
				anagramGroup.put(sorted, list);
			}
		}

		// create and return result
		for (List<String> list : anagramGroup.values()) {
			// if we want the anagrams entries sorted
			Collections.sort(list);
			result.add(list);
		}
		return result;
	}

	// Approach 3: Using Trie data structure.
	/**
	 * The basic idea used is simple. Insert the individual words of the sequence in a trie but before inserting a word
	 * sort it according to the characters. This way, when anagrams are inserted into a trie, their path from root node
	 * to leaf node would be exactly the same and if we store the indices of the words in given sequence at leaf node,
	 * then we would be able to print all anagrams in grouped manner
	 */
	final static int ALPHABET_SIZE = 26;

	class TrieNode {
		// can have max of 26 children
		TrieNode[] children;
		// contains list of index for anagram grouping
		List<Integer> anagramIndex;

		public TrieNode() {
			children = new TrieNode[ALPHABET_SIZE];
			anagramIndex = new ArrayList<>();
		}
	}

	private TrieNode root = new TrieNode();

	private void insertWord(String word, int index, Map<TrieNode, List<Integer>> anagramGroupMap) throws Exception {
		int charIndex = 0;

		TrieNode currentNode = root;
		while (charIndex < word.length()) {
			int childIndex = getChildIndex(word.charAt(charIndex));
			// validate that the child is a small case letter only
			if (childIndex < 0 || childIndex > ALPHABET_SIZE)
				throw new Exception("invalid key");

			if (currentNode.children[childIndex] == null)
				currentNode.children[childIndex] = new TrieNode(); // insert

			// else proceed pointer
			currentNode = currentNode.children[childIndex];
			charIndex++;
		}

		// if charIndex equals the key length
		if (charIndex == word.length()) {
			currentNode.anagramIndex.add(index);
			anagramGroupMap.put(currentNode, currentNode.anagramIndex);
		}
	}

	private int getChildIndex(char ch) {
		return ch - 'a';
	}

	public void printAnagramGroupsUsingTrie(String[] input) throws Exception {
		Map<TrieNode, List<Integer>> anagramGroupMap = new HashMap<>();

		for (int index = 0; index < input.length; index++) {
			char[] charArray = input[index].toCharArray();
			Arrays.sort(charArray);
			insertWord(new String(charArray), index, anagramGroupMap);
		}

		anagramGroupMap.values().forEach(list -> list.forEach(i -> System.out.print(input[i] + ",")));
	}

	public static void main(String args[]) {
		String str[] = { "cat", "dog", "tac", "god", "act" };
		GroupAnagramsTogether pat = new GroupAnagramsTogether();
		System.out.println(pat.groupAnagrams_usingHashMap(str));
		pat.groupAnagrams_hashing(str);
		// System.out.println(pat.anagrams(str));
		try {
			pat.printAnagramGroupsUsingTrie(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
