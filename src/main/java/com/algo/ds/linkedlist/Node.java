package com.algo.ds.linkedlist;

public class Node {

	public int data;
	public Node next;
	public Node previous;

	public Node(int d) {
		this.data = d;
		this.next = null;
		this.previous = null;
	}

	public int getData() {
		return data;
	}

	public Node getNext() {
		return next;
	}

	public Node getPrevious() {
		return previous;
	}

	@Override
	public String toString() {
		return "ListNode [data=" + data + "]";
	}

}
