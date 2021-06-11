package com.leetcode;

import java.util.HashMap;
import java.util.Random;

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
		// if is the last one
		// directly remove it
		int index = valueToIndex.get(value);
		if (index == valueToIndex.size() - 1) {
			valueToIndex.remove(value);
			indexToValue.remove(index);
		}
		// if not, replace the index of last one with the current value
		// so we dont need to change other value's index
		else {
			int lastIndex = indexToValue.size() - 1;
			T lastOne = indexToValue.get(lastIndex);
			valueToIndex.put(lastOne, index);
			indexToValue.put(index, lastOne);
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
}
