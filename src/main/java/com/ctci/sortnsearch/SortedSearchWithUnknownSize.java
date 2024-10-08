package com.ctci.sortnsearch;

import java.util.Arrays;
import java.util.List;

/**
 * CTCI - 10.4
 * 
 * Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size method. It does, however,
 * have an elementAt (i) method that returns the element at index i in 0(1) time. If i is beyond the bounds of the data
 * structure, it returns - 1. (For this reason, the data structure only supports positive integers.) Given a Listy which
 * contains sorted, positive integers, find the index at which an element x occurs. If x occurs multiple times, you may
 * return any index.
 * 
 * Complexity - O(log n)
 * 
 * Category : Medium
 * 
 */
public class SortedSearchWithUnknownSize {

	// assume this method returns the element at index i
	class Listy {
		// sample DS which contains unknown number of elements
		private List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		public int elementAt(int i) {
			return list.get(i);
		}
	}

	public int search(Listy listy, int value) {
		int index = 1;

		// increase the index until it crosses the boundary
		while (listy.elementAt(index) != -1 && listy.elementAt(index) < value) {
			index *= 2;
		}

		return binarySearch(listy, value, index / 2, index);
	}

	private int binarySearch(Listy listy, int value, int start, int end) {
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int middleValue = listy.elementAt(mid);
			if (middleValue == value) {
				return mid;
			} else if (middleValue > value) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SortedSearchWithUnknownSize s = new SortedSearchWithUnknownSize();
		System.out.println(s.search(s.new Listy(), 4));
		System.out.println(s.search(s.new Listy(), 9));
	}
}
