package com.algo.ds.linkedlist;

/**
 * http://www.geeksforgeeks.org/merge-sort-for-linked-list/.
 * 
 * complexity - O(n log n) Test cases 0 nodes 1 nodes 2 nodes 3 nodes fully sorted reverse sorted
 */
public class MergeSortLinkList {

	public Node sort(Node head, boolean isAscending) {

		if (head == null || head.next == null) {
			return head;
		}
		Node head1 = getMiddleOfList(head);
		head = sort(head, isAscending);
		head1 = sort(head1, isAscending);
		return sortedMerge(head, head1, isAscending);
	}

	private Node sortedMerge(Node head1, Node head2, boolean isAscending) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		if (isAscending) {
			if (head1.data <= head2.data) {
				head1.next = sortedMerge(head1.next, head2, isAscending);
				return head1;
			} else {
				head2.next = sortedMerge(head1, head2.next, isAscending);
				return head2;
			}
		} else {
			if (head1.data >= head2.data) {
				head1.next = sortedMerge(head1.next, head2, isAscending);
				return head1;
			} else {
				head2.next = sortedMerge(head1, head2.next, isAscending);
				return head2;
			}
		}
	}

	private Node getMiddleOfList(Node head) {
		if (head == null) {
			return null;
		}
		Node slow = head;
		Node fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Node newHead = slow.next;
		slow.next = null;
		return newHead;
	}

	/**
	 * https://www.techiedelight.com/merge-two-sorted-linked-lists-end/
	 * 
	 * T - O(n) S - O(1) Function to merge two sorted lists from end
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public Node mergeReverse(Node a, Node b) {
		Node result = null;

		while (a != null && b != null) {
			if (a.data < b.data) {

				// take the node from the front of list a, and move it
				// to the front of the result

				Node newNode = a;
				a = a.next;

				newNode.next = result;
				result = newNode;
			} else {
				// take the node from the front of list b, and move it
				// to the front of the result

				Node newNode = b;
				b = b.next;

				newNode.next = result;
				result = newNode;
			}
		}

		while (b != null) {
			Node newNode = b;
			b = b.next;

			newNode.next = result;
			result = newNode;
		}

		while (a != null) {
			Node newNode = a;
			a = a.next;

			newNode.next = result;
			result = newNode;
		}

		return result;
	}

	public static void main(String args[]) {
		MergeSortLinkList msll = new MergeSortLinkList();
		LinkList ll = new LinkList();
		Node head = null;
		head = ll.addNode(11, head);
		head = ll.addNode(12, head);
		head = ll.addNode(-3, head);
		head = ll.addNode(45, head);
		head = ll.addNode(5, head);
		head = msll.sort(head, true);
		ll.printList(head);
	}
}
