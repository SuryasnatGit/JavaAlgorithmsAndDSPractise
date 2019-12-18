package com.algo.ds.array;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
 * 
 * Given an unsorted array of size n. Array elements are in range from 1 to n. One number from set {1, 2, n} is missing
 * and one number occurs twice in array. Find these two numbers. Examples:
 * 
 * arr[] = {3, 1, 3} Output: 2, 3 // 2 is missing and 3 occurs twice
 * 
 * arr[] = {4, 3, 6, 2, 1, 1} Output: 1, 5 // 5 is missing and 1 occurs twice
 */
public class RepeatingAndMissingNumber {

	/**
	 * Method 1 - Using sorting.
	 * 
	 * time complexity - O(n) + O( n log n) = O(n log n)
	 * 
	 * @param input
	 * @return
	 */
	public Pair findNumbers_sorting(int[] input) {
		Pair p = new Pair();
		Arrays.sort(input); // O(n log n)
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i] == input[i + 1])
				p.repeating = input[i];
			if (input[i] != i + 1)
				p.missing = i + 1;
		}
		return p;
	}

	/**
	 * Method 2 - using count array. incomplete.. not working
	 * 
	 * Time Complexity: O(n) Auxiliary Space: O(n)
	 * 
	 * @param input
	 * @return
	 */
	public Pair findNumbers_usingCountArray(int[] input) {
		Pair p = new Pair();
		int[] temp = new int[input.length + 1]; // temp has 1 more element to accommodate elements from 1 - n
		Arrays.fill(temp, 0);
		for (int i = 0; i < input.length; i++) {
			if (temp[input[i]] == 0)
				temp[input[i]] = 1;
			else if (temp[input[i]] == 1)
				p.repeating = i;
		}
		for (int i = 1; i < temp.length; i++) {
			if (temp[i] == 0)
				p.missing = i;
		}
		return p;
	}

	class Pair {
		int repeating;
		int missing;

		public String toString() {
			return "repeating :" + repeating + " missing :" + missing;
		}
	}

	/**
	 * Traverse the array. While traversing, use absolute value of every element as index and make the value at this
	 * index as negative to mark it visited. If something is already marked negative then this is the repeating element.
	 * To find missing, traverse the array again and look for a positive value.
	 * 
	 * O(n)
	 * 
	 * @param input
	 * @return
	 */
	public Pair findNumbers(int input[]) {
		Pair p = new Pair();
		for (int i = 0; i < input.length; i++) {
			if (input[Math.abs(input[i]) - 1] < 0) {
				p.repeating = Math.abs(input[i]);
			} else {
				input[Math.abs(input[i]) - 1] = -input[Math.abs(input[i]) - 1];
			}
		}

		for (int i = 0; i < input.length; i++) {
			if (input[i] < 0) {
				input[i] = -input[i];
			} else {
				p.missing = i + 1;
			}
		}
		return p;
	}

	/**
	 * Method 5 (Use XOR) Let x and y be the desired output elements. Calculate XOR of all the array elements.
	 * 
	 * xor1 = arr[0]^arr[1]^arr[2].....arr[n-1] XOR the result with all numbers from 1 to n
	 * 
	 * xor1 = xor1^1^2^.....^n In the result xor1, all elements would nullify each other except x and y. All the bits
	 * that are set in xor1 will be set in either x or y. So if we take any set bit (We have chosen the rightmost set
	 * bit in code) of xor1 and divide the elements of the array in two sets  one set of elements with same bit set and
	 * other set with same bit not set. By doing so, we will get x in one set and y in another set. Now if we do XOR of
	 * all the elements in first set, we will get x, and by doing same in other set we will get y.
	 * 
	 * @param arr
	 * @param n
	 */
	public void getTwoElements(int arr[], int n) {
		/*
		 * Will hold xor of all elements and numbers from 1 to n
		 */
		int xor1;

		/* Will have only single set bit of xor1 */
		int set_bit_no;

		int i, x = 0, y = 0;

		xor1 = arr[0];

		/* Get the xor of all array elements */
		for (i = 1; i < n; i++)
			xor1 = xor1 ^ arr[i];

		/*
		 * XOR the previous result with numbers from 1 to n
		 */
		for (i = 1; i <= n; i++)
			xor1 = xor1 ^ i;

		/* Get the rightmost set bit in set_bit_no */
		set_bit_no = xor1 & ~(xor1 - 1);

		/*
		 * Now divide elements in two sets by comparing rightmost set bit of xor1 with bit at same position in each
		 * element. Also, get XORs of two sets. The two XORs are the output elements.The following two for loops serve
		 * the purpose
		 */
		for (i = 0; i < n; i++) {
			if ((arr[i] & set_bit_no) != 0)
				/* arr[i] belongs to first set */
				x = x ^ arr[i];

			else
				/* arr[i] belongs to second set */
				y = y ^ arr[i];
		}
		for (i = 1; i <= n; i++) {
			if ((i & set_bit_no) != 0)
				/* i belongs to first set */
				x = x ^ i;

			else
				/* i belongs to second set */
				y = y ^ i;
		}

		/* *x and *y hold the desired output elements */
	}

	public static void main(String args[]) {
		RepeatingAndMissingNumber rmn = new RepeatingAndMissingNumber();
		int input[] = { 4, 3, 6, 2, 1, 1 };
		System.out.println(rmn.findNumbers(input));
		System.out.println(rmn.findNumbers_sorting(input));
		System.out.println(rmn.findNumbers_usingCountArray(input));
	}
}
