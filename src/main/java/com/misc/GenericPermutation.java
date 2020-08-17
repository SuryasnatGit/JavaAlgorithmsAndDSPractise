package com.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generate different permutations of the elements which has generic type.
 * 
 * @param <T>
 */
public class GenericPermutation<T extends Comparable<T>> {

	public List<List<T>> permutation(T[] arr) {
		List<List<T>> res = new ArrayList<List<T>>();
		List<T> list = new ArrayList<>();

		Arrays.sort(arr, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});

		Set<Integer> visited = new HashSet<>();

		helper(res, list, visited, arr);

		return res;
	}

	private void helper(List<List<T>> res, List<T> list, Set<Integer> visited, T[] arr) {
		if (arr.length == list.size()) {
			res.add(new ArrayList<T>(list));
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i) || (i != 0 && arr[i - 1] == arr[i] && !visited.contains(i - 1))) {
				continue;
			}

			visited.add(i);
			list.add(arr[i]);
			helper(res, list, visited, arr);

			// backtrack
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}

	public static void main(String[] args) {
		GenericPermutation<Integer> gp = new GenericPermutation<>();
		System.out.println(gp.permutation(new Integer[] { 5, 3, 8 }));
	}
}
