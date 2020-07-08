
package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.techiedelight.com/find-subarray-having-given-sum-given-array/
 */
public class ContiguousSubarrayWithGivenSum {

	/**
	 * Function to print sub-array having given sum using sliding window technique. This will only work for positive
	 * integers
	 * 
	 * Using sliding window technique. T - O(n) S - O(1)
	 */
	public void findSubarray(int[] A, int sum) {
		// maintains sum of current window
		int windowSum = 0;

		// maintain a window [low, high-1]
		int low = 0, high = 0;

		// consider every sub-array starting from low index
		for (low = 0; low < A.length; low++) {
			// if current window's sum is less than the given sum,
			// then add elements to current window from right
			while (windowSum < sum && high < A.length) {
				windowSum += A[high];
				high++;
			}

			// if current window's sum is equal to the given sum
			if (windowSum == sum) {
				System.out.printf("Subarray found from [%d %d]\n", low, high - 1);
				return;
			}

			// At this point the current window's sum is more than the given
			// sum remove current element (leftmost element) from the window
			windowSum -= A[low];
		}
	}

	/**
	 * Function to check if sub-array with given sum exists in the array or not. This works for both positive and
	 * negative integers.
	 * 
	 * T - O(n) S - O(n)
	 * 
	 */
	public void findSubarrayHash(int[] A, int sum) {
		int start = 0, end = -1, currSum = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < A.length; i++) {
			currSum += A[i];

			if (currSum - sum == 0) {
				start = 0;
				end = i;
				System.out.println("Subarray found from " + start + " to " + end);
				break;
			}

			if (map.containsKey(currSum - sum)) {
				start = map.get(currSum - sum) + 1;
				end = i;
				System.out.println("Subarray found from " + start + " to " + end);
				break;
			}

			map.put(currSum, i);
		}
		if (end == -1) {
			System.out.println("subarray not found");
		}
	}

	public static void main(String args[]) {
		ContiguousSubarrayWithGivenSum sgs = new ContiguousSubarrayWithGivenSum();

		sgs.findSubarray(new int[] { 2, 1, 4, 3 }, 5);

		sgs.findSubarrayHash(new int[] { -3, -1, -5, 8 }, -4);
		sgs.findSubarrayHash(new int[] { -3, -1, -5, 8 }, -6);
		sgs.findSubarrayHash(new int[] { -3, -1, -5, 8 }, 2);
		sgs.findSubarrayHash(new int[] { -3, -1, -5, 8 }, -8);
		sgs.findSubarrayHash(new int[] { 3, -1, -5, 8 }, 5);
	}
}
