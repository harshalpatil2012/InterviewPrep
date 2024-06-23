package com.practice.leetcode.merge.intervals;

import java.util.Arrays;

import java.util.Arrays;

public class MeetingRooms {

    public static void checkConflicts(int[][] intervals) {
        // Sort the intervals by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        boolean hasConflicts = false;
        // Check for overlapping intervals
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                System.out.println("[" + intervals[i - 1][0] + "," + intervals[i - 1][1] + "] and ["
                        + intervals[i][0] + "," + intervals[i][1] + "] conflict.");
                hasConflicts = true;
            }
        }

        if (!hasConflicts) {
            System.out.println("No conflicts found.");
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{4, 5}, {2, 3}, {3, 6}, {5, 7}, {7, 8}};

        System.out.println("Appointments: " + Arrays.deepToString(intervals));
        System.out.println("Output:");
        checkConflicts(intervals);

        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}};

        System.out.println("Can attend all meetings for intervals1: " + canAttendMeetings(intervals1)); // false
        System.out.println("Can attend all meetings for intervals2: " + canAttendMeetings(intervals2)); // true
    }

    public static boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        // Sort the intervals by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Check for overlapping intervals
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false; // Found overlapping intervals
            }
        }

        return true; // No overlapping intervals found
    }
}
