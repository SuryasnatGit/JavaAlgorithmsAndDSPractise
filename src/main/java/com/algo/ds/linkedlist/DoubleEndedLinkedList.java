package com.algo.ds.linkedlist;

public class DoubleEndedLinkedList {

	private class Link{
		private int data;
		private Link next;
		
		public Link(int d) {
			data = d;
		}
	}
	
	private Link first;
	private Link last;
	
	public DoubleEndedLinkedList() {
		first = null; // no links yet
		last = null;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public void insertFirst(int d){
		Link newLink = new Link(d);
		if(isEmpty())
			last = newLink;
		
		newLink.next = first;
		first = newLink;
	}
	
	public void insertLast(int d){
		Link newLink = new Link(d);
		if(isEmpty())
			first = newLink;
		else
			last.next = newLink;
		
		last = newLink;
	}
	
	public int deleteFirst(){
		int data = first.data;
		if(first.next == null)
			last = null;
		
		first = first.next;
		return data;
	}
	
}
