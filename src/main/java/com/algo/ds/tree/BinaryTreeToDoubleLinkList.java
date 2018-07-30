package com.algo.ds.tree;

/**
 * http://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 * 
 * Complexity - O(n)
 */
public class BinaryTreeToDoubleLinkList {

	/**
	 * Approach 1: Given a Binary Tree (Bt), convert it to a Doubly Linked List(DLL). The left and right
	 * pointers in nodes are to be used as previous and next pointers respectively in converted DLL. The
	 * order of nodes in DLL must be same as Inorder of the given Binary Tree. The first node of Inorder
	 * traversal (left most node in BT) must be head node of the DLL.
	 * 
	 * @param root
	 */

    public void toDoubleLL(Node root){
        NodeRef prev = new NodeRef();
        toDoubleLL(root,prev);
    }
    
    private void toDoubleLL(Node root, NodeRef prev){
        if(root == null){
            return;
        }
        toDoubleLL(root.left,prev);
        if(prev.node != null){
            prev.node.right = root;
            root.left = prev.node;
            prev.node = root;
        }else{
            prev.node = root;
        }
        toDoubleLL(root.right,prev);
    }
    
	public Node toDoubleLinkList(Node root) {
		if (root == null)
			return root;

		Node node = toDoubleLinkListUtil(root);

		// return node which is the left most node of the binary tree
		while (node.left != null)
			node = node.left;

		return node;
	}

	private Node toDoubleLinkListUtil(Node node) {
		// base case
		if (node == null)
			return node;

		if (node.left != null) {
			Node left = toDoubleLinkListUtil(node.left);
			// find inorder predecessor
			for (; left.right != null; left = left.right)
				;
			// make root as next of inorder predecessor
			left.right = node;
			// make predecessor as previous of root
			node.left = left;
		}
		if (node.right != null) {
			Node right = toDoubleLinkListUtil(node.right);
			// find inorder successor
			for (; right.left != null; right = right.left)
				;
			// make root as previous of inorder successor
			right.left = node;
			// make successor as next of root
			node.next = right;
		}
		return node;
	}

	/**
	 * Approach 3.
	 * 
	 * 1) Fix Left Pointers: In this step, we change left pointers to point to previous nodes in DLL.
	 * The idea is simple, we do inorder traversal of tree. In inorder traversal, we keep track of
	 * previous visited node and change left pointer to the previous node. See fixPrevPtr() in below
	 * implementation.
	 * 
	 * 2) Fix Right Pointers: The above is intuitive and simple. How to change right pointers to point
	 * to next node in DLL? The idea is to use left pointers fixed in step 1. We start from the
	 * rightmost node in Binary Tree (BT). The rightmost node is the last node in DLL. Since left
	 * pointers are changed to point to previous node in DLL, we can linearly traverse the complete DLL
	 * using these pointers. The traversal would be from last to first node. While traversing the DLL,
	 * we keep track of the previously visited node and change the right pointer to the previous node.
	 * See fixNextPtr() in below implementation.
	 * 
	 * 
	 * 
	 * @param root
	 */
	// Changes left pointers to work as previous
	// pointers in converted DLL The function
	// simply does inorder traversal of Binary
	// Tree and updates left pointer using
	// previously visited node
	private Node prev;

	private void fixPrevptr(Node root) {
		if (root == null)
			return;

		fixPrevptr(root.left);
		root.left = prev;
		prev = root;
		fixPrevptr(root.right);

	}

	// Changes right pointers to work
	// as next pointers in converted DLL
	private Node fixNextptr(Node root) {
		// Find the right most node in
		// BT or last node in DLL
		while (root.right != null)
			root = root.right;

		// Start from the rightmost node, traverse
		// back using left pointers. While traversing,
		// change right pointer of nodes
		while (root != null && root.left != null) {
			Node left = root.left;
			left.right = root;
			root = root.left;
		}

		// The leftmost node is head of linked list, return it
		return root;
	}

	public Node BTTtoDLL(Node root) {
		prev = null;

		// Set the previous pointer
		fixPrevptr(root);

		// Set the next pointer and return head of DLL
		return fixNextptr(root);
	}

    public void print(Node root){
        Node curr = null;
        while(root != null){
            curr = root;
            System.out.print(root.data + " ");
            root = root.right;
        }
        System.out.println();
        root = curr;
        while(root != null){
            System.out.print(root.data + " ");
            root = root.left;
        }
    }
    
    public static void main(String args[]){
        BinaryTreeToDoubleLinkList btd = new BinaryTreeToDoubleLinkList();  
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(100, head);
        head = bt.addNode(90, head);
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(25, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(-7, head);
		// btd.toDoubleLL(head);
		btd.toDoubleLinkList(head);
        btd.print(head);
    }
}
