package com.algo.ds.hashtable;

import com.algo.ds.linkedlist.Link;
import com.algo.ds.linkedlist.SortedList;


/**
 * Separate Chaining - this uses a SortedList internally .<br/>
 * m = Number of slots in hash table. n = Number of keys to be inserted in has table. Load factor α = n/m
 */
public class HashChain {

	private int arraySize;
	private SortedList[] sortedListArr;
	
	public HashChain(int size) {
		arraySize = size;
		sortedListArr = new SortedList[arraySize];
		for(int i=0;i<arraySize;i++){
			sortedListArr[i] = new SortedList(); // initialize each element of the array with SortedList object
		}
	}
	
	public int hashFunc(int key){
		return key % arraySize;
	}
	
    /**
     * Expected time to insert/delete = O(1 + α)
     * 
     * @param link
     */
	public void insert(Link link){
		int hashVal = hashFunc(link.getKey());
		sortedListArr[hashVal].insert(link);
	}
	
	public void delete(int key){
		int hashVal = hashFunc(key);
		sortedListArr[hashVal].delete(key);
	}
	
    /**
     * Expected time to search = O(1 + α). Time complexity of search insert and delete is O(1) if α is O(1)
     * 
     * @param key
     * @return
     */
	public Link find(int key){
		int hashVal = hashFunc(key);
		Link link = sortedListArr[hashVal].find(key);
		return link;
	}
	
}
