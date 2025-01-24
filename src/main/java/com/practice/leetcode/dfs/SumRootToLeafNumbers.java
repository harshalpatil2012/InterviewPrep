package com.practice.leetcode.dfs;

public class SumRootToLeafNumbers {
    static int totalSum = 0;

    public static int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return totalSum;
    }

    private static void dfs(TreeNode node, int currentNumber) {
        if (node == null) return;

        currentNumber = currentNumber * 10 + node.val;

        if (node.left == null && node.right == null) {
            totalSum += currentNumber;
        } else {
            dfs(node.left, currentNumber);
            dfs(node.right, currentNumber);
        }
    }

    /**
     * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf
     * path in the given tree.
     */
    public static boolean isSequencePresent(TreeNode root, int[] arr) {
        return dfsSequenceCheck(root, arr, 0);
    }

    private static boolean dfsSequenceCheck(TreeNode node, int[] arr, int index) {
        if (node == null) return false;  // Base case: Reached a null node, not a valid sequence

        if (index >= arr.length || node.val != arr[index]) return false; // Mismatch or end of sequence

        if (node.left == null && node.right == null && index == arr.length - 1)
            return true; // Leaf node and sequence matches

        return dfsSequenceCheck(node.left, arr, index + 1) ||
                dfsSequenceCheck(node.right, arr, index + 1);
    }

    public static void main(String[] args) {
        // Example 1:
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println(new SumRootToLeafNumbers().sumNumbers(root1)); // Output: 25

        // Example 2:
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(0);
        root2.left.left = new TreeNode(5);
        root2.left.right = new TreeNode(1);
        System.out.println(new SumRootToLeafNumbers().sumNumbers(root2)); // Output: 1026


        // Example tree 1
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.right = new TreeNode(5);

        // Example tree 2
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(7);
        root4.right = new TreeNode(9);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        root4.right.left = new TreeNode(2);
        root4.right.right = new TreeNode(7);


        // Test cases for sumNumbers (unchanged)
        System.out.println("Sum of root-to-leaf numbers (Tree 1): " + sumNumbers(root1)); // 25
        System.out.println("Sum of root-to-leaf numbers (Tree 2): " + sumNumbers(root2)); // 172

        // Test cases for isSequencePresent
        int[] arr1 = {1, 2, 5};
        int[] arr2 = {1, 3, 7};
        int[] arr3 = {1, 9, 2};
        int[] arr4 = {1, 9};

        System.out.println("Sequence 1 present in Tree 1? " + isSequencePresent(root1, arr1)); // true
        System.out.println("Sequence 2 present in Tree 1? " + isSequencePresent(root1, arr2)); // false
        System.out.println("Sequence 3 present in Tree 2? " + isSequencePresent(root2, arr3)); // true
        System.out.println("Sequence 4 present in Tree 2? " + isSequencePresent(root2, arr4)); // false
    }
}