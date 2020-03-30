package com.algo.dp;

import java.util.Map;

/**
 * https://www.techiedelight.com/find-longest-sequence-formed-adjacent-numbers-matrix/
 * 
 */
public class LongestSeqFormedByAdjNumbers {

	public static String formLongestSeq_recur(int[][] matrix, int i, int j) {
		if (!isValid(matrix, i, j))
			return null;

		String path = null;

		if (i > 0 && matrix[i - 1][j] == matrix[i][j] + 1) {
			path = formLongestSeq_recur(matrix, i - 1, j);
		}
		if (i + 1 < matrix.length && matrix[i + 1][j] == matrix[i][j] + 1) {
			path = formLongestSeq_recur(matrix, i + 1, j);
		}
		if (j > 0 && matrix[i][j - 1] == matrix[i][j] + 1) {
			path = formLongestSeq_recur(matrix, i, j - 1);
		}
		if (j + 1 < matrix.length && matrix[i][j + 1] == matrix[i][j] + 1) {
			path = formLongestSeq_recur(matrix, i, j + 1);
		}

		return path != null ? matrix[i][j] + "->" + path : String.valueOf(matrix[i][j]);
	}

	private static boolean isValid(int[][] matrix, int i, int j) {
		return (i >= 0 && i < matrix.length && j >= 0 && j < matrix.length);
	}

	public static String formLongestSeq_lookup(int[][] matrix, int i, int j, Map<String, String> lookup) {
		if (!isValid(matrix, i, j))
			return null;

		String key = i + "|" + j;

		if (!lookup.containsKey(key)) {
			String path = null;

			if (i > 0 && matrix[i - 1][j] == matrix[i][j] + 1) {
				path = formLongestSeq_lookup(matrix, i - 1, j, lookup);
			}
			if (i + 1 < matrix.length && matrix[i + 1][j] == matrix[i][j] + 1) {
				path = formLongestSeq_lookup(matrix, i + 1, j, lookup);
			}
			if (j > 0 && matrix[i][j - 1] == matrix[i][j] + 1) {
				path = formLongestSeq_lookup(matrix, i, j - 1, lookup);
			}
			if (j + 1 < matrix.length && matrix[i][j + 1] == matrix[i][j] + 1) {
				path = formLongestSeq_lookup(matrix, i, j + 1, lookup);
			}

			if (path != null) {
				lookup.put(key, matrix[i][j] + "->" + path);
			} else {
				lookup.put(key, String.valueOf(matrix[i][j]));
			}
		}

		return lookup.get(key);
	}

	public static void main(String[] args) {
		int M[][] = { { 10, 13, 14, 21, 23 }, { 11, 9, 22, 2, 3 }, { 12, 8, 1, 5, 4 }, { 15, 24, 7, 6, 20 },
				{ 16, 17, 18, 19, 25 } };

		String res = ""; // stores longest path found so far
		String str; // stores current path
		long resSize = Long.MIN_VALUE; // stores no. of elements in res

		// from each cell (i, j), find the longest path starting from it
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				// str would be like 1 - 2 - 3 - 4 - 5 -
				str = formLongestSeq_recur(M, i, j);

				// find number of elements involved in current path
				long size = str.chars().filter(ch -> ch == '-').count();

				// update result if longer path is found
				if (size > resSize) {
					res = str;
					resSize = size;
				}
			}
		}

		// print the path
		System.out.println(res);
	}
}
