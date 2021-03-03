package com.leetcode;

import com.algo.common.TreeNode;

/**
 * 
 *
 */
public class BinaryTreeLongestConsecutiveSequence {
	
	/**
	 * Problem 1 :
	 * 
	 * Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
	 */
	
	int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, 0, root.data);
        return max;
    }
    
    // A very naive DFS solution
    void helper(TreeNode node, int cur, int target) {
        if (node == null) {
            return;
        }
        // Looks like pre-order
        if (node.data == target) {
            cur++;
        } else {
            cur = 1;
        }
        
        max = Math.max(max, cur);
        helper(node.left, cur, node.data + 1);
        helper(node.right, cur, node.data + 1);
    }
    
    /**
     * Problem 2 : 
     * 
     * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, 
but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
     */
    public int longestConsecutivePath(TreeNode root) {
        helper(root);
        return max;
    }
    
    Result helper(TreeNode node) {
        if (node == null) { // 这个地方return null最合适
            return null;
        }
        
        Result left = helper(node.left);
        Result right = helper(node.right);
        
        Result now = new Result();
        now.node = node;
        now.inc = 1;
        now.dec = 1; // Leaf node, everything by itself
        
        if (left != null) {
            if (node.data - left.node.data == 1) { // increase
                now.inc = Math.max(now.inc, left.inc + 1);
            } else if (node.data - left.node.data == -1) { // decrease
                now.dec = Math.max(now.dec, left.dec + 1);
            }
        }
        
        if (right != null) {
            if (node.data - right.node.data == 1) { // increase
                now.inc = Math.max(now.inc, right.inc + 1);
            } else if (node.data - right.node.data == -1) { // decrease
                now.dec = Math.max(now.dec, right.dec + 1);
            }
        }
        
        max = Math.max(max, now.inc + now.dec - 1);
        
        return now;
    }
    
    class Result {
        TreeNode node;
        int inc;
        int dec;
    }
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node22 = new TreeNode(2);
    	TreeNode node4 = new TreeNode(4);
    	TreeNode node5 = new TreeNode(5);
    	
    	node1.right = node3;
    	node3.left = node2;
    	node3.right = node4;
    	node4.right = node5;
    	
    	BinaryTreeLongestConsecutiveSequence l = new BinaryTreeLongestConsecutiveSequence();
    	int res = l.longestConsecutive(node1);
    	
    	System.out.println(res);
	}
}
