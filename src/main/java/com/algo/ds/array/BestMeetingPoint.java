package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values
 * 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance,
 * where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|. Find the total distance that needs to be traveled to reach
 * this meeting point.
 * 
 * For example, given three people living at (0,0), (0,4), and (2,2):
 * 
 * 1 - 0 - 0 - 0 - 1 | | | | | 0 - 0 - 0 - 0 - 0 | | | | | 0 - 0 - 1 - 0 - 0
 * 
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 *
 * Time complexity O(m*n) Space complexity O(m + n)
 *
 * https://leetcode.com/problems/best-meeting-point/
 * 
 * Category : Hard
 */
public class BestMeetingPoint {

	// First we collect both the row and column coordinates, sort them and select their middle elements. Then we
	// calculate the total distance as the sum of two independent 1D problems.
	// T - O(mn log mn) S - O(m * n)
	public int minTotalDistance(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		List<Integer> rows = new ArrayList<>();
		List<Integer> cols = new ArrayList<>();

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 1) {
					rows.add(row);
					cols.add(col);
				}
			}
		}

		Collections.sort(rows);
		Collections.sort(cols);

		int size = rows.size() / 2;
		int x = rows.get(size);
		int y = cols.get(size);
		int distance = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					distance += Math.abs(x - i) + Math.abs(y - j);
				}
			}
		}

		return distance;
	}

	public static void main(String args[]) {
		BestMeetingPoint bmp = new BestMeetingPoint();
		int[][] grid = { { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
		System.out.print(bmp.minTotalDistance(grid));
	}
}
