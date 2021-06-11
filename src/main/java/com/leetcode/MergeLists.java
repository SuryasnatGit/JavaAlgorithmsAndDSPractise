package com.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeLists {

	public List<Integer> mergeTwoList(List<Integer> list1, List<Integer> list2) {
		List<Integer> result = new ArrayList<>();
		int index1 = 0;
		int index2 = 0;
		while (index1 < list1.size() && index2 < list2.size()) {
			int number1 = list1.get(index1);
			int number2 = list2.get(index2);
			if (number1 <= number2) {
				result.add(number1);
				index1++;
			} else {
				result.add(number2);
				index2++;
			}
		}
		while (index1 < list1.size()) {
			result.add(list1.get(index1++));
		}
		while (index2 < list2.size()) {
			result.add(list2.get(index2++));
		}
		return result;
	}

	public List<Integer> mergeKList(List<List<Integer>> input) {
		PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
			@Override
			public int compare(Number num1, Number num2) {
				return num1.value - num2.value;
			}
		});
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).size() == 0) {
				continue;
			}
			minNumber.add(new Number(input.get(i).get(0), 0, i));
		}
		while (!minNumber.isEmpty()) {
			Number min = minNumber.poll();
			result.add(min.value);
			if (min.index < input.get(min.listIndex).size() - 1) {
				minNumber.add(new Number(input.get(min.listIndex).get(min.index + 1), min.index + 1, min.listIndex));
			}
		}
		return result;

	}

	class Number {
		int value;
		int index;
		int listIndex;

		public Number(int value, int index, int listIndex) {
			this.value = value;
			this.index = index;
			this.listIndex = listIndex;
		}
	}

	public List<Integer> mergeKListWithIterator(List<Iterator<Integer>> input) {
		PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
			@Override
			public int compare(Number num1, Number num2) {
				return num1.value - num2.value;
			}
		});
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).hasNext()) {
				minNumber.add(new Number(input.get(i).next(), 0, i));
			}
		}
		while (!minNumber.isEmpty()) {
			Number min = minNumber.poll();
			if (input.get(min.listIndex).hasNext()) {
				minNumber.add(new Number(input.get(min.listIndex).next(), 0, min.listIndex));
			}
			result.add(min.value);
		}
		return result;
	}
}
