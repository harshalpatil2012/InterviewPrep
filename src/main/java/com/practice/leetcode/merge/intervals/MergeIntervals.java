package com.practice.leetcode.merge.intervals;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Merge Intervals (medium)
 * https://leetcode.com/problems/merge-intervals/
 * Given a list of intervals, merge all the overlapping intervals to produce a list that has only
 * mutually exclusive intervals.
 * Our goal is to merge the intervals whenever they overlap.
 * The diagram above clearly shows a
 * merging approach. Our algorithm will look like this:
 * 1. Sort the intervals on the start time to ensure a.start <= b.start
 * 2. If a overlaps b (i.e. b.start <= a.end ), we need to merge them into a new interval c such
 * that:
 * c.start = a.start
 * c.end = max(a.end, b.end)
 */
class MergeIntervals {

    // Method to merge intervals
    public int[][] merge(int[][] intervals) {
        // Edge case: if intervals array is empty
        if (intervals.length == 0) {
            return new int[0][];
        }

        // Sort intervals by the start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        // Start with the first interval
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // Check if intervals overlap
            if (nextStart <= currentEnd) {
                // Merge intervals
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // Add non-overlapping interval to the list
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * Given a set of intervals, find out if any two intervals overlap.add new method
     *
     * @param intervals
     * @return
     */
    // Method to check if any two intervals overlap
    public boolean isOverlapping(int[][] intervals) {
        // Edge case: if intervals array is empty or has only one interval
        if (intervals.length <= 1) {
            return false;
        }

        // Sort intervals by the start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Compare each interval's end with the next interval's start
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return true; // Overlap found
            }
        }

        return false; // No overlap found
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        // Test cases for merging intervals
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals2 = {{1, 4}, {4, 5}};

        System.out.println("Merged Intervals for intervals1: " + Arrays.deepToString(solution.merge(intervals1)));
        System.out.println("Merged Intervals for intervals2: " + Arrays.deepToString(solution.merge(intervals2)));

        // Test cases for checking overlaps
        int[][] intervals3 = {{1, 3}, {5, 6}, {8, 10}, {15, 18}};
        int[][] intervals4 = {{1, 3}, {2, 4}, {5, 6}};

        System.out.println("Do intervals3 overlap? " + solution.isOverlapping(intervals3)); // Expected: false
        System.out.println("Do intervals4 overlap? " + solution.isOverlapping(intervals4)); // Expected: true
    }
}
