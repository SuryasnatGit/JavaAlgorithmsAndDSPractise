package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a sorted array arr, two integers k and x, find the k closest elements to x in the array. The result should also
 * be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * 
 * Example 1:
 * 
 * Input: arr = [1,2,3,4,5], k = 4, x = 3 Output: [1,2,3,4] Example 2:
 * 
 * Input: arr = [1,2,3,4,5], k = 4, x = -1 Output: [1,2,3,4]
 * 
 * Constraints:
 * 
 * 1 <= k <= arr.length <br/>
 * 1 <= arr.length <= 10^4 <br/>
 * Absolute value of elements in the array and x will not exceed 104
 * 
 * Category : Medium
 *
 */
public class FindKClosestElements {

	public List<Integer> findClosestElements(int[] arr, int k, int x) {

		int left = 0, right = arr.length - 1;

		while (left + 1 < right) { // very imp.
			int mid = left + (right - left) / 2;
			if (arr[mid] <= x) {
				left = mid;
			} else {
				right = mid;
			}
		}

		List<Integer> result = new ArrayList<>();

		while (result.size() < k && left >= 0 && right < arr.length) {
			if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
				result.add(arr[left]);
				left--;
			} else {
				result.add(arr[right]);
				right++;
			}
		}

		while (result.size() < k && left >= 0) {
			result.add(arr[left]);
			left--;
		}

		while (result.size() < k && right < arr.length) {
			result.add(arr[right]);
			right++;
		}

		Collections.sort(result);

		return result;
	}

	public static void main(String[] args) {
		FindKClosestElements find = new FindKClosestElements();
		System.out.println(find.findClosestElements(new int[] { 1, 2, 3, 4, 5 }, 4, 3));
		System.out.println(find.findClosestElements(new int[] { 1, 2, 3, 4, 5 }, 4, -1));
	}
}
