package com.algo.ds.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a rectangular field with few sensors present on it, cross it by taking the shortest safe route without
 * activating the sensors.
 * 
 * 
 * The rectangular field is given in the form of M x N matrix and we need to find the shortest path from any cell in
 * first column to any cell in the last column of the matrix. The sensors are marked by value 0 in the matrix and all
 * its 8 adjacent cells can also activate the sensors. The path can only be constructed out of cells having value 1 and
 * at any given moment, we can only move one step in one of the four directions. The valid moves are:
 * 
 * https://www.techiedelight.com/find-shortest-safe-route-field-sensors-present/
 * 
 * Category : Hard
 *
 */
public class ShortestSafeRouteWithSensors {

	class Node {
		int x;
		int y;
		int dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	private int M = 10;
	private int N = 10;

	public int shortestPath(int[][] field) {
		boolean[][] visited = new boolean[M][N];

		int[] xPos = { 0, -1, -1, -1, 1, 0, 1, 1 };
		int[] yPos = { -1, 1, -1, 0, 0, 1, 1, -1 };

		// mark adjacent cells of unsafe cells as unsafe also
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// check all 8 possible adjacent positions
				for (int k = 0; k < 8; k++) {
					if (field[i][j] == 0 && isValid(i + xPos[k], j + yPos[k]) && field[i + xPos[k]][j + yPos[k]] == 1) {
						field[i + xPos[k]][j + yPos[k]] = Integer.MAX_VALUE;
					}
				}
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (field[i][j] == Integer.MAX_VALUE) {
					field[i][j] = 0;
				}
			}
		}

		int[] x1Pos = { -1, 1, 0, 0 };
		int[] y1Pos = { 0, 0, -1, 1 };

		// declare queue for BFS
		Queue<Node> queue = new LinkedList<>();

		// mark all nodes in first column as visited and add to queue with distance as 0
		for (int i = 0; i < 8; i++) {
			if (field[i][0] == 1) {
				queue.add(new Node(i, 0, 0));
				visited[i][0] = true;
			}
		}

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.x;
			int y = node.y;
			int dist = node.dist;

			// if last column reached then return dist
			if (y == N - 1)
				return dist;

			// check all possible 4 navigable positions for each cell
			for (int i = 0; i < 4; i++) {
				if (isValid(x + x1Pos[i], y + y1Pos[i]) && isSafe(field, visited, x + x1Pos[i], y + y1Pos[i])) {
					visited[x + x1Pos[i]][y + y1Pos[i]] = true;
					queue.add(new Node(x + x1Pos[i], y + y1Pos[i], dist + 1));
				}
			}
		}

		return Integer.MAX_VALUE;
	}

	private boolean isSafe(int[][] field, boolean[][] visited, int i, int j) {
		return (field[i][j] == 1 && !visited[i][j]);
	}

	private boolean isValid(int x, int y) {
		return (x >= 0 && x < M && y >= 0 && y < N);
	}

	public static void main(String[] args) {
		ShortestSafeRouteWithSensors sh = new ShortestSafeRouteWithSensors();

		int[][] field = { { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
				{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		System.out.println(sh.shortestPath(field));
	}
}
