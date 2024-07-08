package com.practice.leetcode.bst;
/**
 * Algorithm
 * Create a recursive helper function generateTrees(start, end) to generate all BSTs with values from start to end.
 * For each value i from start to end:
 * Use i as the root.
 * Recursively generate all left subtrees with values from start to i-1.
 * Recursively generate all right subtrees with values from i+1 to end.
 * Combine each left subtree with each right subtree and attach them to the root i.
 * Store the results of each subproblem in a memoization table to avoid redundant computations.
 * Return the result from generateTrees(1, n).
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UniqueBinarySearchTreesIIUsingDP {

    List<TreeNode>[][] dp; // dp[start][end] stores the list of all unique BSTs that can be
    // formed using values from start to end.

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        // Initialize dp array
        dp = new List[n + 1][n + 1];
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        if (start > end) {
            result.add(null); // Base case: return null for empty subtree
            return result;
        }

        // Check if result for this range is already computed
        if (dp[start][end] != null) {
            return dp[start][end];
        }

        // Generate all possible BSTs for current range [start, end]
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtrees = generateTrees(start, i - 1);
            List<TreeNode> rightSubtrees = generateTrees(i + 1, end);

            // Combine left and right subtrees with current root i
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        // Memoize the result
        dp[start][end] = result;
        return result;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreesIIUsingMemo binarySearchTreesIIUsingMemo = new UniqueBinarySearchTreesIIUsingMemo();

        UniqueBinarySearchTreesIIUsingDP solver = new UniqueBinarySearchTreesIIUsingDP();
        List<TreeNode> treesForThree = solver.generateTrees(3);
        List<TreeNode> treesForOne = solver.generateTrees(1);


        for (TreeNode tree : treesForThree) {
            System.out.println(binarySearchTreesIIUsingMemo.serialize(tree));
        }

        System.out.println(" 2nd INPUT ...");
        for (TreeNode tree : treesForOne) {
            System.out.println(binarySearchTreesIIUsingMemo.serialize(tree));
        }
    }
}
