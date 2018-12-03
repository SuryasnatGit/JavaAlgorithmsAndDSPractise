package com.ctci.bigo;

import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {

	/**
	 * Given two sorted arrays, find the number of elements in common. The arrays are the same length
	 * and each has all distinct elements. complexity - O(N^2)
	 * 
	 * @return
	 */
	public int numberOfCommonElementsIn2SortedArray(int[] a, int[] b) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i] == b[j])
					count++;
			}
		}
		return count;
	}

	public int numberOfCommonElementsIn2SortedArray_hashTable(int[] a, int[] b) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) { // O(N)
			map.put(a[i], a[i]);// O(1)
		}
		for (int i = 0; i < b.length; i++) {// O(N)
			if (map.containsKey(b[i]))
				count++;
		}
		return count;
	}

	/**
	 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are
	 * set to 0.
	 * 
	 * time complexity - O(M * N). Space complexity - O(M + N)
	 */
	public void zeroMatrix(int[][] matrix) {
		printMatrix(matrix);
		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					column[j] = true;
				}
			}
		}

		for (int i = 0; i < row.length; i++) {
			if (row[i])
				nullifyRow(matrix, i);
		}

		for (int i = 0; i < column.length; i++) {
			if (column[i])
				nullifyColumn(matrix, i);
		}

		System.out.println("result");
		printMatrix(matrix);
	}

	private void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void nullifyRow(int[][] matrix, int row) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[row][i] = 0;
		}
	}

	private void nullifyColumn(int[][] matrix, int column) {
		for (int i = 0; i < matrix[0].length; i++) {
			matrix[i][column] = 0;
		}
	}

	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();

		int[] a = { 13, 17, 39, 40, 55, 59, 78 };
		int[] b = { 15, 19, 39, 43, 55, 60, 78 };
		System.out.println(ap.numberOfCommonElementsIn2SortedArray(a, b));
		int[][] matrix = { { 0, 2, 3 }, { 4, 5, 0 }, { 6, 7, 8 } };
		ap.zeroMatrix(matrix);
	}

}
