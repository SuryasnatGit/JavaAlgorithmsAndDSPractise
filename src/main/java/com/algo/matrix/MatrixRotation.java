package com.algo.matrix;

import java.util.Arrays;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the
 * image by 90 degrees. Can you do this in place?
 * 
 * CTCI - 1.7
 *
 * Category : Medium
 */
public class MatrixRotation {

	/**
	 * rotate N * N matrix anti clockwise by using in place algorithm.
	 * 
	 * @param n
	 * @param matrix
	 */
	public void rotateMatrixAntiClockwise(int n, int[][] matrix) {
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][n - 1 - i]; // moves from right to top
				matrix[j][n - 1 - i] = matrix[n - 1 - i][n - 1 - j];// moves from bottom to right
				matrix[n - 1 - i][n - 1 - j] = matrix[n - 1 - j][i];// moves from left to bottom
				matrix[n - 1 - j][i] = temp;// moves from top to left
			}
		}

		Arrays.stream(matrix).forEach(a -> System.out.println(Arrays.toString(a)));
	}

	/**
	 * for clockwise rotation: - transpose of matrix - reverse columns of transpose. Time complexity :O(R*C) Space
	 * complexity :O(1)
	 * 
	 * To right rotate, we do following steps.
	 * 
	 * Find transpose of matrix. Reverse rows of the transpose.
	 * 
	 * 
	 */
	public void rotateMatrixClockwise(int n, int[][] matrix) {
		int len = matrix.length;
		transpose(matrix, len);
		flipHorizontally(matrix, len);

		Arrays.stream(matrix).forEach(a -> System.out.println(Arrays.toString(a)));
	}

	// Function for do transpose of matrix. after transpose, it will be swap(matrix[i][j], matrix[j][i])
	private void transpose(int arr[][], int len) {
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = temp;
			}
		}
	}

	// Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])
	private void flipHorizontally(int arr[][], int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len / 2; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][len - 1 - j];
				arr[i][len - 1 - j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		MatrixRotation mr = new MatrixRotation();
		mr.rotateMatrixAntiClockwise(3, new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		System.out.println();
		mr.rotateMatrixClockwise(3, new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });

	}

}
