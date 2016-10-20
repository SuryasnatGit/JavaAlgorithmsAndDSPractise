package com.algo.ds.hashtable;

/**
 * Open addressing
 * 1. Linear probing method
 * @author Suryasnat
 *
 */
public class HashTable {

	private DataItem[] array;
	private int arrSize;
	private DataItem noItem; // for deleted items
	
	public HashTable(int size) {
		arrSize= size;
		array = new DataItem[arrSize];
		noItem = new DataItem(-1);
	}
	
	public int hashFunc(int key){
		return key % arrSize;
	}
	
	public void insert(DataItem item){
		int key = item.getKey();
		int hashVal = hashFunc(key);
		while(array[hashVal] != null && array[hashVal].getKey() != -1){
			++hashVal; // increment the hash value
			hashVal %= arrSize;
		}
		array[hashVal] = item;
	}
	
	public DataItem find(int key){
		int hashVal = hashFunc(key);
		while(array[hashVal] != null){
			if(array[hashVal].getKey() == key){
				return array[hashVal];
			}
			++hashVal;
			hashVal %= arrSize;
		}
		return null;
	}
	
	public DataItem delete(int key){
		int hashVal = hashFunc(key);
		while(array[hashVal] != null){
			if(array[hashVal].getKey() == key){
				DataItem item = array[hashVal];
				array[hashVal] = noItem;
				return item;
			}
			++hashVal;
			hashVal %= arrSize;
		}
		return null;
	}
}
