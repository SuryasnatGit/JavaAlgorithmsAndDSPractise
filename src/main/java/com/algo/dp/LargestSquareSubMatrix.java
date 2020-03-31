package com.algo.dp;

import java.util.concurrent.atomic.AtomicInteger;

// https://www.techiedelight.com/find-size-largest-square-sub-matrix-1s-present-given-binary-matrix/
public class LargestSquareSubMatrix {

	// T - exponential S - O(1)
	public int largestSqSubMat_recur(int[][] matrix, int r, int c, AtomicInteger maxSize) {
		// base condition
		if (r == 0 || c == 0) {
			maxSize.set(Integer.max(maxSize.get(), matrix[r][c]));
			return matrix[r][c];
		}

		int top = largestSqSubMat_recur(matrix, r - 1, c, maxSize);

		int left = largestSqSubMat_recur(matrix, r, c - 1, maxSize);

		int diag = largestSqSubMat_recur(matrix, r - 1, c - 1, maxSize);

		int size = 0;
		if (matrix[r][c] != 0)
			size = 1 + Math.min(Math.min(top, left), diag);

		maxSize.set(Integer.max(maxSize.get(), size));

		return size;
	}

	// T - O(m * n) S - O(m * n)
	public int largestSqSubMat_dp(int[][] matrix) {
		// stores size of max sq sub matrix ending at matrix[i][j]
		int[][] count = new int[matrix.length][matrix[0].length];

		int max = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				count[i][j] = matrix[i][j];

				if (i > 0 && j > 0 && matrix[i][j] == 1) {
					count[i][j] = 1 + min(count[i][j - 1], count[i - 1][j], count[i - 1][j - 1]);
				}

				if (max < count[i][j])
					max = count[i][j];
			}
		}

		return max;
	}

	private int min(int a, int b, int c) {
		return Integer.min(a, Integer.min(b, c));
	}

	public static void main(String[] args) {
		LargestSquareSubMatrix la = new LargestSquareSubMatrix();
		int[][] matrix = { { 0, 0, 1, 0, 1, 1 }, { 0, 1, 1, 1, 0, 0 }, { 0, 0, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1 } };
		int r = matrix.length - 1;
		int c = matrix[0].length - 1;

		AtomicInteger maxSize = new AtomicInteger();
		la.largestSqSubMat_recur(matrix, r, c, maxSize);

		System.out.println(maxSize.get());

		System.out.println(la.largestSqSubMat_dp(matrix));
	}
}
