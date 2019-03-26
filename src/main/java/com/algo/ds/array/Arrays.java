
package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Arrays {

	public static void main(String[] args) {

		Arrays arrays = new Arrays();
		// String s = "the dog is black";
		// System.out.println(s);
		// System.out.println(arrays.reverseWordsinString1(s.toCharArray()));
		// System.out.println(arrays.reverseWordsInString2(s));
		/*
		 * int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; arrays.reverse(a);
		 * arrays.maxArray(a); int[] b = { 3, 4, 1, 2, 5, 6, 6, 9 };
		 * System.out.println(arrays.unique2(b));
		 */

		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		// int[] intarr = new int[n];
		// for (int i = 0; i < n; i++) {
		// intarr[i] = sc.nextInt();
		// }
		// for (int j = n - 1; j >= 0; j--) {
		// System.out.print(intarr[j] + " ");
		// }

		// int ar1[] = { 1, 12, 15, 26, 38, 47 };
		// int ar2[] = { 2, 13, 18, 30, 45, 53 };
		//
		// int n1 = ar1.length;
		// int n2 = ar2.length;
		// if (n1 == n2) {
		// System.out.println("Median is " + arrays.medianOfSortedArrays1(ar1, ar2,
		// n1));
		// System.out.println("Median is " + arrays.medianOfSortedArrays2(ar1, ar2));
		// } else
		// System.out.println("arrays are of unequal size");

		// System.out.println(arrays.kthLargestElementInArray(ar1, 4));
		// System.out.println(arrays.kthLargestElementInArray1(ar2, 4));
		// System.out.println(arrays.kthLargestElementInArray2(ar2, 1));

		Interval i1 = arrays.new Interval(1, 3);
		Interval i2 = arrays.new Interval(2, 6);
		Interval i3 = arrays.new Interval(8, 10);
		Interval i4 = arrays.new Interval(15, 18);
		List<Interval> list = new ArrayList<>();
		list.add(i3);
		list.add(i2);
		list.add(i1);
		list.add(i4);

		System.out.println("Output ->" + arrays.mergeIntervals(list));
		System.out.println("Merged ->" + arrays.insertInterval(list, arrays.new Interval(4, 16)));

		int[] arr = new int[] { 2, 7, 11, 15 };
		int[] twoSum = arrays.twoSum_2(arr, 19);
		for (int i = 0; i < twoSum.length; i++)
			System.out.println(twoSum[i]);

//		int[] input = { 1, 2, 3, 4, 5 };
//		arrays.waveArray(input);

		int[] input = { 10, 3, 5, 6, 2 };
		arrays.productArrayPuzzle(input);
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

	/**
	 * rotate int arr by k steps. using intermediate array takes O(n) space and O(n)
	 * time
	 * 
	 * @param arr
	 * @param steps
	 */
	public void rotateArray1(int[] arr, int k) {
		if (k > arr.length)
			k = k % arr.length;

		int[] tempArr = new int[arr.length];
		for (int i = 0; i < k; i++) {
			tempArr[i] = arr[arr.length - k + i];
		}
		int j = 0;
		for (int i = k; i < arr.length; i++) {
			tempArr[i] = arr[j];
			j++;
		}
		System.arraycopy(tempArr, 0, arr, 0, arr.length);
	}

	/**
	 * using bubble sort, it takes O(1) space but O(n * k) time where n = number of
	 * elements in array and k is number of steps
	 * 
	 * @param arr
	 * @param k
	 */
	public void rotateArray2(int[] arr, int k) {
		if (arr == null || k < 0 || arr.length == 0)
			throw new IllegalArgumentException("invalid input");

		for (int i = 0; i < k; i++) {
			for (int j = arr.length - 1; j > 0; j--) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		}
	}

	/**
	 * first split the array in 2 halfs. reverse first half reverse second half
	 * reverse whole array takes O(1) space and O(n) time
	 * 
	 * @param arr
	 * @param k
	 */
	public void rotateArray3(int[] arr, int k) {
		if (arr == null || k < 0 || arr.length == 0)
			throw new IllegalArgumentException("invalid input");

		// first half
		reverse(arr, 0, k - 1);
		// second half
		reverse(arr, k, arr.length - 1);
		// whole array
		reverse(arr, 0, arr.length - 1);
	}

	private void reverse(int[] arr, int start, int end) {
		if (arr == null || arr.length == 1)
			return;

		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * reverse words in string with extra space allocation
	 * 
	 * @param s
	 * @return
	 */
	public String reverseWordsinString1(char[] s) {
		int i = 0;
		for (int j = 0; j < s.length; j++) {
			if (s[j] == ' ') {
				reverse(s, i, j - 1);
				System.out.println("Step 0:" + new String(s));
				i = j + 1;
			}
		}

		reverse(s, i, s.length - 1);
		System.out.println("Step 1:" + new String(s));

		reverse(s, 0, s.length - 1);
		System.out.println("Step 2:" + new String(s));

		return new String(s);
	}

	public void reverse(char[] s, int i, int j) {
		while (i < j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}

	public String reverseWordsInString2(String s) {
		String[] split = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = split.length - 1; i >= 0; i--) {
			sb.append(split[i] + " ");
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}

	/**
	 * Question: There are 2 sorted arrays A and B of size n each. Write an
	 * algorithm to find the median of the array obtained after merging the above 2
	 * arrays(i.e. array of length 2n). The complexity should be O(log(n)) Method 1
	 * (Simply count while Merging) Use merge procedure of merge sort. Keep track of
	 * count while comparing elements of two arrays. If count becomes n(For 2n
	 * elements), we have reached the median. Take the average of the elements at
	 * indexes n-1 and n in the merged array. See the below implementation. Time
	 * Complexity: O(n)
	 * 
	 * @param ar1
	 * @param ar2
	 * @param n
	 * @return
	 */
	public int medianOfSortedArrays1(int[] ar1, int[] ar2, int n) {
		int i = 0, j = 0; // counters for ar1 and ar2
		int count; // to loop through n
		int m1 = -1, m2 = -1; // median of ar1 and ar2

		for (count = 0; count <= n; count++) {
			// if all elements of ar1 is < smallest or first element of ar2
			if (i == n) {
				m1 = m2;
				m2 = ar2[0];
				break;
			}
			// if all elements of ar2 is < smallest or first element of ar1
			if (j == n) {
				m1 = m2;
				m2 = ar1[0];
				break;
			}
			if (ar1[i] < ar2[j]) {
				// store the previous median
				m1 = m2;
				m2 = ar1[i];
				i++;
			} else {
				// store the previous median
				m1 = m2;
				m2 = ar2[j];
				j++;
			}
		}
		return (m1 + m2) / 2;
	}

	/**
	 * Complexity - O(logN)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int medianOfSortedArrays2(int[] a, int[] b) {
		int m = a.length;
		int n = b.length;
		int mid = (m + n) / 2;
		if (mid % 2 != 0)
			return medianOfKth(a, b, mid, 0, m - 1, 0, n - 1); // odd
		else
			return (medianOfKth(a, b, mid, 0, m - 1, 0, n - 1) + medianOfKth(a, b, mid - 1, 0, m - 1, 0, n - 1)) / 2; // even

	}

	private int medianOfKth(int[] a, int[] b, int k, int startA, int endA, int startB, int endB) {
		int lenA = endA - startA + 1;
		int lenB = endB - startB + 1;
		// special cases
		if (lenA == 0)
			return b[startB + k];
		if (lenB == 0)
			return a[startA + k];
		if (k == 0)
			return a[startA] < b[startB] ? a[startA] : b[startB];

		int midA = (lenA * k) / (lenA + lenB);
		int midB = k - midA - 1;

		// make midA and miidB array index
		midA = midA + startA;
		midB = midB + startB;

		if (a[midA] > b[midB]) {
			k = k - (midB - startB + 1);
			endA = midA;
			startB = midB + 1;
		} else {
			k = k - (midA - startA + 1);
			endB = midB;
			startA = midA + 1;
		}
		return medianOfKth(a, b, k, startA, endA, startB, endB);
	}

	/**
	 * complexity - O(nlogn)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthLargestElementInArray(int[] arr, int k) {
		java.util.Arrays.sort(arr);
		return arr[arr.length - k];
	}

	/**
	 * using quick select which is similar to quicksort. Average case time is O(n),
	 * worst case time is O(n^2).
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthLargestElementInArray1(int[] arr, int k) {
		if (k == 0 || arr == null)
			return -1;
		return getKthElement(arr, arr.length - k + 1, 0, arr.length - 1);
	}

	private int getKthElement(int[] arr, int k, int start, int end) {
		int pivot = arr[end];
		int left = start;
		int right = end;
		while (true) {
			while (arr[left] < pivot && left < right)
				left++;
			while (arr[right] >= pivot && right > left)
				right--;
			if (left == right)
				break;
			swap(arr, left, right);
		}
		swap(arr, left, end);

		if (k == left + 1)
			return pivot;
		if (k < left + 1)
			return getKthElement(arr, k, start, left - 1);
		else
			return getKthElement(arr, k, left + 1, end);
	}

	/**
	 * 
	 * @param arr input array
	 * @param left left index
	 * @param right right index
	 */
	private void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;

	}

	/**
	 * We can use a min heap to solve this problem. The heap stores the top k
	 * elements. Whenever the size is greater than k, delete the min. Time
	 * complexity is O(nlog(k)). Space complexity is O(k) for storing the top k
	 * numbers.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int kthLargestElementInArray2(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(k);
		for (int i = 0; i < arr.length; i++) {
			pq.offer(arr[i]);
			if (pq.size() > k)
				pq.poll();
		}
		return pq.peek();
	}

	/**
	 * Given a collection of intervals, merge all overlapping intervals. For
	 * example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
	 * Analysis The key to solve this problem is defining a Comparator first to sort
	 * the array list of Intervals.
	 * 
	 * @param input
	 * @return
	 */
	public List<Interval> mergeIntervals(List<Interval> input) {
		System.out.println("input ->" + input);
		List<Interval> result = new ArrayList<>();

		if (input == null || input.isEmpty())
			return input;

		// use comparator to sort
		Collections.sort(input, new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start)
					return i1.start - i2.start;
				else
					return i1.end - i2.end;
			}
		});

		Interval pre = input.get(0);
		for (int i = 0; i < input.size(); i++) {
			Interval current = input.get(i);
			if (current.start > pre.end) {
				result.add(pre);
				pre = current;
			} else {
				Interval merge = new Interval(pre.start, Math.max(pre.end, current.end));
				pre = merge;
			}
		}
		result.add(pre);

		return result;
	}

	/**
	 * Given a set of non-overlapping & sorted intervals, insert a new interval into
	 * the intervals (merge if necessary). Example 1: Given intervals [1,3],[6,9],
	 * insert and merge [2,5] in as [1,5],[6,9]. Example 2: Given
	 * [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
	 * [1,2],[3,10],[12,16]. This is because the new interval [4,9] overlaps with
	 * [3,5],[6,7],[8,10]. Thoughts of This Problem Quickly summarize 3 cases.
	 * Whenever there is intersection, created a new interval.
	 * 
	 * @param input
	 * @param newInterval
	 * @return
	 */
	public List<Interval> insertInterval(List<Interval> input, Interval newInterval) {
		List<Interval> result = new ArrayList<>();
		for (Interval current : input) {
			if (current.end < newInterval.start)
				result.add(current);
			else if (current.start > newInterval.end) {
				result.add(newInterval);
				newInterval = current;
			} else if (current.end >= newInterval.start || current.start <= newInterval.end)
				newInterval = new Interval(Math.min(current.start, newInterval.start),
						Math.max(current.end, newInterval.end));
		}
		result.add(newInterval);
		return result;
	}

	public class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Interval [start=" + start + ", end=" + end + "]";
		}

	}

	/**
	 * Given an array of integers, find two numbers such that they add up to a
	 * specific target number. The function twoSum should return indices of the two
	 * numbers such that they add up to the target, where index1 must be less than
	 * index2. Please note that your returned answers (both index1 and index2) are
	 * not zero-based. For example: Input: numbers={2, 7, 11, 15}, target=9 Output:
	 * index1=0, index2=1 Java Solution The optimal solution to solve this problem
	 * is using a HashMap. For each element of the array, (target-nums[i]) and the
	 * index are stored in the HashMap.
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	public int[] twoSum_1(int[] arr, int target) {
		if (arr == null || arr.length < 2)
			return new int[] { 0, 0 };

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i]))
				return new int[] { map.get(arr[i]), i };
			else
				map.put(target - arr[i], i);
		}
		return new int[] { 0, 0 };
	}

	/**
	 * Input array is sorted. To solve this problem, we can use two points to scan
	 * the array from both sides. See Java solution below:
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	public int[] twoSum_2(int[] arr, int target) {
		if (arr == null || arr.length < 2)
			return new int[] { 0, 0 };

		int i = 0;
		int j = arr.length - 1;
		while (i < j) {
			int sum = arr[i] + arr[j];
			if (sum < target)
				i++;
			else if (sum > target)
				j--;
			else
				return new int[] { i, j };
		}
		return new int[] { 0, 0 };
	}

	/**
	 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
	 * Note: You may assume that A has enough space to hold additional elements from
	 * B. The number of elements initialized in A and B are m and n respectively.
	 * Analysis The key to solve this problem is moving element of A and B
	 * backwards. If B has some elements left after A is done, also need to handle
	 * that case.
	 * 
	 * @param a
	 * @param m
	 * @param b
	 * @param n
	 */
	public void mergeSortedArrays(int[] a, int m, int[] b, int n) {
		while (m > 0 && n > 0) {
			if (a[m - 1] > b[n - 1]) {
				a[m + n - 1] = a[m - 1];
				m--;
			} else {
				a[m + n - 1] = b[n - 1];
				n--;
			}
		}

		while (n > 0) {
			a[m + n - 1] = b[n - 1];
			n--;
		}
	}

	/**
	 * O(N) time complexity
	 */
	public void printArrayinInReverse() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] intarr = new int[n];
		for (int i = 0; i < n; i++) {
			intarr[i] = sc.nextInt();
		}
		for (int j = n - 1; j >= 0; j--) {
			System.out.print(intarr[j] + " ");
		}
	}

	/**
	 * Given an array arr[] of integers, find out the difference between any two
	 * elements such that larger element appears after the smaller number in arr[].
	 * Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8
	 * (Diff between 10 and 2). If array is [ 7, 9, 5, 6, 3, 2 ] then returned value
	 * should be 2 (Diff between 7 and 9)<br/>
	 * Time complexity - O(n^2) <br/>
	 * Space complexity - O(1)
	 * 
	 * @param arr
	 * @param arr_size
	 * @return
	 */
	public int maxDiff(int arr[], int arr_size) {
		boolean maxDiffConditionSatisfied = false;
		boolean maxDiffConditionNotSatisfied = false;
		int max_diff = arr[1] - arr[0];
		int i, j;
		for (i = 0; i < arr_size; i++) {
			for (j = i + 1; j < arr_size; j++) {
				if (arr[j] - arr[i] > max_diff) {
					max_diff = arr[j] - arr[i];
					maxDiffConditionSatisfied = true;
				} else {
					maxDiffConditionNotSatisfied = true;
				}
			}
		}
		return maxDiffConditionNotSatisfied && !maxDiffConditionSatisfied ? -1 : max_diff;
	}

	/**
	 * In this method, instead of taking difference of the picked element with every
	 * other element, we take the difference with the minimum element found so far.
	 * So we need to keep track of 2 things: 1) Maximum difference found so far
	 * (max_diff). 2) Minimum number visited so far (min_element). <br/>
	 * Time Complexity: O(n) <br/>
	 * Auxiliary Space: O(1)
	 * 
	 * @param arr
	 * @param arr_size
	 * @return
	 */
	public int maxDiff_1(int[] arr, int arr_size) {
		int maxDiff = arr[1] - arr[0];
		int minElem = arr[0];
		for (int i = 1; i < arr_size; i++) {
			if (arr[i] - minElem > maxDiff)
				maxDiff = arr[i] - minElem;
			if (arr[i] < minElem)
				minElem = arr[i];
		}
		return maxDiff;
	}

	/**
	 * We can modify the above method to work in O(1) extra space. Instead of
	 * creating an auxiliary array, we can calculate diff and max sum in same loop.
	 * Following is the space optimized version.<br/>
	 * Time Complexity: O(n)<br/>
	 * Auxiliary Space: O(1)
	 * 
	 * @param arr
	 * @param arr_size
	 * @return
	 */
	public int maxDiff_2(int[] arr, int arr_size) {
		int diff = arr[1] - arr[0];
		int currSum = diff;
		int maxSum = currSum;
		for (int i = 1; i < arr_size - 1; i++) {
			diff = arr[i + 1] - arr[i];
			if (currSum > 0)
				currSum += diff;
			else
				currSum = diff;

			if (currSum > maxSum)
				maxSum = currSum;
		}
		return maxSum;
	}

	/**
	 * O(R x C) where R = # of rows and C = # of columns or O(N^2) if R and C are
	 * same.
	 */
	public void maxHourglassSum() {
		// 6 x 6 array
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[][] { { 0, 6, -7, 1, 6, 3 }, { -8, 2, 8, 3, -2, 7 }, { -3, 3, -6, -3, 0, -6 },
				{ 5, 0, 5, -1, -5, 2 }, { 6, 2, 8, 1, 3, 0 }, { 8, 5, 0, 4, -7, 4 } };
		// input
		// for (int i = 0; i < 6; i++) {
		// for (int j = 0; j < 6; j++) {
		// arr[i][j] = sc.nextInt();
		// }
		// }
		sc.close();
		// print for verification
		// for (int i = 0; i < 6; i++) {
		// StringBuffer sb = new StringBuffer();
		// for (int j = 0; j < 6; j++) {
		// sb.append(arr[i][j] + " ");
		// }
		// System.out.println(sb.toString());
		// }

		int offset = 2;// width of hour glass
		int sum = 0;
		int maxsum = Integer.MIN_VALUE; // very important to initialize to min int value
		for (int i = 0; i < 4; i++) { // 4 = N - offset
			for (int j = 0; j < 4; j++) {
				sum = 0;
				for (int c = j; c <= j + offset; c++) {
					sum += arr[i][c];
					sum += arr[i + offset][c];
				}
				sum += arr[i + 1][j + 1]; // middle element of hour glass
				System.out.println("sum :" + sum);
				maxsum = Math.max(sum, maxsum);
			}
		}
		System.out.println("max sum :" + maxsum);

	}

	/**
	 * Given a sorted array of integers, write a method to remove the duplicates. Do
	 * not use any classes from the java.util package, or the equivalent library for
	 * your language. Example: [ 1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17 ] -> [ 1,
	 * 2, 3, 4, 10, 13, 15, 17 ] This solution has a limitation that after removing
	 * the duplicates it places 0(default value) for the empty spaces.
	 * 
	 * @param values
	 * @return
	 */
	int[] question1(final int[] values) {
		int len = values.length;
		// making use of temp array
		int[] tempArr = new int[len];
		int j = 0;
		for (int i = 0; i < len - 1; i++) {
			// if curr element is not equal to next element then store the curr element
			if (values[i] != values[i + 1])
				tempArr[j++] = values[i];
		}
		// storing last element in temp arr
		tempArr[j++] = values[len - 1];

		return tempArr;
	}

	/**
	 * one approach to remove the 0 is calculate number of duplicates and make the
	 * temp arr with size equals to (values.len - number of duuplicates)
	 * 
	 * @param values
	 * @return
	 */
	int[] question1_correct(final int[] values) {

		int len = values.length;

		// calculate the number of duplicates
		int c = 0;
		for (int i = 0; i < len - 1; i++) {
			if (values[i] == values[i + 1])
				c++;
		}
		// make temp arr
		int[] tempArr = new int[len - c];
		int j = 0;
		for (int i = 0; i < len - 1; i++) {
			// if curr element is not equal to next element then store the curr element
			if (values[i] != values[i + 1])
				tempArr[j++] = values[i];
		}
		// storing last element in temp arr
		tempArr[j++] = values[len - 1];

		return tempArr;
	}

	List<Integer> question1(final List<Integer> values) {
		Set<Integer> set = new HashSet<>();
		set.addAll(values);
		return new ArrayList<>(set);
	}

	/**
	 * Given a sorted array arr[] of non-repeating integers without duplicates. Sort
	 * the array into a wave-like array and return it. In other words, arrange the
	 * elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5.....
	 * (considering the increasing lexicographical order).
	 * 
	 * @param input
	 */
	public void waveArray(int[] input) {
		System.out.println(java.util.Arrays.toString(input));
		for (int i = 0; i < input.length - 1; i += 2) {
			swap(input, i, i + 1);
		}
		System.out.println(java.util.Arrays.toString(input));
	}

	/**
	 * Given an array A of size N, construct a Product Array P (of same size) such
	 * that P is equal to the product of all the elements of A except A[i].
	 * 
	 * 
	 * @param input
	 */
	public void productArrayPuzzle(int[] input) {
		System.out.println(java.util.Arrays.toString(input));
		int l = input.length;
		int[] productArr = new int[l];
		int product = 1;
		for (int i = 0; i < l; i++) {
			product *= input[i];
		}
		for (int i = 0; i < l; i++) {
			productArr[i] = product / input[i];
		}
		System.out.println(java.util.Arrays.toString(productArr));
	}
}
