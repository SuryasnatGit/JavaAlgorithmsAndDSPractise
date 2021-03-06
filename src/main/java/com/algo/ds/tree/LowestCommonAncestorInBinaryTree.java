package com.algo.ds.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.algo.common.TreeNode;

/**
 * Find lowest common ancestor in binary tree. Let T be a rooted tree. The lowest common ancestor between two nodes n1
 * and n2 is defined as the lowest node in T that has both n1 and n2 as descendants (where we allow a node to be a
 * descendant of itself). The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that is located farthest from
 * the root.
 * 
 * 
 * Time complexity O(n) Space complexity O(h)
 */
public class LowestCommonAncestorInBinaryTree {

	/**
	 * (Using Single Traversal) The idea is to traverse the tree starting from root. If any of the given keys (n1 and
	 * n2) matches with root, then root is LCA (assuming that both keys are present). If root doesn't match with any of
	 * the keys, we recur for left and right subtree. The node which has one key present in its left subtree and the
	 * other key present in right subtree is the LCA. If both keys lie in left subtree, then left subtree has LCA also,
	 * otherwise LCA lies in right subtree.
	 * 
	 * Time Complexity: Time complexity of the above solution is O(n) as the method does a simple tree traversal in
	 * bottom up fashion.
	 */
	public TreeNode lca(TreeNode root, int n1, int n2) {
		if (root == null) {
			return null;
		}
		if (root.data == n1 || root.data == n2) {
			return root;
		}

		TreeNode left = lca(root.left, n1, n2);
		TreeNode right = lca(root.right, n1, n2);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

	/**
	 * Use a map to keep all ancestors of p and q, where key is a node in the ancestor chain and value is parent of the
	 * node in key. Given that p and q both will exist in the tree, the LCA must be one of the ancestors of p. Then
	 * traverse the ancestor chain of q, the first node existing in map of p's ancestors is the LCA.
	 * 
	 * @param root
	 * @param n1
	 * @param n2
	 * @return
	 */
	public TreeNode lca_iterative(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null)
			return root;
		Map<TreeNode, TreeNode> parentMap = new HashMap<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		// populate stack and map
		stack.push(root);
		parentMap.put(root, null);
		while (!stack.isEmpty() && (!parentMap.containsKey(n1) || !parentMap.containsKey(n2))) {
			TreeNode node = stack.pop();
			if (node.left != null) {
				stack.push(node.left);
				parentMap.put(node.left, node);
			}
			if (node.right != null) {
				stack.push(node.right);
				parentMap.put(node.right, node);
			}
		}

		Set<TreeNode> ancestors = new HashSet<>();
		for (TreeNode p = n1; p != null; p = parentMap.get(p))
			ancestors.add(p);

		for (TreeNode q = n2; q != null; q = parentMap.get(q)) {
			if (ancestors.contains(q))
				return q;
		}
		return null;
	}

	// What if the tree node has parent pointer
	public TreeNode lowestCommonAncestorWithParentUsingHashMap(TreeNode root, TreeNode p, TreeNode q) {
		Set<TreeNode> visited = new HashSet<TreeNode>();

		while (p != null) {
			visited.add(p);
			p = p.parent;
		}

		while (q != null) {
			if (visited.contains(q)) {
				return q;
			}

			q = q.parent;
		}

		return null;
	}

	public static void main(String[] args) {
		LowestCommonAncestorInBinaryTree low = new LowestCommonAncestorInBinaryTree();
		TreeNode n1 = new TreeNode(1);
		n1.left = new TreeNode(2);
		n1.right = new TreeNode(3);
		n1.left.left = new TreeNode(4);
		n1.left.right = new TreeNode(5);
		n1.right.left = new TreeNode(7);
		n1.right.right = new TreeNode(8);
		TreeNode res = low.lca_iterative(n1, new TreeNode(8), new TreeNode(7)); // this solution does not work for this
																				// case
		// where one number is not a
		// node
		System.out.println(res == null ? null : res.data);
	}
}
