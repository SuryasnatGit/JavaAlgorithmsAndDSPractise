package com.algo.ds.tree;

import java.util.HashMap;
import java.util.Map;

import com.algo.common.TreeNode;

/**
 * Given a Binary Tree, find the vertical sum of the nodes that are in the same vertical line. Print all sums through different vertical lines.

Examples:

      1
    /   \
  2      3
 / \    / \
4   5  6   7

The tree has 5 vertical lines

Vertical-Line-1 has only one node 4 => vertical sum is 4


Vertical-Line-2: has only one node 2=> vertical sum is 2

Vertical-Line-3: has three nodes: 1,5,6 => vertical sum is 1+5+6 = 12

Vertical-Line-4: has only one node 3 => vertical sum is 3

Vertical-Line-5: has only one node 7 => vertical sum is 7

So expected output is 4, 2, 12, 3 and 7

 *Category : Medium
 */
public class VerticalSumBinaryTree {

	/**
	 * Solution 1 : We can do inorder traversal of the given Binary Tree. While traversing the tree, we can recursively
	 * calculate HDs. We initially pass the horizontal distance as 0 for root. For left subtree, we pass the Horizontal
	 * Distance as Horizontal distance of root minus 1. For right subtree, we pass the Horizontal Distance as Horizontal
	 * Distance of root plus 1.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @param root
	 */
	public void verticalSum(TreeNode root) {
		if (root == null)
			return;
		Map<Integer, Integer> map = new HashMap<>();
		verticalSumUtil(root, map, 0);
		if (map != null)
			System.out.println(map);
	}

	/**
	 * Traverses the tree in Inoorder form and builds a hashMap hM that contains the vertical sum
	 * 
	 * @param root
	 * @param map
	 * @param hd
	 */
	private void verticalSumUtil(TreeNode root, Map<Integer, Integer> map, int hd) {
		if (root == null)
			return;
		// do inorder traversal
		verticalSumUtil(root.left, map, hd - 1);
		int prevSum = (map.get(hd) == null) ? 0 : map.get(hd);
		map.put(hd, prevSum + root.data);
		verticalSumUtil(root.right, map, hd + 1);
	}

	// Driver code
	public static void main(String[] args) {

		VerticalSumBinaryTree sbt = new VerticalSumBinaryTree();

		// Let us construct the tree shown above
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		System.out.println("Vertical Sums are");
		sbt.verticalSum(root);
	}
}
