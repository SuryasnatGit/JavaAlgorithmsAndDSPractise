package com.algo.ds.linkedlist;

/**
 * Time Complexity: Time complexity of the above implementation is same as time complexity of QuickSort() for arrays. It
 * takes O(n^2) time in worst case and O(nLogn) in average and best cases. The worst case occurs when the linked list is
 * already sorted.
 * 
 * Can we implement random quick sort for linked list? Quicksort can be implemented for Linked List only when we can
 * pick a fixed point as pivot (like last element in above implementation). Random QuickSort cannot be efficiently
 * implemented for Linked Lists by picking random pivot.
 * 
 * @author Suryasnat
 *
 */
public class QuickSortDoublyLinkedList {

	Node head;

	class Node {
		private int data;
		private Node prev;
		private Node next;

		public Node(int d) {
			data = d;
			prev = null;
			next = null;
		}
	}

	void quickSort(Node head) {
		// get last node as pivot
		Node last = getLastNode(head);
		// call recursive quick sort
		quickSortRecursive(head, last);
	}

	private void quickSortRecursive(Node head, Node last) {
		if (last != null && head != last && head.next != last) {
			Node pivot = partition(head, last);
			quickSortRecursive(head, pivot.prev);
			quickSortRecursive(pivot.next, last);
		}
	}

	/**
	 * Considers last element as pivot, places the pivot element at its correct position in sorted array, and places all
	 * smaller (smaller than pivot) to left of pivot and all greater elements to right of pivot
	 */
	private Node partition(Node head, Node last) {
		int pivotData = last.data;
		Node i = head.prev; // initially points to null
		for (Node j = head; j != last; j = j.next) {
			if (j.data < pivotData) {
				// move i
				i = (i == null) ? head : i.next;
				// swap i.data and j.dada
				int temp = i.data;
				i.data = j.data;
				j.data = temp;
			}
		}
		// move i
		i = (i == null) ? head : i.next;
		// swap i.data and last.dada
		int temp = i.data;
		i.data = last.data;
		last.data = temp;

		return i;
	}

	private Node getLastNode(Node head) {
		while (head.next != null)
			head = head.next;
		return head;
	}

	// A utility function to print contents of arr
	public void printList(Node head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	/* Function to insert a node at the beginging of the Doubly Linked List */
	void push(int new_Data) {
		Node new_Node = new Node(new_Data); /* allocate node */

		// if head is null, head = new_Node
		if (head == null) {
			head = new_Node;
			return;
		}

		/* link the old list off the new node */
		new_Node.next = head;

		/* change prev of head node to new node */
		head.prev = new_Node;

		/* since we are adding at the begining, prev is always NULL */
		new_Node.prev = null;

		/* move the head to point to the new node */
		head = new_Node;
	}

	public static void main(String[] args) {
		QuickSortDoublyLinkedList list = new QuickSortDoublyLinkedList();
		list.push(5);
		list.push(20);
		list.push(4);
		list.push(3);
		list.push(30);

		System.out.println("Linked List before sorting ");
		list.printList(list.head);
		System.out.println("\nLinked List after sorting");
		list.quickSort(list.head);
		list.printList(list.head);
	}

}
