package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Category : Medium
 */
public class SubArraySumProblems {

	/**
	 * Problem : Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous
	 * subarray of which the sum ≥ s. If there isn't one, return 0 instead.
	 * 
	 * Example:
	 * 
	 * Input: s = 7, nums = [2,3,1,2,4,3] Output: 2 Explanation: the subarray [4,3] has the minimal length under the
	 * problem constraint. Follow up: If you have figured out the O(n) solution, try coding another solution of which
	 * the time complexity is O(n log n).
	 * 
	 */
	// T - O(n)
	public int smallestSubarraySumLength(int[] arr, int sum) {
		int windowSum = 0;
		int length = Integer.MAX_VALUE;

		int left = 0;
		for (int right = 0; right < arr.length; right++) {
			windowSum += arr[right];
			while (windowSum >= sum && left <= right) {
				length = Math.min(length, right - left + 1);
				// remove element from window left side till it becomes stable egain
				windowSum -= arr[left];
				// move left
				left++;
			}
		}

		return length == Integer.MAX_VALUE ? -1 : length;
	}

	// T - O(N LogN) S - O(N).
	// TODO: not working need to check.
	public int smallestSubarraySumLength1(int[] arr, int sum) {
		// Stores the frequency of prefix sums in the array
		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();

		mp.put(arr[0], 0);

		for (int i = 1; i < arr.length; i++) {
			arr[i] = arr[i] + arr[i - 1];
			mp.put(arr[i], i);
		}

		// Initialize len as Integer.MAX_VALUE
		int len = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {
			// If sum of array till i-th index is less than K
			if (arr[i] < sum) {
				// No possible subarray exists till i-th index
				continue;
			} else {

				// Find the exceeded value
				int x = sum - arr[i];

				// If exceeded value is zero
				if (x == 0) {
					len = Math.min(len, i);
				}

				if (mp.containsValue(x)) {
					continue;
				} else {
					len = Math.min(len, i);
				}
			}
		}

		return len == Integer.MAX_VALUE ? -1 : len;
	}

