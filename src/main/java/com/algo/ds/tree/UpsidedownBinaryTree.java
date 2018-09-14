

package com.algo.ds.tree;


/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same
 * parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left
 * leaf nodes. Return the new root. https://leetcode.com/problems/binary-tree-upside-down/. <br/>
 * left most node becomes root. <br/>
 * its parent becomes right child <br/>
 * its right sibling becomes left child <br/>
 * recursively follow rule for all nodes.
 */
public class UpsidedownBinaryTree {
    public Node upsideDownBinaryTree(Node root) {
        if (root == null) {
            return null;
        }
        return upsideDownBinaryTree(root, null, null);
    }

    public Node upsideDownBinaryTree(Node root, Node parent, Node rightChild) {
        if (root == null) {
            return parent;
        }
        Node left = root.left;
        Node right = root.right;

        root.right = parent;
        root.left = rightChild;

        return upsideDownBinaryTree(left, root, right);
    }
}
