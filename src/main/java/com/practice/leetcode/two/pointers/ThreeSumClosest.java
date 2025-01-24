package com.practice.leetcode.two.pointers;

import java.util.Arrays;

/**
 * Leetcode: 16. 3Sum Closest https://leetcode.com/problems/3sum-closest/description/
 * Given an integer array nums of length n and an integer target, find three integers
 * in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * Example 1:
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest solution = new ThreeSumClosest();

        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("Closest Sum: " + solution.threeSumClosest(nums1, target1)); // Output: 2

        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("Closest Sum: " + solution.threeSumClosest(nums2, target2)); // Output: 0
    }

    private int threeSumClosest(int[] arr, int target) {
        Arrays.sort(arr);
        int closetSum = arr[0] + arr[1] + arr[2];

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];
                if (Math.abs(currentSum - target) < Math.abs(closetSum - target)) {
                    closetSum = currentSum;
                } else if (currentSum < target) {
                    left++;
                } else if (currentSum > target) {
                    right--;
                } else {
                    return closetSum;
                }
            }
        }
        return closetSum;
    }
}
