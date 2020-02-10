package com.algo.ds.array;

/**
 * https://www.techiedelight.com/smallest-window-sorting-which-make-array-sorted/
 *
 * Given an unsorted array arr[0..n-1] of size n, find the minimum length subarray arr[s..e] such that sorting this
 * subarray makes the whole array sorted.
 * 
 * Examples: 1) If the input array is [10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60], your program should be able to find
 * that the subarray lies between the indexes 3 and 8.
 * 
 * 2) If the input array is [0, 1, 15, 25, 6, 7, 30, 40, 50], your program should be able to find that the subarray lies
 * between the indexes 2 and 5.
 * 
 * Complexity - O(n)
 */
public class MinimumSortedWhichSortsEntireArray {

	public int minLength(int arr[]) {
		int i = 0;
		while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
			i++;
		}
		if (i == arr.length - 1) {
			return 0;
		}
		int j = arr.length - 1;
		while (j > 0 && arr[j] > arr[j - 1]) {
			j--;
		}

		System.out.println("The smallest window lies between index " + i + " and " + j);

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int k = i; k <= j; k++) {
			if (max < arr[k]) {
				max = arr[k];
			}
			if (min > arr[k]) {
				min = arr[k];
			}
		}
		int x = i - 1;
		while (x >= 0) {
			if (min > arr[x]) {
				break;
			}
			x--;
		}

		int y = j + 1;
		while (y < arr.length) {
			if (max < arr[y]) {
				break;
			}
			y++;
		}
		return y - x - 2 + 1;
	}

	public static void main(String args[]) {
		int arr[] = { 4, 5, 10, 21, 18, 23, 7, 8, 19, 34, 38 };
		int arr1[] = { 4, 5, 6, 12, 11, 15 };
		int arr2[] = { 1, 3, 7, 2, 5, 6, 4, 8 }; // doesn't work for this.
		MinimumSortedWhichSortsEntireArray msw = new MinimumSortedWhichSortsEntireArray();
		System.out.println(msw.minLength(arr1));
		System.out.println(msw.minLength(arr2));
	}

}
