package com.practice.ds.linkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

class SinglyLinkedList {
    Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public SinglyLinkedList(String data) {
        this.head = new Node(data);
    }

    public void add(String data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
    }

    public boolean contains(String data) {
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.data.equals(data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void printList() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }
}

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class LinkedListCycleIntersectionDemo {

    public static boolean doLinkedListsIntersect(Collection<SinglyLinkedList> lists) {
        // Create a set to store all the nodes we have seen.
        Set<SinglyLinkedList.Node> seenNodes = new HashSet<>();

        // Iterate over all the lists.
        for (SinglyLinkedList list : lists) {
            // Initialize a pointer to the head of the list.
            SinglyLinkedList.Node currentNode = list.head;

            // While the current node is not null, check if it is in the set of seen nodes.
            while (currentNode != null) {
                if (seenNodes.contains(currentNode)) {
                    // We have found a common node, so return true.
                    return true;
                }

                // Add the current node to the set of seen nodes.
                seenNodes.add(currentNode);

                // Move to the next node.
                currentNode = currentNode.next;
            }
        }

        // We have not found a common node, so return false.
        return false;
    }

    public static void main(String[] args) throws IOException {
        // Read the input from the command line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            // Split the line into a list of nodes.
            String[] nodes = line.split("->");

            // Create a list of SinglyLinkedList objects.
            List<SinglyLinkedList> lists = new ArrayList<>();
            for (String node : nodes) {
                lists.add(new SinglyLinkedList(node));
            }

            // Check if any of the lists intersect.
            if (lists.isEmpty()) {
                System.out.println("Error Thrown!");
                return;
            }
            boolean intersects = doLinkedListsIntersect(lists);
            System.out.println(intersects);
        }
    }
}
