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
 * Example:
 * 
 * Consider the following SpecialStack 16 --> TOP 15 29 19 18
 * 
 * When getMin() is called it should return 15, which is the minimum element in
 * the current stack.
 * 
 * If we do pop two times on stack, the stack becomes 29 --> TOP 19 18
 * 
 * When getMin() is called, it should return 18 which is the minimum in the
 * current stack.
 * 
 * 
 * In this article, a new approach is discussed that supports minimum with O(1)
 * extra space. We define a variable minEle that stores the current minimum
 * element in the stack. Now the interesting part is, how to handle the case
 * when minimum element is removed. To handle this, we push “2x – minEle” into
 * the stack instead of x so that previous minimum element can be retrieved
 * using current minEle and its value stored in stack.
 * 
 * complexity - O(1) time and O(1) space.
 * 
 * Category : Hard
 *
 */
public class SpecialStackOptimized {

	Stack<Integer> s = new Stack<Integer>();

	Integer minEle;

	// Prints minimum element of MyStack
	void getMin() {
		// Get the minimum number in the entire stack
		if (s.isEmpty())
			System.out.println("Stack is empty");

		// variable minEle stores the minimum element
		// in the stack.
		else
			System.out.println("Minimum Element in the " + " stack is: " + minEle);
	}

	// prints top element of MyStack
	void peek() {
		if (s.isEmpty()) {
			System.out.println("Stack is empty ");
			return;
		}

		Integer t = s.peek(); // Top element.

		System.out.print("Top Most Element is: ");

		// If t < minEle means minEle stores
		// value of t.
		if (t < minEle)
			System.out.println(minEle);
		else
			System.out.println(t);
	}

	// Removes the top element from MyStack
	void pop() {
		if (s.isEmpty()) {
			System.out.println("Stack is empty");
			return;
		}

		System.out.print("Top Most Element Removed: ");
		Integer t = s.pop();

		// Minimum will change as the minimum element
		// of the stack is being removed.
		if (t < minEle) {
			System.out.println(minEle);
			minEle = 2 * minEle - t;
		}

		else
			System.out.println(t);
	}

	// Insert new number into MyStack
	void push(Integer x) {
		if (s.isEmpty()) {
			minEle = x;
			s.push(x);
			System.out.println("Number Inserted: " + x);
			return;
		}

		// If new number is less than original minEle
		if (x < minEle) {
			s.push(2 * x - minEle);
			minEle = x;
		}

		else
			s.push(x);

		System.out.println("Number Inserted: " + x);
	}
}
