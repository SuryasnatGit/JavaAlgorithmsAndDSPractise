package com.algo.ds.stack;

import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
 */
public class ReverseStackUsingRecursion {

	public void reverse(Stack<Integer> stack) {
		if (stack.size() == 0) {
			return;
		}
		int temp = stack.pop();
		reverse(stack);

		pushAtBottom(stack, temp);
	}

	private void pushAtBottom(Stack<Integer> stack, int data) {
		if (stack.size() == 0) {
			stack.push(data);
			return;
		}
		int temp = stack.pop();
		pushAtBottom(stack, data);
		stack.push(temp);
	}

	public static void main(String args[]) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);

		System.out.println(stack);

		ReverseStackUsingRecursion rsu = new ReverseStackUsingRecursion();
		rsu.reverse(stack);

		System.out.println(stack);

	}

}
