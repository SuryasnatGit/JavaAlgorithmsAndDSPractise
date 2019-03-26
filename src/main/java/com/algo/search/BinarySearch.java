package com.algo.search;

public class BinarySearch {

	/**
	 * time - O(log n) space - O(log n)
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 * @param num
	 * @return
	 */
	public int binarySearchRecursive(int[] arr, int left, int right, int num) {
		if (left <= right) {
			int mid = (left + right) / 2;
			if (arr[mid] == num)
				return mid;
			if (num < arr[mid])
				return binarySearchRecursive(arr, left, mid - 1, num);
			else
				return binarySearchRecursive(arr, mid + 1, right, num);
		}
		return -1;
	}

	/**
	 * time - T(n/2) + c space - O(1)
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 * @param num
	 * @return
	 */
	public int binarySearchIterative(int[] arr, int left, int right, int num) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (num == arr[mid])
				return mid;
			if (num < arr[mid])
				right = mid - 1;
			else
				left = mid + 1;
		}
		return -1;
	}

}
