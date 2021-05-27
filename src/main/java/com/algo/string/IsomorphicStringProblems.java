package com.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Category : Medium
 *
 */
public class IsomorphicStringProblems {

	public static void main(String[] args) {
		IsomorphicStringProblems iso = new IsomorphicStringProblems();

		System.out.println(iso.isIsomorphicStrings("egg", "add"));
		System.out.println(iso.isIsomorphicStrings("egg", "ads"));

		System.out.println(iso.groupIsomorphicStrings(new String[] { "foo", "gjk", "pkk" }));
	}

	/**
	 * Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters in s
	 * can be replaced to get t.
	 * 
	 * For example,"egg" and "add" are isomorphic, "foo" and "bar" are not. complexity - O(n)
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean isIsomorphicStrings(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;

		if (s1.length() != s2.length())
			return false;

		Map<Character, Character> charMap = new HashMap<>();

		for (int i = 0; i < s1.length(); i++) {
			char ch1 = s1.charAt(i);
			char ch2 = s2.charAt(i);
			if (charMap.containsKey(ch1)) {
				if (!charMap.get(ch1).equals(ch2))
					return false;
			} else {
				if (charMap.containsValue(ch2))
					return false;
				charMap.put(ch1, ch2);
			}
		}
		return true;
	}

	// Another follow up, Follow up: What if it is three Strings. The following method can be solved
	// follow up
	// transfer word into same isomorphic pattern
	// every word start with 'a', every time meet a new letter
	// map it to cur char, and increase the value of cur char the then if same letter show up again, use the value in
	// map
	// ex. foo -> abb
	// ex. gjk -> abc
	// ex. pkk -> abb
	public List<List<String>> groupIsomorphicStrings(String[] input) {
		List<List<String>> res = new ArrayList<List<String>>();
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();

		for (String str : input) {
			String trans = transform(str);

			if (!map.containsKey(trans)) {
				map.put(trans, new HashSet<String>());
			}

			map.get(trans).add(str);
		}

		for (Set<String> set : map.values()) {
			List<String> list = new ArrayList<String>();
			list.addAll(set);
			res.add(list);
		}

		return res;
	}

	// The methodology is very similar to Group Anagram
	private String transform(String str) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		char now = 'a';
		StringBuilder sb = new StringBuilder();

		for (char c : str.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, now);
				now++;
			}

			sb.append(map.get(c));
		}

		return sb.toString();
	}

}
