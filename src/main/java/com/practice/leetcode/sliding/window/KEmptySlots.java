package com.practice.leetcode.sliding.window;

import java.util.TreeSet;

/**
 * https://ttzztt.gitbooks.io/lc/content/array/k-empty-slots.html
 * 683. K Empty Slots
 * There is a garden withNslots. In each slot, there is a flower. TheNflowers will bloom one by one inNdays. In each day, there will beexactlyone flower blooming and it will be in the status of blooming since then.
 * <p>
 * Given an arrayflowersconsists of number from1toN. Each number in the array represents the place where the flower will open in that day.
 * <p>
 * For example,flowers[i] = xmeans that the unique flower that blooms at dayiwill be at positionx, whereiandxwill be in the range from1toN.
 * <p>
 * Also given an integerk, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them iskand these flowers are not blooming.
 * <p>
 * If there isn't such day, output -1.
 * Input:
 * <p>
 * flowers: [1,3,2]
 * k: 1
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * In the second day, the first and the third flower have become blooming.
 */
public class KEmptySlots {
    public static int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> active = new TreeSet<>();  // Blooming flower days (sorted)
        int day = 0;

        for (int flower : flowers) {
            day++;                                            // Next day
            active.add(day);                                  // Flower blooms on this day

            Integer lower = active.lower(day);                 // Previous blooming day
            Integer higher = active.higher(day);               // Next blooming day

            if ((lower != null && day - lower == k + 1) ||     // k empty slots before
                    (higher != null && higher - day == k + 1)) {   // k empty slots after
                return day;  // Found the earliest valid day
            }
        }

        return -1; // No valid k-empty slot found
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
        System.out.println("Example 3: " + kEmptySlots(flowers3, k3)); // Output: 5
    }
}
