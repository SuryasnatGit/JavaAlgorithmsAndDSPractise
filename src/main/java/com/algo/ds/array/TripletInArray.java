package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/.
 * 
 * Given an array and a value, find if there is a triplet in array whose sum is equal to the given
 * value. If there is such a triplet present in array, then print the triplet and return true. Else
 * return false. For example, if the given array is {12, 3, 4, 1, 6, 9} and given sum is 24, then
 * there is a triplet (12, 3 and 9) present in array whose sum is 24.
 */
public class TripletInArray {

	class Triplet {
		int a;
		int b;
		int c;

		public Triplet(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public String toString() {
			return a + " " + b + " " + c;
		}
	}

	/**
	 * O(n^2).. not working..
	 * 
	 * @param input
	 * @param sum
	 * @return
	 */
	public Triplet findTriplet_hashing(int[] input, int sum) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < input.length - 2; i++) {
			int currSum = sum - input[i];
			for (int j = i + 1; j < input.length; j++) {
				int k = input.length;
				if (!map.get(currSum - input[j]).equals(input[k])) {
					Triplet t = new Triplet(input[i], input[j], input[k]);
					return t;
				}
				map.put(input[j], input[j]);
			}
		}
		return new Triplet(0, 0, 0);
	}

	/**
	 * Complexity - O(n^2).
	 * 
	 * @param input
	 * @param sum
	 * @return
	 */
	public Triplet findTriplet(int input[], int sum) {
		Arrays.sort(input);
		for (int i = 0; i < input.length - 2; i++) {

			int start = i + 1;
			int end = input.length - 1;
			int new_sum = sum - input[i];
			while (start < end) {
				if (new_sum == input[start] + input[end]) {
					Triplet t = new Triplet(input[i], input[start], input[end]);
					return t;
				}
				if (new_sum > input[start] + input[end]) {
					start++;
				} else {
					end--;
				}
			}
		}
		return null;
	}

	/**
	 * https://leetcode.com/problems/3sum/
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int start = i + 1;
			int end = nums.length - 1;
			while (start < end) {
				if (nums[i] + nums[start] + nums[end] == 0) {
					List<Integer> r = new ArrayList<>();
					r.add(nums[i]);
					r.add(nums[start]);
					r.add(nums[end]);
					result.add(r);
					start++;
					end--;
					while (start < nums.length && nums[start] == nums[start - 1]) {
						start++;
					}
					while (end >= 0 && nums[end] == nums[end + 1]) {
						end--;
					}
				} else if (nums[i] + nums[start] + nums[end] < 0) {
					start++;
				} else {
					end--;
				}
			}
		}
		return result;
	}

	public static void main(String args[]) {
		TripletInArray tip = new TripletInArray();
		int input[] = { 1, 2, 6, 9, 11, 18, 26, 28 };
		int sum = 22;
		System.out.println(tip.findTriplet_hashing(input, sum));
	}
}
