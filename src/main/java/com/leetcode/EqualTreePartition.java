

package com.leetcode;


import java.util.HashMap;
import java.util.Map;

import com.algo.ds.tree.TreeNode;


/**
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which
 * have the equal sum of values after removing exactly one edge on the original tree.
 * 
 * @author ctsuser1
 */
public class EqualTreePartition {

    /**
     * Method 1 - The idea is to use a hash table to record all the different sums of each subtree in the tree. If the
     * total sum of the tree is sum, we just need to check if the hash table constains sum/2.
     * 
     * @param root
     * @return
     */
    public boolean checkEqualTree(TreeNode<Integer, Integer> root) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = getSum(root, map);
        if (sum == 0)
            return map.getOrDefault(sum, 0) > 1;
        return sum % 2 == 0 && map.containsKey(sum / 2);
    }

    private int getSum(TreeNode<Integer, Integer> root, Map<Integer, Integer> map) {
        if (root == null)
            return 0; // base condition for recursion
        int cur = root.value + getSum(root.left, map) + getSum(root.right, map);
        map.put(cur, map.getOrDefault(cur, 0) + 1);
        return cur;
    }

    private int sum = 0;
    private boolean equal = false;

    public boolean checkEqualTree_1(TreeNode<Integer, Integer> root) {
        if(root.left == null || root.right == null) return false;
        sum = 
    }

    public static void main(String[] args) {
        TreeNode<Integer, Integer> a = new TreeNode<>(5, 5);
        a.left = new TreeNode<>(10, 10);
        a.right = new TreeNode<>(10, 10);
        a.right.left = new TreeNode<>(2, 2);
        a.right.right = new TreeNode<>(3, 3);
        EqualTreePartition par = new EqualTreePartition();
        System.out.println(par.checkEqualTree(a));
    }

}
