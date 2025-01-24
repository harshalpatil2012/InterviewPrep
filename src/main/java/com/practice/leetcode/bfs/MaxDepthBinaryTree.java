package com.practice.leetcode.bfs;

/**
 * 104.Maximum Depth of Binary  Tree Given the root of a binary tree, return its maximum depth.
 * A binary  tree's maximum depth is the number of nodes along the longest path from the root node down
 * to the farthest leaf node.
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 */
public class MaxDepthBinaryTree {
    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        MaxDepthBinaryTree solution = new MaxDepthBinaryTree();
        System.out.println(solution.maxDepth(root1)); // Output: 3

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        System.out.println(solution.maxDepth(root2)); // Output: 2
    }

    private int maxDepth(TreeNode root1) {

        if (root1 == null) {
            return 0;
        }
        int leftDepth = maxDepth(root1.left);
        int rightDepth = maxDepth(root1.right);
        return Math.max(leftDepth, rightDepth) + 1;

    }
}
