package com.algo.ds.linkedlist;

/**
 * http://www.geeksforgeeks.org/delete-n-nodes-after-m-nodes-of-a-linked-list/ <br/>
 * Test cases: <br/>
 * neg value of m and/or n - not allowed <br/>
 * 0 value of n and/or m - not allowed <br/>
 * even n and m <br/>
 * odd n and m <br/>
 * odd size of the list <br/>
 * even size of the list.<br/>
 * 
 * Time Complexity: O(n) where n is number of nodes in linked list
 */
public class DeleteNAfterMNodes {

	public void deleteNAfterMNodes(Node head, int m, int n) {
		if (head == null) {
			return;
		}
		while (head != null) {
			int i = 0;
			while (head != null && i < m - 1) {
				head = head.next;
				i++;
			}
			if (head == null) {
				break;
			}
			Node temp = head.next;
			i = 0;
			while (temp != null && i < n) {
				temp = temp.next;
				i++;
			}
			head.next = temp;
			head = temp;
		}
	}

	public static void main(String args[]) {
//		DeleteNAfterMNodes daf = new DeleteNAfterMNodes();
//		LinkList ll = new LinkList();
//		Node head = null;
//		head = ll.addNode(1, head);
//		head = ll.addNode(2, head);
//		head = ll.addNode(3, head);
//		head = ll.addNode(4, head);
//		head = ll.addNode(5, head);
//		head = ll.addNode(6, head);
//		daf.deleteNAfterMNodes(head, 2, 2);
//		ll.printList(head);
	}
}
