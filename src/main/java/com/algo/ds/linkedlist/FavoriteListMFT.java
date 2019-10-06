package com.algo.ds.linkedlist;

import java.util.Iterator;

/**
 * Maintains a list of elements ordered with move-to-front heuristic.
 * 
 * @author Suryasnat
 *
 * @param <E>
 */
public class FavoriteListMFT<E> extends FavoriteList<E> {

	@Override
	protected void moveUp(Position<Item<E>> p) {
		if (p != favList.first())
			favList.addFirst(favList.remove(p));
	}

	/**
	 * time complexity - O(n^2)
	 */
	@Override
	public PositionalList<E> getFavorites(int k) {
		if (k < 0 || k > size())
			throw new IllegalArgumentException("invalid value of k");

		// begin by making a copy of orignial list
		PositionalList<Item<E>> temp = new LinkedPositionalList<>();
		for (Iterator<Item<E>> iter = favList.elemIter(); iter.hasNext();) {
			Item<E> next = iter.next();
			temp.addLast(next);
		}

		// repeat find, report and remove element with largest count. all
		// operations done by referring to temp
		PositionalList<E> result = new LinkedPositionalList<>();
		for (int j = 0; j < k; j++) {
			Position<Item<E>> highPos = temp.first();
			Position<Item<E>> walk = temp.after(highPos);
			while (walk != null) {
				if (count(walk) > count(highPos)) {
					highPos = walk;
				}
				walk = temp.after(walk);
			}
			result.addLast(value(highPos));
			temp.remove(highPos);
		}
		return result;
	}
}
