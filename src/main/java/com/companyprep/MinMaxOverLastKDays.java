package com.companyprep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A set of integer values are being received (1 per day). Store these values and at any given time, retrieve the min
 * and max value over the last k days. What structures would you use for storing and retrieving ?
 * 
 * TODO. brute force way. can it be better?
 */
public class MinMaxOverLastKDays {

	private List<Integer> numbers = new ArrayList<Integer>();
	private PriorityQueue<Integer> min = new PriorityQueue<Integer>();
	private PriorityQueue<Integer> max = new PriorityQueue<Integer>();

	public void add(int num) {
		numbers.add(num);
	}

	public int getMin(int k) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = numbers.size() - 1; i >= 0 && k > 0; i--, k--) {
			result.add(numbers.get(i));
		}
		Collections.sort(result);
		int val = result.get(0);
		return val;
	}

	public int getMax(int k) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = numbers.size() - 1; i >= 0 && k > 0; i--, k--) {
			result.add(numbers.get(i));
		}
		Collections.sort(result);
		int val = result.get(result.size() - 1);
		return val;
	}

	public static void main(String[] args) {
		MinMaxOverLastKDays minmax = new MinMaxOverLastKDays();
		minmax.add(5); // day 1
		minmax.add(3); // day 2
		minmax.add(7); // day 3
		minmax.add(1); // day 3

		System.out.println(minmax.getMin(1));
		System.out.println(minmax.getMax(1));
		System.out.println(minmax.getMin(2));
		System.out.println(minmax.getMax(2));
		System.out.println(minmax.getMin(3));
		System.out.println(minmax.getMax(3));
	}
}
