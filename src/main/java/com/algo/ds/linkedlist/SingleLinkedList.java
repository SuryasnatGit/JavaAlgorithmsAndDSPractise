package com.algo.ds.linkedlist;

public class SingleLinkedList<E> implements Cloneable {

	private class Link<E> {
		private E data;
		private Link<E> next;

		// constructor contains data and link to next node
		public Link(E d, Link<E> next) {
			this.data = d;
			this.next = next;
		}

		public E getData() {
			return data;
		}

		public Link<E> getNext() {
			return next;
		}

		public void setNext(Link<E> next) {
			this.next = next;
		}
	}

	private Link<E> first, last;
	private int count;

	public SingleLinkedList() {
		first = null;
		last = null;
		count = 0;
	}

	private boolean isEmpty() {
		return count == 0;
	}

	public E getFirst() {
		if (isEmpty())
			return null;
		else
			return first.getData();
	}

	public E getLast() {
		if (isEmpty())
			return null;
		else
			return last.getData();
	}

	public void addFirst(E d) {
		first = new Link<E>(d, first);
		if (isEmpty())
			last = first;
		count++;
	}

	public void addLast(E e) {
		Link<E> newLink = new Link<E>(e, null);
		if (isEmpty())
			first = newLink;
		else
			last.setNext(newLink);
		last = newLink;
		count++;
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		E data = first.getData();
		first = first.getNext();
		count--;
		if (isEmpty())
			last = null;
		return data;
	}

	public void displayList() {
		// Link current = first;
		// while (current != null) {
		// current.display();
		// current = current.next;
		// }
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SingleLinkedList other = (SingleLinkedList) obj;
		if (count != other.count)
			return false;

		Link A = first;
		Link B = other.first;
		while (A != null) {
			if (!A.getData().equals(B.getData()))
				return false;
			A = A.getNext();
			B = B.getNext();
		}

		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		SingleLinkedList<E> other = (SingleLinkedList<E>) super.clone();
		if (count > 0) {
			other.first = new Link<E>(first.getData(), null);
			Link<E> walk = first.getNext();
			Link<E> otherTail = other.first;
			while (walk != null) {
				Link<E> newest = new Link<E>(walk.getData(), null);
				otherTail.setNext(newest);
				otherTail = newest;
				walk = walk.getNext();
			}
		}
		return other;
	}

	public Link findByKey(int key) {
		return null;
		// Link current = first;
		// while (current.data != key) { // keep on looping until current data
		// is
		// // not equal to key
		// if (current.next == null) // if not found
		// return null;
		// else
		// current = current.next;
		// }
		// return current; // found and return current
	}

	public Link deleteByKey(int key) {
		// Link previous = first;
		// Link current = first;
		// while (current.data != key) { // keep on looping until current data
		// is
		// // not equal to key
		// if (current.next == null) // if not found
		// return null;
		// else {
		// previous = current; // shift previous and current
		// current = current.next;
		// }
		// }
		// if (current == first)
		// first = first.next;
		// else
		// previous.next = current.next;
		//
		// return current;
		return null;
	}
}
