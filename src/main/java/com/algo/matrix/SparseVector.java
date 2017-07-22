package com.algo.matrix;

import java.util.HashMap;
import java.util.Map;

public class SparseVector {
	// size of vector
	private int n;
	// container containing key and value
	Map<Integer, Integer> map;

	public SparseVector(int capacity) {
		this.n = capacity;
		this.map = new HashMap<>();
	}

	/**
	 * if a value is 0 then for existing key, the entry is deleted.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(int key, int value) {
		if (key < 0 || key >= n)
			throw new RuntimeException("Index out of bounds");
		if (value != 0)
			map.put(key, value);
		else
			map.remove(key);
	}

	public int get(int key) {
		if (key < 0 || key >= n)
			throw new RuntimeException("Index out of bounds");
		if (map.containsKey(key))
			return map.get(key);
		else
			return 0;
	}

	public int getNonZeroNumber() {
		return map.size();
	}

	public int getVectorSize() {
		return n;
	}

	/**
	 * vector dot product
	 * 
	 * @param that
	 * @return
	 */
	public int dotProduct(SparseVector that) {
		if (this.n != that.n)
			throw new IllegalArgumentException("vectors are not of same size");
		int sum = 0;
		// iterate over the vector with the fewest non zeros. for each iteration check if the key is present in the
		// other SV map
		if (this.getNonZeroNumber() <= that.getNonZeroNumber()) {
			for (int key : this.map.keySet())
				if (that.map.containsKey(key))
					sum += this.get(key) * that.get(key);
		} else {
			for (int key : that.map.keySet())
				if (this.map.containsKey(key))
					sum += this.get(key) * that.get(key);
		}
		return sum;
	}

	/**
	 * vector addition
	 * 
	 * @param that
	 * @return
	 */
	public SparseVector plus(SparseVector that) {
		if (this.n != that.n)
			throw new IllegalArgumentException("vectors are not of same size");
		SparseVector result = new SparseVector(n);
		for (int key : this.map.keySet())
			result.put(key, this.get(key));
		for (int key : that.map.keySet())
			result.put(key, that.get(key) + result.get(key));
		return result;
	}

	// return a string representation
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int key : map.keySet()) {
			s.append("(" + key + ", " + map.get(key) + ") ");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		SparseVector a = new SparseVector(10);
		SparseVector b = new SparseVector(10);
		a.put(1, 20);
		a.put(3, 0);
		a.put(5, 50);
		b.put(1, 0);
		b.put(5, 30);
		System.out.println(a.dotProduct(b));
		System.out.println(a.plus(b));
	}
}
