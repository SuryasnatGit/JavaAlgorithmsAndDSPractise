package com.algo.ds.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Iterable<E>, Queue<E> {

	public class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E e, Node<E> nxt) {
			this.data = e;
			this.next = nxt;
		}

		public E getData() {
			return data;
		}

		public Node<E> getNext() {
			return next;
		}
	}

	private Node<E> first, last;
	private int size;

	public LinkedQueue() {
		first = null;
		last = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		if (isEmpty())
			throw new NoSuchElementException("queue underflow");
		return first.getData();
	}

	@Override
	public void enqueue(E data) {
		Node<E> oldLast = last;
		last = new Node<E>(data, null);
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
		}
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("queue underflow");
		E data = first.getData();
		first = first.getNext();
		size--;
		if (isEmpty())
			last = null; // for GC
		return data;
	}

	@Override
	public void display() {
		Node<E> head = first;
		while (head != null) {
			E data = head.data;
			System.out.println(data);
			head = head.next;
		}
	}

}
