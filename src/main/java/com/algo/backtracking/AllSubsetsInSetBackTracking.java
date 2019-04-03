package com.algo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a set of positive integers, find all its subsets. Examples:
 * 
 * Input : 1 2 3
 * 
 * Output : // this space denotes null element.
 * 
 * 1
 * 
 * 1 2
 * 
 * 1 2 3
 * 
 * 1 3
 * 
 * 2
 * 
 * 2 3
 * 
 * 3
 * 
 * Input : 1 2
 * 
 * Output :
 * 
 * 1
 * 
 * 2
 * 
 * 1 2
 * 
 * Start with an empty node. Then, take one element. Let it be at an index 'i' of the given set. The
 * tree can be constructed by trying out the elements at the indexes after i. Each possibility makes
 * a new branch. The branching ends when we have tried out all the possibilities.
 * 
 * @author surya
 *
 */
public class AllSubsetsInSetBackTracking {

	private Set<List<Integer>> subSets = new HashSet<>();

	public Set<List<Integer>> allSubsets(List<Integer> input) {
		Collections.sort(input);

		subSets.add(new ArrayList<>()); // for empty subset

		int index = 0;
		backtrack(input, index, new ArrayList<>());

		return subSets;
	}

	private void backtrack(List<Integer> input, int index, List<Integer> partial) {
		for (int i = index; i < input.size(); i++) {
			List<Integer> subSet = new ArrayList<>(partial);
			subSet.add(input.get(i));
			subSets.add(subSet);
			backtrack(input, i + 1, subSet);
		}
	}

	public static void main(String[] args) {
		AllSubsetsInSetBackTracking subsets = new AllSubsetsInSetBackTracking();
		Set<List<Integer>> allSubsets = subsets.allSubsets(Arrays.asList(1, 4, 4));
		allSubsets.forEach(s -> System.out.println(s));
	}

}
