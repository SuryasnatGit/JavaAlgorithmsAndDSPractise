package com.algo.ds.array;

import java.util.Arrays;

/**
 * https://www.techiedelight.com/merge-two-arrays-satisfying-given-constraints/
 *
 */
public class Merge2ArraysWithConstraints {

	public void merge(int[] a, int[] b) {

		// move non zero elements in a to the beginning
		int k = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				a[k++] = a[i];
			}
		}

		merge(a, b, k - 1, b.length - 1);
	}

	private void merge(int[] a, int[] b, int m, int n) {
		int k = m + n + 1;

		while (m >= 0 && n >= 0) {
			if (a[m] > b[n]) {
				a[k--] = a[m--];
			} else {
				a[k--] = b[n--];
			}
		}

		while (n >= 0) {
			a[k--] = b[n--];
		}
	}

	public static void main(String[] args) {
		Merge2ArraysWithConstraints m = new Merge2ArraysWithConstraints();
		int[] a = { 0, 2, 0, 3, 0, 5, 6, 0, 0 };
		int[] b = { 1, 8, 9, 10, 15 };
		m.merge(a, b);

		System.out.println(Arrays.toString(a));
	}

}
