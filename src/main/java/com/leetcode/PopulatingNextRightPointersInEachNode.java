package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
 * 
 *
 */
public class PopulatingNextRightPointersInEachNode {
	
	public void connect1Recursion(TreeLinkNode root) {
        if (root == null) return;
        helper(root.left, root.right);
    }
    
    void helper(TreeLinkNode left, TreeLinkNode right) {
        if (left == null) {
            return;
        }
        left.next = right;
        
        helper(left.left, left.right);
        helper(left.right, right.left);
        helper(right.left, right.right);
    }
    
    public void connect1(TreeLinkNode root) {
        TreeLinkNode levelStart = root; 
        while (levelStart != null) { // Line by line
            TreeLinkNode cur = levelStart;
            while (cur != null) { // Each item in a line
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.next != null && cur.right != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            
            levelStart = levelStart.left;
        }
    }
    
    // Level order traversal
    public void connect1LevelOrderTraversal(TreeLinkNode root) {
        if (root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            TreeLinkNode prev = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = queue.poll();
                
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;
            }
        }
    }
    
    /**
     * 117. Follow up for problem "Populating Next Right Pointers in Each Node".

    What if the given tree could be any binary tree? Would your previous solution still work?

    Note:

    You may only use constant extra space.
    For example,
    Given the following binary tree,
             1
           /  \
          2    3
         / \    \
        4   5    7
    After calling your function, the tree should look like:
             1 -> NULL
           /  \
          2 -> 3 -> NULL
         / \    \
        4-> 5 -> 7 -> NULL
     */
    public void connect2LevelOrderTraversal(TreeLinkNode root) {
        if (root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            TreeLinkNode prev = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = queue.poll();
                
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;
            }
        }
    }
    
    /**
     * 唯一不一样的是，每一个level的最后一个node的next要指向下一层的第一个node。
     */
    public void connect2_2(TreeLinkNode root) {
        if (root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.offer(root);
        TreeLinkNode prev = null; // Here is the only change, move this prev node out from while loop, Nice!!!
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = queue.poll();
                
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;
            }
        }
    }
    
    public void connect2_1(TreeLinkNode root) {
        TreeLinkNode prev = null; // cur and prev are 1 level above, to overview left and right nodes
        TreeLinkNode head = null; // head of next level
        TreeLinkNode cur = root;
        
        while (cur != null) {
            
            while (cur != null) {
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                
                cur = cur.next;
            }
            
            cur = head;
            prev = null;
            head = null;
        }
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}