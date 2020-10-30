package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Category : Hard
 */
public class BinaryTreePathSumProblems {

	/**
	 * Problem 1 - https://leetcode.com/problems/path-sum-ii/
	 * 
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 */

	/**
	 * Approach 1 - Recursive method
	 * 
	 */
	public List<List<Integer>> findAllRootToLeafPathsWithGivenSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> list = new ArrayList<Integer>();

		if (root == null) {
			return res;
		}

		helper(res, list, root, sum);

		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, TreeNode node, int sum) {
		if (node.left == null && node.right == null) {
			if (sum == node.data) {
				List<Integer> copy = new ArrayList<Integer>(list);
				copy.add(node.data);
				res.add(copy);
			}
			return;
		}

		list.add(node.data);
		if (node.left != null) {
			helper(res, list, node.left, sum - node.data);
		}

		if (node.right != null) {
			helper(res, list, node.right, sum - node.data);
		}

		list.remove(list.size() - 1);
	}

	/**
	 * Approach 2 - DFS method
	 */
	public List<List<Integer>> findAllRootToLeafPathsWithGivenSumDFS(TreeNode root, int sum) {
		List<List<Integer>> ans = new ArrayList<>();

		if (root == null)
			return ans;

		Stack<TreeNode> stack = new Stack<>();
		Stack<Integer> stackInt = new Stack<>();
		Stack<List<Integer>> stackList = new Stack<>();
		stack.add(root);
		stackInt.add(root.data);
		List<Integer> temp = new ArrayList<>();
		temp.add(root.data);
		stackList.add(temp);

		while (!stack.empty()) {
			TreeNode node = stack.pop();
			int new_sum = stackInt.pop();
			List<Integer> list = stackList.pop();
			List<Integer> list2 = new ArrayList<>(list);

			if (node.left == null && node.right == null && new_sum == sum) {
				ans.add(list);
			}

			if (node.right != null) {
				stack.push(node.right);
				stackInt.push(new_sum + node.right.data);
				list2.add(node.right.data);
				stackList.push(list2);
			}
			if (node.left != null) {
				stack.push(node.left);
				stackInt.add(new_sum + node.left.data);
				list.add(node.left.data);
				stackList.push(list);
			}

		}
		return ans;
	}

	/**
	 * Problem 2 - Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
	 * 
	 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
	 * 
	 * Find the total sum of all root-to-leaf numbers.
	 * 
	 * Note: A leaf is a node with no children.
	 * 
	 * Example:
	 * 
	 * Input: [1,2,3] 1 / \ 2 3 Output: 25
	 * 
	 * Explanation:
	 * 
	 * The root-to-leaf path 1->2 represents the number 12.
	 * 
	 * The root-to-leaf path 1->3 represents the number 13.
	 * 
	 * Therefore, sum = 12 + 13 = 25.
	 */

	// Approach 1 - Iterative using stack. Time complexity:O(N) since one has to visit each node. Space complexity: up
	// to O(H) to keep the stack, where H is a tree height.
	public int sumNumbersIterative(TreeNode root) {
		int rootToLeafSum = 0, currentVal = 0;

		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(root, 0));

		while (!stack.isEmpty()) {
			Pair pair = stack.pop();
			root = pair.node;
			currentVal = pair.value;

			if (root != null) {
				currentVal = currentVal * 10 + root.data;

				// if its a leaf node
				if (root.left == null && root.right == null) {
					rootToLeafSum += currentVal;
				} else {
					// push to stack
					stack.push(new Pair(root.right, currentVal));
					stack.push(new Pair(root.left, currentVal));
				}
			}
		}

		return rootToLeafSum;
	}

	// Approach 2 - Using Morris traversal. Time complexity: O(N).Space complexity: O(1).
	public int sumNumbersMorris(TreeNode root) {
		int rootToLeaf = 0, currNumber = 0;
		int steps;
		TreeNode predecessor;

		while (root != null) {
			// If there is a left child,
			// then compute the predecessor.
			// If there is no link predecessor.right = root --> set it.
			// If there is a link predecessor.right = root --> break it.
			if (root.left != null) {
				// Predecessor node is one step to the left
				// and then to the right till you can.
				predecessor = root.left;
				steps = 1;
				while (predecessor.right != null && predecessor.right != root) {
					predecessor = predecessor.right;
					++steps;
				}

				// Set link predecessor.right = root
				// and go to explore the left subtree
				if (predecessor.right == null) {
					currNumber = currNumber * 10 + root.data;
					predecessor.right = root;
					root = root.left;
				}
				// Break the link predecessor.right = root
				// Once the link is broken,
				// it's time to change subtree and go to the right
				else {
					// If you're on the leaf, update the sum
					if (predecessor.left == null) {
						rootToLeaf += currNumber;
					}
					// This part of tree is explored, backtrack
					for (int i = 0; i < steps; ++i) {
						currNumber /= 10;
					}
					predecessor.right = null;
					root = root.right;
				}
			}

			// If there is no left child
			// then just go right.
			else {
				currNumber = currNumber * 10 + root.data;
				// if you're on the leaf, update the sum
				if (root.right == null) {
					rootToLeaf += currNumber;
				}
				root = root.right;
			}
		}
		return rootToLeaf;
	}

	/**
	 * Problem 3 : Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all
	 * the values along the path equals the given sum.
	 * 
	 * https://leetcode.com/problems/path-sum/
	 * 
	 * Complexity Analysis
	 * 
	 * Time complexity : we visit each node exactly once, thus the time complexity is O(N), where N is the number of
	 * nodes.
	 * 
	 * Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only one child node,
	 * the recursion call would occur N times (the height of the tree), therefore the storage to keep the call stack
	 * would be O(N). But in the best case (the tree is completely balanced), the height of the tree would be log(N).
	 * Therefore, the space complexity in this case would be O(log(N)).
	 */

	/**
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum_recursive(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null) {
			return root.data == sum;
		}
		return hasPathSum_recursive(root.left, sum - root.data) || hasPathSum_recursive(root.right, sum - root.data);
	}

	/**
	 * Algorithm
	 * 
	 * We could also convert the above recursion into iteration, with the help of stack. DFS would be better than BFS
	 * here since it works faster except the worst case. In the worst case the path root->leaf with the given sum is the
	 * last considered one and in this case DFS results in the same productivity as BFS.
	 * 
	 * The idea is to visit each node with the DFS strategy, while updating the remaining sum to cumulate at each visit.
	 * 
	 * So we start from a stack which contains the root node and the corresponding remaining sum which is sum -
	 * root.val. Then we proceed to the iterations: pop the current node out of the stack and return True if the
	 * remaining sum is 0 and we're on the leaf node. If the remaining sum is not zero or we're not on the leaf yet then
	 * we push the child nodes and corresponding remaining sums into stack.
	 * 
	 * Complexity Analysis
	 * 
	 * Time complexity : the same as the recursion approach O(N).
	 * 
	 * Space complexity : O(N) since in the worst case, when the tree is completely unbalanced, e.g. each node has only
	 * one child node, we would keep all NN nodes in the stack. But in the best case (the tree is balanced), the height
	 * of the tree would be log(N). Therefore, the space complexity in this case would be O(log(N)).
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum_iterative(TreeNode root, int sum) {
		// base case
		if (root == null)
			return false;

		Stack<TreeNode> nodeStack = new Stack<>(); // stack for tracking nodes
		nodeStack.push(root);

		Stack<Integer> sumStack = new Stack<>(); // stack for tracking the sum
		sumStack.push(sum - root.data);

		while (!nodeStack.isEmpty()) {
			TreeNode n = nodeStack.pop();
			int currSum = sumStack.pop();

			if (n.left == null && n.right == null && (currSum == 0)) // if child node reached and sum is found
				return true;

			if (n.left != null) {
				nodeStack.push(n.left);
				sumStack.push(currSum - n.left.data);
			}
			if (n.right != null) {
				nodeStack.push(n.right);
				sumStack.push(currSum - n.right.data);
			}
		}
		return false;
	}

	/*
	 * Problem 4 - https://www.techiedelight.com/print-all-paths-from-root-to-leaf-nodes-binary-tree/
	 */

	// T - O(n) S - O(h)
	public void printAllPathsFromRootToLeaf(TreeNode root) {
		Queue<Integer> queue = new LinkedList<>();
		printPathsRecursive(root, queue);
	}

	private void printPathsRecursive(TreeNode root, Queue<Integer> queue) {
		if (root == null)
			return;

		// include current node to path
		queue.add(root.data);

		if (isLeafNode(root))
			System.out.println(queue);

		// recur for left and right subtree
		printPathsRecursive(root.left, queue);
		printPathsRecursive(root.right, queue);

		// remove current node after left and right subtree are done
		queue.remove(root.data);
	}

	private boolean isLeafNode(TreeNode root) {
		return root.left == null && root.right == null;
	}

	public void printAllPathsFromRootToLeafIterative(TreeNode root) {
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(root, ""));

		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			TreeNode node = pair.node;
			String path = pair.path;
			path += " " + node.data;

			// if leaf node
			if (node.left == null && node.right == null)
				System.out.println(path);

			if (node.right != null)
				queue.add(new Pair(node.right, path));

			if (node.left != null)
				queue.add(new Pair(node.left, path));
		}
	}

	public static void main(String[] args) {
		BinaryTreePathSumProblems ps = new BinaryTreePathSumProblems();
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		System.out.println(ps.hasPathSum_iterative(root, 9));
		System.out.println(ps.hasPathSum_iterative(root, 13));
		System.out.println(ps.hasPathSum_iterative(root, 10));
	}
}

class Pair {

	TreeNode node;
	String path;
	int value;

	public Pair(TreeNode root, String path) {
		this.node = root;
		this.path = path;
	}

	public Pair(TreeNode root, int value) {
		this.node = root;
		this.value = value;
	}

}