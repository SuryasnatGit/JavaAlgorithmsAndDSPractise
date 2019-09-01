package com.algo.ds.linkedlist;

public class SortedList {

	private Link first;
	
	public SortedList() {
		first = null;
	}
	
	public void insert(Link link){
		Link previous = null;
		Link current = first;
		while(current != null && link.getKey() > current.getKey()){
			previous = current;
			current = current.next;
		}
		
		if(previous == null)
			first = link;
		else
			previous.next = link;
		
		link.next = current;
	}
	
	public void delete(int key){
		Link previous = null;
		Link current = first;
		while(current != null && key != current.getKey()){
			previous = current;
			current = current.next;
		}
		if(previous == null)
			first = first.next; // if beginning of list, delete first link
		else
			previous.next = current.next; // delete current
	}
	
	public Link find(int key){
		Link current = first;
		while(current != null && current.getKey() <= key){
			if(current.getKey() == key)
				return current;
			current = current.next;
		}
		return null;
	}
}
