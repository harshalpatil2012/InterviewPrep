package com.practice.leetcode.bfs;

/**
 * 116. Populating Next Right Pointers in Each Node You are given a perfect
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 * binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL. Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node,
 * just like in Figure B. The serialized output is in level order as connected by the next pointers,
 * with '#' signifying the end of each level.
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 */
class Node {
    int val;
    Node left;
    Node right;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}

public class PopulateNextRightPointers {
    public static void main(String[] args) {
        // Constructing a perfect binary tree for testing
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        PopulateNextRightPointers solution = new PopulateNextRightPointers();
        Node connectedRoot = solution.connect(root);

        // Print the serialized output in level order
        Node levelStart = connectedRoot;
        while (levelStart != null) {
            Node curr = levelStart;
            while (curr != null) {
                System.out.print(curr.val + " ");
                curr = curr.next;
            }
            System.out.println("#"); // Signifies end of level
            levelStart = levelStart.left;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node levelStart = root;
        while (levelStart.left != null) {
            Node curr = levelStart;
            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            levelStart = levelStart.left;
        }

        return root;
    }
}
