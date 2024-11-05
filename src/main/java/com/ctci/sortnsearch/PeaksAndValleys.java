
package com.ctci.sortnsearch;

import java.util.Arrays;

/**
 * Peaks and Valleys: In an array of integers, a "peak" is an element which is greater than or equal to the adjacent
 * integers and a "valley" is an element which is less than or equal to the adjacent integers. For example, in the array
 * {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {5, 2} are valleys. Given an array of integers, sort the array into an
 * alternating sequence of peaks and valleys. EXAMPLE Input: {5, 3, 1,2, 3} Output: {5, 1,3,2, 3}
 * 
 * Category : Medium
 * 
 * TODO : to check again
 */
public class PeaksAndValleys {

	public void sort(int[] nums) {
		boolean isDownhill = false;
		boolean isUphill = false;

		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1] && !isDownhill) {
				isDownhill = true;
			} else if (isDownhill) {
				swap(nums, i, i + 1);
				isDownhill = false;
			}
			if (nums[i] < nums[i + 1] && !isUphill) {
				isUphill = true;
			} else if (isUphill) {
				swap(nums, i, i + 1);
				isDownhill = false;
			}
		}

		System.out.println(Arrays.toString(nums));
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		PeaksAndValleys pv = new PeaksAndValleys();
		pv.sort(new int[] { 5, 3, 1, 2, 3 });
	}
}
