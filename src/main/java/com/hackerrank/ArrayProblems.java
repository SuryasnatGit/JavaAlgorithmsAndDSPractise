package com.hackerrank;

import java.util.Scanner;

public class ArrayProblems {

	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();
		ap.maxHourglassSum();

	}

	/**
	 * O(N) time complexity
	 */
	public void printArrayinInReverse() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] intarr = new int[n];
		for (int i = 0; i < n; i++) {
			intarr[i] = sc.nextInt();
		}
		for (int j = n - 1; j >= 0; j--) {
			System.out.print(intarr[j] + " ");
		}
	}

	/**
	 * O(R x C) where R = # of rows and C = # of columns or O(N^2) if R and C are same.
	 */
	public void maxHourglassSum() {
		// 6 x 6 array
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[][] { { 0, 6, -7, 1, 6, 3 }, { -8, 2, 8, 3, -2, 7 }, { -3, 3, -6, -3, 0, -6 },
				{ 5, 0, 5, -1, -5, 2 }, { 6, 2, 8, 1, 3, 0 }, { 8, 5, 0, 4, -7, 4 } };
		// input
		// for (int i = 0; i < 6; i++) {
		// for (int j = 0; j < 6; j++) {
		// arr[i][j] = sc.nextInt();
		// }
		// }
		sc.close();
		// print for verification
		// for (int i = 0; i < 6; i++) {
		// StringBuffer sb = new StringBuffer();
		// for (int j = 0; j < 6; j++) {
		// sb.append(arr[i][j] + " ");
		// }
		// System.out.println(sb.toString());
		// }

		int offset = 2;// width of hour glass
		int sum = 0;
		int maxsum = Integer.MIN_VALUE; // very important to initialize to min int value
		for (int i = 0; i < 4; i++) { // 4 = N - offset
			for (int j = 0; j < 4; j++) {
				sum = 0;
				for (int c = j; c <= j + offset; c++) {
					sum += arr[i][c];
					sum += arr[i + offset][c];
				}
				sum += arr[i + 1][j + 1]; // middle element of hour glass
				System.out.println("sum :" + sum);
				maxsum = Math.max(sum, maxsum);
			}
		}
		System.out.println("max sum :" + maxsum);

	}

}
