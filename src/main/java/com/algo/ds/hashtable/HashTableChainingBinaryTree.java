package com.algo.ds.hashtable;

import com.algo.common.TreeNode;

/**
 * Hash Map implementation using balanced binary search tree. O(log N) lookup time. The advantage of this is potentially
 * using less space, since we no longer allocate a large array. We can also iterate through the keys in order, which can
 * be useful sometimes.
 * 
 * @author surya
 *
 * @param <K>
 * @param <V>
 */
public class HashTableChainingBinaryTree<K, V> {

	private TreeNode[] table;
	private int size;

	public HashTableChainingBinaryTree(int size) {
		table = new TreeNode[nextPrime(size)];
		this.size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int hashFunction(Integer key) {
		int hashCode = key.hashCode();
		int index = hashCode % table.length;
		if (index < 0)
			index += table.length;
		return index;
	}

	private int nextPrime(int num) {
		// if num is divisible by 2 increment num
		if (num % 2 == 0)
			num++;
		// find the next prime
		for (; isPrime(num); num += 2)
			;
		return num;
	}

	/**
	 * check if a number is prime or not
	 * 
	 * @param num
	 * @return
	 */
	private boolean isPrime(int num) {
		if (num == 2 || num == 3)
			return true;
		if (num == 1 || num % 2 == 0)
			return false;
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	public void put(int key, int value) {
		int hash = hashFunction(key);
		TreeNode root = table[hash];
		root = insert(root, key, value);
		table[hash] = root;
	}

	private TreeNode insert(TreeNode node, int key, int value) {
		if (node == null)
			node = new TreeNode(value);
		else {
			if (value < node.data)
				node.left = insert(node.left, key, value);
			else
				node.right = insert(node.right, key, value);
		}
		return node;
	}

	public Integer get(int key) {
		int hash = hashFunction(key);
		TreeNode root = table[hash];
		while (root.data != key) {
			if (key < root.data)
				root = root.left;
			else
				root = root.right;
			if (root == null)
				return null;
		}
		return root.data;
	}

	public int remove(int key) {
		int hash = hashFunction(key);
		TreeNode root = table[hash];
		root = delete(root, key);
		return root.data;
	}

	private TreeNode delete(TreeNode treeNode, int key) {
		int hash = hashFunction(key);
		TreeNode root = table[hash];
		TreeNode current = root;
		TreeNode parent = root;
		boolean isLeftChild = false;
		while (current.data != key) {
			if (key < current.data) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.right;
				isLeftChild = false;
			}
			if (current == null)
				return null;
		}
		// at this point we know current is the node to be deleted. now 4 cases arise
		// 1. if current does not have any children
		if (current.left == null && current.right == null) {
			if (current == root)
				root = null;
			if (isLeftChild)
				parent.left = null;
			else
				parent.right = null;
		}

		// 2. when current has only left child
		if (current.right == null) {
			if (current == root)
				root = null;
			if (isLeftChild)
				parent.left = current.left;
			else
				parent.right = current.left;
		}

		// 3. when current has only right child
		if (current.left == null) {
			if (current == root)
				root = null;
			if (isLeftChild)
				parent.left = current.right;
			else
				parent.right = current.right;
		}

		// 4. when current has both left and right child
		if (current.left != null && current.right != null) {
			TreeNode successor = findSuccessor(current);
			if (current == root)
				root = null;
			else {
				if (isLeftChild)
					parent.left = successor;
				else
					parent.right = successor;
			}
			successor.left = current.left;
		}

		return current;
	}

	private TreeNode findSuccessor(TreeNode deleteNode) {
		TreeNode successor = null;
		TreeNode successorParent = null;
		TreeNode current = deleteNode.right;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		// at this point there is no more left child of successor.
		// check if successor has right child, if so add to left of successor parent
		if (successor != deleteNode.right) {
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
		}
		return successor;
	}
}
