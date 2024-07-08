package com.practice.leetcode.two.pointers;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/description/
 * Given an integer array nums sorted in non-decreasing order, return an array of
 * the squares of each number sorted in non-decreasing order.
 * Example 1:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        SquaresOfSortedArray solution = new SquaresOfSortedArray();

        int[] nums1 = {-4, -1, 0, 3, 10};
        int[] result1 = solution.sortedSquares(nums1);
        System.out.println("Output: " + Arrays.toString(result1));

        int[] nums2 = {-2, -1, 0, 2, 3};
        int[] result2 = solution.sortedSquares(nums2);
        System.out.println("Output: " + Arrays.toString(result2));
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1;
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }
        return result;
    }
}
