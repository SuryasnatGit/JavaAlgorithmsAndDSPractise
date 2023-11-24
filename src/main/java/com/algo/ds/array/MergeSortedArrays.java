package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of lists. Each element in the list is sorted. Sort the entire list.
 * 
 * Test cases :
 * 
 * One or more lists are empty All elements in one list are smaller than all elements in another list
 * 
 * Category : Medium
 */
public class MergeSortedArrays {

	/**
	 * Time complexity - O(n1 + n2) Time
	 * 
	 * Space complexity - O(n1 + n2). The idea is to use Merge function of Merge sort.
	 * 
	 * Create an array arr3[] of size n1 + n2. Simultaneously traverse arr1[] and arr2[]. Pick smaller of current
	 * elements in arr1[] and arr2[], copy this smaller element to next position in arr3[] and move ahead in arr3[] and
	 * the array whose element is picked. If there are are remaining elements in arr1[] or arr2[], copy them also in
	 * arr3[].
	 * 
	 */
	public void merge2SortedArrays(int[] arr1, int[] arr2) {
		int l = arr1.length + arr2.length;
		int[] result = new int[l];

		int i = 0, j = 0, k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				result[k++] = arr1[i++];
			} else {
				result[k++] = arr2[j++];
			}
		}

		while (i < arr1.length)
			result[k++] = arr1[i++];

		while (j < arr2.length)
			result[k++] = arr1[j++];

		System.out.println(Arrays.toString(result));
	}

	class Triplet implements Comparable<Triplet> {
		int val;

		@Override
		public int compareTo(Triplet o) {
			if (val <= o.val) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public List<Integer> mergeUsingHeap(List<List<Integer>> chunks) {
		List<Integer> result = new ArrayList<Integer>();
		PriorityQueue<Triplet> queue = new PriorityQueue<Triplet>();

		// my approach
		for (List<Integer> list : chunks) {
			for (Integer i : list) {
				Triplet e = new Triplet();
				e.val = i;
				queue.add(e);
			}
		}

		while (!queue.isEmpty()) {
			Triplet poll = queue.poll();
			result.add(poll.val);
		}

		return result;
	}

	public List<Integer> mergeChunksOfDifferentSize(List<List<Integer>> chunks) {
		List<Integer> result = new ArrayList<Integer>();

		int sum[] = new int[chunks.size() + 1];
		sum[0] = 0;
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + chunks.get(i - 1).size();
		}

		for (List<Integer> chunk : chunks) {
			for (Integer i : chunk) {
				result.add(i);
			}
		}
		mergeSort(result, 0, chunks.size() - 1, sum);
		return result;
	}

	private void mergeSort(List<Integer> result, int start, int end, int sum[]) {
		if (start >= end) {
			return;
		}
		int mid = (start + end) / 2;
		mergeSort(result, start, mid, sum);
		mergeSort(result, mid + 1, end, sum);
		sortedMerge(result, start, end, sum);
	}

	private void sortedMerge(List<Integer> result, int start, int end, int sum[]) {

		/**
		 * If chunks are of equal size then i = size*start to (mid+1)*size -1 j = (mid+1)*size to size*(end+1)
		 */

		int mid = (start + end) / 2;
		int i = sum[start];
		int j = sum[mid + 1];
		List<Integer> temp = new ArrayList<Integer>();
		while (i < sum[mid + 1] && j < sum[end + 1]) {
			if (result.get(i) < result.get(j)) {
				temp.add(result.get(i));
				i++;
			} else {
				temp.add(result.get(j));
				j++;
			}
		}
		while (i < sum[mid + 1]) {
			temp.add(result.get(i));
			i++;
		}
		while (j < sum[end + 1]) {
			temp.add(result.get(j));
			j++;
		}
		int index = sum[start];
		for (int k : temp) {
			result.set(index, k);
			index++;
		}
	}

	/**
	 * Given 2 sorted arrays, X[] and Y[] of m and n elements each. merge elements of X[] with elements of Y[]
	 * maintaining the sorted order. i.e fill X[] with m smallest elements and then fill Y[] with remaining elements.
	 * 
	 * converison should be done in place without using any other data structure.
	 * 
	 * example, X = {1,4,7,8,10} Y = {2,3,9}
	 * 
	 * output, X = {1,2,3,4,7} Y = {8,9,10}
	 * 
	 * T - O(m * n) S - O(1)
	 * 
	 * @param arr1
	 * @param arr2
	 */
	public void inPlaceMerge2SortedArrays(int[] arr1, int[] arr2) {
		int m = arr1.length;
		int n = arr2.length;

		for (int i = 0; i < m; i++) {
			// take each elem of arr1
			if (arr1[i] > arr2[0]) {
				// swap arr1[i] with arr2[0]
				int temp = arr1[i];
				arr1[i] = arr2[0];
				arr2[0] = temp;

				int first = arr2[0];
				// arr2[1 .. n-1] is sorted
				int j;
				for (j = 1; j < n && arr2[j] < first; j++) {
					arr2[j - 1] = arr2[j];
				}
				arr2[j - 1] = first;
			}
		}
	}

	/**
	 * Given two sorted integer arrays nums1 _and _nums2, merge _nums2 _into _nums1 _as one sorted array. Note: The
	 * number of elements initialized in nums1 and nums2 are m and n respectively. You may assume that nums1 has enough
	 * space (size that is greater or equal to m + n) to hold additional elements from nums2. Example: Input:
	 * 
	 * nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3
	 * 
	 * 
	 * Output: [1,2,2,3,5,6]
	 * 
	 * T - O(n) S - O(1)
	 */
	public void merge2SortedArrays(int[] nums1, int m, int[] nums2, int n) {
		int k = m + n - 1;
		int i = m - 1;
		int j = n - 1;

		while (i >= 0 && j >= 0) {
			if (nums2[j] > nums1[i]) {
				nums1[k--] = nums2[j--];
			} else {
				nums1[k--] = nums1[i--];
			}
		}

		while (j >= 0) {
			nums1[k--] = nums2[j--];
		}

		System.out.println(Arrays.toString(nums1));
	}

	public static void main(String args[]) {
		Integer arr1[] = { 1, 5, 6, 9, 21 };
		Integer arr2[] = { 4, 6, 11, 14 };
		Integer arr3[] = { -1, 0, 7 };
		Integer arr4[] = { -4, -2, 11, 14, 18 };
		Integer arr5[] = { 2, 6 };
		Integer arr6[] = { -5, -2, 1, 5, 7, 11, 14 };
		Integer arr7[] = { -6, -1, 0, 15, 17, 22, 24 };

		List<Integer> list1 = Arrays.asList(arr1);
		List<Integer> list2 = Arrays.asList(arr2);
		List<Integer> list3 = Arrays.asList(arr3);
		List<Integer> list4 = Arrays.asList(arr4);
		List<Integer> list5 = Arrays.asList(arr5);
		List<Integer> list6 = Arrays.asList(arr6);
		List<Integer> list7 = Arrays.asList(arr7);

		List<List<Integer>> chunks = new ArrayList<List<Integer>>();
		chunks.add(list1);
		chunks.add(list2);
		chunks.add(list3);
		chunks.add(list4);
		chunks.add(list5);
		chunks.add(list6);
		chunks.add(list7);

		MergeSortedArrays cm = new MergeSortedArrays();
		// List<Integer> result = cm.mergeChunksOfDifferentSize(chunks);
		// System.out.println(result.size());
		// for(Integer r : result){
		// System.out.print(r + " ");
		// }
		//
		// result = cm.mergeUsingHeap(chunks);
		// System.out.println();
		// for(Integer r : result){
		// System.out.print(r + " ");
		// }

		int ar1[] = { 1, 5, 6, 9, 21 };
		int ar2[] = { 4, 6, 11, 14 };
		// cm.merge2SortedArrays(ar1, ar2);
		//
		// cm.inPlaceMerge2SortedArrays(ar1, ar2);
		// System.out.println(Arrays.toString(ar1));
		// System.out.println(Arrays.toString(ar2));

		cm.merge2SortedArrays(new int[] { 1, 2, 0, 0, 0, 0, 0 }, 2, new int[] { 4, 5, 6, 7, 8 }, 5);
		cm.merge2SortedArrays(new int[] { 1, 2, 3, 0, 0, 0, 0 }, 3, new int[] { 2, 4, 6 }, 3);
	}
}
