package com.leetcode;

// TODO: add desc / problem statement / link
// Similar to https://leetcode.com/problems/best-sightseeing-pair/
public class MaximumAppeal {
	public int maxAppeal(int[] a) {

		// int[][] mat = new int[a.length][a.length];

		int start = a[0];
		int end = a[0];

		for (int i = 0; i < a.length; i++) {
			if ((a[i] - i) > start) {
				start = a[i] - i;
			}

			if ((a[i] + i) > end) {
				end = a[i] + i;
			}
		}

		return start + end;
	}

	public static void main(String[] args) {
		MaximumAppeal m = new MaximumAppeal();
		System.out.println(m.maxAppeal(new int[] { 1, 3, -3 }));
		System.out.println(m.maxAppeal(new int[] { -8, 4, 0, 5, -3, 6 }));
	}
}
