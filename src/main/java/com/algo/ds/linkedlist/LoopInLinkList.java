

package com.algo.ds.linkedlist;


import com.algo.common.ListNode;

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
     * extending the Node for second approach
     * 
     * @author ctsuser1
     */
    class ExtNode extends ListNode {
        boolean isVisited = false;

        public ExtNode(int d) {
            super(d);
        }
    }

    /**
     * this approach involves modification of node to have a boolean field to store the isVisited flag
     * 
     * @param head
     * @return
     */
    public boolean hasCycle_markVisitedNodes(ListNode head) {
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
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast || (fast != null && fast.next == slow)) {
                return true;
            }
        }
        return false;
    }

}
