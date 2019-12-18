package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 * 
 * Given an array arr[], find the maximum j  i such that arr[j] > arr[i]. Examples :
 * 
 * Input: {34, 8, 10, 3, 2, 80, 30, 33, 1} Output: 6 (j = 7, i = 1)
 * 
 * Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0} Output: 8 ( j = 8, i = 0)
 * 
 * Input: {1, 2, 3, 4, 5, 6} Output: 5 (j = 5, i = 0)
 * 
 * Input: {6, 5, 4, 3, 2, 1} Output: -1
 */
public class MaximumIminusJSuchThatAiGTAj {

	/**
	 * Run two loops. In the outer loop, pick elements one by one from left. In the inner loop, compare
	 * the picked element with the elements starting from right side. Stop the inner loop when you see
	 * an element greater than the picked element and keep updating the maximum j-i so far.
	 * 
	 * complexity - O(n^2)
	 * 
	 * @param input
	 * @return
	 */
	public int maxDiffSimple(int[] input) {
		int maxDiff = -1;
		for (int i = 0; i < input.length - 1; i++) {
			for (int j = input.length - 1; j > 1; j--) {
				if (input[j] > input[i] && maxDiff < (j - i))
					maxDiff = j - i;
			}
		}
		return maxDiff;
	}

	/**
	 * To solve this problem, we need to get two optimum indexes of arr[]: left index i and right index
	 * j. For an element arr[i], we do not need to consider arr[i] for left index if there is an element
	 * smaller than arr[i] on left side of arr[i]. Similarly, if there is a greater element on right
	 * side of arr[j] then we do not need to consider this j for right index. So we construct two
	 * auxiliary arrays LMin[] and RMax[] such that LMin[i] holds the smallest element on left side of
	 * arr[i] including arr[i], and RMax[j] holds the greatest element on right side of arr[j] including
	 * arr[j]. After constructing these two auxiliary arrays, we traverse both of these arrays from left
	 * to right. While traversing LMin[] and RMa[] if we see that LMin[i] is greater than RMax[j], then
	 * we must move ahead in LMin[] (or do i++) because all elements on left of LMin[i] are greater than
	 * or equal to LMin[i]. Otherwise we must move ahead in RMax[j] to look for a greater j  i value.
	 * 
	 * Time complexity - O(n) , space complexity - O(n)
	 * 
	 * @param arr
	 * @return
	 */
	public int maximumGeeks(int arr[]) {
		int maxDiff;
		int i, j;
		int n = arr.length;
		int RMax[] = new int[n];
		int LMin[] = new int[n];

		/*
		 * Construct LMin[] such that LMin[i] stores the minimum value from (arr[0], arr[1], ... arr[i])
		 */
		LMin[0] = arr[0];
		for (i = 1; i < n; ++i)
			LMin[i] = Math.min(arr[i], LMin[i - 1]);

		/*
		 * Construct RMax[] such that RMax[j] stores the maximum value from (arr[j], arr[j+1], ..arr[n-1])
		 */
		RMax[n - 1] = arr[n - 1];
		for (j = n - 2; j >= 0; --j)
			RMax[j] = Math.max(arr[j], RMax[j + 1]);

		/*
		 * Traverse both arrays from left to right to find optimum j - i This process is similar to merge()
		 * of MergeSort
		 */
		i = 0;
		j = 0;
		maxDiff = -1;
		while (j < n && i < n) {
			if (LMin[i] < RMax[j]) {
				maxDiff = Math.max(maxDiff, j - i);
				j = j + 1;
			} else
				i = i + 1;
		}

		return maxDiff;
	}

	public static void main(String args[]) {
		MaximumIminusJSuchThatAiGTAj mj = new MaximumIminusJSuchThatAiGTAj();
		int input[] = { 34, 8, 10, 3, 2, 80, 30, 33, 1 };
		System.out.println(mj.maximumGeeks(input));
		System.out.println(mj.maxDiffSimple(input));
	}

}
