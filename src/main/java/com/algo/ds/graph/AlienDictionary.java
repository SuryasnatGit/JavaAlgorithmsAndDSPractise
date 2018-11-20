package com.algo.ds.graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are
 * unknown to you. You receive a list of words from the dictionary, where words are sorted
 * lexicographically by the rules of this new language. Derive the order of letters in this
 * language.
 * 
 * https://leetcode.com/problems/alien-dictionary/.
 * 
 * Example:
 * 
 * Input: words[] = {"baa", "abcd", "abca", "cab", "cad"} Output: Order of characters is 'b', 'd',
 * 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd",
 * therefore 'b' is before 'a' in output. Similarly we can find other orders.
 * 
 * Input: words[] = {"caa", "aaa", "aab"} Output: Order of characters is 'c', 'a', 'b'
 * 
 */
public class AlienDictionary {

	/**
	 * The idea is to create a graph of characters and then find topological sorting of the created
	 * graph. Following are the detailed steps.
	 * 
	 * 1) Create a graph g with number of vertices equal to the size of alphabet in the given alien
	 * language. For example, if the alphabet size is 5, then there can be 5 characters in words.
	 * Initially there are no edges in graph.
	 * 
	 * 2) Do following for every pair of adjacent words in given sorted array. …..a) Let the current
	 * pair of words be word1 and word2. One by one compare characters of both words and find the first
	 * mismatching characters. …..b) Create an edge in g from mismatching character of word1 to that of
	 * word2.
	 * 
	 * 3) Print topological sorting of the above created graph.
	 * 
	 * Time Complexity: The first step to create a graph takes O(n + m) time where n is number of given
	 * words and m is number of characters in given alphabet. The second step is also topological
	 * sorting. Note that there would be m vertices and at-most (n-1) edges in the graph. The time
	 * complexity of topological sorting is O(V+E) which is O(n + m) here. So overall time complexity is
	 * O(n + m) + O(n + m) which is O(n + m).
	 * 
	 * @param words
	 * @return
	 */
	public String alienOrder_usingTopologicalSort(String[] words) {
		Set<Character> allCharacters = new HashSet<>();
		Map<Character, Set<Character>> graph = buildGraph(words, new HashMap<>(), allCharacters);
		Deque<Character> stack = new LinkedList<>();
		Set<Character> visited = new HashSet<>();
		Set<Character> dfs = new HashSet<>();

		for (char ch : allCharacters) {
			if (topSortUtil(ch, stack, visited, dfs, graph)) {
				return "";
			}
		}

		StringBuffer buff = new StringBuffer();
		while (!stack.isEmpty()) {
			buff.append(stack.pollFirst());
		}
		return buff.toString();
	}

	private boolean topSortUtil(char vertex, Deque<Character> stack, Set<Character> visited, Set<Character> dfs,
			Map<Character, Set<Character>> graph) {
		if (visited.contains(vertex)) {
			return false;
		}
		visited.add(vertex);
		dfs.add(vertex);
		Set<Character> set = graph.get(vertex);
		if (set != null) {
			for (char neighbor : set) {
				if (dfs.contains(neighbor)) {
					return true;
				}
				if (topSortUtil(neighbor, stack, visited, dfs, graph)) {
					return true;
				}
			}
		}
		dfs.remove(vertex);
		stack.offerFirst(vertex);
		return false;
	}

	public String alienOrder1(String words[]) {
		Map<Character, Integer> degree = new HashMap<>();
		Map<Character, Set<Character>> graph = buildGraph(words, degree, new HashSet<>());

		Queue<Character> zeroDegreeNodes = new LinkedList<>();
		for (Map.Entry<Character, Integer> entry : degree.entrySet()) {
			if (entry.getValue() == 0) {
				zeroDegreeNodes.offer(entry.getKey());
			}
		}

		StringBuilder result = new StringBuilder();

		while (!zeroDegreeNodes.isEmpty()) {
			char vertex = zeroDegreeNodes.poll();
			result.append(vertex);
			Set<Character> neighbors = graph.get(vertex);
			if (neighbors != null) {
				for (char neighbor : graph.get(vertex)) {
					int count = degree.get(neighbor);
					count--;
					if (count == 0) {
						zeroDegreeNodes.offer(neighbor);
					} else {
						degree.put(neighbor, count);
					}
				}
			}
			graph.remove(vertex);
		}

		return graph.size() > 0 ? "" : result.toString();
	}

	/**
	 * degree is only used for BFS. Not for DFS.
	 */
	private Map<Character, Set<Character>> buildGraph(String words[], Map<Character, Integer> degree,
			Set<Character> allCharacters) {
		getAllChars(words, degree, allCharacters);
		Set<Character> all = new HashSet<>(allCharacters);
		Map<Character, Set<Character>> graph = new HashMap<>();
		for (int i = 0; i < words.length - 1; i++) {
			String nextWord = words[i + 1];
			for (int k = 0; k < Math.min(words[i].length(), nextWord.length()); k++) {
				if (words[i].charAt(k) != nextWord.charAt((k))) {
					all.remove(words[i].charAt(k));
					Set<Character> set = graph.get(words[i].charAt(k));
					if (set == null) {
						set = new HashSet<>();
						graph.put(words[i].charAt(k), set);
					}
					set.add(nextWord.charAt(k));
					degree.compute(nextWord.charAt(k), (key, count) -> count + 1);
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

	public static void main(String args[]) {
		AlienDictionary ad = new AlienDictionary();
		String[] words1 = { "zy", "zx" };
		String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
		String[] words2 = { "wrtkj", "wrt" };
		String result = ad.alienOrder1(words2);
		System.out.println(result);
		System.out.println(ad.alienOrder_usingTopologicalSort(words1));
		System.out.println(ad.alienOrder_usingTopologicalSort(words));

		// w -> e
		// e -> r
		// t -> f
		// r -> t
		//
	}
}
