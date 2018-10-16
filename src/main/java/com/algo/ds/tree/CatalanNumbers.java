package com.algo.ds.tree;

public class CatalanNumbers {

	/**
	 * recursive function to find nth catalan number.
	 * 
	 * time complexity - T(n) = T(i) * T(n-i) for n >= 0
	 * 
	 * @param num
	 * @return
	 */
	public int catalan(int num) {
		int res = 0;
		if (num <= 1)
			return 1;

		for (int i = 0; i < num; i++) {
			res += catalan(i) * catalan(num - i - 1);
		}
		return res;
	}

	/**
	 * O(n^2)
	 * 
	 * @param num
	 * @return
	 */
	public int catalan_dp(int num) {
		int[] catalanStore = new int[num + 1];
		catalanStore[0] = 1;
		catalanStore[1] = 1;
		for (int i = 2; i <= num; i++) {
			catalanStore[i] = 0;
			for (int j = 0; j < i; j++) {
				catalanStore[i] += catalanStore[j] * catalanStore[i - j - 1];
			}
		}
		return catalanStore[num];
	}

	public static void main(String[] args) {
		CatalanNumbers num = new CatalanNumbers();
		for (int i = 0; i <= 5; i++) {
			System.out.println(num.catalan(i));
		}
	}
}
