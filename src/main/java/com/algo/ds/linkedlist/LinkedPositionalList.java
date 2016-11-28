package com.algo.ds.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {

	private static class Node<E> implements Position<E> {

		private E element;
		private Node<E> prev;
		private Node<E> next;

		public Node(E e, Node<E> prev, Node<E> next) {
			this.element = e;
			this.prev = prev;
			this.next = next;
		}

		public Node<E> getNext() {
			return next;
		}

		public Node<E> getPrev() {
			return prev;
		}

		@Override
		public E getElement() throws IllegalStateException {
			if (getNext() == null)
				throw new IllegalStateException("position no longer valid");
			return element;
		}

		public void setElement(E e) {
			this.element = e;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

	}

	// header, trailer are sentinel nodes
	private Node<E> header;
	private Node<E> trailer;
	private int size;

	public LinkedPositionalList() {
		this.header = new Node<E>(null, null, null);
		this.trailer = new Node<E>(null, header, null);
		header.setNext(trailer);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	@Override
	public Position<E> last() {
		return position(trailer.getPrev());
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}

	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E element = node.getElement();
		node.setElement(e);
		return element;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E element = node.getElement();
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		// for GC
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return element;
	}

	// utility methods
	/**
	 * validates the position and returns it as a node
	 * 
	 * @param p
	 * @return
	 */
	private Node<E> validate(Position<E> p) {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("invalid position");
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null)
			throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}

	/**
	 * Returns position for the node and null(if it is a sentinel)
	 * 
	 * @param e
	 * @return
	 */
	private Position<E> position(Node<E> node) {
		if (node == header || node == trailer)
			return null;
		return node;
	}

	private Node<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> middle = new Node<E>(e, pred, succ);
		pred.setNext(middle);
		succ.setPrev(middle);
		size++;
		return middle;
	}

	private class PositionIterator implements Iterator<Position<E>> {

		private Position<E> cursor = first();
		private Position<E> recent = null;

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public Position<E> next() {
			if (cursor == null)
				throw new NoSuchElementException("no next element");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}

		public void delete() {
			if (recent == null)
				throw new IllegalStateException("nothing to delete");
			LinkedPositionalList.this.remove(recent);
			recent = null; // donot allow remove again until next is called
		}

	}

	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}

	}

	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	private class ElementIterator implements Iterator<E> {

		private Iterator<Position<E>> iter = new PositionIterator();

		@Override
		public boolean hasNext() {
			return iter.hasNext();
		}

		@Override
		public E next() {
			return iter.next().getElement();
		}

		public void remove() {
			iter.remove();
		}

	}

	public Iterator<E> elemIter() {
		return new ElementIterator();
	}

	public void insertionSort(PositionalList<Integer> list) {
		Position<Integer> marker = list.first();
		while (marker != list.last()) {
			Position<Integer> pivot = list.after(marker);
			Integer value = pivot.getElement();
			if (value > marker.getElement()) {
				marker = pivot;
			} else {
				Position<Integer> walk = marker;// find leftmost item > value
				while (walk != list.first() && list.before(walk).getElement() > value) {
					walk = list.before(walk);
				}
				list.remove(pivot);// remove pivot value
				list.addBefore(walk, value);// reinsert in front of walk.
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
