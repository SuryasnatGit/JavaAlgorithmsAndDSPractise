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
 * 
 * 2) If sizes are same, then calculate sum using recursion. Hold all nodes in recursion call stack
 * till the rightmost node, calculate sum of rightmost nodes and forward carry to left side.<br/>
 * 
 * 3) If size is not same, then follow below steps: �.<br/>
 * a) Calculate difference of sizes of two linked lists. Let the difference be diff �.<br/>
 * 
 * b) Move diff nodes ahead in the bigger linked list. Now use step 2 to calculate sum of smaller
 * list and right sub-list (of same size) of larger list. Also, store the carry of this sum. �.<br/>
 * 
 * c) Calculate sum of the carry (calculated in previous step) with the remaining left sub-list of
 * larger list. Nodes of this sum are added at the beginning of sum list obtained previous step.
 * 
 * <n/> Time Complexity: O(m+n) where m and n are the sizes of given two linked lists.
 * 
 * Category : Hard
 */
public class AddNumberRepresentedByLinkList {

	private int carry = 0;

	/**
	 * Input: First List: 5->6->3 // represents number 563 Second List: 8->4->2 // represents number 842
	 * Output Resultant list: 1->4->0->5 // represents number 1405
	 * 
	 * @param head1
	 * @param head2
	 * @return
	 */
	public Node add_forwordOrder(Node head1, Node head2) {
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

	/**
	 * Given two numbers represented by two lists, write a function that returns sum list. The sum list
	 * is list representation of addition of two input numbers.
	 * 
	 * Example 1
	 * 
	 * Input: First List: 5->6->3 // represents number 365 Second List: 8->4->2 // represents number 248
	 * Output Resultant list: 3->1->6 // represents number 613 Example 2
	 * 
	 * Input: First List: 7->5->9->4->6 // represents number 64957 Second List: 8->4 // represents
	 * number 48 Output Resultant list: 5->0->0->5->6 // represents number 65005.
	 * 
	 * Soln: Traverse both lists. One by one pick nodes of both lists and add the values. If sum is more
	 * than 10 then make carry as 1 and reduce sum. If one list has more elements than the other then
	 * consider remaining values of this list as 0.
	 * 
	 * Time Complexity: O(m + n) where m and n are number of nodes in first and second lists
	 * respectively.
	 * 
	 * @return
	 */
	public Node add_reverseOrder(Node first, Node second) {
		/* Adds contents of two linked lists and return the head node of resultant list */
		Node res = null; // res is head node of the resultant list
		Node prev = null;
		Node temp = null;
		int carry = 0, sum;

		while (first != null || second != null) // while both lists exist
		{
			// Calculate value of next digit in resultant list.
			// The next digit is sum of following things
			// (i) Carry
			// (ii) Next digit of first list (if there is a next digit)
			// (ii) Next digit of second list (if there is a next digit)
			sum = carry + (first != null ? first.data : 0) + (second != null ? second.data : 0);

			// update carry for next calulation
			carry = (sum >= 10) ? 1 : 0;

			// update sum if it is greater than 10
			sum = sum % 10;

			// Create a new node with sum as data
			temp = Node.newNode(sum);

			// if this is the first node then set it as head of
			// the resultant list
			if (res == null) {
				res = temp;
			} else // If this is not the first node then connect it to the rest.
			{
				prev.next = temp;
			}

			// Set prev for next insertion
			prev = temp;

			// Move first and second pointers to next nodes
			if (first != null) {
				first = first.next;
			}
			if (second != null) {
				second = second.next;
			}
		}

		if (carry > 0) {
			temp.next = Node.newNode(carry);
		}

		// return head of the resultant list
		return res;
	}

	public static void main(String args[]) {
		LinkList ll = new LinkList();
		Node head = null;
		head = ll.addNode(9, head);
		head = ll.addNode(4, head);
		ll.printList(head);
		Node head1 = null;
		head1 = ll.addNode(9, head1);
		head1 = ll.addNode(1, head1);
		head1 = ll.addNode(2, head1);
		ll.printList(head1);
		AddNumberRepresentedByLinkList anr = new AddNumberRepresentedByLinkList();
		Node result = anr.add_forwordOrder(head, head1);
		ll.printList(result);
	}
}
