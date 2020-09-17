package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * Category : Medium
 * 
 * Tags : DFS, BFS
 *
 */
public class PhoneNumberLetterCombination {

	/**
	 * BFS approach.
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinationsBFS(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		if (digits.isEmpty())
			return ans;

		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add("");
		while (ans.peek().length() != digits.length()) {
			String remove = ans.remove();
			String map = mapping[digits.charAt(remove.length()) - '0'];
			for (char c : map.toCharArray()) {
				ans.addLast(remove + c);
			}
		}
		return ans;
	}

	/**
	 * Time complexity is O(k^n), where k is the biggest number of letters a digit can map (k=4) and n is the length of
	 * the digit string.
	 */
	public List<String> letterCombinationsDFS(String digits) {
		if (digits == null || digits.length() == 0) {
			return Collections.emptyList();
		}
		Map<Integer, List<Character>> map = new HashMap<>();
		map.put(2, Arrays.asList('a', 'b', 'c'));
		map.put(3, Arrays.asList('d', 'e', 'f'));
		map.put(4, Arrays.asList('g', 'h', 'i'));
		map.put(5, Arrays.asList('j', 'k', 'l'));
		map.put(6, Arrays.asList('m', 'n', 'o'));
		map.put(7, Arrays.asList('p', 'q', 'r', 's'));
		map.put(8, Arrays.asList('t', 'u', 'v'));
		map.put(9, Arrays.asList('w', 'x', 'y', 'z'));

		List<String> result = new ArrayList<>();
		recurse(digits, result, "", map, 0);
		return result;
	}

	private void recurse(String digits, List<String> result, String temp, Map<Integer, List<Character>> map,
			int index) {
		if (index == digits.length()) {
			result.add(temp);
		} else {
			Integer ch = Character.getNumericValue(digits.charAt(index));
			List<Character> chars = map.get(ch);
			for (int i = 0; i < chars.size(); i++) {
				recurse(digits, result, temp + chars.get(i), map, index + 1);
			}
		}
	}

	public static void main(String[] args) {
		PhoneNumberLetterCombination p = new PhoneNumberLetterCombination();
		p.letterCombinationsDFS("23").forEach(a -> System.out.println(a));
		System.out.println();
		p.letterCombinationsBFS("23").forEach(a -> System.out.println(a));
	}
}
