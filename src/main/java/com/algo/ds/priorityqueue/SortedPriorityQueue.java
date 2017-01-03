package com.algo.ds.priorityqueue;

import java.util.Comparator;

import com.algo.ds.linkedlist.LinkedPositionalList;
import com.algo.ds.linkedlist.Position;
import com.algo.ds.linkedlist.PositionalList;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

	public SortedPriorityQueue() {
		super();
	}

	public SortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * time complexity - O(1)
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * time complexity - O(N) worst case
	 */
	@Override
	public Entry<K, V> insert(K key, V value) {
		checkKey(key);
		Entry<K, V> newest = new PQEntry(key, value);
		Position<Entry<K, V>> walk = list.last();
		// walk backwards
		while (walk != null && compare(newest, walk.getElement()) < 0) {
			walk = list.before(walk);
			if (walk == null)
				list.addFirst(newest);
			else
				list.addAfter(walk, newest);
		}
		return newest;
	}

	/**
	 * time complexity - O(1)
	 */
	@Override
	public Entry<K, V> min() {
		if (list.isEmpty())
			return null;
		return list.first().getElement();
	}

	/**
	 * time complexity - O(1)
	 */
	@Override
	public Entry<K, V> removeMin() {
		if (list.isEmpty())
			return null;
		return list.remove(list.first());
	}

}
