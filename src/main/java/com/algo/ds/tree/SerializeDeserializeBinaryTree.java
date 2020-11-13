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
public class SerializeDeserializeBinaryTree {

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

	public static void main(String args[]) {
		SerializeDeserializeBinaryTree sd = new SerializeDeserializeBinaryTree();
		TreeTraversals tt = new TreeTraversals();

		TreeNode r = new TreeNode(1);
		r.left = new TreeNode(2);
		r.left.left = null;
		r.left.right = null;
		r.right = new TreeNode(3);
		r.right.left = new TreeNode(4);
		r.right.right = new TreeNode(5);
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
