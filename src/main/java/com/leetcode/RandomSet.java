package com.leetcode;

import java.util.HashMap;
import java.util.Random;

/**
 * Design a data structure that supports the following operations in ?(1) time.
 * 
 * insert(x): Inserts an item x to the data structure if not already present.
 * 
 * remove(x): Removes item x from the data structure if present.
 * 
 * search(x): Searches an item x in the data structure.
 * 
 * getRandom(): Returns a random element from current set of elements
 * 
 * Resizable arrays support insert in ?(1) amortized time complexity.
 * 
 * Category : Hard
 * 
 */
public class RandomSet<T> {

	private HashMap<T, Integer> valueToIndex;
	private HashMap<Integer, T> indexToValue;
	private Random random;

	public RandomSet() {
		this.valueToIndex = new HashMap<>();
		this.indexToValue = new HashMap<>();
		this.random = new Random();
	}

	public boolean add(T value) {
		if (valueToIndex.containsKey(value)) {
			return false;
		}
		int index = valueToIndex.size();
		valueToIndex.put(value, index);
		indexToValue.put(index, value);
		return true;
	}

	public void remove(T value) {
		if (!valueToIndex.containsKey(value)) {
			return;
		}
		// if is the last one directly remove it
		int index = valueToIndex.get(value);
		if (index == valueToIndex.size() - 1) {
			valueToIndex.remove(value);
			indexToValue.remove(index);
		}
		// if not, replace the index of last one with the current value so we dont need to change other value's index
		// Swapping is done because the last element can be removed in O(1) time.
		else {
			int lastIndex = indexToValue.size() - 1;
			T lastOne = indexToValue.get(lastIndex);
			// first map the last value with the current index(index)
			valueToIndex.put(lastOne, index);
			indexToValue.put(index, lastOne);
			// then remove the input value
			valueToIndex.remove(value);
			indexToValue.remove(lastIndex);
		}
	}

	public T randomGet() {
		if (valueToIndex.size() == 0) {
			return null;
		}
		int index = random.nextInt(valueToIndex.size());
		return indexToValue.get(index);
	}

	public T randomRemove() {
		T value = randomGet();
		if (value == null) {
			return null;
		}
		remove(value);
		return value;
	}

	public int search(int x) {
		if (valueToIndex.containsKey(x)) {
			return valueToIndex.get(x);
		}
		return -1;
	}

	public static void main(String[] args) {
		RandomSet<Integer> rs = new RandomSet<Integer>();

		rs.add(5);
		rs.add(10);
		rs.add(15);

		System.out.println("valueToIndex :" + rs.valueToIndex);
		System.out.println("indexToValue :" + rs.indexToValue);

		System.out.println(rs.search(10));

		rs.remove(10);

		System.out.println(rs.randomGet());

		System.out.println("valueToIndex :" + rs.valueToIndex);
		System.out.println("indexToValue :" + rs.indexToValue);

		System.out.println(rs.randomRemove());

		rs.add(20);
		rs.add(30);

		System.out.println("valueToIndex :" + rs.valueToIndex);
		System.out.println("indexToValue :" + rs.indexToValue);
	}
}
