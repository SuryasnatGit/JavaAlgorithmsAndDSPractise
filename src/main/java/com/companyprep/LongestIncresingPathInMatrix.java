package com.companyprep;

/**
 * 329. Given an integer matrix, find the length of the longest increasing path.
 * 
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move
 * outside of the boundary (i.e. wrap-around is not allowed).
 * 
 * Example 1:
 * 
 * nums = [ [9,9,4], [6,6,8], [2,1,1] ] Return 4 The longest increasing path is [1, 2, 6, 9].
 * 
 * Example 2:
 * 
 * nums = [ [3,4,5], [3,2,6], [2,2,1] ] Return 4 The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not
 * allowed.
 * 
 * T - O(row * col)
 */
public class LongestIncresingPathInMatrix {

	private int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public int longestPath(int[][] matrix) {
		int[][] dp = new int[matrix.length][matrix[0].length];

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int len = dfs(matrix, i, j, dp);
				max = Math.max(max, len);
			}
		}

		return max;
	}

	private int dfs(int[][] matrix, int i, int j, int[][] dp) {
		if (dp[i][j] != 0)
			return dp[i][j];

		int max = 1;
		for (int[] direction : directions) {
			int x = i + direction[0];
			int y = j + direction[1];
			if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
				int len = dfs(matrix, x, y, dp) + 1;
				max = Integer.max(max, len);
			}
		}

		dp[i][j] = max;
		return dp[i][j];
	}

	public static void main(String[] args) {
		LongestIncresingPathInMatrix lip = new LongestIncresingPathInMatrix();
		int[][] matrix = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
		System.out.println(lip.longestPath(matrix));
	}

}
