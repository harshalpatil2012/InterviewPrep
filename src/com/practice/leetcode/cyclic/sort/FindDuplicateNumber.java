package com.practice.leetcode.cyclic.sort;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’. The
 * array has only one duplicate but it can be repeated multiple times. Find that duplicate
 * number without using any extra space. You are, however, allowed to modify the input array.
 */
public class FindDuplicateNumber {


    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};

        System.out.println("Duplicate number in nums1: " + findDuplicate(nums1)); // 2
        System.out.println("Duplicate number in nums2: " + findDuplicate(nums2)); // 3
    }

    private static int findDuplicate(int[] nums) {

        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1) {
                int correctIndex = nums[i] - 1;
                if (nums[i] != nums[correctIndex]) {
                    int tmp = nums[i];
                    nums[i] = nums[correctIndex];
                    nums[correctIndex] = tmp;
                    i++;
                } else {
                    return nums[i];
                }
            } else {
                i++;
            }
        }
        return -1;
    }
}
