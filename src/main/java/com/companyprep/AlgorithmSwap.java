package com.companyprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You're a new Amazon Software Development Engineer (SDE). You're reading through your team's code and find an old
 * sorting algorithm. The following algorithm is used to sort an array of distinct n integers:
 * 
 * For the input array arr of size n do: Try to find the smallest pair of indices 0 <= i < j <= n-1 such that arr[i] >
 * arr[j]. Here smallest means usual alphabetical ordering of pairs, i.e. (i1, j1) < (i2, j2) if and only if i1 < i2 or
 * (i1 = i2 and j1 <j2). If there is no such pair, stop. Otherwise, swap a[i] and a[j] and repeat finding the next pair.
 * The algorithm seems to be correct, but the question is how efficient is it? Write a function that returns the number
 * of swaps performed by the above algorithm.
 * 
 * For example, if the initial array is [5,1,4,2], then the algorithm first picks pair (5,1) and swaps it to produce
 * array [1,5,4,2]. Next, it picks pair (5,4) and swaps it to produce array [1,4,5,2]. Next, pair (4,2) is picked and
 * swapped to produce array [1,2,5,4], and finally, pair (5,4) is swapped to produce the final sorted array [1,2,4,5],
 * so the number of swaps performed is 4.
 * 
 * Function Description
 * 
 * Complete the function howManySwaps in the editor below. The function should return an integer that denotes the number
 * of swaps performed by the proposed algorithm on the input array.
 * 
 * The function has the following parameter(s):
 * 
 * arr: integer array of size n with all unique elements
 * 
 * Constraints
 * 
 * 1 <= n <= 10^5
 * 
 * 1 <= arr[i] <= 10^9
 * 
 * all elements of arr are unique
 * 
 * Input Format Format for Custom Testing
 * 
 * Input from stdin will be processed as follows and passed to the function.
 * 
 * In the first line, there is a single integer n.
 * 
 * In the i-th of the next n lines, there is a single integer arr[i].
 * 
 * Sample Case
 * 
 * Sample Input 3
 * 
 * 7 1 2
 * 
 * Sample Output 2
 * 
 * Explanation : There are 3 elements in the array, 7, 1 and 2 respectively.
 * 
 * Initially, there are two pairs of indices i < j for which a[i] > a[j]. These pairs are (0, 1) and (0, 2). Since (0,
 * 1) is smaller of them, the algorithm swaps elements a[0] and a[1]. The resulting array is [1, 7, 2]. Next, in the
 * second iteration there is only a single pair of indices i < j for which a[i> a[j]. This pair is (1, 2) and the
 * algorithm swaps a[1] with a[2]. The resulting array is [1, 2, 7]. After that, the algorithm tries to find the next
 * pair of indices to swap but since there is none, the algorithm stops. The number of swaps it performed is 2.
 *
 * Another way:
 *
 * Given an array and a sorting algorithm, the sorting algorithm will do a selection swap. Find the number of swaps to
 * sort the array
 * 
 * Example 1: Input: [5, 4, 1, 2]
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * Swap index 0 with 1 to form the sorted array [4, 5, 1, 2].<br/>
 * Swap index 0 with 2 to form the sorted array [1, 5, 4, 2].<br/>
 * Swap index 1 with 2 to form the sorted array [1, 4, 5, 2].<br/>
 * Swap index 1 with 3 to form the sorted array [1, 2, 5, 4].<br/>
 * Swap index 2 with 3 to form the sorted array [1, 2, 4, 5].<br/>
 * 
 * TODO: logic not working. check further.
 * 
 * Category: Hard
 */
public class AlgorithmSwap {

	public int howManySwaps(int[] array) {
		System.out.println("Input :" + Arrays.toString(array));

		int len = array.length;
		int count = 0;
		boolean swapped = false;

		List<Point> list = new ArrayList<Point>();

		for (int i = 0; i < len; i++) {
			swapped = false;
			for (int j = 0; j < len - 1; j++) {
				if (array[j] > array[j + 1]) {
					// dont swap just yet. collect the indices
					Point p = new Point(j, j + 1);
					list.add(p);
				}
			}

			Collections.sort(list);
			if (!list.isEmpty()) {
				Point p = list.get(0);
				swap(array, p.x, p.y);
				swapped = true;
				count++;

				System.out.println("Swap ->" + Arrays.toString(array));
			}
			// clear list
			list.clear();

			// IF no two elements were swapped by inner loop, then break
			if (!swapped) {
				break;
			}
		}

		return count;
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	class Point implements Comparable<Point> {

		int x;
		int y;

		public Point(int i, int j) {
			this.x = i;
			this.y = j;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x < o.x || this.x == o.x && this.y < o.y) {
				return -1;
			} else if (this.x == o.x && this.y == o.y) {
				return 0;
			}
			return 1;
		}
	}

	public static void main(String[] args) {
		AlgorithmSwap as = new AlgorithmSwap();
		System.out.println(as.howManySwaps(new int[] { 7, 1, 2 }));
		System.out.println(as.howManySwaps(new int[] { 5, 4, 1, 2 }));
	}

}
