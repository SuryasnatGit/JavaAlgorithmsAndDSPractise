package com.algo.dp;

/**
 * https://www.techiedelight.com/find-minimum-cost-reach-last-cell-matrix-first-cell/
 *
 */
public class MinCostFromFirstCellToLastCell {

	// T - exponential
	public int findMinCost_rec(int[][] matrix, int m, int n) {
		// base case
		if (m == 0 || n == 0)
			return Integer.MAX_VALUE;

		// if we are at first cell
		if (m == 1 && n == 1)
			return matrix[0][0];

		return matrix[m - 1][n - 1] + Integer.min(findMinCost_rec(matrix, m - 1, n), findMinCost_rec(matrix, m, n - 1));
	}

	// T - O(m * n) S - O(m * n)
	public int findMinCost_iter(int[][] matrix) {
		int r = matrix.length;
		int c = matrix[0].length;

		int[][] cost = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				cost[i][j] = matrix[i][j];

				// fill first row (there is only one way to reach any cell in the first row, that is from its adjacent
				// left cell)
				if (i == 0 && j > 0) {
					cost[0][j] += cost[0][j - 1];
				} else if (i > 0 && j == 0) {
					// fill first column (there is only one way to reach any cell in the first column, that is from its
					// adjacent top cell)
					cost[i][0] += cost[i - 1][0];
				} else if (i > 0 && j > 0) {
					// fill rest of the matrix (there are two way to reach any cell in the rest of the matrix, that is
					// from its adjacent left cell or adjacent top cell)
					cost[i][j] += Integer.min(cost[i - 1][j], cost[i][j - 1]);
				}
			}
		}

		return cost[r - 1][c - 1];
	}

	public static void main(String[] args) {
		MinCostFromFirstCellToLastCell min = new MinCostFromFirstCellToLastCell();
		int[][] matrix = { { 4, 7, 8, 6, 4 }, { 6, 7, 3, 9, 2 }, { 3, 8, 1, 2, 4 }, { 7, 1, 7, 3, 7 },
				{ 2, 9, 8, 9, 3 } };
		System.out.println(min.findMinCost_rec(matrix, matrix.length, matrix[0].length));
		System.out.println(min.findMinCost_iter(matrix));
	}
}
