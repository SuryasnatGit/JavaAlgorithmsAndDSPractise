
package com.algo.ds.array;

import java.util.HashSet;
import java.util.Set;

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
				System.out.printf("Subarray found [%d-%d]\n", low, high - 1);
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
	public boolean findSubarray1(int[] A, int sum) {
		// create an empty set
		Set<Integer> set = new HashSet<>();

		// insert number 0 into the set to handle the case when
		// sub-array with given sum starts from index 0
		set.add(0);

		// maintains sum of elements so far
		int sum_so_far = 0;

		// traverse the given array
		for (int i : A) {
			// update sum_so_far
			sum_so_far += i;

			// if (sum_so_far - sum) is seen before, we have found
			// the sub-array with sum 'sum'
			if (set.contains(sum_so_far - sum)) {
				return true;
			}

			// else insert sum of elements so far into the set
			set.add(sum_so_far);
		}

		// we reach here when no sub-array exists
		return false;
	}

	public static void main(String args[]) {
		ContiguousSubarrayWithGivenSum sgs = new ContiguousSubarrayWithGivenSum();
		int input[] = { 6, 3, 9, 11, 1, 3, 5 };

		System.out.println(sgs.findSubarray1(new int[] { -3, -1, -5, 8 }, -4));
		System.out.println(sgs.findSubarray1(new int[] { -3, -1, -5, 8 }, -6));
		System.out.println(sgs.findSubarray1(new int[] { -3, -1, -5, 8 }, 2));
		System.out.println(sgs.findSubarray1(new int[] { -3, -1, -5, 8 }, -8));
		System.out.println(sgs.findSubarray1(new int[] { 3, -1, -5, 8 }, 5));
	}
}
