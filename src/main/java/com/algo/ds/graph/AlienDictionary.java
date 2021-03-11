package com.algo.ds.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You
 * receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new
 * language. Derive the order of letters in this language.
 * 
 * https://leetcode.com/problems/alien-dictionary/.
 * 
 * Example:
 * 
 * Input: words[] = {"baa", "abcd", "abca", "cab", "cad"} Output: Order of characters is 'b', 'd', 'a', 'c' Note that
 * words are sorted and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly we can find other orders.
 * 
 * Input: words[] = {"caa", "aaa", "aab"} Output: Order of characters is 'c', 'a', 'b'
 * 
 * Time complexity: Say the number of characters in the dictionary (including duplicates) is n. Building the graph takes
 * O(n). Topological sort takes O(V + E). V <= n. E also can't be larger than n. So the overall time complexity is O(n).
 * 
 * Category : Hard
 * 
 */
public class AlienDictionary {

	/**
	 * The idea is to create a graph of characters and then find topological sorting of the created graph. Following are
	 * the detailed steps.
	 * 
	 * 1) Create a graph g with number of vertices equal to the size of alphabet in the given alien language. For
	 * example, if the alphabet size is 5, then there can be 5 characters in words. Initially there are no edges in
	 * graph.
	 * 
	 * 2) Do following for every pair of adjacent words in given sorted array. ..a) Let the current pair of words be
	 * word1 and word2. One by one compare characters of both words and find the first mismatching characters. ..b)
	 * Create an edge in g from mismatching character of word1 to that of word2.
	 * 
	 * 3) Print topological sorting of the above created graph.
	 * 
	 * Time Complexity: The first step to create a graph takes O(n + m) time where n is number of given words and m is
	 * number of characters in given alphabet. The second step is also topological sorting. Note that there would be m
	 * vertices and at-most (n-1) edges in the graph. The time complexity of topological sorting is O(V+E) which is O(n
	 * + m) here. So overall time complexity is O(n + m) + O(n + m) which is O(n + m).
	 * 
	 * @param words
	 * @return
	 */
	public String alienOrder_usingTopologicalSort(String[] words) {
		Set<Character> allCharacters = new HashSet<>();

		// step 1 - build the graph
		Map<Character, Set<Character>> graph = buildGraph(words, allCharacters);

		Stack<Character> stack = new Stack<>();
		Set<Character> visited = new HashSet<>();
		Set<Character> dfs = new HashSet<>();

		for (char ch : allCharacters) {
			if (topSortUtil(ch, stack, visited, dfs, graph)) {
				return "";
			}
		}

		StringBuffer buff = new StringBuffer();
		while (!stack.isEmpty()) {
			buff.append(stack.pop());
		}

		return buff.toString();
	}

	/**
	 * degree is only used for BFS. Not for DFS.
	 */
	private Map<Character, Set<Character>> buildGraph(String words[], Set<Character> allCharacters) {

		Map<Character, Integer> degree = new HashMap<Character, Integer>();

		getAllChars(words, degree, allCharacters);

		Set<Character> all = new HashSet<>(allCharacters);
		Map<Character, Set<Character>> graph = new HashMap<>();

		for (int i = 0; i < words.length - 1; i++) {

			String currentWord = words[i];
			String nextWord = words[i + 1];

			for (int k = 0; k < Math.min(currentWord.length(), nextWord.length()); k++) {

				char currChar = currentWord.charAt(k);
				char nextChar = nextWord.charAt(k);

				if (currChar != nextChar) {
					all.remove(currChar);
					Set<Character> set = graph.get(currChar);
					if (set == null) {
						set = new HashSet<>();
						graph.put(currChar, set);
					}
					set.add(nextChar);
					degree.compute(nextChar, (key, count) -> count + 1);
					break;
				}
			}
		}

		for (char ch : all) {
			graph.put(ch, null);
		}

		return graph;
	}

	private void getAllChars(String words[], Map<Character, Integer> degree, Set<Character> allCharacters) {
		for (String word : words) {
			for (char ch : word.toCharArray()) {
				allCharacters.add(ch);
				degree.computeIfAbsent(ch, key -> 0);
			}
		}
	}

	private boolean topSortUtil(char vertex, Stack<Character> stack, Set<Character> visited, Set<Character> dfs,
			Map<Character, Set<Character>> graph) {

		if (visited.contains(vertex)) {
			return false;
		}

		visited.add(vertex);
		dfs.add(vertex);

		Set<Character> neighbors = graph.get(vertex);
		if (neighbors != null) {
			for (char neighbor : neighbors) {
				if (dfs.contains(neighbor)) {
					return true;
				}
				if (topSortUtil(neighbor, stack, visited, dfs, graph)) {
					return true;
				}
			}
		}

		dfs.remove(vertex);
		stack.push(vertex);

		return false;
	}

	public static void main(String args[]) {
		AlienDictionary ad = new AlienDictionary();

		System.out.println(ad.alienOrder_usingTopologicalSort(new String[] { "zy", "zx" }));

		System.out.println(ad.alienOrder_usingTopologicalSort(new String[] { "wrt", "wrf", "er", "ett", "rftt" }));

		System.out.println(ad.alienOrder_usingTopologicalSort(new String[] { "wrtkj", "wrt" }));
	}
}
