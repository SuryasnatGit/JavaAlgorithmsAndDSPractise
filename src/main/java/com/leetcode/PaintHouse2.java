package com.leetcode;

/**
 * 265. There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house
 * with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same
 * color.
 * 
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Find the minimum cost to paint all houses.
 * 
 * Note: All costs are positive integers.
 * 
 * Follow up: Could you solve it in O(nk) runtime?
 * 
 * 好题
 */
public class PaintHouse2 {
	public int minCostII(int[][] costs) {
		if (costs == null || costs.length == 0) {
			return 0;
		}

		int m = costs.length;
		int n = costs[0].length;

		int[][] hash = new int[m][n];

		int min1 = -1, min2 = -1;

		for (int i = 0; i < m; i++) { // house
			int last1 = min1, last2 = min2;
			min1 = -1; // First time to enter this house
			min2 = -1; // Above are all at the house level. So need to initiate again here

			for (int j = 0; j < n; j++) { // color
				if (j != last1) {
					hash[i][j] = costs[i][j] + ((last1 == -1) ? 0 : hash[i - 1][last1]); // last1 == -1 可以 换成 i == 0
				} else {
					hash[i][j] = costs[i][j] + ((last2 == -1) ? 0 : hash[i - 1][last2]);
				}

				if (min1 < 0 || hash[i][j] < hash[i][min1]) {
					min2 = min1;
					min1 = j;
				} else if (min2 < 0 || hash[i][j] < hash[i][min2]) {
					min2 = j;
				}
			}
		}

		return hash[m - 1][min1];
	}
}
