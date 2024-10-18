package com.algo.ds.queue;

import java.util.Stack;

/**
 * Implement queue using stack
 * 
 * Category : Medium
 * 
 */
public class QueueUsingStack {

	private Stack<Integer> pushStack = new Stack<>();
	private Stack<Integer> popStack = new Stack<>();

	public void push(int num) {
		pushStack.push(num);
	}

	public int pop() {
		move();
		return !popStack.isEmpty() ? popStack.pop() : -1;
	}

	public int peek() {
		move();
		return !popStack.isEmpty() ? popStack.peek() : -1;
	}

	public boolean isEmpty() {
		return pushStack.isEmpty() && popStack.isEmpty();
	}

	/**
	 * 1. if popStack is empty, then pour the pushStack into the popStack
	 * 
	 * 2. if popStack isn't empty, then just pop the top element from popStack
	 */
	private void move() {
		if (popStack.isEmpty()) {
			while (!pushStack.isEmpty()) {
				popStack.push(pushStack.pop());
			}
		}
	}

	public static void main(String[] args) {
		QueueUsingStack qs = new QueueUsingStack();
		qs.push(10);
		qs.push(20);
		qs.push(30);
		System.out.println(qs.pop());
		System.out.println(qs.peek());
		System.out.println(qs.pop());
		System.out.println(qs.pop());
		System.out.println(qs.peek());

	}
}
