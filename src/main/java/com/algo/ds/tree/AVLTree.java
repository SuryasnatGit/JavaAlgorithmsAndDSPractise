package com.algo.ds.tree;

import java.util.NoSuchElementException;

/**
 * Video link - https://youtu.be/rbg7Qf8GkQ4
 * 
 * AVL - Adelson, Velski and Landis
 *
 * Write a program to insert into an AVL tree.
 * 
 * AVL tree is self balancing binary tree. Difference of height of left or right subtree cannot be
 * greater than one. Each sub-tree is a AVL tree.
 * 
 * There are four different use cases to insert into AVL tree:<br/>
 * left left - needs ones right rotation <br/>
 * left right - needs one left and one right rotation <br/>
 * right left - needs one right and one left rotation <br/>
 * right right - needs one left rotation
 * 
 * Follow rotation rules to keep tree balanced.
 * 
 * At every node we will also keep height of the tree so that we don't have to recalculate values
 * again.
 * 
 * Runtime complexity to insert into AVL tree is O(logn).- for searching, inserting and deletion.
 * Because it is self-balancing, the performance of the AVL tree is generally more consistent than
 * that of a BST. However, since it has to check and the rotation operations are performed
 * frequently, in contrast to the red black tree, its performance suffers when inserting/removing
 * data on a regular basis.
 * 
 * AVL trees are ideal in cases where searches are performed frequently and insertion/deletion
 * operations, rarely. This is because AVL trees require a little more rotation in comparison to
 * other tree data structures such as the red-black tree. Bottom line: AVL trees are useful for
 * lookup, or searching for data in an existing collection.
 * 
 * References http://en.wikipedia.org/wiki/AVL_tree
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * 
 */
public class AVLTree<Key extends Comparable<Key>, Value> {

	// root of the tree
	private Node root;

	private class Node {
		private Key key;
		private Value value;
		private int height; // height of sub-tree
		private int size; // total number of nodes in sub-tree
		private Node left; // left sub-tree
		private Node right; // right sub -tree

		public Node(Key key, Value value, int height, int size) {
			this.key = key;
			this.value = value;
			this.height = height;
			this.size = size;
		}
	}

	public AVLTree() {
		// TODO Auto-generated constructor stub
	}

	boolean isEmpty() {
		return root == null;
	}

	int size() {
		return size(root);
	}

	int size(Node node) {
		return node == null ? 0 : node.size;
	}

	/**
	 * Returns the height of the internal AVL tree. It is assumed that the height of an empty tree is -1
	 * and the height of a tree with just one node is 0.
	 * 
	 * @return
	 */
	int height() {
		return height(root);
	}

	int height(Node node) {
		return node == null ? -1 : node.height;
	}

	Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key is null");
		Node node = get(root, key);
		return node == null ? null : node.value;
	}

	Node get(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return get(node.left, key);
		else if (cmp > 0)
			return get(node.right, key);
		else
			return node;
	}

	boolean contains(Key key) {
		return get(key) != null;
	}

    private Node leftRotate(Node root){
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }
    
    private Node rightRotate(Node root){
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }

    private int setHeight(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
    }
    

    
    private int setSize(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.size : 0), (root.right != null ? root.right.size : 0));
    }
    
	void insert(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("key is null");
		if (val == null) {
			delete(key);
		}
		insert(root, key, val);
	}

	/**
	 * They rely on adding an extra attribute, the balance factor to each node. This factor indicates
	 * whether the tree is left-heavy (the height of the left sub-tree is 1 greater than the right
	 * sub-tree), balanced (both sub-trees are the same height) or right-heavy (the height of the right
	 * sub-tree is 1 greater than the left sub-tree). If the balance would be destroyed by an insertion,
	 * a rotation is performed to correct the balance.
	 * 
	 * Following are two basic operations that can be performed to re-balance a BST without violating
	 * the BST property (keys(left) < key(root) < keys(right)). 1) Left Rotation 2) Right Rotation
	 * 
	 * @param node
	 * @param data
	 * @return
	 */
	public Node insert(Node node, Key key, Value value) {
        if(node == null){
			return new Node(key, value, 0, 1);
        }
		int cmp = key.compareTo(node.key);
		if (cmp > 0)
			node.right = insert(node.right, key, value);
		else if (cmp < 0)
			node.left = insert(node.left, key, value);
		else {
			node.value = value;
			return node;
        }
		// set size and height
		node.size = 1 + size(node.left) + size(node.right);
		node.height = 1 + Math.max(height(node.left), height(node.right));
		// do the balancing and return the balanced node
		return balance(node);
    }

	/**
	 * restores AVL tree property of subtree
	 * 
	 * @param node
	 * @return
	 */
	private Node balance(Node node) {
		if (balanceFactor(node) < -1) {
			if (balanceFactor(node.right) > 0) {
				node.right = rightRotate(node.right);
			}
			node = leftRotate(node);
		} else if (balanceFactor(node) > 1) {
			if (balanceFactor(node.left) < 0) {
				node.left = leftRotate(node.left);
			}
			node = rightRotate(node);
		}
		return node;
	}

	/**
	 * Returns the balance factor of the subtree. The balance factor is defined as the difference in
	 * height of the left subtree and right subtree, in this order. Therefore, a subtree with a balance
	 * factor of -1, 0 or 1 has the AVL property since the heights of the two child subtrees differ by
	 * at most one.
	 * 
	 * @param node
	 * @return
	 */
	private int balanceFactor(Node node) {
		return height(node.left) - height(node.right);
    }

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("input key is null");
		if (!contains(key))
			return;
		delete(root, key);
	}

	public Node delete(Node node, Key key) {
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			node.left = delete(node.left, key);
		else if (cmp > 0)
			node.right = delete(node.right, key);
		else {
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;
			else {
				Node temp = node;
				node = min(temp.right);
				node.right = deleteMin(temp.right);
				node.left = temp.left;
			}
		}
		node.size = 1 + size(node.left) + size(node.right);
		node.height = 1 + Math.max(height(node.left), height(node.right));
		return balance(node);
	}

	/**
	 * Returns the smallest key in the symbol table.
	 * 
	 * @return the smallest key in the symbol table
	 * @throws NoSuchElementException
	 *             if the symbol table is empty
	 */
	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}

	/**
	 * Returns the node with the smallest key in the subtree.
	 * 
	 * @param x
	 *            the subtree
	 * @return the node with the smallest key in the subtree
	 */
	private Node min(Node x) {
		if (x.left == null)
			return x;
		return min(x.left);
	}

	/**
	 * Removes the smallest key and associated value from the symbol table.
	 * 
	 * @throws NoSuchElementException
	 *             if the symbol table is empty
	 */
	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException("called deleteMin() with empty symbol table");
		root = deleteMin(root);
	}

	/**
	 * Removes the smallest key and associated value from the given subtree.
	 * 
	 * @param x
	 *            the subtree
	 * @return the updated subtree
	 */
	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}
    
    public static void main(String args[]){
		// AVLTree avlTree = new AVLTree();
		// Node root = new Node(key, value, height, size);
		// root = avlTree.insert(root, -10);
		// root = avlTree.insert(root, 2);
		// root = avlTree.insert(root, 13);
		// root = avlTree.insert(root, -13);
		// root = avlTree.insert(root, -15);
		// root = avlTree.insert(root, 15);
		// root = avlTree.insert(root, 17);
		// root = avlTree.insert(root, 20);
		//
		// TreeTraversals tt = new TreeTraversals();
		// tt.inOrder(root);
		// System.out.println();
		// tt.preOrder(root);
    }
}
