package com.companyprep;

import java.util.HashMap;
import java.util.Map;

/**
 * Two friends Kohli and Dhoni want to test their friendship to check how compatible they are. Given a list of n movies
 * numbered 1,2,3....n and asked both of them to rank the movies. Design an algorithm to find compatibility difference
 * between them.
 * 
 * Compatibility difference is the number of mis-matches in the relative rankings of the same movie given by them i.e.
 * if Kohli ranks Movie 3 before Movie 2 and Dhoni ranks Movie 2 before Movie 3 then its a relative ranking mis-match
 * Compatibility difference is the maximum number of mis-matches
 * 
 * Sample Input
 * 
 * 5 31245 32415 Sample Output
 * 
 * 2 Explanation
 * 
 * Movies are 1,2,3,4,5. Kohli ranks them 3,1,2,4,5, Dhoni ranks them 3,2,4,1,5. Compatibility difference is 2 because
 * Kohli ranks movie 1 before 2,4 but Dhoni ranks it after.
 * 
 * Category : Easy
 * 
 */
public class CompatibilityDifference {

	public int maxMismatch(int[] A, int[] B) {
		int length = A.length;

		// mapping of movie ID to index in array
		Map<Integer, Integer> moviePosMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < length; i++) {
			moviePosMap.put(A[i], i);
		}

		int max = 0;
		for (int i = 0; i < length; i++) {
			int bMoviePos = moviePosMap.get(B[i]);
			if (bMoviePos != i) {
				max = Math.max(max, Math.abs(bMoviePos - i));
			}
		}

		return max;
	}

	public static void main(String[] args) {
		CompatibilityDifference cd = new CompatibilityDifference();
		System.out.println(cd.maxMismatch(new int[] { 3, 1, 2, 4, 5 }, new int[] { 3, 2, 4, 1, 5 }));
	}

}
