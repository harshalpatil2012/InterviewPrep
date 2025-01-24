package com.practice.leetcode.fastslow.pointers;

/**
 * Cycle in a Circular Array (hard)
 * https://leetcode.com/problems/circular-array-loop/
 * We are given an array containing positive and negative numbers. Suppose the array contains anumber‘M’
 * at a particular index. Now, if ‘M’ is positive we will move forward ‘M’ indices and if ‘M’ isnegative
 * move backwards ‘M’ indices. You should assume that the array is circular which meanstwo things:
 * 1. If, while moving forward, we reach the end of the array, we will jump to the first element tocontinue the movement.
 * 2. If, while moving backward, we reach the beginning of the array, we will jump to the last elementto continue the movement.
 * Write a method to determine if the array has a cycle.
 * The cycleshould have more than one element and should follow one direction which means the
 * cycleshould not contain both forward and backward movements.Example 1:Input: [1, 2, -1, 2, 2]
 * Output: true
 * Explanation: The array has a cycle among indices: 0 -> 1 -> 3 -> 0
 * Example 2:Input: [2, 2, -1, 2]
 * Output: true
 * Explanation: The array has a cycle among indices: 1 -> 3 -> 1
 * Example 3:Input: [2, 1, -1, -2]
 * Output: false
 * Explanation: The array does not have any cycle. new java optimal
 */
public class CircularArrayLoop {

    // Method to check if the array contains a cycle
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;  // Skip already visited elements

            // Initialize slow and fast pointers
            int slow = i, fast = i;
            boolean isForward = nums[i] > 0;  // Determine direction

            // Detect cycle using two pointers
            while (true) {
                slow = getNextIndex(nums, isForward, slow);
                fast = getNextIndex(nums, isForward, fast);
                if (fast != -1) {
                    fast = getNextIndex(nums, isForward, fast);
                }

                if (slow == -1 || fast == -1 || slow == fast) {
                    break;
                }
            }

            // Check for a valid cycle
            if (slow != -1 && slow == fast) {
                // Ensure the cycle length is greater than 1
                if (slow != getNextIndex(nums, isForward, slow)) return true;
            }

            // Mark elements in the detected cycle as visited
            int j = i;
            while (nums[j] * (isForward ? 1 : -1) > 0) {
                int nextIndex = getNextIndex(nums, isForward, j);
                nums[j] = 0;
                j = nextIndex;
            }
        }

        return false;  // No valid cycle found
    }

    // Helper method to get the next index, considering the circular nature of the array
    private int getNextIndex(int[] nums, boolean isForward, int index) {
        int n = nums.length;
        boolean direction = nums[index] > 0;
        if (isForward != direction) return -1;  // Change in direction, invalid

        int nextIndex = (index + nums[index]) % n;
        if (nextIndex < 0) nextIndex += n;  // Handle negative indices

        // One element cycle is invalid
        if (nextIndex == index) return -1;

        return nextIndex;
    }

    public static void main(String[] args) {
        CircularArrayLoop solution = new CircularArrayLoop();

        // Test cases
        int[] nums1 = {1, 2, -1, 2, 2};
        System.out.println(solution.circularArrayLoop(nums1)); // Expected: true

        int[] nums2 = {2, 2, -1, 2};
        System.out.println(solution.circularArrayLoop(nums2)); // Expected: true

        int[] nums3 = {2, 1, -1, -2};
        System.out.println(solution.circularArrayLoop(nums3)); // Expected: false
    }
}