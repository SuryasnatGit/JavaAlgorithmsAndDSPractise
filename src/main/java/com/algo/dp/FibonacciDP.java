package com.algo.dp;

/**
 * 
 */
public class FibonacciDP {

	public static void main(String[] args) {
		FibonacciDP dp = new FibonacciDP();
		System.out.println(dp.fibonnacciRecursive(10));
		System.out.println(dp.fibonacciDP(10));
		System.out.println(dp.fibonacciOptimizedSpace(10));
	}

	/**
	 * T - O(2^n)
	 * 
	 */
	public int fibonnacciRecursive(int num) {
		if (num == 0)
			return 0;
		if (num == 1)
			return 1;

		return fibonnacciRecursive(num - 1) + fibonnacciRecursive(num - 2);
	}

	/**
	 * The tabulated program for a given problem builds a table in bottom up fashion and returns the last entry from
	 * table. For example, for the same Fibonacci number, we first calculate fib(0) then fib(1) then fib(2) then fib(3)
	 * and so on. So literally, we are building the solutions of subproblems bottom-up.
	 * 
	 * Following is the tabulated version for nth Fibonacci Number
	 * 
	 * Time Complexity: O(n)
	 * 
	 * Extra Space: O(n)
	 * 
	 * @param num
	 * @return
	 */
	public int fibonacciDP(int num) {
		int[] lookup = new int[num + 1];
		lookup[0] = 0;
		lookup[1] = 1;
		for (int i = 2; i <= num; i++)
			lookup[i] = lookup[i - 1] + lookup[i - 2];
		return lookup[num];
	}

	/**
	 * if we dont want to use extra space for memo table.. This is basically storing the results from the last two
	 * Fibonacci values into a and b. At each iteration, we compute the next value (c = a + b) and then move(b, c = a +
	 * b) into (a, b) .
	 * 
	 * Time Complexity: O(n)
	 * 
	 * Extra Space: O(1)
	 * 
	 * @param num
	 * @return
	 */
	public int fibonacciOptimizedSpace(int num) {
		if (num < 2)
			return num;
		int a = 0;
		int b = 1;
		for (int i = 2; i < num; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return a + b;
	}
}
