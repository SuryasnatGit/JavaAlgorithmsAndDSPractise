package com.hackerrank;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/magic-square-forming/problem
 * 
 * A magic square of order n is an arrangement of n^2 numbers, usually distinct integers, in a square, such that the n numbers in all rows, all columns, and both diagonals sum to the same constant. A magic square contains the integers from 1 to n^2.

The constant sum in every row, column and diagonal is called the magic constant or magic sum, M. The magic constant of a normal magic square depends only on n and has the following value:
M = n(n^2+1)/2

For normal magic squares of order n = 3, 4, 5, ...,
 the magic constants are: 15, 34, 65, 111, 175, 260, ... 
In this post, we will discuss how programmatically we can generate a magic square of size n. Before we go further, consider the below examples:

Magic Square of size 3
-----------------------
  2   7   6
  9   5   1
  4   3   8
Sum in each row & each column = 3*(3^2+1)/2 = 15


Magic Square of size 5
----------------------
  9   3  22  16  15
  2  21  20  14   8
 25  19  13   7   1
 18  12   6   5  24
 11  10   4  23  17
Sum in each row & each column = 5*(5^2+1)/2 = 65


Magic Square of size 7
----------------------
 20  12   4  45  37  29  28
 11   3  44  36  35  27  19
  2  43  42  34  26  18  10
 49  41  33  25  17   9   1
 40  32  24  16   8   7  48
 31  23  15  14   6  47  39
 22  21  13   5  46  38  30
Sum in each row & each column = 7*(7^2+1)/2 = 175
Did you find any pattern in which the numbers are stored?
In any magic square, the first number i.e. 1 is stored at position (n/2, n-1). Let this position be (i,j). 
The next number is stored at position (i-1, j+1) where we can consider each row & column as circular array i.e. they wrap around.

Three conditions hold:

1. The position of next number is calculated by decrementing row number of previous number by 1, and incrementing the column number of previous number by 1. 
At any time, if the calculated row position becomes -1, it will wrap around to n-1. Similarly, if the calculated column position becomes n, 
it will wrap around to 0.(OR)

2. If the magic square already contains a number at the calculated position, calculated column position will be decremented by 2, 
and calculated row position will be incremented by 1.

3. If the calculated row position is -1 &(AND) calculated column position is n, the new position would be: (0, n-2).

Example:
Magic Square of size 3
----------------------
 2  7  6
 9  5  1
 4  3  8 

Steps:
1. position of number 1 = (3/2, 3-1) = (1, 2)
2. position of number 2 = (1-1, 2+1) = (0, 0)
3. position of number 3 = (0-1, 0+1) = (3-1, 1) = (2, 1)
4. position of number 4 = (2-1, 1+1) = (1, 2)
   Since, at this position, 1 is there. So, apply condition 2.
   new position=(1+1,2-2)=(2,0)
5. position of number 5=(2-1,0+1)=(1,1)
6. position of number 6=(1-1,1+1)=(0,2)
7. position of number 7 = (0-1, 2+1) = (-1,3) // this is tricky, see condition 3 
   new position = (0, 3-2) = (0,1)
8. position of number 8=(0-1,1+1)=(-1,2)=(2,2) //wrap around
9. position of number 9=(2-1,2+1)=(1,3)=(1,0) //wrap around
 */
public class FormingMagicSquare {
	
