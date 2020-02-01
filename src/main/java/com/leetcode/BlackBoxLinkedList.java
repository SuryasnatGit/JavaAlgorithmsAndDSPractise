package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	 * 一个数组内要是存在至少三个升序的数（array[x] < array[y] < array[z], x < y < z）就返回true // Brute Force using 3 for loops
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
