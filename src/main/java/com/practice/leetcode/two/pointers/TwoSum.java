package com.practice.leetcode.two.pointers;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-sum/Given an array of sorted numbers and a target sum, find a
 * pair in the array whose sum is equalto the given target.Write a function to return the indices of
 * the two numbers (i.e. the pair) such that they add up to thegiven target.
 */
public class TwoSum {

    public static void main(String[] args) {

        int[] arr = {1, 2, 4, 5, 7};
        int target = 7;

        System.out.println("Aray indices with target sum:: " + Arrays.toString(findSum(arr, target)));
    }

    public static int[] findSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1}; // Return -1, -1 if no pair is found
    }
}
