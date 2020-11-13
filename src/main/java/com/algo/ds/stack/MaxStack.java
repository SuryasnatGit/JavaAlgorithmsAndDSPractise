package com.algo.ds.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxStack {

	private Node top = new Node(-1);
	private PriorityQueue<Node> heap = new PriorityQueue<Node>(new Comparator<Node>() {
		public int compare(Node n1, Node n2) {
			return n2.val - n1.val; // Max Heap
		}
	});

	public boolean isEmpty() {
		return heap.isEmpty(); // Or use top.prev == null
	}

	public void push(int val) {
		Node newNode = new Node(val);

		top.next = newNode;
		newNode.prev = top;
		top = newNode;

		heap.offer(newNode);
	}

	public Integer pop() {
		if (isEmpty()) {
			return null;
		}

		int res = top.val;
		heap.remove(top); // Remove from heap. It takes O(N) to find the element and O(logN) to heapify again.
		top = top.prev; // To make this faster, add an auxiliary HashMap to remember <Object, Index> by implementing
						// your own Heap. HashHeap
		top.next = null;

		return res;
	}

	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return top.val;
	}

	public Integer peekMax() {
		if (isEmpty()) {
			return null;
		}
		return heap.peek().val;
	}

	public Integer popMax() {
		if (isEmpty()) {
			return null;
		}

		Node maxNode = heap.poll();

		Node prev = maxNode.prev;
		Node next = maxNode.next;

		if (prev == null) {

		} else {
			prev.next = maxNode.next;
		}

		if (next == null) {
			top = maxNode.prev;
		} else {
			next.prev = maxNode.prev;
		}

		return maxNode.val;
	}
}

class Node implements Comparable<Node> {
	Node prev = null;
	Node next = null;
	int val = -1;

	Node(int val) {
		this.val = val;
	}

	public int compareTo(Node that) {
		return that.val - this.val; // Sort from big to small
	}
}