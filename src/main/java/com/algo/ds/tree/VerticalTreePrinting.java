package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/
 * 
 * Category : Medium
 */
public class VerticalTreePrinting {

	/**
	 * Solution 1 - The idea is to traverse the tree once and get the minimum and maximum horizontal distance with
	 * respect to root. For the tree shown above, minimum distance is -2 (for node with value 4) and maximum distance is
	 * 3 (For node with value 9). Once we have maximum and minimum distances from root, we iterate for each vertical
	 * line at distance minimum to maximum from root, and for each vertical line traverse the tree and print the nodes
	 * which lie on that vertical line.
	 * 
	 * Time Complexity: Time complexity of above algorithm is O(w*n) where w is width of Binary Tree and n is number of
	 * nodes in Binary Tree. In worst case, the value of w can be O(n) (consider a complete tree for example) and time
	 * complexity can become O(n^2). Space complexity - O(n)
	 * 
	 * 
	 * @param root
	 */
	public List<List<Integer>> printVertical1(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();

		// 1. find the range of left bound and right bound
		int[] range = new int[2];
		findRange(root, range, 0);

		// 2. calculate number of columns in the result
		int rootIndex = 0 - range[0];
		int columns = range[1] - range[0] + 1;
		for (int i = 0; i < columns; i++) {
			ans.add(new ArrayList<Integer>());
		}

		// 3. fill in vertically in a recursive manner
		fillNode(ans, root, rootIndex);

		return ans;
	}

	private void findRange(TreeNode node, int[] range, int position) {
		if (node == null) {
			return;
		}

		range[0] = Math.min(range[0], position);
		range[1] = Math.max(range[1], position);

		findRange(node.left, range, position - 1);
		findRange(node.right, range, position + 1);
	}

	private void fillNode(List<List<Integer>> ans, TreeNode node, int index) {
		if (node == null) {
			return;
		}
		ans.get(index).add(node.data);
		fillNode(ans, node.left, index - 1);
		fillNode(ans, node.right, index + 1);
	}

	/**
	 * Solution 2: Time Complexity of hashing based solution can be considered as O(n) under the assumption that we have
	 * good hashing function that allows insertion and retrieval operations in O(1) time. In the above C++
	 * implementation, map of STL is used. map in STL is typically implemented using a Self-Balancing Binary Search Tree
	 * where all operations take O(Logn) time. Therefore time complexity of above implementation is O(nLogn).
	 * 
	 * 
	 * @param root
	 */
	public void printVertical2(TreeNode root) {
		Map<Integer, List<TreeNode>> map = new TreeMap<Integer, List<TreeNode>>();
		populateMap(root, map, 0);
		printLevel(map);
	}

	private void populateMap(TreeNode root, Map<Integer, List<TreeNode>> map, int level) {
		if (root == null) {
			return;
		}
		List<TreeNode> listNodes = null;
		if (map.containsKey(level)) {
			listNodes = map.get(level);
		} else {
			listNodes = new ArrayList<TreeNode>();
			map.put(level, listNodes);
		}
		listNodes.add(root);
		populateMap(root.left, map, level - 1);
		populateMap(root.right, map, level + 1);
	}

	private void printLevel(Map<Integer, List<TreeNode>> map) {
		for (Integer key : map.keySet()) {
			List<TreeNode> listNodes = map.get(key);
			for (TreeNode n : listNodes) {
				System.out.print(n.data + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Solution 3: The basic idea that will be used here is a modified level-order traversal where we keep track of the
	 * horizontal distance of each node while traversing the tree. And once we know horizontal distance of a node, we
	 * add it to the list of nodes which have same horizontal distance as that of current node. This list of nodes is
	 * maintained using a treeMap where key is horizontal distance 'd' and value is list of nodes which are at
	 * horizontal distance 'd' from the root. A treeMap is modified hashMap where keys are inserted in sorted order. (A
	 * treeMap is implemented using Red-Black tree and hence insertion/retrieval takes O(log n) time instead of O(1)
	 * time as is the case for a regular hashMap).
	 * 
	 * Time Complexity is O(n.log(n)) Space Complexity is O(n)
	 * 
	 * @author surya
	 *
	 */
	class QueueEntry {
		TreeNode node;
		int horizontalDistance;

		QueueEntry(TreeNode node, int horizontalDistance) {
			this.node = node;
			this.horizontalDistance = horizontalDistance;
		}
	}

	public void printVerticalOrder(TreeNode root) {
		Map<Integer, ArrayList<Integer>> verticalOrderMap = new TreeMap<>();

		fillUpVerticalOrderMap(root, 0, verticalOrderMap);

		// print map entries to print the top view
		Iterator<Entry<Integer, ArrayList<Integer>>> iterator = verticalOrderMap.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Integer, ArrayList<Integer>> mapEntry = iterator.next();
			ArrayList<Integer> nodeList = mapEntry.getValue();
			for (int i = 0; i < nodeList.size(); i++) {
				System.out.print("  " + nodeList.get(i));
			}
			System.out.println("");
		}
	}

	private void fillUpVerticalOrderMap(TreeNode currentNode, int horizontalDistFromRoot,
			Map<Integer, ArrayList<Integer>> verticalOrderMap) {
		if (currentNode == null)
			return;

		LinkedList<QueueEntry> queue = new LinkedList<>();

		queue.add(new QueueEntry(currentNode, horizontalDistFromRoot));

		while (!queue.isEmpty()) {
			QueueEntry entry = queue.remove();

			// update verticalOrderMap with current node entry
			ArrayList<Integer> mapEntry = (ArrayList<Integer>) verticalOrderMap.get(entry.horizontalDistance);

			if (mapEntry != null) // if node exists at same horizontal distance from root, update the nodeList
			{
				mapEntry.add(entry.node.data);
				verticalOrderMap.put(entry.horizontalDistance, mapEntry);
			} else // create a new list for this horizontal distance
			{
				mapEntry = new ArrayList<>();
				mapEntry.add(entry.node.data);
				verticalOrderMap.put(entry.horizontalDistance, mapEntry);
			}

			// we don't want to adding null nodes into the queue
			if (entry.node.left != null) {
				// left child would have horizontal distance = parent's horizontal distance - 1
				queue.add(new QueueEntry(entry.node.left, entry.horizontalDistance - 1));
			}

			if (entry.node.right != null) {
				// right child would have horizontal distance = parent's horizontal distance + 1
				queue.add(new QueueEntry(entry.node.right, entry.horizontalDistance + 1));
			}
		}
	}

	public static void main(String args[]) {

		TreeNode head = new TreeNode(3);
		head.left = new TreeNode(3);
		head.right = new TreeNode(-6);
		head.left.left = new TreeNode(-7);
		head.left.right = new TreeNode(2);
		head.right.left = new TreeNode(9);
		head.right.right = new TreeNode(6);
		head.right.right.left = new TreeNode(11);
		head.right.right.right = new TreeNode(13);

		VerticalTreePrinting vtp = new VerticalTreePrinting();
		vtp.printVertical2(head);
		System.out.println();
		List<List<Integer>> list = vtp.printVertical1(head);
		list.forEach(l -> System.out.println(l));
		System.out.println();
		vtp.printVerticalOrder(head);
	}
}
