package com.hackerrank;

import java.util.Scanner;

public class ImplementationProblems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementationProblems p = new ImplementationProblems();
		p.between2sets();
	}

	/**
	 * Consider two sets of positive integers, and . We say that a positive integer,A={a0,a1,a2..,an} and
	 * B={b0,b1,b2...,bm} , is between sets and if the following conditions are satisfied:
	 * 
	 * All elements in are factors of . is a factor of all elements in . Given and , find and print the number of
	 * integers (i.e., possible 's) that are between the two sets.
	 */
	public void between2sets() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		// find max of a[] and min of b[]
		int max_a = 0;
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
			max_a = Integer.max(a[a_i], max_a);
		}
		int min_b = Integer.MAX_VALUE;
		int[] b = new int[m];
		for (int b_i = 0; b_i < m; b_i++) {
			b[b_i] = in.nextInt();
			min_b = Integer.min(b[b_i], min_b);
		}
		int count = 0;
		for (int i = max_a; i <= min_b; i++) {
			if (min_b % i == 0)
				count++;
		}
		System.out.println(count);

	}

}
