package com.leetcode;

import java.util.PriorityQueue;

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

	/**
	 * Time Complexity: let X=min(K,N); X+K log(X)
	 * 
	 * Well the heap construction takes O(X) time. After that, we perform K iterations and each iteration has two
	 * operations. We extract the minimum element from a heap containing X elements. Then we add a new element to this
	 * heap. Both the operations will take O(log(X)) time. Thus, the total time complexity for this algorithm comes down
	 * to be O(X+Klog(X)) where X is min(K,N). Space Complexity: O(X) which is occupied by the heap.
	 * 
	 * return
	 */
	public int kthSmallest1(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0) {
			return -1;
		}

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				pq.add(matrix[i][j]);
			}
		}

		int result = 0;
		while (k-- > 0) {
			result = pq.poll();
		}

		return result;
	}

	// using binary search approach.
	// TODO : to check
	public int kthSmallest2(int[][] matrix, int k) {
		int m = matrix.length;
		int n = matrix[0].length;

		int rowNum = k / n;
		int remaining = k - rowNum * n;

		int[] row = matrix[rowNum];
		return remaining == 0 ? row[remaining - 1] : 0;
	}

	public static void main(String[] args) {
		KthSmallestNumberInSortedMatrix kth = new KthSmallestNumberInSortedMatrix();
		int[][] matrix = new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		System.out.println(kth.kthSmallest1(matrix, 8));
		System.out.println(kth.kthSmallest1(matrix, 2));
		System.out.println(kth.kthSmallest1(matrix, 3));
		System.out.println(kth.kthSmallest1(matrix, 6));
	}
}
