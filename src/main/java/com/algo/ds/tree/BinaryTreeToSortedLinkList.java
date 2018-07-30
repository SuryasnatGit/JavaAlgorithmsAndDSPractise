package com.algo.ds.tree;

/**
 * http://www.careercup.com/question?id=6241652616200192.
 * 
 * Given a Binary Tree (balanced or not) write a method that transforms the tree in a degenerate
 * tree (basically a data structure like a sorted linked list where each node has the left child
 * null) and returns the new root. This must be made in place, no external memory usage is allowed.
 * 
 * Test cases: 0,1 or more nodes in the tree
 */
public class BinaryTreeToSortedLinkList {

    public Node toSortedLinkList(Node root){
        if(root == null){
            return null;
        }
        
        Node left = toSortedLinkList(root.left);
        Node right = toSortedLinkList(root.right);
        
        root.left = null;
        root.right = null;
        root = merge(left,root);
        return merge(root,right);
    }
    
    private Node merge(Node head1,Node head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        if(head1.data <= head2.data){
            head1.next = merge(head1.next, head2);
            return head1;
        }else{
            head2.next = merge(head1,head2.next);
            return head2;
        }
    }
    
    private void print(Node root){
        while(root != null){
            System.out.print(root.data + " ");
            root = root.next;
        }
    }
    
    public static void main(String args[]){
        int in[] = {-6,0,15,10,3,25,2};
        int pre[] = {10,15,-6,0,25,3,2};
        ConstructTreeFromInOrderPreOrder ct = new ConstructTreeFromInOrderPreOrder();
        Node root = ct.createTree(in, pre);
        BinaryTreeToSortedLinkList bt = new BinaryTreeToSortedLinkList();
        root = bt.toSortedLinkList(root);
        bt.print(root);
    }
}
