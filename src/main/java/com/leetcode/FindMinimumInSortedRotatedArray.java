package com.leetcode;

public class FindMinimumInSortedRotatedArray {

	/**
	 * As we are discarding half of the elements at each step, the runtime complexity is O(log n). If
	 * duplicates present then worst case becomes O(n)
	 * 
	 * @param arr
	 * @return
	 */
	public int findMin(int[] arr) {
		int L = 0;
		int R = arr.length - 1;
		while (L < R && arr[L] >= arr[R]) {
			int M = (L + R) / 2;
			if (arr[M] > arr[R]) {
				L = M + 1;
			} else if (arr[M] < arr[R]) {
				R = M;
			} else { // if arr[L] == arr[M] == arr[R]. if duplicates are present.
				L = L + 1;
			}
		}
		return arr[L];
	}

	public static void main(String[] args) {
		FindMinimumInSortedRotatedArray min = new FindMinimumInSortedRotatedArray();
		System.out.println(min.findMin(new int[] { 7, 6, 3, 4, 5 }));
	}
}
