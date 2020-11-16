package com.leetcode;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element
 * in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * Example:
 * 
 * matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ], k = 8,
 * 
 * return 13.
 * 
 * Category : Medium
 */
public class KthSmallestNumberInSortedMatrix {

	public int kthSmallest(int[][] matrix, int k) {
		int m = matrix.length;
		int n = matrix[0].length;

		int rowNum = k / n;
		int remaining = k - rowNum * n;

		int[] row = matrix[rowNum];
		return remaining == 0 ? row[remaining - 1];
	}

	public static void main(String[] args) {
		KthSmallestNumberInSortedMatrix kth = new KthSmallestNumberInSortedMatrix();
		int[][] matrix = new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		System.out.println(kth.kthSmallest(matrix, 8));
		System.out.println(kth.kthSmallest(matrix, 2));
		System.out.println(kth.kthSmallest(matrix, 3));
		System.out.println(kth.kthSmallest(matrix, 6));
	}
}
