package com.algo.ds.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * https://www.techiedelight.com/flood-fill-algorithm/
 * <p>
 * http://en.wikipedia.org/wiki/Flood_fill. Flood fill, also called seed fill, is an algorithm that determines the area
 * connected to a given node in a multi-dimensional array. It is used in the "bucket" fill tool of paint programs to
 * fill connected, similarly-colored areas with a different color, and in games such as Go and Minesweeper for
 * determining which pieces are cleared.
 * <p>
 * It means we must change the color of the starting pixel and all other pixels that are connected to it
 * (directly or indirectly) and have the same original color as the starting pixel.
 * Two pixels are considered connected if they are adjacent horizontally or vertically (not diagonally).
 * <p>
 * T - O(MN)
 * <p>
 * Category : Hard
 */
public class FloodFillAlgorithm {

    // Below arrays details all 4 possible movements
    private final int[] row = {-1, 0, 0, 1};
    private final int[] col = {0, 1, -1, 0};

    public static void main(String[] args) {
        FloodFillAlgorithm ff = new FloodFillAlgorithm();

        // matrix showing portion of the screen having different colors
        char[][] M = {"YYYGGGGGGG".toCharArray(), "YYYYYYGXXX".toCharArray(), "GGGGGGGXXX".toCharArray(),
                "WWWWWGGGGX".toCharArray(), "WRRRRRGXXX".toCharArray(), "WWWRRGGXXX".toCharArray(),
                "WBWRRRRRRX".toCharArray(), "WBBBBRRXXX".toCharArray(), "WBBXBBBBXX".toCharArray(),
                "WBBXXXXXXX".toCharArray()};

        // start node
        int x = 3, y = 9; // target color = "X"

        // replacement color
        char replacement = 'C';

        // replace target color with replacement color
        ff.floodfillBFS(M, x, y, replacement);

        // print the colors after replacement
        for (int i = 0; i < M.length; i++) {
            System.out.println(Arrays.toString(M[i]));
        }

        int[][] mat = {{1, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        ff.floodfillDFS(mat, 1, 2, 2);
        for (int[] a : mat) {
            System.out.println(Arrays.toString(a));
        }
    }

    // check if it is possible to go to pixel (x, y) from
    // current pixel. The function returns false if the pixel
    // has different color or it is not a valid pixel
    public boolean isSafe(char[][] M, int m, int n, int x, int y, char target) {
        return x >= 0 && x < m && y >= 0 && y < n && M[x][y] == target;
    }

    // Flood fill using BFS
    public void floodfillBFS(char[][] M, int x, int y, char replacement) {
        int m = M.length;
        int n = M[0].length;

        // create a queue and enqueue starting pixel
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(x, y));

        // get target color
        char target = M[x][y];

        // run till queue is not empty
        while (!q.isEmpty()) {
            // pop front node from queue and process it
            Pair node = q.poll();

            // (x, y) represents current pixel
            x = node.x;
            y = node.y;

            // replace current pixel color with that of replacement
            M[x][y] = replacement;

            // process all 8 adjacent pixels of current pixel and
            // enqueue each valid pixel
            for (int k = 0; k < row.length; k++) {
                // if adjacent pixel at position (x + row[k], y + col[k]) is
                // a valid pixel and have same color as that of current pixel
                if (isSafe(M, m, n, x + row[k], y + col[k], target)) {
                    // enqueue adjacent pixel
                    q.add(new Pair(x + row[k], y + col[k]));
                }
            }
        }
    }

    // Approach 2 - Flood fill using DFS. T - O(m * n) S - O(m * n)
    public void floodfillDFS(int[][] M, int x, int y, int replacement) {
        // get target color
        int target = M[x][y];

        // replace current pixel color with that of replacement
        M[x][y] = replacement;

        // process all 8 adjacent pixels of current pixel and
        // recur for each valid pixel
        for (int k = 0; k < row.length; k++) {
            // if the adjacent pixel at position (x + row[k], y + col[k]) is
            // a valid pixel and have same color as that of the current pixel
            if (isSafe(M, x + row[k], y + col[k], target))
                floodfillDFS(M, x + row[k], y + col[k], replacement);
        }
    }

    // check if it is possible to go to pixel (x, y) from
    // current pixel. The function returns false if the pixel
    // has different color or it is not a valid pixel
    public boolean isSafe(int[][] M, int x, int y, int target) {
        return x >= 0 && x < M.length && y >= 0 && y < M[0].length && M[x][y] == target;
    }

    // Approach 1 - Using BFS
    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
