package com.algo.ds.sorting;

/**
 * QuickSort is a Divide and Conquer algorithm. It picks an element as pivot and
 * partitions the given array around the picked pivot. There are many different
 * versions of quickSort that pick pivot in different ways.
 * 
 * <p>
 * Always pick first element as pivot. (implemented below)
 * </p>
 * <p>
 * Always pick last element as pivot
 * </p>
 * <p>
 * Pick a random element as pivot.
 * </p>
 * <p>
 * Pick median as pivot.
 * </p>
 * 
 * It works by first selecting a ‘pivot’ element, then re-ordering either side
 * of the list so that everything before the pivot is less than the pivot and
 * everything after is greater. Quicksort is then called recursively on either
 * side of the pivot.
 * 
 * Despite quicksort having a worst-case performance of O(n^2)O(n ​2 ​​ ), it is
 * sometimes regarded at the same level performance-wise as O(n \log n)O(nlogn)
 * sorts like merge sort or heapsort. This is due to its average case being
 * O(nlogn), it will often perform even better in practice than the O(nlogn)
 * sorts.
 * 
 * Time complexity: Worst case : O(n^2) Best case : O(n log n) Average case :
 * O(n log n)
 * 
 * Space complexity: Worst case : O(log n) auxiliary
 * 
 * @author Suryasnat
 *
 */
public class QuickSort {

	private void swap(int A[], int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	private int split(int A[], int low, int high) {
		int pivot = low;
		int i = low + 1;
		int j = high;
		while (i < j) {
			while (i <= j && A[pivot] >= A[i]) {
				i++;
			}
			while (j >= i && A[pivot] < A[j]) {
				j--;
			}
			if (i < j && A[i] > A[j]) {
				swap(A, i++, j--);
			}
		}
		if (A[pivot] > A[j]) {
			swap(A, j, pivot);
		}
		return j;
	}

	/**
	 * This function takes first element as pivot, places the pivot element at its correct position in sorted array, and
	 * places all smaller (smaller than pivot) to left of pivot and all greater elements to right of pivot
	 * 
	 * @param A
	 * @param low
	 * @param high
	 * @return
	 */
	private int split1(int A[], int low, int high) {

		int pivot = low;
		int i = low + 1;
		int j = high;
		while (i <= j) {

			if (A[i] <= A[pivot]) {
				i++;
				continue;
			}
			if (A[j] > A[pivot]) {
				j--;
				continue;
			}
			swap(A, i++, j--);
		}
		if (A[pivot] > A[j]) {
			swap(A, pivot, j);
			return j;
		}
		return pivot;

	}

	public void sort(int A[], int low, int high) {
		if (low >= high) {
			return;
		}
		int pos = split1(A, low, high);
		sort(A, low, pos - 1);
		sort(A, pos + 1, high);
	}

	public void threeWayQuickSort(int[] arr, int left, int right) {
		if (right <= left)
			return;

		int lt = left;
		int gt = right;
		int pivot = arr[left]; // left element as pivot
		int i = left;
		while (i <= gt) {
			if (arr[i] < pivot)
				swap(arr, lt++, i++);
			else if (arr[i] > pivot)
				swap(arr, i, gt--);
			else
				i++;
		}

		threeWayQuickSort(arr, left, lt - 1);
		threeWayQuickSort(arr, gt + 1, right);
	}

	/**
	 * To reduce the stack size, first push the indexes of smaller half.
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	public void quickSortIterative(int[] arr, int left, int right) {
		// Auxiliary stack
		int[] stack = new int[arr.length];

		// Initialize top of stack
		int top = -1;

		// push initial values to stack
		stack[++top] = left;
		stack[++top] = right;

		// continue till stack still has element
		while (top >= 0) {
			// pop items from stack
			right = stack[top--];
			left = stack[top--];

			int partition = split1(arr, left, right);

			// if elements on left of pivot present then push elements onto stack
			if (partition - 1 > left) {
				stack[++top] = left;
				stack[++top] = partition - 1;
			}

			// if elements on right of pivot present then push elements onto stack
			if (partition + 1 < right) {
				stack[++top] = partition + 1;
				stack[++top] = right;
			}
		}
	}

	private void printArray(int arr[]) {
		for (int a : arr) {
			System.out.println(a);
		}
	}

	public static void main(String args[]) {
		QuickSort qs = new QuickSort();
		// int A[] = {11,19,0,-1,5,6,16,-3,6,0,14,18,7,21,18,-6,-8};
		// int A[] = {11,9,0,4,6,-1,13};
		int A[] = { 10, 7, 8, 9, 1, 5 };
		// qs.sort(A, 0, A.length - 1);
		// qs.threeWayQuickSort(A, 0, A.length - 1);
		qs.quickSortIterative(A, 0, A.length - 1);
		qs.printArray(A);

	}
}
