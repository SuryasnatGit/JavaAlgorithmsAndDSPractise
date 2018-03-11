package com.algo.ds.sorting;

/**
 * As we know, the single pivot quick sort takes a pivot from one of the ends of
 * the array and partitioning the array, so that all elements are left to the
 * pivot are less than or equal to the pivot, and all elements that are right to
 * the pivot are greater than the pivot.
 * 
 * The idea of dual pivot quick sort is to take two pivots, one in the left end
 * of the array and the second, in the right end of the array. The left pivot
 * must be less than or equal to the right pivot, so we swap them if necessary.
 * Then, we begin partitioning the array into three parts: in the first part,
 * all elements will be less than the left pivot, in the second part all
 * elements will be greater or equal to the left pivot and also will be less
 * than or equal to the right pivot, and in the third part all elements will be
 * greater than the right pivot. Then, we shift the two pivots to their
 * appropriate positions as we see in the below bar, and after that we begin
 * quicksorting these three parts recursively, using this method.
 *
 * Dual pivot quick sort is a little bit faster than the original single pivot
 * quicksort. But still, the worst case will remain O(n^2) when the array is
 * already sorted in an increasing or decreasing order.
 * 
 * 
 * @author surya
 *
 */
public class DualPivotQuickSort {

	// quicksort the array a[] using dual-pivot quicksort
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
		assert isSorted(a);
	}

	// quicksort the subarray a[lo .. hi] using dual-pivot quicksort
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;

		// make sure a[lo] <= a[hi]
		if (less(a[hi], a[lo]))
			exch(a, lo, hi);

		int lt = lo + 1, gt = hi - 1;
		int i = lo + 1;
		while (i <= gt) {
			if (less(a[i], a[lo]))
				exch(a, lt++, i++);
			else if (less(a[hi], a[i]))
				exch(a, i, gt--);
			else
				i++;
		}
		exch(a, lo, --lt);
		exch(a, hi, ++gt);

		// recursively sort three subarrays
		sort(a, lo, lt - 1);
		if (less(a[lt], a[gt]))
			sort(a, lt + 1, gt - 1);
		sort(a, gt + 1, hi);

		assert isSorted(a, lo, hi);
	}

	/***************************************************************************
	 * Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/***************************************************************************
	 * Check if array is sorted - useful for debugging.
	 ***************************************************************************/
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

}
