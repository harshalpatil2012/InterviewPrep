package com.practice.leetcode.fastslow.pointers;

/**
 * Leetcode : 142. Linked List Cycle II
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

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // No cycle if list is empty or has only one node
        }

        ListNode slow = head, fast = head; // Initialize pointers

        // Cycle detection
        while (fast != null && fast.next != null) {
            slow = slow.next;             // Slow moves one step
            fast = fast.next.next;        // Fast moves two steps
            if (slow == fast) {           // Cycle detected!
                slow = head;             // Reset slow to the head
                while (slow != fast) {   // Move both pointers at same speed
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;              // Meeting point is the cycle start
            }
        }

        return null;  // No cycle found
    }
}
