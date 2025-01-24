package com.practice.leetcode.two.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 15. 3Sum https://leetcode.com/problems/3sum/
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
public class TripletSumToZero {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);  // Sort the array
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first element of the triplet
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1, right = nums.length - 1;

                while (left < right) {
                    if (nums[left] + nums[right] + nums[i] == 0) { // Simplified condition
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // Skip duplicates for the second and third elements of the triplet
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (nums[left] + nums[right] + nums[i] < 0) {
                        left++;
                    } else { // nums[left] + nums[right] + nums[i] > 0
                        right--;
                    }
                }
            }
        }
        return result;
    }



    public static void main(String[] args) {
        TripletSumToZero solution = new TripletSumToZero();

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Triplets: " + solution.threeSum(nums1));

        int[] nums2 = {};
        System.out.println("Triplets: " + solution.threeSum(nums2));

        int[] nums3 = {0};
        System.out.println("Triplets: " + solution.threeSum(nums3));
    }


}
