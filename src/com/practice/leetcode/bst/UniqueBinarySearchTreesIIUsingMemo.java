package com.practice.leetcode.bst;

/**
 * 95. Unique Binary Search Trees II
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * Example 1:
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class UniqueBinarySearchTreesIIUsingMemo {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        Map<String, List<TreeNode>> memo = new HashMap<>();
        return generateTrees(1, n, memo);
    }

    public List<TreeNode> generateTrees(int start, int end, Map<String, List<TreeNode>> memo) {
        String key = start + "," + end;
        if (memo.containsKey(key)) return memo.get(key);

        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
        } else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> leftTrees = generateTrees(start, i - 1, memo);
                List<TreeNode> rightTrees = generateTrees(i + 1, end, memo);

                for (TreeNode left : leftTrees) {
                    for (TreeNode right : rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        result.add(root);
                    }
                }
            }
        }

        memo.put(key, result);
        return result;
    }

    public List<Integer> serialize(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        serializeHelper(root, result);
        return result;
    }

    public void serializeHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            result.add(null);
            return;
        }
        result.add(node.val);
        serializeHelper(node.left, result);
        serializeHelper(node.right, result);
    }


    public static void main(String[] args) {
        UniqueBinarySearchTreesIIUsingMemo solver = new UniqueBinarySearchTreesIIUsingMemo();
        List<TreeNode> treesForThree = solver.generateTrees(3);
        List<TreeNode> treesForOne = solver.generateTrees(1);


        for (TreeNode tree : treesForThree) {
            System.out.println(solver.serialize(tree));
        }

        System.out.println(" 2nd INPUT ...");
        for (TreeNode tree : treesForOne) {
            System.out.println(solver.serialize(tree));
        }
    }
}