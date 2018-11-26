package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given
 * sum.
 *
 * Time complexity O(n)
 *
 * 
 * 
 * https://leetcode.com/problems/path-sum-ii/
 */
public class PathSum {
	public List<List<Integer>> pathSum(Node root, int sum) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> current = new ArrayList<>();
		pathSumUtil(root, sum, result, current);
		return result;
	}

	private void pathSumUtil(Node root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			if (root.data == sum) {
				currentPath.add(root.data);
				result.add(new ArrayList<>(currentPath));
				currentPath.remove(currentPath.size() - 1);
			}
			return;
		}
		currentPath.add(root.data);
		pathSumUtil(root.left, sum - root.data, result, currentPath); // recurse for left tree
		pathSumUtil(root.right, sum - root.data, result, currentPath); // recurse for right tree
		currentPath.remove(currentPath.size() - 1);
	}

	public static List<List<Integer>> pathSum_DFS(TreeNode root, int sum) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null)
			return ans;
		Stack<TreeNode> stack = new Stack<>();
		Stack<Integer> stackInt = new Stack<>();
		Stack<List<Integer>> stackList = new Stack<>();
		stack.add(root);
		stackInt.add(root.value);
		List<Integer> temp = new ArrayList<>();
		temp.add(root.value);
		stackList.add(temp);
		while (!stack.empty()) {
			TreeNode node = stack.pop();
			int new_sum = stackInt.pop();
			List<Integer> list = stackList.pop();
			List list2 = new ArrayList(list);
			if (node.left == null && node.right == null && new_sum == sum) {
				ans.add(list);
			}
			if (node.right != null) {
				stack.push(node.right);
				stackInt.push(new_sum + node.right.value);
				list2.add(node.right.value);
				stackList.push(list2);
			}
			if (node.left != null) {
				stack.push(node.left);
				stackInt.add(new_sum + node.left.value);
				list.add(node.left.value);
				stackList.push(list);
			}

		}
		return ans;
	}

	public static List<List<Integer>> pathSum_BFS(TreeNode root, int sum) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null)
			return ans;
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<Integer> queueInt = new LinkedList<>();
		Queue<List<Integer>> queueList = new LinkedList<>();
		queue.add(root);
		queueInt.add(root.value);
		List<Integer> temp = new ArrayList<>();
		temp.add(root.value);
		queueList.add(temp);
		while (!queue.isEmpty()) {
			TreeNode node = queue.remove();
			int new_sum = queueInt.remove();
			List<Integer> list = queueList.remove();
			List list2 = new ArrayList(list);
			if (node.left == null && node.right == null && new_sum == sum) {
				ans.add(list);
			}
			if (node.left != null) {
				queue.add(node.left);
				queueInt.add(new_sum + node.left.value);
				list.add(node.left.value);
				queueList.add(list);
			}
			if (node.right != null) {
				queue.add(node.right);
				queueInt.add(new_sum + node.right.value);
				list2.add(node.right.value);
				queueList.add(list2);
			}
		}
		return ans;
	}

	/**
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
	 * all the values along the path equals the given sum.
	 * 
	 * https://leetcode.com/problems/path-sum/
	 * 
	 * Complexity Analysis
	 * 
	 * Time complexity : we visit each node exactly once, thus the time complexity is O(N), where N is
	 * the number of nodes.
	 * 
	 * Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only
	 * one child node, the recursion call would occur N times (the height of the tree), therefore the
	 * storage to keep the call stack would be O(N). But in the best case (the tree is completely
	 * balanced), the height of the tree would be log(N). Therefore, the space complexity in this case
	 * would be O(log(N)).
	 */

	/**
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum_recursive(Node root, int sum) {
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
	 * We could also convert the above recursion into iteration, with the help of stack. DFS would be
	 * better than BFS here since it works faster except the worst case. In the worst case the path
	 * root->leaf with the given sum is the last considered one and in this case DFS results in the same
	 * productivity as BFS.
	 * 
	 * The idea is to visit each node with the DFS strategy, while updating the remaining sum to
	 * cumulate at each visit.
	 * 
	 * So we start from a stack which contains the root node and the corresponding remaining sum which
	 * is sum - root.val. Then we proceed to the iterations: pop the current node out of the stack and
	 * return True if the remaining sum is 0 and we're on the leaf node. If the remaining sum is not
	 * zero or we're not on the leaf yet then we push the child nodes and corresponding remaining sums
	 * into stack.
	 * 
	 * Complexity Analysis
	 * 
	 * Time complexity : the same as the recursion approach O(N).
	 * 
	 * Space complexity : O(N) since in the worst case, when the tree is completely unbalanced, e.g.
	 * each node has only one child node, we would keep all NN nodes in the stack. But in the best case
	 * (the tree is balanced), the height of the tree would be log(N). Therefore, the space complexity
	 * in this case would be O(log(N)).
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum_iterative(Node root, int sum) {
		// base case
		if (root == null)
			return false;

		Stack<Node> nodeStack = new Stack<>(); // stack for tracking nodes
		nodeStack.push(root);
		Stack<Integer> sumStack = new Stack<>(); // stack for tracking the sum
		sumStack.push(sum - root.data);

		while (!nodeStack.isEmpty()) {
			Node n = nodeStack.pop();
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

	public static void main(String[] args) {
		PathSum ps = new PathSum();
		Node root = Node.newNode(5);
		root.left = Node.newNode(4);
		root.right = Node.newNode(8);
		System.out.println(ps.hasPathSum_iterative(root, 9));
		System.out.println(ps.hasPathSum_iterative(root, 13));
		System.out.println(ps.hasPathSum_iterative(root, 10));
	}
}
