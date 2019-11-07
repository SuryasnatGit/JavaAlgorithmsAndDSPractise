package com.algo.backtracking;

import java.util.List;

/**
 */
public class PathFromSourceToDest {

	// Problem 1 - find path from source to destination in a matrix that satisfies given constraints.
	/**
	 * We can move exactly k steps from any cell in the matrix where k is the value of that cell. i.e. from any cell
	 * M[i][j] in the matrix M, we can move to location.
	 * 
	 * https://www.techiedelight.com/find-path-source-destination-matrix-satisfies-given-constraints/
	 *
	 */
	// Object of Node class stores cell coordinates of the matrix
	class Node {
		int first, second;

		public Node(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public boolean equals(Object ob) {
			Node node = (Node) ob;
			return (first == node.first && second == node.second);
		}

		@Override
		public int hashCode() {
			return 31 * first + second;
		}
	}

	// N x N matrix
	private static final int N = 10;

	// Below arrays details all 4 possible movements from a cell
	private static final int row[] = { -1, 0, 0, 1 };
	private static final int col[] = { 0, -1, 1, 0 };

	// Function to check if it is possible to go to position pt
	// from current position. The function returns false if pt is
	// not a valid position or it is already visited
	private boolean isValid(List<Node> path, Node pt) {
		return pt.first >= 0 && pt.first < N && pt.second >= 0 && pt.second < N && !path.contains(pt);
	}

	// Function to print the complete path from source to destination
	private void printPath(List<Node> path) {
		for (Node cell : path) {
			System.out.print("(" + cell.first + ", " + cell.second + ") ");
		}
	}

	// Find route in a matrix mat from source cell (0, 0) to
	// destination cell (N-1, N-1)
	public boolean findPath(int mat[][], List<Node> path, Node curr) {
		// include current vertex in the path
		path.add(curr);

		// if destination is found, return true
		if (curr.first == N - 1 && curr.second == N - 1)
			return true;

		// value of current cell
		int n = mat[curr.first][curr.second];

		// check all 4 possible movements from current cell
		// and recur for each valid movement
		for (int i = 0; i < 4; i++) {
			// get next position using value of current cell
			int x = curr.first + row[i] * n;
			int y = curr.second + col[i] * n;

			Node next = new Node(x, y);

			// check if it is possible to go to position (x, y)
			// from current position
			if (isValid(path, next) && findPath(mat, path, next))
				return true;
		}

		// Backtrack - exclude current vertex in the path
		path.remove(path.size() - 1);
		return false;
	}

	// Problem 2 - https://www.techiedelight.com/find-total-number-unique-paths-maze-source-destination/
	/**
	 * Positions in the maze will either be open or blocked with an obstacle. The robot can only move to positions
	 * without obstacles i.e. solution should find paths which contain only cells which are open. Retracing the 1 or
	 * more cells back and forth is not considered a new path. The set of all cells covered in a single path should be
	 * unique from other paths. At any given moment, the robot can only move 1 step in one of 4 directions. Valid moves
	 * are:
	 * 
	 * Go North: (x, y) –> (x – 1, y)
	 * 
	 * Go West: (x, y) –> (x, y – 1)
	 * 
	 * Go South: (x, y) –> (x + 1, y)
	 * 
	 * Go East: (x, y) –> (x, y + 1)
	 */

	private static final int N1 = 4;

	// Check if cell (x, y) is valid or not
	private static boolean isValidCell(int x, int y) {
		if (x < 0 || y < 0 || x >= N1 || y >= N1)
			return false;

		return true;
	}

	public int countPaths(int maze[][], int x, int y, boolean visited[][], int count) {
		// if destination (bottom-rightmost cell) is found,
		// increment the path count
		if (x == N1 - 1 && y == N1 - 1) {
			count++;
			return count;
		}

		// mark current cell as visited
		visited[x][y] = true;

		// if current cell is a valid and open cell
		if (isValidCell(x, y) && maze[x][y] == 1) {
			// go down (x, y) --> (x + 1, y)
			if (x + 1 < N1 && !visited[x + 1][y])
				count = countPaths(maze, x + 1, y, visited, count);

			// go up (x, y) --> (x - 1, y)
			if (x - 1 >= 0 && !visited[x - 1][y])
				count = countPaths(maze, x - 1, y, visited, count);

			// go right (x, y) --> (x, y + 1)
			if (y + 1 < N1 && !visited[x][y + 1])
				count = countPaths(maze, x, y + 1, visited, count);

			// go left (x, y) --> (x, y - 1)
			if (y - 1 >= 0 && !visited[x][y - 1])
				count = countPaths(maze, x, y - 1, visited, count);
		}

		// backtrack from current cell and remove it from current path
		visited[x][y] = false;

		return count;
	}

}
