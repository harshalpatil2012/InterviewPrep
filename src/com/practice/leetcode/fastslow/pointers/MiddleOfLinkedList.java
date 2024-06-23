package com.practice.leetcode.fastslow.pointers;

/**
 * Middle of the LinkedList (easy)https://leetcode.com/problems/middle-of-the-linked-list/
 * Given the head of a Singly LinkedList,
 * write a method to return the middle node of theLinkedList.If the total number of
 * nodes in the LinkedList is even, return the second middle node.
 */
public class MiddleOfLinkedList {
    public static void main(String[] args) {
        MiddleOfLinkedList list = new MiddleOfLinkedList();

        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode middle = list.middleNode(head);
        System.out.println("Middle node value: " + middle.val); // Output: 3

        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);

        ListNode middle2 = list.middleNode(head2);
        System.out.println("Middle node value: " + middle2.val); // Output: 4
    }

    private ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
