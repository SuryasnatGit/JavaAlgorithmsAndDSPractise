package com.algo.matrix;

/**
 * Category : Medium
 * 
 *
 */
public class MatrixSpiralOrder {

	/**
	 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
	 * 
	 * Example 1:
	 * 
	 * Input: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] Output: [1,2,3,6,9,8,7,4,5] Example 2:
	 * 
	 * Input: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10,11,12] ] Output: [1,2,3,4,8,12,11,10,9,5,6,7]
	 * 
	 */
	public void printMatrixInSpiralOrder(int[][] matrix) {

		int top = 0, bottom = matrix.length - 1;
		int left = 0, right = matrix[0].length - 1;

		while (true) {

			if (left > right)
				break;

			for (int i = left; i <= right; i++) {
				System.out.print(matrix[top][i] + " ");
			}
			top++;

			if (top > bottom)
				break;

			for (int i = top; i <= bottom; i++) {
				System.out.print(matrix[i][right] + " ");
			}
			right--;

			if (left > right)
				break;

			for (int i = right; i >= left; i--) {
				System.out.print(matrix[bottom][i] + " ");
			}
			bottom--;

			if (top > bottom)
				break;

			for (int i = bottom; i >= top; i--) {
				System.out.print(matrix[i][left] + " ");
			}
			left++;
		}
	}

	// https://www.techiedelight.com/create-spiral-matrix-given-array/

	private final int M = 5;
	private final int N = 5;

	public void createSpiralMatrixFromArray(int[] arr, int[][] mat) {
		int top = 0, bottom = M - 1;
		int left = 0, right = N - 1;

		int index = 0;

		while (true) {
			if (left > right) {
				break;
			}

			// print top row
			for (int i = left; i <= right; i++) {
				mat[top][i] = arr[index++];
			}
			top++;

			if (top > bottom) {
				break;
			}

			// print right column
			for (int i = top; i <= bottom; i++) {
				mat[i][right] = arr[index++];
			}
			right--;

			if (left > right) {
				break;
			}

			// print bottom row
			for (int i = right; i >= left; i--) {
				mat[bottom][i] = arr[index++];
			}
			bottom--;

			if (top > bottom) {
				break;
			}

			// print left column
			for (int i = bottom; i >= top; i--) {
				mat[i][left] = arr[index++];
			}
			left++;
		}
	}

	// https://www.techiedelight.com/shift-matrix-elements-1-spiral-order/
	public void ShiftMatrix(int[][] mat) {
		int top = 0, bottom = mat.length - 1;
		int left = 0, right = mat[0].length - 1;
		int prev = mat[0][0];

		while (true) {
			if (left > right)
				break;

			// change top row
			for (int i = left; i <= right; i++) {
				int temp = mat[top][i];
				mat[top][i] = prev;
				prev = temp;
			}

			top++;

			if (top > bottom)
				break;

			// change right column
			for (int i = top; i <= bottom; i++) {
				int temp = mat[i][right];
				mat[i][right] = prev;
				prev = temp;
			}

			right--;

			if (left > right)
				break;

			// change bottom row
			for (int i = right; i >= left; i--) {
				int temp = mat[bottom][i];
				mat[bottom][i] = prev;
				prev = temp;
			}

			bottom--;

			if (top > bottom)
				break;

			// change left column
			for (int i = bottom; i >= top; i--) {
				int temp = mat[i][left];
				mat[i][left] = prev;
				prev = temp;
			}

			left++;
		}

		// first element of the matrix will be the last element replaced
		mat[0][0] = prev;
	}

	public static void main(String[] args) {
		MatrixSpiralOrder ms = new MatrixSpiralOrder();
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		ms.printMatrixInSpiralOrder(matrix);
	}
}
