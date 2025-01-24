package com.practice.leetcode.merge.intervals;
/**
 * Employee Free Time (hard)
 * https://leetcode.com/problems/employee-free-time/
 * For ‘K’ employees, we are given a list of intervals representing the working hours of each
 * employee. Our goal is to find out if there is a free interval that is common to all employees.
 * You can assume that each list of employee working hours is sorted on the start time.
 * This problem follows the Merge Intervals pattern. Let’s take the an example:
 * Input: Employee Working Hours=[[[1,3], [9,12]], [[2,4]], [[6,8]]]
 * Output: [4,6], [8,9]
 * One simple solution can be to put all employees’ working hours in a list and sort them on the start
 * time. Then we can iterate through the list to find the gaps. Let’s dig deeper. Sorting the intervals of
 * the above example will give us:
 * [1,3], [2,4], [6,8], [9,12]
 */

import java.util.*;

class Interval1 {
    int start;
    int end;

    public Interval1(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class EmployeeFreeTime {
    public List<Interval1> employeeFreeTime11(List<List<Interval1>> schedule) {
        List<Interval1> result = new ArrayList<>();
        PriorityQueue<Interval1> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));

        // Add all Interval1s to the min heap
        for (List<Interval1> employeeSchedule : schedule) {
            minHeap.addAll(employeeSchedule);
        }

        Interval1 previousInterval1 = minHeap.poll();
        while (!minHeap.isEmpty()) {
            Interval1 currentInterval1 = minHeap.poll();

            // Check for gap between Interval1s
            if (currentInterval1.start > previousInterval1.end) {
                result.add(new Interval1(previousInterval1.end, currentInterval1.start));
                previousInterval1 = currentInterval1;
            } else {
                // Merge overlapping Interval1s
                previousInterval1.end = Math.max(previousInterval1.end, currentInterval1.end);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        EmployeeFreeTime eft = new EmployeeFreeTime();
        List<List<Interval1>> schedule1 = Arrays.asList(
                Arrays.asList(new Interval1(1, 2), new Interval1(5, 6)),
                Arrays.asList(new Interval1(1, 3)),
                Arrays.asList(new Interval1(4, 10))
        );
        List<Interval1> result1 = eft.employeeFreeTime(schedule1);
        for (Interval1 Interval1 : result1) {
            System.out.println(" Input 1 [" + Interval1.start + ", " + Interval1.end + "]");
        }

        System.out.println();
        List<List<Interval1>> schedule2 = Arrays.asList(
                Arrays.asList(new Interval1(1, 3), new Interval1(9, 12)),
                Arrays.asList(new Interval1(2, 4), new Interval1(6, 8))
        );
        List<Interval1> result2 = eft.employeeFreeTime(schedule2);
        for (Interval1 Interval1 : result2) {
            System.out.println(" Input 2 [" + Interval1.start + ", " + Interval1.end + "]");
        }
    }

    public List<Interval1> employeeFreeTime(List<List<Interval1>> schedule) {
        List<Interval1> result = new ArrayList<>();


        PriorityQueue<Interval1> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));

        for (List<Interval1> scheInterval1 : schedule) {
            minHeap.addAll(scheInterval1);
        }
        Interval1 previousInterval1 = minHeap.poll();
        while (!minHeap.isEmpty()) {
            Interval1 currentInterval1 = minHeap.poll();
            if (currentInterval1.end > previousInterval1.start) {
                result.add(new Interval1(previousInterval1.end, currentInterval1.start));
            }

        }

        return result;
    }
}
