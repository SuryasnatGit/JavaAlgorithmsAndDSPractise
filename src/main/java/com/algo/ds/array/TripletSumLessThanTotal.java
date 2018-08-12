package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given array with unique numbers and a total, find all triplets whose sum is less than total
 *
 * http://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/.
 * 
 * Input : arr[] = {-2, 0, 1, 3} sum = 2. Output : 2 Explanation : Below are triplets with sum less
 * than 2 (-2, 0, 1) and (-2, 0, 3)
 * 
 * Input : arr[] = {5, 1, 3, 4, 7} sum = 12. Output : 4 Explanation : Below are triplets with sum
 * less than 4 (1, 3, 4), (1, 3, 5), (1, 3, 7) and (1, 4, 5).
 * 
 * Complexity - O(n^2)
 */
public class TripletSumLessThanTotal {

    public int findAllTriplets(int input[], int total) {
		Arrays.sort(input); // O(n log n)
        int result = 0;
		for (int i = 0; i < input.length - 2; i++) { // O(n)
            int j = i + 1;
            int k = input.length - 1;

			while (j < k) { // O(n)
                if (input[i] + input[j] + input[k] >= total) {
                    k--;
                } else {
                    result += k - j;
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int input[] = {5, 1, 3, 4, 7};
        TripletSumLessThanTotal tt = new TripletSumLessThanTotal();
        System.out.print(tt.findAllTriplets(input, 12));
    }
}
