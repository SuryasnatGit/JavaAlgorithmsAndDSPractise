package com.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implement an iterator to flatten a 2d vector.
 * 
 * For example, Given 2d vector =
 * 
 * [ [1,2], [3], [4,5,6] ]
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 * [1,2,3,4,5,6].
 * 
 * Hint:
 * 
 * How many variables do you need to keep track?
 * 
 * Two variables is all you need. Try with x and y.
 * 
 * Beware of empty rows. It could be the first few rows.
 * 
 * To write correct code, think about the invariant to maintain. What is it?
 * 
 * The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant
 * ahead of time or right when you need it?
 * 
 * Not sure? Think about how you would implement hasNext(). Which is more complex? Common logic in two different places
 * should be refactored into a common method.
 * 
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 * 
 * Category : Medium
 *
 */
public class Flatten2DVector implements Iterator<Integer> {
	private List<List<Integer>> vector;
	private int currentList = 0;
	private int currentPos = 0;

	public Flatten2DVector(List<List<Integer>> vec2d) {
		vector = vec2d;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new IllegalArgumentException();
		}
		int data = vector.get(currentList).get(currentPos);
		currentPos++;
		if (currentPos == vector.get(currentList).size()) {
			currentPos = 0; // reset current pos
			currentList++;
		}
		return data;
	}

	@Override
	public boolean hasNext() {
		while (currentList < vector.size() && vector.get(currentList).size() == 0) {
			currentList++;
		}
		return currentList < vector.size();
	}
}

class Vector2D implements Iterator<Integer> {

	Queue<Integer> queue = new LinkedList<Integer>();

	public Vector2D(List<List<Integer>> vec2d) {
		for (int i = 0; i < vec2d.size(); i++) {
			for (int j = 0; j < vec2d.get(i).size(); j++) {
				queue.offer(vec2d.get(i).get(j));
			}
		}
	}

	@Override
	public Integer next() {
		return queue.poll();
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}
}
