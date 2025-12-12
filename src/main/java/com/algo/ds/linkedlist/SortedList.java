package com.algo.ds.linkedlist;

public class SortedList {

	private Node first;
	
	public SortedList() {
		first = null;
	}
	
	public void insert(Node Node){
		Node previous = null;
		Node current = first;
		while(current != null && Node.getData() > current.getData()){
			previous = current;
			current = current.next;
		}
		
		if(previous == null)
			first = Node;
		else
			previous.next = Node;
		
		Node.next = current;
	}
	
	public void delete(int key){
		Node previous = null;
		Node current = first;
		while(current != null && key != current.getData()){
			previous = current;
			current = current.next;
		}
		if(previous == null)
			first = first.next; // if beginning of list, delete first Node
		else
			previous.next = current.next; // delete current
	}
	
	public Node find(int key){
		Node current = first;
		while(current != null && current.getData() <= key){
			if(current.getData() == key)
				return current;
			current = current.next;
		}
		return null;
	}
}
