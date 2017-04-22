package com.algo.ds.priorityqueue;

import java.util.Comparator;

public class AdaptableHeapPriorityQueue<K, V> extends ArrayBasedHeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

	// this extended version contains an additional location index
	protected class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

		int index;

		public AdaptablePQEntry(K k, V v, int i) {
			super(k, v);
			index = i;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

	/**
	 * default constructor
	 */
	public AdaptableHeapPriorityQueue() {
		super();
	}

	/**
	 * constructor which takes the provided comparator
	 * 
	 * @param comp
	 */
	public AdaptableHeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	protected AdaptablePQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException {
		if (!(entry instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("invalid entry");
		AdaptablePQEntry<K, V> apqEntry = (AdaptableHeapPriorityQueue<K, V>.AdaptablePQEntry<K, V>) entry;
		int index = apqEntry.getIndex();
		if (index >= getHeap().size() || getHeap().get(index) != apqEntry)
			throw new IllegalArgumentException("invalid entry");
		return apqEntry;
	}

	/**
	 * restores heap property by moving the entry at index j upward or downward
	 * 
	 * @param index
	 */
	protected void bubble(int index) {
		if (index > 0 && compare(getHeap().get(index), getHeap().get(parent(index))) < 0) {
			upheap(index);
		} else {
			downheap(index);
		}
	}

	protected void swap(int i, int j) {
		super.swap(i, j); // performs swap
		((AdaptablePQEntry<K, V>) getHeap().get(i)).setIndex(i); // reset index
		((AdaptablePQEntry<K, V>) getHeap().get(j)).setIndex(j); // reset index
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		checkKey(key);
		Entry<K, V> entry = new AdaptablePQEntry<>(key, value, getHeap().size());
		getHeap().add(entry);
		upheap(getHeap().size() - 1);
		return entry;
	}

	@Override
	public void remove(Entry<K, V> entry) {
		AdaptableHeapPriorityQueue<K, V>.AdaptablePQEntry<K, V> validate = validate(entry);
		int index = validate.getIndex();
		// entry is at last position, just remove it.
		if (index == getHeap().size() - 1)
			getHeap().remove(getHeap().size() - 1);
		else {
			// swap entry to last position and then remove it
			swap(index, getHeap().size() - 1);
			getHeap().remove(getHeap().size() - 1);
			bubble(index); // fix the entry displaced by the swap
		}
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) {
		AdaptableHeapPriorityQueue<K, V>.AdaptablePQEntry<K, V> validate = validate(entry);
		checkKey(key); // this might throw an exception
		validate.setKey(key);
		bubble(validate.getIndex()); // with new key may need to move entry
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) {
		AdaptableHeapPriorityQueue<K, V>.AdaptablePQEntry<K, V> validate = validate(entry);
		validate.setValue(value);
	}
}
