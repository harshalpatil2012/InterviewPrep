package com.practice.ds.tree;

/**
 * Problem: Merging two binary trees in such a way that the same level node will
 * be added together.
 * 
 * In this program, we first define a TreeNode class that represents a node in a
 * binary tree. We then define a mergeTrees function that takes in two binary
 * trees t1 and t2 and merges them together, such that the same level node will
 * be added together. We use a recursive approach to merge the trees, by first
 * adding up the values of the current nodes, then recursively merging the left
 * and right subtrees. Finally, we define a main function that constructs two
 * example binary trees, merges them using mergeTrees, and prints out the result
 * using an inorder traversal.
 * 
 * @author harshal
 *
 */
class TreeNode {
	int val;
	TreeNode left, right;

	TreeNode(int val) {
		this.val = val;
		left = right = null;
	}
}

public class BinaryTreeMerge {
	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return null;
		}
		int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
		TreeNode newNode = new TreeNode(val);
		newNode.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
		newNode.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
		return newNode;
	}

	public static void main(String[] args) {
		// Construct first binary tree
		TreeNode t1 = new TreeNode(1);
		t1.left = new TreeNode(3);
		t1.right = new TreeNode(2);
		t1.left.left = new TreeNode(5);

		// Construct second binary tree
		TreeNode t2 = new TreeNode(2);
		t2.left = new TreeNode(1);
		t2.right = new TreeNode(3);
		t2.left.right = new TreeNode(4);
		t2.right.right = new TreeNode(7);

		// Merge the two binary trees
		TreeNode merged = mergeTrees(t1, t2);

		// Print the result
		printInorder(merged);
	}

	public static void printInorder(TreeNode node) {
		if (node == null) {
			return;
		}
		printInorder(node.left);
		System.out.print(node.val + " ");
		printInorder(node.right);
	}
}
