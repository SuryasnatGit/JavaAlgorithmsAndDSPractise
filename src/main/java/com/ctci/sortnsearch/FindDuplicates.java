
package com.ctci.sortnsearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FindDuplicates:Youhaveanarraywithallthenumbersfrom1toN,whereNisatmost32,000.The array may have duplicate entries and
 * you do not know what N is. With only 4 kilobytes of memory available, how would you print all duplicate elements in
 * the array?
 * 
 * dups([1, 2, 3]) = []
 * 
 * dups([1, 2, 2]) = [2]
 * 
 * dups([3, 3, 3]) = [3]
 * 
 * dups([2, 1, 2, 1]) = [1, 2]
 */
public class FindDuplicates {

	// Return a list of duplicates in the array. To avoid using extra space,
	// we flag which elements we've seen before by negating the value at
	// indexed at that value in the array.
	public List<Integer> findDuplicates(int[] arr) {
		// Use a set for results to avoid duplicates
		Set<Integer> resultSet = new HashSet<Integer>();

		for (int i = 0; i < arr.length; i++) {
			// Translate the value into an index (1 <= x <= len(arr))
			int index = Math.abs(arr[i]) - 1;

			// If the value at that index is negative, then we've already seen
			// that value so it's a duplicate. Otherwise, negate the value at
			// that index so we know we've seen it
			if (arr[index] < 0) {
				resultSet.add(Math.abs(arr[i]));
			} else {
				arr[index] = -arr[index];
			}
		}

		// Return the array to it's original state
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Math.abs(arr[i]);
		}

		return new ArrayList<Integer>(resultSet);
	}

	public static void main(String[] args) {
		FindDuplicates fd = new FindDuplicates();
		fd.findDuplicates(new int[] { 1, 2, 3 }).forEach(a -> System.out.print(a + " "));
		System.out.println();
		fd.findDuplicates(new int[] { 1, 2, 2 }).forEach(a -> System.out.print(a + " "));
		System.out.println();
		fd.findDuplicates(new int[] { 3, 3, 3 }).forEach(a -> System.out.print(a + " "));
		System.out.println();
		fd.findDuplicates(new int[] { 2, 1, 2, 1 }).forEach(a -> System.out.print(a + " "));
	}
}
