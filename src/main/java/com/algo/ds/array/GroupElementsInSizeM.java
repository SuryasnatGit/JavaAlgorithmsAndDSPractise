package com.algo.ds.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * http://www.careercup.com/question?id=6026101998485504.
 * 
 * Given an unordered array of positive integers, create an algorithm that makes sure no group of integers of size
 * bigger than M have the same integers.
 * 
 * This answer two questions. Group elements in size m such that in every group only unique elements are possible. It
 * also answers question where rearrange array such that same elements are exactly m distance from each other.
 * 
 * Input: 2,1,1,1,3,4,4,4,5 M = 2 Output: 2,1,1,3,1,4,4,5,4
 * 
 * TODO : to understand properly
 * 
 * Category : Medium
 */
public class GroupElementsInSizeM {

	public boolean group(int input[], int m) {
		System.out.println("Input : " + Arrays.toString(input) + " m : " + m);
		Map<Integer, Integer> count = new HashMap<Integer, Integer>();
		for (Integer i : input) {
			count.put(i, count.getOrDefault(i, 0) + 1);
		}

		PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(count.size(), (p1, p2) -> p2.count - p1.count);
		for (Integer s : count.keySet()) {
			int c = count.get(s);
			// if any count is greater than len/m then this arrangement is not possible
			if (c > Math.ceil(input.length * 1.0 / m)) {
				return false;
			}
			maxHeap.offer(new Pair(s, c));
		}
		System.out.println(maxHeap);
		int current = 0;
		int start = current;
		while (maxHeap.size() > 0) {
			Pair p = maxHeap.poll();
			int i = 0;
			while (i < p.count) {
				input[start] = p.num;
				start = start + m;
				if (start >= input.length) {
					current++;
					start = current;
				}
				i++;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		int input[] = { 2, 1, 5, 1, 3, 5, 3, 3, 4 };
		int input1[] = { 1, 2, 3, 8, 8, 8, 7, 8 };
		GroupElementsInSizeM gps = new GroupElementsInSizeM();
		System.out.println(gps.group(input, 3));
		System.out.println(gps.group(input1, 3));

	}
}

class Pair {
	int num;
	int count;

	Pair(int num, int count) {
		this.count = count;
		this.num = num;
	}

	@Override
	public String toString() {
		return "Pair [num=" + num + ", count=" + count + "]";
	}

}
