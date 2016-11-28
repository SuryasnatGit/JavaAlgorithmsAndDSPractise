package com.algo.ds.tree;

import com.algo.ds.linkedlist.Position;

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
}
