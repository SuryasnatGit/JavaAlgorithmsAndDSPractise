package com.leetcode;

import java.util.List;
import java.util.Stack;

import com.leetcode.ShortestSubarrayWithHighestFrequencyWords.TreeNode;

public class FindNodeInCopy {

	/**
	 * Give an n-nery tree and a copy of it, the parameter passed in is a certain node on the original tree, let you
	 * return the node corresponding to the copy tree:
	 *
	 * This question, I thought it was a similar question to clone graph, what they said was not very clear, I thought
	 * it was returning a 1-1 mapped node; later I clarified that the value in the tree is not unique, and the value
	 * cannot be simply compared. Perform BFS at the same time, Level order traversal or anything else will do, as long
	 * as you do it at the same time
	 *
	 * I started with the same idea to go to traverse tree, but the interviewer suggested that there is a simpler way to
	 * record the path, and then you can find it by returning to the original path. The final code implements this
	 * suggestion
	 */
	TreeNode findNodeInCopy(TreeNode root1, TreeNode root2, TreeNode toFind) {
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		stack1.push(root1);

		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		stack2.push(root1);

		while (!stack1.isEmpty()) {
			TreeNode node1 = stack1.pop();
			TreeNode node2 = stack2.pop();

			if (node1 == toFind) {
				return node2;
			}

			List<TreeNode> list1 = node1.children;
			for (int i = list1.size() - 1; i >= 0; i--) {
				stack1.push(list1.get(i));
			}
			List<TreeNode> list2 = node2.children;
			for (int i = list2.size() - 1; i >= 0; i--) {
				stack2.push(list2.get(i));
			}
		}

		return null;
	}
}
