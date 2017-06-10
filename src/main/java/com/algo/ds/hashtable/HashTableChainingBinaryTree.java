package com.algo.ds.hashtable;

import com.algo.ds.tree.TreeNode;

public class HashTableChainingBinaryTree<K, V> {

	private TreeNode<K, V>[] table;
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
		TreeNode<K, V> root = table[hash];
		root = insert(root, key, value);
		table[hash] = root;
	}

	private TreeNode<K, V> insert(TreeNode<K, V> node, int key, int value) {
		if (node == null)
			node = new TreeNode<K, V>(key, value);
		else {
			if (value < node.value)
				node.left = insert(node.left, key, value);
			else
				node.right = insert(node.right, key, value);
		}
		return node;
	}

	public Integer get(int key) {
		int hash = hashFunction(key);
		TreeNode<K, V> root = table[hash];
		while (root.key != key) {
			if (key < root.key)
				root = root.left;
			else
				root = root.right;
			if (root == null)
				return null;
		}
		return root.value;
	}

	public int remove(int key) {
		int hash = hashFunction(key);
		TreeNode<K, V> root = table[hash];
		root = delete(root, key);
		return root.value;
	}

	private TreeNode<K, V> delete(TreeNode<K, V> treeNode, int key) {
		int hash = hashFunction(key);
		TreeNode<K, V> root = table[hash];
		TreeNode<K, V> current = root;
		TreeNode<K, V> parent = root;
		boolean isLeftChild = false;
		while (current.key != key) {
			if (key < current.key) {
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
			TreeNode<K, V> successor = findSuccessor(current);
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

	private TreeNode<K, V> findSuccessor(TreeNode<K, V> deleteNode) {
		TreeNode<K, V> successor = null;
		TreeNode<K, V> successorParent = null;
		TreeNode<K, V> current = deleteNode.right;
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
