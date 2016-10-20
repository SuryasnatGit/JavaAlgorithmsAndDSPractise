package com.algo.ds.amazon;

public class FibonnaciSeries {

	/**
	 * Inefficient recursive solution
	 * @param n
	 * @return
	 */
	public long fib1(int n){
		if(n == 0) return 0;
		if(n == 1) return 1;
		return fib1(n-1)+fib1(n-2);
	}
	
	/**
	 * Efficient solution with complexity O(n)
	 * @param n
	 * @return
	 */
	public long fib2(int n){
		int result[] = {0,1};
		if(n < 2) return result[n];
		long fibMinus1 = 1;
		long fibMinus2 = 0;
		long fibN = 0;
		for(int i=2;i<=n;++i){
			fibN = fibMinus1 + fibMinus2;
			fibMinus2 = fibMinus1;
			fibMinus1 = fibN;
		}
		return fibN;
	}
}
