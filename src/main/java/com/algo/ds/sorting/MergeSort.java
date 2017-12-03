package com.algo.ds.sorting;

/**
 * Merge Sort is a Divide and Conquer algorithm. It divides input array in two halves, calls itself for the two halves
 * and then merges the two sorted halves.
 * 
 * Time complexity of Merge Sort is O(n Log n) in all 3 cases (worst, average and best) as merge sort always divides the
 * array in two halves and take linear time to merge two halves.
 * 
 * Auxiliary Space: O(n)
 * 
 * Algorithmic Paradigm: Divide and Conquer
 * 
 * http://en.wikipedia.org/wiki/Merge_sort Test cases - 1 element - 2 element - negative numbers - already sorted -
 * reverse sorted.
 * 
 * Applications: 1. used in sorting linked list in O(nlogn) time. 2. inversion count problem 3. used in external sorting
 */
public class MergeSort {

	public void sort(int input[]) {
		sort(input, 0, input.length - 1);
	}

	private void sort(int input[], int low, int high) {
		if (low >= high) {
			return;
		}

		int middle = (low + high) / 2;
		sort(input, low, middle);
		sort(input, middle + 1, high);
		sortedMerge(input, low, high);
	}

	private void sortedMerge(int input[], int low, int high) {
		int middle = (low + high) / 2;
		int temp[] = new int[high - low + 1];
		int i = low;
		int j = middle + 1;
		int r = 0;
		while (i <= middle && j <= high) {
			if (input[i] <= input[j]) {
				temp[r++] = input[i++];
			} else {
				temp[r++] = input[j++];
			}
		}
		while (i <= middle) {
			temp[r++] = input[i++];
		}

		while (j <= high) {
			temp[r++] = input[j++];
		}
		i = low;
		for (int k = 0; k < temp.length;) {
			input[i++] = temp[k++];
		}
	}

	public void printArray(int input[]) {
		for (int i : input) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public void sort3Way(int[] arr, int low, int high) {
		if (high - low < 2)
			return;

		int mid1 = low + (high - low) / 3;
		int mid2 = low + 2 * ((high - low) / 3) + 1;
		sort3Way(arr, low, mid1);
		sort3Way(arr, mid1, mid2);
		sort3Way(arr, mid2, high);

		merge3WaySorts(arr, low, mid1, mid2, high);
	}

	private void merge3WaySorts(int[] arr, int low, int mid1, int mid2, int high) {
		int i = low;
		int j = mid1;
		int k = mid2;
		int c = 0; // index for updating temp array
		int[] temp = new int[arr.length];

		// choose smaller of the smallest in the three ranges
		while ((i <= mid1) && (j <= mid2) && (k <= high)) {
			if (arr[i] <= arr[j]) {
				if (arr[i] <= arr[k]) {
					temp[c++] = arr[i++];
				} else {
					temp[c++] = arr[k++];
				}
			} else {
				if (arr[j] <= arr[k]) {
					temp[c++] = arr[j++];
				} else {
					temp[c++] = arr[k++];
				}
			}
		}

		// case where first and second ranges have remaining values
		while ((i <= mid1) && (j <= mid2)) {
			if (arr[i] <= arr[j]) {
				temp[c++] = arr[i++];
			} else {
				temp[c++] = arr[j++];
			}
		}

		// case where second and third ranges have remaining values
		while ((j <= mid2) && (k <= high)) {
			if (arr[j] <= arr[k]) {
				temp[c++] = arr[j++];
			} else {
				temp[c++] = arr[k++];
			}
		}

		// case where first and third ranges have remaining values
		while ((i <= mid1) && (k <= high)) {
			if (arr[i] <= arr[k]) {
				temp[c++] = arr[i++];
			} else {
				temp[c++] = arr[k++];
			}
		}

		// copy remaining values from the first range
		while (i <= mid1) {
			temp[c++] = arr[i++];
		}

		// copy remaining values from the second range
		while (j <= mid2) {
			temp[c++] = arr[j++];
		}

		// copy remaining values from the third range
		while (k <= high) {
			temp[c++] = arr[k++];
		}

		// i = low;// reset for updating the original array
		// System.out.println(Arrays.toString(arr));
		// System.out.println(Arrays.toString(temp));
		for (int p = 0; p < temp.length; p++) {
			arr[p] = temp[p];
		}
	}

	public static void main(String args[]) {
		int input1[] = { 1 };
		int input2[] = { 4, 2 };
		int input3[] = { 6, 2, 9 };
		int input4[] = { 6, -1, 10, 4, 11, 14, 19, 12, 18 };
		MergeSort ms = new MergeSort();
		// ms.sort(input1);
		// ms.sort(input2);
		// ms.sort(input3);
		// ms.sort(input4);

		// ms.printArray(input1);
		// ms.printArray(input2);
		// ms.printArray(input3);
		// ms.printArray(input4);

		ms.sort3Way(input4, 0, input4.length - 1);
		ms.printArray(input4);
	}
}
