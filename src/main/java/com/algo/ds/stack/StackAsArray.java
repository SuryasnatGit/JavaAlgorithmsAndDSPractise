package com.algo.ds.stack;

public class StackAsArray {

	private int N;
	private char[] arr;
	private int top;
	
	public StackAsArray(int max) {
		N = max;
		arr = new char[N];
		top = -1; // no elements iin array
	}
	
	public void push(char c){
		arr[++top] = c;
	}
	
	public char pop(){
		return arr[top--];
	}
	
	public char peek(){
		return arr[top];
	}
	
	public boolean isEmpty(){
		return top == -1;
	}
	
	public boolean isFull(){
		return top == N-1;
	}
	
	public int size(){
		return top+1;
	}
	
	public int peekKey(int n){
		return arr[n];
	}
}
