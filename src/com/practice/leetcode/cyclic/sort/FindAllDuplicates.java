package com.practice.leetcode.cyclic.sort;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicates {

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums2 = {1, 1, 2, 2};

        System.out.println("Duplicates in nums1: " + findDuplicates(nums1)); // [2, 3]
        System.out.println("Duplicates in nums2: " + findDuplicates(nums2)); // [1, 2]
    }

    private static List<Integer> findDuplicates(int[] nums) {

        int i = 0;
        List<Integer> result = new ArrayList<>();
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            if (nums[i] != nums[correctIndex]) {
                int tmp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = tmp;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }
        return result;
    }
}
