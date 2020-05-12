package com.algo.ds.tree;

import com.algo.common.TreeNode;

class Count {
	int size;
}

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/
 * 
 * A SumTree is a Binary Tree where the value of a node is equal to sum of the nodes present in its left subtree and
 * right subtree. An empty tree is SumTree if sum of an empty tree can be considered as 0. A leaf node is also
 * considered as SumTree.
 * 
 */
public class SumTreeProblems {

	// https://www.techiedelight.com/inplace-convert-a-tree-sum-tree/
	public int convertToSumTree(Node root) {
		if (root == null)
			return 0;

		int left = convertToSumTree(root.left);
		int right = convertToSumTree(root.right);

		int old = root.data;

		// update root to sum of left and right sub tree
		root.data = left + right;

		return root.data + old;
	}

	/**
	 * Solution 1 - Simple. Time Complexity: O(n^2) in worst case. Worst case occurs for a skewed tree.
	 * 
	 * @param root
	 * @return
	 */
	public boolean isSumTree(Node root) {
		Count count = new Count();
		return isSumTree(root, count);
	}

	private boolean isSumTree(Node root, Count count) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			count.size = root.data;
			return true;
		}
		Count leftCount = new Count();
		Count rightCount = new Count();
		boolean isLeft = isSumTree(root.left, leftCount);
		boolean isRight = isSumTree(root.right, rightCount);
		count.size = root.data + leftCount.size + rightCount.size;
		return isLeft && isRight && root.data == (leftCount.size + rightCount.size);
	}

	/**
	 * Solution 2 - optimized
	 * 
	 * @param root
	 * @return
	 */
	public boolean isSumTree1(Node root) {
		int lc = 0, rc = 0;

		if (root == null || isLeafNode(root)) {
			return true;
		}

		if (isSumTree1(root.left) && isSumTree1(root.right)) {

			// sum of nodes in left sub tree
			if (root.left == null)
				lc = 0;
			else if (isLeafNode(root.left))
				lc = root.left.data;
			else
				lc = 2 * root.left.data;

			// sum of nodes in right sub tree
			if (root.right == null)
				rc = 0;
			else if (isLeafNode(root.right))
				rc = root.right.data;
			else
				rc = 2 * root.right.data;

			if (root.data == lc + rc)
				return true;
		}

		return false;
	}

	private boolean isLeafNode(Node root) {
		if (root.left == null && root.right == null)
			return true;

		return false;
	}

	/**
	 * /** Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.
	 * 
	 **/
	private Wrapper max = null;

	public TreeNode maxSubTree(TreeNode root) {
		util(root);
		return max.node;
	}

	private Wrapper util(TreeNode node) {
		if (node == null) {
			return new Wrapper(0, null);
		}

		int data = node.data;
		Wrapper left = util(node.left);
		Wrapper right = util(node.right);
		int sum = left.sum + right.sum + data;
		Wrapper temp = new Wrapper(sum, node);

		if (max == null || temp.sum > max.sum) {
			max = temp;
		}

		return temp;
	}

	public static void main(String args[]) {
		ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
		int inorder[] = { 4, 10, 6, 46, 11, 13, 2 };
		int preorder[] = { 46, 10, 4, 6, 13, 11, 2 };
		Node root = ctf.createTree(inorder, preorder);
		SumTreeProblems st = new SumTreeProblems();
		System.out.println(st.isSumTree(root));
		System.out.println(st.isSumTree1(root));
	}
}

class Wrapper {
	int sum;
	TreeNode node;

	public Wrapper(int sum, TreeNode node) {
		this.sum = sum;
		this.node = node;
	}
}