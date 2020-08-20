package com.algo.ds.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/ <br/>
 * Test cases :<br/>
 * Starting with either 0 or 1 <br/>
 * Maximum length of 0 1 2 or more.
 * 
 * <br/>
 * 
 * CTCI : 17.5
 * 
 * Category : Hard
 * 
 */
public class LargestSubArrayWithEqual0sAnd1s {

	/**
	 * T - O(N ^ 2)
	 * 
	 * @param arr
	 * @return
	 */
	public int[] equalNumber(int[] arr) {

		for (int end = arr.length - 1; end >= 0; end--) {
			for (int start = 0; start < arr.length - start; start++) {
				if (hasEqualNumber(arr, start, end)) {
					return Arrays.copyOfRange(arr, start, end);
				}
			}
		}

		return null;
	}

	private boolean hasEqualNumber(int[] arr, int start, int end) {
		int count = 0;
		for (int i = start; i < end; i++) {
			if (arr[i] == 1) {
				count++;
			} else if (arr[i] == 0) {
				count--;
			}
		}

		return count == 0;
	}

	/**
	 * T - O(n) S - O(n)
	 * 
	 */
	public int[] equalNumberOptimized(int[] arr) {
		// compute deltas between count of 0 and count of 1
		int[] deltas = computeDeltas(arr);

		// find pair in deltas with matching value and largest span
		int[] pair = largestSpanInDeltas(deltas);

		return Arrays.copyOfRange(arr, pair[0] + 1, pair[1] + 1);
	}

	/**
	 * Compute the difference between the number of 0 and 1 between the beginning of the array and each index
	 * 
	 */
	private int[] computeDeltas(int[] arr) {
		int[] deltas = new int[arr.length];

		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			// count += (arr[i] == 0) ? -1 : 1;
			if (arr[i] == 1)
				count++;
			else if (arr[i] == 0)
				count--;

			deltas[i] = count;
		}

		return deltas;
	}

	private int[] largestSpanInDeltas(int[] deltas) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		int[] pair = new int[2];

		for (int i = 0; i < deltas.length; i++) {
			if (!map.containsKey(deltas[i])) {
				map.put(deltas[i], i);
			} else {
				int index = map.get(deltas[i]);
				int distance = i - index;
				int longest = pair[1] - pair[0];
				if (distance > longest) {
					pair[0] = index;
					pair[1] = i;
				}
			}
		}

		return pair;
	}

	/**
	 * Time Complexity: O(n) Auxiliary Space: O(n)
	 * 
	 */
	public int equalNumberOptimizedCount(int arr[]) {

		Map<Integer, Integer> pos = new HashMap<Integer, Integer>();
		int maxLen = 0, count = 0;
		for (int i = 0; i < arr.length; i++) {
			count += (arr[i] == 0 ? -1 : 1);
			if (pos.containsKey(count)) {
				maxLen = Math.max(maxLen, i - pos.get(count));
			} else {
				pos.put(count, i);
			}
		}
		return maxLen;
	}

	public static void main(String args[]) {
		LargestSubArrayWithEqual0sAnd1s mse = new LargestSubArrayWithEqual0sAnd1s();
		System.out.println(Arrays.toString(mse.equalNumber(new int[] { 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0 })));
		System.out.println(Arrays.toString(mse.equalNumber(new int[] { 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0 })));
		System.out.println(mse.equalNumberOptimizedCount(new int[] { 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0 }));
		System.out.println(mse.equalNumberOptimizedCount(new int[] { 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0 }));
		System.out.println(Arrays.toString(mse.equalNumberOptimized(new int[] { 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0 })));
		System.out.println(Arrays.toString(mse.equalNumberOptimized(new int[] { 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0 })));
	}

}
