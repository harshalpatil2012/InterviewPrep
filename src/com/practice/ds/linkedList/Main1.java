package com.practice.ds.linkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class SinglyLinkedList1 {
    Node head;

    public SinglyLinkedList1() {
        this.head = null;
    }

    public SinglyLinkedList1(String data) {
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

    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }
}

public class Main1 {

    public static boolean doLinkedListsIntersect(Collection<SinglyLinkedList1> lists) {
        // Create a set to store all the nodes we have seen.
        Set<SinglyLinkedList1.Node> seenNodes = new HashSet<>();

        // Iterate over all the lists.
        for (SinglyLinkedList1 list : lists) {
            // Initialize a pointer to the head of the list.
            SinglyLinkedList1.Node currentNode = list.head;

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
        List<SinglyLinkedList1> linkedLists = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
                boolean intersects = doLinkedListsIntersect(linkedLists);
                System.out.println(intersects);
                linkedLists.clear();
            } else {
                if (line.contains("->")) {
                    linkedLists.add(parseLinkedList(line));
                } else {
                    Set<SinglyLinkedList1> intersectNodes = new HashSet<>();
                    String[] nodes = line.split(",");
                    for (String node : nodes) {
                        for (SinglyLinkedList1 list : linkedLists) {
                            if (list.contains(node)) {
                                intersectNodes.add(list);
                            }
                        }
                    }
                    boolean intersects = doLinkedListsIntersect(intersectNodes);
                    System.out.println(intersects);
                }
            }
        }
    }

    private static SinglyLinkedList1 parseLinkedList(String line) {
        String[] nodes = line.split("->");
        SinglyLinkedList1 linkedList = new SinglyLinkedList1(nodes[0]);
        for (int i = 1; i < nodes.length; i++) {
            linkedList.add(nodes[i]);
        }
        return linkedList;
    }
}