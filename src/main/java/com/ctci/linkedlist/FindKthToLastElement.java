package com.ctci.linkedlist;

import com.algo.ds.linkedlist.Link;

/**
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * 
 * @author surya
 *
 */
public class FindKthToLastElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Link l1 = new Link(1);
		l1.next = new Link(2);
		l1.next.next = new Link(3);
		l1.next.next.next = new Link(4);
		l1.next.next.next.next = new Link(5);
		FindKthToLastElement kth = new FindKthToLastElement();
		kth.printList(l1);
		Link l = kth.findKthToLastElement(l1, 2);
		kth.printList(l);
	}

	public void printList(Link head) {
		while (head != null) {
			System.out.print(head.getKey() + "->");
			head = head.next;
		}
		System.out.println("NULL");
	}

	public Link findKthToLastElement(Link head, int k) {
		Link curr = head;
		int c = 1;
		while (curr.next != null && c++ != k) {
			curr = curr.next;
		}
		return k > c ? new Link(0) : curr;
	}

}
