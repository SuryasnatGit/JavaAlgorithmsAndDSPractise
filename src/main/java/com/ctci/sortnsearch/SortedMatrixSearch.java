

package com.ctci.sortnsearch;


/**
 * Sorted Matrix Search: Given an M x N matrix in which each row and each column is sorted in ascending order, write a
 * method to find an element.
 * 
 * @author ctsuser1
 */
public class SortedMatrixSearch {

    /**
     * As a first approach, we can do binary search on every row to find the element. This algorithm will be OeM
     * log(N)),since there are M rows and it takes O(log(N)) time to search each one.
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

    // TODO: implement this
    public boolean search_binarySearch(int[][] matrix, int elem) {

    }
}
