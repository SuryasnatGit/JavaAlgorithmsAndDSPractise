
package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Various problems related to anagrams in string
 */
public class AnagramStringProblems {

	/**
	 * Method 1- brute force. <br/>
	 * Get all substrings of str2(O(N^2)). for each substring of str2, check if its an anagram(O(M)). total complexity -
	 * O(N^2 * M)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public boolean isAnagramSubString_bruteForce(char str1[], char str2[]) {
		// find all substrings of str2. complexity - O(N^2)
		int l2 = str2.length;
		for (int i = 0; i < l2; i++) {
			for (int j = i + 1; j <= l2; j++) {
				String subStr = new String(str2).substring(i, j);
				// System.out.println("SubString ->" + subStr);
				if (isAnagram(str1, subStr.toCharArray())) {
					System.out.println(str1);
					System.out.println(subStr);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * O(M) complexity. it is assumed that the characters are stored using 8 bit and there can be 256 possible
	 * characters.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean isAnagram(char[] s1, char[] s2) {
		if (s1.length != s2.length)
			return false;

		int[] count1 = new int[256]; // ascii chars
		Arrays.fill(count1, 0);
		int[] count2 = new int[256]; // ascii chars
		Arrays.fill(count2, 0);

		for (int i = 0; i < s1.length && i < s2.length; i++) {
			count1[s1[i]]++;
			count2[s2[i]]++;
		}

		for (int i = 0; i < 256; i++) {
			if (count1[i] != count2[i])
				return false;
		}
		return true;
	}

	public boolean isAnagramSubString_hashing(char str1[], char str2[]) {
		int index = 0;
		int curLen = 0;
		Map<Character, Integer> count = new HashMap<Character, Integer>();
		for (int i = 0; i < str1.length; i++) {
			incrementCount(str1[i], count);
		}
		Map<Character, Integer> currentCount = new HashMap<Character, Integer>();
		Map<Character, Integer> pos = new HashMap<Character, Integer>();
		while (index < str2.length) {
			if (containsAndUpdate(currentCount, count, str2[index], pos, index)) {
				index++;
				curLen++;
			} else {
				Integer p = pos.get(str2[index]);
				if (p != null) {
					curLen = index - p;
					index = p;
				} else {
					index++;
				}
				currentCount.clear();
				pos.clear();
			}
			if (curLen == str1.length) {
				return true;
			}
		}
		return false;
	}

	private boolean containsAndUpdate(Map<Character, Integer> currentCount, Map<Character, Integer> count, Character ch,
			Map<Character, Integer> pos, int index) {
		if (count.containsKey(ch)) {
			if (currentCount.containsKey(ch)) {
				if (currentCount.get(ch) < count.get(ch)) {
					if (currentCount.get(ch) == 1) {
						pos.put(ch, index);
					}
					currentCount.put(ch, currentCount.get(ch) + 1);
					return true;
				}
			} else {
				currentCount.put(ch, 1);
				pos.put(ch, index);
				return true;
			}
		}
		return false;
	}

	private void incrementCount(char ch, Map<Character, Integer> count) {
		if (count.containsKey(ch)) {
			int c = count.get(ch);
			count.put(ch, c + 1);
		} else {
			count.put(ch, 1);
		}
	}

	/**
	 * Using sorting: We can sort array of strings so that all anagrams come together. Then print all anagrams by
	 * linearly traversing the sorted array. The time complexity of this solution is O(mnLogn) (We would be doing
	 * O(nLogn) comparisons in sorting and a comparison would take O(m) time)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public boolean isAnagramSubString_sorting(char str1[], char str2[]) {
		// find all substrings of str2. complexity - O(n^2) ( n is length of string)
		int len1 = str1.length;
		int len2 = str2.length;
		for (int i = 0; i < len2; i++) {
			for (int j = i + 1; j <= len2; j++) {
				// Step 1 - find substring of str2
				char subStr[] = new String(str2).substring(i, j).toCharArray();
				// If length of both strings is not same, then they cannot be anagram
				if (len1 != subStr.length)
					continue;

				// Step 2 -Sort both the strings. complexity - best case is O(nlogn) . worst case is O(n^2) where n is
				// length of string
				Arrays.sort(str1);
				Arrays.sort(subStr);

				// System.out.println("str1 :" + new String(str1));
				// System.out.println("subStr :" + new String(subStr));

				// Step 3 - compare sorted strings. Complexity - O(n)
				if (new String(str1).equals(new String(subStr)))
					return true;
			}
		}
		return false;
	}

	public void printAnagramsTogether(String[] string) {
		Map<String, List<Integer>> invertedIndex = new HashMap<String, List<Integer>>();
		int index = 0;
		for (String str : string) {
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);
			String newString = new String(charArray);
			if (invertedIndex.containsKey(newString)) {
				List<Integer> pos = invertedIndex.get(newString);
				pos.add(index);
			} else {
				List<Integer> pos = new ArrayList<Integer>();
				pos.add(index);
				invertedIndex.put(newString, pos);
			}
			index++;
		}
		for (List<Integer> result : invertedIndex.values()) {
			for (Integer i : result) {
				System.out.println(string[i]);
			}
		}
	}

	/*
	 * https://leetcode.com/problems/anagrams/. <br/> Given an array of words, print all anagrams together. For example,
	 * given: ["eat", "tea", "tan", "ate", "nat", "bat"], Return: [ ["ate", "eat","tea"], ["nat","tan"], ["bat"] ]
	 */

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

	/*
	 * Anagram Substring Search (Or Search for all permutations):
	 * 
	 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints
	 * all occurrences of pat[] and its permutations (or anagrams) in txt[]. You may assume that n > m. Expected time
	 * complexity is O(n).
	 * 
	 * Examples:
	 * 
	 * 1) Input: txt[] = "BACDGABCDA" pat[] = "ABCD" Output: Found at Index 0 Found at Index 5 Found at Index 6
	 * 
	 * 2) Input: txt[] = "AAABABAA" pat[] = "AABA" Output: Found at Index 0 Found at Index 1 Found at Index 4
	 * 
	 */

	public static void main(String args[]) {
		char str1[] = "aaabccde".toCharArray();
		char str2[] = "tbcdaacaaecbd".toCharArray();
		AnagramStringProblems ana = new AnagramStringProblems();
		System.out.println(ana.isAnagramSubString_bruteForce(str1, str2));
		System.out.println(ana.isAnagramSubString_hashing(str1, str2));
		System.out.println(ana.isAnagramSubString_sorting(str1, str2));
	}
}