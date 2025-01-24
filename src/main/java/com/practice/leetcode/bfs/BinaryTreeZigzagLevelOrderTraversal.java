package com.practice.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. Binary Tree Zigzag Level Order Traversal Given the root of a binary tree,
 * return the zigzag level order traversal of its nodes' values. (i.e., from left to right,
 * then right to left for the next level and alternate between). Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        BinaryTreeZigzagLevelOrderTraversal solution = new BinaryTreeZigzagLevelOrderTraversal();
        System.out.println(solution.zigzagLevelOrder(root1)); // Output: [[3], [20, 9], [15, 7]]

        // Example 2
        TreeNode root2 = new TreeNode(1);
        System.out.println(solution.zigzagLevelOrder(root2)); // Output: [[1]]

        // Example 3
        TreeNode root3 = null;
        System.out.println(solution.zigzagLevelOrder(root3)); // Output: []
    }

    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> quque = new LinkedList<>();
        quque.add(root);

        boolean leftToRight = true;

        while (!quque.isEmpty()) {
            int level = quque.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();

            for (int i = 0; i < level; i++) {
                TreeNode currentNode = quque.poll();
                if (leftToRight) {
                    currentLevel.add(currentNode.val);
                } else {
                    currentLevel.addFirst(currentNode.val);
                }

                if (currentNode.left != null) {
                    quque.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    quque.add(currentNode.right);
                }
            }
            leftToRight = !leftToRight;
            result.add(currentLevel);
        }

        return result;
    }
}

