package com.algo.ds.linkedlist;

/**
 * http://www.geeksforgeeks.org/delete-nodes-which-have-a-greater-value-on-right-side/
 * 
 * Examples: a) The list 12->15->10->11->5->6->2->3->NULL should be changed to 15->11->6->3->NULL. Note that 12, 10, 5
 * and 2 have been deleted because there is a greater value on the right side.
 * 
 * When we examine 12, we see that after 12 there is one node with value greater than 12 (i.e. 15), so we delete 12.
 * When we examine 15, we find no node after 15 that has value greater than 15 so we keep this node. When we go like
 * this, we get 15->6->3
 * 
 * b) The list 10->20->30->40->50->60->NULL should be changed to 60->NULL. Note that 10, 20, 30, 40 and 50 have been
 * deleted because they all have a greater value on the right side.
 * 
 * c) The list 60->50->40->30->20->10->NULL should not be changed.
 * 
 * Test cases : <br/>
 * Sorted list <br/>
 * reverse sorted list <br/>
 * 0 1 or more nodes in the list
 */
public class DeleteNodeWithGreaterValueOnRight {

	private int maxFound = Integer.MIN_VALUE;

	public Node deleteNodes(Node head) {
		if (head == null) {
			return null;
		}
		Node nextNode = deleteNodes(head.next);
		if (head.data > maxFound) {
			maxFound = head.data;
		}
		if (maxFound > head.data) {
			return nextNode;
		}
		head.next = nextNode;
		return head;
	}

	public static void main(String args[]) {
		DeleteNodeWithGreaterValueOnRight dng = new DeleteNodeWithGreaterValueOnRight();
		LinkList ll = new LinkList();
		Node head = null;
		head = ll.addNode(12, head);
		head = ll.addNode(15, head);
		head = ll.addNode(10, head);
		head = ll.addNode(11, head);
		head = ll.addNode(5, head);
		head = ll.addNode(6, head);
		head = ll.addNode(2, head);
		head = ll.addNode(3, head);
		head = dng.deleteNodes(head);
		ll.printList(head);
	}
}
