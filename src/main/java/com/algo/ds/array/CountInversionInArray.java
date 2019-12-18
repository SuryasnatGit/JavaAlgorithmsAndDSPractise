package com.algo.ds.array;

/**
 * Inversion Count for an array indicates  how far (or close) the array is from being sorted. If
 * array is already sorted then inversion count is 0. If array is sorted in reverse order that
 * inversion count is the maximum. Formally speaking, two elements a[i] and a[j] form an inversion
 * if a[i] > a[j] and i < j
 * 
 * Example: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
 * 
 * @author surya
 *
 */
public class CountInversionInArray {

	/**
	 * Method 1- Simple. For each element, count number of elements which are on right side of it and
	 * are smaller than it. Complexity - O(n^2)
	 * 
	 * @param arr
	 * @return
	 */
	int getInversionCount_simple(int[] arr) {
		int len = arr.length;
		int invCount = 0;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (arr[i] > arr[j])
					invCount++;
			}
		}
		return invCount;
	}

	/**
	 * Method 2 - Using merge sort. time complexity - O(n log n). space complexity - O(n)
	 * 
	 * @param arr
	 * @return
	 */
	int getInversionCount_mergeSort(int[] arr) {
		int length = arr.length;
		int[] temp = new int[length];
		return merge(arr, temp, 0, length - 1);
	}

	private int merge(int[] arr, int[] temp, int start, int end) {
		int invCount = 0;
		if (end > start) {
			int mid = (start + end) / 2;
			invCount = merge(arr, temp, start, mid); // left merge
			invCount += merge(arr, temp, mid + 1, end); // right merge
			invCount += mergeSort(arr, temp, start, mid, end);
		}
		return invCount;
	}

	private int mergeSort(int[] arr, int[] temp, int start, int mid, int end) {
		int invCount = 0;
		int i = start;// pointer to left sub array
		int j = mid;// pointer to right sub array
		int k = start;// pointer for final sub array
		while (i <= mid - 1 && j <= end) {
			if (arr[i] < arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];

				// In merge process, let i is used for indexing left sub-array and j for right sub-array. At any
				// step in merge(), if a[i] is greater than a[j], then there are (mid  i) inversions. because left
				// and right subarrays are sorted, so all the remaining elements in left-subarray (a[i+1], a[i+2] 
				// a[mid]) will be greater than a[j]

				invCount += (mid - i);
			}
		}
		// copy remaining elements of left sub-array to temp
		while (i <= mid - 1)
			temp[k++] = arr[i++];
		// copy remaining elements of right sub-array to temp
		while (j <= end)
			temp[k++] = arr[j++];

		// reset arr
		for (i = start; i <= end; i++) {
			arr[i++] = temp[k++];
		}
		return invCount;
	}


}
