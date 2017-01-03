package com.algo.ds.priorityqueue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

	protected class PQEntry implements Entry<K, V> {

		private K key;
		private V value;

		public PQEntry(K k, V v) {
			this.key = k;
			this.value = v;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		protected void setKey(K key) {
			this.key = key;
		}

		protected void setValue(V value) {
			this.value = value;
		}
	}

	private Comparator<K> comp;

	protected AbstractPriorityQueue(Comparator<K> c) {
		this.comp = c;
	}

	protected AbstractPriorityQueue() {
		this(new DefaultComparator<K>());
	}

	/**
	 * compares entries by key
	 * 
	 * @param e1
	 * @param e2
	 * @return
	 */
	public int compare(Entry<K, V> e1, Entry<K, V> e2) {
		return comp.compare(e1.getKey(), e2.getKey());
	}

	/**
	 * checks if a key is valid
	 * 
	 * @param key
	 * @return
	 */
	public boolean checkKey(K key) {
		return comp.compare(key, key) == 0;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}
