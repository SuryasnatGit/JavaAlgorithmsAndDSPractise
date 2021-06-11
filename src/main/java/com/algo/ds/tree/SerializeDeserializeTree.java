package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Serialize/Deserialize a binary tree whose data is a number.
 *
 * Time complexity O(n) Space complexity O(n)
 *
 * Reference https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * 
 * Example:
 * 
 * You may serialize the following tree:
 * 
 * 1 / \ 2 3 / \ 4 5
 * 
 * as "[1,2,3,null,null,4,5]" Clarification: The above format is the same as how LeetCode serializes a binary tree. You
 * do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * 
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class SerializeDeserializeTree {

	/**
	 * Serialize tree using level order traversal.
	 */
	public String serializeBinaryTree(TreeNode root) {
		if (root == null) {
			return "";
		}

		StringBuffer buff = new StringBuffer();

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root == null) {
				buff.append("null").append(",");
			} else {
				buff.append(root.data).append(",");
				queue.offer(root.left);
				queue.offer(root.right);
			}
		}

		for (int i = buff.length() - 1; i >= 0; i--) {
			if (buff.charAt(i) == '%' || buff.charAt(i) == ',') {
				buff.deleteCharAt(i);
			} else {
				break;
			}
		}

		return buff.toString();
	}

	/**
	 * Deserialize Tree using level order traversal.
	 */
	public TreeNode deserializeBinaryTree(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}

		String[] input = data.split(",");
		if (input == null || input.length == 0) {
			return null;
		}

		Queue<TreeNode> queue = new LinkedList<>();

		int index = 0;
		queue.offer(new TreeNode(Integer.parseInt(input[index])));
		TreeNode root = queue.peek();

		index++;

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			if (index < input.length && !input[index].equals("null")) {
				current.left = new TreeNode(Integer.parseInt(input[index]));
				queue.offer(current.left);
			}

			index++;

			if (index < input.length && !input[index].equals("null")) {
				current.right = new TreeNode(Integer.parseInt(input[index]));
				queue.offer(current.right);
			}

			index++;
		}

		return root;
	}

	// Nary tree
	public String serializeNAryTree(Node root) {
		if (root == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("(");
		sb.append(root.val);
		for (Node child : root.children) { // 有孩子就往里加，没有就(5)
			sb.append(serializeNAryTree(child));
		}
		sb.append(")");

		return sb.toString();
	}

	public Node deserializeNAryTree(String s) {
		Node root = null;
		Node parent = null;
		Stack<Node> stack = new Stack<Node>();
		int pos = 0;

		while (pos < s.length()) {
			char c = s.charAt(pos);

			if (c == '(') {
				pos++; // Next must be a number

				Node node = new Node(s.charAt(pos) - '0');
				if (root == null) {
					root = node; // Will come here only once
				}
				if (parent != null) {
					parent.children.add(node);
				}

				parent = node;
				stack.push(node); // 记录一下
			} else if (c == ')') {
				stack.pop(); // 弹出来

				if (!stack.isEmpty()) {
					parent = stack.peek(); // Change parent node, 1 level above
				}
			}

			pos++;
		}

		return root;
	}

	static class Node {
		int val;
		List<Node> children;

		Node(int val) {
			this.val = val;
			this.children = new ArrayList<Node>();
		}
	}

	/**
	 * You need to construct a binary tree from a string consisting of parenthesis and integers.
	 * 
	 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of
	 * parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with
	 * the same structure.
	 * 
	 * You always start to construct the left child node of the parent first if it exists.
	 * 
	 * Example: Input: "4(2(3)(1))(6(5))" Output: return the tree root node representing the following tree:
	 * 
	 * 
	 * 4 / \ 2 6 / \ / 3 1 5
	 * 
	 * Note: There will only be '(', ')', '-' and '0' ~ '9' in the input string. An empty tree is represented by ""
	 * instead of "()".
	 */
	public TreeNode str2BinaryTree(String s) {
		int left = 0, right = 0;

		while (right < s.length() && Character.isDigit(s.charAt(right))) {
			right++;
		} // Right is (

		int num = Integer.valueOf(s.substring(left, right));
		TreeNode root = new TreeNode(num);

		if (right < s.length()) {
			int count = 1;
			left = right; // left is (

			while (right + 1 < s.length() && count != 0) {
				right++;
				if (s.charAt(right) == '(') {
					count++;
				}
				if (s.charAt(right) == ')') {
					count--;
				}
			} // right is ) eventually

			root.left = str2BinaryTree(s.substring(left + 1, right));
		}

		if (right < s.length()) {
			root.right = str2BinaryTree(s.substring(right + 1, s.length() - 1));
		}

		return root;
	}

	// If above is difficult to remember and not generic. Refer to following one. Binary Tree only
	TreeNode str2tree(String s) {
		if (s.length() == 0) {
			return null;
		}

		s = "(" + s + ")";

		TreeNode root = null;
		TreeNode parent = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int pos = 0;

		while (pos < s.length()) {
			char c = s.charAt(pos);

			if (c == '(') {
				pos++; // Next must be a number
				// If number could be multi digits, use a while loop here
				int num = 0;
				while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
					num = num * 10 + (s.charAt(pos) - '0');
					pos++;
				}
				pos--; // pos will point to the char immediately after last digit

				TreeNode node = new TreeNode(num);
				if (root == null) {
					root = node; // Will come here only once
				}
				if (parent != null) {
					// If the tree is guaranteed to be binary, you can easily change above code to
					if (parent.left == null) {
						parent.left = node;
					} else {
						parent.right = node;
					}
				}

				parent = node;
				stack.push(node); // 记录一下
			} else if (c == ')') {
				stack.pop(); // 弹出来

				if (!stack.isEmpty()) {
					parent = stack.peek(); // Change parent node, 1 level above
				}
			}

			pos++;
		}

		return root;
	}

	public NaryTreeNode build(String input) {
		if (input == null || input.length() <= 2) {
			return null;
		}

		int[] pos = { 1 };
		NaryTreeNode root = helper(input, pos);
		return root;
	}

	Node deserialize(String s) {
		Node root = null;
		Node parent = null;
		Stack<Node> stack = new Stack<Node>();
		int pos = 0;

		while (pos < s.length()) {
			char c = s.charAt(pos);

			if (c == '(') {
				pos++; // Next must be a number
				// If number could be multi digits, use a while loop here
				Node node = new Node(s.charAt(pos) - '0');
				if (root == null) {
					root = node; // Will come here only once
				}
				if (parent != null) {
					parent.children.add(node);
				}

				parent = node;
				stack.push(node); // 记录一下
			} else if (c == ')') {
				stack.pop(); // 弹出来

				if (!stack.isEmpty()) {
					parent = stack.peek(); // Change parent node, 1 level above
				}
			}

			pos++;
		}

		return root;
	}

	static class NaryTreeNode {
		int val;
		List<NaryTreeNode> children = new ArrayList<NaryTreeNode>();

		NaryTreeNode(int val) {
			this.val = val;
		}
	}

	public static void main(String args[]) {
		SerializeDeserializeTree sd = new SerializeDeserializeTree();
		TreeTraversals tt = new TreeTraversals();

		TreeNode r = new TreeNode(1);
		r.left = new TreeNode(2);
		r.left.left = null;
		r.left.right = null;
		r.right = new TreeNode(3);
		// r.right.left = new TreeNode(4);
		// r.right.right = new TreeNode(5);
		// String s = sd.serialize(r);
		// System.out.println(s);
		//
		// Node n = sd.deserialize(s);
		// tt.inOrder(n);
		// System.out.println();

		// Node node = sd.deserialize("10,$,30,15,$,%,20,$,21,16,$,%,18");

		// tt.inOrder(node);
		System.out.println();
		String serializedTree = sd.serializeBinaryTree(r);
		System.out.println("Serialized :" + serializedTree);
		TreeNode root = sd.deserializeBinaryTree("1,2,3");
		System.out.println("deserialized ");
		tt.inOrder(root);

		// TreeNode res = sd.deserializeLevelOrder("%%4");
		// tt.inOrder(res);
	}
}
