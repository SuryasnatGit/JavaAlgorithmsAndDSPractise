package com.algo.ds.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/regions-cut-by-slashes/
 *
 */
public class RegionsCutBySlashes {

	public static void main(String[] args) {
		char[][] matrix = { { '\\', '\\', '/', '/' }, { '/', '/', '/', '\\' }, { '\\', '\\', '\\', '/' },
				{ '\\', '/', '/', '/' } };
		int[] from = { 0, 0 };
		int[] to = { 3, 3 };

		RegionsCutBySlashes sg = new RegionsCutBySlashes();
		sg.prepare();
		boolean res = sg.findPath(matrix, from, to);
		System.out.println(res);
	}

	Map<Character, int[][]> map = new HashMap<Character, int[][]>();
	int m = 0;
	int n = 0;

	public void prepare() {
		int[][] arr1 = { { -1, -1 }, { 1, 1 } };
		int[][] arr2 = { { -1, 1 }, { 1, -1 } };

		map.put('\\', arr1);
		map.put('/', arr2);
	}

	public boolean findPath(char[][] matrix, int[] from, int[] to) {
		m = matrix.length;
		n = matrix[0].length;

		int id1 = from[0] * n + from[1];
		int id2 = to[0] * n + to[1];

		if (id1 == id2) {
			return true;
		}

		Set<Integer> visited = new HashSet<Integer>();
		visited.add(id1);

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(id1);

		while (!queue.isEmpty()) {
			int now = queue.poll();
			int x = now / n;
			int y = now % n;

			int[][] directions = map.get(matrix[x][y]);

			for (int[] dir : directions) {
				int newX = x + dir[0];
				int newY = y + dir[1];
				int newId = newX * n + newY;

				if (newId == id2) {
					return true;
				}

				if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited.contains(newId)) {
					visited.add(newId);
					queue.offer(newId);
				}
			}
		}

		return false;
	}

	/**
	 * Union Find approach.
	 * 
	 * Complexity Analysis
	 * 
	 * Time Complexity: O(N * N * alpha(N)), where N is the length of the grid, and alpha is the Inverse-Ackermann
	 * function (if we were to use union-find by rank.)
	 * 
	 * Space Complexity: O(N * N).
	 * 
	 * @return
	 */
	public int regionsBySlashes(String[] grid) {
		int N = grid.length;
		DSU dsu = new DSU(4 * N * N);
		for (int r = 0; r < N; ++r)
			for (int c = 0; c < N; ++c) {
				int root = 4 * (r * N + c);
				char val = grid[r].charAt(c);
				if (val != '\\') {
					dsu.union(root + 0, root + 1);
					dsu.union(root + 2, root + 3);
				}
				if (val != '/') {
					dsu.union(root + 0, root + 2);
					dsu.union(root + 1, root + 3);
				}

				// north south
				if (r + 1 < N)
					dsu.union(root + 3, (root + 4 * N) + 0);
				if (r - 1 >= 0)
					dsu.union(root + 0, (root - 4 * N) + 3);
				// east west
				if (c + 1 < N)
					dsu.union(root + 2, (root + 4) + 1);
				if (c - 1 >= 0)
					dsu.union(root + 1, (root - 4) + 2);
			}

		int ans = 0;
		for (int x = 0; x < 4 * N * N; ++x) {
			if (dsu.find(x) == x)
				ans++;
		}

		return ans;
	}
}

class DSU {
	int[] parent;

	public DSU(int N) {
		parent = new int[N];
		for (int i = 0; i < N; ++i)
			parent[i] = i;
	}

	public int find(int x) {
		if (parent[x] != x)
			parent[x] = find(parent[x]);
		return parent[x];
	}

	public void union(int x, int y) {
		parent[find(x)] = find(y);
	}
}