package com.companyprep;

import java.util.Arrays;

/**
 * You are on a flight and wanna watch two movies during this flight. You are given int[] movie_duration which includes
 * all the movie durations. You are also given the duration of the flight which is d in minutes. Now, you need to pick
 * two movies and the total duration of the two movies is less than or equal to (d - 30min). Find the pair of movies
 * with the longest total duration. If multiple found, return the pair with the longest movie.
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

	// its a 2 sum closest problem
	// time - O(n log n) Space - O(1)
	public int[] findPair1(int flightDuration, int[] movieDurations) {
		int[] result = new int[2];

		Arrays.sort(movieDurations);
		int minDiff = Integer.MAX_VALUE;
		for (int low = 0, high = movieDurations.length - 1; low < high;) {
			int diff = flightDuration - 30 - (movieDurations[low] + movieDurations[high]);
			if (diff > 0) {
				if (diff < minDiff) {
					minDiff = diff;
					result[0] = movieDurations[low];
					result[1] = movieDurations[high];
				}
				low++;
			} else if (diff < 0) {
				high--;
			} else {
				result[0] = movieDurations[low];
				result[1] = movieDurations[high];
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		MoviesOnFlight m = new MoviesOnFlight();
		int[] res = m.findPair(250, new int[] { 90, 85, 75, 60, 120, 150, 125 });
		System.out.println(Arrays.toString(res));
		int[] res1 = m.findPair1(250, new int[] { 90, 85, 75, 60, 120, 150, 125 });
		System.out.println(Arrays.toString(res1));
	}

}
