package com.algo.ds.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.techiedelight.com/find-shortest-path-source-destination-matrix-satisfies-given-constraints/
 * 
 * Category : Hard
 *
 */
public class ShortestPathSourceToDestWithConstraints {

	public class Node {
		int x;
		int y;
		int dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	public int pathLength(int[][] matrix) {
		int N = 8;

		int[] row = { 0, 0, 1, -1 };
		int[] col = { 1, -1, 0, 0 };

		boolean[][] visited = new boolean[N][N];

		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 1));

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.x;
			int y = node.y;
			int dist = node.dist;

			// if destination is reached
			if (x == N - 1 && y == N - 1)
				return dist;

			int curval = matrix[x][y];

			for (int i = 0; i < 4; i++) {
				int newx = x + row[i] * curval;
				int newy = y + col[i] * curval;
				if (isValid(newx, newy)) {
					Node newnode = new Node(newx, newy, dist + 1);
					if (!visited[newx][newy]) {
						queue.add(newnode);
						visited[newx][newy] = true;
					}
				}
			}
		}

		return 0;
	}

	private boolean isValid(int x, int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8;
	}

	public static void main(String[] args) {

		ShortestPathSourceToDestWithConstraints sp = new ShortestPathSourceToDestWithConstraints();

		int[][] matrix = { { 4, 4, 6, 5, 5, 1, 1, 1, 7, 4 }, { 3, 6, 2, 4, 6, 5, 7, 2, 6, 6 },
				{ 1, 3, 6, 1, 1, 1, 7, 1, 4, 5 }, { 7, 5, 6, 3, 1, 3, 3, 1, 1, 7 }, { 3, 4, 6, 4, 7, 2, 6, 5, 4, 4 },
				{ 3, 2, 5, 1, 2, 5, 1, 2, 3, 4 }, { 4, 2, 2, 2, 5, 2, 3, 7, 7, 3 }, { 7, 2, 4, 3, 5, 2, 2, 3, 6, 3 },
				{ 5, 1, 4, 2, 6, 4, 6, 7, 3, 7 }, { 1, 4, 1, 7, 5, 3, 6, 5, 3, 4 } };

		// Find a route in the matrix from source cell (0, 0) to
		// destination cell (N - 1, N - 1)
		int p = sp.pathLength(matrix);
		System.out.println(p);
	}

}
