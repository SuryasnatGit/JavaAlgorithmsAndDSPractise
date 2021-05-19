package com.leetcode;

import java.util.PriorityQueue;

/**
 *
 * Category : Hard
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		TrappingRainWater trap = new TrappingRainWater();
		System.out.println(trap.trapRainWater(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
		System.out.println(trap.trapRainWaterIn2DMap(
				new int[][] { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } }));
	}

	/**
	 * Problem 1 - Given n non-negative integers representing an elevation map where the width of each bar is 1, compute
	 * how much water it is able to trap after raining.
	 * 
	 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 * 
	 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
	 * (blue section) are being trapped.
	 * 
	 */
	// Two pointers
	public int trapRainWater(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int left = 0, right = height.length - 1;
		int vol = 0;
		int smaller;

		while (left < right) {
			if (height[left] < height[right]) { // As long as there is support on both sides
				smaller = height[left];
				while (left < right && height[left] <= smaller) { // Move one by one
					vol += smaller - height[left];
					left++;
				}
			} else {
				smaller = height[right];
				while (left < right && height[right] <= smaller) { // Move one by one
					vol += smaller - height[right];
					right--;
				}
			}
		}

		return vol;
	}

	/**
	 * Problem 2 : Given an m x n matrix of positive integers representing the height of each unit cell in a 2D
	 * elevation map, compute the volume of water it is able to trap after raining.
	 * 
	 * Note: Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
	 * 
	 * Example:
	 * 
	 * Given the following 3x6 height map: [ [1,4,3,1,3,2], [3,2,1,3,2,4], [2,3,3,2,3,1] ]
	 * 
	 * Return 4.
	 * 
	 * TODO: understand clearly
	 */
	public int trapRainWaterIn2DMap(int[][] heightMap) {
		int rows = heightMap.length;
		int cols = heightMap[0].length;

		PriorityQueue<Cell> heap = new PriorityQueue<Cell>();
		boolean[][] visited = new boolean[rows][cols];

		// Left and Right
		for (int i = 0; i < rows; i++) {
			visited[i][0] = true;
			visited[i][cols - 1] = true;
			heap.offer(new Cell(i, 0, heightMap[i][0]));
			heap.offer(new Cell(i, cols - 1, heightMap[i][cols - 1]));
		}

		// Top and Bottom
		for (int i = 0; i < cols; i++) {
			visited[0][i] = true;
			visited[rows - 1][i] = true;
			heap.offer(new Cell(0, i, heightMap[0][i]));
			heap.offer(new Cell(rows - 1, i, heightMap[rows - 1][i]));
		}

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		int sum = 0;
		while (!heap.isEmpty()) {
			Cell now = heap.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];

				if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && !visited[nextX][nextY]) {
					visited[nextX][nextY] = true;
					sum += Math.max(0, now.h - heightMap[nextX][nextY]);
					heap.offer(new Cell(nextX, nextY, Math.max(now.h, heightMap[nextX][nextY])));
				}
			}
		}

		return sum;
	}

	class Cell implements Comparable<Cell> {
		int x, y, h;

		Cell(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}

		public int compareTo(Cell another) {
			return this.h - another.h;
		}
	}
}
