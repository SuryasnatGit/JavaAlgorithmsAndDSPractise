package com.algo.ds.map;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {

	protected class MapEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getVaue() {
			return value;
		}

		private void setKey(K key) {
			this.key = key;
		}

		private V setValue(V value) {
			V old = value;
			this.value = value;
			return old;
		}

	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	private class KeyIterator implements Iterator<K> {

		private Iterator<Entry<K, V>> entries = entrySet().iterator();

		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public K next() {
			return entries.next().getKey();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private class KeyIterable implements Iterable<K> {

		@Override
		public Iterator<K> iterator() {
			return new KeyIterator();
		}

	}

	@Override
	public Iterable<K> keySet() {
		return new KeyIterable();
	}

	private class ValueIterator implements Iterator<V> {

		private Iterator<Entry<K, V>> entries = entrySet().iterator();

		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public V next() {
			return entries.next().getVaue();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class ValueIterable implements Iterable<V> {

		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}

	}

	@Override
	public Iterable<V> valueSet() {
		return new ValueIterable();
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
