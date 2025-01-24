package com.practice.leetcode.merge.intervals;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 436. Find Right Interval
 * You are given an array of  intervals, where  intervals[i] = [starti, endi]  and each  starti  is  unique.
 * The  right interval  for an interval  i  is an interval  j  such that  startj >= endi  and  startj  is  minimized. Note that  i  may equal  j.
 * Return  an array of  right interval  indices for each interval  i. If no  right interval  exists for interval  i, then put  -1  at index  i.
 * <p>
 * Example 1:
 * Input: intervals = [[1,2]]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * Example 2:
 * Input: intervals = [[3,4],[2,3],[1,2]]
 * Output: [-1,0,1]
 * Explanation: There is no right interval for [3,4].
 * The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
 * The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
 * Example 3:
 * Input: intervals = [[1,4],[2,3],[3,4]]
 * Output: [-1,2,-1]
 * Explanation: There is no right interval for [1,4] and [3,4].
 * The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
 * <p>
 * Constraints:
 * 1 <= intervals.length <= 2 * 104
 * intervals[i].length == 2
 * -106 <= starti <= endi <= 106
 * The start point of each interval is  unique.
 */
public class FindRightInterval {

    public int[] findRightInterval1(int[][] intervals) {
        int n = intervals.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Initialize with -1

        // Map to track original indices
        Map<Integer, Integer> originalIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            originalIndex.put(intervals[i][0], i);
        }
        // Sort intervals by start point
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Binary search for right intervals (corrected)
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            int left = i, right = n - 1; // Start search from current index

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (intervals[mid][0] >= end) {
                    result[originalIndex.get(intervals[i][0])] = originalIndex.get(intervals[mid][0]);
                    right = mid - 1; // Look for smaller start points
                } else {
                    left = mid + 1;
                }
            }
        }
        return result;
    }

    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        HashMap<Integer, Integer> originalIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            originalIndex.put(intervals[i][0], i);
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            int left = i;
            int right = n-1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if(intervals[mid][0] >= end) {
                    result[originalIndex.get(intervals[i][0])]= originalIndex.get(intervals[mid][0]);
                    right = mid-1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindRightInterval solver = new FindRightInterval();

        int[][] intervals1 = {{1, 2}};
        System.out.println(Arrays.toString(solver.findRightInterval(intervals1))); // [-1]

        int[][] intervals2 = {{3, 4}, {2, 3}, {1, 2}};
        System.out.println(Arrays.toString(solver.findRightInterval(intervals2))); // [-1, 0, 1]

        int[][] intervals3 = {{1, 4}, {2, 3}, {3, 4}};
        System.out.println(Arrays.toString(solver.findRightInterval(intervals3))); // [-1, 2, -1]
    }
}
