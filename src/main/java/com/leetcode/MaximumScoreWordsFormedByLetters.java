package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-score-words-formed-by-letters/
 * 
 * Given a list of words, list of single letters (might be repeating) and score of every character.
 * 
 * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or
 * more times).
 * 
 * It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a',
 * 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
 * 
 * Example 1:
 * 
 * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score =
 * [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * 
 * Output: 23
 * 
 * Explanation:
 * 
 * Score a=1, c=9, d=5, g=3, o=2
 * 
 * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
 * 
 * Words "dad" and "dog" only get a score of 21.
 * 
 * Example 2:
 * 
 * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score =
 * [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
 * 
 * Output: 27
 * 
 * Explanation:
 * 
 * Score a=4, b=4, c=4, x=5, z=10
 * 
 * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
 * 
 * Word "xxxz" only get a score of 25.
 * 
 * Example 3:
 * 
 * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score =
 * [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * Letter "e" can only be used once.
 *
 * Category : Hard
 */
public class MaximumScoreWordsFormedByLetters {

	private Map<String, Integer> map = new HashMap<>();

	public int maxScoreWords(String[] words, char[] letters, int[] score) {
		// to capture the count of each char in letters[]
		int[] count = new int[26];
		for (char c : letters) {
			count[c - 'a']++;
		}

		return dfs(words, count, 0, 0, score);
	}

	private int dfs(String[] words, int[] count, int curr, int bitMask, int[] score) {
		if (curr == words.length)
			return 0;

		String key = bitMask + "," + curr;
		if (map.containsKey(key))
			return map.get(key);

		// do dfs for the next word
		int max = dfs(words, count, curr + 1, bitMask, score);

		int[] newcount = count.clone(); // important
		boolean valid = true;
		int currscore = 0;
		for (char ch : words[curr].toCharArray()) {
			if (newcount[ch - 'a'] < 1) {
				valid = false;
				break;
			}

			newcount[ch - 'a']--;
			currscore += score[ch - 'a'];
		}

		if (valid) {
			int newbitMask = bitMask | (1 << curr);
			max = Math.max(max, currscore + dfs(words, newcount, curr + 1, newbitMask, score));
		}

		map.put(key, max);

		return max;
	}

	public static void main(String[] args) {
		MaximumScoreWordsFormedByLetters max = new MaximumScoreWordsFormedByLetters();
		String[] words = { "dog", "cat", "dad", "good" };
		char[] letters = { 'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o' };
		int[] score = { 1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		System.out.println(max.maxScoreWords(words, letters, score));
	}
}
