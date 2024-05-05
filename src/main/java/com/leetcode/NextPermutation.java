package com.leetcode;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
 * order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
 * column.
 * 
 * 1,2,3 → 1,3,2
 * 
 * 3,2,1 → 1,2,3
 * 
 * 1,1,5 → 1,5,1
 * 
 * Given an integer, it is required to find another number composed of the same digits, so that the new number is the
 * largest number smaller than the original number. For example: 321 -》312.
 * 
 * I had no clue when I saw this question. After asking the interviewer to give a few examples, I found out that it was
 * the previous permutation question in the interview.
 * 
 * Category : Hard
 * 
 * TODO : understand properly
 *
 */
public class NextPermutation {

	public void nextPermutation(int[] nums) {
		if (nums.length <= 1) {
			return;
		}

		int i = nums.length - 1;
		for (; i > 0; i--) {
			if (nums[i] > nums[i - 1]) { // find first number which is smaller than it's next number
				break;
			}
		}

		if (i != 0) { // If the number exist, which means that the nums not like{5,4,3,2,1}
			findAndSwap(nums, i - 1); // From the right end, find the first larger than nums[left]
		}

		reverse(nums, i, nums.length - 1); // Make it in increasing sequence
	}

	void findAndSwap(int[] arr, int left) {
		for (int i = arr.length - 1; i > left; i--) {
			if (arr[i] > arr[left]) {
				swap(arr, left, i);
				break;
			}
		}
	}

	void swap(int[] arr, int left, int right) {
		arr[left] = arr[left] ^ arr[right];
		arr[right] = arr[left] ^ arr[right];
		arr[left] = arr[left] ^ arr[right];
	}

	void reverse(int[] data, int from, int end) {
		int left = from;
		int right = end;

		while (left < right) {
			// swap the values at the left and right indices
			swap(data, left, right);

			// move the left and right index pointers in toward the center
			left++;
			right--;
		}
	}
}
