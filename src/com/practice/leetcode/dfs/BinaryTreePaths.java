package com.practice.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths Given the  root  of a binary tree, return  all root-to-leaf paths in  any order.
 * A  leaf  is a node with no children.
 * <p>
 * Example 1: Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 * Example 2:
 * Input: root = [1]
 * Output: ["1"]
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range  [1, 100].
 * -100 <= Node.val <= 100 Java optimal solution with approach intituion, algorithm and time and space complexity and visuaization.
 * Give complete code including main method and all inputs
 */
public class BinaryTreePaths {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        System.out.println(new BinaryTreePaths().binaryTreePaths(root1));

        TreeNode root2 = new TreeNode(1);
        System.out.println(new BinaryTreePaths().binaryTreePaths(root2));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        dfs(root, "", paths);
        return paths;
    }

    private void dfs(TreeNode node, String path, List<String> paths) {
        if (node == null) return;

        path += node.val; // Add the current node's value to the path
        if (node.left == null && node.right == null) { // Leaf node
            paths.add(path);
        } else {
            dfs(node.left, path + "->", paths);
            dfs(node.right, path + "->", paths);
        }
    }


}



