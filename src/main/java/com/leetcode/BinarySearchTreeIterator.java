package com.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implement an iterator for a binary search tree that will iterate the nodes by value in ascending order
 *
 */
public class BinarySearchTreeIterator<E extends Comparable<E>> implements Iterator<E> {

	LinkedList<Node<E>> stack;

	BinarySearchTreeIterator(Node<E> root) {
		stack = new LinkedList<>();
		Node<E> node = root;
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public E next() {
		if (!hasNext())
			throw new NoSuchElementException();

		Node<E> node = stack.pop();
		E retval = node.value;

		node = node.right;
		while (node != null) {
			stack.push(node);
			node = node.left;
		}

		return retval;
	}

}

class Node<E extends Comparable<? super E>> implements Iterable<E> {
	Node<E> left;
	Node<E> right;
	E value;

	public Node(E value) {
		this.value = value;
	}

	public Node<E> left(E leftval) {
		left = new Node<>(leftval);
		return left;
	}

	public Node<E> right(E rightval) {
		right = new Node<>(rightval);
		return right;
	}

	public Iterator<E> iterator() {
		return new BinarySearchTreeIterator(this);
	}
}
