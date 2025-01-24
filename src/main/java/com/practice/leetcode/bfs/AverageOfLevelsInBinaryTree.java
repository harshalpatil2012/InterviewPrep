package com.practice.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. Average of Levels in Binary Tree Given the root of a binary tree,
 * return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on
 * level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 */
public class AverageOfLevelsInBinaryTree {

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        AverageOfLevelsInBinaryTree solution = new AverageOfLevelsInBinaryTree();
        System.out.println(solution.averageOfLevels(root1)); // Output: [3.0, 14.5, 11.0]

        // Example 2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.left.left = new TreeNode(15);
        root2.left.right = new TreeNode(7);
        System.out.println(solution.averageOfLevels(root2)); // Output: [3.0, 14.5, 11.0]
    }

    private List<Double> averageOfLevels(TreeNode root) {

        List<Double> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int level = queue.size();
            double total = 0;
            for (int i = 0; i < level; i++) {
                TreeNode currentNode = queue.poll();
                total += currentNode.val;
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            result.add(total / level);
        }
        return result;
    }
}
