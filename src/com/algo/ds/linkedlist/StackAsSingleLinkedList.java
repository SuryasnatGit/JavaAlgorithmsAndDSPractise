package com.algo.ds.linkedlist;

public class StackAsSingleLinkedList {

	private Node first; // data
	private int N; // count of nodes
	
	private class Node{
		Object object;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null; //or N == 0
	}
	
	public int size(){
		return N;
	}
	
	public void push(Object data){
		Node newFirst = first;
		first = new Node();
		first.object = data;
		newFirst.next = first;
		N++;
	}
	
	public Object pop(){
		Object data = first.object;
		first = first.next;
		N--;
		return data;
	}
}
