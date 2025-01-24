package com.practice.leetcode.dfs;

/**
 * 543. Diameter of Binary Tree
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between
 * any two nodes in a tree. This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 */
public class DiameterOfBinaryTree {
    static int maxDiameter = 0;

    public static void main(String[] args) {
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        System.out.println("Example 1: Diameter = " + solution.diameterOfBinaryTree(root1)); // Output: 3

        maxDiameter = 0;
        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        System.out.println("Example 2: Diameter = " + solution.diameterOfBinaryTree(root2)); // Output: 1
    }

    private String diameterOfBinaryTree(TreeNode root) {
        calculatDiameter(root);
        return String.valueOf(maxDiameter);
    }

    private int calculatDiameter(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = calculatDiameter(root.left);
        int right = calculatDiameter(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return Math.max(left, right) + 1;
    }
}


