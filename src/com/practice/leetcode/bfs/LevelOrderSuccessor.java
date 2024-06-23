package com.practice.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderSuccessor {

    public static void main(String[] args) {
        // Constructing a binary tree for testing
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode target = root.left; // Node with value 2
        TreeNode successor = findLevelOrderSuccessor(root, target);

        if (successor != null) {
            System.out.println("The level order successor of node " + target.val + " is " + successor.val);
        } else {
            System.out.println("The node " + target.val + " has no level order successor");
        }
    }

    public static TreeNode findLevelOrderSuccessor(TreeNode root, TreeNode target) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }

            if (current.val == target.val) {
                break;
            }
        }
        return queue.isEmpty() ? null : queue.poll();
    }

}
