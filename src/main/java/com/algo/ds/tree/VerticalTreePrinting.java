package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/
 */
public class VerticalTreePrinting {

	/**
	 * Solution 1 - The idea is to traverse the tree once and get the minimum and maximum horizontal
	 * distance with respect to root. For the tree shown above, minimum distance is -2 (for node with
	 * value 4) and maximum distance is 3 (For node with value 9). Once we have maximum and minimum
	 * distances from root, we iterate for each vertical line at distance minimum to maximum from root,
	 * and for each vertical line traverse the tree and print the nodes which lie on that vertical line.
	 * 
	 * Time Complexity: Time complexity of above algorithm is O(w*n) where w is width of Binary Tree and
	 * n is number of nodes in Binary Tree. In worst case, the value of w can be O(n) (consider a
	 * complete tree for example) and time complexity can become O(n2).
	 * 
	 * 
	 * @param root
	 */
	public void verticalOrder(Node root) {
		// find min and max
		int min = 0; // minimum horizontal distance from root
		int max = 0; // maximum horizontal distance from root
		int hd = 0; // horizontal distance from root
		findMinMax(root, min, max, hd);

		for (int i = min; i <= max; i++) {
			printVerticalLine(root, i, 0);
		}
	}

	private void findMinMax(Node root, int min, int max, int hd) {
		if (root == null)
			return;
		if (hd < min)
			min = hd;
		else if (hd > max)
			max = hd;

		// recur for left and right subtrees
		findMinMax(root.left, min, max, hd - 1);
		findMinMax(root.right, min, max, hd + 1);
	}

	private void printVerticalLine(Node root, int line, int hd) {
		if (root == null)
			return;
		// print if this node is the given line
		if (hd == line)
			System.out.println(root.data + " ");
		// recurse
		printVerticalLine(root.left, line, hd - 1);
		printVerticalLine(root.right, line, hd + 1);
	}

	/**
	 * Solution 2: Time Complexity of hashing based solution can be considered as O(n) under the
	 * assumption that we have good hashing function that allows insertion and retrieval operations in
	 * O(1) time. In the above C++ implementation, map of STL is used. map in STL is typically
	 * implemented using a Self-Balancing Binary Search Tree where all operations take O(Logn) time.
	 * Therefore time complexity of above implementation is O(nLogn).
	 * 
	 * 
	 * @param root
	 */
    public void printVertical(Node root){
        Map<Integer,List<Node>> map = new TreeMap<Integer,List<Node>>();
        populateMap(root,map,0);
        printLevel(map);
    }
    
    private void printLevel(Map<Integer,List<Node>> map){
        for(Integer key : map.keySet()){
            List<Node> listNodes = map.get(key);
            for(Node n : listNodes){
                System.out.print(n.data + " ");
            }
            System.out.println();
        }
    }
    
    private void populateMap(Node root, Map<Integer,List<Node>> map,int level){
        if(root == null){
            return;
        }
        List<Node> listNodes = null;
        if(map.containsKey(level)){
            listNodes = map.get(level);
        }else{
            listNodes = new ArrayList<Node>();
            map.put(level, listNodes);
        }
        listNodes.add(root);
        populateMap(root.left,map,level-1);
        populateMap(root.right,map,level+1);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(3, head);
        head = bt.addNode(-6, head);
        head = bt.addNode(-7, head);
        head = bt.addNode(2, head);
        head = bt.addNode(9, head);
        head = bt.addNode(6, head);
        head = bt.addNode(11, head);
        head = bt.addNode(13, head);
        head = bt.addNode(12, head);
        VerticalTreePrinting vtp = new VerticalTreePrinting();
        vtp.printVertical(head);
    }
}
