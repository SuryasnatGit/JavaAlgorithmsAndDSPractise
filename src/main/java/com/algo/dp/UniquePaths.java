package com.algo.dp;

/**
 * A robot is located at the top-left corner of a m ×n grid (marked ‘Start’ in the diagram below). The robot can only
 * move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid
 * (marked ‘Finish’ in the diagram below). How many possible unique paths are there?
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
	 * Approach 2 - using memoization. time complexity - O(mn) space complexity - O(mn)
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
		return dp(0, 0, m, n, matrix);
	}

	private int dp(int row, int col, int m, int n, int[][] matrix) {
		// base case
		if (row == m - 1 && col == n - 1)
			return 1;
		if (row >= m || col >= n)
			return 0;

		if (matrix[row + 1][col] == -1)
			matrix[row + 1][col] = dp(row + 1, col, m, n, matrix);
		if (matrix[row][col + 1] == -1)
			matrix[row][col + 1] = dp(row, col + 1, m, n, matrix);

		return matrix[row + 1][col] + matrix[row][col + 1];
	}

	/**
	 * Similar to Unique Paths, but now consider if some obstacles are added to the grids. How many unique paths would
	 * there be? An obstacle and empty space are marked as 1 and 0 respectively in the grid.
	 * 
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[][] hash = new int[m][n];

		boolean leftBlock = false;
		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				leftBlock = true;
			}
			hash[i][0] = leftBlock ? 0 : 1;
		}

		boolean topBlock = false;
		for (int i = 0; i < n; i++) {
			if (obstacleGrid[0][i] == 1) {
				topBlock = true;
			}
			hash[0][i] = topBlock ? 0 : 1;
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					hash[i][j] = 0;
				} else {
					hash[i][j] = hash[i - 1][j] + hash[i][j - 1];
				}
			}
		}

		return hash[m - 1][n - 1];
	}

	/**
	 * https://leetcode.com/problems/unique-paths-iii/
	 * 
	 * @param grid
	 * @return
	 */
	int ans;
	int[][] grid;
	int R, C;
	int tr, tc, target;
	int[] dr = new int[] { 0, -1, 0, 1 };
	int[] dc = new int[] { 1, 0, -1, 0 };
	Integer[][][] memo;

	public int uniquePathsIII(int[][] grid) {
		this.grid = grid;
		R = grid.length;
		C = grid[0].length;
		target = 0;

		int sr = 0, sc = 0;
		for (int r = 0; r < R; ++r)
			for (int c = 0; c < C; ++c) {
				if (grid[r][c] % 2 == 0)
					target |= code(r, c);

				if (grid[r][c] == 1) {
					sr = r;
					sc = c;
				} else if (grid[r][c] == 2) {
					tr = r;
					tc = c;
				}
			}

		memo = new Integer[R][C][1 << R * C];
		return dp(sr, sc, target);
	}

	public int code(int r, int c) {
		return 1 << (r * C + c);
	}

	public Integer dp(int r, int c, int todo) {
		if (memo[r][c][todo] != null)
			return memo[r][c][todo];

		if (r == tr && c == tc) {
			return todo == 0 ? 1 : 0;
		}

		int ans = 0;
		for (int k = 0; k < 4; ++k) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (0 <= nr && nr < R && 0 <= nc && nc < C) {
				if ((todo & code(nr, nc)) != 0)
					ans += dp(nr, nc, todo ^ code(nr, nc));
			}
		}
		memo[r][c][todo] = ans;
		return ans;
	}

	public static void main(String[] args) {
		UniquePaths up = new UniquePaths();
		int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		System.out.println(up.uniquePathsWithObstacles(obstacleGrid));
	}
}
