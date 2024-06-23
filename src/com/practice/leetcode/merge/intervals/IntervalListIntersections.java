package com.practice.leetcode.merge.intervals;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * Given two lists of intervals, find the intersection of these two lists. Each list consists of
 * disjoint intervals sorted on their start time.
 */

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {

    public static int[][] intervalIntersection1(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < firstList.length && j < secondList.length) {
            // Check if firstList[i] intersects with secondList[j]
            if (firstList[i][0] <= secondList[j][1] && secondList[j][0] <= firstList[i][1]) {
                // There is an intersection
                int start = Math.max(firstList[i][0], secondList[j][0]);
                int end = Math.min(firstList[i][1], secondList[j][1]);
                result.add(new int[]{start, end});
            }

            // Move the pointer of the interval that ends first
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

        int[][] intersections = intervalIntersection(firstList, secondList);

        System.out.println("Intersections:");
        for (int[] interval : intersections) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }

        // Expected Output:
        // Intersections:
        // [1, 2]
        // [5, 5]
        // [8, 10]
        // [15, 23]
        // [24, 24]
        // [25, 25]
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < firstList.length && i <= secondList.length) {
            if (firstList[i][0] <= secondList[j][1] && secondList[j][0] <= firstList[i][1]) {
                int start = Math.max(firstList[i][0], secondList[i][0]);
                int end = Math.min(firstList[i][1], secondList[i][1]);
                result.add(new int[]{start, end});
            }

            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}

