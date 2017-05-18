package com.hackerrank.ds.linkedlist;

import com.algo.ds.linkedlist.Link;

public class LinkedListUtils {

	public int compareLists(Link a, Link b) {
		while (a != null && b != null) {
			if (a.getKey() != b.getKey())
				return 0;
			a = a.next;
			b = b.next;
		}
		return a == null && b == null ? 1 : 0;
	}

	public Link mergeSortedListsRecursive(Link a, Link b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		if (a.getKey() < b.getKey()) {
			a.next = mergeSortedListsRecursive(a.next, b);
			return a;
		} else {
			b.next = mergeSortedListsRecursive(a, b.next);
			return b;
		}
	}

	public Link mergeSortedListsNonRecursive(Link a, Link b) {
		Link temp = null;
		while (a != null && b != null) {
			if (a.getKey() < b.getKey()) {
				temp = formSortedList(a, temp);
				a = a.next;
			} else {
				temp = formSortedList(b, temp);
				b = b.next;
			}
		}
		while (a != null) {
			temp = formSortedList(a, temp);
			a = a.next;
		}
		while (b != null) {
			temp = formSortedList(b, temp);
			b = b.next;
		}
		return temp;
	}

	private Link formSortedList(Link tempLink, Link head) {
		Link current = head;
		Link temp = new Link(tempLink.getKey());
		if (head == null) {
			head = temp;
		} else {
			current.next = temp;
			current = current.next;
		}
		return head;
	}

	private void displayList(Link head) {
		while (head != null) {
			System.out.println(head.getKey());
			head = head.next;
		}
	}

	/**
	 * wip. not 100% correct
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public Link deleteNode(Link head, int x) {
		if (head == null) {
			return null;
		} else {
			Link current = head;
			Link previous = head;
			while (current.next != null) {
				previous = current;
				if (current.getKey() > x) {
					previous.next = current.next;
					previous = previous.next;
					current = previous;
				}

				current = current.next;

			}
			// if(current.getKey() > x)
			return head;
		}
	}

	public static void main(String[] args) {
		LinkedListUtils utils = new LinkedListUtils();
		Link a = null;
		Link b = null;
		// System.out.println(utils.compareLists(a, b));
		b = new Link(2);
		// System.out.println(utils.compareLists(a, b));
		a = new Link(3);
		b = new Link(3);
		a.next = b;
		// System.out.println(utils.compareLists(a, b));

		Link a1 = new Link(5);
		Link a2 = new Link(3);
		Link a3 = new Link(1);
		a1.next = a2;
		a2.next = a3;
		utils.displayList(a1);
		System.out.println("********");
		Link b1 = new Link(3);
		Link b2 = new Link(4);
		b1.next = b2;
		// utils.displayList(b1);
		utils.displayList(utils.deleteNode(a1, 3));
		// a.next = new Link(5);
		// System.out.println("********");
		// utils.displayList(utils.mergeSortedListsRecursive(a1, b1));
		// System.out.println("********");
		// utils.displayList(utils.mergeSortedListsNonRecursive(a1, b1));
	}

}
