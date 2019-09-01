package com.algo.ds.array;

public class OrderedArray {

	private long[] arr; // long array
	private int N; // number of elements
	
	public OrderedArray(int max) {
		arr = new long[max];
		N = arr.length;
	}
	
	public int size(){
		return N;
	}
	
	/**
	 * binary search
	 * @param index
	 * @return search index
	 * @throws Exception 
	 */
	public int search(long searchKey) throws Exception{
		int lowerIndex = 0;
		int upperIndex = N - 1;
		int currIndex;
		
		while(true){
			currIndex = (lowerIndex + upperIndex)/2;
			if(arr[currIndex] == searchKey){
				return currIndex; // found it
			}else if(lowerIndex > upperIndex){
				throw new Exception("Not found");
			}else{
				if(arr[currIndex] < searchKey){
					lowerIndex = currIndex +1;
				}else{
					upperIndex = currIndex - 1;
				}
			}
		}
	}
	
	public void insert(long value){
		int i;
		for(i=0;i<N;i++){
			if(arr[i] > value) // found location where value needs to go
				break;
		}
		
		for(int j=N;j > i;j--){
			arr[j] = arr[j-1]; // shift the higher ones up.
		}
		arr[i] = value;
		N++;
	}
	
	public boolean delete(long value) {
		int key;
		try {
			key = search(value); // found to be deleted.
		} catch (Exception e) {
			return false; // not found
		}
		for(int i=key;i < N;i++){
			arr[i] = arr[i+1]; // move higher elements down
		}
		N--;
		return true;
	}
}
