package com.algo.ds.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two words (start and end), and a dictionary, find the length of shortest transformation
 * sequence from start to end, such that: Only one letter can be changed at a time. Each
 * intermediate word must exist in the dictionary.
 * 
 * For example, given start = "hit", end = "cog", dict = ["hot","dot","dog","lot","log"], as one
 * shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * 
 * Note: Return 0 if there is no such transformation sequence. All words have the same length. All
 * words contain only lowercase alphabetic characters.
 * 
 * @author surya
 *
 */
public class WordLadder1 {

	/**
	 * 2 end BFS solution
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param dict
	 * @return
	 */
	public int ladderLength(String beginWord, String endWord, Set<String> dict) {
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();

		// to keep track of visited words in dictionary
		Set<String> visited = new HashSet<>();

		beginSet.add(beginWord);
		endSet.add(endWord);

		int len = 1; // to start

		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			// check size and swap
			if (beginSet.size() > endSet.size()) {
				Set<String> temp = beginSet;
				beginSet = endSet;
				endSet = temp;
			}

			Set<String> temp = new HashSet<>();
			// scan through beginSet
			for (String word : beginSet) {
				char[] chArr = word.toCharArray();
				for (int i = 0; i < chArr.length; i++) {
					for (char c = 'a'; c <= 'z'; c++) {
						// take backup of old char
						char oldChar = chArr[i];
						chArr[i] = c; // replace with c
						String newWord = new String(chArr);

						if (endSet.contains(newWord)) {
							len++;
							return len;
						}

						if (!visited.contains(newWord) && dict.contains(newWord)) {
							visited.add(newWord);
							temp.add(newWord);
						}
						chArr[i] = oldChar;// reset the char
					}
				}
			}

			beginSet = temp;
			len++;
		}

		return 0;
	}

	public int ladderLength1(String start, String end, Set<String> dict) {

		if (start == null || end == null || start.length() != end.length() || start.length() == 0)

			return 0;

		// A queue used for breadth-first search

		Deque<String> queue = new ArrayDeque<String>();

		queue.offer(start);

		Set<String> visited = new HashSet<String>(); // Record the strings that have been visited

		int depth = 1;

		int nodesInCurrentLevel = 1, nodesInNextLevel = 0;

		// A breadth-first search starting from "start"

		while (queue.peek() != null) {

			String current = queue.poll();

			nodesInCurrentLevel--;

			// Try each string that is one character away from the current one

			for (int i = 0; i < current.length(); i++) {

				char[] charCurrent = current.toCharArray();

				for (char c = 'a'; c <= 'z'; c++) {

					charCurrent[i] = c;

					String temp = new String(charCurrent);

					if (temp.equals(end)) // Reach target string with one more transformation

						return depth + 1;

					if (!visited.contains(temp) && dict.contains(temp)) {

						queue.offer(temp);

						nodesInNextLevel++;

						visited.add(temp);

					}

				}

			}

			// All nodes at the current level are done; prepare to go to the next level

			if (nodesInCurrentLevel == 0) {

				nodesInCurrentLevel = nodesInNextLevel;

				nodesInNextLevel = 0;

				depth++;

			}

		}

		return 0;

	}

}
