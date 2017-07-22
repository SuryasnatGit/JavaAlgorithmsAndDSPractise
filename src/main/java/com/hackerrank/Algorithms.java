package com.hackerrank;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Algorithms {

	public static void main(String[] args) {
		Algorithms algo = new Algorithms();
		// algo.diagonalAbsoluteDiff();
		algo.precisionProblem();
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

	public void precisionProblem() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		for (int arr_i = 0; arr_i < n; arr_i++) {
			arr[arr_i] = in.nextInt();
		}
		int posc = 0, negc = 0, zeroc = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] < 0)
				negc++;
			else if (arr[i] > 0)
				posc++;
			else
				zeroc++;
		}
		// Float f = new Float(zeroc);
		DecimalFormat df = new DecimalFormat("#.000000");
		// df.setMaximumFractionDigits(6);
		// System.out.println(df.format(negc));
		float p = posc / n;
		float ne = negc / n;
		float z = zeroc / n;
		System.out.println(p);
		System.out.println(ne);
		System.out.println(z);
		System.out.println(df.format(ne));
		System.out.println(df.format(p));
		System.out.println(String.format("%.6f", z));
	}

}
