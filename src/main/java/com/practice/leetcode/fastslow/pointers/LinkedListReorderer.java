package com.practice.leetcode.fastslow.pointers;

public class LinkedListReorderer {


    private void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListReorderer solution = new LinkedListReorderer();

        // Test case 1
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(6);
        head1.next.next.next = new ListNode(8);
        head1.next.next.next.next = new ListNode(10);
        head1.next.next.next.next.next = new ListNode(12);
        solution.reorderList(head1);
        System.out.print("Output for test case 1: ");
        solution.printLinkedList(head1);
        // Output: 2 -> 12 -> 4 -> 10 -> 6 -> 8 -> null

        // Test case 2
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);
        head2.next.next.next = new ListNode(8);
        head2.next.next.next.next = new ListNode(10);
        solution.reorderList(head2);
        System.out.print("Output for test case 2: ");
        solution.printLinkedList(head2);
        // Output: 2 -> 10 -> 4 -> 8 -> 6 -> null
    }

    private void reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2nd half reverse
        ListNode secondHalf = getReverse(slow.next);
        slow.next = null;
        ListNode firstHalf = head;
        while (secondHalf != null) {
            ListNode tmp1 = firstHalf.next;
            ListNode tmp2 = secondHalf.next;
            firstHalf.next = secondHalf;
            secondHalf.next = tmp1;
            firstHalf = tmp1;
            secondHalf = tmp2;

        }
    }

    private ListNode getReverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}