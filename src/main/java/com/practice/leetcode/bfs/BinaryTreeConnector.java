package com.practice.leetcode.bfs;

/**
 * Given a binary tree, connect each node with its level order successor. The last node of each
 * level should point to the first node of the next level.
 */

import java.util.LinkedList;
import java.util.Queue;

class BinaryTreeConnector { // Changed class name for clarity

    static class TreeNode {
        int val;
        TreeNode left, right, next;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public void connectLevelOrderSuccessor(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode levelEnd = root;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }

            if (current != levelEnd) {
                current.next = queue.peek();
            } else {
                current.next = null;
                levelEnd = queue.peek();
            }
        }
    }

    public void printLevelOrderUsingNext(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            TreeNode temp = current;
            while (temp != null) {
                System.out.print(temp.val + " ");
                temp = temp.next;
            }
            System.out.println(); // Move to the next level
            current = current.left;
        }
    }

    public static void main(String[] args) {
        BinaryTreeConnector tree = new BinaryTreeConnector();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        tree.connectLevelOrderSuccessor(root);
        System.out.println("Level order traversal using 'next' pointers:");
        tree.printLevelOrderUsingNext(root);
    }
}
