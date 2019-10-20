package com.algo.search;

public class BinarySearchIn2DArray {

	/**
	 * 
	 * @param array sorted array where each row is also sorted and last element of a row is less that
	 *              first element of next rows
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
			int mid = (start + end) / 2;
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

	public static void main(String[] args) {
		BinarySearchIn2DArray bs = new BinarySearchIn2DArray();
		int[][] array = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(bs.searchMatrix(array, 4));
		System.out.println(bs.searchMatrix(array, 5));
		System.out.println(bs.searchMatrix(array, 8));
	}

}
