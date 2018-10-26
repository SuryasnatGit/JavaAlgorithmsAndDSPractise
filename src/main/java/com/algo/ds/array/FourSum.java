package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array S of n integers, there are elements a, b, c, and d in S such that a + b + c + d =
 * target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Time complexity O(n^3) Space complexity O(1).
 * 
 * Example:
 * 
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 * A solution set is: [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 *
 * Reference https://leetcode.com/problems/4sum/
 */
public class FourSum {

	public List<List<Integer>> fourSum_1(int[] nums, int target) {
		int k = 4;
		List<List<Integer>> list = new ArrayList<>();
		if (nums.length < k)
			return list;
		Arrays.sort(nums);
		list = kSum(k, nums, target);
		return list;
	}

	private List<List<Integer>> kSum(int k, int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		if (nums.length < k)
			return list;
		if (k == 2) {
			int lo = 0, hi = nums.length - 1;
			while (lo < hi) {
				if (nums[lo] + nums[hi] == target) {
					list.add(new ArrayList<>(Arrays.asList(nums[lo], nums[hi])));
					while (lo < hi && nums[lo] == nums[lo + 1])
						lo++;
					while (lo < hi && nums[hi] == nums[hi - 1])
						hi--;
					lo++;
					hi--;
				} else if (nums[lo] + nums[hi] < target)
					lo++;
				else
					hi--;
			}
		} else {
			for (int i = 0; i < nums.length - k + 1; i++) {
				if (i == 0 || nums[i] != nums[i - 1]) {
					List<List<Integer>> tmp = kSum(k - 1, Arrays.copyOfRange(nums, i + 1, nums.length),
							target - nums[i]);
					for (List<Integer> l : tmp) {
						l.add(nums[i]);
						list.add(l);
					}
				}
			}
		}
		return list;
	}

	/**
	 * Easier to understand. O(n^3)
	 * 
	 * @param num
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum_2(int[] num, int target) {
		ArrayList<List<Integer>> ans = new ArrayList<>();

		if (num.length < 4)
			return ans;

		Arrays.sort(num);

		for (int i = 0; i < num.length - 3; i++) {
			if (num[i] + num[i + 1] + num[i + 2] + num[i + 3] > target)
				break; // first candidate too large, search finished

			if (num[i] + num[num.length - 1] + num[num.length - 2] + num[num.length - 3] < target)
				continue; // first candidate too small

			if (i > 0 && num[i] == num[i - 1])
				continue; // prevents duplicate result in ans list

			for (int j = i + 1; j < num.length - 2; j++) {
				if (num[i] + num[j] + num[j + 1] + num[j + 2] > target)
					break; // second candidate too large

				if (num[i] + num[j] + num[num.length - 1] + num[num.length - 2] < target)
					continue; // second candidate too small

				if (j > i + 1 && num[j] == num[j - 1])
					continue; // prevents duplicate results in ans list

				int low = j + 1, high = num.length - 1;

				while (low < high) {
					int sum = num[i] + num[j] + num[low] + num[high];
					if (sum == target) {
						ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
						while (low < high && num[low] == num[low + 1])
							low++; // skipping over duplicate on low
						while (low < high && num[high] == num[high - 1])
							high--; // skipping over duplicate on high
						low++;
						high--;
					}
					// move window
					else if (sum < target)
						low++;
					else
						high--;
				}
			}
		}
		return ans;

	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		if (nums.length < 4) {
			return Collections.emptyList();
		}
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < nums.length - 3; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) { // prevents duplicate result in result list
				continue;
			}
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) { // first candidate too large, search
																				// finished
				break;
			}
			if (nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) { // first
																											// candidate
																											// too small
				continue;
			}
			for (int j = i + 1; j < nums.length - 2; j++) {
				if (j != i + 1 && nums[j] == nums[j - 1]) { // prevents duplicate result in result list
					continue;
				}
				if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) { // second candidate too large
					break;
				}
				if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) { // second candidate
																									// too small
					continue;
				}
				int low = j + 1;
				int high = nums.length - 1;
				while (low < high) {
					if (low != j + 1 && nums[low] == nums[low - 1]) {
						low++;
						continue;
					}
					if (high != nums.length - 1 && nums[high] == nums[high + 1]) {
						high--;
						continue;
					}

					int sum = nums[i] + nums[j] + nums[low] + nums[high];
					if (sum == target) {
						List<Integer> r = new ArrayList<>();
						r.add(nums[i]);
						r.add(nums[j]);
						r.add(nums[low]);
						r.add(nums[high]);
						result.add(r);
						low++;
						high--;
					} else if (sum < target) {
						low++;
					} else {
						high--;
					}
				}
			}
		}
		return result;
	}

	public static void main(String args[]) {
		int[] nums = { 1, 1, 4, 5, 9, 11 };
		int[] nums1 = { 1, 0, -1, 0, -2, 2, 2, -2, 2, -2 };
		int target = 0;
		FourSum fourSum = new FourSum();
		List<List<Integer>> result = fourSum.fourSum_2(nums1, target);
		result.forEach(System.out::print);
	}
}
