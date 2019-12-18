package com.algo.backtracking;

import java.util.Stack;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block
 * i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1]. A rat
 * starts from source and has to reach the destination. The rat can move only in two directions:
 * forward and down. In the maze matrix, 0 means the block is a dead end and 1 means the block can
 * be used in the path from source to destination. Note that this is a simple version of the typical
 * Maze problem. For example, a more complex version can be that the rat can move in 4 directions
 * and a more complex version can be with a limited number of moves.
 * 
 * Naive Algorithm The Naive Algorithm is to generate all paths from source to destination and one
 * by one check if the generated path satisfies the constraints.
 * 
 * while there are untried paths { generate the next path if this path has all blocks as 1 { print
 * this path; } }
 * 
 * Backtracking Algorithm
 * 
 * If destination is reached print the solution matrix Else a) Mark current cell in solution matrix
 * as 1. b) Move forward in the horizontal direction and recursively check if this move leads to a
 * solution. c) If the move chosen in the above step doesn't lead to a solution then move down and
 * check if this move leads to a solution. d) If none of the above solutions works then unmark this
 * cell as 0 (BACKTRACK) and return false.
 * 
 * @author surya
 *
 */
public class RatInAMaze {

	private int N = 4;

	public boolean solveMaze(int[][] maze) {
		int[][] sol = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
		if (!solveMazeUtil(maze, sol, 0, 0)) { // starting point for solving the maze(0,0)
			System.out.println("maze cannot be solved");
			return false;
		}
		printSolvedMaze(sol);
		return true;
	}

	// using recursion
	private boolean solveMazeUtil(int[][] maze, int[][] sol, int x, int y) {
		// if(x,y) reaches goal, return true
		if (x == N - 1 && y == N - 1) {
			sol[x][y] = 1;
			return true;
		}

		// check if maze[x][y] is valid
		if (isValid(maze, x, y)) {
			// mark maze[x][y] as part of solution path
			sol[x][y] = 1;

			// check if forward movement of x is possible
			if (solveMazeUtil(maze, sol, x + 1, y))
				return true;

			// check if forward movement of y is possible
			if (solveMazeUtil(maze, sol, x, y + 1))
				return true;

			// if both are not possible then backtrack. i.e unmark x and y as part of solution path.
			sol[x][y] = 0;
			return false;
		}
		return false;
	}

	private boolean isValid(int[][] maze, int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
	}

