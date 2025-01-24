package com.practice.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1161. Maximum Level Sum of a Binary Tree Given the root of a binary tree,
 * the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at
 * level x is maximal. Input: root = [1,7,0,7,-8,null,null] Output: 2
 * Explanation: Level 1 sum = 1. Level 2 sum = 7 + 0 = 7. Level 3
 * sum = 7 + -8 = -1. So we return the level with the maximum sum which is level 2.
 * Example 2: Input: root = [989,null,10250,98693,-89388,null,null,null,-32127] Output: 2
 */
public class MaximumLevelSumBinaryTree {
    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(0);
        root1.left.left = new TreeNode(7);
        root1.left.right = new TreeNode(-8);

        MaximumLevelSumBinaryTree solution = new MaximumLevelSumBinaryTree();
        System.out.println(solution.maxLevelSum(root1)); // Output: 2

        // Example 2
        TreeNode root2 = new TreeNode(989);
        root2.right = new TreeNode(10250);
        root2.right.left = new TreeNode(98693);
        root2.right.right = new TreeNode(-89388);
        root2.right.left.right = new TreeNode(-32127);
        System.out.println(solution.maxLevelSum(root2)); // Output: 2
    }

    public int maxLevelSum(TreeNode root) {

        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        int curLevel = 1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelSum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                levelSum += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            if (levelSum > maxSum) {
                maxSum = levelSum;
                curLevel = maxLevel;
            }
            maxLevel++;
        }
        return curLevel;
    }
}
