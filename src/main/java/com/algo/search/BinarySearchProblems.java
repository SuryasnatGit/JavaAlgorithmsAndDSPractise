
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

	public int searchInsertPosition(int[] array, int target) {
		int left = 0, right = array.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	/**
	 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest
	 * version of your product fails the quality check. Since each version is developed based on the previous version,
	 * all the versions after a bad version are also bad. Suppose you have n versions[1, 2, ..., n]and you want to find
	 * out the first bad one, which causes all the following ones to be bad. You are given an API bool
	 * isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad
	 * version. You should minimize the number of calls to the API.
	 * 
	 * @param n
	 * @return
	 */
	public int firstBadVersion(int n) {
		int low = 1, high = n;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (isBadVersion(mid)) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	private boolean isBadVersion(int n) {
		return true;
	}

	public static void main(String[] args) {
		BinarySearchProblems bs = new BinarySearchProblems();
		int[] arr = { 1, 3, 5, 6, 9 };
		System.out.println(bs.binarySearchRecursive(arr, 4, 0, 4));
		System.out.println(bs.binarySearchIterative(arr, 6, 0, 4));
		System.out.println(bs.searchInsertPosition(new int[] { 1, 3, 5, 6 }, 5));
		System.out.println(bs.searchInsertPosition(new int[] { 1, 3, 5, 6 }, 2));
		System.out.println(bs.searchInsertPosition(new int[] { 1, 3, 5, 6 }, 7));
		System.out.println(bs.searchInsertPosition(new int[] { 1, 3, 5, 6 }, 0));
	}
}
