package com.practice.ds.linkedList;

import java.io.*;
import java.util.*;

public class LinkedListsIntersect {
	public static boolean doLinkedListsIntersect(Collection<SinglyLinkedList> linkedLists) {
		Set<String> visitedNodes = new HashSet<>();

		for (SinglyLinkedList list : linkedLists) {
			SinglyLinkedList currentNode = list;

			while (currentNode != null) {
				if (!visitedNodes.add(currentNode.from)) {
					return true; // Common node found
				}
				currentNode = findListByFrom(linkedLists, currentNode.to);
			}
		}

		return false; // No common node found
	}

	private static SinglyLinkedList findListByFrom(Collection<SinglyLinkedList> linkedLists, String from) {
		for (SinglyLinkedList list : linkedLists) {
			if (list.from.equals(from)) {
				return list;
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		List<SinglyLinkedList> linkedLists = new ArrayList<>();

		System.out.println("Enter singly-linked lists (e.g., 'a->b', or press Enter to finish):");

		String line;
		while (!(line = bufferedReader.readLine()).isEmpty()) {
			String[] nodes = line.trim().split("->");
			if (nodes.length == 2) {
				linkedLists.add(new SinglyLinkedList(nodes[0], nodes[1]));
			}
		}

		boolean result = doLinkedListsIntersect(linkedLists);

		System.out.println(result ? "True" : "False");
	}
}

class SinglyLinkedList {
	String from;
	String to;

	SinglyLinkedList(String from, String to) {
		this.from = from;
		this.to = to;
	}
}
