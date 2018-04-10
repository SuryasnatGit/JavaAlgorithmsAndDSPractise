package com.algo.ds.array;

public class UnionIntersectionOfSortedArray {

	public static void main(String[] args) {
		int arr1[] = { 1, 2, 4, 5, 6 };
		int arr2[] = { 2, 3, 5, 7 };
		int m = arr1.length;
		int n = arr2.length;
		UnionIntersectionOfSortedArray un = new UnionIntersectionOfSortedArray();
		un.union(arr1, arr2, m, n);
		un.intersection(arr1, arr2, m, n);

	}

	/**
	 * complexity - O(m + n)
	 * 
	 * @param arr1
	 * @param arr2
	 * @param m
	 * @param n
	 */
	public void union(int[] arr1, int[] arr2, int m, int n) {
		int i = 0;
		int j = 0;
		StringBuffer sb = new StringBuffer();
		while (i < m && j < n) {
			if (arr1[i] < arr2[j])
				sb.append(arr1[i++] + " ");
			else if (arr2[j] < arr1[i])
				sb.append(arr2[j++] + " ");
			else {
				sb.append(arr2[j++] + " ");
				i++;
			}
		}

		while (i < m)
			sb.append(arr1[i++] + " ");
		while (j < n)
			sb.append(arr2[j++] + " ");

		System.out.println(sb.toString());
	}

	/**
	 * complexity - O(m + n)
	 * 
	 * @param arr1
	 * @param arr2
	 * @param m
	 * @param n
	 */
	public void intersection(int[] arr1, int[] arr2, int m, int n) {
		int i = 0;
		int j = 0;
		StringBuffer sb = new StringBuffer();
		while (i < m && j < n) {
			if (arr1[i] < arr2[j])
				i++;
			else if (arr2[j] < arr1[i])
				j++;
			else {
				sb.append(arr1[i++] + " ");
				j++;
			}
		}
		System.out.println(sb.toString());
	}

	/**
	 * when difference between size of m and n is significant. loop through shorter array and do a
	 * binary search of each element of shorter array in long array. complexity - O(min(mlogn , nlogm))
	 * 
	 * @param arr1
	 * @param arr2
	 * @param m
	 * @param n
	 * @throws Exception
	 */
	public void intersection1(int[] arr1, int[] arr2, int m, int n) throws Exception {
		for (int i = 0; i < m; i++) {
			int int1 = arr1[i];
			// do binary search
			if (binarySearch(arr2, n, int1)) {

			}
		}
	}

	private boolean binarySearch(int[] arr, int n, int elem) throws Exception {
		int low = 0;
		int high = n - 1;
		while (true) {
			int mid = (low + high) / 2;
			if (arr[mid] == elem)
				return true;
			else if (low > high)
				throw new Exception("element not found");
			else if (arr[mid] < elem)
				low = mid++;
			else
				high = mid--;
		}
	}

}
