package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * References https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * 
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * 
 * Example 1:
 * 
 * Input: s = "barfoothefoobarman", words = ["foo","bar"] Output: [0,9] Explanation: Substrings starting at index 0 and
 * 9 are "barfoor" and "foobar" respectively. The output order does not matter, returning [9,0] is fine too.
 * 
 * Example 2:
 * 
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"] Output: []
 * 
 * Category : Hard
 */
public class SubtringWithConcatentationOfWords {

	public List<Integer> findSubstringOptimized(String s, String[] words) {

		Map<String, Integer> actual = new HashMap<>();
		Map<String, Integer> used = new HashMap<>();

		set(actual, words);

		List<Integer> output = new ArrayList<>();
		int len = words[0].length();
		int count = words.length;
		int k = words.length * len;

		for (int i = 0; i <= s.length() - k; i++) {
			int j = i; // temp pointer
			int currentCount = 0;

			while (true) {
				if (j + len > s.length()) {
					break;
				}
				String sub = s.substring(j, j + len);
				Integer actualCount = actual.get(sub);
				if (actualCount != null) {
					Integer usedCount = used.get(sub);
					if (usedCount == null) {
						usedCount = 0;
					}
					if (actualCount > usedCount) {
						j = j + len;
						currentCount++;
						used.put(sub, usedCount + 1);
					} else {
						break;
					}
				} else {
					break;
				}
				if (currentCount == count) {
					break;
				}
			}

			used.clear();
			if (currentCount == count) {
				output.add(i);
			}
		}
		return output;
	}

	private void set(Map<String, Integer> actual, String[] words) {
		for (String word : words) {
			if (actual.containsKey(word)) {
				actual.put(word, actual.get(word) + 1);
			} else {
				actual.put(word, 1);
			}
		}
	}

	public ArrayList<Integer> findSubstring(String a, final List<String> b) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		if (a.length() < b.size() * b.get(0).length()) {
			return res;
		}

		Set<String> dict = getAllCombinations(b);
		for (int i = 0; i <= a.length() - b.size() * b.get(0).length(); i++) {
			String sub = a.substring(i, i + b.size() * b.get(0).length());
			if (dict.contains(sub)) {
				res.add(i);
			}
		}

		return res;
	}

	private Set<String> getAllCombinations(List<String> b) {
		Set<String> set = new HashSet<String>();
		Set<Integer> visited = new HashSet<Integer>();

		helper(set, visited, b, "");
		return set;
	}

	private void helper(Set<String> res, Set<Integer> visited, List<String> b, String s) {
		if (s.length() == b.size() * b.get(0).length()) {
			res.add(s);
			return;
		}

		if (s.length() > b.size() * b.get(0).length()) {
			return;
		}

		for (int i = 0; i < b.size(); i++) {
			if (visited.contains(i)) {
				continue;
			}
			visited.add(i);
			helper(res, visited, b, s + b.get(i));
			visited.remove(i);
		}
	}

	public static void main(String[] args) {
		SubtringWithConcatentationOfWords s = new SubtringWithConcatentationOfWords();
		System.out.println(s.findSubstringOptimized("barfoothefoobarman", new String[] { "foo", "bar" }));
		System.out.println(
				s.findSubstringOptimized("wordgoodgoodgoodbestword", new String[] { "word", "good", "best", "word" }));
		System.out.println(s.findSubstring("barfoothefoobarman", Arrays.asList("foo", "bar")));
		System.out.println(s.findSubstring("wordgoodgoodgoodbestword", Arrays.asList("word", "good", "best", "word")));
	}
}
