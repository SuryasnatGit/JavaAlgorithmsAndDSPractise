package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		int[] nums = { 10, 22, 9, 33, 21, 50, 41, 60, 20, 100 };
		System.out.println(lis.lis_dp_naive(nums));
		System.out.println(lis.lis_recursive(nums, 8));
		System.out.println(lis.lis_binarySearch(nums));
	}

	/**
	 * time complexity - O(n^2) space - O(n)
	 * 
	 * @param nums
	 * @return
	 */
	public int lis_dp_naive(int[] nums) {
		// base condition
		if (nums == null || nums.length == 0)
			return 0;

		// prefill
		int[] max = new int[nums.length];
		Arrays.fill(max, 1);

		int result = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					max[i] = Math.max(max[i], max[j] + 1);
				}
			}
			result = Math.max(max[i], result);
		}

		return result;
	}

	/**
	 * for each num in nums if(list.size()==0) add num to list else if(num > last element in list) add num to list else
	 * replace the element in the list which is the smallest but bigger than num
	 * 
	 * @param nums
	 * @return
	 */
	public int lis_binarySearch(int[] nums) {
		// base condition
		if (nums == null || nums.length == 0)
			return 0;

		List<Integer> list = new ArrayList<>();
		for (int num : nums) {
			if (list.size() == 0 || num > list.get(list.size() - 1)) {
				list.add(num);
			} else {
				// do a binary search and replace element
				int i = 0;
				int j = list.size() - 1;
				while (i < j) {
					int mid = (i + j) / 2;
					if (list.get(mid) < num) {
						i = mid + 1;
					} else {
						j = mid;
					}
				}
				list.set(j, num);
			}
		}
		return list.size();
	}

	public int lis_recursive(int[] nums, int n) {
		if (n == 1)
			return 1;

		int res = 1, max_ending_here = 1;

		for (int i = 1; i < n; i++) {
			res = lis_recursive(nums, i);
			if (nums[i - 1] < nums[n - 1] && res + 1 > max_ending_here)
				max_ending_here = res + 1;
		}
		return max_ending_here;
	}

}
