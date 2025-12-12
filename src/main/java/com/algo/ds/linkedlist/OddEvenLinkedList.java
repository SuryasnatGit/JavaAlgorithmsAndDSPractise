package com.algo.ds.linkedlist;

public class OddEvenLinkedList {

	/**
	 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
	 * talking about the node position and not the value in the nodes.
	 * 
	 * The program should run in O(1) space complexity and O(n) time complexity. where n is number of nodes
	 * 
	 * @param head
	 * @return
	 */
	public Node rearrangeOddEvenListByPosition(Node head) {
		if (head == null)
			return head;

		Node odd = head;
		Node even = head.next;
		Node connectLink = head.next;

		while (odd != null && even != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;

			odd = odd.next; // combines odds
			even = even.next; // combines even
		}
		odd.next = connectLink; // combines eveens to odds

		return head;
	}

	/**
	 * https://www.techiedelight.com/rearrange-linked-list-separating-odd-nodes-even/
	 * 
	 * @return
	 */
	public Node rearrangeOddEvenListByValue(Node head) {
		Node odd = null, oddTail = null;
		Node even = null, evenTail = null;
		Node curr = head;

		while (curr != null) {
			if ((curr.data & 1) != 0) // current node is odd
			{
				// handle head for first odd node
				if (odd == null) {
					odd = oddTail = curr;
				} else {
					oddTail.next = curr;
					oddTail = oddTail.next;
				}
			} else // current node is even
			{
				// handle head for first even node
				if (even == null) {
					even = evenTail = curr;
				} else {
					evenTail.next = curr;
					evenTail = curr;
				}
			}
			curr = curr.next;
		}

		// if list contains at-least one even node
		if (even != null) {
			head = even;
			evenTail.next = odd;
		}
		// special case - list contains all odd nodes
		else {
			head = odd;
		}

		// null to terminate the list,
		// else it will go in infinite loop
		if (oddTail != null) {
			oddTail.next = null;
		}

		return head;
	}

	public static void main(String[] args) {
		SingleLinkedList sll = new SingleLinkedList();

		OddEvenLinkedList oe = new OddEvenLinkedList();
		Node head = new Node(1);
		Node one = new Node(2);
		head.next = one;
		Node two = new Node(3);
		one.next = two;
		Node three = new Node(4);
		two.next = three;
		Node four = new Node(5);
		three.next = four;

		Node res = oe.rearrangeOddEvenListByPosition(head);
		sll.displayList(res);
		System.out.println();
		Node res1 = oe.rearrangeOddEvenListByValue(head);
		sll.displayList(res1);
	}
}
