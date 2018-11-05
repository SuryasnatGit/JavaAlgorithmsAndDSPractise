package com.algo.ds.array;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/find-number-of-triangles-possible/
 * 
 * Given an unsorted array of positive integers. Find the number of triangles that can be formed
 * with three different array elements as three sides of triangles. For a triangle to be possible
 * from 3 values, the sum of any two values (or sides) must be greater than the third value (or
 * third side). For example, if the input array is {4, 6, 3, 7}, the output should be 3. There are
 * three triangles possible {3, 4, 6}, {4, 6, 7} and {3, 6, 7}. Note that {3, 4, 7} is not a
 * possible triangle.
 * 
 * Time Complexity: O(n^2). The time complexity looks more because of 3 nested loops. If we take a
 * closer look at the algorithm, we observe that k is initialized only once in the outermost loop.
 * The innermost loop executes at most O(n) time for every iteration of outer most loop, because k
 * starts from i+2 and goes upto n for all values of j. Therefore, the time complexity is O(n^2).
 */
public class NumberOfTrianglesInUnsortedArray {

	public int numberOfTriangles(int input[]) {
		Arrays.sort(input);

		int count = 0;
		// Fix the first element. We need to run till n-3 as
		// the other two elements are selected from arr[i+1...n-1]
		for (int i = 0; i < input.length - 2; i++) {
			// Initialize index of the rightmost third element
			int k = i + 2;

			// Fix the second element
			for (int j = i + 1; j < input.length; j++) {
				/*
				 * Find the rightmost element which is smaller than the sum of two fixed elements The important
				 * thing to note here is, we use the previous value of k. If value of arr[i] + arr[j-1] was greater
				 * than arr[k], then arr[i] + arr[j] must be greater than k, because the array is sorted.
				 */
				while (k < input.length && input[i] + input[j] > input[k]) {
					k++;
				}
				/*
				 * Total number of possible triangles that can be formed with the two fixed elements is k - j - 1.
				 * The two fixed elements are arr[i] and arr[j]. All elements between arr[j+1] to arr[k-1] can form
				 * a triangle with arr[i] and arr[j]. One is subtracted from k because k is incremented one extra in
				 * above while loop. k will always be greater than j. If j becomes equal to k, then above loop will
				 * increment k, because arr[k] + arr[i] is always/ greater than arr[k]
				 */
				if (k > j)
					count += k - j - 1;
			}
		}
		return count;

	}

	public static void main(String args[]) {
		int input[] = { 3, 4, 5, 6, 8, 9, 15 };
		NumberOfTrianglesInUnsortedArray not = new NumberOfTrianglesInUnsortedArray();
		System.out.println(not.numberOfTriangles(input));
	}
}
