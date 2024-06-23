package com.practice.leetcode.merge.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3}, {6, 9}
        };
        int[] newInterval = {2, 5};
        int[][] mergedIntervals = insert(intervals, newInterval);

        System.out.println("Merged intervals:");
        for (int[] interval : mergedIntervals) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }   // Output: Merged intervals: [1, 5][6, 9]
    }

    private static int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        int n = intervals.length;
        List<int[]> mergedIntervals = new ArrayList<>();
        while (i < n && intervals[i][1] < newInterval[0]) {
            mergedIntervals.add(intervals[i]);
            i++;
        }
        while (i < n && intervals[i][0] < newInterval[1]) {

            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        mergedIntervals.add(newInterval);
        while (i < n) {
            mergedIntervals.add(intervals[i]);
            i++;
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);

    }
}
