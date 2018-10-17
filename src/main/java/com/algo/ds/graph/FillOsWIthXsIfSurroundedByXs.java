

package com.algo.ds.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'. A region is captured by flipping all
 * 'O's into 'X's in that surrounded region. 
 * 
 * Reference https://leetcode.com/problems/surrounded-regions/.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.

This problem is similar to Number of Islands. In this problem, only the cells on the boarders can not be surrounded. 
So we can first merge those O's on the boarders like in Number of Islands and replace O's with '#', and then scan the board 
and replace all O's left (if any).

 */
public class FillOsWIthXsIfSurroundedByXs {

    /**
     * This solution causes java.lang.StackOverflowError, because for a large board, too many method calls are pushed to the
     * stack and causes the overflow.
     * 
     * @param board
     */
    public void solve_dfs(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }

        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        if (board[i][j] != 'O') {
            return;
        }

        board[i][j] = '1';
        if (i < board.length - 2) {
            dfs(board, i + 1, j);
        }
        if (i > 1) {
            dfs(board, i - 1, j);
        }
        if (j < board[0].length - 2) {
            dfs(board, i, j + 1);
        }
        if (j > 1) {
            dfs(board, i, j - 1);
        }
    }
    
    /**
     * Please use BFS with queues to avoid stack overflow problems.
     * @param board
     */
    public void solve_bfs(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        int m = board.length, n = board[0].length;
        char[][] r = new char[m][n];
        for (char[] line : r)
            Arrays.fill(line, 'X');

        for (int col = 0; col < n; col++) {
            if (board[0][col] == 'O') {
                label(board, r, 0, col);
            }
            if (board[m - 1][col] == 'O') {
                label(board, r, m - 1, col);
            }
        }
        for (int row = 0; row < m; row++) {
            if (board[row][0] == 'O') {
                label(board, r, row, 0);
            }
            if (board[row][n - 1] == 'O') {
                label(board, r, row, n - 1);
            }
        }
        for (int row = 0; row < m; row++)
            board[row] = r[row];
    }

    private void label(char[][] board, char[][] r, int row, int col) {
        Queue<Integer[]> q = new LinkedList<>();
        q.offer(new Integer[] { row, col });
        while (!q.isEmpty()) {
            Integer[] cell = q.poll();
            int i = cell[0], j = cell[1];
            if (board[i][j] == 'O' && r[i][j] == 'X') {
                r[i][j] = 'O';
                if (i > 1)
                    q.offer(new Integer[] { i - 1, j });
                if (j > 1)
                    q.offer(new Integer[] { i, j - 1 });
                if (i < r.length - 2)
                    q.offer(new Integer[] { i + 1, j });
                if (j < r[0].length - 2)
                    q.offer(new Integer[] { i, j + 1 });
            }
        }
    }


    public static void main(String args[]) {
        FillOsWIthXsIfSurroundedByXs fo = new FillOsWIthXsIfSurroundedByXs();
        char board[][] = { { 'X', 'X', 'X', 'X' }, { 'X', 'X', 'O', 'X' }, { 'X', 'O', 'X', 'X' }, { 'X', 'X', 'O', 'X' } };

        printArray(board);
        fo.solve_bfs(board);
        System.out.println("after solve -->");
        printArray(board);
    }
    
    private static void printArray(char[][] screen) {
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                System.out.print(screen[i][j] + " ");
            }
            System.out.println();
        }
    }
}
