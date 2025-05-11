package com.algo.ds.linkedlist;

public class SingleLinkedList {

	private Node head;

	public SingleLinkedList() {
		head = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public Node getHead() {
		return head;
	}

	public void addNode(int d) {
		addNodeAtFront(d);
	}

	public void addNodeAtFront(int d) {
		Node node = new Node(d);
		node.next = head;
		head = node;
	}

	public void addNodeAtEnd(int d) {
		Node node = new Node(d);
		if (head == null) {
			head = node;
			return;
		}

		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = node;
		return;
	}

	public void displayList() {
		Node current = head;
		while (current != null) {
			System.out.print(current.getData() + " -> ");
			current = current.next;
		}
		System.out.print("null");
	}

	public void displayList(Node head) {
		Node current = head;
		while (current != null) {
			System.out.print(current.getData() + " -> ");
			current = current.next;
		}
		System.out.print("null");
	}

	public Node findByKey(int key) {
		Node current = head;
		while (current.getData() != key) { // keep on looping until current data is not equal to key
			if (current.next == null) { // if not found
				return null;
			} else {
				current = current.next;
			}
		}
		return current; // found and return current
	}

	public Node deleteByKey(int key) {
		Node previous = head;
		Node current = head;
		while (current.getData() != key) { // keep on looping until current data is
			// not equal to key
			if (current.next == null) // if not found
				return null;
			else {
				previous = current; // shift previous and current
				current = current.next;
			}
		}
		if (current == head)
			head = head.next;
		else
			previous.next = current.next;

		return current;
	}

	public int size(Node head) {
		Node node = head;
		int count = 0;
		while (node != null) {
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		SingleLinkedList sll = new SingleLinkedList();
		sll.addNodeAtFront(10);
		sll.addNodeAtFront(20);
		sll.addNodeAtEnd(30);
		sll.addNodeAtEnd(40);
		sll.displayList();
		sll.deleteByKey(30);
		System.out.println();
		sll.displayList();
		sll.deleteByKey(20);
		System.out.println();
		sll.displayList();
		sll.deleteByKey(10);
		sll.deleteByKey(40);
		System.out.println();
		sll.displayList();
	}
}
