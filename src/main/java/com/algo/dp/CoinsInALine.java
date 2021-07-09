package com.algo.dp;

/**
 *
 * Tags : DP
 */
public class CoinsInALine {

	/**
	 * Problem 1 :
	 * 
	 * There are n coins in a line. (Assume n is even). Two players take turns to take a coin from one of the ends of
	 * the line until there are no more coins left. So, the goal is to provide a strategy in which the player with the
	 * first move gets the maximum value coins in the end.
	 * 
	 * The player with the larger amount of money wins.
	 *
	 * Let’s make it more clear- Suppose we have coins lined up from Ci to Cj wit the values of Vi to Vj respectively.
	 * 
	 * MV(i, j) = maximum value the Player 1 can collect from i'th coin to j'th coin.
	 * 
	 * Case 1 - Player 1 Picks the ith coin (from starting) ==> Vi + Min{MV(i+2,j), MV(i+1, j-1)}
	 * 
	 * Case 2 - Player 1 Picks the jth coin (from the end) ==> Vj + Min{MV(i+1,j-1), MV(i, j-2)}
	 * 
	 * So now we need to decide whether Player 1 should pick ith coin or jth coin. Player 1 will pick the coin which
	 * ever gives the more value considering 2 moves ahead
	 * 
	 * MV(i, j) = Max { Vi + Min{MV(i+2,j), MV(i+1, j-1)} , Vj + Min{MV(i+1,j-1), MV(i, j-2)}}
	 * 
	 */

	// Returns optimal value possible that a player
	// can collect from an array of coins of size n.
	// Note than n must be even
	public int optimalStrategyOfGame(int arr[], int n) {
		// Create a table to store solutions of subproblems
		int table[][] = new int[n][n];
		int gap, i, j, x, y, z;

		// Fill table using above recursive formula.
		// Note that the tableis filled in diagonal
		// fashion (similar to http:// goo.gl/PQqoS),
		// from diagonal elements to table[0][n-1]
		// which is the result.
		for (gap = 0; gap < n; ++gap) {
			for (i = 0, j = gap; j < n; ++i, ++j) {

				// Here x is value of F(i+2, j),
				// y is F(i+1, j-1) and z is
				// F(i, j-2) in above recursive formula
				x = ((i + 2) <= j) ? table[i + 2][j] : 0;
				y = ((i + 1) <= (j - 1)) ? table[i + 1][j - 1] : 0;
				z = (i <= (j - 2)) ? table[i][j - 2] : 0;

				table[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
			}
		}

		return table[0][n - 1];
	}

	/**
	 * Approach 2 -Optimized approach DP using memoization
	 * 
	 * We have discussed an approach that makes 4 recursive calls. In this post, a new approach is discussed that makes
	 * two recursive calls.
	 * 
	 * There are two choices:
	 * 
	 * 1. The user chooses the ith coin with value Vi: The opponent either chooses (i+1)th coin or jth coin. The
	 * opponent intends to choose the coin which leaves the user with minimum value. i.e. The user can collect the value
	 * Vi + (Sum – Vi) + F(i+1, j, Sum) where Sum is sum of coins from index i to j. The expression can be simplified to
	 * Sum + F(i+1, j, Sum)
	 * 
	 * 2. The user chooses the jth coin with value Vj: The opponent either chooses ith coin or (j-1)th coin. The
	 * opponent intends to choose the coin which leaves the user with minimum value. i.e. The user can collect the value
	 * Vj + (Sum – Vj) + F(i, j-1, Sum) where Sum is sum of coins from index i to j. The expression can be simplified to
	 * Sum + F(i, j-1, Sum)
	 * 
	 * Following is recursive solution that is based on above two choices. We take the maximum of two choices.
	 * 
	 * F(i, j) represents the maximum value the user can collect from i'th coin to j'th coin.
	 * 
	 * F(i, j) = Max(Sum + F(i+1, j, Sum), Sum + F(i, j-1, Sum))
	 * 
	 * Base Case F(i, j) = max(Vi, Vj) If j == i+1
	 * 
	 * 
	 * 
	 */
	private int MAX = 100;

	private int[][] memo = new int[MAX][MAX];

	public int optimalStrategyOfGame1(int[] arr, int n) {
		// Compute sum of elements
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}

		// Initialize memoization table
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				memo[i][j] = -1;

		return oSRec(arr, 0, n - 1, sum);
	}

	private int oSRec(int arr[], int i, int j, int sum) {
		if (j == i + 1)
			return Math.max(arr[i], arr[j]);

		if (memo[i][j] != -1)
			return memo[i][j];

		// For both of your choices, the opponent
		// gives you total sum minus maximum of
		// his value
		memo[i][j] = Math.max((sum - oSRec(arr, i + 1, j, sum - arr[i])), (sum - oSRec(arr, i, j - 1, sum - arr[j])));

		return memo[i][j];
	}
}
