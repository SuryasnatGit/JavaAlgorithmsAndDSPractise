package com.algo.ds.array;

/**
 * Category : Medium
 */
public class CountInversionInArray {

	public static void main(String[] args) {
		CountInversionInArray ci = new CountInversionInArray();
		System.out.println(ci.getInversionCount_simple(new int[] { 2, 4, 1, 3, 5 }));
		System.out.println(ci.getInversionCount_mergeSort(new int[] { 2, 4, 1, 3, 5 }));
	}

	/*
	 * Problem 1 : Inversion Count for an array indicates how far (or close) the array is from being sorted. If array is
	 * already sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the
	 * maximum. Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
	 * 
	 * Example: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
	 */

	/**
	 * Method 1- Simple. For each element, count number of elements which are on right side of it and are smaller than
	 * it. Complexity - O(n^2)
	 * 
	 * @param arr
	 * @return
	 */
	int getInversionCount_simple(int[] arr) {
		int len = arr.length;
		int invCount = 0;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (arr[i] > arr[j])
					invCount++;
			}
		}
		return invCount;
	}

	/**
	 * 
	 * Method 2 - Using merge sort. time complexity - O(n log n). space complexity - O(n)
	 * 
	 * @param arr
	 * @return
	 */
	public int getInversionCount_mergeSort(int[] arr) {
		int length = arr.length;
		int[] temp = new int[length];
		return merge(arr, temp, 0, length - 1);
	}

	private int merge(int[] arr, int[] temp, int start, int end) {
		int invCount = 0;
		if (end > start) {
			int mid = (start + end) / 2;
			invCount = merge(arr, temp, start, mid); // left merge
			invCount += merge(arr, temp, mid + 1, end); // right merge
			invCount += mergeSort(arr, temp, start, mid, end);
		}
		return invCount;
	}

	private int mergeSort(int[] arr, int[] temp, int start, int mid, int end) {
		int invCount = 0;
		int i = start;// pointer to left sub array
		int j = mid;// pointer to right sub array
		int k = start;// pointer for final sub array
		while (i <= mid - 1 && j <= end) {
			if (arr[i] < arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];

				// In merge process, let i is used for indexing left sub-array and j for right sub-array. At any
				// step in merge(), if a[i] is greater than a[j], then there are (mid i) inversions. because left
				// and right subarrays are sorted, so all the remaining elements in left-subarray (a[i+1], a[i+2]
				// a[mid]) will be greater than a[j]

				invCount += (mid - i);
			}
		}
		// copy remaining elements of left sub-array to temp
		while (i <= mid - 1)
			temp[k++] = arr[i++];
		// copy remaining elements of right sub-array to temp
		while (j <= end)
			temp[k++] = arr[j++];

		return invCount;
	}

	/**
	 * Method 3 : Using AVL Tree
	 * 
	 * Approach: The idea is to use Self-Balancing Binary Search Tree like Red-Black Tree, AVL Tree, etc and augment it
	 * so that every node also keeps track of number of nodes in the right subtree. So every node will contain the count
	 * of nodes in its right subtree i.e. the number of nodes greater than that number. So it can be seen that the count
	 * increases when there is a pair (a,b), where a appears before b in the array and a > b, So as the array is
	 * traversed from start to the end, add the elements to the AVL tree and the count of the nodes in its right subtree
	 * of the newly inserted node will be the count increased or the number of pairs (a,b) where b is the present
	 * element.
	 * 
	 * Time Complexity: O(n Log n). Insertion in an AVL insert takes O(log n) time and n elements are inserted in the
	 * tree so time complexity is O(n log n).
	 * 
	 * Space Complexity: O(n). To create a AVL tree with max n nodes O(n) extra space is required.
	 */
	// Initialize result
	private int result = 0;

	// An AVL tree node
	class Node {
		int key, height;
		Node left, right;

		// Size of the tree rooted
		// with this Node
		int size;
	}

	// A utility function to get the height of
	// the tree rooted with N
	int height(Node N) {
		if (N == null)
			return 0;

		return N.height;
	}

	// A utility function to size of the
	// tree of rooted with N
	int size(Node N) {
		if (N == null)
			return 0;

		return N.size;
	}

	// A utility function to create a new node
	Node newNode(int ele) {
		Node temp = new Node();
		temp.key = ele;
		temp.left = null;
		temp.right = null;
		temp.height = 1;
		temp.size = 1;
		return temp;
	}

	// A utility function to right rotate
	// subtree rooted with y
	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		// Update sizes
		y.size = size(y.left) + size(y.right) + 1;
		x.size = size(x.left) + size(x.right) + 1;

		// Return new root
		return x;
	}

	// A utility function to left rotate
	// subtree rooted with x
	Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		// Update sizes
		x.size = size(x.left) + size(x.right) + 1;
		y.size = size(y.left) + size(y.right) + 1;

		// Return new root
		return y;
	}

	// Get Balance factor of Node N
	int getBalance(Node N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	// Inserts a new key to the tree rotted
	// with Node. Also, updates *result
	// (inversion count)
	Node insert(Node node, int key) {

		// 1. Perform the normal BST rotation
		if (node == null)
			return (newNode(key));

		if (key < node.key) {
			node.left = insert(node.left, key);

			// UPDATE COUNT OF GREATE ELEMENTS FOR KEY
			result = result + size(node.right) + 1;
		} else
			node.right = insert(node.right, key);

		// 2. Update height and size of
		// this ancestor node
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		node.size = size(node.left) + size(node.right) + 1;

		// 3. Get the balance factor of this
		// ancestor node to check whether this
		// node became unbalanced
		int balance = getBalance(node);

		// If this node becomes unbalanced,
		// then there are 4 cases

		// Left Left Case
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		// Return the (unchanged) node pointer
		return node;
	}

	// The following function returns inversion
	// count in arr[]
	public void getInvCount(int arr[], int n) {

		// Create empty AVL Tree
		Node root = null;

		// Starting from first element,
		// insert all elements one by
		// one in an AVL tree.
		for (int i = 0; i < n; i++)

			// Note that address of result
			// is passed as insert operation
			// updates result by adding count
			// of elements greater than arr[i]
			// on left of arr[i]
			root = insert(root, arr[i]);
	}
}
