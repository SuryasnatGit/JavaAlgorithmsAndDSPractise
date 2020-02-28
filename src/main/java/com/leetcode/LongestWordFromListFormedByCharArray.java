package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * given 2 arrays wrds[] , chars[] as an input to a function such that
 * 
 * words[] = [ "abc" , "baa" , "caan" , "an" , "banc" ] chars[] = [ "a" , "a" , "n" , "c" , "b"] Function should return
 * the longest word from words[] which can be constructed from the chars in chars[] array.
 * 
 * for above example - "caan" , "banc" should be returned
 * 
 * Note: Once a character in chars[] array is used, it can't be used again.
 * 
 * eg: words[] = [ "aat" ] characters[] = [ "a" , "t" ] then word "aat" can't be constructed, since we've only 1 "a" in
 * chars[].
 *
 */
public class LongestWordFromListFormedByCharArray {

	public List<String> getLongestWords(String[] words, char[] characters) {
		// contains map of string length to list of strings having same length. passes in a custom comparator to sort in
		// desc order of length
		SortedMap<Integer, List<String>> wordToLengthMap = new TreeMap<>((a, b) -> b - a);
		// contains map of char to unique prime value
		Map<Character, Integer> charPrimeValueMap = new HashMap<>();

		// assign unique prime value to each char in characters
		charPrimeValueMap = getCharPrimeValueMap();

		int product = 1;
		for (char ch : characters) {
			product *= charPrimeValueMap.getOrDefault(ch, 1);
		}

		for (String word : words) {
			int wordProduct = 1;
			int len = word.length();
			for (int i = 0; i < word.length(); i++) {
				wordProduct *= charPrimeValueMap.getOrDefault(word.charAt(i), 1);
			}
			if (product % wordProduct == 0) {
				if (!wordToLengthMap.containsKey(len)) {
					wordToLengthMap.put(len, new ArrayList<>());
				}
				List<String> wordList = wordToLengthMap.get(len);
				wordList.add(word);
				wordToLengthMap.put(len, wordList);
			}
		}

		return wordToLengthMap.size() > 0 ? wordToLengthMap.get(wordToLengthMap.firstKey()) : new ArrayList<>();
	}

	private Map<Character, Integer> getCharPrimeValueMap() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('a', 2);
		map.put('n', 3);
		map.put('c', 5);
		map.put('b', 7);
		return map;
	}

	public static void main(String[] args) {
		LongestWordFromListFormedByCharArray lon = new LongestWordFromListFormedByCharArray();
		System.out.println(lon.getLongestWords(new String[] { "abc", "baa", "caan", "an", "banc" },
				new char[] { 'a', 'a', 'n', 'c', 'b' }));
		System.out.println(lon.getLongestWords(new String[] { "aat" }, new char[] { 'a', 't' }));

	}
}
