package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given a sorted array of positive integers, rearrange the array alternately i.e first element
 * should be maximum value, second minimum value, third second max, fourth second min and so on.
 *
 * http://www.geeksforgeeks.org/rearrange-array-maximum-minimum-form/
 */
public class MaximumMinimumArrangement {

	/**
	 * The idea is use an auxiliary array. We maintain two pointers one to leftmost or smallest element
	 * and other to rightmost or largest element. We move both pointers toward each other and
	 * alternatively copy elements at these pointers to an auxiliary array. Finally we copy auxiliary
	 * array back to original array.
	 * 
	 * Time: O(n) <br/>
	 * Space: O(n)
	 * 
	 * @param arr
	 */
	public void maxMinArrange(int[] arr) {
		System.out.println(Arrays.toString(arr));
		int l = arr.length;
		int[] temp = new int[l];

		int start = 0;
		int end = l - 1;
		// to keep track of whether to update largest element or smallest element
		boolean flag = true;
		for (int i = 0; i < l; i++) {
			if (flag) {
				temp[i] = arr[end--];
			} else {
				temp[i] = arr[start++];
			}
			// reverse the flag
			flag = !flag;
		}
		// copy temp to arr
		arr = temp.clone();
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Time - O(n) Space - O(1)
	 * 
	 * @param arr
	 */
	public void maxMinArrange_1(int[] arr) {
		int n = arr.length;
		int max = arr[n - 1];
		int min = arr[0];

		for (int i = 0; i < n; i++) {
			// for even index put max element
			if (i % 2 == 0) {
				arr[i] = max;
				max -= 1;
			} else {
				// for odd element put min element
				arr[i] = min;
				min += 1;
			}
		}
	}

	public static void main(String[] args) {
		MaximumMinimumArrangement mm = new MaximumMinimumArrangement();
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(Arrays.toString(arr));
		mm.maxMinArrange_1(arr);
		System.out.println(Arrays.toString(arr));
	}
}
