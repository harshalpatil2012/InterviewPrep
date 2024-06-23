package com.practice.leetcode.fastslow.pointers;

public class LinkedListCycleLength {
    public static void main(String[] args) {
        LinkedListCycleLength list = new LinkedListCycleLength();

        // Create a linked list with a cycle
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1; // Create cycle

        System.out.println("Cycle length: " + list.cycleLength(head)); // Output: 3

        // Create a linked list without a cycle
        ListNode head2 = new ListNode(1);
        ListNode node4 = new ListNode(2);

        head2.next = node4;

        System.out.println("Cycle length: " + list.cycleLength(head2)); // Output: 0
    }

    private int cycleLength(ListNode head) {
        int cycleLength = 0;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                cycleLength = calculateCycleLength(slow);
                break;
            }
        }
        return cycleLength;
    }

    private int calculateCycleLength(ListNode slow) {
        int length = 0;
        ListNode node = slow;
        while (true) {
            slow = slow.next;
            length++;
            if (slow == node) {
                break;
            }
        }
        return length;
    }
}
