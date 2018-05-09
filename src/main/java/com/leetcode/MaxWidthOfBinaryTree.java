

package com.leetcode;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.algo.ds.tree.TreeNode;


/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum
 * width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null. The
 * width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the
 * level, where the null nodes between the end-nodes are also counted into the length calculation. <br/>
 * <br/>
 * We know that a binary tree can be represented by an array (assume the root begins from the position with index 1 in
 * the array). If the index of a node is i, the indices of its two children are 2*i and 2*i + 1. The idea is to use two
 * arrays (start[] and end[]) to record the the indices of the leftmost node and rightmost node in each level,
 * respectively. For each level of the tree, the width isend[level] - start[level] + 1. Then, we just need to find the
 * maximum width
 *
 * @author ctsuser1
 */
public class MaxWidthOfBinaryTree {

    /**
     * Solution 1 -
     * 
     * @param node
     * @return
     */
    int maxWidth(final TreeNode<Integer, Integer> node) {
        return dfs(node, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    private int dfs(final TreeNode<Integer, Integer> root, final int level, final int order, final List<Integer> start, final List<Integer> end) {
        if (root == null) {
            return 0;
        }
        if (start.size() == level) {
            start.add(order);
            end.add(order);
        } else {
            end.set(level, order);
        }
        final int cur = (end.get(level) - start.get(level)) + 1;
        final int left = dfs(root.left, level + 1, 2 * order, start, end);
        final int right = dfs(root.right, level + 1, (2 * order) + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }

    // this method does not seem to work.
    /**
     * Solution 2 -
     * 
     * @param node
     * @return
     */
    int maxWidthUsingHeapIndexing(TreeNode<Integer, Integer> node) {
        List<Integer> leftNodes = new ArrayList<>(); // container for left nodes
        int maxWidth = 0; // contains max width
        dfs(node, 1, 0, leftNodes, maxWidth);
        return maxWidth;
    }

    private void dfs(TreeNode<Integer, Integer> node, int id, int depth, List<Integer> leftNodes, int maxWidth) {
        if (node == null)
            return;
        if (depth >= leftNodes.size())
            leftNodes.add(id);
        maxWidth = Integer.max(maxWidth, id + 1 - leftNodes.get(depth));
        dfs(node.left, 2 * id, depth + 1, leftNodes, maxWidth);
        dfs(node.right, 2 * id + 1, depth + 1, leftNodes, maxWidth);
    }

    private Map<Integer, int[]> map = new HashMap<>();

    /**
     * Solution 3 - Using node position of binary tree
     * 
     * @return
     */
    int maxWidthUsingNodePositionOfBinaryTree(TreeNode<Integer, Integer> node) {
        if (node == null)
            return 0;
        findMax(node, 0, 0);
        int res = 1;
        for (int[] rec : map.values()) {
            res = Math.max(res, (rec[1] - rec[0] + 1));
        }
        return res;
    }

    private void findMax(TreeNode<Integer, Integer> node, int level, int pos) {
        if (node == null)
            return;

        int[] rec = map.get(level);
        if (rec == null) { // initialize
            rec = new int[2];
            rec[0] = Integer.MAX_VALUE;
            rec[1] = Integer.MIN_VALUE;
        }
        rec[0] = Math.min(rec[0], pos);
        rec[1] = Math.max(rec[1], pos);
        map.put(level, rec);

        findMax(node.left, level + 1, pos * 2);
        findMax(node.right, level + 1, pos * 2 + 1);
    }

    int maxWidthOfBinaryTreeUsingBFS(TreeNode<Integer, Integer> node) {
        if (node == null)
            return 0;
        int maxWidth = 0;
        // declare queue
        Queue<Map.Entry<TreeNode<Integer, Integer>, Integer>> queue = new LinkedList<>();
        queue.offer(new AbstractMap.SimpleEntry<>(node, 1));

        while (!queue.isEmpty()) {
            int left = queue.peek().getValue();
            int right = left; // initialize
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode<Integer, Integer> key = queue.peek().getKey();
                right = queue.poll().getValue();
                if (key.left != null)
                    queue.offer(new AbstractMap.SimpleEntry<>(key.left, 2 * right));
                if (key.right != null)
                    queue.offer(new AbstractMap.SimpleEntry<>(key.right, 2 * right + 1));
            }
            maxWidth = Math.max(maxWidth, right + 1 - left);
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        MaxWidthOfBinaryTree maxWidth = new MaxWidthOfBinaryTree();

        TreeNode<Integer, Integer> root = new TreeNode<>(1, 1);
        root.left = new TreeNode<>(2, 2);
        root.right = new TreeNode<>(3, 3);
        root.left.left = new TreeNode<>(4, 4);
        root.left.right = new TreeNode<>(5, 5);
        root.right.left = new TreeNode<>(6, 6);
        root.right.right = new TreeNode<>(7, 7);
        // System.out.println(maxWidth.maxWidth(root));
        // System.out.println(maxWidth.maxWidthUsingHeapIndexing(root));
        // System.out.println(maxWidth.maxWidthUsingNodePositionOfBinaryTree(root));
        System.out.println(maxWidth.maxWidthOfBinaryTreeUsingBFS(root));
    }

}
