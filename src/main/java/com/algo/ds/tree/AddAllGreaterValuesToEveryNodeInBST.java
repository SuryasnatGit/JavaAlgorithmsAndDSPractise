package com.algo.ds.tree;

import java.util.Stack;

import com.algo.common.TreeNode;

/*
 * http://www.geeksforgeeks.org/add-greater-values-every-node-given-bst/.
 * 
 * Given a Binary Search Tree (BST), modify it so that all greater values in the given BST are added to every node. For
 * example, consider the following BST.
 * 
 *     		  50
           /      \
         30        70
        /   \      /  \
      20    40    60   80 
The above tree should be modified to following 
              260
           /      \
         330        150
        /   \       /  \
      350   300    210   80
 * 
 * Test cases: Empty tree One node tree Two node tree
 * 
 * Approach: In this problem as we could notice that the largest node would remain the same. 
 * The value of 2nd largest node = value of largest + value of second largest node. 
 * Similarly, the value of nth largest node will be the sum of the n-th node and value of (n-1)th largest node 
 * after modification. So if we traverse the tree in descending order and simultaneously update the sum value at 
 * every step while adding the value to the root node, the problem would be solved. 

So to traverse the BST in descending order we use reverse in-order traversal of BST. 
This takes a global variable sum which is updated at every node and once the root node is reached it is 
added to the value of root node and value of the root node is updated
 * 
 * Category : Medium
 */

public class AddAllGreaterValuesToEveryNodeInBST {

	/**
	 * We can do it using a single traversal. The idea is to use following BST property. If we do reverse Inorder
	 * traversal of BST, we get all nodes in decreasing order. We do reverse Inorder traversal and keep track of the sum
	 * of all nodes visited so far, we add this sum to every node.
	 * 
	 * T - O(n) S - O(1)
	 * 
	 */
	public void convertBSTRecursive(TreeNode root) {
		convertBST(root, new Sum());
	}

	private void convertBST(TreeNode root, Sum ref) {
		if (root == null) {
			return;
		}

		convertBST(root.right, ref);

		root.data += ref.sum;
		ref.sum = root.data;

		convertBST(root.left, ref);
	}

	class Sum {
		int sum = 0;
	}

	/**
	 * 
	 * Approach 2 - Iterative
	 *
	 */
	class BSTIterator {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = null;

		BSTIterator(TreeNode node) {
			cur = node;
		}

		TreeNode getNext() {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}

			TreeNode node = stack.pop();
			cur = node.right;

			return node;
		}

		boolean hasNext() {
			if (cur == null && stack.isEmpty()) {
				return false;
			}
			return true;
		}
	}

	public TreeNode convertBSTIterative(TreeNode root) {
		BSTIterator it = new BSTIterator(root);
		helper(it);
		return root;
	}

	private int helper(BSTIterator it) {
		if (it.hasNext()) {
			TreeNode node = it.getNext();
			// int originalVal = node.val;
			int res = helper(it);
			// node.val = originalVal + res;
			node.data = node.data + res;
			return node.data;
		} else {
			// node.val is still node.val
			// node.val = 0;
			return 0;
		}
	}

	public static void main(String args[]) {
		TreeTraversals tt = new TreeTraversals();

		BinarySearchTree bst = new BinarySearchTree();

		bst.insert(10);
		bst.insert(5);
		bst.insert(20);
		bst.insert(15);
		bst.insert(25);

		AddAllGreaterValuesToEveryNodeInBST agv = new AddAllGreaterValuesToEveryNodeInBST();

		agv.convertBSTRecursive(bst.getRoot());

		tt.inOrder(bst.getRoot());
		System.out.println();
		agv.convertBSTIterative(bst.getRoot());

		tt.inOrder(bst.getRoot());
	}
}
