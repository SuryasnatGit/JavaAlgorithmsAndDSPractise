package com.ctci.stacknqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in
 * real life, we would likely start a new stack when the previous stack exceeds some threshold.
 * Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of
 * several stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks.
 * push () and SetOfStacks. pop () should behave identically to a single stack (that is, pop ( )
 * should return the same values as it would if there were just a single stack). FOLLOW UP Implement
 * a function popAt (int index) which performs a pop operation on a specific sub-stack.
 * 
 * @author surya
 *
 */
public class SetOfStacks {

	private List<Stack<Integer>> stacks = new ArrayList<>();

	public void push(int value) {
		Stack stack = getLastStack();
		if (stack != null && !isFull()) {
			stack.push(value);
		} else {
			// add new stack
			Stack s = new Stack();
			s.push(value);
			stacks.add(s);
		}
	}

	public int pop() {
		Stack last = getLastStack();
		int v = (Integer) last.pop();
		if (last.size() == 0) {
			stacks.remove(stacks.size() - 1);
		}
		return v;
	}

	// TODO: needs to be implemented
	private boolean isFull() {
		return false;
	}

	private Stack getLastStack() {
		if (stacks.size() == 0)
			return null;
		return stacks.get(stacks.size() - 1);
	}

}
