package com.algo.ds.array;

import java.util.Arrays;

/**
 * 
 *
 * Given an array of size n + 1 with elements from 1 to n. One element is duplicated multiple times. Find that element
 * in O(1) space. Array cannot be changed.
 * 
 * Note: You must not modify the array (assume the array is read only). You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2). There is only one duplicate number in the array, but it could be
 * repeated more than once.
 *
 * CTCI - Find duplicates
 * 
 * Reference https://leetcode.com/problems/find-the-duplicate-number/
 */
public class DuplicateNumberDetection {

	/**
	 * time - O(n log n). using binary search after sorting the array
	 * 
	 * @param nums
	 * @return
	 */
	public int duplicateNumber(int[] nums) {
		// base
		if (nums.length == 0 || nums.length == 1) {
			return -1;
		}

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			// do binary search for num in rest of array
			int left = i + 1;
			int right = nums.length - 1;
			while (left <= right) {
				int mid = left + (right - left) / 2;
				if (nums[mid] == num) {
					return num;
				} else if (nums[mid] < num) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}

	/**
	 * Using auxillary boolean array. time - O(n) space - O(n)
	 * 
	 * @param A
	 * @return
	 */
	public int findDuplicate(int[] A) {
		// create an visited array of size n+1
		// we can also use map instead of visited array
		boolean visited[] = new boolean[A.length + 1];

		// for each element of the array mark it as visited and
		// return the element if it is seen before
		for (int i = 0; i < A.length; i++) {
			// if element is seen before
			if (visited[A[i]]) {
				return A[i];
			}

			// mark element as visited
			visited[A[i]] = true;
		}

		// no duplicate found
		return -1;
	}

	/**
	 * time - O(n) Space - O(1). doesn't work for all cases.
	 * 
	 * @param arr
	 * @return
	 */
	public int findDuplicateUsingSum(int[] arr) {
		int length = arr.length;
		int expectedSum = length * (length - 1) / 2;
		int actualSum = 0;
		for (int num : arr) {
			actualSum += num;
		}
		return actualSum - expectedSum;
	}

	/**
	 * time - O(n) Space - O(1) doesn't work for all cases.
	 * 
	 * @param A
	 * @return
	 */
	public int findDuplicateUsingXOR(int[] A) {
		int xor = 0;

		// take xor of all array elements
		for (int i = 0; i < A.length; i++) {
			xor ^= A[i];
		}

		// take xor of numbers from 1 to n-1
		for (int i = 1; i < A.length; i++) {
			xor ^= i;
		}

		// same elements will cancel out each other as a ^ a = 0,
		// 0 ^ 0 = 0 and a ^ 0 = a

		// xor will contain the missing number
		return xor;
	}

	public static void main(String args[]) {

		DuplicateNumberDetection dd = new DuplicateNumberDetection();
		System.out.println(dd.duplicateNumber(new int[] { 1, 2, 3, 4, 5, 2 }));
		System.out.println(dd.findDuplicate(new int[] { 1, 2, 3, 4, 5, 2 }));
		System.out.println(dd.findDuplicateUsingSum(new int[] { 1, 2, 3, 4, 5, 2 }));
		System.out.println(dd.findDuplicateUsingXOR(new int[] { 1, 2, 3, 4, 5, 2 }));

		System.out.println(dd.duplicateNumber(new int[] { 2, 2, 2, 2, 2 }));
		System.out.println(dd.findDuplicate(new int[] { 2, 2, 2, 2, 2 }));
		System.out.println(dd.findDuplicateUsingSum(new int[] { 2, 2, 2, 2, 2 }));
		System.out.println(dd.findDuplicateUsingXOR(new int[] { 2, 2, 2, 2, 2 }));
	}
}
