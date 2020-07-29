package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute
 * the researcher's h-index. https://leetcode.com/problems/h-index/.
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least
 * h citations each, and the other N − h papers have no more than h citations each."
 * 
 * Example:
 * 
 * Input: citations = [3,0,6,1,5] Output: 3 Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each
 * of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3
 * citations each and the remaining two with no more than 3 citations each, her h-index is 3. Note: If there are several
 * possible values for h, the maximum one is taken as the h-index.
 * 
 * First, you may ask why bucket sort. Well, the h-index is defined as the number of papers with reference greater than
 * the number. So assume n is the total number of papers, if we have n+1 buckets, number from 0 to n, then for any paper
 * with reference corresponding to the index of the bucket, we increment the count for that bucket. The only exception
 * is that for any paper with larger number of reference than n, we put in the n-th bucket.
 * 
 * Then we iterate from the back to the front of the buckets, whenever the total count exceeds the index of the bucket,
 * meaning that we have the index number of papers that have reference greater than or equal to the index. Which will be
 * our h-index result. The reason to scan from the end of the array is that we are looking for the greatest h-index. For
 * example, given array [3,0,6,5,1], we have 6 buckets to contain how many papers have the corresponding index. Hope to
 * image and explanation help.
 * 
 * Category : Medium
 */
public class HIndex {
	public int hIndex(int[] citations) {
		int length = citations.length;
		int[] count = new int[length + 1];
		for (int c : citations) {
			if (c < length) {
				count[c]++;
			} else {
				count[length]++;
			}
		}

		int sum = 0;
		int max = 0;
		for (int i = length; i >= 0; i--) {
			sum += count[i];
			if (sum >= i)
				return i;
		}
		return max;
	}

	/**
	 * O(n log n). Using binary search
	 * 
	 * @param citations
	 * @return
	 */
	public int hindex_1(int[] citations) {
		Arrays.sort(citations);
		int start = 0;
		int end = citations.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (citations[mid] >= citations.length - mid) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return citations.length - start;
	}

	public static void main(String args[]) {
		HIndex hi = new HIndex();
		// int[] input = { 0, 1, 1, 3, 1, 6, 7, 8 };
		int[] input = { 3, 0, 6, 1, 5 };
		System.out.println(hi.hIndex(input));
		System.out.println(hi.hindex_1(input));
	}
}

// 0 1 2 6 6 6 6 7
// 0 1 2 3 4 5 6 7
// 0 1 1 1 3 6 7 8
// 0 1 2 3 4 5 6 7

// 0 1 2 5 6
