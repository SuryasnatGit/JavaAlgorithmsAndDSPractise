package com.algo.common;

public class ListNode {

	public int data;
	public ListNode next;

	public ListNode(int d) {
		this.data = d;
		this.next = null;
	}

	public int getData() {
		return data;
	}

	public ListNode getNext() {
		return next;
	}

	@Override
	public String toString() {
		return "ListNode [data=" + data + ", next=" + next + "]";
	}
}
