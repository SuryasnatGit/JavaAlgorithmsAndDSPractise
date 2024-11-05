package com.companyprep;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D grid, each cell is either a zombie or a human. Zombies can turn adjacent (up/down/left/right) human beings
 * into zombies every day. Find out how many days does it take to infect all humans?
 * 
 * Input: matrix, a 2D integer array where a[i][j] = 1 represents a zombie on the cell and a[i][j] = 0 represents a
 * human on the cell.
 * 
 * Output: Return an integer represent how many days does it take to infect all humans. Return -1 if no zombie exists.
 * 
 * Example : Input: [[0, 1, 1, 0, 1], [0, 1, 0, 1, 0], [0, 0, 0, 0, 1], [0, 1, 0, 0, 0]]
 * 
 * Output: 2
 * 
 * Explanation: At the end of the day 1, the status of the grid: [[1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [0, 1, 0, 1, 1], [1,
 * 1, 1, 0, 1]]
 * 
 * At the end of the day 2, the status of the grid: [[1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1]]
 * 
 * Solve the problem:
 * 
 * BFS approach
 * 
 * Category : Medium
 *
 */
public class ZombieMatrix {

	public int numDaysToAffectAllHumans(int[][] grid) {

		int count = 0, result = 0;
		int target = grid.length * grid[0].length;

		// calculate the count of zombies and add their position to the queue
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					q.add(new int[] { i, j });
					count++;
				}
			}
		}

		int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		while (!q.isEmpty()) {
			if (count == target) {
				return result;
			}

			int size = q.size();
			for (int i = 0; i < size; i++) { // consume all elements from queue i.e all zombies movement in 1 day
				int[] pos = q.poll();
				for (int[] dir : direction) {
					int newx = pos[0] + dir[0];
					int newy = pos[1] + dir[1];
					// if new pos is within bounds and is human
					if (isSafe(grid, newx, newy) && grid[newx][newy] == 0) {
						count++;
						grid[newx][newy] = 1; // make human to zombie
						q.add(new int[] { newx, newy });
					}
				}
			}
			System.out.println("Grid after day " + result + "\n");
			printGrid(grid);

			result++;
		}

		return -1;
	}

	private boolean isSafe(int[][] grid, int newx, int newy) {
		return newx >= 0 && newx < grid.length && newy >= 0 && newy < grid[0].length;
	}

	private void printGrid(int[][] grid) {
		Arrays.stream(grid).forEach(a -> System.out.println(Arrays.toString(a)));
	}

	public static void main(String[] args) {
		ZombieMatrix zm = new ZombieMatrix();
		int[][] grid = new int[][] { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };
		System.out.println(zm.numDaysToAffectAllHumans(grid));
	}

}
