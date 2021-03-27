package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Category : Hard
 * 
 * Tags : dfs, backtracking
 *
 */
public class WordSearch {

	public static void main(String[] args) {
		char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'o', 'r' },
				{ 'i', 'f', 'l', 'v' } };

		String[] words = { "oath", "pea", "eat", "rain", "hf" };

		WordSearch ws = new WordSearch();
		System.out.println(ws.isWordExist(board, "pea"));
		System.out.println(ws.isWordExist(board, "eat"));

		ws.findAllWordsInBoardDFS(board, words).forEach(a -> System.out.print(a + " , "));
		// List<List<int[]>> res = ws2.findWords(board, words);

		ws.findWordsWithPath(board, "oath").forEach(a -> System.out.println(a));

	}

	/**
	 * Problem 1: Given an m x n board and a word, find if the word exists in the grid.
	 * 
	 * The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally
	 * or vertically neighboring. The same letter cell may not be used more than once.
	 * 
	 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED" Output: true
	 * 
	 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB" Output: false
	 * 
	 * Time Complexity: O(N * 3^L ) where N is the number of cells in the board and L is the length of the word to be
	 * matched.
	 * 
	 * For the backtracking function, initially we could have at most 4 directions to explore, but further the choices
	 * are reduced into 3 (since we won't go back to where we come from). As a result, the execution trace after the
	 * first step could be visualized as a 3-ary tree, each of the branches represent a potential exploration in the
	 * corresponding direction. Therefore, in the worst case, the total number of invocation would be the number of
	 * nodes in a full 3-nary tree, which is about 3^L .
	 * 
	 * We iterate through the board for backtracking, i.e. there could be N times invocation for the backtracking
	 * function in the worst case.
	 * 
	 * Space Complexity: O(L) where L is the length of the word to be matched.
	 * 
	 * The main consumption of the memory lies in the recursion call of the backtracking function. The maximum length of
	 * the call stack would be the length of the word. Therefore, the space complexity of the algorithm is O(L)
	 */

	private int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private int rows = -1;
	private int cols = -1;

	public boolean isWordExist(char[][] board, String word) {
		Set<Integer> visited = new HashSet<Integer>();

		rows = board.length;
		cols = board[0].length;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (board[row][col] == word.charAt(0)) {
					int cellIndex = row * cols + col;
					visited.add(cellIndex);
					boolean res = dfs(board, word, row, col, visited, 1);
					if (res) {
						return true;
					}
					visited.remove(cellIndex);
				}
			}
		}

		return false;
	}

	private boolean dfs(char[][] board, String word, int row, int col, Set<Integer> visited, int pos) {
		if (pos == word.length()) {
			return true;
		}

		for (int[] dir : directions) {
			int x = row + dir[0];
			int y = col + dir[1];
			int newCellIndex = x * cols + y;

			if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == word.charAt(pos)
					&& !visited.contains(newCellIndex)) {
				visited.add(newCellIndex);
				boolean res = dfs(board, word, x, y, visited, pos + 1);
				if (res) {
					return true;
				}
				visited.remove(newCellIndex);
			}
		}

		return false;
	}

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
	 * eight directions. DFS solved it and asked why not BFS.
	 * 
	 * T - O(w * n) S - O(n) w = number of words, n = number of total chars in grid
	 */
	public List<String> findAllWordsInBoardDFS(char[][] board, String[] words) {
		// create a list to store valid words
		List<String> result = new ArrayList<>();
		// set to mark visited cells
		Set<Integer> visited = new HashSet<Integer>();
		rows = board.length;
		cols = board[0].length;

		// loop through all the words
		for (String word : words) {

			// perform a sequential search
			for (int row = 0; row < rows; row++) {

				boolean found = false;

				for (int col = 0; col < cols; col++) {

					int newCellIndex = row * cols + col;
					visited.add(newCellIndex);

					if (board[row][col] == word.charAt(0) && dfs(board, word, row, col, visited, 1)) {
						result.add(word);
						found = true;
						break;
					}

					visited.remove(newCellIndex);
				}

				// if the word was found, break from the loop
				if (found == true) {
					break;
				}
			}
		}

		return result;
	}

	// Problem 3
	public List<List<int[]>> findWordsWithPath(char[][] board, String word) {
		rows = board.length;
		cols = board[0].length;

		List<List<int[]>> res = new ArrayList<List<int[]>>();
		List<int[]> list = new ArrayList<int[]>();
		Set<Integer> visited = new HashSet<Integer>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == word.charAt(0)) {
					visited.add(i * cols + j);
					list.add(new int[] { i, j });
					helper(res, list, board, visited, word, 1, i, j);
					list.remove(list.size() - 1);
					visited.remove(i * cols + j);
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
			int newId = x * cols + y;

			if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == word.charAt(pos)
					&& !visited.contains(newId)) {
				visited.add(newId);
				list.add(new int[] { x, y });
				helper(res, list, board, visited, word, pos + 1, x, y);
				list.remove(list.size() - 1);
				visited.remove(newId);
			}
		}
	}
}
