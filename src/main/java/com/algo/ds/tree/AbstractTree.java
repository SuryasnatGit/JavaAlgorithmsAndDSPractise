package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.List;

import com.algo.ds.linkedlist.Position;
import com.algo.ds.queue.LinkedQueue;
import com.algo.ds.queue.Queue;

public abstract class AbstractTree<E> implements Tree<E> {

	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) > 0;
	}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) == 0;
	}

	@Override
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		return p == root();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * returns the number of levels separating position p from the root
	 * 
	 * @param p
	 * @return
	 */
	public int depth(Position<E> p) {
		if (isRoot(p))
			return 0;
		else
			return (depth(parent(p)) + 1);
	}

	/**
	 * returns the height of the tree
	 * 
	 * @param p
	 * @return
	 */
	public int height(Position<E> p) {
		int h = 0;
		for (Position<E> c : children(p))
			h = Math.max(h, 1 + height(c));
		return h;
	}

	/**
	 * returns a iterable collection of positions of the tree reported in
	 * preorder. applicable to all trees
	 * 
	 * @return
	 */
	@Override
	public Iterable<Position<E>> preorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()) {
			preorderSubTree(root(), snapshot);
		}
		return snapshot;
	}

	/**
	 * adds positions of the sub-tree rooted at position p to the snapshot
	 * 
	 * @param p
	 * @param snapshot
	 */
	private void preorderSubTree(Position<E> p, List<Position<E>> snapshot) {
		snapshot.add(p);
		for (Position<E> child : children(p)) {
			preorderSubTree(child, snapshot);
		}
	}

	/**
	 * returns a iterable collection of positions of the tree reported in
	 * postorder. applicable to all trees
	 * 
	 * @return
	 */
	public Iterable<Position<E>> postOrder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()) {
			postorderSubTree(root(), snapshot);
		}
		return snapshot;
	}

	private void postorderSubTree(Position<E> p, List<Position<E>> snapshot) {
		for (Position<E> child : children(p)) {
			postorderSubTree(child, snapshot);
		}
		snapshot.add(p);
	}

	public Iterable<Position<E>> breadthFirstTraversal() {
		List<Position<E>> snapshot = new ArrayList<>();
		while (!isEmpty()) {
			Queue<Position<E>> fringe = new LinkedQueue<>();
			fringe.enqueue(root());
			while (!fringe.isEmpty()) {
				Position<E> p = fringe.dequeue();
				snapshot.add(p);
				for (Position<E> child : children(p)) {
					fringe.enqueue(child);
				}
			}
		}
		return snapshot;
	}
}
