package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * 
 * For example, given two 1d vectors:
 * 
 * v1 = [1, 2] v2 = [3, 4, 5, 6] By calling next repeatedly until hasNext returns false, the order of elements returned
 * by next should be: [1, 3, 2, 4, 5, 6].
 * 
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * 
 * Clarification for the follow up question - Update (2015-09-18): The "Zigzag" order is not clearly defined and is
 * ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given
 * the following input:
 * 
 * [1,2,3] [4,5,6,7] [8,9] It should return [1,4,8,2,5,9,3,6,7].
 *
 */
/*
 * public class ZigZagIterator {
 * 
 * List<Integer> v1 = null; List<Integer> v2 = null; int pos1 = 0; int pos2 = 0; boolean isFirst = true;
 * 
 * public ZigZagIterator(List<Integer> v1, List<Integer> v2) { this.v1 = v1; this.v2 = v2; this.pos1 = 0; this.pos2 = 0;
 * }
 * 
 * public int next() { int res = -1; if (isFirst) { if (pos1 < v1.size()) { res = v1.get(pos1++); } else { res =
 * v2.get(pos2++); } } else { if (pos2 < v2.size()) { res = v2.get(pos2++); } else { res = v1.get(pos1++); } }
 * 
 * isFirst = !isFirst; return res; }
 * 
 * public boolean hasNext() { if (pos1 < v1.size() || pos2 < v2.size()) { return true; } return false; } }
 */

// Extend to K
class ZigZagIteratorK {

	private List<Iterator<Integer>> iterators = null;
	private int turn;

	public ZigZagIteratorK(List<List<Integer>> vectors) {
		iterators = new ArrayList<Iterator<Integer>>();
		for (List<Integer> vector : vectors) {
			if (vector.size() > 0) {
				iterators.add(vector.iterator());
			}
		}
		turn = 0;
	}

	// Remove and then add if there are numbers left
	public int next() {
		Iterator<Integer> iterator = iterators.get(turn);
		int result = iterator.next();

		if (iterator.hasNext()) {
			turn = (turn + 1) % iterators.size();
		} else {
			iterators.remove(turn); // if next item not present remove current iterator
			if (iterators.size() > 0) {
				turn %= iterators.size();
			}
		}

		return result;
	}

	public boolean hasNext() {
		return iterators.size() > 0;
	}

	public static void main(String[] args) {
		List<Integer> v1 = Arrays.asList(1, 2, 3, 4);
		List<Integer> v2 = Arrays.asList(5, 6, 7, 8);
		List<List<Integer>> vectors = new ArrayList<List<Integer>>();
		vectors.add(v1);
		vectors.add(v2);

		ZigZagIteratorK zk = new ZigZagIteratorK(vectors);
		while (zk.hasNext()) {
			System.out.println(zk.next() + " ");
		}
		System.out.println();
		vectors.clear();

		vectors.add(Arrays.asList(1, 2, 3));
		vectors.add(Arrays.asList(4, 5, 6, 7));
		vectors.add(Arrays.asList(8, 9));

		ZigZagIteratorK zk1 = new ZigZagIteratorK(vectors);
		while (zk1.hasNext()) {
			System.out.println(zk1.next() + " ");
		}
	}
}
