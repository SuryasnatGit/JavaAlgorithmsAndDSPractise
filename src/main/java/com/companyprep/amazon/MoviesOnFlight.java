package com.companyprep.amazon;

import java.util.Arrays;

/**
 * You are on a flight and wanna watch two movies during this flight. You are given int[]
 * movie_duration which includes all the movie durations. You are also given the duration of the
 * flight which is d in minutes. Now, you need to pick two movies and the total duration of the two
 * movies is less than or equal to (d - 30min). Find the pair of movies with the longest total
 * duration. If multiple found, return the pair with the longest movie.
 * 
 * e.g. Input:
 * 
 * movie_duration: [90, 85, 75, 60, 120, 150, 125]
 * 
 * d: 250
 * 
 * Output:
 * 
 * [90, 125]
 * 
 * 90min + 125min = 215 is the maximum number within 220 (250min - 30min)
 * 
 * 
 * @author M_402201
 *
 */
public class MoviesOnFlight {

	public int[] findPair(int flightDuration, int[] movieDurations) {

		// crude way. complexity - O(n ^ 2)
		int[] result = new int[2];
		int movieWatchTime = flightDuration - 30;
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < movieDurations.length; i++) {
			for (int j = i + 1; j < movieDurations.length; j++) {
				if (movieDurations[i] + movieDurations[j] < movieWatchTime) {
					int actualDiff = movieWatchTime - (movieDurations[i] + movieDurations[j]);
					if (minDiff > actualDiff) {
						minDiff = actualDiff;
						result[0] = movieDurations[i];
						result[1] = movieDurations[j];
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		MoviesOnFlight m = new MoviesOnFlight();
		int[] res = m.findPair(250, new int[] { 90, 85, 75, 60, 120, 150, 125 });
		System.out.println(Arrays.toString(res));
	}

}
