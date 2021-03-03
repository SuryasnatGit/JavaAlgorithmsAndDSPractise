package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MidStack {

	Map<Integer, Node> map = new HashMap<Integer, Node>();
	int index = 0;
	Node top = null;

	void push(int val) {
		Node newNode = new Node(val);
		map.put(index, newNode);
		index++;

		if (top == null) {
			top = newNode;
		} else {
			top.next = newNode;
			newNode.prev = top;
			top = top.next;
		}
	}

	int pop() {
		if (top == null) {
			return -1;
		}

		int res = top.val;
		index--;
		map.remove(index);

		Node prev = top.prev;
		prev.next = null;
		top = prev;

		return res;
	}

	int peekMid() {
		int midIndex = map.size() / 2;
		System.out.println(midIndex);
		return map.get(midIndex).val;
	}

	int popMid() {
		int midIndex = map.size() / 2;
		Node midNode = map.get(midIndex);

		Node prev = midNode.prev;
		Node next = midNode.next;

		if (prev != null) {
			prev.next = next;
		}

		if (next != null) {
			next.prev = prev;
		} else {
			top = prev;
		}

		map.remove(midIndex);

		return midNode.val;
	}

	static class Node {
		Node prev;
		Node next;
		int val;

		Node(int val) {
			this.val = val;
		}
	}
}
