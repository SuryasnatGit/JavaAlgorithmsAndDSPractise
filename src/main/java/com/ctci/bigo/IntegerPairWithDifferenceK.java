package com.ctci.bigo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of distinct integer values, count the number of pairs of
 * integers that have difference k. For example, given the array {1, 7, 5, 9, 2,
 * 12, 3} and the difference k = 2, there are four pairs with difference 2: (1,
 * 3), (3, 5), (5, 7), (7, 9) .
 * 
 * LeetCode 532 - K-diff Pairs in an Array
 * 
 * @author surya
 *
 */
public class IntegerPairWithDifferenceK {

	private class Pair {
		int int1;
		int int2;

		public Pair(int int1, int int2) {
			this.int1 = int1;
			this.int2 = int2;
		}

		@Override
		public String toString() {
			return "Pair [int1=" + int1 + ", int2=" + int2 + "]";
		}
	}

	/**
	 * complexity - O(N^2). bottleneck is repeated search of other side of pair
	 * which can be optimized.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public List<Pair> getPairs_bruteForce(int[] arr, int k) {
		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (Math.abs(arr[i] - arr[j]) == k) {
					Pair p = new Pair(arr[i], arr[j]);
					list.add(p);
				}
			}
		}
		return list;
	}

	/**
	 * Both step 1(sorting) - O(N log N) and step 2 - binary search is O(N log N).
	 * So until first step is optimized the complexity will still remain O(N log N)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public List<Pair> getPairs_sorting(int[] arr, int k) {
		List<Pair> list = new ArrayList<>();
		Arrays.sort(arr); // O(N log N)
		for (int i = 0; i < arr.length; i++) {
			int other = arr[i] - k;
			int res = Arrays.binarySearch(arr, other); // O(log N). this gets called N times. so total is O(N log N)
			if (res >= 0) {
				Pair p = new Pair(arr[i], other);
				list.add(p);
			}
		}
		return list;
	}

	/**
	 * get rid of step 1 (sorting) all together. how to find things quickly in
	 * unsorted array.. using hash table. overall time complexity - O(N)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public List<Pair> getPairs_hashTable(int[] arr, int k) {
		List<Pair> list = new ArrayList<>();
		Map<Integer, Boolean> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], true);
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] - k > 0 && map.get(arr[i] - k) != null && map.get(arr[i] - k)) {
				list.add(new Pair(arr[i], arr[i] - k));
			}
			if (arr[i] + k > 0 && map.get(arr[i] + k) != null && map.get(arr[i] + k)) {
				list.add(new Pair(arr[i], arr[i] + k));
			}
		}
		return list;
	}

	/**
	 * Another approach using java sliding window
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int getPairs_slidingWindow(int[] nums, int k) {
		if (k < 0 || nums.length <= 1) {
			return 0;
		}

		Arrays.sort(nums);
		int count = 0;
		int left = 0;
		int right = 1;

		while (right < nums.length) {
			int firNum = nums[left];
			int secNum = nums[right];
			// If less than k, increase the right index
			if (secNum - firNum < k) {
				right++;
			}
			// If larger than k, increase the left index
			else if (secNum - firNum > k) {
				left++;
			}
			// If equal, move left and right to next different number
			else {
				count++;
				while (left < nums.length && nums[left] == firNum) {
					left++;
				}
				while (right < nums.length && nums[right] == secNum) {
					right++;
				}

			}
			// left and right should not be the same number
			if (right == left) {
				right++;
			}
		}
		return count;
	}

	/**
	 * self balancing BST like AVL tree or Red black tree. complexity - O(N log N)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public List<Pair> getPairs_balancedBST(int[] arr, int k) {
		return null;
	}

	public static void main(String[] args) {
		IntegerPairWithDifferenceK intk = new IntegerPairWithDifferenceK();
		int[] arr = { 1, 7, 5, 9, 2, 12, 3 };
		int k = 2;
		System.out.println(intk.getPairs_bruteForce(arr, k));
		System.out.println(intk.getPairs_sorting(arr, k));
		System.out.println(intk.getPairs_hashTable(arr, k));
	}

}
