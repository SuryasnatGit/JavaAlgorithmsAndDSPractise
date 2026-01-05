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

    public static void main(String args[]) {

        int matrix[][] = {{1, 1, 0, 1, 0}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}};
        NumberOfIsland island = new NumberOfIsland();
        System.out.println(island.numberOfIslandDFS(matrix));
        System.out.println(island.numberOfIslandsDisjointSets(5, 5, matrix));
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
    public void numberOfIslandsBFS(int[][] mat, boolean[][] processed, int i, int j) {

        int[] row = {-1, -1, -1, 0, 1, 0, 1, 1};
        int[] col = {-1, 1, 0, -1, -1, 1, 0, 1};

        // create an empty queue and enqueue source node
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));

        // mark source node as processed
        processed[i][j] = true;

        // run till queue is not empty
        while (!q.isEmpty()) {
            // pop front node from queue and process it
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            // check for all 8 possible movements from current cell
            // and enqueue each valid movement
            for (int k = 0; k < 8; k++) {
                // Skip if location is invalid or already processed
                // or has water
                if (isSafe(mat, x + row[k], y + col[k], processed)) {
                    // skip if location is invalid or it is already
                    // processed or consists of water
                    processed[x + row[k]][y + col[k]] = true;
                    q.add(new Pair(x + row[k], y + col[k]));
                }
            }
        }
    }

    /**
     * Function to check if it is safe to go to position (x, y) from current position. The function returns false if (x,
     * y) is not valid matrix coordinates or (x, y) represents water or position (x, y) is already processed
     */
    private boolean isSafe(int[][] mat, int x, int y, boolean[][] processed) {
        return (x >= 0) && (x < processed.length) && (y >= 0) && (y < processed[0].length)
                && (mat[x][y] == 1 && !processed[x][y]);
    }

    /**
     * Solution 3 - Using disjoint sets
     *
     *
     */
    public List<Integer> numberOfIslandsDisjointSets(int n, int m, int[][] positions) {
        if (positions.length == 0 || positions[0].length == 0) {
            return Collections.emptyList();
        }

        int count = 0;

        DisjointSet ds = new DisjointSet();
        Set<Integer> land = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (int[] position : positions) {
            int index = position[0] * m + position[1];
            land.add(index);
            ds.makeSet(index);
            count++;

            // find the four neighbors;
            int n1 = (position[0] - 1) * m + position[1];
            int n2 = (position[0] + 1) * m + position[1];
            int n3 = (position[0]) * m + position[1] + 1;
            int n4 = (position[0]) * m + position[1] - 1;

            if (position[0] - 1 >= 0 && land.contains(n1)) {
                if (ds.union(index, n1)) {
                    count--;
                }
            }
            if (position[0] + 1 < n && land.contains(n2)) {
                if (ds.union(index, n2)) {
                    count--;
                }
            }
            if (position[1] + 1 < m && land.contains(n3)) {
                if (ds.union(index, n3)) {
                    count--;
                }
            }
            if (position[1] - 1 >= 0 && land.contains(n4)) {
                if (ds.union(index, n4)) {
                    count--;
                }
            }
            result.add(count);
        }

        return result;
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
