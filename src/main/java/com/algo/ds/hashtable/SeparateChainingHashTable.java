package com.algo.ds.hashtable;

import com.algo.ds.linkedlist.Link;
import com.algo.ds.linkedlist.SortedList;


/**
 * Separate Chaining - this uses a SortedList internally .<br/>
 * m = Number of slots in hash table. n = Number of keys to be inserted in has table. Load factor α = n/m
 */
public class SeparateChainingHashTable {

	private int bucketSize;
	private SortedList[] sortedListArr;
	
	public SeparateChainingHashTable(int size) {
		bucketSize = size;
		sortedListArr = new SortedList[bucketSize];
		for (int i = 0; i < bucketSize; i++) {
			sortedListArr[i] = new SortedList(); // initialize each element of the array with SortedList object
		}
	}
	
	public int hashFunc(int key){
		return key % bucketSize;
	}

	public int getBucketIndex(int hashCode) {
		return hashCode % bucketSize;
	}
	
    /**
     * Expected time to insert/delete = O(1 + α)
     * 
     * @param link
     */
	public void insert(Link link){
		int hashVal = hashFunc(link.getKey());
		int bucketIndex = getBucketIndex(hashVal);
		sortedListArr[bucketIndex].insert(link);
	}
	
	public void delete(int key){
		int hashVal = hashFunc(key);
		int bucketIndex = getBucketIndex(hashVal);
		sortedListArr[bucketIndex].delete(key);
	}
	
    /**
     * Expected time to search = O(1 + α). Time complexity of search insert and delete is O(1) if α is O(1)
     * 
     * @param key
     * @return
     */
	public Link find(int key){
		int hashVal = hashFunc(key);
		int bucketIndex = getBucketIndex(hashVal);
		Link link = sortedListArr[bucketIndex].find(key);
		return link;
	}
	
}
