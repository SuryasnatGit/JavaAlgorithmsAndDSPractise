package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;
import com.algo.ds.stack.LinkedListStack;

public class BinaryTreeUtil<K, V> {

	public TreeNode invertBinaryTree_recursive(TreeNode node) {
		if (node != null)
			recursive(node);

		return node;
	}

	private void recursive(TreeNode root) {
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		if (root.left != null)
			recursive(root.left);
		if (root.right != null)
			recursive(root.right);
	}

	public TreeNode invertBinaryTree_iterative(TreeNode root) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		if (root != null)
			queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);

			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
		}

		return root;
	}

	/**
	 * Method 1: Using Inorder Traversal.
	 * 
	 * Inorder traversal of BST retrieves elements of tree in the sorted order. The inorder traversal uses stack to
	 * store to be explored nodes of tree (threaded tree avoids stack and recursion for traversal, see this post). The
	 * idea is to keep track of popped elements which participate in the order statics. Hypothetical algorithm is
	 * provided below, Problem with this approach - this takes extra space.<br/>
	 * Time complexity: O(n) where n is total nodes in tree
	 * 
	 * kth largest element will be (n-k)th smallest element
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallestElementinBst(TreeNode root, int k) {
		LinkedListStack<TreeNode> stack = new LinkedListStack<>();
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

	/**
	 * Time Complexity: O(n) Auxiliary Space : O(1) if Function Call Stack size is not considered, otherwise O(n)
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBSTValid(TreeNode root) {
		return recursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean recursive(TreeNode root, int minValue, int maxValue) {
		// check null
		if (root == null)
			return true;

		// boundry conditions
		if (root.data < minValue || root.data > maxValue)
			return false;

		// check the tree recursively
		return recursive(root.left, minValue, root.data - 1) && recursive(root.right, root.data + 1, maxValue);
	}

	/**
	 * prints 1st element in each level
	 */
	public void printLeftView(TreeNode root) {
		if (root == null)
			return;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		TreeNode curr;
		while (!queue.isEmpty()) {
			int i = 0;
			int size = queue.size();
			while (i++ < size) {
				curr = queue.poll();
				// if this is the first node in current level then print it. for last node in curr level check against
				// size
				if (i == 1)
					System.out.println(curr.data);

				if (curr.left != null) {
					queue.add(curr.left);
				}
				if (curr.right != null) {
					queue.add(curr.left);
				}
			}
		}
	}

	public static void main(String[] args) {
		BinaryTreeUtil<Integer, Integer> util = new BinaryTreeUtil<>();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		// System.out.println(util.kthSmallestElementinBst(root, 4));

		TreeNode n = new TreeNode(1);
		n.left = new TreeNode(2);
		n.right = new TreeNode(3);
		n.left.left = new TreeNode(4);
		n.right.right = new TreeNode(5);
		util.printLeftView(n);
	}
}
