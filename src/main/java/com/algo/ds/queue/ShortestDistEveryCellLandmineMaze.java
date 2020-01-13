package com.algo.ds.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.techiedelight.com/find-shortest-distance-every-cell-landmine-maze/
 *
 */
public class ShortestDistEveryCellLandmineMaze {

	class Node {
		int x;
		int y;
		int dist;

		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.dist = d;
		}
	}

	public void shortestDist(char[][] matrix, int[][] result, int M, int N) {
		// bfs problem. define a queue
		Queue<Node> queue = new LinkedList<>();

		// check for land mines and enqueue all cells with mines
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (matrix[i][j] == 'M') {
					result[i][j] = 0;
					queue.add(new Node(i, j, 0));
				} else {
					result[i][j] = -1;
				}
			}
		}

		// 4 possible nodes to check from each cell
		int[] row = { 1, -1, 0, 0 };
		int[] col = { 0, 0, 1, -1 };

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.x;
			int y = node.y;
			int dist = node.dist;

			// try for all 4 directions
			for (int i = 0; i < 4; i++) {
				if (isValid(x + row[i], y + col[i], M, N) && isSafe(x + row[i], y + col[i], matrix, result)) {
					result[x + row[i]][y + col[i]] = dist + 1;
					queue.add(new Node(x + row[i], y + col[i], dist + 1));
				}
			}
		}
	}

	// if the cell is open and not covered yet
	private boolean isSafe(int i, int j, char[][] matrix, int[][] result) {
		return (matrix[i][j] == 'O' && result[i][j] == -1);
	}

	private boolean isValid(int i, int j, int M, int N) {
		return (i >= 0 && i < M && j >= 0 && j < N);
	}

	public static void main(String[] args) {
		ShortestDistEveryCellLandmineMaze sh = new ShortestDistEveryCellLandmineMaze();
		char[][] mat = { { 'O', 'M', 'O', 'O', 'X' }, { 'O', 'X', 'X', 'O', 'M' }, { 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'X', 'X', 'X', 'O' }, { 'O', 'O', 'M', 'O', 'O' }, { 'O', 'X', 'X', 'M', 'O' } };

		int[][] result = new int[6][5];
		sh.shortestDist(mat, result, 6, 5);

		// print results
		for (int i = 0; i < 6; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
	}
}
