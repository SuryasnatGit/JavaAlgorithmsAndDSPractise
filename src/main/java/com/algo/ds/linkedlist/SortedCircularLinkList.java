package com.algo.ds.linkedlist;

/**
 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 * 
 * Test cases: Insert 2nd element smaller than head.<br/>
 * Insert 2nd element larger than head.<br/>
 * Insert element larger than tail.<br/>
 * Insert element just before tail.<br/>
 * Insert element somewhere between head and tail.<br/>
 */
public class SortedCircularLinkList {

	public Node add(Node head, int data) {
		if (head == null) {
			head = Node.newNode(data);
			head.next = head;
			return head;
		}
		Node node = Node.newNode(data);
		Node tail = getTail(head);
		if (node.data < head.data) {
			node.next = head;
			tail.next = node;
			return node;
		}
		Node current = head;
		Node pre = null;
		while (current != tail && node.data >= current.data) {
			pre = current;
			current = current.next;
		}
		if (node.data < current.data) {
			node.next = current;
			pre.next = node;
		} else {
			node.next = tail.next;
			tail.next = node;
		}
		return head;
	}

	private Node getTail(Node head) {
		Node temp = head;
		while (temp.next != head) {
			temp = temp.next;
		}
		return temp;
	}

	public void printList(Node head) {
		if (head == null) {
			return;
		}
		Node current = head.next;
		System.out.println(head.data);
		while (current != head) {
			System.out.println(current.data);
			current = current.next;
		}
	}

	public static void main(String args[]) {
		SortedCircularLinkList scll = new SortedCircularLinkList();
		Node head = null;
		head = scll.addInterval(head, 10);
		head = scll.addInterval(head, 12);
		head = scll.addInterval(head, -1);
		head = scll.addInterval(head, -5);
		head = scll.addInterval(head, 11);
		head = scll.addInterval(head, 7);

		scll.printList(head);
	}
}
