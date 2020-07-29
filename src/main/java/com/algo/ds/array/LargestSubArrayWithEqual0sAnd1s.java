package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/ <br/>
 * Test cases :<br/>
 * Starting with either 0 or 1 <br/>
 * Maximum length of 0 1 2 or more.
 * 
 * <br/>
 * Time Complexity: O(n) Auxiliary Space: O(n)
 * 
 * Category : Hard
 * 
 */
public class LargestSubArrayWithEqual0sAnd1s {

	public int equalNumber(int arr[]) {

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
		int arr[] = { 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0 };
		LargestSubArrayWithEqual0sAnd1s mse = new LargestSubArrayWithEqual0sAnd1s();
		System.out.println(mse.equalNumber(arr));
	}

}
