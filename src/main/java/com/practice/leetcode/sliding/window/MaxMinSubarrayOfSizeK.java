package com.practice.leetcode.sliding.window;

import java.util.List;

/**
 * 209. Minimum Size Subarray Sum
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray
 * whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 */
public class MaxMinSubarrayOfSizeK {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("Max subarr " + maxSubarrays(arr, 5));

        System.out.println("Min subarr " + minSubarrays(arr, 5));


        // Test Case 1
        int target1 = 7;
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(target1, nums1));  // Output: 2

    }

    private static Integer maxSubarrays(int[] arr, int k) {

        int windowSum = 0;
        int windwostart = 0;
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            windowSum += arr[i];
            if (i >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windwostart];
                windwostart++;
            }
        }
        return maxSum;
    }

    private static Integer minSubarrays(int[] arr, int k) {

        int windowSum = 0;
        int windwostart = 0;
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            windowSum += arr[i];
            if (i >= k - 1) {
                minSum = Math.min(minSum, windowSum);
                windowSum -= arr[windwostart];
                windwostart++;
            }
        }
        return minSum;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int left = 0, currentSum = 0;

        for (int right = 0; right < n; right++) {
            currentSum += nums[right];

            while (currentSum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                currentSum -= nums[left++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
