package com.companyprep.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are in charge of preparing a recently purchased lot for Amazon’s building. The lot is covered with trenches and
 * has a single obstacle that needs to be taken down before the foundation is prepared for the building. The demolition
 * robot must remove the obstacle before the progress can be made on the building. Write an algorithm to determine the
 * minimum distance required for the demolition robot to remove the obstacle.
 * 
 * Assumptions:
 * 
 * The lot is flat, except the trenches and can be represented by a 2D grid.
 * 
 * The demolition robot must start at the top left corner of the lot, which is always flat, and can move on block up,
 * down, right, left
 * 
 * The demolition robot cannot enter trenches and cannot leave the lot.
 * 
 * The flat areas are indicated by 1, areas with trenches are indicated by 0, and the obstacle is indicated by 9
 * 
 * Input: The input of the function has 3 arguments:
 * 
 * numRows – number of rows
 * 
 * numColumns – number of columns
 * 
 * lot – 2d grid of integers
 * 
 * Output: Return an integer that indicated the minimum distance traversed to remove the obstacle else return -1
 * 
 * Constraints: 1<= numRows, numColumns <= 1000
 * 
 * Example: numRows = 3, numColumns = 3 lot = [ [1, 0, 0], [1, 0, 0], [1, 9, 1] ]
 * 
 * Output: 3
 * 
 * Explanation: Starting from the top-left corner, the demolition robot traversed the cells (0,0) -> (1,0)->
 * (2,0)->(2,1). The robot moves 3 times to remove the obstacle “9”
 * 
 * Using BFS.
 *
 */
public class MoveTheObstacle {

	class Cell {
		int x;
		int y;
		int dist;

		public Cell(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	public int minDistCovered(int[][] grid) {
		Queue<Cell> queue = new LinkedList<>();
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		visited[0][0] = true; // start from top left corner
		queue.add(new Cell(0, 0, 0));

		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			visited[cell.x][cell.y] = true;

			if (grid[cell.x][cell.y] == 9) { // found result
				return cell.dist;
			}

			// check top
			if (cell.x - 1 > 0 && grid[cell.x - 1][cell.y] != 0 && !visited[cell.x - 1][cell.y]) {
				queue.add(new Cell(cell.x - 1, cell.y, cell.dist + 1));
			}

			// check bottom
			if (cell.x + 1 < grid.length && grid[cell.x + 1][cell.y] != 0 && !visited[cell.x + 1][cell.y]) {
				queue.add(new Cell(cell.x + 1, cell.y, cell.dist + 1));
			}

			// check left
			if (cell.y - 1 > 0 && grid[cell.x][cell.y - 1] != 0 && !visited[cell.x][cell.y - 1]) {
				queue.add(new Cell(cell.x, cell.y - 1, cell.dist + 1));
			}

			// check right
			if (cell.y + 1 < grid[0].length && grid[cell.x][cell.y + 1] != 0 && !visited[cell.x][cell.y + 1]) {
				queue.add(new Cell(cell.x, cell.y + 1, cell.dist + 1));
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		MoveTheObstacle m = new MoveTheObstacle();
		int[][] grid = new int[][] { { 1, 0, 0 }, { 1, 9, 0 }, { 1, 0, 1 } };
		System.out.println(m.minDistCovered(grid));
	}
}
