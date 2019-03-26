package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/ Given an array of size
 * n, the array contains numbers in range from 0 to k-1 where k is a positive integer and k <= n.
 * Find the maximum repeating number in this array.
 * 
 * complexity - O(n) and O(1) extra space.
 * 
 * How does the above algorithm work? Since we use arr[i]%k as index and add value k at the index
 * arr[i]%k, the index which is equal to maximum repeating element will have the maximum value in
 * the end. Note that k is added maximum number of times at the index equal to maximum repeating
 * element and all array elements are smaller than k.
 * 
 * 
 */
public class MaxRepeatingNumber {

	public int maxRepeatingNumber(int arr[], int k) {
		for (int i = 0; i < arr.length; i++) {
			arr[arr[i] % k] += k;
		}
		// find index of max repeating element
		int maxRepeating = 0;
		int maxRepeatingIndex = 0;
		for (int i = 0; i < k; i++) {
			if (maxRepeating < arr[i]) {
				maxRepeating = arr[i];
				maxRepeatingIndex = i;
			}
		}
		// get the original array back
		for (int i = 0; i < k; i++) {
			arr[i] = arr[i] % k;
		}
		return maxRepeatingIndex;
	}

	public static void main(String args[]) {
		MaxRepeatingNumber mrn = new MaxRepeatingNumber();
		int arr[] = { 0, 2, 1, 4, 2, 3 };
		System.out.println(mrn.maxRepeatingNumber(arr, 5));
	}
}
