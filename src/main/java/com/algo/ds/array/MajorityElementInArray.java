package com.algo.ds.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Given an array of integers containing duplicates, return the majority element in an array if present. A majority
 * element appears more than n/2 times where n is the size of the array.
 * 
 * For example, the majority element is 2 in the array {2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2}
 *
 */
public class MajorityElementInArray {

	// T - O(n) S - O(n)
	public int findMajorityHashing(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : arr) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
			if (map.get(i) > arr.length / 2) {
				return i;
			}
		}
		return -1;
	}

	// Function to return majority element present in given array. T - O(n) S - O(1)
	public int findMajorityElementOptimized(int[] A) {
		// m stores majority element if present
		int m = -1;

		// initialize counter i with 0
		int i = 0;

		// do for each element A[j] of the array
		for (int j = 0; j < A.length; j++) {
			// if the counter i becomes 0
			if (i == 0) {
				// set the current candidate to A[j]
				m = A[j];

				// reset the counter to 1
				i = 1;
			}

			// else increment the counter if A[j] is current candidate
			else if (m == A[j]) {
				i++;
			}
			// else decrement the counter if A[j] is not current candidate
			else {
				i--;
			}
		}

		return m;
	}

	/**
	 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run
	 * in linear time and in O(1) space.
	 * 
	 * Hint:
	 * 
	 * How many majority elements could it possibly have?
	 * 
	 */
	public List<Integer> majorityElement(int[] nums) {
		// There can be at most 2 elements.
		int candidate1 = 0, count1 = 0;
		int candidate2 = 0, count2 = 0;

		for (int num : nums) {
			if (num == candidate1) {
				candidate1 = num;
				count1 += 1;
			} else if (num == candidate2) {
				candidate2 = num;
				count2 += 1;
			} else if (count1 == 0) { // Equals 0 must come after
				candidate1 = num;
				count1 = 1;
			} else if (count2 == 0) {
				candidate2 = num;
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}

		// Find out the real ones, which are more than n / 3
		count1 = 0;
		count2 = 0;
		List<Integer> res = new ArrayList<Integer>();
		for (int num : nums) {
			if (num == candidate1) {
				count1++;
			} else if (num == candidate2) {
				count2++;
			}
		}

		if (count1 > nums.length / 3) {
			res.add(candidate1);
		}
		if (count2 > nums.length / 3) {
			res.add(candidate2);
		}

		return res;
	}

	/**
	 * Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the
	 * size of the array.
	 * 
	 * Find it.
	 * 
	 * Notice
	 * 
	 * There is only one majority number in the array.
	 * 
	 * Have you met this question in a real interview? Yes Example Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
	 * 
	 */
	public int majorityNumber(List<Integer> nums, int k) {
		// Maintain a hashmap to hold all possible k - 1 elements
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int num : nums) {
			if (map.containsKey(num)) { // If it is already in map, just count++
				map.put(num, map.get(num) + 1);
			} else {
				if (map.size() == k - 1) { // Map is full

					// Must use Iterator to avoid ConcurrentModificationException
					Iterator it = map.entrySet().iterator();
					while (it.hasNext()) {
						Entry thisEntry = (Entry) it.next();
						int key = (int) thisEntry.getKey();
						int value = (int) thisEntry.getValue();

						value--;
						if (value == 0) {
							// Must use iterator.remove() to avoid above exception
							// map.remove(new Integer(key));
							it.remove();
						} else {
							map.put(key, value);
						}
					}

					// for (int key : map.keySet()) {
					// int value = map.get(key);
					// value--;
					// if (value == 0) {
					// map.remove(new Integer(key));
					// } else {
					// map.put(key, value);
					// }
					// }
				} else {
					map.put(num, 1);
				}
			}
		}

		// Clear Map's value
		for (int key : map.keySet()) {
			map.put(key, 0);
		}

		// Find out the real count
		for (int num : nums) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			}
		}

		// Then determine which is the winner
		int winnerKey = 0;
		int winnerValue = 0;
		for (int key : map.keySet()) {
			int value = map.get(key);
			if (value > winnerValue) {
				winnerKey = key;
				winnerValue = value;
			}
		}

		return winnerKey;
	}

	public static void main(String[] args) {
		MajorityElementInArray maj = new MajorityElementInArray();
		int[] arr = { 2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2 };
		System.out.println(maj.findMajorityHashing(arr));
		System.out.println(maj.findMajorityElementOptimized(arr));
	}

}
