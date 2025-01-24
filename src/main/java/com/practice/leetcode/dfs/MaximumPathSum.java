package com.practice.leetcode.dfs;

/**
 * 124. Binary Tree Maximum Path Sum
 * A  path  in a binary tree is a sequence of nodes where each
 * pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence  at most once. Note that the path does
 * not need to pass through the root.
 * The  path sum  of a path is the sum of the node's values in the path.
 * Given the  root  of a binary tree, return  the maximum  path sum  of any  non-empty  path.
 * <p>
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range  [1, 3 * 104].
 * -1000 <= Node.val <= 1000 Java optimal solution with approach intuition,
 * algorithm and time and space complexity and visualization.
 * Give complete code including main method and all inputs
 */
public class MaximumPathSum {
    static int maxSum = 0;

    public static void main(String[] args) {
        // Example 1:
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        MaximumPathSum solution1 = new MaximumPathSum();
        System.out.println("Example 1: Maximum Path Sum = " + solution1.maxPathSum(root1)); // Output: 6

        // Example 2:
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        maxSum = 0;
        MaximumPathSum solution2 = new MaximumPathSum();
        System.out.println("Example 2: Maximum Path Sum = " + solution2.maxPathSum(root2)); // Output: 42
    }

    private int maxPathSum(TreeNode root) {
        maxSumHelper(root);
        return maxSum;
    }

    private int maxSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftMax = Math.max(maxSumHelper(node.left), 0);
        int rightMax = Math.max(maxSumHelper(node.right), 0);
        int currentMax = node.val + leftMax + rightMax;
        maxSum = Math.max(maxSum, currentMax);
        return node.val + Math.max(leftMax, rightMax);
    }
}
