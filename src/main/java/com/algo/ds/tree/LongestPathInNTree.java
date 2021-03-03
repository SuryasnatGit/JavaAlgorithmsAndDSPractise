package com.algo.ds.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * An n-ary tree, the elements in the tree are not repeated, find the longest increasing path from top to bottom in the
 * tree, not necessarily starting from the root. To define TreeNode yourself. Follow up is what to do if there are
 * duplicate elements in the tree
 *
 */

public class LongestPathInNTree {

	private LinkedList<NTreeNode> max = new LinkedList<>();

	public LinkedList<NTreeNode> getLongestIncreasingPath(NTreeNode node) {
		LinkedList<NTreeNode> result = new LinkedList<>();
		if (node == null) {
			return result;
		}

		for (NTreeNode nNode : node.children) {
			LinkedList<NTreeNode> list = getLongestIncreasingPath(nNode);
			if (list.size() > result.size()) {
				result = list;
			}
		}

		if (result.isEmpty() || node.val < result.getLast().val) {
			result.addLast(node);
		}

		if (result.size() > max.size()) {
			max = result;
		}

		return result;
	}

	public static void main(String[] args) {

		NTreeNode nn1 = new NTreeNode(1);
		NTreeNode nn2 = new NTreeNode(2);
		NTreeNode nn3 = new NTreeNode(31);
		NTreeNode nn4 = new NTreeNode(4);
		NTreeNode nn5 = new NTreeNode(5);
		NTreeNode nn6 = new NTreeNode(6);
		NTreeNode nn7 = new NTreeNode(7);
		NTreeNode nn8 = new NTreeNode(8);
		NTreeNode nn9 = new NTreeNode(9);
		NTreeNode nn10 = new NTreeNode(10);
		NTreeNode nn11 = new NTreeNode(11);
		NTreeNode nn12 = new NTreeNode(12);

		Set<NTreeNode> ch1 = new HashSet<NTreeNode>();
		ch1.add(nn2);
		ch1.add(nn3);
		ch1.add(nn4);
		nn1.children = ch1;

		Set<NTreeNode> ch2 = new HashSet<NTreeNode>();
		ch2.add(nn5);
		nn3.children = ch2;

		LongestPathInNTree l = new LongestPathInNTree();
		l.getLongestIncreasingPath(nn1);

		for (NTreeNode nn : l.max) {
			System.out.println(nn.val);
		}
	}
}

class NTreeNode {
	int val;
	Set<NTreeNode> children = new HashSet<>();

	public NTreeNode(int val) {
		this.val = val;
	}
}
