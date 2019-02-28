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
 * @author surya
 *
 */
public class PhoneNumberLetterCombination {

	/**
	 * Approach 1: Backtracking Backtracking is an algorithm for finding all solutions by exploring all
	 * potential candidates. If the solution candidate turns to be not a solution (or at least not the
	 * last one), backtracking algorithm discards it by making some changes on the previous step, i.e.
	 * backtracks and then try again.
	 * 
	 * Here is a backtrack function backtrack(combination, next_digits) which takes as arguments an
	 * ongoing letter combination and the next digits to check.
	 * 
	 * If there is no more digits to check that means that the current combination is done. If there are
	 * still digits to check : Iterate over the letters mapping the next available digit. Append the
	 * current letter to the current combination combination = combination + letter. Proceed to check
	 * next digits : backtrack(combination + letter, next_digits[1:]).
	 * 
	 * 
	 */
	private Map<String, String> phone = new HashMap<String, String>() {
		{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}
	};

	private List<String> output = new ArrayList<String>();

	public void backtrack(String combination, String next_digits) {
		// if there is no more digits to check
		if (next_digits.length() == 0) {
			// the combination is done
			output.add(combination);
		}
		// if there are still digits to check
		else {
			// iterate over all letters which map
			// the next available digit
			String digit = next_digits.substring(0, 1);
			String letters = phone.get(digit);
			for (int i = 0; i < letters.length(); i++) {
				String letter = phone.get(digit).substring(i, i + 1);
				// append the current letter to the combination
				// and proceed to the next digits
				backtrack(combination + letter, next_digits.substring(1));
			}
		}
	}

	public List<String> letterCombinations(String digits) {
		if (digits.length() != 0)
			backtrack("", digits);
		return output;
	}

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
	 * Time complexity O(4 ^ n), where n is length of string [for number 7, recursion tree will have 4
	 * nodes] Space complexity O(n) (because the maximum depth of recursion will be n)
	 * 
	 * @param digits
	 * @return
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

}
