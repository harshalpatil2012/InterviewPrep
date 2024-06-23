package com.practice.leetcode.twoheaps;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class SlidingWindowMedian {
    private TreeMap<Integer, Integer> map; // TreeMap to store the count of each element in the window
    private int size; // Current number of elements in the window
    private int k; // Window size

    public double[] medianSlidingWindow(int[] nums, int k) {
        this.map = new TreeMap<>();
        this.size = 0;
        this.k = k;

        double[] result = new double[nums.length - k + 1];
        int start = 0;
        int end = 0;

        while (end < nums.length) {
            add(nums[end]);
            if (end - start + 1 == k) {
                result[start] = getMedian();
                remove(nums[start]);
                start++;
            }
            end++;
        }

        return result;
    }

    private void add(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
        size++;

        if (size > k) {
            int first = map.firstKey();
            if (map.get(first) == 1) {
                map.remove(first);
            } else {
                map.put(first, map.get(first) - 1);
            }
            size--;
        }
    }

    private void remove(int num) {
        if (map.containsKey(num)) {
            if (map.get(num) == 1) {
                map.remove(num);
            } else {
                map.put(num, map.get(num) - 1);
            }
            size--;
        }
    }

    private double getMedian() {
        if (size % 2 == 0) {
            Integer first = map.floorKey(size / 2);
            Integer second = map.ceilingKey(size / 2);
            return (double) (first + second) / 2.0;
        } else {
            return map.floorKey(size / 2);
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] result1 = swm.medianSlidingWindow(nums1, 3);
        for (double d : result1) {
            System.out.println(d);
        }

        int[] nums2 = {1, 2, 3, 4, 2, 3, 1, 4, 2};
        double[] result2 = swm.medianSlidingWindow(nums2, 3);
        for (double d : result2) {
            System.out.println(d);
        }

        int[] nums3 = {2147483647, 2147483647};
        double[] result3 = swm.medianSlidingWindow(nums3, 2);
        for (double d : result3) {
            System.out.println(d);
        }
    }
}
