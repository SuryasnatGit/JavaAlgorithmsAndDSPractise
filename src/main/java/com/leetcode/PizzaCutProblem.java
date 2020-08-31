package com.leetcode;

/**
 * Given number of cuts, find the maximum number of possible pieces.
 * 
 * Examples:
 * 
 * Input : 2 Output : 4
 * 
 * Input : 3 Output : 7
 *
 * This problem is nothing but The Lazy Caterer’s Problem and has below formula.
 * 
 * Maximum number of pieces = 1 + n*(n+1)/2
 */
public class PizzaCutProblem {

	public int maxCut(int n) {
		return n * (n + 1) / 2 + 1;
	}

	/**
	 * 
	 * Pizza cut n knife ask how many pieces can be cut at most. Note that all tangents do not have to be at the same
	 * intersection // When cutting the nth knife, it can intersect with the previous n-1 knife at most. This extra
	 * knife produces n pizzas
	 * 
	 * TODO : understand more on this.
	 */
	public int maxCut1(int n) {
		int[] hash = new int[n + 1];
		hash[0] = 1;

		for (int i = 1; i < n + 1; i++) {
			hash[i] = 1 + hash[i - 1];
		}

		return hash[n];
	}

	public static void main(String[] args) {
		PizzaCutProblem pi = new PizzaCutProblem();
		System.out.println(pi.maxCut(2));
		System.out.println(pi.maxCut(3));
		System.out.println(pi.maxCut(4));

		System.out.println(pi.maxCut1(2));
		System.out.println(pi.maxCut1(3));
		System.out.println(pi.maxCut1(4));
	}
}
