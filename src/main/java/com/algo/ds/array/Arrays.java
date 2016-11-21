package com.algo.ds.array;

public class Arrays {

	public static void main(String[] args) {
		Arrays arrays = new Arrays();

		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		arrays.reverse(a);
		arrays.maxArray(a);

		int[] b = { 3, 4, 1, 2, 5, 6, 6, 9 };
		System.out.println(arrays.unique2(b));

	}

	public void reverse(int[] a) {
		int l = a.length;
		// reverse elements within array.
		for (int i = 0; i < l / 2; i++) {
			int temp = a[i];
			a[i] = a[l - 1 - i];
			a[l - 1 - i] = temp;
		}

		// display
		for (int i = 0; i < l; i++) {
			System.out.print(a[i] + "\n");
		}
	}

	/**
	 * runs in O(n) time.
	 * 
	 * @param arr
	 */
	public void maxArray(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		System.out.println(max);
	}

	/**
	 * 
	 * 2 loops. run in O(n^2)
	 */
	public boolean unique1(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j])
					return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * final time taken is O(n log n) + O(n) ==> O(n log n)
	 */
	public boolean unique2(int[] arr) {
		int[] tempArr = java.util.Arrays.copyOf(arr, arr.length);
		// first sort. best is O(n log n) time
		java.util.Arrays.sort(tempArr);
		// then compare in O(n) time.. 1 loop
		for (int i = 0; i < tempArr.length - 1; i++) {
			if (tempArr[i] == tempArr[i + 1])
				return false;
		}
		return true;
	}

	/**
	 * takes quadriatic time..O(n2)
	 * 
	 * @param arr
	 */
	public void prefixAverage1(long[] arr) {
		int[] newArr = new int[arr.length]; // O(n) time
		for (int i = 0; i < arr.length; i++) { // O(n) time
			int total = 0;
			for (int j = 0; j <= i; j++)
				total += arr[j]; // O(n2) as this happens for 1,2,..n =>
									// n(n+1)/2
			newArr[i] = total / (i + 1);
		}
	}

	/**
	 * takes linear time.. O(n)
	 * 
	 * @param arr
	 */
	public void prefixAverage2(long[] arr) {
		int[] newArr = new int[arr.length]; // O(n)
		int total = 0; // O(1)
		for (int i = 0; i < arr.length; i++) { // O(n)
			total += arr[i];
			newArr[i] = total / (i + 1);
		}
	}

}
