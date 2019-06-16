package com.algo.ds.tree;

import java.util.HashMap;
import java.util.Map;

import com.algo.ds.linkedlist.RandomLink;

/**
 * Given a Binary Tree where every node has following structure.
 * 
 * struct node { int key; struct node *left,*right,*random; } The random pointer points to any random node of the binary
 * tree and can even point to NULL, clone the given binary tree.
 * 
 * @author surya
 *
 */
public class CloneBinaryTreeWithRandomPointers {

	public RandomLink cloneBinaryTree(RandomLink root) {
		if (root == null)
			return null;

		Map<RandomLink, RandomLink> map = new HashMap<>();
		RandomLink newTree = copyLeftRightNode(root, map);
		copyRandom(root, newTree, map);
		return newTree;
	}

	private RandomLink copyLeftRightNode(RandomLink root, Map<RandomLink, RandomLink> map) {
		if (root == null)
			return null;

		RandomLink cloneNode = new RandomLink();
		cloneNode.data = root.data;
		map.put(root, cloneNode);
		cloneNode.left = copyLeftRightNode(root.left, map);
		cloneNode.right = copyLeftRightNode(root.right, map);
		return cloneNode;
	}

	private void copyRandom(RandomLink root, RandomLink cloneNode, Map<RandomLink, RandomLink> map) {
		if (cloneNode == null)
			return;

		cloneNode.random = map.get(root.random);
		copyRandom(root.left, cloneNode.left, map);
		copyRandom(root.right, cloneNode.right, map);
	}

}
