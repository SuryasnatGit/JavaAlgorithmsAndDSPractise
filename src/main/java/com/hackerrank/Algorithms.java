package com.hackerrank;

import java.util.Scanner;

public class Algorithms {

	public static void main(String[] args) {
		Algorithms algo = new Algorithms();
		algo.diagonalAbsoluteDiff();
	}

	public void diagonalAbsoluteDiff() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		sc.close();
		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < n; i++) {
			s1 += arr[i][i];
			s2 += arr[i][n - 1 - i];
		}
		System.out.println(Math.abs(s1 - s2));
	}

}
