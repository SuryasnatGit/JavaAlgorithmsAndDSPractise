package com.algo.ds.linkedlist;

/**
 * Swap two nodes in double link list. If it swaps first node its callers responsibility to fix the head.
 * 
 * Given a linked list and two keys in it, swap nodes for two given keys. Nodes should be swapped by changing links.
 * Swapping data of nodes may be expensive in many situations when data contains many fields. It may be assumed that all
 * keys in linked list are distinct.
 * 
 * Examples:
 * 
 * Input: 10->15->12->13->20->14, x = 12, y = 20 Output: 10->15->20->13->12->14
 * 
 * Input: 10->15->12->13->20->14, x = 10, y = 20 Output: 20->15->12->13->10->14
 * 
 * Input: 10->15->12->13->20->14, x = 12, y = 13 Output: 10->15->13->12->20->14
 * 
 * 
 * Test cases:<br/>
 * A right neighbor of B.<br/>
 * B right neighbor of A.<br/>
 * A and B not neighbors of each other.<br/>
 * A or B are start or end nodes.<br/>
 */
public class SwapTwoNodesInDoubleLL {

	public void swap(Node nodeA, Node nodeB) {
		if (nodeA == null || nodeB == null) {
			throw new IllegalArgumentException();
		}
		// if B is right neighbor of A
		if (nodeA.next == nodeB) {
			if (nodeA.before != null) {
				nodeA.before.next = nodeB;
				nodeB.before = nodeA.before;
			} else {
				nodeB.before = null;
			}
			if (nodeB.next != null) {
				nodeB.next.before = nodeA;
				nodeA.next = nodeB.next;
			} else {
				nodeA.next = null;
			}
			nodeB.next = nodeA;
			nodeA.before = nodeB;
		} // else if A is right neighbor of B
		else if (nodeB.next == nodeA) {
			if (nodeB.before != null) {
				nodeB.before.next = nodeA;
				nodeA.before = nodeB.before;
			} else {
				nodeA.before = null;
			}
			if (nodeA.next != null) {
				nodeA.next.before = nodeB;
				nodeB.next = nodeA.next;
			} else {
				nodeB.next = null;
			}
			nodeA.next = nodeB;
			nodeB.before = nodeA;
		} // if A and B are not neighbors of each other
		else {
			if (nodeA.before != null) {
				nodeA.before.next = nodeB;
			}
			if (nodeA.next != null) {
				nodeA.next.before = nodeB;
			}
			Node next = nodeB.next;
			Node before = nodeB.before;
			nodeB.before = nodeA.before;
			nodeB.next = nodeA.next;

			if (next != null) {
				next.before = nodeA;
			}
			if (before != null) {
				before.next = nodeA;
			}
			nodeA.before = before;
			nodeA.next = next;
		}
	}

	public static void main(String args[]) {
		DoublyLinkedList dll = new DoublyLinkedList();
		dll.insertLast(1);
		dll.insertLast(2);
		dll.insertLast(3);
		dll.insertLast(4);
		dll.insertLast(5);

		SwapTwoNodesInDoubleLL snt = new SwapTwoNodesInDoubleLL();
		Node nodeA = Node.newNode(3);
		Node nodeB = Node.newNode(5);
		snt.swap(nodeA, nodeB);

	}
}
