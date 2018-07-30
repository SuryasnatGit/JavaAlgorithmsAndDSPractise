package com.algo.ds.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Find lowest common ancestor in binary tree. Let T be a rooted tree. The lowest common ancestor
 * between two nodes n1 and n2 is defined as the lowest node in T that has both n1 and n2 as
 * descendants (where we allow a node to be a descendant of itself). The LCA of n1 and n2 in T is
 * the shared ancestor of n1 and n2 that is located farthest from the root.
 * 
 * 
 * Time complexity O(n) Space complexity O(h)
 */
public class LowestCommonAncestorInBinaryTree {

	/**
	 * (Using Single Traversal) The idea is to traverse the tree starting from root. If any of the given
	 * keys (n1 and n2) matches with root, then root is LCA (assuming that both keys are present). If
	 * root doesn’t match with any of the keys, we recur for left and right subtree. The node which has
	 * one key present in its left subtree and the other key present in right subtree is the LCA. If
	 * both keys lie in left subtree, then left subtree has LCA also, otherwise LCA lies in right
	 * subtree.
	 * 
	 * Time Complexity: Time complexity of the above solution is O(n) as the method does a simple tree
	 * traversal in bottom up fashion.
	 */
	public Node lca(Node root, int n1, int n2) {
        if(root == null){
            return null;
        }
		if (root.data == n1 || root.data == n2) {
            return root;
        }
        
        Node left = lca(root.left, n1, n2);
        Node right = lca(root.right, n1, n2);

        if(left != null && right != null){
            return root;
        }
        return left != null ? left : right;
    }

	// Using corrective approach
	private boolean b1;
	private boolean b2;

	public Node lca_correct(Node root, int n1, int n2) {
		if (root == null)
			return null;
		Node node = lca_util(root, n1, n2);
		// return true only if both n1 and n2 are present in the tree
		if (b1 && b2 || b1 && find(node, n2) || b2 && find(node, n1))
			return node;
		// else return null
		return null;
	}

	/**
	 * Returns true if key k is present in tree rooted with root
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	private boolean find(Node root, int k) {
		if (root == null)
			return false;
		// return true if k is present in root or left sub-tree or right sub-tree
		return (root.data == k || find(root.left, k) || find(root.right, k));
	}

	private Node lca_util(Node root, int n1, int n2) {
		if (root == null) {
			return null;
		}
		// If either n1 or n2 matches with root's key, report the presence by setting b1 or b2 as true and
		// return root (Note that if a key is ancestor of other, then the ancestor key becomes LCA)
		if (root.data == n1) {
			b1 = true;
			return root;
		}
		if (root.data == n2) {
			b2 = true;
			return root;
		}

		Node left = lca(root.left, n1, n2);
		Node right = lca(root.right, n1, n2);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

	/**
	 * Use a map to keep all ancestors of p and q, where key is a node in the ancestor chain and value
	 * is parent of the node in key. Given that p and q both will exist in the tree, the LCA must be one
	 * of the ancestors of p. Then traverse the ancestor chain of q, the first node existing in map of
	 * p's ancestors is the LCA.
	 * 
	 * @param root
	 * @param n1
	 * @param n2
	 * @return
	 */
	public Node lca_iterative(Node root, Node n1, Node n2) {
		if (root == null)
			return root;
		Map<Node, Node> parentMap = new HashMap<>();
		Deque<Node> stack = new ArrayDeque<>();
		// populate stack and map
		stack.push(root);
		parentMap.put(root, null);
		while (!stack.isEmpty() && (!parentMap.containsKey(n1) || !parentMap.containsKey(n2))) {
			Node node = stack.pop();
			if (node.left != null) {
				stack.push(node.left);
				parentMap.put(node.left, node);
			}
			if (node.right != null) {
				stack.push(node.right);
				parentMap.put(node.right, node);
			}
		}

		Set<Node> ancestors = new HashSet<>();
		for (Node p = n1; p != null; p = parentMap.get(p))
			ancestors.add(p);

		for (Node q = n2; q != null; q = parentMap.get(q)) {
			if (ancestors.contains(q))
				return q;
		}
		return null;
	}

	public static void main(String[] args) {
		LowestCommonAncestorInBinaryTree low = new LowestCommonAncestorInBinaryTree();
		Node n1 = Node.newNode(1);
		n1.left = Node.newNode(2);
		n1.right = Node.newNode(3);
		n1.left.left = Node.newNode(4);
		n1.left.right = Node.newNode(5);
		n1.right.left = Node.newNode(7);
		n1.right.right = Node.newNode(8);
		Node res = low.lca_iterative(n1, Node.newNode(8), Node.newNode(7)); // this solution does not work for this case
																			// where one number is not a
												// node
		System.out.println(res == null ? null : res.data);
	}
}
