package com.leetcode;

/**
 * You are given a sequence of black and white horses, and a set of K stables numbered 1 to K. You have to accommodate
 * the horses into the stables in such a way that the following conditions are satisfied:
 * 
 * You fill the horses into the stables preserving the relative order of horses.
 * 
 * For instance, you cannot put horse 1 into stable 2 and horse 2 into stable 1. You have to preserve the ordering of
 * the horses.
 * 
 * No stable should be empty and no horse should be left unaccommodated.
 * 
 * Take the product (number of white horses * number of black horses) for each stable and take the sum of all these
 * products.
 * 
 * This value should be the minimum among all possible accommodation arrangements
 * 
 * Example:
 * 
 * Input: {WWWB} , K = 2
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * We have 3 choices {W, WWB}, {WW, WB}, {WWW, B}
 * 
 * for first choice we will get 1*0 + 2*1 = 2.
 * 
 * for second choice we will get 2*0 + 1*1 = 1.
 * 
 * for third choice we will get 3*0 + 0*1 = 0.
 * 
 * Of the 3 choices, the third choice is the best option.
 * 
 * If a solution is not possible, then return -1
 * 
 * https://codevillage.wordpress.com/2016/09/06/mastering-dynamic-programming-2/
 * 
 * Category : Hard
 */
public class HorseStableArrangement {

	// a - horse sequence. b - number of stables
	public int arrange(String a, int b) {

		int length = a.length();

		// base case
		if (b > length)
			return -1;

		int[][] dp = new int[length][b];

		// for a single partition/stable number of horses will be black * white
		int blackCount = 0;
		int whiteCount = 0;
		for (int i = 0; i < length; i++) {
			if (a.charAt(i) == 'W')
				whiteCount++;
			else
				blackCount++;

			dp[i][0] = whiteCount * blackCount;
		}

		// for the remainder of the partitions
		for (int j = 1; j < b; j++) {
			for (int i = 0; i < length; i++) {
				if (j > i) {
					dp[i][j] = Integer.MAX_VALUE;
				} else {
					blackCount = 0;
					whiteCount = 0;
					int minSum = Integer.MAX_VALUE;
					// traverse backwards
					for (int k = i - 1; k >= 0; k--) {
						if (a.charAt(k + 1) == 'W')
							whiteCount++;
						else
							blackCount++;

						if (dp[k][j - 1] + blackCount * whiteCount >= 0) {
							minSum = Math.min(minSum, dp[k][j - 1] + blackCount * whiteCount);
						}
					}
					dp[i][j] = minSum;
				}
			}
		}

		return dp[length - 1][b - 1] > 0 ? dp[length - 1][b - 1] : 0;
	}

	public static void main(String[] args) {
		HorseStableArrangement hs = new HorseStableArrangement();
		System.out.println(hs.arrange("WBWB", 2));
	}

}
