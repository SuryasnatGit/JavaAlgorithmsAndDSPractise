package com.companyprep.amazon;

import java.util.Arrays;

/**
 * Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the
 * array, find the sum of the element inside the window at each moving.
 * 
 * For array [1,2,7,8,5], moving window size k = 3. <br/>
 * 1 + 2 + 7 = 10 <br/>
 * 2 + 7 + 8 = 17 <br/>
 * 7 + 8 + 5 = 20 <br/>
 * return [10,17,20]
 */
public class WindowSum {

	public int[] sum(int[] array, int k) {
		// check for null
		if (array == null || array.length == 0)
			return new int[] {};

		int[] result = new int[array.length - k + 1];

		// check for length
		if (array.length < k)
			return result;

		int sum = 0, left = 0, right = k;
		for (int i = 0; i < k; i++) {
			sum += array[i];
		}

		while (right < array.length) {
			result[right - k] = sum;
			sum = sum - array[left] + array[right];
			left++;
			right++;
		}

		// for last element
		result[right - k] = sum;

		return result;
	}

	public static void main(String[] args) {
		WindowSum ws = new WindowSum();
		System.out.println(Arrays.toString(ws.sum(new int[] { 1, 2, 7, 8, 5 }, 3)));
	}

}
