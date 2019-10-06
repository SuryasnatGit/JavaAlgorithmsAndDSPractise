package com.algo.ds.queue;

public class QueueAsSingleLinkedList {

	private Node first;
	private Node last;
	private int N;
	
	private class Node{
		Object object;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return N;
	}
	
	public void enqueue(Object data){
		Node oldLast = last;
		last = new Node();
		last.object = data;
		last.next = null;
		if(isEmpty())
			first = last;
		else
			oldLast.next = last;
		N++;
	}
	
	public Object dequeue(){
		Object data = first.object;
		first = first.next;
		N--;
		if(isEmpty())
			last = null;
		
		return data;
	}
}
