package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to
 * bottom, column by column). If two nodes are in the same row and column, the order should be from
 * left to right.
 *
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/.
 * 
 * For each node, its left child's degree is -1 and is right child's degree is +1. We can do a level
 * order traversal and save the degree information.
 * 
 * 
 */
public class VerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        int minLevel = 0;
        int maxLevel = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        Deque<Node> queue = new LinkedList<>();
        Deque<Integer> level = new LinkedList<>();

        queue.offerFirst(root);
        level.offerFirst(0);

        while (!queue.isEmpty()) {
            root = queue.pollFirst();
			int verticalLevel = level.pollFirst();
			minLevel = Math.min(minLevel, verticalLevel);
			maxLevel = Math.max(maxLevel, verticalLevel);

			List<Integer> r = map.get(verticalLevel);
            if (r == null) {
                r = new ArrayList<>();
				map.put(verticalLevel, r);
            }
            r.add(root.data);

            if (root.left != null) {
                queue.offerLast(root.left);
				level.offerLast(verticalLevel - 1);
            }

            if (root.right != null) {
                queue.offerLast(root.right);
				level.offerLast(verticalLevel + 1);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = minLevel; i <= maxLevel; i++) {
            List<Integer> r = map.get(i);
            result.add(r);
        }
        return result;
    }
}