

package com.algo.ds.linkedlist;


import java.util.HashSet;
import java.util.Set;


/**
 * Date 04/17/2016 Given a linked list, determine if it has a cycle in it. Time complexity O(n) Space complexity O(1)
 * https://leetcode.com/problems/linked-list-cycle/
 * 
 * @author tusroy
 */
public class LoopInLinkList {

    /**
     * Time complexity - O(n) .Space complexity - O(n) as set occupies n nodes max
     * 
     * @param head
     * @return
     */
    public boolean hasCycle_usingHash(Node head) {
        // container to hold the nodes
        Set<Node> hashSet = new HashSet<>();
        while (head != null) {
            if (hashSet.contains(head))
                return true;
            hashSet.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * extending the Node for second approach
     * 
     * @author ctsuser1
     */
    class ExtNode extends Node {
        boolean isVisited = false;
    }

    /**
     * this approach involves modification of node to have a boolean field to store the isVisited flag
     * 
     * @param head
     * @return
     */
    public boolean hasCycle_markVisitedNodes(Node head) {
        ExtNode curr = (ExtNode)head;
        while (curr != null) {
            if (curr.isVisited)
                return true;
            curr.isVisited = true;
            curr = (ExtNode)curr.next;
        }
        return false;
    }

    /**
     * Floyd’s Cycle-Finding Algorithm: This is the fastest method. Traverse linked list using two pointers. Move one
     * pointer by one and other pointer by two. If these pointers meet at some node then there is a loop. If pointers do
     * not meet then linked list doesn’t have loop. Time Complexity: O(n) Auxiliary Space: O(1)
     * 
     * @param head
     * @return
     */
    public boolean hasCycle(Node head) {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast || (fast != null && fast.next == slow)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(2, head);
        head = ll.addNode(3, head);
        head = ll.addNode(4, head);
        head = ll.addNode(5, head);
        head = ll.addNode(6, head);
        head = ll.addNode(7, head);
        head = ll.addNode(8, head);
        Node node1 = ll.find(head, 8);
        Node node2 = ll.find(head, 4);
        node1.next = node2;
        LoopInLinkList lll = new LoopInLinkList();
        System.out.println(lll.hasCycle(head));

        node2.next = null;
        System.out.println(lll.hasCycle(head));

        node1 = ll.find(head, 3);
        node2.next = node1;
        System.out.println(lll.hasCycle(head));
    }
}
