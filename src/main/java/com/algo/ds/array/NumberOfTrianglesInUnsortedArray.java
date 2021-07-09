package com.algo.ds.array;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/find-number-of-triangles-possible/
 * 
 * Given an unsorted array of positive integers. Find the number of triangles that can be formed with three different
 * array elements as three sides of triangles. For a triangle to be possible from 3 values, the sum of any two values
 * (or sides) must be greater than the third value (or third side). For example, if the input array is {4, 6, 3, 7}, the
 * output should be 3. There are three triangles possible {3, 4, 6}, {4, 6, 7} and {3, 6, 7}. Note that {3, 4, 7} is not
 * a possible triangle.
 * 
 * Time Complexity: O(n^2). The time complexity looks more because of 3 nested loops. If we take a closer look at the
 * algorithm, we observe that k is initialized only once in the outermost loop. The innermost loop executes at most O(n)
 * time for every iteration of outer most loop, because k starts from i+2 and goes upto n for all values of j.
 * Therefore, the time complexity is O(n^2).
 */
public class NumberOfTrianglesInUnsortedArray {

	// T - O(n ^ 2 + n log n)
	public int numberOfTriangles(int input[]) {
		Arrays.sort(input);

		int count = 0;

		for (int i = 0; i < input.length; i++) {
			// apply 2 pointer approach
			int left = 0;
			int right = i - 1;

			while (left < right) {
				if ((input[left] + input[right]) > input[i]) {
					count += (right - left);
					right--;
				} else {
					left++;
				}
			}
		}

		return count;

	}

	public static void main(String args[]) {

		NumberOfTrianglesInUnsortedArray not = new NumberOfTrianglesInUnsortedArray();
		System.out.println(not.numberOfTriangles(new int[] { 3, 4, 5, 6, 8, 9, 15 }));
		System.out.println(not.numberOfTriangles(new int[] { 3, 4, 6, 7 }));
	}
}
