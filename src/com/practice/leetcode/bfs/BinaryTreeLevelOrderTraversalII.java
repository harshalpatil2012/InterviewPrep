package com.practice.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * 107. Binary Tree Level Order Traversal II Given the root of a binary tree, return the bottom-up level order
 * traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 * Input: root = [3,9,20,null,null,15,7] Output: [[15,7],[9,20],[3]]
 * Example 2: Input: root = [1] Output: [[1]] Example 3: Input: root = [] Output: [] java optimal solution
 */
public class BinaryTreeLevelOrderTraversalII {

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        BinaryTreeLevelOrderTraversalII solution = new BinaryTreeLevelOrderTraversalII();
        System.out.println(solution.levelOrderBottom(root1)); // Output: [[15, 7], [9, 20], [3]]

        // Example 2
        TreeNode root2 = new TreeNode(1);
        System.out.println(solution.levelOrderBottom(root2)); // Output: [[1]]

        // Example 3
        TreeNode root3 = null;
        System.out.println(solution.levelOrderBottom(root3)); // Output: []
    }

    private List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                level.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            result.add(0, level);
        }

        return result;
    }
}

