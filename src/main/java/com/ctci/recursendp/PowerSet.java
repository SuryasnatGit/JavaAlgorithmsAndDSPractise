
package com.ctci.recursendp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Power Set: Write a method to return all subsets of a set.
 * 
 * Power Set Power set P(S) of a set S is the set of all subsets of S. For example S = {a, b, c}
 * then P(s) = {{}, {a}, {b}, {c}, {a,b}, {a, c}, {b, c}, {a, b, c}}. If S has n elements in it then
 * P(s) will have 2^n elements
 * 
 * @author ctsuser1
 */
public class PowerSet {

	/**
	 * Assuming that we're going to be returning a list of subsets, then our best case time is actually
	 * the total number of elements across all of those subsets. There are 2^n subsets and each of the n
	 * elements will be contained in half of the subsets (which 2^(n-1) subsets). Therefore, the total
	 * number of elements across all of those subsets is n * 2^n-1. We will not be able to beat O (n *
	 * 2^n) in space or time complexity.
	 * 
	 * @return
	 */
	public List<List<Object>> getSubSets_recursion(List<Object> input, int index) {
		List<List<Object>> result;
		if (input.size() == index) { // base case. add empty set
			result = new ArrayList<>();
			result.add(new ArrayList<>());
		} else {
			result = getSubSets_recursion(input, index + 1);
			Object item = input.get(index);
			List<List<Object>> moreSubSets = new ArrayList<>();
			for (List<Object> subSet : result) {
				List<Object> newSubSet = new ArrayList<>();
				newSubSet.addAll(subSet);
				newSubSet.add(item);
				moreSubSets.add(newSubSet);
			}
			result.addAll(moreSubSets);
		}
		return result;
	}

	/**
	 * Recall that when we're generating a set, we have two choices for each
	 * element: (1) the element is in the set (the "yes" state) or (2) the element
	 * is not in the set (the "no" state).This means that each subset is a sequence
	 * of yess' and nos' -e.g., "yes, yes, no, no, yes, no" This gives us 2^n
	 * possible subsets. How can we iterate through all possible sequences of"yes"
	 * and "no" states for all elements? If each "yes" can be treated as a 1 and
	 * each "no" can be treated as a 0, then each subset can be represented as a
	 * binary string. Generating all subsets, then, really just comes down to
	 * generating all binary numbers (that is, all integers). We iterate through all
	 * numbers from empty set{} to 2^n(exclusive) and translate the binary
	 * representation of the numbers into a set. Easy!
	 * 
	 * time - O(n * 2^n) Space - O(1)
	 * 
	 * @param input
	 * @return
	 */
	public List<List<Integer>> getSubSets_combinatorics(List<Integer> input) {
		Collections.sort(input);
		List<List<Integer>> result = new ArrayList<>();
		int max = 1 << input.size(); // 2^n
		for (int i = 0; i < max; i++) {
			List<Integer> subSets = new ArrayList<>();
			for (int j = 0; j < input.size(); j++) {
				// (1<<j) is a number with jth bit 1
				// so when we 'and' them with the
				// subset number we get which numbers
				// are present in the subset and which
				// are not
				if ((i & (1 << j)) > 0) {
					subSets.add(input.get(j));
				}
			}
			// Collections.sort(subSets);
			result.add(subSets);
		}
		return result;
	}

	public Set<String> getSubSets_distinct_lexiorder(List<Integer> input) {
		Collections.sort(input);
		Set<String> result = new HashSet<>();
		int max = 1 << input.size(); // 2^n
		for (int i = 0; i < max; i++) {
			String subSets = "";
			for (int j = 0; j < input.size(); j++) {
				// (1<<j) is a number with jth bit 1
				// so when we 'and' them with the
				// subset number we get which numbers
				// are present in the subset and which
				// are not
				if ((i & (1 << j)) > 0) {
					subSets += input.get(j) + " ";
				}
			}
			result.add(subSets);
		}
		return result;
	}

	public static void main(String[] args) {
		PowerSet ps = new PowerSet();
		List<Object> input = Arrays.asList("a", "b", "c");
		List<List<Object>> res = ps.getSubSets_recursion(input, 0);
		res.forEach(o -> System.out.println(o));
		int i = 1;
		i = i >> 1;
		System.out.println(i);
		int m = 1 << 3;
		System.out.println(m);
		List<Integer> list = Arrays.asList(3, 4, 1);
		List<List<Integer>> res1 = ps.getSubSets_combinatorics(list);
		res1.forEach(o -> System.out.println(o));

		List<Integer> list1 = Arrays.asList(3, 4, 3);
		Set<String> res2 = ps.getSubSets_distinct_lexiorder(list1);
		res2.forEach(o -> System.out.println(o));

	}

}
