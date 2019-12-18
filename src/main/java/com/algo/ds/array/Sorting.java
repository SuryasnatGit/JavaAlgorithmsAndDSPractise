package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.ds.priorityqueue.DefaultComparator;

public class Sorting<K> {

	/**
	 * sorting in ascending order
	 * 
	 * @param data
	 */
	public void insertionSort(char[] data) {
		int l = data.length;
		for (int i = 1; i < l; i++) {
			char cur = data[i];
			int j = i;
			while (j > 0 && data[j - 1] > cur) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = cur;
		}
		System.out.println(Arrays.toString(data));

	}

	/**
	 * 3 step process. 1. Divide 2. Concor 3. Merge. complexity - O(n log n) For a tree T, number of nodes is 2^i for
	 * depth i. Time spent at each node is O(n/2^i). so total time at depth i is O(2^i * n/2^i) = O(n). hence for height
	 * of tree which is log n, so total sort time is O(n * log n) = O(n log n)
	 * 
	 * @param arr
	 * @param comp
	 */
	public void mergeSort_array(K[] arr, Comparator<K> comp) {
		int n = arr.length;
		// if size is 1 then its trivial, just return
		if (n < 2)
			return;

		// 1. Divide
		int mid = n / 2;
		K[] s1 = Arrays.copyOfRange(arr, 0, mid);
		K[] s2 = Arrays.copyOfRange(arr, mid, n);

		// 2. Concour
		mergeSort_array(s1, comp);
		mergeSort_array(s2, comp);

		// 3. merge
		merge(s1, s2, arr, comp);

		System.out.println(Arrays.asList(arr));
	}

