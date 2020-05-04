package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * https://www.geeksforgeeks.org/find-k-pairs-smallest-sums-two-arrays/
 * 
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * 
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * 
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * 
 * Example 1:
 * 
 * Given nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 
 * Return: [1,2],[1,4],[1,6]
 * 
 * The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 
 * Example 2:
 * 
 * Given nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 
 * Return: [1,1],[1,1]
 * 
 * The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 
 * Example 3:
 * 
 * Given nums1 = [1,2], nums2 = [3], k = 3
 * 
 * Return: [1,3],[2,3]
 * 
 * All possible pairs are returned from the sequence: [1,3],[2,3]
 * 
 * Category : Medium
 *
 */
public class KSmallestSumPairs {

	public static void main(String[] args) {
		KSmallestSumPairs k = new KSmallestSumPairs();
		int[] nums1 = { 1, 1, 2 };
		int[] nums2 = { 1, 2, 3 };
		List<int[]> pairs = k.kSmallestPairs(nums1, nums2, 2);
		pairs.forEach(a -> System.out.println(Arrays.toString(a)));
	}

	// Method 1 - Simple
	// Find all pairs and store their sums. Time complexity of this step is O(n1 * n2) where n1 and n2 are sizes of
	// input arrays. Then sort pairs according to sum. Time complexity of this step is O(n1 * n2 * log (n1 * n2))
	// Overall Time Complexity : O(n1 * n2 * log (n1 * n2))

	// Method 2 - Using priority queue. T - O(k log k)
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> res = new ArrayList<int[]>();
		PriorityQueue<Pair> heap = new PriorityQueue<Pair>(k);

		for (int n1 : nums1) {
			for (int n2 : nums2) {
				int sum = n1 + n2;
				Pair p = new Pair(n1, n2);

				if (heap.size() < k) {
					heap.offer(p);
				} else {
					if (sum < heap.peek().sum) {
						heap.poll();
						heap.offer(p);
					}
				}
			}
		}

		while (!heap.isEmpty()) {
			Pair p = heap.poll();
			res.add(new int[] { p.num1, p.num2 });
		}

		return res;
	}
}

class Pair implements Comparable<Pair> {
	int num1;
	int num2;
	int sum;

	Pair(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
		this.sum = num1 + num2;
	}

	public int compareTo(Pair that) {
		return that.sum - this.sum;
	}
}
