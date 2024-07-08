package com.practice.leetcode.bitwise;

/**
 * 136. Single Number
 * Given a non-empty array of integers nums, every element appears twice except for one.
 * Find that single one. You must implement a solution with a linear runtime complexity and use only constant extra space.
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 * Problem Intuition:
 * XOR Property: The core insight is the XOR (exclusive OR) operation:
 * x ^ 0 = x (XOR with 0 doesn't change the value)
 * x ^ x = 0 (XOR of a number with itself is 0)
 * Pairing: Since every number appears twice except for the single number, when we XOR all elements,
 * the pairs will cancel each other out, leaving only the single number.
 * Algorithm:
 * Initialize: Start with a variable result set to 0.
 * XOR Iteration: Iterate through the nums array.
 * For each element num, XOR it with the result (result = result ^ num).
 * Return: The final value of result will be the single number.
 */
public class FindSingleNumber {
    public static void main(String[] args) {
        FindSingleNumber solution = new FindSingleNumber();
        int[] nums1 = {2, 2, 1};
        int[] nums2 = {4, 1, 2, 1, 2};
        int[] nums3 = {1};

        System.out.println("Test 1: " + solution.singleNumber(nums1)); // Expected: 1
        System.out.println("Test 2: " + solution.singleNumber(nums2)); // Expected: 4
        System.out.println("Test 3: " + solution.singleNumber(nums3)); // Expected: 1
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
