package com.companyprep.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous
 * reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out
 * a shortest route to the treasure island.
 * 
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from the
 * top-left corner of the map and can move one block up, down, left or right at a time. The treasure island is marked as
 * ‘X’ in a block of the matrix. ‘X’ will not be at the top-left corner. Any block with dangerous rocks or reefs
 * will be marked as ‘D’. You must not enter dangerous blocks. You cannot leave the map area. Other areas ‘O’
 * are safe to sail in. The top-left corner is always safe. Output the minimum number of steps to get to the treasure.
 * from aonecode.com
 * 
 * Example:
 * 
 * Input: [['O', 'O', 'O', 'O'], ['D', 'O', 'D', 'O'], ['O', 'O', 'O', 'O'], ['X', 'D', 'D', 'O']]
 * 
 * Output: 5 Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 *
 */
public class TreasureIsland {

	/**
	 * Using BFS approach. time complexity - O(r * c) space complexity - O(r * c) where r = num of rows, c = num of
	 * columns
	 * 
	 * @param grid
	 * @return
	 */
	public int minSteps(char[][] grid) {

		// to check if any cell has been visited
		boolean[][] visited = new boolean[grid.length][grid[0].length];

		Queue<Coordinates> queue = new LinkedList<>();
		queue.add(new Coordinates(0, 0)); // start from the top left

		int[][] directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // horizontal and vertical move
																						// directions
		int steps = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Coordinates coordinate = queue.poll();
				int x = coordinate.x;
				int y = coordinate.y;
				if (grid[x][y] == 'X')
					return steps;

				for (int[] direction : directions) {
					int nextx = coordinate.x + direction[0];
					int nexty = coordinate.y + direction[1];
					// trace the next one
					if (nextx >= 0 && nextx < grid.length && nexty >= 0 && nexty < grid[0].length
							&& !visited[nextx][nexty] && grid[nextx][nexty] != 'D') {
						queue.add(new Coordinates(nextx, nexty));
						visited[nextx][nexty] = true;
					}
				}
			}
			steps++;
		}

		return 0;
	}

	class Coordinates {
		int x;
		int y;

		public Coordinates(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		TreasureIsland ti = new TreasureIsland();
		char[][] grid = new char[][] { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
				{ 'X', 'D', 'D', 'O' } };
		int minSteps = ti.minSteps(grid);
		System.out.println(minSteps);
	}

}
