package com.algo.math;

public class SquareRootProblems {

	/*
	 * Problem 1 : Given an integer x, find square root of it. If x is not a perfect square, then return floor(√x).
	 * 
	 * Examples :
	 * 
	 * Input: x = 4 Output: 2
	 * 
	 * Input: x = 11 Output: 3
	 */

	/**
	 * Time complexity of the above solution is O(√ n)
	 * 
	 * @param num
	 * @return
	 */
	public int floorSqrt(int num) {
		if (num == 0 || num == 1)
			return num;

		int i = 1, result = 1;
		while (result <= num) {
			i++;
			result = i * i;
		}
		return i - 1;
	}

	/**
	 * time complexity - O(log n). can be further optimized by start = 0 and end = num/2 as floor of sq rt of num cannot
	 * be more than num / 2 when num > 1
	 * 
	 * @param num
	 * @return
	 */
	public int floorSqrtBinarySearch(int num) {
		// base case
		if (num == 0 || num == 1)
			return num;

		int start = 1, end = num, result = 0;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (mid * mid == num)
				return mid;
			else if (mid * mid < num) {
				start = mid + 1;
				result = mid;
			} else {
				end = mid - 1;
			}
		}
		return result;
	}

	/*
	 * Find square root of a number upto given precision value. T - O(log num)
	 */
	public float squareRoot(int num, int precision) {
		double result = 0.0;

		int left = 0, right = num;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (mid * mid == num) {
				result = mid;
			} else if (mid * mid < num) {
				left = mid + 1;
				result = mid;
			} else {
				right = mid - 1;
			}
		}

		double fraction = 0.1;
		for (int i = 0; i < precision; i++) {
			// this terminates if result * result > num
			while (result * result <= num) {
				result += fraction;
			}
			result = result - fraction;
			fraction = fraction / 10;
		}

		return (float) result;
	}

	public static void main(String[] args) {
		SquareRootProblems sr = new SquareRootProblems();
		System.out.println(sr.floorSqrt(9));
		System.out.println(sr.floorSqrt(11));
		System.out.println(sr.floorSqrt(28));
		System.out.println(sr.floorSqrtBinarySearch(28));
		System.out.println(sr.squareRoot(20, 6));
	}
}
