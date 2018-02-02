

package com.algo.ds.linkedlist;


/**
 * http://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/ <br/>
 * 
 * 
 * Test cases : <br/>
 * All duplicates <br/>
 * No duplicates <br/>
 * Duplicates only in starting <br/>
 * Duplicates only at the end <br/>
 * 0 1 or more nodes in the list
 * 
 * Time Complexity: O(n) where n is number of nodes in the given linked list.
 *
 */
public class RemoveDuplicatesSortedList {

    public void removeDuplicates(Node head) {
        if (head == null) {
            return;
        }
        Node current = head;
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            }
            else {
                current = current.next;
            }
        }
    }

    public static void main(String args[]) {
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(1, head);
        head = ll.addNode(1, head);
        head = ll.addNode(4, head);
        head = ll.addNode(4, head);
        head = ll.addNode(5, head);
        head = ll.addNode(6, head);
        head = ll.addNode(6, head);
        head = ll.addNode(6, head);
        RemoveDuplicatesSortedList rds = new RemoveDuplicatesSortedList();
        rds.removeDuplicates(head);
        ll.printList(head);
    }
}
