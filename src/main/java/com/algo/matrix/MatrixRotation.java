package com.algo.matrix;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a
 * method to rotate the image by 90 degrees. (an you do this in place?
 * 
 * @author surya
 *
 */
public class MatrixRotation {

	/**
	 * rotate N * N matrix anti clockwise by using in place algorithm.
	 * 
	 * @param n
	 * @param matrix
	 */
	public void rotateMatrix(int n, int[][] matrix) {
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][n - 1 - i]; // moves from right to top
				matrix[j][n - 1 - i] = matrix[n - 1 - i][n - 1 - j];// moves from bottom to right
				matrix[n - 1 - i][n - 1 - j] = matrix[n - 1 - j][i];// moves from left to bottom
				matrix[n - 1 - j][i] = temp;// moves from top to left
			}
		}
	}

	/**
	 * for anti clockwise rotation: - transpose of matrix - reverse columns of transpose. Time
	 * complexity :O(R*C) Space complexity :O(1)
	 * 
	 * To right rotate, we do following steps.
	 * 
	 * Find transpose of matrix. Reverse rows of the transpose.
	 * 
	 * 
	 * 
	 * @param n
	 * @param matrix
	 */
	public void rotateMatrix_1(int n, int[][] matrix) {
		transpose(matrix);
		reverseColumns(matrix);
	}

	// After transpose we swap elements of
	// column one by one for finding left
	// rotation of matrix by 90 degree
	private void reverseColumns(int arr[][]) {
		for (int i = 0; i < arr[0].length; i++)
			for (int j = 0, k = arr[0].length - 1; j < k; j++, k--) {
				int temp = arr[j][i];
				arr[j][i] = arr[k][i];
				arr[k][i] = temp;
			}
	}

	// Function for do transpose of matrix
	private void transpose(int arr[][]) {
		for (int i = 0; i < arr.length; i++)
			for (int j = i; j < arr[0].length; j++) {
				int temp = arr[j][i];
				arr[j][i] = arr[i][j];
				arr[i][j] = temp;
			}
	}

}
