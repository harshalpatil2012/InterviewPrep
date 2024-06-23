package com.practice.leetcode.bfs;


import java.util.*;

class Graph {
    private int numVertices;
    private LinkedList<Integer> adjacencyList[];

    // Constructor
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Add an edge to the graph
    public void addEdge(int src, int dest) {
        adjacencyList[src].add(dest);
    }

    // BFS traversal from a given source node
    public void BFS(int startNode) {
        boolean visited[] = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            startNode = queue.poll();
            System.out.print(startNode + " ");

            Iterator<Integer> iterator = adjacencyList[startNode].listIterator();
            while (iterator.hasNext()) {
                int node = iterator.next();
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }
    }
}

public class BFSExample {
    public static void main(String[] args) {
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("BFS traversal starting from node 0:");
        graph.BFS(0);
    }
}
