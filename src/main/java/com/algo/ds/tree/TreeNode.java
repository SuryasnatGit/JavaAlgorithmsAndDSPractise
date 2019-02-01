package com.algo.ds.tree;

public class TreeNode<K, V> {

	public int key;
	public int value;
	public TreeNode<K, V> left;
	public TreeNode<K, V> right;
	public TreeNode<K, V> parent;
	private int size = 0;

	public TreeNode(int key, int val) {
		this.key = key;
		this.value = val;
	}

	public void insertlnOrder(int d) {
		if (d <= value) {
			if (left == null) {
				setLeftChild(new TreeNode(d,d));
			} else {
				left.insertlnOrder(d);
			}
		}else{
			if(right==null){
				setRightChild(new TreeNode(d, d));
			} else {
				right.insertlnOrder(d);
			}
		}
		size++;
	}

	public int size() {
		return size;
	}

	public TreeNode find(int d) {
		if (d == value) {
			return this;
		}else if(d<=value)	{
			return left != null ? left.find(d) : null;
		}else if(d>value){
			return right != null ? right.find(d) : null;
		}
		return null;
	}

	public void setLeftChild(TreeNode left){
		this.left = left;
		if (left != null) {
			left.parent = this;
		}
	}

	public void setRightChild(TreeNode right) {
		this.right = right;
		if (right != null) {
			right.parent = this;
		}
	}
}
