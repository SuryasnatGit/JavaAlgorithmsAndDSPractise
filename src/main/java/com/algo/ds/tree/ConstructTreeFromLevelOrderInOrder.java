package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
 * 
 * Given inorder and level-order traversals of a Binary Tree, construct the Binary Tree. Following is an example to
 * illustrate the problem.
 * 
 * Input: Two arrays that represent Inorder and level order traversals of a Binary Tree in[] = {4, 8, 10, 12, 14, 20,
 * 22}; level[] = {20, 8, 22, 4, 12, 10, 14};
 * 
 * Output: Construct the tree represented by the two arrays. For the above two arrays, the constructed tree is shown in
 * the diagram on right side
 * 
 * An upper bound on time complexity of above method is O(n3). In the main recursive function, extractNodes() is called
 * which takes O(n2) time.
 * 
 * 
 * 
 * Test cases:
 * 
 * same length of inorder and level order array all elements should be same in them what to do if repetition happens.
 * This logic only works for non repeated values if inorder and levelorder were represented by string how would you
 * handle multi digits e.g 1234 we don't know if it is 12 34 or 1 2 3 4 or what. Maybe use brackets (12)(3)(4) to
 * differentiate between them.
 */
public class ConstructTreeFromLevelOrderInOrder {

	public TreeNode constructTree(int inOrder[], int levelOrder[]) {
		return constructTree(inOrder, levelOrder, 0, inOrder.length - 1);
	}

	private TreeNode constructTree(int inOrder[], int levelOrder[], int low, int high) {
		if (low > high) {
			return null;
		}
		int lowElement = levelOrder[0];
		TreeNode n = new TreeNode(lowElement);
		int index = search(inOrder, lowElement, low, high);
		int left[] = extractArray(inOrder, levelOrder, low, index - 1);
		int right[] = extractArray(inOrder, levelOrder, index + 1, high);
		n.left = constructTree(inOrder, left, low, index - 1);
		n.right = constructTree(inOrder, right, index + 1, high);
		return n;
	}

	private int[] extractArray(int inOrder[], int levelOrder[], int low, int high) {
		int result[] = new int[high - low + 1];
		int p = 0;
		for (int i = 1; i < levelOrder.length; i++) {
			int index = search(inOrder, levelOrder[i], low, high);
			if (index != -1) {
				result[p++] = inOrder[index];
			}
		}
		return result;
	}

	private int search(int input[], int key, int low, int high) {
		if (low > high) {
			return -1;
		}
		int middle = (low + high) / 2;
		if (input[middle] == key) {
			return middle;
		} else if (input[middle] > key) {
			return search(input, key, low, middle - 1);
		} else {
			return search(input, key, middle + 1, high);
		}
	}

	public static void main(String args[]) {
		int inOrder[] = { 4, 8, 10, 12, 14, 20, 22 };
		int levelOrder[] = { 20, 8, 22, 4, 12, 10, 14 };
		ConstructTreeFromLevelOrderInOrder ctf = new ConstructTreeFromLevelOrderInOrder();
		TreeNode root = ctf.constructTree(inOrder, levelOrder);
		LevelOrderTraversal lot = new LevelOrderTraversal();
		TreeTraversals tt = new TreeTraversals();
		tt.inOrder(root);
		lot.levelOrder(root);
	}

}
