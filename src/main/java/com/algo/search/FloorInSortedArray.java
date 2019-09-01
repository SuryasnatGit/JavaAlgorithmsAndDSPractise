package com.algo.search;

/**
 * Given a sorted array and a value x, the floor of x is the largest element in
 * array smaller than or equal to x. Write efficient functions to find floor of
 * x.
 * 
 * Examples:
 * 
 * Input : arr[] = {1, 2, 8, 10, 10, 12, 19}, x = 5 Output : 2. 2 is the largest
 * element in arr[] smaller than 5.
 * 
 * Input : arr[] = {1, 2, 8, 10, 10, 12, 19}, x = 20 Output : 19. 19 is the
 * largest element in arr[] smaller than 20.
 * 
 * Input : arr[] = {1, 2, 8, 10, 10, 12, 19}, x = 0 Output : -1. Since floor
 * doesn't exist, output is -1.
 * 
 * 
 * @author M_402201
 *
 */
public class FloorInSortedArray {

	/**
	 * time complexity - O(n)
	 * 
	 * @param array
	 * @param n
	 * @return
	 */
	public int findFloorLinear(int[] array, int n) {
		int l = array.length;

		// if n is > than last element
		if (n > array[l - 1])
			return l - 1;

		// if n is < than first element
		if (n < array[0])
			return -1;

		// scan through all elements
		for (int i = 1; i < l; i++) {
			if (array[i] > n)
				return i - 1;
		}
		return -1;
	}

	/**
	 * time complexity - o(log n)
	 * 
	 * @param array
	 * @param n
	 * @param left
	 * @param right
	 * @return
	 */
	public int findFloorBinarySearch(int[] array, int n, int left, int right) {
		// base case
		if (left > right)
			return -1;

		// if n is > last element
		if (n >= array[right])
			return right;

		int mid = left + (right - left) / 2;
		if (array[mid] == n)
			return mid;

		// if n lies between mid - 1 and mid
		if (mid > 0 && array[mid - 1] < n && n < array[mid])
			return mid - 1;

		if (array[mid] < n)
			return findFloorBinarySearch(array, n, mid + 1, right);
		else
			return findFloorBinarySearch(array, n, left, mid - 1);
	}

	public static void main(String[] args) {
		FloorInSortedArray f = new FloorInSortedArray();
		System.out.println(f.findFloorLinear(new int[] { 1, 2, 8, 10, 10, 12, 19 }, 5));
		System.out.println(f.findFloorBinarySearch(new int[] { 1, 2, 8, 10, 10, 12, 19 }, 20, 0, 6));
	}

}
