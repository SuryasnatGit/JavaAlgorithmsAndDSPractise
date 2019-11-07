package com.algo.backtracking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a maze in the form of the binary rectangular matrix, find length of the shortest path in maze from given source
 * to given destination. The path can only be constructed out of cells having value 1 and at any given moment, we can
 * only move one step in one of the four directions.
 * 
 * https://www.techiedelight.com/find-shortest-path-in-maze/
 * 
 * https://www.techiedelight.com/find-longest-possible-route-matrix/
 * 
 * https://www.techiedelight.com/lee-algorithm-shortest-path-in-a-maze/
 * 
 * Category : Hard
 * 
 */
public class ShortestLongestPathInMaze {

	private int M = 10;
	private int N = 10;

	// SOlution 1 - using backtracking
	public int findShortestPath(int[][] maze, int destX, int destY) {

		int[][] visited = new int[M][N];

		return findShortestPath(maze, visited, 0, 0, destX, destY, 0, Integer.MAX_VALUE);
	}

	/**
	 * 
	 * @param maze
	 * @param visited
	 * @param i
	 * @param j
	 * @param destX
	 * @param destY
	 * @param dist
	 *            maintains length of path from source cell(0,0) to current cell(i,j)
	 * @param minDist
	 *            length of longest path from source to destination found so far
	 * @return
	 */
	private int findShortestPath(int[][] maze, int[][] visited, int i, int j, int destX, int destY, int dist,
			int minDist) {

		// if dest points are reached return the min
		if (i == destX && j == destY)
			return Integer.min(dist, minDist);

		// mark this point as visited
		visited[i][j] = 1;

		if (isValid(i + 1, j) && isSafe(maze, visited, i + 1, j)) {
			minDist = findShortestPath(maze, visited, i + 1, j, destX, destY, dist + 1, minDist);
		}

		if (isValid(i - 1, j) && isSafe(maze, visited, i - 1, j)) {
			minDist = findShortestPath(maze, visited, i - 1, j, destX, destY, dist + 1, minDist);
		}

		if (isValid(i, j + 1) && isSafe(maze, visited, i, j + 1)) {
			minDist = findShortestPath(maze, visited, i, j + 1, destX, destY, dist + 1, minDist);
		}

		if (isValid(i, j - 1) && isSafe(maze, visited, i, j - 1)) {
			minDist = findShortestPath(maze, visited, i, j - 1, destX, destY, dist + 1, minDist);
		}

		// if nothing satisfied then backtrack
		visited[i][j] = 0;

		return minDist;
	}

	// if the next point is unvisited and next point has value 1
	private boolean isSafe(int[][] maze, int[][] visited, int i, int j) {
		return (visited[i][j] == 0 && maze[i][j] != 0);
	}

	private boolean isValid(int i, int j) {
		return (i >= 0 && i < M && j >= 0 && j < N);
	}

	// solution 2 - Using BFS.
	/**
	 * is one possible solution for maze routing problems based on Breadth-first search. It always gives an optimal
	 * solution, if one exists, but is slow and requires considerable memory. Below is the complete algorithm –
	 * 
	 * 1. Create an empty queue and enqueue source cell having distance 0 from source (itself) and mark it as visited
	 * 
	 * 2. do till queue is not empty :
	 * 
	 * - Pop front node from the queue
	 * 
	 * - If the popped node is destination node, then return its distance
	 * 
	 * - else for each of 4 adjacent cells of current cell, we enqueue each valid cell into the queue with +1 distance
	 * and mark them as visited
	 * 
	 * 3. If all the nodes in the queue is processed and destination is not reached, then return false
	 * 
	 * @param args
	 */
	public int shortestPathBFS(int[][] maze, int destX, int destY) {

		int minDist = Integer.MAX_VALUE; // longest distance from source to destination
		boolean[][] visited = new boolean[M][N];

		// possible positions
		int[] xPos = { 0, 0, -1, 1 };
		int[] yPos = { 1, -1, 0, 0 };

		Queue<Cell> queue = new LinkedList<Cell>();
		queue.add(new Cell(0, 0, 0)); // source cell

		while (!queue.isEmpty()) {
			Cell cell = queue.poll();

			int x = cell.i;
			int y = cell.j;
			int dist = cell.dist;

			// check if the dest point has been reached
			if (x == destX && y == destY) {
				minDist = dist;
				break;
			}

			// mark this cell as visited
			visited[x][y] = true;

			// now try all 4 possible positions for the current co-ordinate
			for (int i = 0; i < 4; i++) {
				if (isValidMove(maze, visited, x + xPos[i], y + yPos[i])) {
					// mark this new cell as visited and add to queue
					visited[x + xPos[i]][y + yPos[i]] = true;
					queue.add(new Cell(x + xPos[i], y + yPos[i], dist + 1));
				}
			}
		}

		return minDist;
	}

	private boolean isValidMove(int[][] maze, boolean[][] visited, int i, int j) {
		return (i >= 0 && i < M && j >= 0 && j < N && maze[i][j] != 0 && !visited[i][j]);
	}

	private class Cell {

		int i;
		int j;
		int dist;

		public Cell(int x, int y, int d) {
			this.i = x;
			this.j = y;
			this.dist = d;
		}
	}

	public int findLongestPath(int[][] maze, int[][] visited, int i, int j, int destX, int destY, int dist,
			int minDist) {

		// if dest points are reached return the min
		if (i == destX && j == destY)
			return Integer.max(dist, minDist);

		// mark this point as visited
		visited[i][j] = 1;

		if (isValid(i + 1, j) && isSafe(maze, visited, i + 1, j)) {
			minDist = findShortestPath(maze, visited, i + 1, j, destX, destY, dist + 1, minDist);
		}

		if (isValid(i - 1, j) && isSafe(maze, visited, i - 1, j)) {
			minDist = findShortestPath(maze, visited, i - 1, j, destX, destY, dist + 1, minDist);
		}

		if (isValid(i, j + 1) && isSafe(maze, visited, i, j + 1)) {
			minDist = findShortestPath(maze, visited, i, j + 1, destX, destY, dist + 1, minDist);
		}

		if (isValid(i, j - 1) && isSafe(maze, visited, i, j - 1)) {
			minDist = findShortestPath(maze, visited, i, j - 1, destX, destY, dist + 1, minDist);
		}

		// if nothing satisfied then backtrack
		visited[i][j] = 0;

		return minDist;
	}

	public static void main(String[] args) {
		int mat[][] = { { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
				{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 }, { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 }, { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
				{ 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 }, { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 }, };

		ShortestLongestPathInMaze s = new ShortestLongestPathInMaze();
		System.out.println(s.findShortestPath(mat, 7, 5));
		System.out.println(s.shortestPathBFS(mat, 7, 5));
	}
}
