package com.companyprep.amazon;

import java.util.List;

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
 * TODO: to complete
 *
 */
public class ZombieMatrix {

	public int minHours(int rows, int columns, List<List<Integer>> grid) {
		// todo
	}

}
