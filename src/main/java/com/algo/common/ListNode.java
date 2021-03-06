package com.algo.common;

public class ListNode {

	public int data;
	public ListNode next;
	public ListNode previous;

	public ListNode(int d) {
		this.data = d;
		this.next = null;
		this.previous = null;
	}

	public int getData() {
		return data;
	}

	public ListNode getNext() {
		return next;
	}

	public ListNode getPrevious() {
		return previous;
	}

	@Override
	public String toString() {
		return "ListNode [data=" + data + "]";
	}

}
