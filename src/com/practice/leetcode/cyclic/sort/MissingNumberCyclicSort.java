package com.practice.leetcode.cyclic.sort;

/**
 * https://leetcode.com/problems/missing-number/
 * We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’ .
 * Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing
 * number.
 */
public class MissingNumberCyclicSort {
    public static int findMissingNumbe1r(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] < nums.length && nums[i] != nums[nums[i]]) {
                int temp = nums[i];
                nums[i] = nums[nums[temp]];
                nums[nums[temp]] = temp;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return nums.length;
    }

    public static int findMissingNumber2(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int esum = 0;

        for (int num : nums) {
            esum += num;
        }
        return sum - esum;
    }

    public static int findMissingNumber(int[] nums) {

        int n = nums.length;
        int i = 0;

        while (i < n) {
            if (nums[i] < n && nums[i] != nums[nums[i]]) {

                int tmp = nums[i];
                nums[i] = nums[nums[tmp]];
                nums[nums[tmp]] = tmp;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {

            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 1};
        int[] nums2 = {0, 1};
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        System.out.println("Missing number in nums1: " + findMissingNumber(nums1)); // 2
        System.out.println("Missing number in nums2: " + findMissingNumber(nums2)); // 2
        System.out.println("Missing number in nums3: " + findMissingNumber(nums3)); // 8
    }
}
