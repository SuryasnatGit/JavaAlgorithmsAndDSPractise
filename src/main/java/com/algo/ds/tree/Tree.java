package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.algo.ds.linkedlist.Position;
import com.algo.ds.tree.LinkedBinaryTree.Node;

public interface Tree<E> extends Iterable<E> {

	/**
	 * returns the position of root of tree or null if empty
	 * 
	 * @return
	 */
	Position<E> root();

	/**
	 * returns the position of parent of position p or null if p is the root
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	Position<E> parent(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns an iterable collection containing the children of position p, if
	 * any
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns the number of children of position p
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	int numChildren(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns true if position p has atleast 1 child
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	boolean isInternal(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns true if position p does not have any children
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	boolean isExternal(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns true if position p is the root
	 * 
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	boolean isRoot(Position<E> p) throws IllegalArgumentException;

	/**
	 * returns the number of positions(thus elements) in the tree
	 * 
	 * @return
	 */
	int size();

	/**
	 * returns true if the tree does not have any positions(and thus elements)
	 * 
	 * @return
	 */
	boolean isEmpty();

	@Override
	Iterator<E> iterator();

	/**
	 * returns an iterable collection of all positions in a tree
	 * 
	 * @return
	 */
	Iterable<Position<E>> positions();

	Position<E> addRightChild(Position<E> p, E elem) throws IllegalArgumentException;

	Position<E> addLeftChild(Position<E> p, E elem) throws IllegalArgumentException;

	Position<E> addRoot(E elem) throws IllegalArgumentException;

	Iterable<Position<E>> preorder();
}
