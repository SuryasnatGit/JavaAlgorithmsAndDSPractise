package com.algo.ds.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific
 * target.
 * 
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 * 
 * Example:
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 * 
 * @author surya
 *
 */
public class TwoSum {

	/**
	 * O(n2) runtime, O(1) space – Brute force: The brute force approach is simple. Loop through each
	 * element x and find if there is another value that equals to target – x. As finding another value
	 * requires looping through the rest of array, its runtime complexity is O(n2).
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_bruteForce(int[] arr, int n) {
		int[] res = new int[2];
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] + arr[j] == n) {
					res[0] = i;
					res[1] = j;
				}
			}
		}
		return res;
	}

	/**
	 * O(n) runtime, O(n) space – Hash table: We could reduce the runtime complexity of looking up a
	 * value to O(1) using a hash map that maps a value to its index.
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_hashTable(int[] arr, int n) {
		int[] res = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int x = arr[i];
			if (map.containsKey(n - x)) {
				res[0] = map.get(n - x) + 1;
				res[1] = i + 1;
			}
			map.put(x, i);
		}
		return res;
	}

	/**
	 * Of course we could still apply the [Hash table] approach, but it costs us O(n) extra space, plus
	 * it does not make use of the fact that the input is already sorted.
	 * 
	 * <br/>
	 * O(n log n) runtime, O(1) space – Binary search: For each element x, we could look up if target –
	 * x exists in O(log n) time by applying binary search over the sorted array. Total runtime
	 * complexity is O(n log n).
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_binarySearch(int[] arr, int n) {
		for (int i = 0; i < arr.length; i++) { // n times log n
			int j = binarySearch(arr, n - arr[i], i + 1);
			if (j != -1) {
				return new int[] { i + 1, j + 1 };
			}
		}
		throw new IllegalArgumentException("no 2 sum solution");
	}

	private int binarySearch(int[] arr, int key, int start) {
		int left = start;
		int right = arr.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (arr[mid] < key) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return (left == right && arr[left] == key) ? left : -1;
	}

	/**
	 * O(n) runtime, O(1) space –
	 * 
	 * Two pointers: Let’s assume we have two indices pointing to the ith and jth elements, Ai and Aj
	 * respectively. The sum of Ai and Aj could only fall into one of these three possibilities: i. Ai +
	 * Aj > target. Increasing i isn’t going to help us, as it makes the sum even bigger. Therefore we
	 * should decrement j. ii. Ai + Aj < target. Decreasing j isn’t going to help us, as it makes the
	 * sum even smaller. Therefore we should increment i. iii. Ai + Aj == target. We have found the
	 * answer.
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_2pointers(int[] arr, int n) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum < n) {
				left++;
			} else if (sum > n) {
				right--;
			} else {
				return new int[] { left + 1, right + 1 };
			}
		}
		throw new IllegalArgumentException("no 2 sum solution");
	}

	public static void main(String[] args) {
		int[] a = new int[] { 2, 7, 11, 15 };
		TwoSum two = new TwoSum();
		Arrays.stream(two.twoSum_binarySearch(a, 17)).forEach(new IntConsumer() {

			@Override
			public void accept(int value) {
				System.out.println(value);

			}
		});
	}

}
