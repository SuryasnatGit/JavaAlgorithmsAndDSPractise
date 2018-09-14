

package com.algo.ds.linkedlist;


/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers (all occurrences), leaving only numbers that
 * appear once in the original list. Input : 23->28->28->35->49->49->53->53 Output : 23->35 Input :
 * 11->11->11->11->75->75 Output : empty List. Time Complexity : O(n)
 * 
 * @author ctsuser1
 */
public class RemoveAllDuplicatesFromSortedList {

    public void removeAllDuplicates(Node head) {
        if (head == null)
            return;

        // make a temp head
        Node tempHead = new Node();
        tempHead.next = head;
        Node prev = tempHead;
        Node curr = head;
        while (curr != null) {
            // traverse nodes till curr and prev values are same
            while (curr.next != null && prev.next.data == curr.next.data)
                curr = curr.next;

            if (prev.next == curr)
                prev = prev.next; /*
                                   * if current has unique value i.e current is not updated, Move the prev pointer to
                                   * next node
                                   */
            else
                prev.next = curr.next; /*
                                        * when current is updated to the last duplicate value of that segment, make prev
                                        * the next of current
                                        */

            curr = curr.next;
        }
        head = tempHead.next; // restore
    }

}
