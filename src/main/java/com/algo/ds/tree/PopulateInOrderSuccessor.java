package com.algo.ds.tree;

/**
 * http://www.geeksforgeeks.org/populate-inorder-successor-for-all-nodes/.
 * 
 * Given a Binary Tree where each node has following structure, write a function to populate next
 * pointer for all nodes. The next pointer for every node should be set to point to inorder
 * successor.
 * 
 * Initially, all next pointers have NULL values. Your function should fill these next pointers so
 * that they point to inorder successor.
 * 
 * Time Complexity: O(n)
 */
public class PopulateInOrderSuccessor {

    private void populate(Node root, NodeRef nodeRef){
        if(root == null){
            return;
        }
		// The first visited node will be the rightmost node next of the rightmost node will be NULL
        populate(root.right,nodeRef);
		// Set the next as previously visited node in reverse Inorder
        root.next = nodeRef.node;
		// Change the prev for subsequent node
        nodeRef.node = root;
		// Finally, set the next pointer in left subtree
        populate(root.left,nodeRef);
    }
    
	/**
	 * Solution (Use Reverse Inorder Traversal) Traverse the given tree in reverse inorder traversal and
	 * keep track of previously visited node. When a node is being visited, assign previously visited
	 * node as next.
	 * 
	 * 
	 * @param root
	 */
    public void populate(Node root){
        NodeRef nodeRef = new NodeRef();
        populate(root,nodeRef);
    }
    
    public void print(Node root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        print(root.next);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        head = bt.addNode(21, head);
        PopulateInOrderSuccessor pio = new PopulateInOrderSuccessor();
        pio.populate(head);
        while(head.left != null){
            head = head.left;
        }
        pio.print(head);
    }
}
