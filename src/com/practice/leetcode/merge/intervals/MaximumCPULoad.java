package com.practice.leetcode.merge.intervals;

import java.util.*;

/**
 * We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is
 * running. Our goal is to find the maximum CPU load at any time if all the jobs are running on the
 * same machine.
 * <p>
 * The problem follows the Merge Intervals pattern and can easily be converted to Minimum
 * Meeting Rooms. Similar to ‘Minimum Meeting Rooms’ where we were trying to find the maximum
 * number of meetings happening at any time, for ‘Maximum CPU Load’ we need to find the maximum
 * number of jobs running at any time. We will need to keep a running count of the maximum CPU load
 * at any time to find the overall maximum load.
 * Jobs: [[1,4,3], [2,5,4], [7,9,6]]
 * Output: 7
 * Explanation: Since [1,4,3] and [2,5,4] overlap, their maximum CPU load (3+4=7) will be
 * jobs are running at the same time i.e., during the time interval (2,4).
 * Jobs: [[6,7,10], [2,4,11], [8,12,15]]
 * Output: 15
 * Explanation: None of the jobs overlap, therefore we will take the maximum load of any
 * Jobs: [[1,4,2], [2,4,1], [3,6,5]]
 * Output: 8
 * Approach:
 * Sort the jobs based on their start times.
 * Create a min-heap to keep track of the end times of jobs that are currently running.
 * Iterate through the sorted jobs:
 * Remove jobs from the heap whose end times have passed.
 * Add the current job's CPU load to the heap.
 * Update the maximum CPU load if the current total load is greater than the previous maximum.
 * Return the maximum CPU load.
 */
import java.util.*;

public class MaximumCPULoad {
    public static int findMaxCPULoad(int[][] jobs) {
        if (jobs == null || jobs.length == 0) {
            return 0;
        }

        // Sort the jobs by start time
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        int maxCPULoad = 0;
        int currentCPULoad = 0;

        for (int[] job : jobs) {
            int startTime = job[0];
            int endTime = job[1];
            int cpuLoad = job[2];

            // Increment CPU load at start time
            currentCPULoad += cpuLoad;

            // Update the maximum CPU load
            maxCPULoad = Math.max(maxCPULoad, currentCPULoad);

            // Decrement CPU load at end time
            currentCPULoad -= cpuLoad;
        }

        return maxCPULoad;
    }

    public static void main(String[] args) {
        int[][] jobs1 = {{1, 4, 3}, {2, 5, 4}, {7, 9, 6}};
        System.out.println("Maximum CPU Load for jobs1: " + findMaxCPULoad(jobs1)); // Output: 6

        int[][] jobs2 = {{6, 7, 10}, {2, 4, 11}, {8, 12, 15}};
        System.out.println("Maximum CPU Load for jobs2: " + findMaxCPULoad(jobs2)); // Output: 15

        int[][] jobs3 = {{1, 4, 2}, {2, 4, 1}, {3, 6, 5}};
        System.out.println("Maximum CPU Load for jobs3: " + findMaxCPULoad(jobs3)); // Output: 5
    }
}


