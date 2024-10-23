package com.algo.ds.array;

import java.util.Arrays;

public class ArrayAddition {

	public int[] add(int arr1[], int arr2[]) {
		int l = Math.max(arr1.length, arr2.length);
		int[] result = new int[l];
		int c = 0;
		int i = arr1.length - 1;
		int j = arr2.length - 1;
		int r = 0;
		l--; // last index of result array

		while (i >= 0 && j >= 0) {
			r = arr1[i--] + arr2[j--] + c;
			c = r / 10;
			result[l--] = r % 10;
		}

		while (i >= 0) {
			r = arr1[i--] + c;
			c = r / 10;
			result[l--] = r % 10;
		}

		while (j >= 0) {
			r = arr2[j--] + c;
			c = r / 10;
			result[l--] = r % 10;
		}

		if (c != 0) {
			int[] newResult = new int[result.length + 1];
			for (int t = newResult.length - 1; t > 0; t--) {
				newResult[t] = result[t - 1];
			}
			newResult[0] = c;
			return newResult;
		}

		return result;
	}

	/**
	 * Given a non-negative number represented as an array of digits, plus one to the number.
	 * 
	 * The digits are stored such that the most significant digit is at the head of the list.
	 * 
	 * _*Example_8
	 * 
	 * Given [1,2,3] which represents 123, return [1,2,4].
	 * 
	 * Given [9,9,9] which represents 999, return [1,0,0,0].
	 * 
	 * Analysis
	 * 
	 * A more clever method is to regard +1 as the carry carries of the last digit; each time a carry is carried out
	 * carries = sum / 10;, the remaining digits aredigits[i] = sum % 10;
	 * 
	 * If it ends up being carried! = 0, indicating that a new digit is to be added, then a new array is created to fill
	 * in the calculation results.
	 * 
	 * @param nums
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		int carries = 1;
		for (int i = digits.length - 1; i >= 0 && carries > 0; i--) {
			int sum = digits[i] + carries;
			digits[i] = sum % 10;
			carries = sum / 10;
		}
		if (carries == 0) {
			return digits;
		}
		int[] result = new int[digits.length + 1];
		result[0] = 1;
		for (int i = 1; i < result.length; i++) {
			result[i] = digits[i - 1];
		}
		return result;
	}

	public static void main(String args[]) {

		int arr1[] = { 9, 9, 9, 9, 9, 9, 9 };
		int arr2[] = { 1, 6, 8, 2, 6, 7 };
		ArrayAddition aa = new ArrayAddition();
		int result[] = aa.add(arr1, arr2);
		for (int i = 0; i < result.length; i++) {
			System.out.print(" " + result[i]);
		}
		System.out.println();
		System.out.println(Arrays.toString(aa.plusOne(new int[] { 7, 5, 6 })));
		System.out.println(Arrays.toString(aa.plusOne(new int[] { 7, 5, 9 })));
	}
}
