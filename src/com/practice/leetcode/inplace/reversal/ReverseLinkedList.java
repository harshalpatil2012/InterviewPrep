package com.practice.leetcode.inplace.reversal;

/**
 * Leetcodde : 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class ReverseLinkedList {


    public static void main(String[] args) {
        // Example 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode reversed1 = reverseList(head1);
        printList(reversed1); // Output: [5, 4, 3, 2, 1]

        // Example 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        ListNode reversed2 = reverseList(head2);
        printList(reversed2); // Output: [2, 1]

        // Example 3
        ListNode head3 = null;
        ListNode reversed3 = reverseList(head3);
        printList(reversed3); // Output: []
    }

    private static ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }
        return prev;
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
