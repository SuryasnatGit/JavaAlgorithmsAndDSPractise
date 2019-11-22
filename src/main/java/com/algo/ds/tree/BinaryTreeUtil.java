package com.algo.ds.tree;

import java.util.LinkedList;

import com.algo.ds.stack.StackAsSingleLinkedList;

public class BinaryTreeUtil<K, V> {

	public TreeNode<K, V> invertBinaryTree_recursive(TreeNode<K, V> node) {
		if (node != null)
			recursive(node);

		return node;
	}

	private void recursive(TreeNode<K, V> root) {
		TreeNode<K, V> temp = root.left;
		root.left = root.right;
		root.right = temp;

		if (root.left != null)
			recursive(root.left);
		if (root.right != null)
			recursive(root.right);
	}

	public TreeNode<K, V> invertBinaryTree_iterative(TreeNode<K, V> root) {
		LinkedList<TreeNode<K, V>> queue = new LinkedList<>();
		if (root != null)
			queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode<K, V> node = queue.poll();
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);

			TreeNode<K, V> temp = node.left;
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
	public int kthSmallestElementinBst(TreeNode<K, V> root, int k) {
		StackAsSingleLinkedList stack = new StackAsSingleLinkedList();
		TreeNode<K, V> p = root;
		int result = 0;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				TreeNode<K, V> temp = (TreeNode<K, V>) stack.pop();
				k--;
				if (k == 0) {
					result = temp.value;
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
	public boolean isBSTValid(TreeNode<K, V> root) {
		return recursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean recursive(TreeNode<K, V> root, int minValue, int maxValue) {
		// check null
		if (root == null)
			return true;

		// boundry conditions
		if (root.value < minValue || root.value > maxValue)
			return false;

		// check the tree recursively
		return recursive(root.left, minValue, root.value - 1) && recursive(root.right, root.value + 1, maxValue);
	}

	public static void main(String[] args) {
		BinaryTreeUtil<Integer, Integer> util = new BinaryTreeUtil<>();
		TreeNode<Integer, Integer> root = new TreeNode<>(1, 1);
		root.left = new TreeNode<>(2, 2);
		root.right = new TreeNode<>(3, 3);
		root.left.left = new TreeNode<>(4, 4);
		root.left.right = new TreeNode<>(5, 5);
		System.out.println(util.kthSmallestElementinBst(root, 4));
	}
}
