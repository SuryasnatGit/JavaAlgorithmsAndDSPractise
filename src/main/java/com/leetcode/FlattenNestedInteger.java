package com.leetcode;

import java.util.List;
import java.util.Stack;

public class FlattenNestedInteger {

	private Stack<NestedInteger> stack = new Stack<NestedInteger>();

	public FlattenNestedInteger(List<NestedInteger> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			stack.push(list.get(i));
		}
	}

	int next() {
		return stack.pop().getInteger();
	}

	boolean hasNext() {
		if (stack.isEmpty()) {
			return false;
		}

		while (!stack.isEmpty()) {
			NestedInteger ni = stack.peek();

			if (ni.isInteger()) {
				return true;
			}

			ni = stack.pop();
			List<NestedInteger> list = ni.getList();
			for (int i = list.size() - 1; i >= 0; i--) {
				stack.push(list.get(i));
			}
		}

		return false;
	}
}

class NestedInteger {
	Integer intValue = null;
	List<NestedInteger> listValue = null;

	int getInteger() {
		return intValue;
	}

	List<NestedInteger> getList() {
		return listValue;
	}

	boolean isInteger() {
		return intValue != null;
	}
}
