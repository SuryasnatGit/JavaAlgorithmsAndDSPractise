package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand
 * operation which turns the water at position (row, col) into a land. Given a list of positions to
 * operate, count the number of islands after each addLand operation. An island is surrounded by
 * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all
 * four edges of the grid are all surrounded by water.
 * 
 * https://leetcode.com/problems/number-of-islands-ii/
 * 
 * Example:
Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

This is a basic union-find problem. Given a graph with points being added, we can at least solve:
How many islands in total?
Which island is pointA belonging to?
Are pointA and pointB connected?
The idea is simple. To represent a list of islands, we use trees. i.e., a list of roots. 
This helps us find the identifier of an island faster. If roots[c] = p 
means the parent of node c is p, we can climb up the parent chain to find out the 
identifier of an island, i.e., which island this point belongs to:

Do root[root[roots[c]]]... until root[c] == c;

To transform the two dimension problem into the classic UF, perform a linear mapping:

int id = n * x + y;

Initially assume every cell are in non-island set {-1}. 
When point A is added, we create a new root, i.e., a new island. 
Then, check if any of its 4 neighbors belong to the same island. 
If not, union the neighbor by setting the root to be the same. Remember to skip non-island cells.
UNION operation is only changing the root parent so the running time is O(1).
FIND operation is proportional to the depth of the tree. If N is the number of points added, 
the average running time is O(logN), and a sequence of 4N operations take O(NlogN). 

If there is no balancing, the worse case could be O(N^2).
Remember that one island could have different roots[node] value for each node. 
Because roots[node] is the parent of the node, not the highest root of the island. 
To find the actually root, we have to climb up the tree by calling findIsland function

 */
public class NumberOfIslandDynamic {
	public List<Integer> numIslands2(int n, int m, int[][] positions) {
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

	public static void main(String args[]) {
		NumberOfIslandDynamic nd = new NumberOfIslandDynamic();
		int[][] input = { { 0, 1 }, { 1, 2 }, { 2, 1 }, { 1, 0 }, { 0, 2 }, { 0, 0 }, { 1, 1 } };
		System.out.print(nd.numIslands2(3, 3, input));
	}
}