	private void printSolvedMaze(int[][] sol) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(sol[i][j] + " ");
			}
			System.out.println();
		}
	}

	class Node {
		int i;
		int j;
		int dir;

		public Node(int i, int j) {
			this.i = i;
			this.j = j;
			this.dir = 0; // initially direction set to 0
		}
	}

	// Iterative way using stack

	private boolean[][] visited = new boolean[N][N];

	/**
	 * A node structure is used to store the (i, j) coordinates and directions explored from this node
	 * and which direction to try out next.
	 * 
	 * Structure Used:
	 * 
	 * X : x coordinate of the node Y : y coordinate of the node dir : This variable will be used to
	 * tell which all directions we have tried and which to choose next. We will try all the directions
	 * in anti-clockwise manner starting from Up. Initially it will be assigned 0. If dir=0 try Up
	 * direction. If dir=1 try left direction. If dir=2 try down direction. If dir=3 try right
	 * direction. Else retract back to the previous block of the maze.
	 * 
	 * Initially, we will push a node with indexes i=0, j=0 and dir=0 into the stack. We will move to
	 * all the direction of the topmost node one by one in an anti-clockwise manner and each time as we
	 * try out a new path we will push that node (block of the maze) in the stack. We will increase dir
	 * variable of the topmost node each time so that we can try a new direction each time unless all
	 * the directions are explored ie. dir=4. If dir equals to 4 we will pop that node from the stack
	 * that means we are retracting one step back to the path where we came from.
	 * 
	 * We will also maintain a visited matrix which will maintain which blocks of the maze are already
	 * used in the path or in other words present in the stack. While trying out any direction we will
	 * also check if the block of the maze is not a dead end and is not out of the maze too.
	 * 
	 * We will do this while either the topmost node coordinates become equal to the foods coordinates
	 * that means we have reached the food or the stack becomes empty which means that there is no
	 * possible path to reach the food.
	 * 
	 * @param maze
	 * @param fx
	 * @param fy
	 * @return
	 */
	public boolean isReachable(int[][] maze, int fx, int fy) {
		// initially starting at 0,0
		int i = 0, j = 0;
		Stack<Node> s = new Stack<>();
		Node temp = new Node(i, j);
		s.push(temp);

		while (!s.isEmpty()) {
			temp = s.peek();
			int dir = temp.dir;
			i = temp.i;
			j = temp.j;

			// pop the top node
			s.pop();
			// increment the direction and push node back into stack
			temp.dir++;
			s.push(temp);

			// if we reach the food co-ordinates, return true
			if (i == fx && j == fy)
				return true;

			// Checking the Up direction.
			if (dir == 0) {
				if (i - 1 >= 0 && maze[i - 1][j] > 0 && visited[i - 1][j]) {
					Node temp1 = new Node(i - 1, j);
					visited[i - 1][j] = false;
					s.push(temp1);
				}
			}

			// Checking the left direction
			else if (dir == 1) {
				if (j - 1 >= 0 && maze[i][j - 1] > 0 && visited[i][j - 1]) {
					Node temp1 = new Node(i, j - 1);
					visited[i][j - 1] = false;
					s.push(temp1);
				}
			}

			// Checking the down direction
			else if (dir == 2) {
				if (i + 1 < N && maze[i + 1][j] > 0 && visited[i + 1][j]) {
					Node temp1 = new Node(i + 1, j);
					visited[i + 1][j] = false;
					s.push(temp1);
				}
			}
			// Checking the right direction
			else if (dir == 3) {
				if (j + 1 < N && maze[i][j + 1] > 0 && visited[i][j + 1]) {
					Node temp1 = new Node(i, j + 1);
					visited[i][j + 1] = false;
					s.push(temp1);
				}
			}

			// If none of the direction can take
			// the rat to the Food, retract back
			// to the path where the rat came from.
			else {
				visited[temp.i][temp.j] = true;
				s.pop();
			}
		}

		// If the stack is empty and
		// no path is found return false.
		return false;
	}

	private int[][] dp = new int[4][4];

	/**
	 * Given a maze with obstacles, count number of paths to reach rightmost-bottommost cell from
	 * topmost-leftmost cell. A cell in given maze has value -1 if it is a blockage or dead end, else 0.
	 * 
	 * From a given cell, we are allowed to move to cells (i+1, j) and (i, j+1) only.
	 * 
	 * The idea is to modify the given grid[][] so that grid[i][j] contains count of paths to reach (i,
	 * j) from (0, 0) if (i, j) is not a blockage, else grid[i][j] remains -1.
	 * 
	 * @param matrix
	 * @param i
	 * @param j
	 * @return
	 */
	public int countWays(int[][] matrix, int i, int j) {
		if (i < 0 || j < 0) { // index out of bounds
			return 0;
		}
		if (dp[i][j] != 0) { // if subproblem has been solved, return memoized value
			return dp[i][j];
		}
		if (i == 0 && j == 0) { // base case
			dp[0][0] = matrix[0][0] + 1;
			return dp[0][0];
		}
		if (matrix[i][j] == -1) { // if maze[i,j] == -1, no path to i,j
			dp[i][j] = 0;
			return 0;
		} else { // if maze[i,j] == 0, two possible ways into [i,j], add up the # of paths in each way
			dp[i][j] = countWays(matrix, i - 1, j) + countWays(matrix, i, j - 1);
			return dp[i][j];
		}
	}

	public static void main(String[] args) {
		RatInAMaze r = new RatInAMaze();
		int[][] maze = new int[][] { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
		r.printSolvedMaze(maze);
		System.out.println("---------------");
		System.out.println(r.solveMaze(maze));
	}

}
