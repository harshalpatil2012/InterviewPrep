package com.practice.leetcode.two.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum https://leetcode.com/problems/4sum/description/
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * Example 1:
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 */
public class FourSum {

    public static void main(String[] args) {
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;   // Output: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
        System.out.println("Quadruplets for target 0: " + fourSum(nums1, target1));

        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;  //Output: [[2, 2, 2, 2]]
        System.out.println("Quadruplets for target 8: " + fourSum(nums2, target2));

        int[] nums3 = {1000000000, 1000000000, 1000000000, 1000000000};
        int target3 = -294967296; // Output: []
        System.out.println("Quadruplets for target -294967296: " + fourSum(nums3, target3));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result; // Check for edge cases

        Arrays.sort(nums); // Step 1: Sort the array to use the two-pointer technique and skip duplicates

        for (int i = 0; i < nums.length - 3; i++) { // Outer loop for the first element
            // Skip duplicates for the first element
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; j++) { // Loop for the second element
                    // Skip duplicates for the second element
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        int left = j + 1; // Initialize the left pointer
                        int right = nums.length - 1; // Initialize the right pointer

                        // Two-pointer technique
                        while (left < right) {
                            // Cast to long to avoid overflow
                            long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];

                            if (sum == target) { // Found a quadruplet
                                result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                                // Skip duplicates for the third element
                                while (left < right && nums[left] == nums[left + 1]) left++;
                                // Skip duplicates for the fourth element
                                while (left < right && nums[right] == nums[right - 1]) right--;
                                left++;
                                right--;
                            } else if (sum < target) { // Move the left pointer to increase the sum
                                left++;
                            } else { // Move the right pointer to decrease the sum
                                right--;
                            }
                        }
                    }
                }
            }
        }

        return result; // Return the list of unique quadruplets
    }
}
