package com.leetcode;

/**
 * Given a list of number, there is only one peak or one drop. Find the maximum drop.
 * 
 * Exps:
 * 
 * 1 -> 2 -> 3 -> 9 -> 3 -> 0 = 9;
 * 
 * 10 -> 4 -> 3 -> 8 = 7 ;
 * 
 * T - O(n), S = O(1)
 *
 */
public class FindMaxDrop {

	public static void main(String[] args) {
		FindMaxDrop fmd = new FindMaxDrop();
		System.out.println(fmd.maxDrop(new int[] { 1, 2, 3, 9, 0 }));
		System.out.println(fmd.maxDrop(new int[] { 10, 4, 3, 8 }));
	}

	int maxDrop(int[] arr) {
		if (arr == null || arr.length <= 2) {
			return -1; // Not valid
		}

		if (arr[0] < arr[1]) {
			// for increasing pattern minimum should be on left or right
			int min = Math.min(arr[0], arr[arr.length - 1]);
			int max = findMax(arr, 0, arr.length - 1);
			return max - min;
		} else {
			// for decreasing pattern maximum should be on left or right
			int max = Math.max(arr[0], arr[arr.length - 1]);
			int min = findMin(arr, 0, arr.length - 1);
			return max - min;
		}
	}

	// Is it possible to use Binary Search here?
	private int findMin(int[] arr, int left, int right) {
		int min = arr[left];
		for (int i = left + 1; i <= right; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	private int findMax(int[] arr, int left, int right) {
		int max = arr[left];
		for (int i = left + 1; i <= right; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}
}
