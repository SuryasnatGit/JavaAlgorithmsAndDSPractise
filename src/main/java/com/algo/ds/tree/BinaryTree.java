package com.algo.ds.tree;

import com.algo.ds.linkedlist.Position;

public interface BinaryTree<E> extends Tree<E> {

	/**
	 * returns position of left child of position p or null if p does not have
	 * left child
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	Position<E> left(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns position of right child of position p or null if p does not have
	 * right child
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	Position<E> right(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns position of sibling of position p or null if p does not have any
	 * sibling
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
