package com.algo.ds.tree;

/**
 * http://www.geeksforgeeks.org/add-greater-values-every-node-given-bst/.
 * 
 * Given a Binary Search Tree (BST), modify it so that all greater values in the given BST are added to every node. 
 * For example, consider the following BST.

              50
           /      \
         30        70
        /   \      /  \
      20    40    60   80 

The above tree should be modified to following 

              260
           /      \
         330        150
        /   \       /  \
      350   300    210   80

 * Test cases:
 * Empty tree
 * One node tree
 * Two node tree
 */

class IntegerRef{
    int val;
}

public class AddGreaterValueNodeToEveryNode {

	/**
	 * A simple method for solving this is to find sum of all greater values for every node. This method
	 * would take O(n^2) time. We can do it using a single traversal. The idea is to use following BST
	 * property. If we do reverse Inorder traversal of BST, we get all nodes in decreasing order. We do
	 * reverse Inorder traversal and keep track of the sum of all nodes visited so far, we add this sum
	 * to every node.
	 * 
	 * @param root
	 * @param ref
	 */
    public void add(Node root,IntegerRef ref){
        if(root == null){
            return ;
        }
        add(root.right,ref);
        root.data += ref.val;
        ref.val = root.data;
        add(root.left,ref);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(5, root);
        root = bt.addNode(20, root);
        root = bt.addNode(15, root);
        root = bt.addNode(25, root);
        AddGreaterValueNodeToEveryNode agv = new AddGreaterValueNodeToEveryNode();
        IntegerRef ir = new IntegerRef();
        ir.val = 0;
        agv.add(root, ir);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
    }
}
