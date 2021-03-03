package com.companyprep.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptimizingBoxWeights {

	public List<Integer> minimalHeaviestSetA(List<Integer> arr) {
		// sort the array
		// Collections.sort(arr, (a,b) -> b-a);
		Collections.sort(arr);

		int sum = 0;
		for (int weight : arr) {
			sum += weight;
		}

		List<Integer> result = new ArrayList<>();

		int sumOfA = 0;
		for (int i = arr.size() - 1; i >= 0; i--) {
			sumOfA += arr.get(i);
			result.add(arr.get(i));

			if (sumOfA > sum / 2) {
				break;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		OptimizingBoxWeights obw = new OptimizingBoxWeights();
		System.out.println(obw.minimalHeaviestSetA(Arrays.asList(3, 7, 5, 6, 2)));
	}
}
