package com.algo.ds.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a two-dimensional array or matrix, Do the breadth-First Search (BFS) to print the elements of the given matrix.
 * Implement a Breadth-first traversal in an iterative manner.
 *
 * https://algorithms.tutorialhorizon.com/breadth-first-search-bfs-in-2d-matrix-2d-array/
 */
public class BFSIn2DArray {

	public void bfs(int[][] matrix) {
		int r = matrix.length;
		int c = matrix[0].length;

		Queue<Cell> queue = new LinkedList<>();
		queue.add(new Cell(0, 0, matrix[0][0]));

		boolean[][] visited = new boolean[r][c];
		visited[0][0] = true;

		int[] row = { 0, 0, -1, 1 };
		int[] col = { 1, -1, 0, 0 };

		while (!queue.isEmpty()) {
			Cell curr = queue.poll();
			System.out.print(curr.val + " ");

			for (int i = 0; i < 4; i++) {
				int newrow = curr.row + row[i];
				int newcol = curr.col + col[i];
				if (isSafe(newrow, newcol, visited)) {
					visited[newrow][newcol] = true;
					queue.add(new Cell(newrow, newcol, matrix[newrow][newcol]));
				}
			}
		}
	}

	private boolean isSafe(int row, int col, boolean[][] visited) {
		return row >= 0 && row < 4 && col >= 0 && col < 4 && !visited[row][col];
	}

	public static void main(String[] args) {
		BFSIn2DArray bfs = new BFSIn2DArray();
		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		bfs.bfs(matrix);
	}
}

class Cell {
	int row;
	int col;
	int val;

	public Cell(int r, int c, int v) {
		this.row = r;
		this.col = c;
		this.val = v;
	}
}
