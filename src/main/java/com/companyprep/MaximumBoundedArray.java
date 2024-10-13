package com.companyprep;

import java.util.Stack;

/**
 * TODO : to understand again.
 * 
 * https://aonecode.com/interview-question/maximum-bounded-array
 * 
 * https://medium.com/javarevisited/day-57-number-of-subarrays-bounded-maximum-5791fe316a45
 * 
 * https://leetcode.com/discuss/interview-question/815454/amazon-oa-question-sde-1
 * 
 * Category : Hard
 *
 */
public class MaximumBoundedArray {

	private static void func(int[] arr) {
		int n = arr.length;
		int[] ansL = new int[n];
		int[] ansR = new int[n];
		Stack<Integer> st = new Stack();
		int i = 0;
		for (i = 0; i < n;) {
			if (st.isEmpty() || arr[i] >= arr[st.peek()]) {
				st.push(i);
				i++;
			} else {
				int top = st.pop();
				if (st.isEmpty()) {
					ansR[top] = i - 1;
					ansL[top] = 0;
				} else {
					ansR[top] = i - 1;
					ansL[top] = st.peek() + 1;
				}
			}
		}

		while (!st.isEmpty()) {
			int top = st.pop();
			if (st.isEmpty()) {
				ansR[top] = n - 1;
				ansL[top] = 0;
			} else {
				ansR[top] = n - 1;
				ansL[top] = st.peek() + 1;
			}
		}

		for (int k = 0; k < n; k++) {
			System.out.println("l: " + ansL[k] + " r: " + ansR[k]);
		}

		int[] cumSum = new int[n + 1];
		cumSum[0] = 0;
		for (int k = 1; k <= n; k++) {
			cumSum[k] = arr[k - 1] + cumSum[k - 1];
		}
		for (int k = 0; k <= n; k++) {
			System.out.println("cumSum: i " + k + " value:  " + cumSum[k]);
		}

		int ans = 0;
		for (int k = 0; k < n; k++) {
			ans = Math.max(ans, (cumSum[ansR[k] + 1] - cumSum[ansL[k]]) * (arr[k]));
		}

		System.out.println("Ans : " + ans);
	}

	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		int[] ar = { 3, 1, 6, 4, 5, 2 };
		func(ar);
	}

}
