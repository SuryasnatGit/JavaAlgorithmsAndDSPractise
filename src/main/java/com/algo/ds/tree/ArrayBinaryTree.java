package com.algo.ds.tree;

import java.util.Iterator;

import com.algo.ds.linkedlist.Position;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {

	private int count;
	private E[] tree;
	private int max = 50;

	public ArrayBinaryTree() {
		count = 0;
		tree = (E[]) new Object[max];
	}

	private void expandCapacity() {
		E[] temp = (E[]) new Object[2 * max];
		for (int i = 0; i < tree.length; i++) {
			temp[i] = tree[i];
		}
		tree = temp;
	}

	public E getLeft(int root) {
		return tree[2 * root + 1];
	}

	public E getRight(int root) {
		return tree[2 * root + 2];
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> root() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return count;
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

	@Override
	public Position<E> addRightChild(Position<E> p, E elem) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addLeftChild(Position<E> p, E elem) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addRoot(E elem) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
