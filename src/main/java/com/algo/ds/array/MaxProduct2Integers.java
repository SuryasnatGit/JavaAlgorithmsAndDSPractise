package com.algo.ds.array;

import java.util.Arrays;

//https://www.techiedelight.com/find-maximum-product-two-integers-array/

// https://www.techiedelight.com/find-triplet-maximum-product-array/
public class MaxProduct2Integers {

	// T - O(n^2) S - O(1)
	public int maxProduct1(int[] arr) {
		int max = 0;
		int x = 0;
		int y = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] * arr[j] > max) {
					max = arr[i] * arr[j];
					x = i;
					y = j;
				}
			}
		}
		System.out.println("x=" + x + " y=" + y);
		return max;
	}

	// T - O(n log n)
	public int maxProduct2(int[] arr) {
		Arrays.sort(arr);
		int max = 0;
		// max will be first 2 or last 2 integers in array
		int n = arr.length;
		if ((arr[0] * arr[1]) > (arr[n - 1] * arr[n - 2])) {
			max = arr[0] * arr[1];
		} else {
			max = arr[n - 1] * arr[n - 2];
		}
		return max;
	}

	// T - O(n)
	public int maxProduct3(int[] arr) {
		// we are only worried about min, 2nd min, max and 2nd max to decide the max product of 2 integers
		int min1 = arr[0], min2 = Integer.MAX_VALUE;
		int max1 = arr[0], max2 = Integer.MIN_VALUE;

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max1) {
				max2 = max1;
				max1 = arr[i];
			} else if (arr[i] > max2) {
				max2 = arr[i];
			}

			if (arr[i] < min1) {
				min2 = min1;
				min1 = arr[2];
			} else if (arr[i] < min2) {
				min2 = arr[i];
			}
		}

		int prod = 0;
		if (min1 * min2 < max1 * max2) {
			prod = max1 * max2;
		} else {
			prod = min1 * min2;
		}
		return prod;
	}

	public static void main(String[] args) {
		MaxProduct2Integers max = new MaxProduct2Integers();
		int[] arr = { -10, -3, 5, 7, -2 };
		System.out.println(max.maxProduct1(arr));
		System.out.println(max.maxProduct2(arr));
		System.out.println(max.maxProduct3(arr));
	}

}
