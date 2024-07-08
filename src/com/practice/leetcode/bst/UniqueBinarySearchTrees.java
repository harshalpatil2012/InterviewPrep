package com.practice.leetcode.bst;

/**
 * 96. Unique Binary Search Trees Given an integer n, return the number
 * of structurally unique BST's (binary search trees) which has exactly n nodes
 * of unique values from 1 to n. Example 1: Input: n = 3 Output: 5 Example 2:
 * Input: n = 1 Output: 1 Constraints: 1 <= n <= 19 Java optimal but crisp intutive
 * solution with solution approach intuition, algorithm and time and space
 * complexity and visualization. Give complete code including main method and all inputs
 */

/**
 * Algorithm (Dynamic Programming)
 * Base Case:  If there are 0 or 1 node(s), there's only one possible BST (empty tree or a single node).
 * DP Table: Create a DP array dp of size n+1. dp[i] will store the number of unique BSTs with i nodes.
 * Build Up:
 * Iterate from i = 2 to n.
 * For each i, iterate from j = 1 to i.
 * Let j be the root node.
 * The left subtree has j - 1 nodes (possible BSTs: dp[j - 1]).
 * The right subtree has i - j nodes (possible BSTs: dp[i - j]).
 * The total number of BSTs with i nodes and j as the root is the product of dp[j - 1] and dp[i - j].
 * Add this product to dp[i].
 * Result: Return dp[n], the number of unique BSTs with n nodes.
 * Visualization (n = 3)
 *  *   1      3     3      2      1
 *  *    \    /     /      / \      \
 *  *     3  2     1      1   3      2
 *  *    /    \           \    /      \
 *  *   2      1           2  1        3
 */
public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        UniqueBinarySearchTrees solution = new UniqueBinarySearchTrees();
        int n1 = 3;
        int result1 = solution.numTrees(n1);
        System.out.println("Unique BSTs for n = " + n1 + ": " + result1);

        int n2 = 1;
        int result2 = solution.numTrees(n2);
        System.out.println("Unique BSTs for n = " + n2 + ": " + result2);
    }

    private int numTrees(int num) {
        if (num == 0) {
            return 0;
        }
        int[] dp = new int[num + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[num];
    }
}
