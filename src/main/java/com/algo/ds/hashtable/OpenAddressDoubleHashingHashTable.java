package com.algo.ds.hashtable;

/**
 * Open addressing 2. Double hashing method
 * 
 * Double hashing can be done using : (hash1(key) + i * hash2(key)) % TABLE_SIZE Here hash1() and hash2() are hash
 * functions and TABLE_SIZE is size of hash table. (We repeat by increasing i when collision occurs)
 * 
 * @author Suryasnat
 *
 */
public class OpenAddressDoubleHashingHashTable {

	private DataItem[] array;
	private int arrSize;
	private DataItem noItem; // for deleted items

	public OpenAddressDoubleHashingHashTable(int size) {
		arrSize = size;
		array = new DataItem[arrSize];
		noItem = new DataItem(-1);
	}

	public int hashFunc1(int key) {
		return key % arrSize;
	}

	public int hashFunc2(int key) {
		return 5 - (key % 5);
	}

	public void insert(int key, DataItem item) {
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		while (array[hashVal] != null && array[hashVal].getKey() != -1) {
			hashVal += stepSize;
			hashVal %= arrSize;
		}
		array[hashVal] = item;
	}

	public DataItem find(int key) {
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		while (array[hashVal] != null) {
			if (array[hashVal].getKey() == key) {
				return array[hashVal];
			}
			hashVal += stepSize;
			hashVal %= arrSize;
		}
		return null;
	}

	public DataItem delete(int key) {
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		while (array[hashVal] != null) {
			if (array[hashVal].getKey() == key) {
				DataItem item = array[hashVal];
				array[hashVal] = noItem;
				return item;
			}
			hashVal += stepSize;
			hashVal %= arrSize;
		}
		return null;
	}
}
