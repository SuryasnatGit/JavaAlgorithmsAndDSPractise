package com.algo.dp;

/**
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together.
 * The problem is not actually to perform the multiplications, but merely to decide in which order
 * to perform the multiplications
 * 
 * We have many options to multiply a chain of matrices because matrix multiplication is
 * associative. In other words, no matter how we parenthesize the product, the result will be the
 * same. For example, if we had four matrices A, B, C, and D, we would have:
 * 
 * (ABC)D = (AB)(CD) = A(BCD) = .... However, the order in which we parenthesize the product affects
 * the number of simple arithmetic operations needed to compute the product, or the efficiency. For
 * example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
 * 
 * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations A(BC) = (30×5×60) + (10×30×60) =
 * 9000 + 18000 = 27000 operations. Clearly the first parenthesization requires less number of
 * operations.
 * 
 * Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of
 * dimension p[i-1] x p[i]. We need to write a function MatrixChainOrder() that should return the
 * minimum number of multiplications needed to multiply the chain.
 * 
 * IN matrix multiplication, complexity is: if(m * n) * (n * p) = O(m n p)
 * 
 * 
 * @author M_402201
 *
 */
public class MatrixChainMultiplication {

	/**
	 * 1) Optimal Substructure: A simple solution is to place parenthesis at all possible places,
	 * calculate the cost for each placement and return the minimum value. In a chain of matrices of
	 * size n, we can place the first set of parenthesis in n-1 ways. For example, if the given chain is
	 * of 4 matrices. let the chain be ABCD, then there are 3 ways to place first set of parenthesis
	 * outer side: (A)(BCD), (AB)(CD) and (ABC)(D). So when we place a set of parenthesis, we divide the
	 * problem into subproblems of smaller size. Therefore, the problem has optimal substructure
	 * property and can be easily solved using recursion.
	 * 
	 * Minimum number of multiplication needed to multiply a chain of size n = Minimum of all n-1
	 * placements (these placements create subproblems of smaller size)
	 * 
	 * Time complexity of the above naive recursive approach is exponential.
	 * 
	 * @param p
	 * @param i
	 * @param j
	 * @return
	 */
	public int minMatMulCount(int[] p, int i, int j) {
		if (i == j)
			return 0;

		int min = Integer.MAX_VALUE;

		// place parenthesis at different places between first
		// and last matrix, recursively calculate count of
		// multiplications for each parenthesis placement and
		// return the minimum count
		for (int k = i; k < j; k++) {
			int count = minMatMulCount(p, i, k) + minMatMulCount(p, k + 1, j) + p[i - 1] * p[k] * p[j];
			if (count < min)
				min = count;
		}
		return min;
	}

	/**
	 * DP approach. Matrix Ai has dimension p[i-1] x p[i] for i = 1..n Time Complexity: O(n^3) Auxiliary
	 * Space: O(n^2)
	 * 
	 * @param p
	 * @param n
	 * @return
	 */
	public int MatrixChainOrder(int matrix[]) {
		int n = matrix.length; // length of the matrix

		/*
		 * It is the table for memorization for overlapping sub-problem property. Also, minimumCost[i][j]
		 * stands for minimum cost for multiplication(num of steps) of the matrix chain starting from matrix
		 * A[i] and ending at A[j] where, 'i' starts from 1 and the max value of j is 'n-1' as there are
		 * only 'n-1' matrices and also always i<j;
		 */
		int[][] minimumCost = new int[n][n];

		/*
		 * The index starts from 1 because the dimension of a matrix is 2X2 and for matrix A[i] the
		 * dim(A[i])=matrix[i-1]*matrix[i]
		 */
		for (int i = 1; i < n; ++i)
			minimumCost[i][i] = 0;

		/*
		 * Here 'l' defines total permissible matrix to be taken in the iteration. 'l' traverses from 2 to
		 * n-1 because there are only n-1 matrices(not n matrices)
		 */
		for (int l = 2; l < n; ++l) {
			/*
			 * Here 'i' starts from the first matrix and traverses up to that matrix where total of 'l' elements
			 * are present in continuation after the matrix including the current matrix. So, total of 'l-1'
			 * matrices are permissible after a given matrix. So total number of such matrix possible is
			 * n-l((n-1)-(l-1))
			 */
			for (int i = 1; i <= n - l; ++i) {
				/*
				 * As for permissible length 'l' you can traverse only maximum of 'l' elements including the current
				 * matrix, therefore maximum index of the matrix permissible after matrix 'i' is 'j' which is given
				 * as 'i + (l-1)'. Note: -1 is there because we are including the current matrix.
				 */
				int j = i + l - 1;

				/*
				 * The aim of the loop is to make all possible partitions into 2 halves for the group of matrix and
				 * find the minimum possible chain for the multiplication purpose.
				 * 
				 * Also it is to be noted that 'i' is traversing from 'i' to 'j-1' not 'j' because if it'll traverse
				 * from 'i' to 'j' then we'll cover the case which will provide out-of-bound exception.
				 * 
				 * 'k': It is the variable which tells around which matrix we are partitioning
				 */
				minimumCost[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; ++k) {
					int costOfCurrentPartition = minimumCost[i][k] + minimumCost[k + 1][j]
							+ matrix[i - 1] * matrix[k] * matrix[j];
					minimumCost[i][j] = Math.min(minimumCost[i][j], costOfCurrentPartition);
				}
			}
		}
		return minimumCost[1][n - 1];
	}

	// TODO : <SD> Then implement
	// https://www.geeksforgeeks.org/printing-brackets-matrix-chain-multiplication-problem/

}