	/**
	 * complexity - O(n1+n2) because number of iteration of the loop is n1 + n2
	 * 
	 * @param s1
	 * @param s2
	 * @param s
	 * @param comp
	 */
	private void merge(K[] s1, K[] s2, K[] s, Comparator<K> comp) {
		int i = 0, j = 0;
		while (i + j < s.length) {
			if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0)) {
				s[i + j] = s1[i++];
			} else {
				s[i + j] = s2[j++];
			}
		}
	}

	/**
	 * complexity - O(n log n)
	 * 
	 * @param S
	 * @param comp
	 */
	public void mergeSort_queue(Queue<K> S, Comparator<K> comp) {
		int n = S.size();
		if (n < 2)
			return;
		// 1. divide
		Queue<K> S1 = new LinkedList<>();
		Queue<K> S2 = new LinkedList<>();
		// move first n/2 elements to S1
		while (S1.size() < n / 2) {
			S1.add(S.poll());
		}
		// move next elements to S2
		while (!S.isEmpty()) {
			S2.add(S.poll());
		}

		// 2. concour
		mergeSort_queue(S1, comp);
		mergeSort_queue(S2, comp);

		// 3. merge
		merge(S1, S2, S, comp);
	}

	private void merge(Queue<K> S1, Queue<K> S2, Queue<K> S, Comparator<K> comp) {
		while (!S1.isEmpty() && !S2.isEmpty()) {
			if (comp.compare(S1.peek(), S2.peek()) < 0) {
				S.add(S1.poll());
			} else {
				S.add(S2.poll());
			}
		}
		while (!S1.isEmpty()) {
			S.add(S1.poll());
		}
		while (!S2.isEmpty()) {
			S.add(S2.poll());
		}
	}

	/**
	 * complexity - worst case - O(n^2) best case - O(n log n). this is achieved by having pivot close to the middle of
	 * the sequence. randomized quick sort - O(nlogn)
	 * 
	 * @param S
	 * @param comp
	 */
	public void quickSort_queue(Queue<K> S, Comparator<K> comp) {
		int n = S.size();
		if (n < 2)
			return;

		// 1. Divide
		K pivot = S.peek();
		Queue<K> L = new LinkedList<>(); // for < pivot
		Queue<K> E = new LinkedList<>(); // for = pivot
		Queue<K> G = new LinkedList<>(); // for > pivot
		while (!S.isEmpty()) {
			K elem = S.poll();
			int result = comp.compare(elem, pivot);
			if (result < 0) {
				L.add(elem);
			} else if (result == 0) {
				E.add(elem);
			} else {
				G.add(elem);
			}
		}

		// 2. concor -- recursive
		quickSort_queue(L, comp);// sort elements < pivot
		quickSort_queue(G, comp);// sort elements > pivot

		// 3. concatenate
		while (!L.isEmpty())
			S.add(L.poll());
		while (!E.isEmpty())
			S.add(E.poll());
		while (!G.isEmpty())
			S.add(G.poll());

		// S.display();// test
	}

	/**
	 * 
	 * @param S
	 * @param comp
	 * @param a
	 * @param b
	 */
	public void quickSortInPlace(K[] S, Comparator<K> comp, int a, int b) {
		if (a > b)
			return;
		int left = a;
		int right = b - 1;
		K pivot = S[b]; // pivot on right most side
		K temp; // used during swapping
		while (left <= right) {
			// continue until value is equal or > pivot(or right marker)
			while (left <= right && comp.compare(S[left], pivot) < 0)
				left++;
			// continue until value is equal or < pivot(or left marker)
			while (left <= right && comp.compare(S[right], pivot) > 0)
				right++;
			if (left <= right) {
				// swap values and shrink range
				temp = S[left];
				S[left] = S[right];
				S[right] = temp;
				left++;
				right--;
			}
		}
		// put pivot into final place
		temp = S[left];
		S[left] = S[b];
		S[b] = temp;

		// recursion
		quickSortInPlace(S, comp, a, left - 1);
		quickSortInPlace(S, comp, left + 1, b);

	}

	/**
	 * complexity - O(n + k)best and average where n is number of elements in S and k is number of buckets. worst case -
	 * O(n^2)
	 * 
	 * @param S
	 * @param bucketSize
	 */
	public void bucketSort(int[] S, int bucketSize) {
		if (S.length == 0)
			return;

		// initialize min and max values
		int min = S[0];
		int max = S[0];
		for (int i = 1; i < S.length; i++) {
			if (S[i] < min)
				min = S[i];
			if (S[i] > max)
				max = S[i];
		}

		// create buckets
		int bucketCount = (max - min) / bucketSize + 1; // + 1 because we started the count from 1
		List<List<Integer>> buckets = new ArrayList<>(); // buckets is the bucket collections
		for (int i = 0; i < bucketCount; i++) {
			buckets.add(new ArrayList<Integer>());
		}

		// distribute entries among the buckets
		for (int i = 0; i < S.length; i++) {
			buckets.get((S[i] - min) / bucketSize).add(S[i]);
		}

		// sort the buckets
		int count = 0;
		for (int i = 0; i < buckets.size(); i++) {
			List<Integer> list = buckets.get(i);
			Collections.sort(list);
			for (int j = 0; j < list.size(); j++) {
				S[count++] = list.get(j);
			}
		}

	}

	/**
	 * Time Complexity: O(n+k) where n is the number of elements in input array and k is the range of input. Auxiliary
	 * Space: O(n+k)
	 * 
	 * @param arr
	 */
	public void countingSort(char[] arr) {
		int n = arr.length;
		if (n < 2)
			return;

		// create output array which will contain sorted chars
		char[] output = new char[n];

		// create count array and initialize it
		char[] count = new char[256];
		for (int i = 0; i < count.length; i++)
			count[i] = 0;

		// store count of each char
		for (int i = 0; i < n; i++)
			count[arr[i]]++;

		// change count so that count now contains actual position of each char in the arr
		for (int i = 1; i < 256; i++)
			count[i] += count[i - 1];

		// form output array
		for (int i = 0; i < n; i++) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--; // decrease count
		}

		// put the sorted array to arr
		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
		System.out.println(new String(arr));

	}

	/**
	 * O((n+b) * logb(k)). where n is number of elements, b is base, k is max possible value
	 * 
	 * @param arr
	 */
	public void radixSort(int[] arr) {
		int n = arr.length;
		if (n < 2)
			return;

		// find max from arr
		int max = findMax(arr);

		for (int exp = 1; max / exp > 0; exp *= 10) {
			// create count array and initialize it
			int[] count = new int[10];
			Arrays.fill(count, 0);

			// create output array
			int[] output = new int[n];

			// store count of each int
			for (int i = 0; i < n; i++) {
				count[(arr[i] / exp) % 10]++;
			}

			// change count so that count contains actual position of the int in the arr
			for (int i = 1; i < 10; i++) {
				count[i] += count[i - 1];
			}

			// form output array
			for (int i = n - 1; i >= 0; i--) {
				output[count[(arr[i] / exp) % 10] - 1] = arr[i];
				count[(arr[i] / exp) % 10]--;
			}

			// put sorted arr to arr
			for (int i = 0; i < n; i++) {
				arr[i] = output[i];
			}

		}
		System.out.println(Arrays.toString(arr));
	}

	private int findMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		return max;
	}

	public static void main(String[] args) {
		Sorting<Integer> sort = new Sorting<>();
		Integer[] intarr = { 10, 15, 12, 17, 13 };
		// sort.mergeSort_array(intarr, new DefaultComparator<Integer>());
		Queue<Integer> S = new LinkedList<>();
		S.add(10);
		S.add(15);
		S.add(12);
		S.add(17);
		S.add(13);
		// System.out.println(Arrays.asList(intarr));
		// sort.mergeSort_queue(S, new DefaultComparator<Integer>());
		sort.quickSort_queue(S, new DefaultComparator<Integer>());
		// S.display();
		int[] ar = new int[] { 10, 15, 12, 17, 13, 21, 19 };
		sort.bucketSort(ar, 3);
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i] + " ");
		}

		sort.countingSort("suryasnat".toCharArray());
		sort.radixSort(ar);
	}

}
