package com.algo.ds.array;

/**
 * Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:
 * 
 * All elements < k are moved to the left
 * 
 * All elements >= k are moved to the right
 * 
 * Return the partitioning index, i.e the first index i nums[i] >= k.
 * 
 * Notice:
 * 
 * You should do really partition in array nums instead of just counting the numbers of integers smaller than k. If all
 * elements in nums are smaller than k, then return nums.length
 * 
 * Category : Medium
 **/
public class PartitionArray {

	public static void main(String[] args) {
		PartitionArray pa = new PartitionArray();
		System.out.println(pa.partitionArray1(new int[] { 2, 3, 1, 4 }, 3));
	}

	public int partitionArray1(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int left = 0, right = nums.length - 1;

		while (left <= right) {
			while (left <= right && nums[left] < k) {
				left++;
			}

			while (left <= right && nums[right] >= k) {
				right--;
			}

			if (left <= right) {
				swap(left, right, nums);
			}
		}
		return left;
	}

	private void swap(int left, int right, int[] nums) {
		int temp = nums[left];
		nums[left] = nums[right];
		nums[right] = temp;
	}
}