	public void generateMagicSquare(int n) {

		int[][] magicSquare = new int[n][n];

		// initialize
		int i = n / 2;
		int j = n - 1;

		// fill the magic square
		for (int num = 1; num <= n * n;) {
			// condition 3. AND
			if (i == -1 && j == n) {
				i = 0;
				j = n - 2;
			} else {
				// condition 1. OR
				if (i < 0)
					i = n - 1; // wrap around

				if (j == n)
					j = 0;
			}

			// condition 2
			if (magicSquare[i][j] != 0) {
				i++;
				j -= 2;
				continue;
			} else {
				magicSquare[i][j] = num++;
			}

			// condition 1
			i--;
			j++;
		}
		
		for(int a=0;a<magicSquare.length;a++) {
			for(int b=0;b<magicSquare[0].length;b++) {
				System.out.print(magicSquare[a][b] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * A Magic Square is a n x n matrix of distinct element from 1 to n2 where the sum of any row, column or diagonal is always equal to same number.
Consider a 3 X 3 matrix, s, of integers in the inclusive range [1, 9] . We can convert any digit, a, to any other digit, b, in the range [1, 9] at cost |a – b|.
Given s, convert it into a magic square at minimal cost by changing zero or more of its digits. The task is to find minimum cost.
Note: The resulting matrix must contain distinct integers in the inclusive range [1, 9].

Examples:

Input : mat[][] = { { 4, 9, 2 },
                    { 3, 5, 7 },
                    { 8, 1, 5 }};
Output : 1
Given matrix s is not a magic square. To convert
it into magic square we change the bottom right 
value, s[2][2], from 5 to 6 at a cost of | 5 - 6 |
= 1.

Input : mat[][] = { { 4, 8, 2 },
                    { 4, 5, 7 },
                    { 6, 1, 6 }};
Output : 4
	 * @param s
	 * @return
	 */
	public int formingMagicSquare(int[][] s) {

		int[][][] possiblePermutations = { { { 8, 1, 6 }, { 3, 5, 7 }, { 4, 9, 2 } }, // 1

				{ { 6, 1, 8 }, { 7, 5, 3 }, { 2, 9, 4 } }, // 2

				{ { 4, 9, 2 }, { 3, 5, 7 }, { 8, 1, 6 } }, // 3

				{ { 2, 9, 4 }, { 7, 5, 3 }, { 6, 1, 8 } }, // 4

				{ { 8, 3, 4 }, { 1, 5, 9 }, { 6, 7, 2 } }, // 5

				{ { 4, 3, 8 }, { 9, 5, 1 }, { 2, 7, 6 } }, // 6

				{ { 6, 7, 2 }, { 1, 5, 9 }, { 8, 3, 4 } }, // 7

				{ { 2, 7, 6 }, { 9, 5, 1 }, { 4, 3, 8 } },// 8
		};

		int minCost = Integer.MAX_VALUE;
		for (int permutation = 0; permutation < 8; permutation++) {
			int permutationCost = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++)
					permutationCost += Math.abs(s[i][j] - possiblePermutations[permutation][i][j]);
			}
			minCost = Math.min(minCost, permutationCost);
		}
		return minCost;
	}
	
	private boolean isMagicSquare(int[][] square) {
		int sum = 0;
		for(int i=0;i<square.length;i++)
			sum += square[0][i];
		
		// check each row sum is same as sum
		for(int row = 1;row <= 2;row++) {
			int temp = 0;
			for(int col=0;col < square.length;col++) {
				temp += square[row][col];
			}
			if(temp != sum)
				return false;
		}
		
		// check each column sum is same as sum
		for(int row=0;row<square.length;row++) {
			int temp = 0;
			for(int col=0;col<square.length;col++) {
				temp += square[row][col];
			}
			if(temp != sum)
				return false;
		}
		
		// check diagonal 1 sum is same as sum
		int temp = 0;
		for(int row=0;row<square.length;row++) {
			temp += square[row][row];
		}
		if(temp != sum)
			return false;
		
		// check diagonal 2 sum is same as sum
		temp = 0;
		for(int row = square.length-1, col = 0;row>= 0 && col < square.length;row--,col++) {
			temp += square[row][col];
		}
		if(temp != sum)
			return false;
		
		return true;
	}
	
	public static void main(String[] args) {
		FormingMagicSquare ms = new FormingMagicSquare();
		ms.generateMagicSquare(3);
		
		int[][] s = new int[][]{{4,8,2},{4,5,7},{6,1,6}};
		System.out.println(ms.formingMagicSquare(s));
	}

}
