package com.algo.ds.tree;

import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/.
 * 
 * For example, if the given traversal is {10, 5, 1, 7, 40, 50}, then the output should be root of following tree. 10 /
 * \ 5 40 / \ \ 1 7 50
 * 
 * Test case empty array 1,2 or more elements in the array
 */
public class ConstructBSTFromPreOrderArray {

	/**
	 * The trick is to set a range {min .. max} for every node. Initialize the range as {INT_MIN .. INT_MAX}. The first
	 * node will definitely be in range, so create root node. To construct the left subtree, set the range as {INT_MIN
	 * root->data}. If a values is in the range {INT_MIN .. root->data}, the values is part part of left subtree. To
	 * construct the right subtree, set the range as {root->data..max .. INT_MAX}.
	 * 
	 * Time Complexity: O(n) Auxiliary Space : O(1) if Function Call Stack size is not considered, otherwise O(n)
	 * 
	 * @param preorder
	 * @return
	 */
	public TreeNode toBST_recursive(int preorder[]) {
		return toBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private int index;

	private TreeNode toBST(int preorder[], int min, int max) {
		if (index >= preorder.length) {
			return null;
		}
		if (preorder[index] < min || preorder[index] >= max) {
			return null;
		}

		TreeNode node = new TreeNode();
		node.data = preorder[index];
		index++;
		node.left = toBST(preorder, min, node.data);
		node.right = toBST(preorder, node.data, max);
		return node;
	}

	/**
	 * Stack based iterative solution that works in O(n) time.
	 * 
	 * Time Complexity: O(n). The complexity looks more from first look. If we take a closer look, we can observe that
	 * every item is pushed and popped only once. So at most 2n push/pop operations are performed in the main loops of
	 * constructTree(). Therefore, time complexity is O(n).
	 * 
	 * @param preorder
	 * @return
	 */
	public TreeNode toBST_Iterative(int preorder[]) {
		// first element of pre is always root.
		TreeNode root = new TreeNode(preorder[0]);

		Stack<TreeNode> s = new Stack<>();
		// push root to stack
		s.push(root);

		// iterate through the rest of the array
		for (int i = 1; i < preorder.length; i++) {
			TreeNode temp = null;
			// keep on popping elements from the stack until the element in pre array is > stack's top element
			while (!s.isEmpty() && preorder[i] > s.peek().data)
				temp = s.pop();
			// make this greater value as right child and push to stack
			if (temp != null) {
				temp.right = new TreeNode(preorder[i]);
				s.push(temp.right);
			} else {
				// if the element is < stack's top element then make this as left child and push to stack.
				temp = s.peek();
				temp.left = new TreeNode(preorder[i]);
				s.push(temp.left);
			}
		}

		return root;
	}

	public static void main(String args[]) {
		int preorder[] = { 10, 5, 1, 7, 40, 50 };
		ConstructBSTFromPreOrderArray poa = new ConstructBSTFromPreOrderArray();
		TreeNode root = poa.toBST_Iterative(preorder);
		TreeTraversals tt = new TreeTraversals();
		tt.preOrder(root);
		System.out.println();
		tt.inOrder(root);
	}
}
