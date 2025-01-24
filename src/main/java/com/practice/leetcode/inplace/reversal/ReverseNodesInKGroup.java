package com.practice.leetcode.inplace.reversal;

/**
 * Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * You may not alter the values in the list's nodes, only nodes themselves may be changed. Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5] Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {
            int count = 0;
            ListNode temp = current;
            while (temp != null && count < k) {
                temp = temp.next;
                count++;
            }

            if (count == k) {
                ListNode next = null;
                ListNode prevNode = null;
                temp = current;
                while (count > 0) {
                    next = temp.next;
                    temp.next = prevNode;
                    prevNode = temp;
                    temp = next;
                    count--;
                }
                prev.next = prevNode;
                current.next = temp;
                prev = current;
                current = temp;
            } else {
                break;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

        // Example 1
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original List: ");
        printList(head1);
        ListNode result1 = solution.reverseKGroup(head1, 2);
        System.out.println("Reversed List in groups of 2: ");
        printList(result1);

        // Example 2
        ListNode head2 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original List: ");
        printList(head2);
        ListNode result2 = solution.reverseKGroup(head2, 3);
        System.out.println("Reversed List in groups of 3: ");
        printList(result2);
    }

    // Helper method to create a linked list from an array
    public static ListNode createList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    // Helper method to print a linked list
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}