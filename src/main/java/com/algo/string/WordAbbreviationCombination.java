package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Write a function to generate the generalized abbreviations of a word. Example: Given word = "word", return the
 * following list (order does not matter): ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1",
 * "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 *
 * https://leetcode.com/problems/generalized-abbreviation/
 * 
 * The shorthand rule is that several letters can be represented by numbers, but there can be no two adjacent numbers
 */
public class WordAbbreviationCombination {

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

	public static void main(String args[]) {
		WordAbbreviationCombination ssc = new WordAbbreviationCombination();
		ssc.generateAbbreviationRecursive("word").forEach(r -> System.out.print(r + " "));
		System.out.println();
		ssc.generateAbbreviationsBinary("word").forEach(r -> System.out.print(r + " "));
	}
}
