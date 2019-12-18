package com.algo.ds.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bucket sort, also known as bin sort, is a distribution sort that works by arranging elements into several ‘buckets’
 * which are then sorted using another sort, typically insertion sort, and merged into a sorted list.
 * 
 * Time Complexity: Worst case - O(n^2) Best case - O(n + k) Average case - O(n + k)
 * 
 * Space complexity: Worst case - O(n + k) auxiliary. where n is the number of elements to be sorted and k is the number
 * of buckets.
 * 
 * When it's fast: Bucket sort’s best case occurs when the data being sorted can be distributed between the buckets
 * perfectly. If the values are sparsely allocated over the possible value range, a larger bucket size is better since
 * the buckets will likely be more evenly distributed. The inverse of this is also true; a densely allocated array like
 * performs best when buckets are as small as possible. Bucket sort is an ideal algorithm choice when:
 * 
 * a. The additional O(n + k)O(n+k) memory usage is not an issue <br/>
 * b. Elements are expected to be fairly evenly distributed
 * 
 * When it’s slow: Bucket sort performs at its worst, O(n^2), when all elements at allocated to the same bucket. Since
 * individual buckets are sorted using another algorithm, if only a single bucket needs to be sorted, bucket sort will
 * take on the complexity of the inner sorting algorithm.
 * 
 * This depends on the individual implementation though and can be mitigated. For example a bucket sort algorithm could
 * be made to work with large bucket sizes by using insertion sort on small buckets (due to its low overhead), and merge
 * sort or quicksort on larger buckets.
 * 
 * @author surya
 *
 */
public class BucketSort {

	public void sort(int[] arr, int bucketSize) {
		if (arr.length == 0)
			return;

		int min = 0;
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
			else if (arr[i] > max)
				max = arr[i];
		}

		int bucketCount = (max - min) / bucketSize + 1;
		List<List<Integer>> buckets = new ArrayList<>();
		// create buckets
		for (int i = 0; i < bucketCount; i++) {
			buckets.add(new ArrayList<Integer>());
		}

		// populate buckets
		for (int i = 0; i < arr.length; i++) {
			buckets.get((arr[i] - min) / bucketSize).add(arr[i]);
		}

		// go through the buckets, sort individual bucket and merge sorted list to array
		int c = 0;
		for (int i = 0; i < buckets.size(); i++) {
			List<Integer> list = buckets.get(i);
			int[] intarr = new int[list.size()];
			for (int j = 0; j < list.size(); j++) {
				intarr[j] = list.get(j);
			}
			InsertionSort is = new InsertionSort();
			is.sort_iterative(intarr);
			for (int j = 0; j < intarr.length; j++) {
				arr[c++] = intarr[j];
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 5, 3, 1, 7, 4, 10 };
		System.out.println("input :" + Arrays.toString(arr));
		BucketSort bs = new BucketSort();
		bs.sort(arr, 5);
		System.out.println("output :" + Arrays.toString(arr));
	}

}