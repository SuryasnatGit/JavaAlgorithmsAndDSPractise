package com.ctci.moderate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative)
 * difference. Return the difference.
 * 
 * EXAMPLE
 * 
 * Input {1,3,5,11,2} and {23,127,235,19,8}
 * 
 * Output 3. That is, the pair (11, 8).
 * 
 * This algorithm takes O(A log A + B log B) time to sort and O(A + B) time to find the minimum difference. Therefore,
 * the overall runtime is 0 (A log A + B log B).
 *
 * CTCI: 16.6
 */
public class SmallestDifference {

	public int smallestDiff(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);

		int diff = Integer.MAX_VALUE;
		int i = 0, j = 0;
		int[] res = new int[2];

		while (i < a1.length && j < a2.length) {
			if (Math.abs(a1[i] - a2[j]) < diff) {
				diff = Math.abs(a1[i] - a2[j]);
				res[0] = a1[i];
				res[1] = a2[j];
			}

			// move the smaller one
			if (a1[i] < a2[j]) {
				i++;
			} else {
				j++;
			}
		}

		System.out.println(Arrays.toString(res));
		return diff;
	}

	/**
	 * https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem
	 * 
	 * Each element in a must be used, the length of b is greater than or equal to a, and a corresponding element from
	 * each element in a to b (must be taken in order) to minimize the sum of the differences. I am an example,
	 * math.abs(a1-b3)+math.abs(a2-b9)+math.abs(a3-b17) is the smallest. It cannot be sorted because if sort is done,
	 * the order of a and b will be hit Messed up. For example, in the above example, a1-b3 cannot be taken, a2-b1 is
	 * illegal.
	 * 
	 * // array[i][j] mean when we reach a[i] and we try to chose b[j] // thus array[i][j] = min(array[i - 1][j - 1] +
	 * abs(a[i] - b[j]), array[i - 1][j]) // thus array[a.length][b.length] will be the result
	 */
	public int minDifferenceSum(int[] a, int[] b) {
		int[][] hash = new int[a.length + 1][b.length + 1];

		for (int i = 1; i <= a.length; i++) {
			hash[i][0] = Integer.MAX_VALUE; // Need to initialize this because we need hash[i][j - 1]
		}

		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length - (a.length - i); j++) { // Make sure to leave room on b to match a
				hash[i][j] = Math.min(hash[i - 1][j - 1] + Math.abs(a[i - 1] - b[j - 1]), hash[i][j - 1]);
			}
		}

		return hash[a.length][b.length];
	}

	// What if B is super long
	// This solution has better space efficiency
	public int minDifferenceSumLong(int[] a, int[] b) {
		int[] hash = new int[a.length + 1];

		for (int i = 1; i <= a.length; i++) {
			hash[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < b.length; i++) { // Start from index i in b, b is super long
			int[] temp = new int[a.length + 1];
			for (int j = 1; j <= a.length; j++) {
				if (b.length - i - 1 < a.length - j) {
					continue;
				}
				temp[j] = Math.min(hash[j], hash[j - 1] + Math.abs(b[i] - a[j - 1]));
			}

			hash = temp;
		}

		return hash[a.length];
	}

	/**
	 * Given three sorted arrays A[], B[] and C[], find 3 elements i, j and k from A, B and C respectively such that
	 * max(abs(A – B[j]), abs(B[j] – C[k]), abs(C[k] – A)) is minimized. Here abs() indicates absolute value. Example :
	 * Input: A[] = {1, 4, 10} B[] = {2, 15, 20} C[] = {10, 12} Output: 10 15 10。 10 from A, 15 from B and 10 from C.
	 * Input: A[] = {20, 24, 100} B[] = {2, 19, 22, 79, 800} C[] = {10, 12, 23, 24, 119} Output: 24 22 23。24 from A, 22
	 * from B and 23 from C
	 * 
	 */
	public List<Integer> getMinimum(int[] A, int[] B, int[] C) {
		if (A == null || B == null || C == null || A.length == 0 || B.length == 0 || C.length == 0) {
			return new ArrayList<Integer>();
		}

		int posA = 0, posB = 0, posC = 0;
		int min = Integer.MAX_VALUE;
		List<Integer> result = new ArrayList<>();

		while (posA < A.length && posB < B.length && posC < C.length) {
			int a = A[posA];
			int b = B[posB];
			int c = C[posC];
			int sum = Math.abs(a - b) + Math.abs(b - c) + Math.abs(c - a);
			if (sum < min) {
				min = sum;
				result = Arrays.asList(a, b, c);
			}

			if (a <= b && a <= c) {
				posA++;
			} else if (b <= c && b <= a) {
				posB++;
			} else {
				posC++;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		SmallestDifference sd = new SmallestDifference();
		System.out.println(sd.smallestDiff(new int[] { 1, 3, 5, 11, 2 }, new int[] { 23, 127, 235, 19, 8 }));
		System.out.println(sd.getMinimum(new int[] { 20, 24, 100 }, new int[] { 2, 19, 22, 79, 800 },
				new int[] { 10, 12, 23, 24, 119 }));
	}
}
