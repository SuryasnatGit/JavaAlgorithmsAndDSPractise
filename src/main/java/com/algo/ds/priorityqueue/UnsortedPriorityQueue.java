package com.algo.ds.priorityqueue;

import java.util.Comparator;

import com.algo.ds.linkedlist.LinkedPositionalList;
import com.algo.ds.linkedlist.Position;
import com.algo.ds.linkedlist.PositionalList;

public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

	public UnsortedPriorityQueue() {
		super();
	}

	public UnsortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * finds position of the entry having the minimun key
	 * 
	 * @param entry
	 * @return
	 */
	private Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> small = list.first();
		for (Position<Entry<K, V>> cur : list.positions()) {
			if (compare(cur.getElement(), small.getElement()) < 1)
				small = cur;
		}
		return small;
	}

	/**
	 * time complexity - O(1)
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * time complexity - O(1)
	 */
	@Override
	public Entry<K, V> insert(K key, V value) {
		checkKey(key);
		PQEntry entry = new PQEntry(key, value);
		list.addFirst(entry);
		return entry;
	}

	/**
	 * time complexity - O(N)
	 */
	@Override
	public Entry<K, V> min() {
		if (list.isEmpty())
			return null;
		return findMin().getElement();
	}

	/**
	 * time complexity - O(N)
	 */
	@Override
	public Entry<K, V> removeMin() {
		if (list.isEmpty())
			return null;
		return list.remove(findMin());
	}

}
