package com.algo.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a set of activities and start and end time of each activity, we need to find the maximum number of activities
 * that can be performed by a single person assuming that a person can only work on a single activity at a time. A
 * classic application of this problem is in scheduling a room for multiple competing events, each having its own time
 * requirements(start and end time). lets assume there exists n activities with start and end time as S(i/j) - F(i/j).
 * Two activities
 * 
 * Input:
 * 
 * (1, 4), (3, 5), (0, 6), (5, 7), (3, 8), (5, 9), (6, 10), (8, 11), (8, 12), (2, 13), (12, 14)
 * 
 * Output:
 * 
 * (1 4), (5 7), (8 11), (12 14)
 * 
 * @author surya
 *
 */
public class ActivitySelectionProblem {

	public void activitySelection(List<Pair> pairs) {
		// sort the pairs first
		Collections.sort(pairs, (a, b) -> a.getEnd() - b.getEnd());

		Set<Integer> result = new HashSet<>();

		// Add the first pair
		result.add(0);

		// start with the first activity
		int k = 0;
		for (int i = 1; i < pairs.size(); i++) {
			if (pairs.get(i).getStart() >= pairs.get(k).getEnd()) {
				result.add(i);
				k = i;
			}
		}

		for (int n : result)
			System.out.println(pairs.get(n));
	}

	public static void main(String[] args) {
		ActivitySelectionProblem a = new ActivitySelectionProblem();
		List<Pair> activities = Arrays.asList(new Pair(1, 4), new Pair(3, 5), new Pair(0, 6), new Pair(5, 7),
				new Pair(3, 8), new Pair(5, 9), new Pair(6, 10), new Pair(8, 11), new Pair(8, 12), new Pair(2, 13),
				new Pair(12, 14));

		a.activitySelection(activities);
	}
}
