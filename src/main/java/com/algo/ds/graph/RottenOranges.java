package com.algo.ds.graph;

/**
 * Given a matrix mat[][], where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:
 * <p>
 * 0: Empty cell
 * 1: Cells have fresh oranges
 * 2: Cells have rotten oranges
 * <p>
 * Find the minimum time required so that all the oranges become rotten. A rotten orange at index (i,j) can rot other fresh oranges which are its neighbours
 * (up, down, left, and right). Each rotten orange takes 1 unit of time to rot all its adjacent fresh oranges.
 * <p>
 * Note : If it is impossible to rot every orange then simply return -1.
 * <p>
 * Examples:
 * <p>
 * Input:  mat[][] = [[2, 1, 0, 2, 1],
 * [1, 0, 1, 2, 1],
 * [1, 0, 0, 2, 1]]
 * <p>
 * Output: 2
 * <p>
 * Input:  mat[][] = [[2, 1, 0, 2, 1],
 * [0, 0, 1, 2, 1],
 * [1, 0, 0, 2, 1]]
 * <p>
 * Output: -1
 */
public class RottenOranges {

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        RottenOranges ro = new RottenOranges();
//        System.out.println(ro.naiveSol(new int[][]{{2, 1, 0, 2, 1}, {1, 0, 0, 2, 1}, {1, 0, 1, 2, 1}}));
        System.out.println(ro.dfsSol(new int[][]{{2, 1, 0, 2, 1}, {1, 0, 0, 2, 1}, {1, 0, 1, 2, 1}}));
    }

    /**
     * To handle this properly: Whenever a rotten orange at cell (i, j) rots a fresh orange, we update that fresh orange’s value to mat[i][j] + 1.
     * This way, oranges that become rotten at time t will have a value t + 2, meaning at the next time step (t + 1), they can start infecting their neighbors.
     * This process continues as long as at least one fresh orange is rotted in a given iteration.The moment no more oranges can be rotted,
     * the last time value we used indicates the minimum time required to rot all oranges. Finally, we perform a check to see if any fresh oranges (1s)
     * are still left in the grid:
     * <p>
     * If yes - it means not all oranges could rot, so we return -1.
     * Otherwise - return the last computed time as the result.
     * <p>
     * O((n x m) ^ 2) Time and O(1) Space
     *
     * @return
     */
    public int naiveSol(int[][] matrix) {
        int elapsedTime = 0;
        boolean changed = false;
        int rows = matrix.length;
        int cols = matrix[0].length;

        while (true) {
            changed = false;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    // check if cell was marked in last iteration
                    if (matrix[row][col] == elapsedTime + 2) { // already marked
                        // check all directions
                        for (int[] direction : directions) {
                            int newRow = row + direction[0];
                            int newCol = col + direction[1];
                            // check boundary and increment
                            if (isSafe(newRow, newCol, rows, cols) && matrix[newRow][newCol] == 1) {
                                matrix[newRow][newRow] = matrix[row][col] + 1;
                                changed = true;
                            }
                        }
                    }
                }
            }

            if (!changed) {
                break;
            }
            elapsedTime++;
        }

        // again check for presence of 1
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1) {
                    return -1;
                }
            }
        }
        return elapsedTime;
    }

    private boolean isSafe(int newRow, int newCol, int rows, int cols) {
        return newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols;
    }

    /**
     * We start by iterating through the entire matrix, and whenever we find a rotten orange (2), we begin a DFS from that cell. During the DFS, we go to all its adjacent cells and record the time when each orange becomes rotten.
     * If a neighboring cell contains a fresh orange (1), that means it can now become rotten, so we recursively call DFS for that cell with the time current_time + 1.
     * If a cell was already rotten earlier but now can rot in less time, we update its time and continue DFS from that cell again.
     * <p>
     * O(n x m) Time and O(1) Space
     *
     * @param matrix
     * @return
     */
    public int dfsSol(int[][] matrix) {
        int elapsedTime = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 2) {
                    dfs(matrix, row, col, rows, cols, 2);
                }
            }
        }

        // again check for presence of 1
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1) {
                    return -1;
                }

                // update maximum time
                elapsedTime = Math.max(elapsedTime, matrix[row][col] - 2);
            }
        }
        return elapsedTime;
    }

    private void dfs(int[][] matrix, int row, int col, int rows, int cols, int time) {
        // update minimum time
        matrix[row][col] = time;
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (isSafe(newRow, newCol, rows, cols) && (matrix[newRow][newCol] == 1 || matrix[newRow][newCol] > time + 1)) {
                dfs(matrix, newRow, newCol, rows, cols, time + 1);
            }
        }
    }

}
