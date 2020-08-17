package com.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Julia and Samantha are playing with strings. Julia has a string S, and Samantha has a string T which is a subsequence
 * of string S. They are trying to find out what words are missing in T. Help Julia and Samantha to solve the problem.
 * List all the missing words in T, such that inserting them at the appropriate positions in T, in the same order,
 * results in the string S.
 * 
 * Constraints : 1. 1 <= |T| <= |S| <= 106, where |X| denotes the length of string X. 2.The length of each word will be
 * less than 15.
 * 
 * Function Parameter:
 * 
 * You are given a function missingWords that takes the strings S and T as its arguments.
 * 
 * Function Return Value:
 * 
 * Return an array of the missing words.
 * 
 * Sample Input:
 * 
 * I am using hackerrank to improve programming
 * 
 * am hackerrank to improve
 * 
 * Sample Output
 * 
 * I
 * 
 * using
 * 
 * programming
 * 
 * Explanation
 * 
 * Missing words are:
 * 
 * 1. I 2. using 3. programming
 *
 */
public class MissingWords {

	public List<String> missingWords(String s, String t) {
		String[] sStrings = s.split(" ");
		String[] tStrings = t.split(" ");

		Map<String, Integer> map = new HashMap<>();
		List<String> result = new ArrayList<>();

		for (String tStr : tStrings) {
			map.put(tStr, map.getOrDefault(tStr, 0) + 1);
		}

		for (String sStr : sStrings) {
			if (!map.containsKey(sStr)) {
				result.add(sStr);
			} else {
				map.put(sStr, map.get(sStr) - 1);
				if (map.get(sStr) == 0) {
					map.remove(sStr);
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		MissingWords mw = new MissingWords();
		System.out.println(mw.missingWords("I am using hackerrank to improve programming", "am hackerrank to improve"));
	}

}
