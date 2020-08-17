package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationOfArrays {

	public static void main(String[] args) throws java.lang.Exception {
		CombinationOfArrays coa = new CombinationOfArrays();

		ArrayList<List<Integer>> arrays = new ArrayList<List<Integer>>();

		Integer[] arr1 = new Integer[] { 1, 2, 3 };
		Integer[] arr2 = new Integer[] { 4, 5 };
		Integer[] arr3 = new Integer[] { 6, 7 };

		arrays.add(Arrays.asList(arr1));
		arrays.add(Arrays.asList(arr2));
		arrays.add(Arrays.asList(arr3));

		List<List<Integer>> res = coa.getCombination(0, arrays);

		for (List<Integer> list : res) {
			for (int val : list) {
				System.out.print(val + "==");
			}
			System.out.println();
		}
	}

	private List<List<Integer>> getCombination(int currentIndex, List<List<Integer>> containers) {
		if (currentIndex == containers.size()) {
			// Skip the items for the last container
			List<List<Integer>> combinations = new ArrayList<List<Integer>>();
			combinations.add(new ArrayList<Integer>());
			return combinations;
		}

		List<List<Integer>> res = new ArrayList<List<Integer>>();

		// Take out the current one first, and add them one by one later to form different solutions
		List<Integer> curList = containers.get(currentIndex);

		// Get combination from next index
		List<List<Integer>> suffixList = getCombination(currentIndex + 1, containers);

		for (int i = 0; i < curList.size(); i++) {
			Integer item = curList.get(i);

			// Add the current Item to all possible combinations
			if (suffixList != null) {
				for (List<Integer> suffix : suffixList) {
					List<Integer> nextCombination = new ArrayList<Integer>(); // Form a new combination
					nextCombination.add(item); // Put one
					nextCombination.addAll(suffix); // Put all
					res.add(nextCombination);
				}
			}
		}

		return res;
	}
}
