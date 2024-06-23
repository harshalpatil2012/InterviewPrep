package com.practice.leetcode.dfs;

import java.util.HashMap;

/**
 * 437. Path Sum III
 * Given the root of a binary tree and an integer targetSum,
 * return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 */
public class PathSumIII {
    static int pathCount = 0;

    public int pathSum(TreeNode root, int targetSum) {

        HashMap<Integer, Integer> prefSumCount = new HashMap<>();
        prefSumCount.put(0, 1);
        dfs(targetSum, 0, prefSumCount, root);
        return pathCount;
    }

    private void dfs(int targetSum, int currentSum, HashMap<Integer, Integer> prefSumCount, TreeNode root) {
        if (root == null) return;

        currentSum += root.val;
        pathCount += prefSumCount.getOrDefault(currentSum - targetSum, 0);
        prefSumCount.put(currentSum, prefSumCount.getOrDefault(currentSum, 0) + 1);

        dfs(targetSum, currentSum, prefSumCount, root.left);
        dfs(targetSum, currentSum, prefSumCount, root.right);
        prefSumCount.put(currentSum, prefSumCount.getOrDefault(currentSum, 0) - 1);

    }


    public static void main(String[] args) {
        // Example 1:
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(-3);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(11);
        root1.left.left.left = new TreeNode(3);
        root1.left.left.right = new TreeNode(-2);
        root1.left.right.right = new TreeNode(1);
        int targetSum1 = 8;

        // Example 2:
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(8);
        root2.left.left = new TreeNode(11);
        root2.left.left.left = new TreeNode(7);
        root2.left.left.right = new TreeNode(2);
        root2.right.left = new TreeNode(13);
        root2.right.right = new TreeNode(4);
        root2.right.right.left = new TreeNode(5);
        root2.right.right.right = new TreeNode(1);
        int targetSum2 = 22;

        PathSumIII solution = new PathSumIII();
        System.out.println("Example 1: Number of paths = " + solution.pathSum(root1, targetSum1)); // Output: 3
        pathCount = 0;
        System.out.println("Example 2: Number of paths = " + solution.pathSum(root2, targetSum2)); // Output: 3
    }
}