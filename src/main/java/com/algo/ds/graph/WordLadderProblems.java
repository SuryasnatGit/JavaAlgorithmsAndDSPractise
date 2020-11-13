package com.algo.ds.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderProblems {

	/**
	 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start
	 * to end, such that: Only one letter can be changed at a time. Each intermediate word must exist in the dictionary.
	 * 
	 * For example, given start = "hit", end = "cog", dict = ["hot","dot","dog","lot","log"], as one shortest
	 * transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
	 * 
	 * Note: Return 0 if there is no such transformation sequence. All words have the same length. All words contain
	 * only lowercase alphabetic characters.
	 * 
	 * https://leetcode.com/problems/word-ladder/solution/
	 * 
	 **/

	/*
	 * /** 2 end BFS solution
	 * 
	 * @param beginWord
	 * 
	 * @param endWord
	 * 
	 * @param dict
	 * 
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

	/**
	 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
	 * 
	 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined
	 * as ONE single character changed in the gene string.
	 * 
	 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
	 * 
	 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to
	 * make it a valid gene string.
	 * 
	 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed
	 * to mutate from "start" to "end". If there is no such a mutation, return -1.
	 * 
	 * Note:
	 * 
	 * Starting point is assumed to be valid, so it might not be included in the bank. If multiple mutations are needed,
	 * all mutations during in the sequence must be valid. You may assume start and end string is not the same.
	 * 
	 * Example 1:
	 * 
	 * start: "AACCGGTT" end: "AACCGGTA" bank: ["AACCGGTA"]
	 * 
	 * return: 1
	 * 
	 * Example 2:
	 * 
	 * start: "AACCGGTT" end: "AAACGGTA" bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
	 * 
	 * return: 2
	 * 
	 * Example 3:
	 * 
	 * start: "AAAAACCC" end: "AACCCCCC" bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
	 * 
	 * return: 3
	 * 
	 */
	public int minMutation(String start, String end, String[] bank) {
		if (bank == null || bank.length == 0)
			return -1;
		Set<String> set = new HashSet<>();
		for (String b : bank)
			set.add(b);

		int mutations = 0;
		char[] choices = new char[] { 'A', 'C', 'G', 'T' };

		Queue<String> queue = new LinkedList<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			int siz = queue.size();
			mutations++;
			// go over all neighbors/level nodes
			while (siz-- > 0) {
				// test all possible mutations
				StringBuilder curr = new StringBuilder(queue.remove());
				for (int i = 0; i < curr.length(); i++) {
					char original = curr.charAt(i);
					for (char choice : choices) {
						// String newCurr = curr.substring(0,i) + choice + curr.substring(i+1);
						curr.setCharAt(i, choice);
						String newCurr = curr.toString();
						if (set.contains(newCurr)) {
							if (newCurr.equals(end)) {
								return mutations;
							}

							queue.add(newCurr);
							set.remove(newCurr); // mark as visited
						}

						curr.setCharAt(i, original);
					}

				}

			}
		}

		return -1;
	}

	/**
	 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation
	 * sequence(s) from beginWord to endWord, such that: Only one letter can be changed at a time. Each intermediate
	 * word must exist in the word list.
	 *
	 * For example, given start = "hit", end = "cog", dict = ["hot","dot","dog","lot","log"], as one shortest
	 * transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
	 * 
	 * Note:
	 * 
	 * Return 0 if there is no such transformation sequence.<br/>
	 * All words have the same length. <br/>
	 * All words contain only lowercase alphabetic characters.
	 * 
	 * https://leetcode.com/problems/word-ladder-ii/
	 * https://leetcode.com/problems/word-ladder-ii/discuss/40477/super-fast-java-solution-two-end-bfs
	 */

	// Approach 1
	/**
	 * The idea is the same. To track the actual ladder, we need to add a pointer that points to the previous node in
	 * the WordNode class. In addition, the used word can not directly removed from the dictionary. The used word is
	 * only removed when steps change.
	 * 
	 * @author surya
	 *
	 */
	class WordNode {
		private String word;
		private int depth;
		private WordNode previous;

		public WordNode(String w, int d, WordNode p) {
			this.word = w;
			this.depth = d;
			this.previous = p;
		}
	}

	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> dict) {
		List<List<String>> result = new ArrayList<>();

		// Set<String> visited = new HashSet<>();

		Set<String> unvisited = new HashSet<>();
		unvisited.addAll(dict);

		Queue<WordNode> ladder = new LinkedList<>();
		WordNode root = new WordNode(beginWord, 0, null);
		ladder.offer(root);

		int minLength = Integer.MAX_VALUE;

		while (!ladder.isEmpty()) {
			// poll the queue
			WordNode top = ladder.poll();

			// return result if shorter result already
			if (result.size() > 0 && top.depth > minLength)
				return result;

			for (int i = 0; i < top.word.length(); i++) {
				char c = top.word.charAt(i);
				char[] charArr = top.word.toCharArray();
				for (char ch = 'a'; ch <= 'z'; ch++) {
					if (ch == c)
						continue;

					charArr[i] = ch;
					String newWord = new String(charArr);
					if (newWord.equals(endWord)) {
						// add to result
						List<String> temp = new ArrayList<>();
						temp.add(endWord);
						WordNode p = top;
						while (p != null) {
							temp.add(p.word);
							p = p.previous;
						}

						// reverse temp
						Collections.reverse(temp);
						result.add(temp);

						// stop if got shorter result
						if (top.depth <= minLength) {
							minLength = top.depth;
						} else {
							return result;
						}
					}

					if (unvisited.contains(newWord)) {
						unvisited.remove(newWord);
						WordNode n = new WordNode(newWord, top.depth + 1, top);
						ladder.offer(n);
					}
				}
			}
		}

		return result;
	}

	// Approach 2

	/**
	 * 1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from
	 * start node to end node, and store node's next level neighbors to HashMap;
	 * 
	 * 2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap: compare if the
	 * distance of the next level node equals the distance of the current node + 1.
	 * 
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public List<List<String>> findLadders1(String start, String end, Set<String> dict) {
		List<List<String>> res = new ArrayList<List<String>>();
		HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for
																									// every node
		HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
		ArrayList<String> solution = new ArrayList<String>();

		dict.add(end);
		bfs(start, end, dict, nodeNeighbors, distance);
		dfs(start, end, dict, nodeNeighbors, distance, solution, res);
		return res;
	}

	// BFS: Trace every node's distance from the start node (level by level).
	private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,
			HashMap<String, Integer> distance) {
		for (String str : dict)
			nodeNeighbors.put(str, new ArrayList<String>());

		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		distance.put(start, 0);

		while (!queue.isEmpty()) {
			int count = queue.size();
			boolean foundEnd = false;
			for (int i = 0; i < count; i++) {
				String cur = queue.poll();
				int curDistance = distance.get(cur);
				ArrayList<String> neighbors = getNeighbors(cur, dict);

				for (String neighbor : neighbors) {
					nodeNeighbors.get(cur).add(neighbor);
					if (!distance.containsKey(neighbor)) {// Check if visited
						distance.put(neighbor, curDistance + 1);
						if (end.equals(neighbor))// Found the shortest path
							foundEnd = true;
						else
							queue.offer(neighbor);
					}
				}
			}

			if (foundEnd)
				break;
		}
	}

	// Find all next level nodes.
	private ArrayList<String> getNeighbors(String node, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char chs[] = node.toCharArray();

		for (char ch = 'a'; ch <= 'z'; ch++) {
			for (int i = 0; i < chs.length; i++) {
				if (chs[i] == ch)
					continue;
				char old_ch = chs[i];
				chs[i] = ch;
				if (dict.contains(String.valueOf(chs))) {
					res.add(String.valueOf(chs));
				}
				chs[i] = old_ch;
			}

		}
		return res;
	}

	// DFS: output all paths with the shortest distance.
	private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,
			HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
		solution.add(cur);
		if (end.equals(cur)) {
			res.add(new ArrayList<String>(solution));
		} else {
			for (String next : nodeNeighbors.get(cur)) {
				if (distance.get(next) == distance.get(cur) + 1) {
					dfs(next, end, dict, nodeNeighbors, distance, solution, res);
				}
			}
		}
		solution.remove(solution.size() - 1);
	}

}
