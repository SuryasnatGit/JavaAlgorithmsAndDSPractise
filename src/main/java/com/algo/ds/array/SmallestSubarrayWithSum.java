package com.algo.ds.array;

// https://www.techiedelight.com/length-of-smallest-subarray-with-sum-greater-number/
public class SmallestSubarrayWithSum {

	public int smallestSubarray(int[] arr, int sum) {
		int windowSum = 0;
		int len = Integer.MAX_VALUE;

		int left = 0;
		for (int right = 0; right < arr.length; right++) {
			windowSum += arr[right];
			while (windowSum > sum && left <= right) {
				len = Math.min(len, right - left + 1);
				// remove element from window left side till it becomes stable egain
				windowSum -= arr[left];
				// move left
				left++;
			}
		}

		return len;
	}

	public static void main(String[] args) {
		SmallestSubarrayWithSum sm = new SmallestSubarrayWithSum();
		System.out.println(sm.smallestSubarray(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 20));
	}
}
