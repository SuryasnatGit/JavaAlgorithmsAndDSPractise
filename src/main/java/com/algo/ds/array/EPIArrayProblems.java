package com.algo.ds.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EPIArrayProblems {

	public static void main(String[] args) {
		EPIArrayProblems epi = new EPIArrayProblems();
		epi.oddEven(Arrays.asList(1, 2, 3, 4, 5, 6));
		System.out.println(epi.plusOne(Arrays.asList(1, 2, 9)));
		System.out.println(epi.plusOne(Arrays.asList(9, 9)));
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

	// increment an arbitrary precision integer
	public List<Integer> plusOne(List<Integer> num) {
		int len = num.size() - 1;
		num.set(len, num.get(len) + 1);

		for (int i = len; i > 0 && num.get(i) == 10; i--) {
			num.set(i, 0);
			num.set(i - 1, num.get(i - 1) + 1);
		}

		if (num.get(0) == 10) {
			num.set(0, 1);
			num.add(0);
		}

		return num;
	}
}
