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

    private static int[] findSum(int[] arr, int target) {

        int start = 0;
        int end = arr.length - 1;
        while (start < end) {

            int sum = arr[start] + arr[end];
            if (sum == target) {
                return new int[]{start, end};
            }

            if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return new int[]{};
    }
}
