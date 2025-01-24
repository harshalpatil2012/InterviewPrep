package com.practice.leetcode.fastslow.pointers;

/**
 * Palindrome LinkedList (medium)
 * https://leetcode.com/problems/palindrome-linked-list/
 * Given the head of a Singly LinkedList, write a method to check if the LinkedList is a
 * palindrome or not.
 * Your algorithm should use constant space and the input LinkedList should be in the original
 * form once the algorithm is finished. The algorithm should have O(N) time complexity where
 * ‘N’ is the number of nodes in the LinkedList.
 * Example 1:
 * Input: 2 -> 4 -> 6 -> 4 -> 2 -> null
 * Output: true
 * Example 2:
 * Input: 2 -> 4 -> 6 -> 4 -> 2 -> 2 -> null
 * Output: false
 * This optimized solution has a time complexity of O(N/2) and still uses only constant space (O(1)).
 */


public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null && head.next == null) {
            return false;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            ListNode next = slow.next;

            slow.next = prev;
            prev = slow;
            slow = next;
        }
        // handle odd
        if (fast != null) {
            slow = slow.next;
        }

        while (prev != null) {
            if (prev.val != slow.val) return false;
            prev = prev.next;
            slow = slow.next;
        }
        return true;

    }

    public static void main(String[] args) {
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();

        // Example 1: 2 -> 4 -> 6 -> 4 -> 2 -> null
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(6);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(2);
        System.out.println("Example 1: " + palindromeLinkedList.isPalindrome(head1)); // Output: true

        // Example 2: 2 -> 4 -> 6 -> 4 -> 2 -> 2 -> null
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next = new ListNode(2);
        System.out.println("Example 2: " + palindromeLinkedList.isPalindrome(head2)); // Output: false
    }
}