	/**
	 * Problem : Find the number of fragments of an array such that sum is equals to 0 (that is, pairs(P,Q) such that
	 * P'<"Q and the sum A[P]+A[P+1]...+A[Q] equals to 0). T - O(n) S - O(n)
	 * 
	 */
	public int numberOfSubarraysWithZeroSum(int[] a) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();

		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (a[i] == 0)
				count++;
			if (map.containsKey(-a[i])) {
				count++;
			} else {
				map.put(sum, sum);
			}
		}

		if (sum == 0)
			count++;

		return count;
	}

	/**
	 * Problem : Function to print all sub-arrays with 0 sum present in the given array
	 * 
	 * @param A
	 */
	public void printallSubarraysWithZeroSum(int[] A) {
		// create an empty Multi-map to store ending index of all
		// sub-arrays having same sum
		Map<Integer, List<Integer>> hashMap = new HashMap<>();

		// insert (0, -1) pair into the map to handle the case when
		// sub-array with 0 sum starts from index 0
		insert(hashMap, 0, -1);

		int sum = 0;

		// traverse the given array
		for (int i = 0; i < A.length; i++) {
			// sum of elements so far
			sum += A[i];

			// if sum is seen before, there exists at-least one
			// sub-array with 0 sum
			if (hashMap.containsKey(sum)) {
				List<Integer> list = hashMap.get(sum);

				// find all sub-arrays with same sum
				for (Integer value : list) {
					System.out.println("Subarray [" + (value + 1) + ".." + i + "]");
				}
			}

			// insert (sum so far, current index) pair into the Multi-map
			insert(hashMap, sum, i);
		}
	}

	// Utility function to insert <key, value> into the Multimap
	private void insert(Map<Integer, List<Integer>> hashMap, Integer key, Integer value) {
		// if the key is seen for the first time, initialize the list
		if (!hashMap.containsKey(key)) {
			hashMap.put(key, new ArrayList<>());
		}

		hashMap.get(key).add(value);
	}

	/**
	 * Problem : Function to check if sub-array with given sum exists in the array or not. This works for both positive
	 * and negative integers.
	 * 
	 * T - O(n) S - O(n)
	 * 
	 */
	public void checkSubArrayWithGivenSumExists(int[] A, int sum) {
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

	/**
	 * Problem : Given an array of integers and an integer k, you need to find the total number of continuous subarrays
	 * whose sum equals to k.
	 * 
	 * Example 1:
	 * 
	 * Input:nums = [1,1,1], k = 2 Output: 2
	 * 
	 * T - O(n) S - O(n)
	 */
	public int numberOfSubArraysWithKSum(int[] nums, int k) {
		int currSum = 0, count = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			currSum += nums[i];

			if (currSum == k) {
				count++;
			}

			if (map.containsKey(currSum - k)) {
				count += map.get(currSum - k);
			}

			Integer c = map.get(currSum);
			map.put(currSum, c == null ? 1 : c + 1);
		}

		return count;
	}

	/**
	 * Problem : Find the contiguous subarray within an array (containing at least one number) that has the largest sum.
	 * For example, given the array [2, 1, -3, 4, -1, 2, 1, -5, 4], The contiguous array [4, -1, 2, 1] has the largest
	 * sum = 6.
	 */
	/**
	 * Approach 1 - divide and conquor.
	 * 
	 * Assume we partition the array A into two smaller arrays S and T at the middle index, M. Then, S = A1 ... AM-1,
	 * and T = AM+1 ... AN.
	 * 
	 * The contiguous subarray that has the largest sum could either:
	 * 
	 * i. Contain the middle element: a. The largest sum is the maximum suffix sum of S + A[M] + the maximum prefix sum
	 * of T.
	 * 
	 * ii. Does not contain the middle element: a. The largest sum is in S, which we could apply the same algorithm to
	 * S. b. The largest sum is in T, which we could apply the same algorithm to T.
	 * 
	 * time complexity - O(n log n), Space - O(log n) stack space.
	 * 
	 * @param a
	 * @return
	 */
	public int maximumSumSubArray(int[] a) {
		return maxSumSubArray(a, 0, a.length - 1);
	}

	private int maxSumSubArray(int[] a, int left, int right) {
		if (left > right)
			return Integer.MIN_VALUE;

		int mid = left + (right - left) / 2;
		int leftAns = maxSumSubArray(a, left, mid - 1);
		int rightAns = maxSumSubArray(a, mid + 1, right);

		int leftSum = 0, maxLeftSum = 0;
		for (int i = mid - 1; i >= left; i--) {
			leftSum += a[i];
			maxLeftSum = Math.max(maxLeftSum, leftSum);
		}

		int rightSum = 0, maxRightSum = 0;
		for (int i = mid + 1; i <= right; i++) {
			rightSum += a[i];
			maxRightSum = Math.max(maxRightSum, rightSum);
		}

		return Math.max(maxLeftSum + a[mid] + maxRightSum, Math.max(leftAns, rightAns));
	}

	/**
	 * Approach 2 - dynamic programming. Time - O(n), Space - O(1)
	 * 
	 * @param a
	 * @return
	 */
	public int maximumSumSubArray1(int[] nums) {
		int maxSoFar = nums[0];
		int maxEndingHere = nums[0];
		for (int i = 1; i < nums.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

	/**
	 * Using kadane's algorithm.
	 * 
	 * Simple idea of the Kadane’s algorithm is to look for all positive contiguous segments of the array
	 * (max_ending_here is used for this). And keep track of maximum sum contiguous segment among all positive segments
	 * (max_so_far is used for this). Each time we get a positive sum compare it with max_so_far and update max_so_far
	 * if it is greater than max_so_far
	 * 
	 * 
	 * 
	 * @param arr
	 */
	public void printMaximumSumSubArray(int[] A) {
		// stores maximum sum sub-array found so far
		int maxSoFar = 0;

		// stores maximum sum of sub-array ending at current position
		int maxEndingHere = 0;

		// stores end-points of maximum sum sub-array found so far
		int start = 0, end = 0;

		// stores starting index of a positive sum sequence
		int beg = 0;

		// traverse the given array
		for (int i = 0; i < A.length; i++) {
			// update maximum sum of sub-array "ending" at index i
			maxEndingHere = maxEndingHere + A[i];

			// if maximum sum is negative, set it to 0
			if (maxEndingHere < 0) {
				maxEndingHere = 0; // empty sub-array
				beg = i + 1;
			}

			// update result if current sub-array sum is found to be greater
			if (maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
				start = beg;
				end = i;
			}
		}

		System.out.println("The sum of contiguous sub-array with the " + "largest sum is " + maxSoFar);

		System.out.print("The contiguous sub-array with the largest sum is ");
		for (int i = start; i <= end; i++) {
			System.out.print(A[i] + " ");
		}
	}

	public static void main(String[] args) {
		SubArraySumProblems sf = new SubArraySumProblems();
		System.out.println(
				"numberOfSubarraysWithZeroSum :" + sf.numberOfSubarraysWithZeroSum(new int[] { 2, -2, 3, 0, 4, -7 }));
		sf.printallSubarraysWithZeroSum(new int[] { 2, -2, 3, 0, 4, -7 });

		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, -4);
		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, -6);
		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, 2);
		sf.checkSubArrayWithGivenSumExists(new int[] { -3, -1, -5, 8 }, -8);
		sf.checkSubArrayWithGivenSumExists(new int[] { 3, -1, -5, 8 }, 5);

		System.out.println("numberOfSubArraysWithKSum :"
				+ sf.numberOfSubArraysWithKSum(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0));

		System.out.println(
				"smallestSubarraySumLength :" + sf.smallestSubarraySumLength(new int[] { 2, 3, 1, 2, 4, 3 }, 7));
		System.out.println("smallestSubarraySumLength :" + sf.smallestSubarraySumLength(new int[] { 1, 1 }, 3));
		System.out.println(
				"smallestSubarraySumLength1 :" + sf.smallestSubarraySumLength1(new int[] { 2, 3, 1, 2, 4, 3 }, 7));
		System.out.println("smallestSubarraySumLength1 :" + sf.smallestSubarraySumLength1(new int[] { 1, 1 }, 3));

		System.out.println("maximumSumSubArray :" + sf.maximumSumSubArray(new int[] { 2, 1, -3, 4, -1, 2, 1, -5, 4 }));
		System.out
				.println("maximumSumSubArray1 :" + sf.maximumSumSubArray1(new int[] { 2, 1, -3, 4, -1, 2, 1, -5, 4 }));
		sf.printMaximumSumSubArray(new int[] { 2, 1, -3, 4, -1, 2, 1, -5, 4 });
	}
}
