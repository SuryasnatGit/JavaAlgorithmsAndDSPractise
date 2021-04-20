

package com.algo.ds.linkedlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/given-linked-list-representation-of-complete-tree-convert-it-to-linked-representation/
 * Test cases
 * Zero, One or more nodes in link list.
 * 
 * Approach:
 * 
 * We are mainly given level order traversal in sequential access form. We know head of linked list is always is root of the tree. 
 * We take the first node as root and we also know that the next two nodes are left and right children of root. 
 * So we know partial Binary Tree. The idea is to do Level order traversal of the partially built Binary Tree using queue and 
 * traverse the linked list at the same time. At every step, we take the parent node from queue, make next two nodes of linked 
 * list as children of the parent node, and enqueue the next two nodes to queue.
 *
 *   1. Create an empty queue.
 *   2. Make the first node of the list as root, and enqueue it to the queue.
 *   3. Until we reach the end of the list, do the following.
 *       a. Dequeue one node from the queue. This is the current parent.
 *       b. Traverse two nodes in the list, add them as children of the current parent.
 *       c. Enqueue the two nodes into the queue.
 *
 *  Time Complexity: Time complexity of the above solution is O(n) where n is the number of nodes.
 */
public class LinkListToCompleteBinaryTree {

    public void convert(Node head){
        if(head == null){
            return;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.convertBSTRecursive(head); // enqueue root to queue
        head = head.next;
        while(head != null){
            Node top = queue.poll();
            top.before = head; // left child added
            head = head.next; // progress to next node in list
            if(head != null){
                top.next = head; // right child added
                head = head.next; // progress to next node in list
                //null the next of child before putting them into queue
                top.before.next = null;
                top.next.next = null;
                queue.convertBSTRecursive(top.before);
                queue.convertBSTRecursive(top.next);
            }else{
                break;
            }
        }
     }
    
    public void inorder(Node head){
        if(head == null){
            return;
        }
        inorder(head.before);
        System.out.print(head.data + " ");
        inorder(head.next);
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(10, head);
        head = ll.addNode(12, head);
        head = ll.addNode(15, head);
        head = ll.addNode(25, head);
        head = ll.addNode(30, head);
        head = ll.addNode(36, head);
        head = ll.addNode(40, head);
        head = ll.addNode(45, head);
        
        LinkListToCompleteBinaryTree llct = new LinkListToCompleteBinaryTree();
        llct.convert(head);
        llct.inorder(head);
    }
}
