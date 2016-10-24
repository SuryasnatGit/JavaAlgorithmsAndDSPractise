package com.algo.ds.linkedlist;

public class SingleLinkedList {

	private class Link{
		private int data;
		private Link next;
		
		public Link(int d) {
			data = d;
		}
		
		public void display(){
			System.out.println(data);
		}
	}
	
	private Link first;
	
	public SingleLinkedList() {
		first = null;
	}
	
	public void insertFirst(int d){
		Link newLink = new Link(d);
		newLink.next = first;
		first = newLink;
	}
	
	public Link deleteFirst(){
		Link temp = first;
		first = first.next;
		return temp;
	}
	
	public void displayList(){
		Link current = first;
		while(current != null){
			current.display();
			current = current.next;
		}
	}
	
	public Link findByKey(int key){
		Link current = first;
		while(current.data != key){ // keep on looping until current data is not equal to key
			if(current.next == null) // if not found
				return null;
			else
				current = current.next;
		}
		return current; // found and return current
	}
	
	public Link deleteByKey(int key){
		Link previous = first;
		Link current = first;
		while(current.data != key){ // keep on looping until current data is not equal to key
			if(current.next == null) // if not found
				return null;
			else{
				previous = current; // shift previous and current
				current = current.next;
			}
		}
		if(current == first)
			first = first.next;
		else
			previous.next = current.next;
		
		return current;
	}
}
