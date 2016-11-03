package com.algo.ds.linkedlist;

public class CircularLinkedList<E> {

	private class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}

		public E getData() {
			return data;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

	private Node<E> last;
	private int size;

	public CircularLinkedList() {
		this.last = null;
		this.size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		else
			return last.getNext().getData();
	}

	public E last() {
		if (isEmpty())
			return null;
		else
			return last.getData();
	}

	public void rotate() {
		if (last != null)
			last = last.getNext();
	}

	public void addFirst(E data) {
		if (isEmpty()) {
			last = new Node<E>(data, null);
			last.setNext(last);
		} else {
			Node<E> newNode = new Node<E>(data, last.getNext());
			last.setNext(newNode);
		}
		size++;
	}

	public void addLast(E data) {
		addFirst(data);
		last = last.getNext();
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		Node<E> head = last.getNext();
		if (last == head) // must be the only node left
			last = null;
		else {
			last.setNext(head.getNext());
		}
		size--;
		return head.getData();
	}
}
