package com.practice.leetcode.merge.intervals;

/**
 * 253. Meeting Rooms II
 * Question
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * <p>
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {

    public static int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Use a min-heap to track the end times of meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Iterate through the sorted intervals
        for (int[] interval : intervals) {
            // If the earliest ending meeting has finished, remove its end time from the heap
            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek()) {
                minHeap.poll();
            }

            // Add the current meeting's end time to the heap
            minHeap.add(interval[1]);
        }

        // The size of the heap is the minimum number of conference rooms required
        return minHeap.size();
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}, {5, 9}};

        System.out.println("Minimum number of conference rooms required for intervals1: " + minMeetingRooms(intervals1)); // 2
        System.out.println("Minimum number of conference rooms required for intervals2: " + minMeetingRooms(intervals2)); // 1
    }

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null && intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] interval : intervals) {

            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(interval[1]);
        }
        return minHeap.size();
    }
}

