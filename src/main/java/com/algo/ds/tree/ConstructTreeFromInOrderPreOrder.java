package com.algo.ds.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * 
 * Test cases: <br/>
 * Empty tree <br/>
 * One node tree <br/>
 * All left side tree <br/>
 * All right side tree <br/>
 * Mixed tree <br/>
 * Full tree <br/>
 * complete tree
 */
public class ConstructTreeFromInOrderPreOrder {

	// Approach 1: Time Complexity: O(n^2). Worst case occurs when tree is left skewed.
	// Approach 2: Optimized way is to use hash map to store indexes of in-order traversal so that
	// search can be done in O(1) time. overall time complexity - O(n)

	private int preIndex = 0;

	public Node createTree(int inorder[], int preorder[]) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		Node result = createTree(inorder, preorder, 0, inorder.length - 1, map);
		preIndex = 0;
		return result;
	}

	private Node createTree(int inorder[], int preorder[], int start, int end, Map<Integer, Integer> map) {
		if (start > end) {
			return null;
		}
		// find the index of the pre-order node in in-order traversal
		// int i;
		// for (i = start; i <= end; i++) {
		// if (preorder[preIndex] == inorder[i]) {
		// break;
		// }
		// }
		// pick current node from pre-order traversal using preIndex and increment preIndex
		int curr = preorder[preIndex++];
		Node node = Node.newNode(curr);

		int inIndex = map.get(curr);

		// using index in in-order traversal construct left and right sub-tree
		node.left = createTree(inorder, preorder, start, inIndex - 1, map);
		node.right = createTree(inorder, preorder, inIndex + 1, end, map);
		return node;
	}

	// Approach 3 - using stack -- difficult to understand
	// TODO : for later
	/**
	 * Use the fact that InOrder traversal is Left-Root-Right and PreOrder traversal is Root-Left-Right.
	 * Also, first node in the PreOrder traversal is always the root node and the first node in the
	 * InOrder traversal is the leftmost node in the tree.
	 * 
	 * Maintain two data-structures : Stack (to store the path we visited while traversing PreOrder
	 * array) and Set (to maintain the node in which the next right subtree is expected).
	 * 
	 * 1. Do below until you reach the leftmost node. Keep creating the nodes from PreOrder traversal If
	 * the stacks topmost element is not in the set, link the created node to the left child of stacks
	 * topmost element (if any), without popping the element. Else link the created node to the right
	 * child of stacks topmost element. Remove the stacks topmost element from the set and the stack.
	 * Push the node to a stack.
	 *
	 * 2. Keep popping the nodes from the stack until either the stack is empty, or the topmost element
	 * of stack compares to the current element of InOrder traversal. Once the loop is over, push the
	 * last node back into the stack and into the set.
	 * 
	 * 3. Goto Step 1.
	 * 
	 * 
	 * 
	 */
	static Set<Node> set = new HashSet<>();
	static Stack<Node> stack = new Stack<>();

	// Function to build tree using given traversal
	public Node buildTree(int[] preorder, int[] inorder) {

		Node root = null;
		for (int pre = 0, in = 0; pre < preorder.length;) {

			Node node = null;
			do {
				node = Node.newNode(preorder[pre]);
				if (root == null) {
					root = node;
				}
				if (!stack.isEmpty()) {
					if (set.contains(stack.peek())) {
						set.remove(stack.peek());
						stack.pop().right = node;
					} else {
						stack.peek().left = node;
					}
				}
				stack.push(node);
			} while (preorder[pre++] != inorder[in] && pre < preorder.length);

			node = null;
			while (!stack.isEmpty() && in < inorder.length && stack.peek().data == inorder[in]) {
				node = stack.pop();
				in++;
			}

			if (node != null) {
				set.add(node);
				stack.push(node);
			}
		}

		return root;
	}

	/* This funtcion is here just to test buildTree() */
	void printInorder(Node node) {
		if (node == null)
			return;

		/* first recur on left child */
		printInorder(node.left);

		/* then print the data of node */
		System.out.print(node.data + " ");

		/* now recur on right child */
		printInorder(node.right);
	}

	public static void main(String args[]) {
		ConstructTreeFromInOrderPreOrder tree = new ConstructTreeFromInOrderPreOrder();
		int in[] = new int[] { 4, 2, 5, 1, 6, 3 };
		int pre[] = new int[] { 1, 2, 4, 5, 3, 6 };
		int len = in.length;
		Node root = tree.createTree(in, pre);

		// building the tree by printing inorder traversal
		System.out.println("Inorder traversal of constructed tree is : ");
		tree.printInorder(root);
	}
}
