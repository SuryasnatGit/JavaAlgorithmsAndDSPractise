package com.algo.ds.linkedlist;

import java.util.Iterator;

public class FavoriteList<E> {

	// inner class for item
	class Item<E> {
		private E element;
		private int count = 0;

		public Item(E e) {
			this.element = e;
		}

		public int getCount() {
			return count;
		}

		public E getElement() {
			return element;
		}

		public int increment() {
			return count++;
		}
	}

	PositionalList<Item<E>> favList = new LinkedPositionalList<>();

	protected int count(Position<Item<E>> p) {
		return p.getElement().getCount();
	}

	protected E value(Position<Item<E>> p) {
		return p.getElement().getElement();
	}

	protected boolean isEmpty() {
		return favList.isEmpty();
	}

	protected int size() {
		return favList.size();
	}

	/**
	 * returns the position having element equals to E or null if not found
	 * 
	 * @param e
	 * @return
	 */
	protected Position<Item<E>> findPosition(E e) {
		Position<Item<E>> walk = favList.first();
		while (walk != null && !e.equals(value(walk))) {
			walk = favList.after(walk);
		}
		return walk;
	}

	/**
	 * moves item at position p based on access count
	 * 
	 * @param p
	 */
	protected void moveUp(Position<Item<E>> p) {
		Position<Item<E>> walk = p;
		int count = count(p);
		while (walk != favList.first() && count(favList.before(walk)) < count) {
			walk = favList.before(walk);
		}
		if (walk != p) {
			Item<E> e = favList.remove(p);
			favList.addBefore(walk, e);
		}
	}

	/**
	 * access element E, adding to fav list if not already present and
	 * increments access count
	 * 
	 * @param e
	 */
	public void access(E e) {
		Position<Item<E>> p = findPosition(e);
		if (p == null) {
			favList.addLast(new Item(e));
		}
		p.getElement().increment();
		moveUp(p);
	}

	/**
	 * removes E from list if present
	 * 
	 * @param e
	 */
	public void remove(E e) {
		Position<Item<E>> p = findPosition(e);
		if (p != null)
			favList.remove(p);
	}

	/**
	 * returns iterable collection of k most accessed elements. time complexity
	 * is O(n^3)
	 * 
	 * @param k
	 * @return
	 */
	public PositionalList<E> getFavorites(int k) {
		if (k < 0 || k > size())
			throw new IllegalArgumentException("invalid value of k");
		PositionalList<E> list = new LinkedPositionalList<>();
		Iterator<Item<E>> elemIter = favList.elemIter();
		for (int j = 0; j < k; j++) {
			list.addLast(elemIter.next().getElement());
		}
		return list;
	}
}
