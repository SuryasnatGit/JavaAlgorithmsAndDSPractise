package com.ctci.sortnsearch;

/**
 * CTCI - 10.3
 * 
 * Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown number of times, write
 * code to find an element in the array. You may assume that the array was originally sorted in increasing order.
 * 
 * EXAMPLE : Input: find 5 in {15, 16, 19, 20, 25, 1, 3 ,4 ,5 ,7 ,10 , 14}
 * 
 * Output 8 (the index of 5 in the array).
 * 
 * Complexity: O(log n) if all elements are unique. But if many duplicates are there, then its O(n).
 * 
 * Category : Medium
 * 
 */
public class SearchInRotatedSortedArray {

	// Iterative approach
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (target == nums[mid])
				return mid;

			if (nums[left] <= nums[mid]) {
				if (nums[left] <= target && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (nums[mid] < target && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}

		return -1;
	}

	public boolean searchDuplicates(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			// pretty much same as above except the duplicate check condition
			while (left < right && nums[left] == nums[left + 1]) {
				left++;
			}
			while (left < right && nums[right] == nums[right - 1]) {
				right--;
			}
			int mid = left + (right - left) / 2;
			if (nums[mid] == target)
				return true;

			if (nums[left] <= nums[mid]) {
				if (nums[left] <= target && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (nums[mid] < target && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
		int[] arr = new int[] { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		System.out.println(s.search(arr, 19));
		System.out.println(s.search(arr, 91));
		System.out.println(s.searchDuplicates(new int[] { 2, 5, 6, 0, 0, 1, 2 }, 0));
		System.out.println(s.searchDuplicates(new int[] { 2, 5, 6, 0, 0, 1, 2 }, 3));
		System.out.println(s.searchDuplicates(new int[] { 2, 5, 6, 0, 0, 1, 2 }, 2));
	}

}
