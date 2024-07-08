package com.practice.leetcode;

public class CountSpecialQuadruplets {

    public static int countSpecialQuadruplets(int[] nums) {
        int numValidQuadruplets = 0;
        int n = nums.length;
        int[] differenceFrequency = new int[310]; // Array to store frequencies of differences

        // Iterate over the array from the second-to-last element to the start
        for (int b = n - 3; b > 0; --b) {
            int c = b + 1;

            // Update differenceFrequency with differences nums[d] - nums[c]
            for (int d = c + 1; d < n; ++d) {
                int difference = nums[d] - nums[c];
                if (difference >= 0) {
                    ++differenceFrequency[difference];
                }
            }

            // Check for valid pairs (a, b)
            for (int a = 0; a < b; ++a) {
                int sum = nums[a] + nums[b];
                numValidQuadruplets += differenceFrequency[sum];
            }
        }
        return numValidQuadruplets;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 6};
        System.out.println("Output for nums1: " + countSpecialQuadruplets(nums1)); // Expected output: 1

        int[] nums2 = {3, 3, 6, 4, 5};
        System.out.println("Output for nums2: " + countSpecialQuadruplets(nums2)); // Expected output: 0

        int[] nums3 = {1, 1, 1, 3, 5};
        System.out.println("Output for nums3: " + countSpecialQuadruplets(nums3)); // Expected output: 4
    }
}
