package com.hackerrank;

import java.util.Scanner;

public class ArrayProblems {

	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();
		// ap.maxHourglassSum();
		//
		// System.out.println(Integer.toBinaryString(10));
		// System.out.println(Integer.toBinaryString(15));
		// int s = 31 / 0;
		// if (s > 0)
		// System.out.println("YES");
		// else
		// System.out.println("NO");
		// System.out.println(s);
		int[] arr = { 1, 2, 5, 3, 7, 10 };
		// int[] arr = { 7, 5, 3, 2, 1 };
		System.out.println(ap.maxDiff(arr, 6));
		System.out.println(ap.maxDiff_1(arr, 6));
		System.out.println(ap.maxDiff_2(arr, 6));
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
	 * Given an array arr[] of integers, find out the difference between any two elements such that larger element
	 * appears after the smaller number in arr[].
	 * 
	 * Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2). If array is
	 * [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)<br/>
	 * 
	 * Time complexity - O(n^2) <br/>
	 * Space complexity - O(1)
	 * 
	 * @param arr
	 * @param arr_size
	 * @return
	 */
	public int maxDiff(int arr[], int arr_size) {
		boolean maxDiffConditionSatisfied = false;
		boolean maxDiffConditionNotSatisfied = false;
		int max_diff = arr[1] - arr[0];
		int i, j;
		for (i = 0; i < arr_size; i++) {
			for (j = i + 1; j < arr_size; j++) {
				if (arr[j] - arr[i] > max_diff) {
					max_diff = arr[j] - arr[i];
					maxDiffConditionSatisfied = true;
				} else {
					maxDiffConditionNotSatisfied = true;
				}
			}
		}
		return maxDiffConditionNotSatisfied && !maxDiffConditionSatisfied ? -1 : max_diff;
	}

	/**
	 * In this method, instead of taking difference of the picked element with every other element, we take the
	 * difference with the minimum element found so far. So we need to keep track of 2 things: 1) Maximum difference
	 * found so far (max_diff). 2) Minimum number visited so far (min_element). <br/>
	 * Time Complexity: O(n) <br/>
	 * Auxiliary Space: O(1)
	 * 
	 * @param arr
	 * @param arr_size
	 * @return
	 */
	public int maxDiff_1(int[] arr, int arr_size) {
		int maxDiff = arr[1] - arr[0];
		int minElem = arr[0];
		for (int i = 1; i < arr_size; i++) {
			if (arr[i] - minElem > maxDiff)
				maxDiff = arr[i] - minElem;
			if (arr[i] < minElem)
				minElem = arr[i];
		}
		return maxDiff;
	}

	/**
	 * We can modify the above method to work in O(1) extra space. Instead of creating an auxiliary array, we can
	 * calculate diff and max sum in same loop. Following is the space optimized version.<br/>
	 * Time Complexity: O(n)<br/>
	 * Auxiliary Space: O(1)
	 * 
	 * @param arr
	 * @param arr_size
	 * @return
	 */
	public int maxDiff_2(int[] arr, int arr_size) {
		int diff = arr[1] - arr[0];
		int currSum = diff;
		int maxSum = currSum;
		for (int i = 1; i < arr_size - 1; i++) {
			diff = arr[i + 1] - arr[i];
			if (currSum > 0)
				currSum += diff;
			else
				currSum = diff;

			if (currSum > maxSum)
				maxSum = currSum;
		}
		return maxSum;
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
