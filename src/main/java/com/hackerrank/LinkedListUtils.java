package com.hackerrank;

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
			System.out.print(head.getKey() + "->");
			head = head.next;
		}
		System.out.println();
	}

	/**
	 *
	 */
	public Link deleteNodeInMiddle(Link head, int x) {
		if (head == null)
			return head;
		Link current = head;
		if (x == 0) {
			head = current.next;
			return head;
		}

		// find previous node of the node to be deleted
		for (int i = 0; current != null && i < x - 1; i++) {
			current = current.next;
		}

		// if x is more than number of nodes
		if (current == null || current.next == null)
			return head;

		// unlink the deleted node from the list
		current.next = current.next.next;

		return head;
	}

	/**
	 * Delete Middle Node: Implement an algorithm to delete a node in the middle (Le., any node but the
	 * fi rst and last node, not necessarily the exact middle) of a singly linked list, given only
	 * access to that node. EXAMPLE Input: the node c from the linked list a - >b- >c - >d - >e- >f
	 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
	 * 
	 * @param n
	 * @return
	 */
	public boolean deleteMiddleNode(Link n) {
		if (n == null || n.next == null)
			return false;
		Link next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true;
	}

	public int getNthNodeFromEnd(Link head, int n) {
		Link temp = head;
		int c = 0;
		while (temp != null) {
			c++;
			temp = temp.next;
		}
		temp = head;
		int c1 = 0;
		while (temp != null) {
			c1++;
			if (c1 == c - n)
				return temp.getKey();
			temp = temp.next;
		}
		return 0;
	}

	/**
	 * Given a linked list, swap every two adjacent nodes and return its head. For example, Given
	 * 1->2->3->4, you should return the list as 2->1->4->3. Your algorithm should use only constant
	 * space. You may not modify the values in the list, only nodes itself can be changed.
	 * 
	 * @param head
	 * @return
	 */
	public Link swapPairs(Link head) {
		Link dummy = new Link(-1);

		dummy.next = head;
		Link p = dummy;
		while (p.next != null && p.next.next != null) {
			Link next = p.next;
			Link next2 = p.next.next;
			p.next = next2;
			next.next = next2.next;
			next2.next = next;

			p = next;
		}

		return dummy.next;
	}

	public Link cloneList(Link head) {
		if (head == null)
			return null;

		Link newHead = null;
		Link tail = null;
		Link current = head;

		while (current != null) {
			if (newHead == null) {
				newHead = new Link(current.data);
				tail = newHead;
			} else {
				Link temp = new Link(current.data);
				tail.next = temp;
				tail = tail.next;
				tail.next = null;
			}
			current = current.next;
		}
		return newHead;
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

		Link t = utils.cloneList(a1);
		utils.displayList(t);

		Link b1 = new Link(3);
		Link b2 = new Link(4);
		b1.next = b2;
		// utils.displayList(b1);
//		Link l = utils.deleteNodeInMiddle(a1, 3);
//		utils.displayList(l);
		// utils.displayList(head);
		// System.out.println(utils.getNthNodeFromEnd(a1, 3));
		// a.next = new Link(5);
		// System.out.println("********");
		// utils.displayList(utils.mergeSortedListsRecursive(a1, b1));
		// System.out.println("********");
		// utils.displayList(utils.mergeSortedListsNonRecursive(a1, b1));
	}

}
