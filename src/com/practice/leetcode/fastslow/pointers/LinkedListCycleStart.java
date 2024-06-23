package com.practice.leetcode.fastslow.pointers;

/**
 * Start of LinkedList Cycle (medium)
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * Given the head of a Singly LinkedList that contains a cycle, write a function to find the
 * starting node of the cycle.
 */
public class LinkedListCycleStart {

    public static void main(String[] args) {
        LinkedListCycleStart list = new LinkedListCycleStart();

        // Create a linked list with a cycle
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1; // Create cycle

        ListNode cycleStart = list.detectCycle(head);
        if (cycleStart != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart.val); // Output: 2
        } else {
            System.out.println("No cycle detected");
        }

        // Create a linked list without a cycle
        ListNode head2 = new ListNode(1);
        ListNode node4 = new ListNode(2);

        head2.next = node4;

        ListNode cycleStart2 = list.detectCycle(head2);
        if (cycleStart2 != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart2.val);
        } else {
            System.out.println("No cycle detected"); // Output: No cycle detected
        }
    }

    private ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {

                ListNode ptr1 = head;
                ListNode ptr2 = slow;
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }
                return ptr1;
            }
        }

        return null; // no cycle
    }
}
