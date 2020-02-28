package com.algo.ds.stack;

import java.util.Stack;

/**
 * Question: Design a Data Structure SpecialStack that supports all the stack
 * operations like push(), pop(), isEmpty(), isFull() and an additional
 * operation getMin() which should return minimum element from the SpecialStack.
 * All these operations of SpecialStack must be O(1). To implement SpecialStack,
 * you should only use standard Stack data structure and no other data structure
 * like arrays, list, .. etc.
 *
 * 
 * Example:
 * 
 * Consider the following SpecialStack<br/>
 * 
 * 16 --> TOP<br/>
 * 15<br/>
 * 29<br/>
 * 19<br/>
 * 18<br/>
 * 
 * When getMin() is called it should return 15, which is the minimum element in
 * the current stack.
 * 
 * If we do pop two times on stack, the stack becomes <br/>
 * 29 --> TOP<br/>
 * 19<br/>
 * 18<br/>
 * 
 * When getMin() is called, it should return 18 which is the minimum in the
 * current stack.
 * 
 * Solution: Use two stacks: one to store actual stack elements and other as an
 * auxiliary stack to store minimum values. The idea is to do push() and pop()
 * operations in such a way that the top of auxiliary stack is always the
 * minimum.
 * 
 * Time - O(1) Space - O(n) extra space
 *
 * Category : Hard
 */
public class SpecialStack extends Stack<Integer> {

	private static final long serialVersionUID = 1L;

	private Stack<Integer> min = new Stack<>();

	/*
	 * SpecialStack's member method to insert an element to it. This method makes
	 * sure that the min stack is also updated with appropriate minimum values
	 */
	public void push(int x) {
		if (isEmpty()) {
			super.push(x);
			min.push(x);
		} else {
			super.push(x);
			int y = min.pop();
			min.push(y);
			if (x < y)
				min.push(x);
			else
				min.push(y);
		}
	}

	public Integer pop() {
		int x = super.pop();
		min.pop();
		return x;
	}

	/* SpecialStack's member method to get minimum element from it. */
	int getMin() {
		int x = min.pop();
		min.push(x);
		return x;
	}

	public static void main(String[] args) {
		SpecialStack ss = new SpecialStack();
		ss.push(5);
		ss.push(7);
		System.out.println(ss.getMin());
		ss.push(3);
		System.out.println(ss.getMin());
		System.out.println(ss.pop());
		System.out.println(ss.getMin());
	}
}
