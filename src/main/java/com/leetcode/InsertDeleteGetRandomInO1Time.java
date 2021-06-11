package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * 
 * insert(val): Inserts an item val to the set if not already present.
 * 
 * remove(val): Removes an item val from the set if present.
 * 
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 * being returned.
 * 
 * Example:
 * 
 * // Init an empty set.
 * 
 * RandomizedSet randomSet = new RandomizedSet();
 * 
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * 
 * randomSet.insert(1);
 * 
 * // Returns false as 2 does not exist in the set.
 * 
 * randomSet.remove(2);
 * 
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * 
 * randomSet.insert(2);
 * 
 * // getRandom should return either 1 or 2 randomly.
 * 
 * randomSet.getRandom();
 * 
 * // Removes 1 from the set, returns true. Set now contains [2].
 * 
 * randomSet.remove(1);
 * 
 * // 2 was already in the set, so return false.
 * 
 * randomSet.insert(2);
 * 
 * // Since 2 is the only number in the set, getRandom always return 2.
 * 
 * randomSet.getRandom();
 *
 * Explanation:
 *
 * Let's consider them one by one.
 * 
 * Hashmap provides Insert and Delete in average constant time, although has problems with GetRandom.
 * 
 * The idea of GetRandom is to choose a random index and then to retrieve an element with that index. There is no
 * indexes in hashmap, and hence to get true random value, one has first to convert hashmap keys in a list, that would
 * take linear time. The solution here is to build a list of keys aside and to use this list to compute GetRandom in
 * constant time.
 * 
 * Array List has indexes and could provide Insert and GetRandom in average constant time, though has problems with
 * Delete.
 * 
 * To delete a value at arbitrary index takes linear time. The solution here is to always delete the last value:
 * 
 * Swap the element to delete with the last one.
 * 
 * Pop the last element out.
 * 
 * For that, one has to compute an index of each element in constant time, and hence needs a hashmap which stores
 * element -> its index dictionary.
 * 
 * Both ways converge into the same combination of data structures:
 * 
 * Hashmap element -> its index.
 * 
 * Array List of elements.
 * 
 * Category : Medium
 * 
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 */
public class InsertDeleteGetRandomInO1Time {

	// Map of element to its index
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	// list to hold the set of keys/elements
	private List<Integer> list = new ArrayList<Integer>();

	Random rand = new Random();

	/** Initialize your data structure here. */
	public InsertDeleteGetRandomInO1Time() {

	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		}

		int pos = list.size();

		list.add(val); // Position -> Value
		map.put(val, pos); // Value -> Position

		return true;
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}

		// Move the element to the end first, because list is ordered
		int originalPos = map.get(val);
		if (originalPos != list.size() - 1) {
			int lastElement = list.get(list.size() - 1);
			list.set(originalPos, lastElement); // There are 2 lastElements now, but will remove the last one later
			map.put(lastElement, originalPos); // Update position in Map
		}

		list.remove(list.size() - 1); // Remove last element, always
		map.remove(val); // Remove from Map

		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return list.get(rand.nextInt(list.size()));
	}

	public int removeRandom() {
		int index = rand.nextInt(list.size());
		int val = list.get(index);

		remove(val);

		return val;
	}
}
