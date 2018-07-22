package com.algo.ds.tree;

/**
 * http://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
 * Only operation you can do is increase data on the node. No decrement of data.
 * 
 * Question: Given an arbitrary binary tree, convert it to a binary tree that holds Children Sum Property. You can only increment data 
 * values in any node (You cannot change the structure of the tree and cannot decrement the value of any node).

For example, the below tree doesn’t hold the children sum property, convert it to a tree that holds the property.

             50
           /     \     
         /         \
       7             2
     / \             /\
   /     \          /   \
  3        5      1      30
Algorithm:
Traverse the given tree in post order to convert it, i.e., first change left and right children to hold the children sum property 
then change the parent node.

Let difference between node’s data and children sum be diff.

     diff = node’s children sum - node’s data  
	If diff is 0 then nothing needs to be done.

If diff > 0 ( node’s data is smaller than node’s children sum) increment the node’s data by diff.

If diff < 0 (node’s data is greater than the node's children sum) then increment one child’s data. 
We can choose to increment either left or right child if they both are not NULL. Let us always first increment the left child. 
Incrementing a child changes the subtree’s children sum property so we need to change left subtree also. 
So we recursively increment the left child. If left child is empty then we recursively call increment() for right child. 
Let us run the algorithm for the given example. First convert the left subtree (increment 7 to 8).

             50
           /     \     
         /         \
       8             2
     / \             /\
   /     \          /   \
  3        5      1      30
Then convert the right subtree (increment 2 to 31)

          50
        /    \     
      /        \
    8            31
   / \           / \
 /     \       /     \
3       5    1       30
Now convert the root, we have to increment left subtree for converting the root.

          50
        /    \     
      /        \
    19           31
   / \           /  \
 /     \       /      \
14      5     1       30
Please note the last step – we have incremented 8 to 19, and to fix the subtree we have incremented 3 to 14.

 * 
 * Test case
 * Root greater than children
 * Root less than children
 * Root equal to children.
 * 
 * Time Complexity: O(n^2), Worst case complexity is for a skewed tree such that nodes are in decreasing order from root to leaf.
 */
public class ArbitaryTreeToChildSumTree {

    public void childSumTree(Node root){
        toChildSumTree(root);
    }
    
    private int toChildSumTree(Node root){
        if(root == null){
            return 0;
        }
        
        if(root.left == null && root.right == null){
            return root.data;
        }
        
        int sum1 = toChildSumTree(root.left);
        int sum2 = toChildSumTree(root.right);
        if(root.data < sum1 + sum2){
            root.data = sum1 + sum2;
        }else if(root.data > sum1 + sum2){
            incrementChild(root,root.data - sum1 - sum2);
        }
        return root.data;
    }
    
	private void incrementChild(Node root, int increment) {
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		if (root.left != null) {
			root.left.data = root.left.data + increment;
			incrementChild(root.left, increment);
		} else {
			root.right.data = root.right.data + increment;
			incrementChild(root.right, increment);
		}
	}

    public static void main(String args[]){
        ArbitaryTreeToChildSumTree att = new ArbitaryTreeToChildSumTree();
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        att.childSumTree(head);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(head);
    }
    
}
