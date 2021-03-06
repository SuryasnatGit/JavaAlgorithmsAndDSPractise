package com.ctci.arraysnstrings;

import java.util.Arrays;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
 * 
 * Question: Given a boolean matrix, update it so that if any cell is true, all the cells in that row and column are
 * true.
 * 
 * ● Eg.
 * 
 * [true, false, false] <br/>
 * [false, false, false] <br/>
 * [false, false, false] <br/>
 *
 * to
 *
 * [true, true, true ] <br/>
 * [true, false, false] <br/>
 * [true, false, false] <br/>
 *
 */
public class ZeroMatrix {

	public void zeroMatrix(boolean[][] matrix) {

		// Verify the input array is nonzero
		if (matrix.length == 0 || matrix[0].length == 0)
			return;

		// Determine whether the first row or first column is true.
		// boolean 1 - true and 0 - false
		boolean rowZero = false, colZero = false;
		for (boolean i : matrix[0]) {
			rowZero |= i;
		}

		for (boolean[] i : matrix) {
			colZero |= i[0];
		}

		// For each cell not in the first row/column, if it is true, set the
		// cell in the first row/same column and first column/same row to be
		// true
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j]) {
					matrix[i][0] = true;
					matrix[0][j] = true;
				}
			}
		}

		// Go through the first column and set each row to true where cell in
		// the first column is true
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i][0]) {
				for (int j = 1; j < matrix[i].length; j++) {
					matrix[i][j] = true;
				}
			}
		}

		// Repeat for the rows
		for (int j = 1; j < matrix[0].length; j++) {
			if (matrix[0][j]) {
				for (int i = 1; i < matrix.length; i++) {
					matrix[i][j] = true;
				}
			}
		}

		// Set first row/column to true if necessary
		if (rowZero) {
			for (int i = 0; i < matrix[0].length; i++) {
				matrix[0][i] = true;
			}
		}

		if (colZero) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = true;
			}
		}
	}

	public void setZeroes(int[][] matrix) {

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int firstRowZero = 1;
		int firstColZero = 1;

		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				firstRowZero = 0;
				break;
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				firstColZero = 0;
				break;
			}
		}

		// we start from index 1 so that we don't set the first row or first col to 0 which will make all rows and cols
		// to 0 in the next 2 steps
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < matrix[0].length; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		for (int i = 1; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				for (int j = 1; j < matrix.length; j++) {
					matrix[j][i] = 0;
				}
			}
		}

		// target first row and col
		if (firstColZero == 0) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}

		if (firstRowZero == 0) {
			for (int i = 0; i < matrix[0].length; i++) {
				matrix[0][i] = 0;
			}
		}
	}

	public static void main(String[] args) {
		ZeroMatrix zm = new ZeroMatrix();
		boolean[][] matrix = { { true, false, false }, { false, false, false }, { false, false, false } };
		zm.zeroMatrix(matrix);
		Arrays.stream(matrix).forEach(a -> System.out.println(Arrays.toString(a) + ","));

		int[][] mat = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		zm.setZeroes(mat);
		Arrays.stream(mat).forEach(a -> System.out.println(Arrays.toString(a) + ","));
	}

}
