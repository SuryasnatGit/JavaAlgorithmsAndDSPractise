package com.algo.ds.linkedlist;

public class LinkedPositionalList<E> implements PositionalList<E> {

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
}
