package com.practice.leetcode.cyclic.sort;

import java.util.ArrayList;
import java.util.List;

public class FindAllMissingNumbers {

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums2 = {1, 1};

        System.out.println("Missing numbers in nums1: " + findDisappearedNumbers(nums1)); // [5, 6]
        System.out.println("Missing numbers in nums2: " + findDisappearedNumbers(nums2)); // [2]
    }

    private static List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;
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
        List<Integer> list = new ArrayList<>();

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                list.add(j + 1);
            }
        }
        return list;
    }
}
