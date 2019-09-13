package com.algo.matrix;

import java.util.Arrays;

public class MatrixMultiplication {

	/**
	 * Plain square matrix multiplication takes O(n^3) time complexity
	 * 
	 * @param A
	 * @param B
	 */
	public void multiply(int[][] A, int[][] B) {
		int[][] C = new int[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				C[i][j] = 0;
				for (int k = 0; k < 2; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		for (int[] i : C) {
			System.out.println(Arrays.toString(i));
		}
	}

	public static void main(String[] args) {
		MatrixMultiplication m = new MatrixMultiplication();
		int[][] A = { { 1, 1 }, { 2, 2 } };
		int[][] B = { { 2, 2 }, { 3, 3 } };
		m.multiply(A, B);
	}

}
