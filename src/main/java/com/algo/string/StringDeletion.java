package com.algo.string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * http://blog.gainlo.co/index.php/2016/04/29/minimum-number-of-deletions-of-a-string/
 * 
 * Question : Given a string and a dictionary HashSet, write a function to determine the minimum number of characters to
 * delete to make a word.
 * 
 * dictionary: [“a”, “aa”, “aaa”]
 * 
 * query: “abc”
 * 
 * output: 2
 * 
 * For example, string “catn” needs one deletion to make it a valid word “cat” in the dictionary. And string “bcatn”
 * needs two deletions.
 * 
 * Questions : 1. will the characters that need to be deleted need to be contiguous or they can be random located
 * 
 * Category : Hard
 */
public class StringDeletion {

	public int delete(String query, Set<String> dictionary) {
		Queue<String> queue = new LinkedList<>();
		Set<String> queueElements = new HashSet<>();

		queue.add(query);
		queueElements.add(query);

		while (!queue.isEmpty()) {
			String s = queue.poll();
			queueElements.remove(s);

			if (dictionary.contains(s))
				return query.length() - s.length();

			for (int i = 0; i < s.length(); i++) {
				String sub = s.substring(0, i) + s.substring(i + 1, s.length());
				if (sub.length() > 0 && !queueElements.contains(sub)) {
					queue.add(sub);
					queueElements.add(sub);
				}
			}
		}
		return -1;
	}

}
