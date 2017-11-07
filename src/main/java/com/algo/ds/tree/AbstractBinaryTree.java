package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.List;

import com.algo.ds.linkedlist.Position;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements IBinaryTree<E> {

	@Override
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Position<E> parent = parent(p);
		if (parent == null)
			return null;// p is root
		if (p == left(parent)) // if p is left child of parent
			return right(parent); // return right child, may be null
		else
			return left(parent); // return left child, may be null
	}

	@Override
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		int count = 0;
		if (left(p) != null)
			count++;
		if (right(p) != null)
			count++;
		return count;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		List<Position<E>> snapShot = new ArrayList<>(2);
		if (left(p) != null)
			snapShot.add(left(p));
		if (right(p) != null)
			snapShot.add(right(p));
		return snapShot;
	}

	/**
	 * overrides positions to make inorder the default order for binary tree
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return inorder();
	}

	private Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()) {
			inorderSubTree(root(), snapshot);
		}
		return snapshot;
	}

	private void inorderSubTree(Position<E> p, List<Position<E>> snapshot) {
		if (left(p) != null)
			inorderSubTree(left(p), snapshot);
		snapshot.add(p);
		if (right(p) != null)
			inorderSubTree(right(p), snapshot);
	}
}
