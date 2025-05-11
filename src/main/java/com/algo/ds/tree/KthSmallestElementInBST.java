package com.algo.ds.tree;

import com.algo.common.TreeNode;
import com.algo.ds.stack.LinkedListStack;

import java.util.Stack;

public class KthSmallestElementInBST {

	public int kthSmallestElementinBst(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = root;
		int result = 0;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				TreeNode temp = (TreeNode) stack.pop();
				k--;
				if (k == 0) {
					result = temp.data;
					break;
				}
				p = temp.right;
			}
		}
		return result;
	}
}
