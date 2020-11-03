package com.ctci.moderate;

import java.util.HashSet;
import java.util.Set;

/**
 * Diving Board: You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of
 * planks, one of length shorter and one of length longer. You must use exactly K planks of wood. Write a method to
 * generate all possible lengths for the diving board.
 *
 * CTCI : 16.11
 */
public class DivingBoard {

	/**
	 * This algorithm takes O(2 ^ K) time, since there are two choices at each recursive call and we recurse to a depth
	 * ofK.
	 * 
	 */
	public Set<Integer> allLengths(int k, int shorter, int longer) {
		Set<Integer> allLengths = new HashSet<>();
		recurse(k, 0, allLengths, shorter, longer);
		return allLengths;
	}

	private void recurse(int k, int total, Set<Integer> allLengths, int shorter, int longer) {
		if (k == 0) {
			allLengths.add(total);
			return;
		}

		recurse(k - 1, total + shorter, allLengths, shorter, longer);
		recurse(k - 1, total + longer, allLengths, shorter, longer);
	}

	/**
	 * T - O(k)
	 * 
	 */
	public Set<Integer> allLengthsOptimal(int k, int shorter, int longer) {
		Set<Integer> allLengths = new HashSet<>();

		for (int i = 0; i <= k; i++) {
			int length = shorter * i + longer * (k - i);
			allLengths.add(length);
		}

		return allLengths;
	}

	public static void main(String[] args) {
		DivingBoard db = new DivingBoard();
		System.out.println(db.allLengths(5, 1, 2));
		System.out.println(db.allLengths(5, 3, 4));
		System.out.println(db.allLengthsOptimal(5, 1, 2));
		System.out.println(db.allLengthsOptimal(5, 3, 4));
	}

}
