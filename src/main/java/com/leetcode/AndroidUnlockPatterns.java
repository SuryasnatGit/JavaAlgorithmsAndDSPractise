package com.leetcode;

/**
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock
 * patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys. Rules for a valid
 * pattern: Each pattern must connect at least m keys and at most n keys. All the keys must be distinct. If the line
 * connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously
 * selected in the pattern. No jumps through non selected key is allowed. The order of keys used matters.
 * 
 * 
 * Explanation:<br/>
 * | 1 | 2 | 3 |<br/>
 * | 4 | 5 | 6 |<br/>
 * | 7 | 8 | 9 |<br/>
 * 
 * Invalid move: 4 - 1 - 3 - 6
 * 
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 * 
 * Invalid move: 4 - 1 - 9 - 2
 * 
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 * 
 * Valid move: 2 - 4 - 1 - 3 - 6
 * 
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 * 
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * 
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 * 
 * Example:
 * 
 * Given m = 1, n = 1, return 9.
 * 
 * T - Since there are 9 digits and each DFS invocation can lead up to 8 further invocations (minus the visited numbers
 * and the cross numbers that require a middle number to be visited first), the upper bound can initially seem to be
 * O(9!). Yet, the time complexity is better due to the constraints that cut down the search space significantly - the
 * cross matrix prevents jumps over unvisited keys.
 * 
 * S - O(n) due to the recursive nature of DFS, where n in this context is the maximum depth of the recursive stack,
 * which corresponds to the maximum length of the pattern (up to 9)
 * 
 * Category : Hard
 *
 */
public class AndroidUnlockPatterns {

	// Even better. 1,3,7, 9 are systematic
	public int numberOfPatterns4(int m, int n) {
		int res = 0;
		boolean[] visited = new boolean[10];

		// defining a cross matrix where cross[i][j] represents the middle point (the point that should be previously
		// visited) when drawing a line from dot i to dot j. This matrix is precomputed and hard-coded into the solution
		// to comply with the constraint regarding the line segments.
		int[][] jumps = new int[10][10];

		jumps[1][3] = jumps[3][1] = 2; // From a to b, need to go through c
		jumps[1][7] = jumps[7][1] = 4;
		jumps[1][9] = jumps[9][1] = 5;
		jumps[2][8] = jumps[8][2] = 5;
		jumps[3][7] = jumps[7][3] = 5;
		jumps[3][9] = jumps[9][3] = 6;
		jumps[4][6] = jumps[6][4] = 5;
		jumps[7][9] = jumps[9][7] = 8;

		for (int i = m; i <= n; i++) {
			res += dfs4(1, i - 1, visited, jumps) * 4; // start from either 1, 3, 7, 9. They are symmetric
			res += dfs4(2, i - 1, visited, jumps) * 4; // start from either 2, 4, 6, 8. They are symmetric
			res += dfs4(5, i - 1, visited, jumps);// start from 5
		}

		return res;
	}

	/**
	 * A recursive backtracking function dfs is implemented to explore all unique paths from any start dot.
	 * 
	 * @param cur
	 * @param remain
	 * @param visited
	 * @param jumps
	 * @return
	 */
	int dfs4(int cur, int remain, boolean[] visited, int[][] jumps) {
		if (remain < 0) {
			return 0;
		}

		if (remain == 0) {
			return 1;
		}

		visited[cur] = true;
		int result = 0;
		for (int i = 1; i <= 9; i++) {
			if (visited[i]) {
				continue;
			}

			if (jumps[cur][i] != 0 && !visited[jumps[cur][i]]) {
				continue;
			}

			result += dfs4(i, remain - 1, visited, jumps);
		}
		visited[cur] = false; // back track
		return result;
	}

	public static void main(String[] args) {
		AndroidUnlockPatterns an = new AndroidUnlockPatterns();
		System.out.println(an.numberOfPatterns4(3, 3));
		System.out.println(an.numberOfPatterns4(1, 1));
	}

}
