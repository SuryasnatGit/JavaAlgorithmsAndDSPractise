
package com.algo.search;

/**
 * The idea of binary search is to use the information that the array is sorted and reduce the time complexity to O(Log
 * n).
 * 
 * Time Complexity: The time complexity of Binary Search can be written as
 * 
 * T(n) = T(n/2) + c The above recurrence can be solved either using Recurrence T ree method or Master method. It falls
 * in case II of Master Method and solution of the recurrence is Theta(Logn).
 * 
 * Auxiliary Space: O(1) in case of iterative implementation. In case of recursive implementation, O(Logn) recursion
 * call stack space.
 * 
 * 
 */
public class BinarySearchProblems {

	public int binarySearchRecursive(int[] input, int x, int left, int right) {
		if (left <= right) {
			int mid = left + (right - left) / 2;
			if (input[mid] == x)
				return mid;
			else if (input[mid] < x)
				return binarySearchRecursive(input, x, mid + 1, right);
			else
				return binarySearchRecursive(input, x, left, mid - 1);
		}
		return -1;
	}

	public int binarySearchIterative(int[] input, int x, int left, int right) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (input[mid] == x)
				return mid;
			else if (input[mid] < x)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		BinarySearchProblems bs = new BinarySearchProblems();
		int[] arr = { 1, 3, 5, 6, 9 };
		System.out.println(bs.binarySearchRecursive(arr, 4, 0, 4));
		System.out.println(bs.binarySearchIterative(arr, 6, 0, 4));
	}
}
