package com.algo.ds.linkedlist;


/**
 * this is also a impl of deque using a doubly linked list.
 *
 */
public class DoublyLinkedList {

	private Node first;
	private Node last;

	public DoublyLinkedList() {
		first = null; // no links exist
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(int d) {
		Node newLink = new Node(d);
		if (isEmpty())
			last = newLink;
		else
			first.previous = newLink;
		newLink.next = first;
		first = newLink;
	}

	public void insertLast(int d) {
		Node newLink = new Node(d);
		if (isEmpty())
			first = newLink;
		else {
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}

	public Node deleteFirst() {
		Node temp = first;
		if (first.next == null)
			last = null;
		else
			first.next.previous = null;
		first = first.next;
		return temp;
	}

	public Node deleteLast() {
		Node temp = last;
		if (first.next == null)
			first = null;
		else
			last.previous.next = null;
		last = last.previous;
		return temp;
	}

	/**
	 * Insert the link with data d after key
	 * 
	 * @param key
	 * @param d
	 */
	public boolean insertAfter(int key, int d) {
		Node current = first;
		while (current.data != key) {
			current = current.next;
			if (current == null)
				return false;
		}
		Node newLink = new Node(d);
		if (current == last) {
			newLink.next = null;
			last = newLink;
		} else {
			newLink.next = current.next;
			current.next.previous = newLink;
		}
		newLink.previous = current;
		current.next = newLink;
		return true;
	}

	public Node deleteKey(int key) {
		Node current = first;
		while (current.data != key) {
			current = current.next;
			if (current == null)
				return null;
		}
		if (current == first) {
			first = current.next;
		} else {
			current.previous.next = current.next;
		}

		if (current == last) {
			last = current.previous;
		} else {
			current.next.previous = current.previous;
		}
		return current;
	}
}
