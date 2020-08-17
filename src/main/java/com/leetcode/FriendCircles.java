package com.leetcode;

import java.util.Arrays;

/**
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in
 * nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
 * And we defined a friend circle is a group of students who are direct or indirect friends.
 * 
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith
 * and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend
 * circles among all the students.
 * 
 * Example 1:
 * 
 * Input: [[1,1,0], [1,1,0], [0,0,1]]
 * 
 * Output: 2
 * 
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. The 2nd student himself is
 * in a friend circle. So return 2.
 * 
 * Example 2:
 * 
 * Input: [[1,1,0], [1,1,1], [0,1,1]]
 * 
 * Output: 1
 * 
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, so the 0th and
 * 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * 
 * Note: N is in range [1,200]. M[i][i] = 1 for all students. If M[i][j] = 1, then M[j][i] = 1.
 * 
 * Solutions: It is a connected component problem. It can be solved by DFS/BFS for Union-Find. I will use UF(Union Find)
 * with the path compression algorithm. We don't have to process all the value of the matrix with 1 because value in the
 * position of [2,1] means student_2 is friend with student_1. Value in the position of [1,2] means student_1 is a
 * friend with student_2. [2,1] and [1,2] are same thing. We will process only one of the values.
 * 
 * Category : Medium
 *
 */
public class FriendCircles {

	public int friendCircles(int[][] M) {
		int numberOfFriends = M.length;

		// to keep track of friend that's already visited
		boolean[] visited = new boolean[numberOfFriends];
		Arrays.fill(visited, false);

		int numFriendCircles = 0;

		for (int i = 0; i < numberOfFriends; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(M, visited, i, numberOfFriends);
				numFriendCircles++;
			}
		}

		return numFriendCircles;
	}

	private void dfs(int[][] M, boolean[] visited, int v, int numberOfFriends) {

		for (int i = 0; i < numberOfFriends; i++) {
			if (M[i][v] == 1 && !visited[i] && i != v) {
				visited[i] = true;
				dfs(M, visited, i, numberOfFriends);
			}
		}
	}

	public static void main(String[] args) {
		FriendCircles fc = new FriendCircles();
		System.out.println(fc.friendCircles(new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } })); // 2
		System.out.println(fc.friendCircles(new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } })); // 1
	}
}
