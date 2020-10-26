package com.algo.math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod
 * operator.
 * 
 * Return the quotient after dividing dividend by divisor.
 * 
 * The integer division should truncate toward zero, which means losing its fractional part. For example,
 * truncate(8.345) = 8 and truncate(-2.7335) = -2.
 * 
 * Note:
 * 
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range:
 * [−231, 231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.
 * 
 * Example 1:
 * 
 * Input: dividend = 10, divisor = 3 Output: 3 Explanation: 10/3 = truncate(3.33333..) = 3. Example 2:
 * 
 * Input: dividend = 7, divisor = -3 Output: -2 Explanation: 7/-3 = truncate(-2.33333..) = -2. Example 3:
 * 
 * Input: dividend = 0, divisor = 1 Output: 0 Example 4:
 * 
 * Input: dividend = 1, divisor = 1 Output: 1
 *
 * Category : Hard
 * 
 */
public class Divide2Integers {

	/**
	 * Using repeated subtraction. will not solve all the test cases.
	 * 
	 */
	public int divide(int dividend, int divisor) {
		int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		int quotient = 0;
		while (dividend >= divisor) {
			dividend -= divisor;
			quotient++;
		}

		return sign * quotient;
	}

	// TODO : to understand. these does not pass all LC test cases.
	public int divideOptimized(int dividend, int divisor) {
		int flag = 1;

		if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
			flag = -1;
		}

		long C = Math.abs((long) dividend);
		long D = Math.abs((long) divisor); // C / D;

		if (C == 0 || C < D) {
			return 0;
		}

		if (D == 0) {
			return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}

		long left = 1;
		long right = C;

		while (left + 1 < right) {
			long mid = left + (right - left) / 2;

			if (mid <= C / D) {
				if ((mid + 1) > C / D) {
					return (int) mid * flag;
				}
				left = mid;
			} else {
				right = mid;
			}
		}

		if (right * D <= C) {
			return (int) right;
		} else {
			return (int) left;
		}
	}

	public static void main(String[] args) {
		Divide2Integers div = new Divide2Integers();
		System.out.println(div.divide(10, 3));
		System.out.println(div.divide(7, -3));
		System.out.println(div.divide(-10, 3));
		System.out.println(div.divide(-10, -3));

		System.out.println(div.divideOptimized(10, 3));
		System.out.println(div.divideOptimized(7, -3));
		System.out.println(div.divideOptimized(-10, 3));
		System.out.println(div.divideOptimized(-10, -3));
	}
}
