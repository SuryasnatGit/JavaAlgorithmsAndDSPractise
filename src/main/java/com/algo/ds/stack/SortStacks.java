package com.algo.ds.stack;

import java.util.Stack;

public class SortStacks {

	public Stack<Integer> sort(Stack<Integer> input) {
		if (input == null || input.isEmpty()) {
			return null;
		}

		Stack<Integer> result = new Stack<>();
		result.push(input.pop());

		while (!input.isEmpty()) {
			int temp = input.pop();
			while (!result.isEmpty() && temp > result.peek()) {
				input.push(result.pop());
			}
			result.push(temp);
		}

		return result;
	}

	public static void main(String[] args) {
		SortStacks ss = new SortStacks();

		Stack<Integer> s = new Stack<Integer>();
		s.push(10);
		s.push(5);
		s.push(7);
		s.push(1);

		Stack<Integer> sort = ss.sort(s);
		while (!sort.isEmpty()) {
			System.out.println(sort.pop());
		}
	}
}
