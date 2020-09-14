package com.algo.ds.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

	private Queue<Integer> primary = new LinkedList<Integer>();
	private Queue<Integer> secondary = new LinkedList<Integer>();

	public StackUsingQueue() {
	}

	public void push(int x) {
		secondary.add(x);
		while (!primary.isEmpty()) {
			secondary.add(primary.remove());
		}

		Queue<Integer> temp = primary;
		primary = secondary;
		secondary = temp;
	}

	public int pop() {
		if (primary.isEmpty())
			throw new IndexOutOfBoundsException();
		return primary.remove();
	}
}
