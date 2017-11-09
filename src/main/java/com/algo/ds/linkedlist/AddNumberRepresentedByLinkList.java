package com.algo.ds.linkedlist;

/**
 * http://www.geeksforgeeks.org/sum-of-two-linked-lists/<br/>
 * Test case.. <br/>
 * 1. Either of list is null.<br/>
 * 2. Size of list1 is greater, equal or smaller than list2.<br/>
 * 3. Add with carry in main. <br/>
 * 4. Add with carry in remaining.
 * 
 * <br/>
 * Steps: Following are the steps. <br/>
 * 1) Calculate sizes of given two linked lists. <br/>
 * 2) If sizes are same, then calculate sum using recursion. Hold all nodes in recursion call stack till the rightmost
 * node, calculate sum of rightmost nodes and forward carry to left side.<br/>
 * 3) If size is not same, then follow below steps: ….<br/>
 * a) Calculate difference of sizes of two linked lists. Let the difference be diff ….<br/>
 * b) Move diff nodes ahead in the bigger linked list. Now use step 2 to calculate sum of smaller list and right
 * sub-list (of same size) of larger list. Also, store the carry of this sum. ….<br/>
 * c) Calculate sum of the carry (calculated in previous step) with the remaining left sub-list of larger list. Nodes of
 * this sum are added at the beginning of sum list obtained previous step.
 * 
 * <n/> Time Complexity: O(m+n) where m and n are the sizes of given two linked lists.
 */
public class AddNumberRepresentedByLinkList {

	private int carry = 0;

	private Node addWithCarry(Node head1, Node head2) {
		if (head1 == null) {
			return null;
		}
		Node result = Node.newNode(0);
		result.next = addWithCarry(head1.next, head2.next);
		int r = head1.data + head2.data + carry;
		result.data = r % 10;
		carry = r / 10;
		return result;
	}

	private Node addRemaining(Node start, Node stop) {
		if (start != stop) {
			Node result = Node.newNode(0);
			result.next = addRemaining(start.next, stop);
			result.data = (start.data + carry) % 10;
			carry = (start.data + carry) / 10;
			return result;
		} else {
			return null;
		}
	}

	public Node add(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			throw new IllegalArgumentException();
		}
		LinkList ll = new LinkList();
		int size1 = ll.size(head1);
		int size2 = ll.size(head2);
		Node larger = null;
		Node smaller = null;
		if (size1 >= size2) {
			larger = head1;
			smaller = head2;
		} else {
			larger = head2;
			smaller = head1;
		}
		int diff = Math.abs(size1 - size2);
		Node largerStart = larger;
		while (diff > 0) {
			largerStart = largerStart.next;
			diff--;
		}
		Node result = addWithCarry(largerStart, smaller);
		Node result1 = addRemaining(larger, largerStart);
		if (carry != 0) {
			Node top = Node.newNode(carry);
			result1 = ll.addAtFront(top, result1);
		}
		if (result1 != null) {
			Node tail = result1;
			while (tail.next != null) {
				tail = tail.next;
			}
			tail.next = result;
			return result1;
		}
		return result;
	}

	public static void main(String args[]) {
		LinkList ll = new LinkList();
		Node head = null;
		head = ll.addNode(9, head);
		head = ll.addNode(4, head);

		Node head1 = null;
		head1 = ll.addNode(9, head1);
		head1 = ll.addNode(1, head1);
		head1 = ll.addNode(2, head1);

		AddNumberRepresentedByLinkList anr = new AddNumberRepresentedByLinkList();
		Node result = anr.add(head, head1);
		ll.printList(result);
	}
}
