package com.companyprep.amazon;

import java.util.PriorityQueue;

/**
 * Given a two 2D integer array, find the max score of a path from the upper left cell to bottom right cell. The score
 * of a path is the minimum value in that path.
 * 
 * Notice: the path can only right and down.
 * 
 * For example:
 * 
 * Input:
 * 
 * [7,5,3] [2,0,9] [4,5,9]
 * 
 * Here are some paths from [0,0] to [2,2] and the minimum value on each path:
 * 
 * path: 7->2->4->5->9, minimum value on this path: 2
 * 
 * path: 7->2->0->9->9, minimum value on this path: 0
 * 
 * path: 7->2->0->5->9, minimum value on this path: 0
 * 
 * In the end the max score(the min value) of all the paths is 3.
 * 
 * Output: 3
 *
 * TODO: to complete
 */
public class MaximalMinimumValuePath {

	/**
	 * Time: O(RClog(RC)) Space: O(RC)
	 * 
	 * @param A
	 * @return
	 */
	public int maximumMinimumPath(int[][] A) {
		if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
			return 0;
		}
		int row = A.length, col = A[0].length;
		// Comparator
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> { // max heap
			return A[o2[0]][o2[1]] - A[o1[0]][o1[1]];
		});
		// Init
		int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		boolean[][] visited = new boolean[row][col];
		int score = Integer.MAX_VALUE; // must be updated if A is not empty
		pq.add(new int[] { 0, 0 });
		// PQ
		while (pq.size() > 0) {
			int[] p = pq.remove();
			visited[p[0]][p[1]] = true;
			score = Math.min(score, A[p[0]][p[1]]);
			// check if reach the end point
			if (p[0] == row - 1 && p[1] == col - 1) {
				break;
			}
			// 4 directions
			for (int[] d : dir) {
				int r = p[0] + d[0];
				int c = p[1] + d[1];
				if (r >= 0 && r <= row - 1 && c >= 0 && c <= col - 1 && !visited[r][c]) {
					pq.add(new int[] { r, c });
				}
			}
		}
		return score;
	}

	// Problem 2
	/**
	 * Given a two 2D integer array, find the max score of a path from the upper left cell to bottom right cell that
	 * doesn't visit any of the cells twice. The score of a path is the minimum value in that path.
	 * 
	 * For example:
	 * 
	 * Input:
	 * 
	 * [7,5,3] [2,0,9] [4,5,9]
	 * 
	 * Here are some paths from [0,0] to [2,2] and the minimum value on each path:
	 * 
	 * path: 7->2->4->5->9, minimum value on this path: 2
	 * 
	 * path: 7->2->0->9->9, minimum value on this path: 0
	 * 
	 * path: 7->2->0->5->9, minimum value on this path: 0
	 * 
	 * Notice: the path can move all four directions. (not only right and down. ALL FOUR DIRECTIONS)
	 * 
	 * Here 7->2->0->5->3->9->9 is also a path, and the minimum value on this path is 0.
	 * 
	 * The path doesn't visit the same cell twice. So 7->2->0->5->3->9->0->5->9 is not a path.
	 * 
	 * In the end the max score(the min value) of all the paths is 3.
	 * 
	 * Output: 3
	 * 
	 * 
	 */
}
