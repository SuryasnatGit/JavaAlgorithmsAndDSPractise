package com.algo.ds.queue;

public class QueueAsSingleLinkedList implements MyQueue<Object> {

	private Node first;
	private Node last;
	private int N;

	private class Node {
		Object object;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void enqueue(Object data) {
		Node oldLast = last;
		last = new Node();
		last.object = data;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldLast.next = last;
		N++;
	}

	public Object dequeue() {
		Object data = first.object;
		first = first.next;
		N--;
		if (isEmpty())
			last = null;

		return data;
	}

	@Override
	public Object front() {
		return first.object;
	}

	@Override
	public Object rear() {
		return last.object;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}
}
