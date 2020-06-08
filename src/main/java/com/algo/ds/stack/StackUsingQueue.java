package com.algo.ds.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a LIFO stack with basic functionality (push and pop) using FIFO queues to store the data.
 *
 */
public class StackUsingQueue {

	private Queue<Integer> primary = new LinkedList<>();
	private Queue<Integer> secondary = new LinkedList<>();

	public void push(int num) {
		secondary.add(num);
		while (!primary.isEmpty()) {
			secondary.add(primary.remove());
		}

		// swap
		Queue<Integer> temp = primary;
		primary = secondary;
		secondary = temp;
	}

	public int pop() {
		if (primary.isEmpty())
			throw new IndexOutOfBoundsException();

		return primary.remove();
	}

	public static void main(String[] args) {
		StackUsingQueue stack = new StackUsingQueue();
		stack.push(10);
		stack.push(20);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
