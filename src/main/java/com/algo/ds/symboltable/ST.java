package com.algo.ds.symboltable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

	private TreeMap<Key, Value> st;

	public ST() {
		this.st = new TreeMap<>();
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("null key not allowed");
		return st.get(key);
	}

	/**
	 * if value is null, remove the key from ST
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value) {
		if (key == null)
			throw new IllegalArgumentException("null key not allowed");
		if (value == null)
			remove(key);
		else
			st.put(key, value);
	}

	public void remove(Key key) {
		if (key == null)
			throw new IllegalArgumentException("null key not allowed");
		st.remove(key);
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("null key not allowed");
		return st.containsKey(key);
	}

	public int size() {
		return st.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterable<Key> keys() {
		return st.keySet();
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("called min() with empty symbol table");
		return st.firstKey();
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException("called min() with empty symbol table");
		return st.lastKey();
	}

	/**
	 * Returns the largest key in this symbol table less than or equal to key}.
	 * 
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("called floor() with null key");
		Key floorKey = st.floorKey(key);
		if (floorKey == null)
			throw new NoSuchElementException("all keys are greater than " + key);
		return floorKey;
	}

	/**
	 * Returns the smallest key in this symbol table greater than or equal to key}.
	 * 
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("called ceiling() with null key");
		Key ceilingKey = st.ceilingKey(key);
		if (ceilingKey == null)
			throw new NoSuchElementException("all keys are less than " + key);
		return ceilingKey;
	}

	@Override
	public Iterator<Key> iterator() {
		return st.keySet().iterator();
	}

}
