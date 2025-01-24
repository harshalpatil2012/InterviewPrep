package com.practice.leetcode.twoheaps;

import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream Java optimal solution with approach intuition,
 * algorithm and time and space complexity and visualization.
 * Give complete code including main method and all inputs
 */
public class MedianFinder {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        int[] inputs = {1, 2, 3, 4, 5};
        for (int num : inputs) {
            medianFinder.addNum(num);
            System.out.println("Current Median: " + medianFinder.findMedian());
        }
    }

    private int findMedian() {

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (int) ((maxHeap.peek() + minHeap.peek()) / 2.0);
        }
    }

    private void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

    }
}
