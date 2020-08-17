package com.leetcode;

/**
 * Category : Medium
 *
 */
public class RemoveDuplicatesFromSortedArray {

	/**
	 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
	 * 
	 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the
	 * new length.
	 * 
	 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
	 * extra memory.
	 * 
	 * Example 1:
	 * 
	 * Given nums = [1,1,2],
	 * 
	 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
	 * 
	 * It doesn't matter what you leave beyond the returned length. Example 2:
	 * 
	 * Given nums = [0,0,1,1,1,2,2,3,3,4],
	 * 
	 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4
	 * respectively.
	 * 
	 * It doesn't matter what values are set beyond the returned length.
	 * 
	 * T - O(n) S - O(1)
	 */
	public int removeDuplicates1(int[] nums) {
		if (nums.length == 0)
			return 0;

		int slow = 0;
		for (int fast = 1; fast < nums.length; fast++) {
			if (nums[fast] != nums[slow]) {
				slow++;
				nums[slow] = nums[fast];
			}
		}
		return slow + 1;
	}

	/**
	 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
	 * 
	 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return
	 * the new length.
	 * 
	 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
	 * extra memory.
	 * 
	 * Example 1:
	 * 
	 * Given nums = [1,1,1,2,2,3],
	 * 
	 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
	 * 
	 * It doesn't matter what you leave beyond the returned length. Example 2:
	 * 
	 * Given nums = [0,0,1,1,1,1,2,3,3],
	 * 
	 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3
	 * and 3 respectively.
	 * 
	 * It doesn't matter what values are set beyond the returned length.
	 */
	public int removeDuplicates2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int count = 0;
		for (int i = 1; i < nums.length; i++) {
			if ((nums[i] == nums[count]) && (count == 0 || (count >= 1 && nums[count - 1] != nums[i]))) {
				nums[++count] = nums[i];
			} else if (nums[count] != nums[i]) {
				nums[++count] = nums[i];
			}
		}

		return ++count;
	}

	/**
	 * No duplicates allowed at all
	 * 
	 */
	public int removeDuplicates3(int[] nums) {
		if (nums.length <= 1) {
			return nums.length;
		}

		int size = 0;
		int prevIndex = 0; // The first appearance of this value

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[prevIndex]) {
				continue;
			} else {
				if (i == prevIndex + 1) { // This means prevValue appears only once
					nums[size++] = nums[prevIndex];
				}
				prevIndex = i;
			}
		}

		if (prevIndex == nums.length - 1) {
			nums[size++] = nums[prevIndex];
		}

		return size;
	}

	public static void main(String[] args) {
		RemoveDuplicatesFromSortedArray rem = new RemoveDuplicatesFromSortedArray();
		System.out.println(rem.removeDuplicates1(new int[] { 1, 1, 2 }));
		System.out.println(rem.removeDuplicates1(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }));
		System.out.println(rem.removeDuplicates1(new int[] { 1, 2, 3, 4, 5, 6, 6 }));
		System.out.println(rem.removeDuplicates2(new int[] { 1, 1, 1, 2, 2, 3 }));
		System.out.println(rem.removeDuplicates2(new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 }));
		System.out.println(rem.removeDuplicates3(new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 }));
	}
}
