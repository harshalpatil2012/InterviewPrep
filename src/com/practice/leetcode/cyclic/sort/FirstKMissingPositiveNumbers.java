package com.practice.leetcode.cyclic.sort;

import java.util.*;

public class FirstKMissingPositiveNumbers {


    public static void main(String[] args) {
        int[] nums1 = {-2, -3, 4};
        int k1 = 2;
        System.out.println("First " + k1 + " missing positive numbers: " + findFirstKMissingPositiveNumbers(nums1, k1)); // [1, 2]

        int[] nums2 = {2, 1, 3, 6, 5};
        int k2 = 2;
        System.out.println("First " + k2 + " missing positive numbers: " + findFirstKMissingPositiveNumbers(nums2, k2)); // [4, 7]
    }

    private static List<Integer> findFirstKMissingPositiveNumbers(int[] nums, int k) {
        List<Integer> misiingLIst = new ArrayList<>();
        int i = 0;
        while (misiingLIst.size() < k) {
            if (i < nums.length && nums[i] == i + 1) {
                i++;
            } else {
                misiingLIst.add(nums[i]);
                i++;
            }
        }

        return misiingLIst;
    }
}
