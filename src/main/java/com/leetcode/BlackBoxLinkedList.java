package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Give a linkedlist, the elements inside are sorted, but it is a blackbox, There are three functions that can be
 * called. pop () randomly pops out the first or last element, peek () randomly peeks at the front or back element,
 * isEmpty () returns whether the linkedlist is empty.
 * 
 * Ask to design a data structure, either a list or an array. Take out all the elements in the linkedlist and keep them
 * sorted. Followup is what to do if peek () cannot be used. My thinking: if I got element A, and next element B is
 * smaller than A, then A is from the tail of the list; otherwise, A is from the head of the list.
 * 
 * TODO : to understand
 *
 */
public class BlackBoxLinkedList {

	public static void main(String[] args) {
		int[] arr = { 4, 2, 1, 16, 13, 0, 9 };
		boolean res = increasing(arr);
		System.out.println(res);
	}

	List<Integer> blackList(LinkedList<Integer> black) {
		LinkedList<Integer> left = new LinkedList<Integer>();
		LinkedList<Integer> right = new LinkedList<Integer>();

		while (!black.isEmpty()) {
			Integer now = black.pop();

			if (!black.isEmpty()) {
				if (now < black.peek()) { // now is from left of black list
					left.addLast(now);
				} else { // now is from right, need to wait
					right.addFirst(now);
				}
			} else {
				left.addLast(now);
			}
		}

		left.addAll(right);

		return left;
	}

	/**
	 * 
	 * If there are at least three ascending numbers in an array (array [x] <array [y] <array [z], x <y <z) returns true
	 */
	static boolean increasing(int[] arr) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++) {
			Map.Entry<Integer, Integer> floorEntry = map.floorEntry(arr[i]);

			if (floorEntry == null) {
				map.put(arr[i], 1); // Include itself
			} else {
				int smallerCount = floorEntry.getValue();
				if (smallerCount >= 2) {
					return true;
				} else {
					map.put(arr[i], smallerCount + 1);
				}
			}
		}

		return false;
	}
}
