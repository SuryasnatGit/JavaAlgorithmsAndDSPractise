package com.algo.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumberProblems {

	public void primeFactors(int num) {
		// i = 2
		while (num % 2 == 0) {
			System.out.print(2 + " , ");
			num /= 2;
		}

		// now evens are over. only odds remain
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			while (num % i == 0) {
				System.out.print(i + " , ");
				num /= i;
			}
		}

		if (num > 2) {
			System.out.print(num + " , ");
		}
	}

	public boolean isPrimeSimple(int n) {
		if (n < 2)
			return false;

		for (int i = 2; i < n - 1; i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	/**
	 * less number of iterations than 1st as we need to go only upto sqrt.
	 * 
	 * @param n
	 * @return
	 */
	public boolean isPrimeBetter(int n) {
		if (n < 2)
			return false;

		double sqrt = Math.sqrt(n);
		for (int i = 2; i < sqrt; i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	/**
	 * using sieve of eratostehenes principle.
	 * 
	 * @param n
	 * @return
	 */
	public boolean[] listOfPrimes(int n) {
		boolean[] flags = new boolean[n + 1];
		int count = 0;

		// set all flags to true other than 0 and 1
		init(flags);
		int prime = 2;

		while (prime <= Math.sqrt(n)) {
			// cross of remaining multiples of prime
			crossOff(flags, prime);
			// find next value which is true
			prime = findNextPrime(flags, prime);
		}

		return flags;
	}

	private void init(boolean[] flags) {
		for (int i = 2; i < flags.length; i++)
			flags[i] = true;
	}

	/**
	 * Cross off remaining multiples of prime. We can start with (prime*prime), * because if we have a k * prime, where
	 * k < prime, this value would have already been crossed off in a prior iteration
	 * 
	 * @param flags
	 * @param prime
	 */
	private void crossOff(boolean[] flags, int prime) {
		for (int i = prime * prime; i < flags.length; i += prime)
			flags[i] = false;
	}

	private int findNextPrime(boolean[] flags, int prime) {
		int next = prime + 1;
		while (next < flags.length && !flags[next])
			next++;

		return next;
	}

	/**
	 * 给一个质数数组，返回所有可能的product，顺序不管 比如给 [2,3,5] 返回 [2,3,5,6,10,15,30] 数组中的数如果有重复则需要去重，不允许用set。 比如给 [2,2,2] 返回
	 * [2,4,8]，顺序不用管。
	 * 
	 * 那个很简单，就是基本的combination题，就是DFS 然后乘积的集合不用查重，因为题目说了给你一堆unique的prime number，他们乘出来的结果肯定没有duplicate
	 * 
	 */
	List<Integer> productCombination(int[] arr) {
		Arrays.sort(arr);
		List<Integer> res = new ArrayList<Integer>();
		helper(arr, res, 1, 0);
		return res;
	}

	// Product一直在变， 随时加到result中。这是combination问题的变种
	private void helper(int[] arr, List<Integer> res, int product, int pos) {
		if (product != 1) { // 那如果数组中本身有1呢， 1是质数吗
			res.add(product);
		}

		if (pos == arr.length) {
			return;
		}

		for (int i = pos; i < arr.length; i++) {
			if (i != pos && arr[i] == arr[i - 1]) {
				continue;
			}
			helper(arr, res, product * arr[i], i + 1);
		}
	}

	public static void main(String[] args) {
		PrimeNumberProblems pn = new PrimeNumberProblems();
		pn.primeFactors(100);
		System.out.println();
		pn.primeFactors(69);
	}

}
