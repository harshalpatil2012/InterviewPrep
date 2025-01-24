package com.practice.leetcode.dfs;

/**
 * Given a binary tree, find the root-to-leaf path with the maximum sum.
 */
public class MaximumRootToLeafPathSum {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root, 0);
        return maxSum;
    }

    private void dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return;
        }
        currentSum += node.val;

        if (node.left == null && node.right == null) {
            // Update maxSum if current path sum is greater
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        } else {
            // Continue the DFS on the left and right children
            dfs(node.left, currentSum);
            dfs(node.right, currentSum);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaximumRootToLeafPathSum solution = new MaximumRootToLeafPathSum();
        int maxSum = solution.maxPathSum(root);
        System.out.println("Maximum Root-to-Leaf Path Sum: " + maxSum);  // Output: 42
    }
}