package com.practice.ds.bst;

import java.util.*;
import java.io.*;
import java.lang.*;

class Node1 {
	int key;
	Node1 left, right;

	Node1(int x) {
		key = x;
		left = right = null;
	}
}

public class ValidateBST {

	public static boolean isBST(Node1 root, int min, int max) {
		if (root == null)
			return true;

		return (root.key > min && root.key < max && isBST(root.left, min, root.key)
				&& isBST(root.right, root.key, max));
	}

	static int prev = Integer.MIN_VALUE;

	public static boolean isBSTEfficient(Node1 root) {
		if (root == null)
			return true;

		if (isBSTEfficient(root.left) == false)
			return false;

		if (root.key <= prev)
			return false;
		prev = root.key;

		return isBSTEfficient(root.right);
	}

	public static void main(String args[]) {
		Node1 root = new Node1(4);
		root.left = new Node1(2);
		root.right = new Node1(5);
		root.left.left = new Node1(1);
		root.left.right = new Node1(3);

		if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE))
			System.out.println("It IS BST");
		else
			System.out.println("Not a BST");

		if (isBSTEfficient(root))
			System.out.println("It IS BST");
		else
			System.out.println("Not a BST");
	}
}