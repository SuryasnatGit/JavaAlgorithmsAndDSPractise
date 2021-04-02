package com.algo.ds.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EPIArrayProblems {

	public static void main(String[] args) {
		EPIArrayProblems epi = new EPIArrayProblems();
		epi.oddEven(Arrays.asList(1, 2, 3, 4, 5, 6));
	}

	public void oddEven(List<Integer> list) {
		int nextEven = 0, nextOdd = list.size() - 1;

		while (nextEven < nextOdd) {
			if (list.get(nextEven) % 2 == 0) {
				nextEven++;
			} else {
				Collections.swap(list, nextEven, nextOdd--);
			}
		}

		System.out.println(list);
	}
}
