package com.algo.ds.tree;

import java.util.Iterator;

import com.algo.ds.linkedlist.Position;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/**
	 * Nested node class
	 */
	protected static class Node<E> implements Position<E> {

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

		public void displayNode() {
			System.out.println("Node: " + element);
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
	@Override
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
	@Override
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
	@Override
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

	/**
	 * Attach sub-trees t1 as left sub-tree and t2 as right sub-tree to the
	 * external p
	 * 
	 * @param p
	 * @param t1
	 * @param t2
	 * @throws IllegalArgumentException
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (isInternal(node))
			throw new IllegalArgumentException("not external node");
		size += t1.size + t2.size;
		if (!t1.isEmpty()) {
			t1.root.parent = node;
			node.setLeftChild(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if (!t2.isEmpty()) {
			t2.root.parent = node;
			node.setRightChild(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}

	/**
	 * removes the element at p and replaces it with its child
	 * 
	 * @param p
	 * @return
	 */
	public E delete(Position<E> p) {
		Node<E> node = validate(p);
		if (numChildren(node) == 2)
			throw new IllegalArgumentException("p has 2 children");
		Node<E> parent = node.getParent();
		Node<E> child = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
		child.setParent(parent);
		if (node == root) {
			root = child;
		} else {
			if (node == parent.getLeftChild())
				parent.setLeftChild(child);
			else
				parent.setRightChild(child);
		}
		size--;
		node.setElement(null);// to aid in GC
		node.setLeftChild(null);
		node.setRightChild(null);
		node.setParent(node);// as per our contract for no parent
		return node.getElement();
	}

}
