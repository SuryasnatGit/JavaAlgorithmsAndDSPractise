package com.algo.dp;

/**
 * 
 * @author Suryasnat
 *
 */
public class FibonacciDP {

	public static void main(String[] args) {
		FibonacciDP dp = new FibonacciDP();
		dp.initializeLookup();
		System.out.println(dp.fib_memoized(10));
		System.out.println(dp.fib_tabulation(12));
	}

	private int[] lookup = new int[100];

	/**
	 * The memoized program for a problem is similar to the recursive version with a small modification
	 * that it looks into a lookup table before computing solutions. We initialize a lookup array with
	 * all initial values as NIL. Whenever we need solution to a subproblem, we first look into the
	 * lookup table. If the precomputed value is there then we return that value, otherwise we calculate
	 * the value and put the result in lookup table so that it can be reused later.
	 * 
	 * Following is the memoized version for nth Fibonacci Number.
	 * 
	 * Time Complexity: T(n) = T(n-1) + T(n-2) which is exponential.
	 * 
	 * @param num
	 * @return
	 */
	public int fib_memoized(int num) {
		if (lookup[num] == -1) {
			if (num <= 1)
				lookup[num] = num;
			else
				lookup[num] = fib_memoized(num - 1) + fib_memoized(num - 2);
		}
		return lookup[num];
	}

	private void initializeLookup() {
		for (int i = 0; i < 100; i++)
			lookup[i] = -1;
	}

	/**
	 * The tabulated program for a given problem builds a table in bottom up fashion and returns the
	 * last entry from table. For example, for the same Fibonacci number, we first calculate fib(0) then
	 * fib(1) then fib(2) then fib(3) and so on. So literally, we are building the solutions of
	 * subproblems bottom-up.
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
	public int fib_tabulation(int num) {
		int[] lookup = new int[num + 1];
		lookup[0] = 0;
		lookup[1] = 1;
		for (int i = 2; i <= num; i++)
			lookup[i] = lookup[i - 1] + lookup[i - 2];
		return lookup[num];
	}

	/**
	 * if we dont want to use extra space for memo table.. This is basically storing the results from
	 * the last two Fibonacci values into a and b. At each iteration, we compute the next value (c = a +
	 * b) and then move(b, c = a + b) into (a, b) .
	 * 
	 * Time Complexity: O(n)
	 * 
	 * Extra Space: O(1)
	 * 
	 * @param num
	 * @return
	 */
	public int fib_table_noextraspace(int num) {
		if (num == 0)
			return 0;
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
