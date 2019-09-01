package com.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DistinctNumbers {

	public static void main(String[] args) {
		DistinctNumbers num = new DistinctNumbers();

		// TC 1
		int[] a = { 2, 3, 6, 9, 1 };
		int[] b = { 3, 6, 10 };

		// TC 2
		// int[] a = null;
		// int[] b = null;

		// TC 3
		// int[] a = { 2, 3, 6, 9, 1 };
		// int[] b = null;

		// TC 4
		// int[] a = null;
		// int[] b = { 3, 6, 10 };
		System.out.println(num.findNumbers(a, b));
	}

	public List<Integer> findNumbers(int[] a, int[] b) {
		List<Integer> result = new ArrayList<>();

		// check for boundry conditions
		Set<Integer> set = new HashSet<>();
		if (b == null) {
			if (a != null)
				result = Arrays.stream(a).boxed().collect(Collectors.toList());
			return result;
		}
		if (a == null)
			return result;

		for (int i : b) {
			set.add(i);
		}

		for (int i : a) {
			if (!set.contains(i))
				result.add(i);
		}
		return result;
	}

}
