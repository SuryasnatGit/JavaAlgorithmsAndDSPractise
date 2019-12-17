package com.algo.greedy;

import java.util.Arrays;
import java.util.List;

/**
 * https://www.techiedelight.com/shortest-superstring-problem/
 * 
 * This is a NP hard problem but it can be solved by taking a greedy approach.
 *
 * Category : Hard
 */
public class ShortestSuperstringProblem {

	public String findShortestSuperstring(List<String> list) {
		int n = list.size();

		// run n-1 times to consider every pair
		while (n != 1) {
			int max = Integer.MIN_VALUE;

			// store index of strings involved in max overlap
			int p = -1, q = -1;

			String resStr = "";

			for (int i = 1; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					StringBuilder sb = new StringBuilder();
					int res = findOverlappingLength(list.get(i), list.get(j), sb);

					if (max < res) {
						max = res;
						resStr = sb.toString();
						p = i;
						q = j;
					}
				}
			}

			// ignore last element in next cycle
			n--;

			if (max == Integer.MIN_VALUE) {
				list.set(0, list.get(0) + list.get(n));
			} else {
				list.set(p, resStr);
				list.set(q, list.get(n));
			}
		}

		return list.get(0);
	}

	private int findOverlappingLength(String s1, String s2, StringBuilder sb) {
		int min = Integer.min(s1.length(), s2.length());
		int max = Integer.MIN_VALUE;

		// check s1 suffix and s2 prefix
		for (int i = 1; i <= min; i++) {
			if (s1.substring(s1.length() - i).equals(s2.substring(0, i))) {
				if (max < i) {
					max = i;
					sb.append(s1).append(s2.substring(i));
				}
			}
		}

		// check s1 prefix and s2 suffix
		for (int i = 1; i <= min; i++) {
			if (s2.substring(s2.length() - i).equals(s1.substring(0, i))) {
				if (max < i) {
					max = i;
					sb.append(s2).append(s1.substring(i));
				}
			}
		}

		return max;
	}

	// Shortest Superstring Problem
	public static void main(String[] args) {

		ShortestSuperstringProblem sp = new ShortestSuperstringProblem();
		List<String> list = Arrays.asList("CATGC", "CTAAGT", "GCTA", "TTCA", "ATGCATC");

		System.out.println("The Shortest Superstring is " + sp.findShortestSuperstring(list));
	}
}
