package codeInterView.linkedList;

import java.util.HashSet;
import java.util.Set;

//Java program to remove duplicates from unsorted 
//linked list

public class DuplicateNodeRemoval {

    static Node head;

    static class Node {

        Integer data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    /* Function to remove duplicates from an
    unsorted linked list */
    void remove_duplicates() {
        Node ptr1 = null, ptr2 = null, dup = null;
        ptr1 = head;

        /* Pick elements one by one */
        while (ptr1 != null && ptr1.next != null) {
            ptr2 = ptr1;

            /* Compare the picked element with rest
             of the elements */
            while (ptr2.next != null) {

                /* If duplicate then delete it */
                if (ptr1.data == ptr2.next.data) {

                    /* sequence of steps is important here */
                    dup = ptr2.next;
                    ptr2.next = ptr2.next.next;
                    System.gc();
                } else /* This is tricky */ {
                    ptr2 = ptr2.next;
                }
            }
            ptr1 = ptr1.next;
        }
    }

    void remove_duplicatesUsingSet() {
        Node previous = null;
        Node current = head;
        Set<Integer> set = new HashSet<>();

        /* Pick elements one by one */
        while (current != null) {

            if (set.contains(current.data)) {
                previous.next = current.next;
                set.remove(current);
            } else {
                set.add(current.data);
                previous = current;
            }
            current = previous.next;
        }
    }

    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        DuplicateNodeRemoval list = new DuplicateNodeRemoval();
        list.head = new Node(10);
        list.head.next = new Node(12);
        list.head.next.next = new Node(11);
        list.head.next.next.next = new Node(11);
        list.head.next.next.next.next = new Node(12);
        list.head.next.next.next.next.next = new Node(11);
        list.head.next.next.next.next.next.next = new Node(10);

        System.out.println("Linked List before removing duplicates : \n ");
        list.printList(head);

        list.remove_duplicates();
        System.out.println("");
        System.out.println("Linked List after removing duplicates : \n ");
        list.printList(head);
    }
}
