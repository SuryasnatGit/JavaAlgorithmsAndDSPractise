package com.algo.search;

public class CountOfTarget {

	public int count(int[] array, int target) {
		// first search for first occurance of target
		int left = findLeftOccurance(array, 0, array.length - 1, target);

		// then search for last occurance of target
		int right = findRightOccurance(array, left, array.length - 1, target);
		return right - left + 1;
	}

	private int findLeftOccurance(int[] array, int start, int end, int target) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (array[start] == target)
			return start;

		return end;
	}

	private int findRightOccurance(int[] array, int start, int end, int target) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] <= target) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (array[end] == target)
			return end;

		return start;
	}

	public static void main(String[] args) {
		CountOfTarget c = new CountOfTarget();
		int[] array = { 1, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 9, 9 };
		int target = 7;
		System.out.println(c.count(array, target));
		System.out.println(c.count(array, 9));
	}

}
