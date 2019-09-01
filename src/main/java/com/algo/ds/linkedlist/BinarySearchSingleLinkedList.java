

package com.algo.ds.linkedlist;


/**
 * Given a singly linked list and a key, find key using binary search approach. To perform a Binary search based on
 * Divide and Conquer Algorithm, determination of the middle element is important. Binary Search is usually fast and
 * efficient for arrays because accessing the middle index between two given indices is easy and fast(Time Complexity
 * O(1)). But memory allocation for the singly linked list is dynamic and non-contiguous, which makes finding the middle
 * element difficult. One approach could be of using skip list, one could be traversing the linked list using one
 * pointer. complexity - O(n). Approach : Here, start node(set to Head of list), and the last node(set to NULL
 * initially) are given. Middle is calculated using two pointers approach. If middle’s data matches the required value
 * of search, return it. Else if midele’s data < value, move to upper half(setting last to midele's next). Else go to
 * lower half(setting last to middle). The condition to come out is, either element found or entire list is traversed.
 * When entire list is traversed, last points to start i.e. last -> next == start.
 * 
 * @author ctsuser1
 */
public class BinarySearchSingleLinkedList {

    public Node findMiddleElement(Node start, Node end) {
        if (start == null)
            return null;

        Node slow = start;
        Node fast = start.next;

        while (fast != end) {
            fast = fast.next;
            if (fast != end) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }

    public Node binarySearch(Node head, int value) {
        Node start = head;
        Node last = null;

        while (last == null || last.next != start) {
            Node middle = findMiddleElement(start, last);
            if (middle == null)
                return null;
            if (middle.data == value)
                return middle;
            else if (middle.data < value)
                start = middle.next;
            else
                last = middle;
        }
        return null;
    }

}
