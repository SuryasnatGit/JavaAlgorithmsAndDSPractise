package com.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s. Strings consists of
 * lowercase English letters only and the length of both strings s and p will not be larger than 20,100. The order of
 * output does not matter.
 * 
 * Example 1 :
 * 
 * Input:
 * 
 * s: "cbaebabacd" p: "abc"
 * 
 * * Output:
 * 
 * [0, 6]
 * 
 * * Explanation:
 * 
 * The substring with start index = 0 is "cba", which is an anagram of "abc". The substring with start index = 6 is
 * "bac", which is an anagram of "abc".
 * 
 * Example 2 :
 * 
 * Input:
 * 
 * s: "abab" p: "ab"
 * 
 * 
 * Output:
 * 
 * [0, 1, 2]
 * 
 * 
 * Explanation:
 * 
 * The substring with start index = 0 is "ab", which is an anagram of "ab". The substring with start index = 1 is "ba",
 * which is an anagram of "ab". The substring with start index = 2 is "ab", which is an anagram of "ab".
 * 
 * 
 */
public class FindAllAnagramsInString {

	public List<Integer> findAllSubstringsOfStringThatArePermutationOfAnotherString(String s, String p) {
		List<Integer> result = new ArrayList<>();

		if (s == null || s.length() == 0 || s.length() < p.length()) {
			return result;
		}

		int pLen = p.length();
		Map<Character, Integer> pCharMap = new HashMap<>();
		Map<Character, Integer> sCharMap = new HashMap<>();

		for (char ch : p.toCharArray()) {
			pCharMap.put(ch, pCharMap.getOrDefault(ch, 0) + 1);
		}

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			// increment the window by adding 1 letter each time to right side of window
			sCharMap.put(ch, sCharMap.getOrDefault(ch, 0) + 1);

			// remove 1 letter from left side of window
			if (i >= pLen) {
				ch = s.charAt(i - pLen);
				// update s map
				if (sCharMap.get(ch) == 1) {
					sCharMap.remove(ch);
				} else {
					sCharMap.put(ch, sCharMap.get(ch) - 1);
				}
			}

			if (pCharMap.equals(sCharMap)) {
				result.add(i - pLen + 1);
			}
		}

		return result;

	}

	// another approach
	/**
	 * TODO : understand more
	 * 
	 * The universal Sliding Window template can solve this problem: the counter records the number of characters that
	 * still need to be matched in the map. If it does counter==0, it means that the substring in the current window
	 * contains the target string anagram you are looking for, but you can further check whether the window can be
	 * shortened. This is achieved by adjusting begin. Therefore, the window size of this sliding window is actually
	 * changing , and the result is recorded only when counter==0and window size: end - begine == t.length()when it is
	 * current.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public List<Integer> findAnagrams(String s, String t) {
		List<Integer> result = new ArrayList<>();
		if (t.length() > s.length())
			return result;
		Map<Character, Integer> map = new HashMap<>();
		for (char c : t.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int counter = map.size();

		int begin = 0, end = 0;

		while (end < s.length()) {
			char c = s.charAt(end);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				if (map.get(c) == 0)
					counter--;
			}
			end++;

			while (counter == 0) {
				char tempc = s.charAt(begin);
				if (map.containsKey(tempc)) {
					map.put(tempc, map.get(tempc) + 1);
					if (map.get(tempc) > 0) {
						counter++;
					}
				}
				if (end - begin == t.length()) {
					result.add(begin);
				}
				begin++;
			}

		}
		return result;
	}

	public static void main(String[] args) {
		FindAllAnagramsInString find = new FindAllAnagramsInString();
		System.out.println(find.findAllSubstringsOfStringThatArePermutationOfAnotherString("cbaebabacd", "abc"));
		System.out.println(find.findAllSubstringsOfStringThatArePermutationOfAnotherString("abab", "ab"));
		System.out.println(find.findAnagrams("cbaebabacd", "abc"));
		System.out.println(find.findAnagrams("abab", "ab"));
	}
}
