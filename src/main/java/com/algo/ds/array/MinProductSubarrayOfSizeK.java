package com.algo.ds.array;

public class MinProductSubarrayOfSizeK {

	public int minProduct1(int[] arr, int k) {
		int len = arr.length;

		int minProduct = 1;
		for (int i = 0; i < k; i++) {
			minProduct *= arr[i];
		}

		int prevProduct = minProduct;

		// sliding window mechanism
		for (int i = 1; i <= len - k; i++) {
			int currProduct = (prevProduct / arr[i - 1]) * arr[i + k - 1];
			minProduct = Math.min(minProduct, currProduct);
			prevProduct = currProduct;
		}

		return minProduct;

	}

	public static void main(String[] args) {
		MinProductSubarrayOfSizeK min = new MinProductSubarrayOfSizeK();
		System.out.println(min.minProduct1(new int[] { 1, 5, 9, 8, 2, 4, 1, 8, 1, 2 }, 3));
		System.out.println(min.minProduct1(new int[] { 1, 5, 9, 8, 2, 4, 1, 8, 1, 2 }, 2));
	}

}
