

package com.ctci.sortnsearch;


/**
 * CTCI - 10.9
 * 
 * Sorted Matrix Search: Given an M x N matrix in which each row and each column
 * is sorted in ascending order, write a method to find an element.
 * 
 * Input : mat[4][4] = { {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}};
              x = 29
Output : Found at (2, 1)

Input : mat[4][4] = { {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}};
              x = 100
Output : Element not found
 * 
 * @author ctsuser1
 */
public class SortedMatrixSearch {

    /**
	 * Time Complexity: O(n)
	 * 
	 * The above approach will also work for m x n matrix (not only for n x n).
	 * Complexity would be O(m + n).
	 * 
	 * @param matrix
	 * @return
	 */
    public boolean search_naive(int[][] matrix, int elem) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == elem)
                return true;
            else if (matrix[row][col] > elem)
                col--;
            else
                row++;
        }
        return false;
    }

	/**
	 * Time complexity: We are given a n*n matrix, the algorithm can be seen as
	 * recurring for 3 matrices of size n/2 x n/2. Following is recurrence for time
	 * complexity
	 * 
	 * T(n) = 3T(n/2) + O(1) The solution of recurrence is O(n1.58) using Master
	 * Method.
	 * 
	 * @param matrix
	 * @param elem
	 */
	public void search_binarySearch(int[][] matrix, int elem) {
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				search(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, matrix[i][j]);
    }

	// A divide and conquer method to search a given key in mat[]
	// in rows from fromRow to toRow and columns from fromCol to
	// toCol
	public static void search(int[][] mat, int fromRow, int toRow, int fromCol, int toCol, int key) {
		// Find middle and compare with middle
		int i = fromRow + (toRow - fromRow) / 2;
		int j = fromCol + (toCol - fromCol) / 2;
		if (mat[i][j] == key) // If key is present at middle
			System.out.println("Found " + key + " at " + i + " " + j);
		else {
			// right-up quarter of matrix is searched in all cases.
			// Provided it is different from current call
			if (i != toRow || j != fromCol)
				search(mat, fromRow, i, j, toCol, key);

			// Special case for iteration with 1*2 matrix
			// mat[i][j] and mat[i][j+1] are only two elements.
			// So just check second element
			if (fromRow == toRow && fromCol + 1 == toCol)
				if (mat[fromRow][toCol] == key)
					System.out.println("Found " + key + " at " + fromRow + " " + toCol);

			// If middle key is lesser then search lower horizontal
			// matrix and right hand side matrix
			if (mat[i][j] < key) {
				// search lower horizontal if such matrix exists
				if (i + 1 <= toRow)
					search(mat, i + 1, toRow, fromCol, toCol, key);
			}

			// If middle key is greater then search left vertical
			// matrix and right hand side matrix
			else {
				// search left vertical if such matrix exists
				if (j - 1 >= fromCol)
					search(mat, fromRow, toRow, fromCol, j - 1, key);
			}
		}
	}
}
