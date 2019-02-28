package com.algo.ds.array;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * You may assume nums1 and nums2 cannot be both empty.
 * 
 * Example 1:
 * 
 * nums1 = [1, 3] nums2 = [2]
 * 
 * The median is 2.0 Example 2:
 * 
 * nums1 = [1, 2] nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * @author surya
 *
 */
public class MedianOfSortedArrays {

	public static void main(String[] args) {
		int ar1[] = { 1, 12, 15, 26, 38 };
		int ar2[] = { 2, 13, 18, 30, 45 };
		MedianOfSortedArrays me = new MedianOfSortedArrays();
		System.out.println(me.findMedian(ar1, ar2));
	}

	/**
	 * (By doing binary search for the median):
	 * 
	 * 
	 * It requires constant time to check if ar2[j] <= ar1[i] <= ar2[j + 1]. If ar1[i] is not the
	 * median, then depending on whether ar1[i] is greater or less than ar2[j] and ar2[j + 1], you know
	 * that ar1[i] is either greater than or less than the median. Thus you can binary search for median
	 * in O(lg n) worst-case time. For two arrays ar1 and ar2, first do binary search in ar1[]. If you
	 * reach at the end (left or right) of the first array and don't find median, start searching in the
	 * second array ar2[].
	 * 
	 * 1) Get the middle element of ar1[] using array indexes left and right. Let index of the middle
	 * element be i.
	 * 
	 * 2) Calculate the corresponding index j of ar2[] j = n – i – 1
	 * 
	 * 3) If ar1[i] >= ar2[j] and ar1[i] <= ar2[j+1] then ar1[i] and ar2[j] are the middle elements.
	 * return average of ar2[j] and ar1[i]
	 * 
	 * 4) If ar1[i] is greater than both ar2[j] and ar2[j+1] then do binary search in left half (i.e.,
	 * arr[left ... i-1]) 5) If ar1[i] is smaller than both ar2[j] and ar2[j+1] then do binary search in
	 * right half (i.e., arr[i+1....right])
	 * 
	 * 6) If you reach at any corner of ar1[] then do binary search in ar2[]
	 * 
	 * 
	 * @param ar1
	 * @param ar2
	 * @param n
	 * @return
	 */
	public int getMedian(int ar1[], int ar2[], int n) {
		return getMedianRec(ar1, ar2, 0, n - 1, n);
	}

	/*
	 * A recursive function to get the median of ar1[] and ar2[]
	 * 
	 * using binary search
	 */

	int getMedianRec(int ar1[], int ar2[], int left, int right, int n) {

		int i, j;

		/* We have reached at the end (left or right) of ar1[] */

		if (left > right)

			return getMedianRec(ar2, ar1, 0, n - 1, n);

		i = (left + right) / 2;

		j = n - i - 1; /* Index of ar2[] */

		/* Recursion terminates here. */

		if (ar1[i] > ar2[j] && (j == n - 1 || ar1[i] <= ar2[j + 1]))

		{

			/*
			 * ar1[i] is decided as median 2, now select the median 1
			 * 
			 * (element just before ar1[i] in merged array) to get the
			 * 
			 * average of both
			 */

			if (i == 0 || ar2[j] > ar1[i - 1])

				return (ar1[i] + ar2[j]) / 2;

			else

				return (ar1[i] + ar1[i - 1]) / 2;

		}

		/* Search in left half of ar1[] */

		else if (ar1[i] > ar2[j] && j != n - 1 && ar1[i] > ar2[j + 1])

			return getMedianRec(ar1, ar2, left, i - 1, n);

		/* Search in right half of ar1[] */

		else /* ar1[i] is smaller than both ar2[j] and ar2[j+1] */

			return getMedianRec(ar1, ar2, i + 1, right, n);

	}

	public int findMedian(int[] arrA, int[] arrB) {
		if (arrA.length == 0 && arrB.length == 0)
			throw new IllegalArgumentException("Array should not be null");
		return findMedianOfArrays(arrA, 0, arrA.length - 1, arrB, 0, arrB.length - 1);
	}

