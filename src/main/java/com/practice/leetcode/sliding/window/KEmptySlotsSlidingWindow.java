package com.practice.leetcode.sliding.window;

import static com.practice.leetcode.sliding.window.KEmptySlots.kEmptySlots;

public class KEmptySlotsSlidingWindow {

    public static int kEmptySlots1(int[] flowers, int k) {
        int n = flowers.length;
        int[] days = new int[n];

        // Construct the days array
        for (int i = 0; i < n; i++) {
            days[flowers[i] - 1] = i + 1;
        }

        // Initialize the result with an impossible value
        int result = Integer.MAX_VALUE;

        // Sliding window to find the minimum day
        int left = 0;
        int right = k + 1;

        for (int i = 1; right < n; i++) {
            // If current element is valid
            if (days[i] < days[left] || days[i] <= days[right]) {
                // If i == right, we found a valid subarray
                if (i == right) {
                    result = Math.min(result, Math.max(days[left], days[right]));
                }
                // Move the window
                left = i;
                right = i + k + 1;
            }
        }

        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    private static int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        int[] days = new int[n];

        for (int i = 0; i < n; i++) {
            days[flowers[i] - 1] = i + 1;
        }

        int left = 0;
        int right = k + 1;
        int result = Integer.MAX_VALUE;

        for (int i = 0; right < n; i++) {
            if (days[i] < days[left] || days[i] <= days[right]) {
                if (i == right) {
                    result = Math.min(result, Math.max(days[left], days[right]));
                }
                left = i;
                right = i + k + 1;
            }
        }
        return (result == Integer.MAX_VALUE) ? -1 : result;

    }


    public static void main(String[] args) {
        // Example 1
        int[] flowers1 = {1, 3, 2};
        int k1 = 1;
        System.out.println("Example 1: " + kEmptySlots(flowers1, k1)); // Output: 2

        // Example 2
        int[] flowers2 = {1, 2, 3};
        int k2 = 1;
        System.out.println("Example 2: " + kEmptySlots(flowers2, k2)); // Output: -1

        // Example with Unordered Schedule
        int[] flowers3 = {3, 1, 5, 4, 2};
        int k3 = 1;
        System.out.println("Example 3: " + kEmptySlots(flowers3, k3)); // Output: 2
    }


}
