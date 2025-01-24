package com.practice.leetcode.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. Path Sum II  Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 * A root-to-leaf path is a path starting from the root and ending at any leaf node.
 * A leaf is a node with no children.
 * <p>
 * Example 1: Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 */
public class PathSumII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        PathSumII solution = new PathSumII();
        List<List<Integer>> paths = solution.pathSum(root, 22);
        System.out.println(paths);
    }

    private List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> paths = new ArrayList<>();
        LinkedList<Integer> currentPath = new LinkedList<>();
        dfs(root, targetSum, currentPath, paths);
        return paths;
    }

    private void dfs(TreeNode node, int targetSum, LinkedList<Integer> currentPath, List<List<Integer>> paths) {

        if (node == null) return;

        currentPath.offerLast(node.val);

        if (node.left == null && node.right == null && targetSum == node.val) {
            paths.add(currentPath);
        } else {
            dfs(node.left, targetSum - node.val, currentPath, paths);
            dfs(node.right, targetSum - node.val, currentPath, paths);
        }
        currentPath.pollLast();

    }


}
