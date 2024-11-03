package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * The task is to design an algo to find out the closest driver to a passenger. The idea is that assume a city has a lot
 * of intersections, a passenger can call a car at any intersection. Assuming the distance between each section is 500
 * meters, find the driver closest to the passenger. If there is no driver within 5000 meters, return 0. All classes are
 * defined by themselves.
 * 
 * Refer ShortestPathInUndirectedGraph.java problem
 * 
 * Category : Hard
 * 
 * TODO : to understand and execute
 */
public class ClosestDriver {

	int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	// Matrix[x][y] == 1 means there is a driver. Passenger is the passenger's position
	int findClosestDriver(int[][] matrix, int[] passenger) {
		// Do a BFS to find the closest
		int m = matrix.length;
		int n = matrix[0].length;
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		int start = passenger[0] * n + passenger[1];
		queue.offer(start);
		visited.add(start);

		if (matrix[passenger[0]][passenger[1]] == 1) {
			return -1; // The distance is 0. right there
		}

		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int id = queue.poll();
				int x = id / n;
				int y = id % n;

				for (int[] dir : directions) {
					int newX = x + dir[0];
					int newY = y + dir[1];
					int newId = newX * n + newY;

					if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited.contains(newId)) {
						if (matrix[newX][newY] == 1) {
							return level;
						}

						queue.offer(newId);
						visited.add(newId);
					}
				}
			}

			level++;

			if (level > 10) {
				return 0; // > 5000 m
			}
		}

		return -1;
	}
}
