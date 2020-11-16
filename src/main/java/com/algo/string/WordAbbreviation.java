package com.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordAbbreviation {

	private Map<String, Set<String>> abbrMap = new HashMap<>();

	public WordAbbreviation(String[] dictionary) {
		for (String word : dictionary) {
			String abbrWord = getAbbreviation(word);

			Set<String> set = null;
			if (!abbrMap.containsKey(abbrWord)) {
				set = new HashSet<>();
			} else {
				set = abbrMap.get(abbrWord);
			}
			set.add(word);
			abbrMap.put(abbrWord, set);
		}
		System.out.println(abbrMap);
	}

	/**
	 * The abbreviation of a word is a concatenation of its first letter, the number of characters between the first and
	 * last letter, and its last letter. If a word has only two characters, then it is an abbreviation of itself.
	 * 
	 * For example:
	 * 
	 * dog --> d1g because there is one letter between the first letter 'd' and the last letter 'g'.
	 * internationalization --> i18n because there are 18 letters between the first letter 'i' and the last letter 'n'.
	 * it --> it because any word with only two characters is an abbreviation of itself. Implement the ValidWordAbbr
	 * class:
	 * 
	 * ValidWordAbbr(String[] dictionary) Initializes the object with a dictionary of words. boolean isUnique(string
	 * word) Returns true if either of the following conditions are met (otherwise returns false): There is no word in
	 * dictionary whose abbreviation is equal to word's abbreviation. For any word in dictionary whose abbreviation is
	 * equal to word's abbreviation, that word and word are the same.
	 * 
	 */
	public boolean isUnique(String word) {
		String abbrWord = getAbbreviation(word);
		if (!abbrMap.containsKey(abbrWord)) {
			return true;
		} else if (abbrMap.get(abbrWord).contains(word) && abbrMap.get(abbrWord).size() == 1) {
			return true;
		}
		return false;
	}

	private String getAbbreviation(String word) {
		if (word.length() <= 2) {
			return word;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(word.charAt(0));
		sb.append(word.length() - 2);
		sb.append(word.charAt(word.length() - 1));

		return sb.toString();
	}

	/**
	 * Given a dictionary of words and a word, tell if there is unique abbrreviation of this word in the dictionary.
	 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given
	 * abbreviation.
	 * 
	 * A string such as “word? contains only the following valid abbreviations: ["word", "1ord", "w1rd", "wo1d", "wor1",
	 * "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"] Notice that only the above
	 * abbreviations are valid abbreviations of the string “word". Any other string is not a valid abbreviation of
	 * “word". Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
	 * 
	 * Example 1: Given s = "internationalization", abbr = "i12iz4n": Return true.
	 * 
	 * Example 2: Given s = "apple", abbr = "a2e": Return false.
	 * 
	 */
	public boolean validWordAbbreviation(String word, String abbr) {
		int i = 0, j = 0;
		while (i < word.length() && j < abbr.length()) {
			if (abbr.charAt(j) == '0')
				return false;
			if (Character.isDigit(abbr.charAt(j))) {
				int end = j;
				while (end < abbr.length() && Character.isDigit(abbr.charAt(end))) {
					end++;
				}
				int count = Integer.parseInt(abbr.substring(j, end));
				i += count;
				j = end;
			} else {
				if (word.charAt(i++) != abbr.charAt(j++))
					return false;
			}
		}
		return i == word.length() && j == abbr.length();
	}

	/**
	 * Write a function to generate the generalized abbreviations of a word. Example: Given word = "word", return the
	 * following list (order does not matter): ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d",
	 * "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
	 *
	 * https://leetcode.com/problems/generalized-abbreviation/
	 * 
	 * The shorthand rule is that several letters can be represented by numbers, but there can be no two adjacent
	 * numbers
	 */
	/**
	 * Solution 1 - using recursion + DFS.
	 * 
	 * @param word
	 * @return
	 */
	public List<String> generateAbbreviationRecursive(String word) {
		List<String> result = new ArrayList<>();
		generateAbbreviationsUtil(word, result, "", 0, 0);
		return result;
	}

	public void generateAbbreviationsUtil(String input, List<String> result, String current, int pos, int count) {
		if (input.length() == pos) {
			if (count > 0) {
				result.add(current + count);
			} else {
				result.add(current);
			}
			return;
		}

		generateAbbreviationsUtil(input, result, current, pos + 1, count + 1);
		generateAbbreviationsUtil(input, result, current + (count > 0 ? count : "") + input.charAt(pos), pos + 1, 0);
	}

	/**
	 * Solution 2 - Using binary string. We can observe the pattern, where the 0 is the original letter, the single 1 as
	 * 1, if it is a number of 1 together, it is required to count the number, replace the corresponding letters with
	 * this number.
	 *
	 * @param args
	 */
	public List<String> generateAbbreviationsBinary(String word) {

		List<String> result = new ArrayList<>();

		int len = (int) Math.pow(2, word.length());
		for (int i = 0; i < len; i++) {
			String out = "";
			int count = 0;
			int t = i;
			for (int j = 0; j < word.length(); j++) {
				if ((t & 1) == 1) {
					count++;
					if (j == word.length() - 1) {
						out += count;
					}
				} else {
					if (count != 0) { // add count before the letter
						out += count;
						count = 0; // reset count
					}
					out += word.charAt(j);
				}
				t >>= 1;
			}
			result.add(out);
		}

		return result;
	}

	public static void main(String[] args) {
		String[] words = { "deer", "door", "cake", "card" };
		WordAbbreviation abbr = new WordAbbreviation(words);

		System.out.println(abbr.isUnique("deer"));
		System.out.println(abbr.isUnique("door"));
		System.out.println(abbr.isUnique("cart"));
		System.out.println(abbr.isUnique("cake"));

		System.out.println(abbr.validWordAbbreviation("internationalization", "i18n"));
		System.out.println(abbr.validWordAbbreviation("internationalization", "i17n"));
		System.out.println(abbr.validWordAbbreviation("internatioan", "i10n"));

		abbr.generateAbbreviationRecursive("word").forEach(r -> System.out.print(r + " "));
		System.out.println();
		abbr.generateAbbreviationsBinary("word").forEach(r -> System.out.print(r + " "));
	}
}
