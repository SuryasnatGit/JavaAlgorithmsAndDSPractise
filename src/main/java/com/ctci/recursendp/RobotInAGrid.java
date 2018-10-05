package com.ctci.recursendp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can
 * only move in two directions, right and down, but certain cells are "off limits" such that the
 * robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 * 
 * If we picture this grid, the only way to move to spot (r, c) is by moving to one of the adjacent
 * spots: ( r -1, c) or ( r, c -1). So, we need to find a path to either (r - 1, c) or ( r, c - 1) .
 * 
 * 
 * 
 * @author surya
 *
 */
public class RobotInAGrid {

	/**
	 * Recursive approach. complexity = O(2 ^ (r + c)) since each path has r + c steps and there are 2
	 * choices at each step.
	 * 
	 * @param maze
	 * @return
	 */
	public List<Point> getPath(boolean[][] maze) {
		if (maze == null || maze.length == 0)
			return null;
		List<Point> result = new ArrayList<>();
		if (getPath(maze, maze.length - 1, maze[0].length - 1, result))
			return result;
		return null;
	}

	private boolean getPath(boolean[][] maze, int row, int column, List<Point> result) {
		// if out of bounds or not available
		if (row < 0 || column < 0 || !maze[row][column])
			return false;
		boolean isOrigin = row == 0 && column == 0;
		// if there is a path from start to here and my location..
		if (isOrigin || getPath(maze, row, column - 1, result) || getPath(maze, row - 1, column, result)) {
			Point p = new Point(row, column);
			result.add(p);
			return true;
		}
		return false;
	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * Dynamic approach. Complexity - O(r * c) because we hit each cell just once.
	 * 
	 * @param maze
	 * @return
	 */
	public List<Point> getPath_dp(boolean[][] maze) {
		if (maze == null || maze.length == 0)
			return null;
		List<Point> result = new ArrayList<>();
		// to cache the results
		Set<Point> failedPoints = new HashSet<>();
		if (getPath(maze, maze.length - 1, maze[0].length - 1, result, failedPoints))
			return result;
		return null;
	}

	private boolean getPath(boolean[][] maze, int row, int column, List<Point> result, Set<Point> failedPoints) {
		// if out of bounds or not available
		if (row < 0 || column < 0 || !maze[row][column])
			return false;

		Point p = new Point(row, column);
		// if point already exists in hashset, simply return false
		if (failedPoints.contains(p))
			return false;

		boolean isOrigin = row == 0 && column == 0;

		// if there is a path from start to here and my location..
		if (isOrigin || getPath(maze, row, column - 1, result, failedPoints)
				|| getPath(maze, row - 1, column, result, failedPoints)) {
			result.add(p);
			return true;
		}

		// cache the point
		failedPoints.add(p);
		return false;
	}

}
