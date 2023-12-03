
package com.algo.ds.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 * 
 * -1 : A wall or an obstacle.
 * 
 * 0 : A gate.
 * 
 * INF : Infinity means an empty room.
 * 
 * We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than
 * 2147483647. Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it
 * should be filled with INF
 * 
 * Time complexity O(n*m) Space complexity O(n*m)
 * 
 * https://leetcode.com/problems/walls-and-gates/
 * 
 * The performance of recursive DFS is very unstable. It is much slower than BFS if the rooms are interconnected. It is
 * only faster than BFS when small groups of rooms are isolated. Thus, for this problem we should prefer BFS over DFS.
 * And the best Solution is Multi End BFS
 * 
 * TBD: space and time complexity
 */
public class WallsAndGates {
	private static final int directions[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // directions from a point
	private static final int INF = Integer.MAX_VALUE;

	/**
	 * The solution of DFS is to perform dfs search from gate, but the problem of DFS is that it may search to the same
	 * position multiple times, and if it is shorter than the currently stored distance, the current distance is
	 * updated, so the algorithm is not efficient and stable enough.
	 * 
	 * @param rooms
	 */
	public void wallsAndGatesDFS(int[][] rooms) {
		if (rooms == null || rooms.length == 0 || rooms[0].length == 0)
			return;

		int m = rooms.length;
		int n = rooms[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) {
					fill(rooms, i, j, 0);
				}
			}
		}
	}

	private void fill(int[][] rooms, int i, int j, int distance) {
		int m = rooms.length;
		int n = rooms[0].length;

		if (i < 0 || i >= m || j < 0 || j >= n || rooms[i][j] < distance) {
			return;
		}

		rooms[i][j] = distance;

		fill(rooms, i - 1, j, distance + 1);
		fill(rooms, i, j + 1, distance + 1);
		fill(rooms, i + 1, j, distance + 1);
		fill(rooms, i, j - 1, distance + 1);
	}

	/**
	 * Push all gates into queue first. Then for each gate update its neighbor cells and push them to the queue.
	 * Repeating above steps until there is nothing left in the queue.
	 */
	public void wallsAndGatesBFS(int[][] rooms) {
		Queue<Cell> queue = new LinkedList<>();
		gates(rooms, queue);

		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			addNeighbors(rooms, cell.row, cell.col, queue);
		}

	}

	private void addNeighbors(int[][] rooms, int row, int col, Queue<Cell> queue) {
		for (int[] direction : directions) {
			int newrow = row + direction[0];
			int newcol = col + direction[1];
			if (newrow < 0 || newcol < 0 || newrow >= rooms.length || newcol >= rooms[0].length || rooms[newrow][newcol] != INF) {
				continue;
			}
			rooms[newrow][newcol] = 1 + rooms[row][col];
			queue.offer(new Cell(newrow, newcol));
		}
	}

	private void gates(int[][] rooms, Queue<Cell> queue) {
		for (int row = 0; row < rooms.length; row++) {
			for (int col = 0; col < rooms[0].length; col++) {
				if (rooms[row][col] == 0) {
					queue.offer(new Cell(row, col));
				}
			}
		}
	}

	class Cell {
		int row;
		int col;

		Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) {
		WallsAndGates wg = new WallsAndGates();
		int[][] rooms = { { INF, -1, 0, INF }, { INF, INF, INF, -1 }, { INF, -1, INF, -1 }, { 0, -1, INF, INF } };
		wg.wallsAndGatesDFS(rooms);
		Arrays.stream(rooms).forEach(a -> System.out.println(Arrays.toString(a)));
		System.out.println();
		int[][] rooms1 = { { INF, -1, 0, INF }, { INF, INF, INF, -1 }, { INF, -1, INF, -1 }, { 0, -1, INF, INF } };
		wg.wallsAndGatesBFS(rooms1);
		Arrays.stream(rooms1).forEach(a -> System.out.println(Arrays.toString(a)));
	}
}
