package com.practice.leetcode.inplace.reversal;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        // Find the length of the list and calculate the actual k value
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        k = k % length;
        if (k == 0) {
            return head;
        }

        // Move to the (length - k)th node
        ListNode current = head;
        for (int i = 0; i < length - k - 1; i++) {
            current = current.next;
        }

        // Rotate the list
        tail.next = head; // Make the list circular
        head = current.next; // Update the new head
        current.next = null; // Break the circular list

        return head;
    }

    public static void main(String[] args) {
        RotateList solution = new RotateList();

        // Example 1
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original List: ");
        printList(head1);
        ListNode result1 = solution.rotateRight(head1, 2);
        System.out.println("Rotated List by 2 places: ");
        printList(result1);

        // Example 2
        ListNode head2 = createList(new int[]{0, 1, 2});
        System.out.println("Original List: ");
        printList(head2);
        ListNode result2 = solution.rotateRight(head2, 4);
        System.out.println("Rotated List by 4 places: ");
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