

package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest
 * transformation sequence(s) from beginWord to endWord, such that: Only one letter can be changed
 * at a time. Each intermediate word must exist in the word list.
 *
 * For example, given start = "hit", end = "cog", dict = ["hot","dot","dog","lot","log"], as one
 * shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
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
public class WordLadder2 {

	// Approach 1
	/**
	 * The idea is the same. To track the actual ladder, we need to add a pointer that points to the
	 * previous node in the WordNode class. In addition, the used word can not directly removed from the
	 * dictionary. The used word is only removed when steps change.
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
	 * 1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing
	 * nodes from start node to end node, and store node's next level neighbors to HashMap;
	 * 
	 * 2). Use DFS to output paths with the same distance as the shortest distance from distance
	 * HashMap: compare if the distance of the next level node equals the distance of the current node +
	 * 1.
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

    public static void main(String args[]) {
        String[] wordList = {"hot","dot","dog","lot","log"};
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(Arrays.asList(wordList));
        WordLadder2 wl = new WordLadder2();
		List<List<String>> result = wl.findLadders("hit", "cog", wordSet);
		result.forEach(l -> System.out.println(l));
    }
}
