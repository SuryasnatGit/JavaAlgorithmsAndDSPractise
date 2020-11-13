package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearch {

	public static void main(String[] args) {
		char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'o', 'r' },
				{ 'i', 'f', 'l', 'v' } };

		String[] words = { "oath", "pea", "eat", "rain" };

		WordSearch ws = new WordSearch();
		System.out.println(ws.isWordExist(board, "pea"));
		System.out.println(ws.isWordExist(board, "eat"));
		ws.findWords(board, words).forEach(a -> System.out.print(a + " "));
		// List<List<int[]>> res = ws2.findWords(board, words);

	}

	// Problem 1

	private int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private int m = -1;
	private int n = -1;

	public boolean isWordExist(char[][] board, String word) {
		Set<Integer> visited = new HashSet<Integer>();

		m = board.length;
		n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) {
					visited.add(i * n + j);
					boolean res = dfs(board, word, i, j, visited, 1);
					if (res) {
						return true;
					}
					visited.remove(i * n + j);
				}
			}
		}

		return false;
	}

	private boolean dfs(char[][] board, String word, int i, int j, Set<Integer> visited, int pos) {
		if (pos == word.length()) {
			return true;
		}

		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			int newId = x * n + y;

			if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == word.charAt(pos) && !visited.contains(newId)) {
				visited.add(newId);
				boolean res = dfs(board, word, x, y, visited, pos + 1);
				if (res) {
					return true;
				}
				visited.remove(newId);
			}
		}

		return false;
	}

	// Problem 2 - find words using trie approach
	/**
	 * 
	 * Given a 2D board and a list of words from the dictionary, find all words in the board.
	 * 
	 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
	 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
	 * 
	 * For example, Given words = ["oath","pea","eat","rain"] and board =
	 * 
	 * [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'], ['i','f','l','v'] ] Return ["eat","oath"].
	 * 
	 * Given a dictionary and a string array, find out all the characters that may appear in the string to form the
	 * words in the dictionary, that is, the string array is a 2-dimensional char matrix, and each character can go in
	 * eight directions. DFS solved it and asked why not BFS
	 */
	public List<String> findWords(char[][] board, String[] words) {
		int m = board.length;
		int n = board[0].length;

		TrieNode root = new TrieNode();

		for (String word : words) {
			root.insert(word, 0);
		}

		Set<Integer> visited = new HashSet<Integer>();
		List<String> res = new ArrayList<String>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				visited.add(i * n + j);
				dfs(board, res, i, j, root, visited);
				visited.remove(i * n + j);
			}
		}

		return res;
	}

	private void dfs(char[][] board, List<String> res, int i, int j, TrieNode root, Set<Integer> visited) {
		if (root == null) {
			return;
		}

		if (root.hasWord) {
			if (!res.contains(root.str)) {
				res.add(root.str);
			}
		}

		if (root.children.containsKey(board[i][j])) { // This is the start point
			for (int[] dir : directions) {
				int x = i + dir[0];
				int y = j + dir[1];
				int id = x * n + y;

				if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited.contains(id)) {
					visited.add(id);
					dfs(board, res, x, y, root.children.get(board[i][j]), visited);
					visited.remove(id);
				}
			}
		}
	}

	class TrieNode {
		Map<Character, TrieNode> children = null;
		boolean hasWord = false;
		String str;

		TrieNode() {
			this.children = new HashMap<Character, TrieNode>();
		}

		void insert(String word, int pos) {
			if (pos == word.length()) {
				this.hasWord = true;
				this.str = word;
				return;
			}

			char key = word.charAt(pos);
			TrieNode node = children.get(key);

			if (node == null) {
				children.put(key, new TrieNode());
			}

			children.get(key).insert(word, pos + 1);
		}

		TrieNode search(String word, int pos) {
			if (pos == word.length()) {
				return this;
			}

			int key = word.charAt(pos);
			TrieNode node = children.get(key);

			if (node == null) {
				return null;
			}

			return children.get(key).search(word, pos + 1);
		}

		boolean hasWord(String word) {
			TrieNode node = search(word, 0);
			return node != null && node.hasWord;
		}

		// To solve following question, 如果一个长字符串中有字典中的单词，替换
		TrieNode containsPrefix(String word, int pos) { // Word is in sentence
			if (pos == word.length()) {
				if (this.hasWord) {
					return this;
				}
				return null; // Return itself, nothing to replace
			}

			int key = word.charAt(pos);
			TrieNode node = this.children.get(key);

			if (node == null) {
				return null; // Nothing to replace
			}

			if (node.hasWord) {
				return node; // Once find a prefix, just return
			}

			return children.get(key).search(word, pos + 1);
		}
	}

	// Problem 3
	public List<List<int[]>> findWordsWithPath(char[][] board, String word) {
		m = board.length;
		n = board[0].length;

		List<List<int[]>> res = new ArrayList<List<int[]>>();
		List<int[]> list = new ArrayList<int[]>();
		Set<Integer> visited = new HashSet<Integer>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) {
					visited.add(i * n + j);
					list.add(new int[] { i, j });
					helper(res, list, board, visited, word, 1, i, j);
					list.remove(list.size() - 1);
					visited.remove(i * n + j);
				}
			}
		}

		return res;
	}

	private void helper(List<List<int[]>> res, List<int[]> list, char[][] board, Set<Integer> visited, String word,
			int pos, int i, int j) {
		if (pos == word.length()) {
			res.add(new ArrayList<int[]>(list));
			return;
		}

		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			int newId = x * n + y;

			if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == word.charAt(pos) && !visited.contains(newId)) {
				visited.add(newId);
				list.add(new int[] { x, y });
				helper(res, list, board, visited, word, pos + 1, x, y);
				list.remove(list.size() - 1);
				visited.remove(newId);
			}
		}
	}
}
