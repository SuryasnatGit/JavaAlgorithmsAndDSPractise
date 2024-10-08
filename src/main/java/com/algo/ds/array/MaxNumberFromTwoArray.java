package com.algo.ds.array;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <=
 * m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array
 * of the k digits.
 *
 * e.g nums1 = [3, 4, 6, 5] nums2 = [9, 1, 2, 5, 8, 3] k = 5 return [9, 8, 6, 5, 3]
 * 
 * Example 2:
 * 
 * Input: nums1 = [6, 7] nums2 = [6, 0, 4] k = 5 Output: [6, 7, 6, 0, 4]
 * 
 * Example 3:
 * 
 * Input: nums1 = [3, 9] nums2 = [8, 9] k = 3 Output: [9, 8, 9]
 * 
 * https://leetcode.com/problems/create-maximum-number/
 * 
 * Category : Hard
 */
public class MaxNumberFromTwoArray {

	/**
	 * To find the maximum ,we can enumerate how many digits we should get from nums1 , we suppose it is i.
	 * 
	 * So , the digits from nums2 is K – i.
	 * 
	 * And we can use a stack to get the get maximum number(x digits) from one array.（just like leetcode Remove
	 * Duplicate Letters）
	 * 
	 * OK, Once we choose two maximum subarray , we should combine it to the answer.
	 * 
	 * It is just like merger sort, but we should pay attention to the case: the two digital are equal.
	 * 
	 * we should find the digits behind it to judge which digital we should choose now.
	 * 
	 * In other words,we should judge which subarry is bigger than the other.
	 * 
	 * That’s all.
	 * 
	 * The algorithm is O((m+n)^3) in the worst case.
	 * 
	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
	public int[] maxNumber1(int[] nums1, int[] nums2, int k) {
		int[] max = new int[k];
		for (int i = 0; i <= k; i++) {
			if (nums1.length < i || nums2.length < k - i) {
				continue;
			}
			int[] a = merge(getMaxSubSequence(nums1, i), getMaxSubSequence(nums2, k - i));
			if (isGreater(a, max, 0, 0)) {
				max = a;
			}
		}
		return max;
	}

	private int[] merge(int[] a1, int[] a2) {
		int[] result = new int[a1.length + a2.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < a1.length || j < a2.length) {
			if (i == a1.length) {
				result[k++] = a2[j++];
			} else if (j == a2.length) {
				result[k++] = a1[i++];
			} else if (a1[i] > a2[j]) {
				result[k++] = a1[i++];
			} else if (a1[i] < a2[j]) {
				result[k++] = a2[j++];
			} else {
				if (isGreater(a1, a2, i, j)) {
					result[k++] = a1[i++];
				} else {
					result[k++] = a2[j++];
				}
			}
		}
		return result;
	}

	private boolean isGreater(int[] a, int[] b, int i, int j) {
		while (i < a.length && j < b.length) {
			if (a[i] > b[j]) {
				return true;
			} else if (a[i] < b[j]) {
				return false;
			}
			i++;
			j++;
		}
		return j == b.length;
	}

	/**
	 * The solution to this problem is Greedy with the help of stack. The recipe is as following:<br/>
	 * Initialize a empty stack.<br/>
	 * Loop through the array nums.<br/>
	 * <t/>pop the top of stack if it is smaller than nums[i] until:<br/>
	 * stack is empty.<br/>
	 * the digits left is not enough to fill the stack to size k.<br/>
	 * if stack size < k push nums[i].<br/>
	 * Return stack.
	 * 
	 * Since the stack length is known to be k, it is very easy to use an array to simulate the stack. The time
	 * complexity is O(n) since each element is at most been pushed and popped once.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	private int[] getMaxSubSequence(int[] nums, int k) {
		if (k == 0) {
			return new int[0];
		}
		int[] result = new int[k];
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			while (index > 0 && index + (nums.length - i - 1) >= k && result[index - 1] < nums[i]) {
				index--;
			}
			if (index < k) {
				result[index++] = nums[i];
			}
		}
		System.out.println(Arrays.toString(result));
		return result;
	}

	// Using actual stack
	public int[] maxNumber2(int[] nums1, int[] nums2, int k) {
		int[] result = new int[k];

		for (int ki = Math.max(0, k - nums2.length); ki <= Math.min(k, nums1.length); ki++) {
			int[] res1 = getMaxSubsequence(nums1, ki); // ki < nums1.length
			int[] res2 = getMaxSubsequence(nums2, k - ki); // k - ki < nums2.length
			int[] resTmp = new int[k];
			int p1 = 0, p2 = 0, pt = 0;
			while (p1 < res1.length || p2 < res2.length) {
				resTmp[pt++] = isGreater(res1, p1, res2, p2) ? res1[p1++] : res2[p2++];
			}
			if (!isGreater(result, 0, resTmp, 0)) {
				result = resTmp;
			}
		}
		return result;
	}

	private int[] getMaxSubsequence(int[] nums, int cnt) {
		Stack<Integer> stack = new Stack<>();
		int remain = cnt;
		for (int i = 0; i < nums.length; i++) {
			while (!stack.isEmpty() && stack.peek() < nums[i] && nums.length - 1 - i >= remain) {
				stack.pop();
				remain++;
			}
			if (remain > 0) {
				stack.push(nums[i]);
				remain--;
			}
		}
		int[] maxSub = new int[cnt];
		int mi = maxSub.length - 1;
		while (!stack.isEmpty()) {
			maxSub[mi--] = stack.pop();
		}
		return maxSub;
	}

	private boolean isGreater(int[] nums1, int p1, int[] nums2, int p2) {
		for (; p1 < nums1.length && p2 < nums2.length; p1++, p2++) {
			if (nums1[p1] > nums2[p2]) {
				return true;
			}
			if (nums1[p1] < nums2[p2]) {
				return false;
			}
		}
		return p1 != nums1.length;
	}

	public static void main(String args[]) {
		MaxNumberFromTwoArray max = new MaxNumberFromTwoArray();
		int[] input1 = { 9, 1, 2, 5, 8, 3 };
		int[] input2 = { 3, 4, 6, 5 };
		int[] result1 = max.maxNumber1(input1, input2, 5);
		System.out.println(Arrays.toString(result1));

		int[] input3 = { 2, 5, 6, 4, 4, 0 };
		int[] input4 = { 7, 3, 8, 0, 6, 5, 7, 6, 2 };
		int[] result = max.maxNumber1(input3, input4, 15);
		System.out.println(Arrays.toString(result));
	}
}