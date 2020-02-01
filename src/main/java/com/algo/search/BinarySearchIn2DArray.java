package com.algo.search;

public class BinarySearchIn2DArray {

	/**
	 * 
	 * @param array
	 *            sorted array where each row is also sorted and last element of a row is less that first element of
	 *            next rows
	 * @param num
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;

		int m = matrix.length;
		int n = matrix[0].length;

		int start = 0;
		int end = m * n - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;
			int midX = mid / n;
			int midY = mid % n;

			if (matrix[midX][midY] == target)
				return true;

			if (matrix[midX][midY] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return false;
	}

	/**
	 * In a binary matrix (all elements are 0 and 1), every row is sorted in ascending order (0 to the left of 1). Find
	 * the leftmost column index with a 1 in it.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * 
	 * [[0, 0, 0, 1], [0, 0, 1, 1], [0, 1, 1, 1], [0, 0, 0, 0]]
	 * 
	 * Output: 1
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * 
	 * [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]
	 * 
	 * Output: -1
	 * 
	 * Expected solution better than O(r * c).
	 * 
	 * T - O(r + c)
	 * 
	 * @param args
	 */
	public int findLeftMostIndex(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int candidate = -1;
		for (int r = 0, c = cols - 1; r < rows && c >= 0;) {
			if (matrix[r][c] == 1) {
				candidate = c;
				c--;
			} else {
				r++;
			}
		}

		return candidate;
	}

	public static void main(String[] args) {
		BinarySearchIn2DArray bs = new BinarySearchIn2DArray();
		int[][] array = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(bs.searchMatrix(array, 4));
		System.out.println(bs.searchMatrix(array, 5));
		System.out.println(bs.searchMatrix(array, 8));
		System.out.println(bs.searchMatrix(array, 80));

		int[][] ar = new int[][] { { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 } };
		int[][] ar1 = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
		System.out.println(bs.findLeftMostIndex(ar));
		System.out.println(bs.findLeftMostIndex(ar1));
	}

}
