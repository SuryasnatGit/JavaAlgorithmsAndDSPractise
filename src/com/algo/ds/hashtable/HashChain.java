package com.algo.ds.hashtable;

import com.algo.ds.linkedlist.Link;
import com.algo.ds.linkedlist.SortedList;

/**
 * Separate Chaining - this uses a SortedList internally 
 * @author Suryasnat
 *
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
	
	public void insert(Link link){
		int hashVal = hashFunc(link.getKey());
		sortedListArr[hashVal].insert(link);
	}
	
	public void delete(int key){
		int hashVal = hashFunc(key);
		sortedListArr[hashVal].delete(key);
	}
	
	public Link find(int key){
		int hashVal = hashFunc(key);
		Link link = sortedListArr[hashVal].find(key);
		return link;
	}
	
}
