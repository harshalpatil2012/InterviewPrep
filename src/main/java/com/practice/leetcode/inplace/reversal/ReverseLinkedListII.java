package com.practice.leetcode.inplace.reversal;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list. Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 */
public class ReverseLinkedListII {


    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode leftNode = prev.next;
        ListNode curr = leftNode.next;

        for (int i = 0; i < right - left; i++) {
            leftNode.next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = leftNode.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original List: ");
        printList(head1);
        ListNode result1 = reverseBetween(head1, 2, 4);
        System.out.println("Reversed List from position 2 to 4: ");
        printList(result1);

        // Example 2
        ListNode head2 = createList(new int[]{5});
        System.out.println("Original List: ");
        printList(head2);
        ListNode result2 = reverseBetween(head2, 1, 1);
        System.out.println("Reversed List from position 1 to 1: ");
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
        return head.next;
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