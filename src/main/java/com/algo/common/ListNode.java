package com.algo.common;

public class ListNode {

	public int data;
	public int value;
	public ListNode next;
	public ListNode previous;

	public ListNode(int key) {
		this.data = key;
		this.next = null;
		this.previous = null;
	}

	public ListNode(int key, int value) {
		this.data = key;
		this.value = value;
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
