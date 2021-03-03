package com.algo.ds.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The map of a museum is a two-digit matrix of N*N. The security guard coordinates are given, and the wall coordinates
 * are also given. Ask the distance between each position of the map and the nearest guard. Do it with BFS. First put
 * all guard positions in current positions, and then move up and down, left and right for each position. If it is not a
 * wall, also put it in positions until there is no position.
 *
 */
public class SecurityGuardGraphBFS {

	public static void main(String[] args) {
		int[][] M = { { -2, 0, -2, 0 }, { 0, -1, 0, 0 }, { 0, -2, 0, 0 }, { 0, 0, -1, 0 }, };

		SecurityGuardGraphBFS sg = new SecurityGuardGraphBFS();
		sg.guardWall(M);
	}

	// default is 0. wall is -1. guard is -2. Find out the closest distance to any guard from default(non-wall)
	void guardWall(int[][] M) {
		int m = M.length;
		int n = M[0].length;

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (M[i][j] == -2) {
					queue.offer(i * n + j); // Put all guards into queue
				}
			}
		}

		int level = 1;
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int now = queue.poll();
				int x = now / n;
				int y = now % n;

				for (int[] dir : dirs) {
					int nextX = x + dir[0];
					int nextY = y + dir[1];

					if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && M[nextX][nextY] == 0) {
						M[nextX][nextY] = level;
						queue.offer(nextX * n + nextY);
					}
				}
			}

			level++;
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(M[i][j] + ",");
			}
			System.out.println();
		}
	}
}
