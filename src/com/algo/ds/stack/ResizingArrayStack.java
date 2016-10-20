package com.algo.ds.stack;

import java.util.Iterator;

public class ResizingArrayStack implements Iterable<Object>{

	private Object[] objArray = new Object[1];
	private int N = 0; //number of items
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	private void resize(int num){
		Object[] temp = new Object[num];
		for(int i=0;i<N;i++){
			temp[i] = objArray[i];
		}
		objArray = temp;
	}
	
	public void push(Object obj){
		if(N == objArray.length) 
			resize(2*objArray.length);
		objArray[N++] = obj;
	}
	
	public Object pop(){
		Object obj = objArray[--N];
		objArray[N] = null;
		if(N > 0 && N == objArray.length/4)
			resize(objArray.length/2);
		
		return obj;
	}
	
	@Override
	public Iterator<Object> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Object>{

		private int i = N;
		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Object next() {
			return objArray[--i];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
