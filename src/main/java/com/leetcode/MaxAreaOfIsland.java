package com.leetcode;

import java.util.Stack;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected
 * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * 
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * 
 * Category : Medium
 * 
 * Tags : Recursion, DFS
 */
public class MaxAreaOfIsland {

	public int maxAreaOfIslandRecursive(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int maxArea = 0;

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				maxArea = Math.max(maxArea, area(grid, visited, row, col));
			}
		}

		return maxArea;
	}

	private int area(int[][] grid, boolean[][] visited, int row, int col) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0
				|| visited[row][col]) {
			return 0;
		}

		visited[row][col] = true;
		// try for all 4 directions
		return 1 + area(grid, visited, row, col + 1) + area(grid, visited, row, col - 1)
				+ area(grid, visited, row + 1, col) + area(grid, visited, row - 1, col);
	}

	public int maxAreaOfIslandDFS(int[][] grid) {
		boolean[][] seen = new boolean[grid.length][grid[0].length];
		int[] dr = new int[] { 1, -1, 0, 0 };
		int[] dc = new int[] { 0, 0, 1, -1 };

		int ans = 0;
		for (int r0 = 0; r0 < grid.length; r0++) {
			for (int c0 = 0; c0 < grid[0].length; c0++) {
				if (grid[r0][c0] == 1 && !seen[r0][c0]) {
					int shape = 0;
					Stack<int[]> stack = new Stack<>();
					stack.push(new int[] { r0, c0 });
					seen[r0][c0] = true;
					while (!stack.empty()) {
						int[] node = stack.pop();
						int r = node[0], c = node[1];
						shape++;
						for (int k = 0; k < 4; k++) {
							int nr = r + dr[k];
							int nc = c + dc[k];
							if (0 <= nr && nr < grid.length && 0 <= nc && nc < grid[0].length && grid[nr][nc] == 1
									&& !seen[nr][nc]) {
								stack.push(new int[] { nr, nc });
								seen[nr][nc] = true;
							}
						}
					}
					ans = Math.max(ans, shape);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		MaxAreaOfIsland ma = new MaxAreaOfIsland();
		int[][] grid = new int[][] { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };
		System.out.println(ma.maxAreaOfIslandRecursive(grid));
		System.out.println(ma.maxAreaOfIslandDFS(grid));
	}
}
