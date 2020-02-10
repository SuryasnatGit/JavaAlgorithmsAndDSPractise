package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a password, we have a map of possible replacements for each letter, and output all possible replaced passwords.
 * e.g. password, ['a': 'A', 'e'; 'o': 'O', '0']
 *
 */
public class PasswordReplacement {

	public static void main(String[] args) {
		PasswordReplacement pr = new PasswordReplacement();

		String s = "ao";
		Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
		Character[] arr1 = { 'A', 'e' };
		List<Character> list1 = Arrays.asList(arr1);

		Character[] arr2 = { 'O', '0' };
		List<Character> list2 = Arrays.asList(arr2);

		map.put('a', list1);
		map.put('o', list2);

		List<String> res = pr.replaceIterative(s, map);

		for (String str : res) {
			System.out.println(str);
		}
	}

	public List<String> replaceRecursive(String s, Map<Character, List<Character>> map) {
		List<String> res = new ArrayList<String>();
		helper(res, s, 0, map, "");
		return res;
	}

	private void helper(List<String> res, String s, int pos, Map<Character, List<Character>> map, String now) {
		if (pos == s.length()) {
			res.add(now);
			return;
		}

		for (char c : map.get(s.charAt(pos))) {
			helper(res, s, pos + 1, map, now + c);
		}
	}

	public List<String> replaceIterative(String s, Map<Character, List<Character>> map) {
		List<String> res = new ArrayList<String>();

		for (char c : map.get(s.charAt(0))) {
			res.add(String.valueOf(c));
		}

		for (int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
			List<String> next = new ArrayList<String>();

			for (String prefix : res) {
				for (char c2 : map.get(c)) {
					next.add(prefix + c2);
				}
			}

			res = next;
		}

		return res;
	}
}
