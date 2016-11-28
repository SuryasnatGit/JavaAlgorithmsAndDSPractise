package com.algo.ds.tree;

import java.util.Iterator;

import com.algo.ds.linkedlist.Position;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/**
	 * Nested node class
	 */
	protected class Node<E> implements Position<E> {

		private E element;
		private Node<E> parent;
		private Node<E> leftChild;
		private Node<E> rightChild;

		public Node(E elem, Node<E> p, Node<E> lc, Node<E> rc) {
			this.element = elem;
			this.parent = p;
			this.leftChild = lc;
			this.rightChild = rc;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node<E> leftChild) {
			this.leftChild = leftChild;
		}

		public Node<E> getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node<E> rightChild) {
			this.rightChild = rightChild;
		}

		public void setElement(E element) {
			this.element = element;
		}

		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}

	}

	// LinkedBinaryTree instance variables
	private Node<E> root = null; // root
	private int size = 0; // number of nodes in tree

	public LinkedBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public Node<E> createNode(E elem, Node<E> p, Node<E> lc, Node<E> rc) {
		return new Node<E>(elem, p, lc, rc);
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeftChild();
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRightChild();
	}

	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
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
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("p not an instance of node");
		Node<E> node = (Node<E>) p;
		if (node.getParent() == p)
			throw new IllegalArgumentException("p is no longer an active node");
		return node;
	}

	/**
	 * Adds root node to empty tree and returns the position
	 * 
	 * @param elem
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Position<E> addRoot(E elem) throws IllegalArgumentException {
		if (!isEmpty())
			throw new IllegalArgumentException("tree is not empty");
		Node<E> root = createNode(elem, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Adds left child with element E for position p and returns the new
	 * position
	 * 
	 * @param p
	 * @param elem
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Position<E> addLeftChild(Position<E> p, E elem) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeftChild() != null)
			throw new IllegalArgumentException("p already has left child");
		Node<E> child = createNode(elem, parent, null, null);
		parent.setLeftChild(child);
		size++;
		return child;
	}

	/**
	 * Adds right child with element E for position p and returns the new
	 * position
	 * 
	 * @param p
	 * @param elem
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Position<E> addRightChild(Position<E> p, E elem) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRightChild() != null)
			throw new IllegalArgumentException("p already has right child");
		Node<E> child = createNode(elem, parent, null, null);
		parent.setRightChild(child);
		size++;
		return child;
	}

	/**
	 * replaces the element at position p and returns the replaced element
	 * 
	 * @param p
	 * @param elem
	 * @return
	 */
	public E set(Position<E> p, E elem) {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(elem);
		return temp;
	}
}
