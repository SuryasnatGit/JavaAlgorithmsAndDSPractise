package com.algo.ds.queue;

import java.util.Stack;

/**
 * Implement queue using stack
 * 
 * @author M_402201
 *
 */
public class QueueUsingStack {

	private Stack<Integer> stack1 = new Stack<>();
	private Stack<Integer> stack2 = new Stack<>();

	/**
	 * By making enQueue operation costly. this method moves all elements twice in enqueue operation.
	 * Time - O(n)
	 * 
	 * @param num
	 */
	public void enqueue(int num) {
		// move all existing elements from stack1 to stack2
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}

		stack1.push(num);

		// push everything back to stack1
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
	}

	/**
	 * Time - O(1)
	 * 
	 * @return
	 */
	public int dequeue() {

		if (stack1.isEmpty()) {
			System.out.println("queue is empty");
			return 0;
		}

		return stack1.pop();
	}

	// Method 2 - making dequeue heavy
	/**
	 * Time - O(1)
	 * 
	 * @param num
	 */
	public void enqueue1(int num) {
		stack1.push(num);
	}

	/**
	 * Time - O(n)
	 */
	public int dequeue1() {
		// if both stacks are empty then return error
		if (stack1.isEmpty() && stack2.isEmpty()) {
			System.out.println("queue empty");
			return -1;
		}

		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}

		return stack2.pop();
	}

	// Method 3 : Queue can also be implemented using one user stack and one Function Call Stack. Below
	// is modified method 2 where recursion (or Function Call Stack) is used to implement queue using
	// only one user defined stack
	public void enqueue3(int num) {
		stack1.push(num);
	}

	public int dequeue3() {
		if (stack1.isEmpty()) {
			System.out.println("Q is null");
			return -1;
		}

		if (stack1.size() == 1) {
			return stack1.pop();
		} else {
			// pop item from stack1
			int num = stack1.pop();
			// store the last dequeued item
			int res = dequeue3();
			// push everything back to stack1
			stack1.push(num);

			return res;
		}
	}

	public static void main(String[] args) {
		QueueUsingStack qs = new QueueUsingStack();
//		qs.enqueue(10);
//		qs.enqueue(20);
//		System.out.println(qs.dequeue());
//		System.out.println(qs.dequeue());
//		System.out.println(qs.dequeue());

//		qs.enqueue1(10);
//		qs.enqueue1(20);
//		System.out.println(qs.dequeue1());
//		qs.enqueue1(30);
//		System.out.println(qs.dequeue1());
//		System.out.println(qs.dequeue1());

		qs.enqueue3(10);
		qs.enqueue3(20);
		System.out.println(qs.dequeue3());
		qs.enqueue3(30);
		System.out.println(qs.dequeue3());
		System.out.println(qs.dequeue3());
		System.out.println(qs.dequeue3());
	}
}
