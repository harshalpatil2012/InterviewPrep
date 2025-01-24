package com.practice.ds.bst;

import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * Calculate sum of vertical nodes in a binary tree
 * 
 * @author Harshal
 *
 */
class Node12 {
	int key;
	Node12 left, right;

	Node12(int x) {
		key = x;
		left = right = null;
	}
}

public class VerticleNodeSumBST {

	public static void verticleSumResult(Node12 root, int horizantalDist, TreeMap<Integer, Integer> mp) {
		if (root == null)
			return;
		verticleSumResult(root.left, horizantalDist - 1, mp);
		int pSum = (mp.get(horizantalDist) == null) ? 0 : mp.get(horizantalDist);
		mp.put(horizantalDist, pSum + root.key);
		verticleSumResult(root.right, horizantalDist + 1, mp);
	}

	public static void verticleListResult(Node12 root, int horizantalDist, TreeMap<Integer, List<Integer>> mp) {
		if (root == null)
			return;
		verticleListResult(root.left, horizantalDist - 1, mp);
		List<Integer> numList = (mp.get(horizantalDist) == null) ? new ArrayList<Integer>() : mp.get(horizantalDist);
		numList.add(root.key);
		mp.put(horizantalDist, numList);
		verticleListResult(root.right, horizantalDist + 1, mp);
	}

	public static void verticleList(Node12 root) {
		TreeMap<Integer, List<Integer>> mp = new TreeMap<>();
		verticleListResult(root, 0, mp);
		for (Map.Entry entry : mp.entrySet()) {
			List<Integer> list = (List<Integer>) entry.getValue();
			Collections.sort(list);
			System.out.println("\n Verticle elements ");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				System.out.print("  " + integer);
			}

		}
	}

	public static void vTraversal(Node12 root) {
		Queue<Pair> q = new LinkedList<>();
		Map<Integer, ArrayList<Integer>> mp = new TreeMap<>();
		q.add(new Pair(root, 0));
		while (q.isEmpty() == false) {
			Pair p = q.poll();
			Node12 curr = p.node;
			int hd = p.hd;
			if (mp.containsKey(hd))
				mp.get(hd).add(curr.key);
			else {
				ArrayList<Integer> al = new ArrayList<>();
				al.add(curr.key);
				mp.put(hd, al);
			}
			if (curr.left != null)
				q.add(new Pair(curr.left, hd - 1));
			if (curr.right != null)
				q.add(new Pair(curr.right, hd + 1));
		}
		for (Map.Entry<Integer, ArrayList<Integer>> p : mp.entrySet()) {
			ArrayList<Integer> al = p.getValue();
			for (int x : al)
				System.out.print(x + " ");
			System.out.println();
		}
	}

	public static void vSum(Node12 root) {
		TreeMap<Integer, Integer> mp = new TreeMap<Integer, Integer>();
		verticleSumResult(root, 0, mp);
		for (Map.Entry sum : mp.entrySet())
			System.out.print(sum.getValue() + " ");
	}

	public static void main(String args[]) {
		Node12 rootNode = new Node12(10);
		rootNode.left = new Node12(20);
		rootNode.right = new Node12(30);
		rootNode.left.left = new Node12(40);
		rootNode.left.right = new Node12(50);

		vSum(rootNode);

		verticleList(rootNode);
	}
}

class Pair {
	Node12 node;
	int hd;

	Pair(Node12 n, int h) {
		node = n;
		hd = h;
	}
}