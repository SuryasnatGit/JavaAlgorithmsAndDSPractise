package com.algo.ds.array;

import java.util.Arrays;

public class RotateArray {

	public void leftRotate(int[] arr, int d) {
		int length = arr.length;
		d %= length;

		int[] temp = new int[length];
		for (int i = 0; i < length; i++) {
			temp[(length - d + i) % length] = arr[i];
		}
		System.arraycopy(temp, 0, arr, 0, length);

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * rotate int arr by k steps. T - O(n) S - O(n)
	 * 
	 * @param arr
	 * @param steps
	 */
	public void rotateArray1(int[] arr, int k) {
		k %= arr.length;

		int[] tempArr = new int[arr.length];
		for (int i = 0; i < k; i++) {
			tempArr[i] = arr[arr.length - k + i];
		}
		int j = 0;
		for (int i = k; i < arr.length; i++) {
			tempArr[i] = arr[j];
			j++;
		}
		System.arraycopy(tempArr, 0, arr, 0, arr.length);

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * using bubble sort, T - O(n * k) time where n = number of elements in array and k is number of steps. S - O(1)
	 * 
	 * @param arr
	 * @param k
	 */
	public void rotateArray2(int[] arr, int k) {
		if (arr == null || k < 0 || arr.length == 0)
			throw new IllegalArgumentException("invalid input");

		k %= arr.length;

		for (int i = 0; i < k; i++) {
			for (int j = arr.length - 1; j > 0; j--) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		}

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * first split the array in 2 halfs. T - O(n) S - O(1)
	 * 
	 * @param arr
	 * @param k
	 */
	public void rotateArray3(int[] arr, int k) {
		if (arr == null || k < 0 || arr.length == 0)
			throw new IllegalArgumentException("invalid input");

		k %= arr.length;

		// whole array
		reverse(arr, 0, arr.length - 1);
		// first half
		reverse(arr, 0, k - 1);
		// second half
		reverse(arr, k, arr.length - 1);

		System.out.println(Arrays.toString(arr));
	}

	private void reverse(int[] arr, int start, int end) {
		if (arr == null || arr.length == 1)
			return;

		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		RotateArray ra = new RotateArray();
		ra.leftRotate(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3);
		ra.rotateArray1(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3);
		ra.rotateArray2(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3);
		ra.rotateArray3(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3);
	}
}