	private int findMedianOfArrays(int[] arrA, int startA, int endA, int[] arrB, int startB, int endB) {
		// check for 0 length
		if (arrA.length == 0)
			return medianOfArray(arrB, startB, endB);
		if (arrB.length == 0)
			return medianOfArray(arrA, startA, endA);

		int lengthA = endA - startA + 1;
		int lengthB = endB - startB + 1;

		// check for 1 length of both arrays
		if (lengthA == 1 && lengthB == 1)
			return (arrA[startA] + arrB[startB]) / 2;

		// check for length 1 of 1 array and > 1 of other array
		if (lengthA == 1 && lengthB > 1)
			return findMedianOfArrayAndValue(arrB, startB, endB, arrA[startA]);
		if (lengthB == 1 && lengthA > 1)
			return findMedianOfArrayAndValue(arrA, startA, endA, arrB[startB]);

		// check for length 2 of arr 1 and > 2 of arr 2
		if (lengthA == 2 && lengthB >= 2 && lengthB % 2 == 0) {
			if (areVauesInMiddleOFEvenArray(arrB, startB, endB, arrA[startA], arrA[endA]))
				return (arrA[startA] + arrA[endA]) / 2;
		}

		if (lengthB == 2 && lengthA >= 2 && lengthA % 2 == 0) {
			if (areVauesInMiddleOFEvenArray(arrA, startA, endA, arrB[startB], arrB[endB]))
				return (arrB[startB] + arrB[endB]) / 2;
		}

		int medianA = medianOfArray(arrA, startA, endA);
		int medianB = medianOfArray(arrB, startB, endB);
		if (medianA == medianB)
			return medianA;

		int maxDiscardable = Math.min((endA - startA) / 2 - 1, (endB - startB) / 2 - 1);
		if (maxDiscardable < 1)
			maxDiscardable = 1;

		if (medianA < medianB)
			return findMedianOfArrays(arrA, startA + maxDiscardable, endA, arrB, startB, endB - maxDiscardable);
		return findMedianOfArrays(arrA, startA, endA - maxDiscardable, arrB, startB + maxDiscardable, endB);
	}

	/**
	 * Gets whether two values belong in sorted order in the middle of an array.
	 * 
	 * @param arr
	 * @param start
	 *            start index of arr
	 * @param end
	 *            end index of arr
	 * @param smallVal
	 * @param bigVal
	 * @return
	 */
	private boolean areVauesInMiddleOFEvenArray(int[] arr, int start, int end, int smallVal, int bigVal) {
		int midIndex = (start + end) / 2;
		return smallVal > arr[midIndex] && bigVal < arr[midIndex + 1];
	}

	/**
	 * Get median of sorted array and an additional value
	 * 
	 * @param arr
	 * @param start
	 *            start index of array
	 * @param end
	 *            end index of array
	 * @param value
	 *            single value within the array
	 * @return
	 */
	private int findMedianOfArrayAndValue(int[] arr, int start, int end, int value) {
		int median = medianOfArray(arr, start, end);
		if (median == value)
			return value;
		// if array length is even
		if ((end - start) % 2 == 1)
			return findMedianOfArrayAndValueEvenCase(arr, start, end, median, value);
		return findMedianOfArrayAndValueOddCase(arr, start, end, median, value);
	}

	/**
	 * finds median of sorted array with odd number of elements and additional value
	 * 
	 * @param arr
	 * @param start
	 *            start index of arr
	 * @param end
	 *            end index of arr
	 * @param median
	 *            median value of arr
	 * @param value
	 *            additional value
	 * @return
	 */
	private int findMedianOfArrayAndValueOddCase(int[] arr, int start, int end, int median, int value) {
		int midIndex = (start + end) / 2;
		int leftVal = arr[midIndex - 1];
		int midVal = arr[midIndex];
		int rightVal = arr[midIndex + 1];
		if (value < leftVal)
			return (leftVal + midVal) / 2;
		if (value > rightVal)
			return (midVal + rightVal) / 2;
		else
			return (value + midVal) / 2;
	}

	/**
	 * finds median of sorted array with even number of elements and additional value
	 * 
	 * @param arr
	 * @param start
	 *            start index of arr
	 * @param end
	 *            end index of arr
	 * @param median
	 *            median value in arr
	 * @param value
	 *            additional value
	 * @return
	 */
	private int findMedianOfArrayAndValueEvenCase(int[] arr, int start, int end, int median, int value) {
		if (median < value) {
			int right = arr[(end - start) / 2 + 1];
			return Math.max(right, value);
		} else {
			int left = arr[(end - start) / 2];
			return Math.max(left, value);
		}
	}

	/**
	 * Finds median value of the array
	 * 
	 * @param arr
	 * @param start
	 *            start index
	 * @param end
	 *            end index
	 * @return median value
	 */
	private int medianOfArray(int[] arr, int start, int end) {
		if (start == end)
			return arr[start];
		int mid = (start + end) / 2;
		// if array length is even
		if ((end - start) % 2 == 1)
			return (arr[mid] + arr[mid + 1]) / 2;
		return arr[mid];
	}

}
