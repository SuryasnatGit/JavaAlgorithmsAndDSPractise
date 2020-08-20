package com.algo.ds.array;

/**
 * http://stackoverflow.com/questions/4686823/given-2-sorted-arrays-of-integers-find-the-nth-largest-number-in-sublinear-time
 * http://articles.leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html.
 * 
 * Given two sorted arrays of size m and n respectively, you are tasked with finding the element that would be at the
 * kth position of the final sorted array.
 * 
 * Examples:
 * 
 * Input : Array 1 - 2 3 6 7 9 Array 2 - 1 4 8 10 k = 5 Output : 6 Explanation: The final sorted array would be - 1, 2,
 * 3, 4, 6, 7, 8, 9, 10 The 5th element of this array is 6. Input : Array 1 - 100 112 256 349 770 Array 2 - 72 86 113
 * 119 265 445 892 k = 7 Output : 256 Explanation: Final sorted array is - 72, 86, 100, 112, 113, 119, 256, 265, 349,
 * 445, 770, 892 7th element of this array is 256.
 * 
 * Category : Medium
 */
public class KthLargestInTwoSortedArray {

	/**
	 * Approach 1 - SImple. merge 2 sorted arrays and find the kth element from the merged array.
	 * 
	 * time complexity - O(max(m,n)). Space complexity - O(m + n)
	 *
	 */
	public int findKthLargestElement(int[] arr1, int[] arr2, int k) {
		int m = 0, n = 0, c = 0;
		int l = arr1.length + arr2.length;

		// temp array
		int[] result = new int[l];
		while (m < arr1.length && n < arr2.length) {
			if (arr1[m] < arr2[n])
				result[c++] = arr1[m++];
			else
				result[c++] = arr2[n++];
		}
		// leftovers from arr1 and arr2
		while (m < arr1.length)
			result[c++] = arr1[m++];
		while (n < arr2.length)
			result[c++] = arr2[n++];

		return result[l - k]; // for smallest use k-1
	}

	// T - O(k) S - O(1). NOT WORKING..
	public int findKthLargestElementBetter(int[] arr1, int[] arr2, int k) {
		int m = 0, n = 0, c = 0;
		int l = arr1.length + arr2.length;

		while (m < arr1.length && n < arr2.length) {
			if (arr1[m] < arr2[n]) {
				c++;
				if (c == l - k - 1) {
					return arr1[m];
				}
				m++;
			} else {
				c++;
				if (c == l - k - 1) {
					return arr2[n];
				}
				n++;
			}
		}

		while (m < arr1.length) {
			c++;
			if (c == l - k - 1) {
				return arr1[m];
			}
			m++;
		}

		while (n < arr2.length) {
			c++;
			if (c == l - k - 1) {
				return arr2[n];
			}
			n++;
		}

		return -1;
	}

	/**
	 * Approach 2 -NOT WORKING. TO CHECK LATER . Divide and concqur. Using a modified binary search.
	 * 
	 * time complexity - O(log m + log n)
	 * 
	 * @param input1
	 * @param input2
	 * @param k
	 * @return
	 */
	public int kthLargestBinarySearch(int input1[], int input2[], int k) {
		return kthLargest(input1, input2, 0, input1.length - 1, 0, input2.length - 1, k);
	}

	private int kthLargest(int input1[], int input2[], int low1, int high1, int low2, int high2, int k) {
		if (low1 > high1) {
			return input2[k - 1];
		}
		if (low2 > high2) {
			return input1[k - 1];
		}

		if ((high1 - low1 + 1) + (high2 - low2 + 1) == k) { // if k is at the end
			return Math.max(input1[high1], input2[high2]);
		}

		if (k == 1) { // if k is at the beginning
			return Math.min(input1[low1], input2[low2]);
		}

		// handle the situation where only one element is left
		// in either of array.
		if (low2 == high2 || low1 == high1) {
			if (low2 == high2) {
				if (input1[low1 + k - 1] < input2[low2]) {
					return input1[low1 + k - 1];
				} else if (input1[low1 + k - 2] > input2[low2]) {
					return input1[low1 + k - 2];
				} else {
					return input2[low2];
				}
			}
			if (low1 == high1) {
				if (input2[low2 + k - 1] < input1[low1]) {
					return input2[low2 + k - 1];
				} else if (input2[low2 + k - 2] > input1[low1]) {
					return input2[low2 + k - 2];
				} else {
					return input1[low1];
				}
			}
		}

		int m1 = (high1 + low1) / 2;
		int m2 = (high2 + low2) / 2;

		int diff1 = m1 - low1 + 1;
		int diff2 = m2 - low2 + 1;
		if (diff1 + diff2 >= k) {
			if (input1[m1] < input2[m2]) {
				return kthLargest(input1, input2, low1, high1, low2, m2, k);
			} else {
				return kthLargest(input1, input2, low1, m1, low2, high2, k);
			}
		} else {
			if (input1[m1] < input2[m2]) {
				return kthLargest(input1, input2, m1 + 1, high1, low2, high2, k - diff1);
			} else {
				return kthLargest(input1, input2, low1, high1, m2 + 1, high2, k - diff2);
			}
		}
	}

	public static void main(String args[]) {
		KthLargestInTwoSortedArray kis = new KthLargestInTwoSortedArray();
		int input1[] = { 1, 4, 7, 11, 17, 21 };
		int input2[] = { -4, -1, 3, 5, 6, 28, 35, 41, 56, 70 };
		// for (int i = 0; i < input1.length + input2.length; i++) {
		// System.out.println("****");
		// System.out.println(Arrays.toString(input1));
		// System.out.println(Arrays.toString(input2));
		// System.out.println(kis.kthLargest(input1, input2, 6));
		// System.out.println("****");
		// }

		System.out.println(kis.findKthLargestElement(new int[] { 2, 3, 6, 7, 9, 11 }, new int[] { 1, 4, 8, 10 }, 5)); // 7
		System.out.println(kis.findKthLargestElement(new int[] { 2, 3, 6, 7, 9, 11 }, new int[] { 1, 4, 8, 10 }, 6)); // 6

		System.out.println(
				kis.findKthLargestElementBetter(new int[] { 2, 3, 6, 7, 9, 11 }, new int[] { 1, 4, 8, 10 }, 5));
		System.out.println(
				kis.findKthLargestElementBetter(new int[] { 2, 3, 6, 7, 9, 11 }, new int[] { 1, 4, 8, 10 }, 6));

		System.out.println(kis.kthLargestBinarySearch(new int[] { 2, 3, 6, 7, 9, 11 }, new int[] { 1, 4, 8, 10 }, 5));
		System.out.println(kis.kthLargestBinarySearch(new int[] { 2, 3, 6, 7, 9, 11 }, new int[] { 1, 4, 8, 10 }, 6));
	}
}
