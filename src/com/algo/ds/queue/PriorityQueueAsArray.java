package com.algo.ds.queue;

public class PriorityQueueAsArray {

	private int maxSize;
	private int[] arr;
	private int numItems;
	
	public PriorityQueueAsArray(int size) {
		maxSize = size;
		arr = new int[maxSize];
		numItems = 0;
	}
	
	/**
	 * Complexity - O(n)
	 * @param num
	 */
	public void insert(int num){
		int i;
		if(numItems == 0)
			arr[numItems++] = num;
		else{ // numItems exists and > 0
			for(i=numItems-1;i>=0;i--){
				if(num > arr[i])
					arr[i+1] = arr[i]; // move the number up
				else
					break; // do nothing . break out.
			}
			arr[i+1] = num;
			numItems++;
		}
	}
	
	/**
	 * Complexity - O(1)
	 * @return
	 */
	public int delete(){
		return arr[--numItems];
	}
	
	public int peek(){
		return arr[numItems - 1];
	}
	
	public boolean isEmpty(){
		return numItems == 0;
	}
	
	public boolean isFull(){
		return numItems == maxSize;
	}
}
