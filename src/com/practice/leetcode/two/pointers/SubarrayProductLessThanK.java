package com.practice.leetcode.two.pointers;

import java.util.Scanner;

/**
 * Leetcode 713. Subarray Product Less Than K https://leetcode.com/problems/subarray-product-less-than-k/description/
 * Given an array of integers nums and an integer k, return the number of contiguous
 * subarrays where the product of all the elements in the subarray is strictly less than k.
 * Example 1:
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Example 2:
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 */
public class SubarrayProductLessThanK {

    public static int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (k <= 1) return 0;
        int left = 0, right = 0, product = 1, result = 0;
        while (right < nums.length) {
            product *= nums[right];
            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }
            result += right - left + 1;

            right++;
        }
        return result;
    }


    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {10, 5, 2, 6};
        int k1 = 1;
        int count1 = numSubarrayProductLessThanK(nums1, k1);
        System.out.println("Test Case 1: " + count1);  // Expected: 8

        // Test Case 2
        int[] nums2 = {10, 20, 30};
        int k2 = 2;
        int count2 = numSubarrayProductLessThanK(nums2, k2);
        System.out.println("Test Case 2: " + count2);  // Expected: 0

        // additional test case with a negative value
        int[] nums3 = {-1, 2, 3, 4};
        int k3 = 10;
        int count3 = numSubarrayProductLessThanK(nums3, k3);
        System.out.println("Test Case 3: " + count3);  // Expected: 5

    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {

        if (k <= 1) return 0;

        int left = 0, right = 0, product = 1, result = 0;

        while (right < nums.length) {
            product *= nums[right];

            if (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }
            result += right - left + 1;
            right++;
        }
        return result;
    }
}

