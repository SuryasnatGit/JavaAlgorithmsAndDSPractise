package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Category : Medium
 */
public class UniquePaths {

	/*
	 * Problem 1 - A robot is located at the top-left corner of a m ×n grid (marked ‘Start’ in the diagram below). The
	 * robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
	 * corner of the grid (marked ‘Finish’ in the diagram below). How many possible unique paths are there?
	 */
	/**
	 * Approach 1: not optimal solution, time complexity is exponential.
	 * 
	 * Explanation : That means that the total number of paths to move into (m, n) cell is uniquePaths(m - 1, n) +
	 * uniquePaths(m, n - 1).
	 */
	public int uniquePaths(int m, int n) {
		if (m == 1 || n == 1) {
			return 1;
		}

		return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
	}

	/**
	 * Approach 2 - using memoization. time complexity - O(mn) space complexity - O(mn)
	 * 
	 */
	public int uniquePathsDP(int m, int n) {
		int[][] hash = new int[m][n];

		// prefill the hash
		for (int[] row : hash) {
			Arrays.fill(row, 1);
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				hash[i][j] = hash[i][j - 1] + hash[i - 1][j];
			}
		}

		return hash[m - 1][n - 1];
	}

	/**
	 * Similar to Unique Paths, but now consider if some obstacles are added to the grids. How many unique paths would
	 * there be? An obstacle and empty space are marked as 1 and 0 respectively in the grid.
	 * 
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		// if the (0,0) cell is 1 then robot can't start, so return 0
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}

		int[][] hash = new int[m][n];

		// iterate 1st column and if the current cell is an obstacle then it shouldn't contribute to any path. Hence set
		// the value of that cell to 0 and subsequent cells on the same column as well
		boolean leftBlock = false;
		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				leftBlock = true;
			}
			hash[i][0] = leftBlock ? 0 : 1;
		}

		// iterate 1st row and if the current cell is an obstacle then it shouldn't contribute to any path. Hence set
		// the value of that cell to 0 and subsequent cells on the same row as well
		boolean topBlock = false;
		for (int i = 0; i < n; i++) {
			if (obstacleGrid[0][i] == 1) {
				topBlock = true;
			}
			hash[0][i] = topBlock ? 0 : 1;
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 1) { // if cell contains obstacle set to 0 and continue
					hash[i][j] = 0;
				} else {
					hash[i][j] = hash[i - 1][j] + hash[i][j - 1];
				}
			}
		}

		return hash[m - 1][n - 1];
	}

	/**
	 * https://leetcode.com/problems/unique-paths-iii/
	 * 
	 * @param grid
	 * @return
	 */
	int ans;
	int[][] grid;
	int R, C;
	int tr, tc, target;
	int[] dr = new int[] { 0, -1, 0, 1 };
	int[] dc = new int[] { 1, 0, -1, 0 };
	Integer[][][] memo;

	public int uniquePathsIII(int[][] grid) {
		this.grid = grid;
		R = grid.length;
		C = grid[0].length;
		target = 0;

		int sr = 0, sc = 0;
		for (int r = 0; r < R; ++r)
			for (int c = 0; c < C; ++c) {
				if (grid[r][c] % 2 == 0)
					target |= code(r, c);

				if (grid[r][c] == 1) {
					sr = r;
					sc = c;
				} else if (grid[r][c] == 2) {
					tr = r;
					tc = c;
				}
			}

		memo = new Integer[R][C][1 << R * C];
		return dp(sr, sc, target);
	}

	public int code(int r, int c) {
		return 1 << (r * C + c);
	}

	public Integer dp(int r, int c, int todo) {
		if (memo[r][c][todo] != null)
			return memo[r][c][todo];

		if (r == tr && c == tc) {
			return todo == 0 ? 1 : 0;
		}

		int ans = 0;
		for (int k = 0; k < 4; ++k) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (0 <= nr && nr < R && 0 <= nc && nc < C) {
				if ((todo & code(nr, nc)) != 0)
					ans += dp(nr, nc, todo ^ code(nr, nc));
			}
		}
		memo[r][c][todo] = ans;
		return ans;
	}

	/**
	 * TODO : understand clearly
	 * 
	 * Unique Path II, the problem of bug free, was quickly solved. The second question is the first question follow up.
	 * There are 2 robots, one on the upper left (A) and one on the lower right (B). Two robots move at the same time, A
	 * can only move Down, right. B can only move Up, left. Write an equation and ask whether A and B can reach the end
	 * at the same time (ie. A to the lower right, B to the upper left)
	 * 
	 * Note<br/>
	 * 1. There are obstacles<br/>
	 * 2. A and B cannot collide.
	 *
	 * If a and b can only be one down right and one up left, if both can be reached, they must be at the same time,
	 * because they both take m + n-2 steps. If there is only one path from a to b, then they must meet and there is no
	 * cure. If there are two or more, if they intersect, they must only intersect at one point. Because of their moving
	 * direction, they do not intersect at the midpoint of the length (m+n+2)/2.
	 *
	 * 1) If it collides, the midpoint must collide; (so m+n% 2 == 0, return true) 2) If there is no collision, (can it
	 * be reached at the same time) == (can both a and b be reached), if both can be reached: 3) Take (m+n-2)/2 steps
	 * with a BFS, and mark these points as obstacles, but not 1, but 2. 4) Use b to go without passing 2 or (passing
	 * 2&& more than one 2), TRUE;
	 * 
	 * "Disjoint at the midpoint of the length" is amazing~ The judgment only needs to satisfy: 1. There is a feasible
	 * path 2. If it is possible to collide at a certain point, there are at least two different midpoint positions in
	 * the feasible path
	 * 
	 * My thoughts: <br/>
	 * 1. A path for A is also a path for B, just reverse it. <br/>
	 * 2. If there is only 1 path available, then definitely it will collide. <br/>
	 * 3. If there are more than 1 paths, need to check if there are 2 paths which don't meet in the middle step, (m +
	 * n-2) / 2; <br/>
	 * 4. If you want to remember the path, use a Map<Integer, Integer> for backtracking, but it is not necessary in our
	 * case. BFS takes (m+n-2)/2 steps and marks these points as obstacles, but not 1, but 2
	 * 
	 */
	int[][] downRight = { { 1, 0 }, { 0, 1 } };
	int[][] upLeft = { { -1, 0 }, { 0, -1 } };
	int m = 0;
	int n = 0;

	public int uniquePath2Robots(int[][] M) {
		m = M.length;
		n = M[0].length;
		List<Integer> meetPoints = new ArrayList<Integer>();
		// Move Robot A
		bfs(M, 0, 0, downRight, meetPoints);
	}

	private void bfs(int[][] M, int i, int j, int[][] direction, List<Integer> meetPoints) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();

		int id = i * n + j;
		queue.offer(id);
		visited.add(id);
		int step = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int a = 0; a < size; a++) {
				int now = queue.poll();
				int x1 = now / n;
				int y1 = now % n;
				int id1 = x1 * n + y1;

				if (step == (m + n - 2) / 2) { // Meet point
					// Not all meet points can reach the end

				}
			}
		}
	}

	/**
	 * TODO : understand clearly
	 * 
	 * This is a good one The problem is that a robot is in a space and does not know its size, shape, or initial
	 * position. There is an obstacle in the space, there is a move function, if there is an obstacle in front, it will
	 * return false, not return true. Find the area of ​​all reachable positions. Note: move is true move, even
	 * obstacles will move there, but it will return false. Follow up is how to reduce the number of call move on the
	 * basis of dfs
	 *
	 * My thoughts: Need to have a visited[] to avoid revisit empty space Need to have a obstacle[] to remember known
	 * obstacles. Only if move() to that position, can we find out if it is obstacle. So, important to remember We dont
	 * know the start point, but there is one. We will treat that as (0,0), move from there. ID is based on the start
	 * point and distance. ID is a string now Use DFS is easier encode() and moveBack() are helpful sub-methods There is
	 * only 1 move(UP, DOWN, LEFT, RIGHT), no int[][] dashboard for us. We dont know the borders, but move() can take us
	 * to 4 directions
	 */
	int getArea(int[] start) {
		Set<String> visited = new HashSet<String>();
		Set<String> obstacles = new HashSet<String>();

		visited.add(encode(start[0], start[1]));
		dfs(visited, obstacles, start[0], start[1]);
		return visited.size();
	}

	int[][] directions = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	private void dfs(Set<String> visited, Set<String> obstacles, int i, int j) {
		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			String id = encode(x, y);

			if (visited.contains(id) || obstacles.contains(id)) {
				continue; // Don't repeat the same mistakes
			}

			if (move(x, y)) { // [x, y] is available
				visited.add(id);
				dfs(visited, obstacles, x, y); // Go deeper
			} else {
				obstacles.add(id);
			}

			// No matter success or failure, move back. If you follow the traditional thinking and go on without going
			// back, if you reach a dead end, this dfs will be over. No, This question has to go back to the starting
			// point, continue searching
			moveBack(dir);
		}
	}

	String encode(int i, int j) {
		return i + "," + j;
	}

	void moveBack(int[] dir) {
		move(-dir[0], -dir[1]);
	}

	public static void main(String[] args) {
		UniquePaths up = new UniquePaths();
		int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		System.out.println(up.uniquePathsWithObstacles(obstacleGrid));

		System.out.println(up.uniquePaths(3, 2));
		System.out.println(up.uniquePaths(7, 3));

		System.out.println(up.uniquePathsDP(3, 2));
		System.out.println(up.uniquePathsDP(7, 3));
	}
}
