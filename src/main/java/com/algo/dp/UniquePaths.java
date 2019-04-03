package com.algo.dp;

/**
 * A robot is located at the top-left corner of a m ×n grid (marked ‘Start’ in
 * the diagram below). The robot can only move either down or right at any point
 * in time. The robot is trying to reach the bottom-right corner of the grid
 * (marked ‘Finish’ in the diagram below). How many possible unique paths are
 * there?
 * 
 * @author M_402201
 *
 */
public class UniquePaths {

	/**
	 * Approach 1: not optimal solution, time complexity is exponential
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		return backtrack(0, 0, m, n);
	}

	private int backtrack(int row, int col, int m, int n) {
		// base case
		if (row == m - 1 && col == n - 1)
			return 1;
		if (row >= m || col >= n)
			return 0;
		return backtrack(row + 1, col, m, n) + backtrack(row, col + 1, m, n);
	}

	/**
	 * Approach 2 - using memoization. time complexity - O(mn) space complexity -
	 * O(mn)
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths_memoization(int m, int n) {
		int[][] matrix = new int[m + 1][n + 1]; // for caching intermediate results
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				matrix[i][j] = -1;// to start
			}
		}
		return backtrack1(0, 0, m, n, matrix);
	}

	private int backtrack1(int row, int col, int m, int n, int[][] matrix) {
		// base case
		if (row == m - 1 && col == n - 1)
			return 1;
		if (row >= m || col >= n)
			return 0;

		if (matrix[row + 1][col] == -1)
			matrix[row + 1][col] = backtrack1(row + 1, col, m, n, matrix);
		if (matrix[row][col + 1] == -1)
			matrix[row][col + 1] = backtrack1(row, col + 1, m, n, matrix);

		return matrix[row + 1][col] + matrix[row][col + 1];
	}

	/**
	 * TODO: to complete
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths_combinatorial(int m, int n) {
		return 0;
	}

	/**
	 * Similar to Unique Paths, but now consider if some obstacles are added to the
	 * grids. How many unique paths would there be? An obstacle and empty space are
	 * marked as 1 and 0 respectively in the grid.
	 * 
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		if (m == 0)
			return 0;
		int n = obstacleGrid[0].length;
		int[][] mat = new int[m + 1][n + 1];
		mat[m - 1][n] = 1;
		for (int r = m - 1; r >= 0; r--) {
			for (int c = n - 1; c >= 0; c--) {
				mat[r][c] = (obstacleGrid[r][c] == 1) ? 0 : mat[r][c + 1] + mat[r + 1][c];
			}
		}
		return mat[0][0];
	}
}
