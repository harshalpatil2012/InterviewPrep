package com.practice.ds.linkedList;

/**
 * Problem:
 * Given a Linked List, write a java program to pairwise reverse the Linked List,
 * 
 * In this program, we first define a ListNode class that represents a node in a
 * linked list. We then define a pairwiseReverse function that takes in the head
 * of a linked list and returns the head of the pairwise reversed linked list.
 * We use a recursive approach to pairwise reverse the linked list, where we
 * reverse the first two nodes and recursively call pairwiseReverse on the rest
 * of the list. Finally, we define a main function that constructs an example
 * linked list, pairwise reverses it using pairwiseReverse, and prints out the
 * result.
 * 
 * @author harshal
 *
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
		next = null;
	}
}

public class PairwiseReverseLinkedList {
	public static ListNode pairwiseReverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null, curr = head, next = null;
		int count = 0;
		while (curr != null && count < 2) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			count++;
		}
		if (next != null) {
			head.next = pairwiseReverse(next);
		}
		return prev;
	}

	public static void main(String[] args) {
		// Construct linked list
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		// Pairwise reverse the linked list
		ListNode newHead = pairwiseReverse(head);

		// Print the result
		while (newHead != null) {
			System.out.print(newHead.val + " ");
			newHead = newHead.next;
		}
	}
}
