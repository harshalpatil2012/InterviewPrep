package com.practice.leetcode.fastslow.pointers;

public class LinkedListCycle {

    public static void main(String[] args) {
        LinkedListCycle list = new LinkedListCycle();

        // Create a linked list with a cycle
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1; // Create cycle

        System.out.println("Cycle detected: " + list.hasCycle(head)); // Output: true

        // Create a linked list without a cycle
        ListNode head2 = new ListNode(1);
        ListNode node4 = new ListNode(2);

        head2.next = node4;

        System.out.println("Cycle detected: " + list.hasCycle(head2)); // Output: false
    }

    private boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast != null && fast.next != null) {
                if (fast == slow) {
                    return true;
                }
            }
        }
        return false;
    }

}
