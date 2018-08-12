package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given two arrays one with values and other with index where values should be positioned. Move values to correct
 * position.
 * 
 * Example:

Input:  arr[]   = [10, 11, 12];
        index[] = [1, 0, 2];
Output: arr[]   = [11, 10, 12]
        index[] = [0,  1,  2] 

Input:  arr[]   = [50, 40, 70, 60, 90]
        index[] = [3,  0,  4,  1,  2]
Output: arr[]   = [40, 60, 90, 50, 70]
        index[] = [0,  1,  2,  3,   4] 

 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/reorder-a-array-according-to-given-indexes/
 */
public class ReorderArrayByIndex {
    public void reorder(int input[], int index[]) {
        if(index.length != input.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0 ; i < index.length; i++) {
            while (index[i] != i) {
				int sIndex = index[index[i]];// 0
				int sVal = input[index[i]];// 40

				index[index[i]] = index[i];// index[1] = 0
				input[index[i]] = input[i];// input[1] = 40

				index[i] = sIndex;// index[0] = 0
				input[i] = sVal;// input[0] = 40
            }
        }
    }

    public static void main(String args[]) {
        int input[] = {50, 40, 70, 60, 90};
        int index[] = {3,  0,  4,  1,  2};
        ReorderArrayByIndex reorderArrayByIndex = new ReorderArrayByIndex();
        reorderArrayByIndex.reorder(input, index);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(index).forEach(i -> System.out.print(i + " "));
    }
}
