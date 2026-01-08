package com.algo.ds.graph;

import java.util.*;

/**
 * http://www.geeksforgeeks.org/find-number-of-islands/.
 * <p>
 * Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island. For example, the
 * below matrix contains 5 islands.
 * <p>
 * Input : mat[][] = {{1, 1, 0, 0, 0}, {0, 1, 0, 0, 1}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1} Output : 5.
 * <p>
 * This is a variation of the standard problem: Counting the number of connected components in an undirected graph. The
 * problem can be easily solved by applying DFS() on each component. In each DFS() call, a component or a sub-graph is
 * visited. We will call DFS on the next un-visited component. The number of calls to DFS() gives the number of
 * connected components. BFS can also be used.
 * <p>
 * Category : Hard
 */
public class NumberOfIsland {

    public static void main(String[] args) {

        NumberOfIsland island = new NumberOfIsland();
        System.out.println(island.numberOfIslandDFS(new int[][]{{1, 1, 0, 1, 0}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}}));
        System.out.println(island.numberOfIslandsBFS(new int[][]{{1, 1, 0, 1, 0}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}}));
        System.out.println(island.numberOfIslandsUF(new int[][]{{1, 1, 0, 1, 0}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}}));
    }

    /**
     * Solution 1 - Using DFS. Time complexity: O(ROW x COL)
     *
     * @param graph
     * @return
     */
    public int numberOfIslandDFS(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] == 1) {
                    count++;
                    DFS(graph, i, j);
                }
            }
        }
        return count;
    }

    private void DFS(int[][] graph, int i, int j) {
        if (i < 0 || j < 0 || i == graph.length || j == graph[i].length || graph[i][j] == 0) {
            return;
        }
        graph[i][j] = 0;

        DFS(graph, i, j + 1);
        DFS(graph, i + 1, j);
        DFS(graph, i, j - 1);
        DFS(graph, i - 1, j);
    }

    /**
     * Solution 2 - Using BFS
     *
     */
    public int numberOfIslandsBFS(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[] row = {1, 0, -1, 0};
        int[] col = {0, 1, 0, -1};
        int numOfIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    numOfIslands++;
                    grid[i][j] = 0;
                    // create an empty queue and enqueue source node
                    Queue<Pair> q = new LinkedList<>();
                    q.offer(new Pair(i, j));

                    // run till queue is not empty
                    while (!q.isEmpty()) {
                        Pair p = q.poll();

                        // check for all 8 possible movements from current cell
                        // and enqueue each valid movement
                        for (int k = 0; k < 4; k++) {
                            // Skip if location is invalid or already processed
                            // or has water
                            if (isSafe(grid, p.x + row[k], p.y + col[k])) {
                                // skip if location is invalid or it is already
                                // processed or consists of water
                                grid[p.x + row[k]][p.y + col[k]] = 0;
                                q.add(new Pair(p.x + row[k], p.y + col[k]));
                            }
                        }
                    }
                }
            }
        }
        return numOfIslands;
    }

    /**
     * Function to check if it is safe to go to position (x, y) from current position. The function returns false if (x,
     * y) is not valid matrix coordinates or (x, y) represents water or position (x, y) is already processed
     */
    private boolean isSafe(int[][] grid, int x, int y) {
        return (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1);
    }

    /**
     * Solution 3 - Using disjoint sets
     *
     *
     */
    public int numberOfIslandsUF(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(grid);
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        if (isSafe(grid, x, y)) {
                            int id1 = i * cols + j;
                            int id2 = x * cols + y;
                            uf.union(id1, id2);
                        }
                    }
                }
            }
        }
        return uf.count;
    }

    class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            parent = new int[rows * cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        int id = i * cols + j;
                        parent[id] = id;
                        count++;
                    }
                }
            }
        }

        public void union(int i, int j) {
            int parentI = find(i);
            int parentJ = find(j);
            if (parentI != parentJ) {
                parent[parentI] = parentJ;
                count--;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

