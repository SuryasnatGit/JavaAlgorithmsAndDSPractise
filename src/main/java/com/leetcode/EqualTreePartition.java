
package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.algo.ds.tree.TreeNode;

/**
 * Given a binary tree with n nodes, your task is to check if it's possible to
 * partition the tree to two trees which have the equal sum of values after
 * removing exactly one edge on the original tree.
 * 
 * Example 1: Input: 5 / \ 10 10 / \ 2 3
 * 
 * Output: True Explanation: 5 / 10
 * 
 * Sum: 15
 * 
 * 10 / \ 2 3
 * 
 * Sum: 15 Example 2: Input: 1 / \ 2 10 / \ 2 20
 * 
 * Output: False Explanation: You can't split the tree into two trees with equal
 * sum after removing exactly one edge on the tree.
 * 
 * Note: The range of tree node value is in the range of [-100000, 100000]. 1 <=
 * n <= 10000
 * 
 * 
 * @author ctsuser1
 */
public class EqualTreePartition {

	/**
	 * Method 1 - Using DFS. After removing some edge from parent to child, (where
	 * the child cannot be the original root) the subtree rooted at child must be
	 * half the sum of the entire tree. Let's record the sum of every subtree. We
	 * can do this recursively using depth-first search. After, we should check that
	 * half the sum of the entire tree occurs somewhere in our recording (and not
	 * from the total of the entire tree.)
	 * 
	 * Complexity Analysis
	 * 
	 * Time Complexity: O(N) where N is the number of nodes in the input tree. We
	 * traverse every node.
	 * 
	 * Space Complexity: O(N), the size of seen and the implicit call stack in our
	 * DFS
	 */
	private Stack<Integer> stack = new Stack<>();

	public boolean hasEqualPartition(TreeNode<Integer, Integer> root) {
		int total = sum(root);
		stack.pop();
		if (total % 2 == 0) {
			for (int s : stack) {
				if (s == total / 2)
					return true;
			}
		}
		return false;
	}

	private int sum(TreeNode<Integer, Integer> root) {
		// exit criteria
		if (root == null)
			return 0;

		stack.push(sum(root.left) + sum(root.right) + root.value);
		return stack.peek();
	}

	/**
	 * Method 2 - The idea is to use a hash table to record all the different sums
	 * of each subtree in the tree. If the total sum of the tree is sum, we just
	 * need to check if the hash table constains sum/2.
	 * 
	 * @param root
	 * @return
	 */
	public boolean checkEqualTree(TreeNode<Integer, Integer> root) {
		Map<Integer, Integer> map = new HashMap<>();
		int sum = getSum(root, map);
		if (sum == 0)
			return map.getOrDefault(sum, 0) > 1;
		return sum % 2 == 0 && map.containsKey(sum / 2);
	}

	private int getSum(TreeNode<Integer, Integer> root, Map<Integer, Integer> map) {
		if (root == null)
			return 0; // base condition for recursion
		int cur = root.value + getSum(root.left, map) + getSum(root.right, map);
		map.put(cur, map.getOrDefault(cur, 0) + 1);
		return cur;
	}

	public static void main(String[] args) {
		TreeNode<Integer, Integer> a = new TreeNode<>(5, 5);
		a.left = new TreeNode<>(10, 10);
		a.right = new TreeNode<>(10, 10);
		a.right.left = new TreeNode<>(2, 2);
		a.right.right = new TreeNode<>(3, 3);
		EqualTreePartition par = new EqualTreePartition();
		System.out.println(par.checkEqualTree(a));
		System.out.println(par.hasEqualPartition(a));
	}

}
