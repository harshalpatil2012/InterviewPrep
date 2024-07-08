package com.practice.leetcode.two.pointers;

/**
 * Leetcode 454. 4Sum II https://leetcode.com/problems/4sum-ii/description/
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * Example 1:
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * Example 2:
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 */

import java.util.HashMap;
import java.util.Map;

public class FourSumII {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumFrequencyMap = new HashMap<>();

        // Calculate and store frequencies of sums from nums3 and nums4
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                int sum = num3 + num4;
                sumFrequencyMap.put(sum, sumFrequencyMap.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;
        // Find complements in nums1 and nums2
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int complement = -(num1 + num2);
                count += sumFrequencyMap.getOrDefault(complement, 0);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        FourSumII fourSum = new FourSumII();

        // Test Case 1
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        int count1 = fourSum.fourSumCount(nums1, nums2, nums3, nums4);
        System.out.println("Test Case 1: " + count1); // Expected Output: 2

        // Test Case 2
        int[] nums5 = {0};
        int[] nums6 = {0};
        int[] nums7 = {0};
        int[] nums8 = {0};
        int count2 = fourSum.fourSumCount(nums5, nums6, nums7, nums8);
        System.out.println("Test Case 2: " + count2); // Expected Output: 1
    }
}

