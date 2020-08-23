package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Given a binary tree, write a function that returns true if the tree satisfies below property.
 * 
 * For every node, data value must be equal to sum of data values in left and right children. Consider data value as 0
 * for NULL children.
 * 
 * Traverse the given binary tree. For each node check (recursively) if the node and both its children satisfy the
 * Children Sum Property, if so then return true else return false.
 * 
 * Time Complexity: O(n), we are doing a complete traversal of the tree.
 * 
 * @author surya
 *
 */
public class CheckForChildrenSumPropertyBT {

	public boolean isSumProperty(TreeNode node) {
		int rightVal = 0;// for left child
		int leftVal = 0;// for right child

		// if node is null or it is a child node then return true
		if (node == null || (node.left == null && node.right == null))
			return true;
		else {
			if (node.left != null)
				leftVal = node.left.data;
			if (node.right != null)
				rightVal = node.right.data;
			if (node.data == leftVal + rightVal && isSumProperty(node.left) && isSumProperty(node.right))
				return true;
			else
				return false;
		}
	}

}
