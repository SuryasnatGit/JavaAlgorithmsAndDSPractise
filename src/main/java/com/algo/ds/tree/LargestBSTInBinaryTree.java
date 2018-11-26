package com.algo.ds.tree;

/**
 * Video link - https://youtu.be/4fiDs7CCxkc
 * 
 * Given a binary tree, find size of largest binary search subtree in this binary tree.
 * 
 * Traverse tree in post order fashion. Left and right nodes return 4 piece of information to root
 * which is: isBST, size of max BST, min and max in those subtree. If both left and right subtree
 * are BST and this node data is greater than max of left and less than min of right then it returns
 * to above level left size + right size + 1 and new min will be min of left side and new max will
 * be max of right side.
 * 
 * References:
 * 
 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 * 
 * https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTInBinaryTree {

	private IsBST isBST = new IsBST();
	private SizeOfBinaryTree sizeBT = new SizeOfBinaryTree();

	/**
	 * Method 1 - simple but inefficient.
	 * 
	 * Start from root and do an inorder traversal of the tree. For each node N, check whether the
	 * subtree rooted with N is BST or not. If BST, then return size of the subtree rooted with N. Else,
	 * recur down the left and right subtrees and return the maximum of values returned by left and
	 * right subtrees.
	 * 
	 * Time Complexity: The worst case time complexity of this method will be O(n^2). Consider a skewed
	 * tree for worst case analysis.
	 * 
	 * @param root
	 * @return
	 */
	public int largestBSTSize(Node root) {
		if (isBST.isBST(root)) {
			return sizeBT.size(root);
		} else {
			return Math.max(largestBSTSize(root.left), largestBSTSize(root.right));
		}
	}

	/**
	 * A Tree is BST if following is true for every node x.
	 * 
	 * The largest value in left subtree (of x) is smaller than value of x. The smallest value in right
	 * subtree (of x) is greater than value of x. We traverse tree in bottom up manner. For every
	 * traversed node, we return maximum and minimum values in subtree rooted with it.
	 * 
	 * Time Complexity : O(n)
	 * 
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public int largestBST(Node root) {
		MinMax m = largest(root);
		return m.size;
	}

	private MinMax largest(Node root) {
		// if root is null return min as Integer.MAX and max as Integer.MIN
		if (root == null) {
			return new MinMax();
		}

		// postorder traversal of tree. First visit left and right then
		// use information of left and right to calculate largest BST.
		MinMax leftMinMax = largest(root.left);
		MinMax rightMinMax = largest(root.right);

		MinMax m = new MinMax();

		// if either of left or right subtree says its not BST or the data
		// of this node is not greater/equal than max of left and less than min of right
		// then subtree with this node as root will not be BST.
		// Return false and max size of left and right subtree to parent
		if (leftMinMax.isBST == false || rightMinMax.isBST == false
				|| (leftMinMax.max > root.data || rightMinMax.min <= root.data)) {
			m.isBST = false;
			m.size = Math.max(leftMinMax.size, rightMinMax.size);
			return m;
		}

		// if we reach this point means subtree with this node as root is BST.
		// Set isBST as true. Then set size as size of left + size of right + 1.
		// Set min and max to be returned to parent.
		m.isBST = true;
		m.size = leftMinMax.size + rightMinMax.size + 1;

		// if root.left is null then set root.data as min else
		// take min of left side as min
		m.min = root.left != null ? leftMinMax.min : root.data;

		// if root.right is null then set root.data as max else
		// take max of right side as max.
		m.max = root.right != null ? rightMinMax.max : root.data;

		return m;
	}

	public static void main(String args[]) {
		LargestBSTInBinaryTree lbi = new LargestBSTInBinaryTree();
		ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
		// this is just to create a binary tree.
		int inorder[] = { -7, -6, -5, -4, -3, -2, 1, 2, 3, 16, 6, 10, 11, 12, 14 };
		int preorder[] = { 3, -2, -3, -4, -5, -6, -7, 1, 2, 16, 10, 6, 12, 11, 14 };
		Node root = ctf.createTree(inorder, preorder);
		int largestBSTSize = lbi.largestBST(root);
		System.out.println("Size of largest BST  is " + largestBSTSize);
		assert largestBSTSize == 8;
	}
}

/**
 * Object of this class holds information which child passes back to parent node.
 */
class MinMax {
	int min;
	int max;
	boolean isBST;
	int size;

	MinMax() {
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		isBST = true;
		size = 0;
	}
}
