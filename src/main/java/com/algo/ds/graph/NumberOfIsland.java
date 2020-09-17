package com.algo.ds.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/find-number-of-islands/.
 * 
 * Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island. For example, the
 * below matrix contains 5 islands.
 * 
 * Input : mat[][] = {{1, 1, 0, 0, 0}, {0, 1, 0, 0, 1}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1} Output : 5.
 * 
 * This is a variation of the standard problem: Counting the number of connected components in an undirected graph. The
 * problem can be easily solved by applying DFS() on each component. In each DFS() call, a component or a sub-graph is
 * visited. We will call DFS on the next un-visited component. The number of calls to DFS() gives the number of
 * connected components. BFS can also be used.
 * 
 * Category : Hard
 */
public class NumberOfIsland {

	/**
	 * Solution 1 - Using DFS. Time complexity: O(ROW x COL)
	 * 
	 * @param graph
	 * @return
	 */
	public int numberOfIslandDFS(int[][] graph) {
		if (graph == null || graph.length == 0) {
			return 0;
		}

		int count = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (graph[i][j] == 1) {
					count++;
					DFS(graph, i, j);
				}
			}
		}
		return count;
	}

	private void DFS(int[][] graph, int i, int j) {
		if (i < 0 || j < 0 || i == graph.length || j == graph[i].length || graph[i][j] == 0) {
			return;
		}
		graph[i][j] = 0;

		DFS(graph, i, j + 1);
		DFS(graph, i + 1, j);
		DFS(graph, i, j - 1);
		DFS(graph, i - 1, j);
	}

	/**
	 * Solution 2 - Using BFS
	 *
	 */
	public void numberOfIslandsBFS(int[][] mat, boolean[][] processed, int i, int j) {

		int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
		int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };

		// create an empty queue and enqueue source node
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(i, j));

		// mark source node as processed
		processed[i][j] = true;

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue and process it
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();

			// check for all 8 possible movements from current cell
			// and enqueue each valid movement
			for (int k = 0; k < 8; k++) {
				// Skip if location is invalid or already processed
				// or has water
				if (isSafe(mat, x + row[k], y + col[k], processed)) {
					// skip if location is invalid or it is already
					// processed or consists of water
					processed[x + row[k]][y + col[k]] = true;
					q.add(new Pair(x + row[k], y + col[k]));
				}
			}
		}
	}

	/**
	 * Function to check if it is safe to go to position (x, y) from current position. The function returns false if (x,
	 * y) is not valid matrix coordinates or (x, y) represents water or position (x, y) is already processed
	 */
	private boolean isSafe(int[][] mat, int x, int y, boolean[][] processed) {
		return (x >= 0) && (x < processed.length) && (y >= 0) && (y < processed[0].length)
				&& (mat[x][y] == 1 && !processed[x][y]);
	}

	/**
	 * Solution 3 - Using disjoint sets
	 * 
	 * @param args
	 */
	public int numberOfIslandsDisjointSets(int a[][]) {
		int n = a.length;
		int m = a[0].length;

		DisjointUnionSets dus = new NumberOfIsland().new DisjointUnionSets(n * m);

		/*
		 * The following loop checks for its neighbours and unites the indexes if both are 1.
		 */
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				// If cell is 0, nothing to do
				if (a[j][k] == 0)
					continue;

				// Check all 8 neighbours and do a union
				// with neighbour's set if neighbour is
				// also 1
				if (j + 1 < n && a[j + 1][k] == 1)
					dus.union(j * (m) + k, (j + 1) * (m) + k);
				if (j - 1 >= 0 && a[j - 1][k] == 1)
					dus.union(j * (m) + k, (j - 1) * (m) + k);
				if (k + 1 < m && a[j][k + 1] == 1)
					dus.union(j * (m) + k, (j) * (m) + k + 1);
				if (k - 1 >= 0 && a[j][k - 1] == 1)
					dus.union(j * (m) + k, (j) * (m) + k - 1);
				if (j + 1 < n && k + 1 < m && a[j + 1][k + 1] == 1)
					dus.union(j * (m) + k, (j + 1) * (m) + k + 1);
				if (j + 1 < n && k - 1 >= 0 && a[j + 1][k - 1] == 1)
					dus.union(j * m + k, (j + 1) * (m) + k - 1);
				if (j - 1 >= 0 && k + 1 < m && a[j - 1][k + 1] == 1)
					dus.union(j * m + k, (j - 1) * m + k + 1);
				if (j - 1 >= 0 && k - 1 >= 0 && a[j - 1][k - 1] == 1)
					dus.union(j * m + k, (j - 1) * m + k - 1);
			}
		}

		// Array to note down frequency of each set
		int[] c = new int[n * m];
		int numberOfIslands = 0;
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				if (a[j][k] == 1) {

					int x = dus.find(j * m + k);

					// If frequency of set is 0,
					// increment numberOfIslands
					if (c[x] == 0) {
						numberOfIslands++;
						c[x]++;
					}

					else
						c[x]++;
				}
			}
		}
		return numberOfIslands;
	}

	// Class to represent Disjoint Set Data structure
	class DisjointUnionSets {
		int[] rank, parent;
		int n;

		public DisjointUnionSets(int n) {
			rank = new int[n];
			parent = new int[n];
			this.n = n;
			makeSet();
		}

		void makeSet() {
			// Initially, all elements are in their
			// own set.
			for (int i = 0; i < n; i++)
				parent[i] = i;
		}

		// Finds the representative of the set that x
		// is an element of
		int find(int x) {
			if (parent[x] != x) {
				// if x is not the parent of itself,
				// then x is not the representative of
				// its set.
				// so we recursively call Find on its parent
				// and move i's node directly under the
				// representative of this set
				return find(parent[x]);
			}

			return x;
		}

		// Unites the set that includes x and the set
		// that includes y
		void union(int x, int y) {
			// Find the representatives (or the root nodes)
			// for x an y
			int xRoot = find(x);
			int yRoot = find(y);

			// Elements are in the same set, no need
			// to unite anything.
			if (xRoot == yRoot)
				return;

			// If x's rank is less than y's rank
			// Then move x under y so that depth of tree
			// remains less
			if (rank[xRoot] < rank[yRoot])
				parent[xRoot] = yRoot;

			// Else if y's rank is less than x's rank
			// Then move y under x so that depth of tree
			// remains less
			else if (rank[yRoot] < rank[xRoot])
				parent[yRoot] = xRoot;

			else // Else if their ranks are the same
			{
				// Then move y under x (doesn't matter
				// which one goes where)
				parent[yRoot] = xRoot;

				// And increment the the result tree's
				// rank by 1
				rank[xRoot] = rank[xRoot] + 1;
			}
		}
	}

	public static void main(String args[]) {

		int matrix[][] = { { 1, 1, 0, 1, 0 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 0 } };
		NumberOfIsland island = new NumberOfIsland();
		int count = island.numberOfIslandDFS(matrix);
		System.out.println(count);
	}
}

class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
