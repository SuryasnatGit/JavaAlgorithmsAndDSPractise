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
 * 
 * Tags : Heap, Binary Search
 */
public class KthSmallestNumberInSortedMatrix {

	public int kthSmallest2(int[][] matrix, int k) {
		int m = matrix.length;
		int n = matrix[0].length;

		if (k <= 0 || k > m * n) {
			return -1;
		}

		int rowNum = (k % n == 0) ? (k / n - 1) : (k / n);
		int remaining = k - rowNum * n;

		int[] row = matrix[rowNum];
		return remaining != 0 ? row[remaining - 1] : 0;
	}

	public static void main(String[] args) {
		KthSmallestNumberInSortedMatrix kth = new KthSmallestNumberInSortedMatrix();
		int[][] matrix = new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };

		System.out.println(kth.kthSmallest2(matrix, 8));
		System.out.println(kth.kthSmallest2(matrix, 2));
		System.out.println(kth.kthSmallest2(matrix, 3));
		System.out.println(kth.kthSmallest2(matrix, 6));
		System.out.println(kth.kthSmallest2(matrix, 0));
		System.out.println(kth.kthSmallest2(matrix, 1));
		System.out.println(kth.kthSmallest2(matrix, 9));
		System.out.println(kth.kthSmallest2(matrix, 10));
	}
}
