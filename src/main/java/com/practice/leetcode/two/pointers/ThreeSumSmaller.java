package com.practice.leetcode.two.pointers;

import java.util.Arrays;

/**
 * Triplets with Smaller Sum (medium)https://leetcode.com/problems/3sum-smaller/
 * Given an array arr of unsorted numbers and a target sum,
 * count all triplets in it such thatarr[i] + arr[j] + arr[k] < target where
 * i, j, and k are three different indices.
 * Write a function to return the count of such triplets.
 */
public class ThreeSumSmaller {

    public static void main(String[] args) {
        ThreeSumSmaller solution = new ThreeSumSmaller();

        int[] nums1 = {-1, 0, 2, 3};
        int target1 = 3;
        System.out.println("Count of Triplets: " + solution.threeSumSmaller(nums1, target1)); // Output: 2

        int[] nums2 = {-1, 4, 2, 1, 3};
        int target2 = 5;
        System.out.println("Count of Triplets: " + solution.threeSumSmaller(nums2, target2)); // Output: 4
    }

    private int threeSumSmaller(int[] arr, int target) {
        Arrays.sort(arr);
        int right = arr.length - 1;
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int left = i + 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum < target) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}
