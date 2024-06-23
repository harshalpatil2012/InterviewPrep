package com.practice.leetcode.cyclic.sort;

/**
 * Find the Corrupt Pair (easy)We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’.
 * The array originally contained all the numbers from 1 to ‘n’,
 * but due to a data error, one ofthe numbers got duplicated which also resulted in one number going missing. Find both thesenumbers.
 */
public class FindCorruptPair {

    public static void main(String[] args) {
        int[] nums1 = {3, 1, 2, 5, 2};
        int[] nums2 = {3, 1, 2, 3, 6, 4};

        int[] result1 = findCorruptPair(nums1);
        System.out.println("Corrupt pair in nums1: [" + result1[0] + ", " + result1[1] + "]");

        int[] result2 = findCorruptPair(nums2);
        System.out.println("Corrupt pair in nums2: [" + result2[0] + ", " + result2[1] + "]");
    }

    private static int[] findCorruptPair(int[] nums) {

        int i = 0;
        while (i < nums.length) {

            int correctIndex = nums[i] - 1;
            if (nums[i] == correctIndex) {
                int tmp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = tmp;
            } else {
                i++;
            }

        }

        for (int j = 0; j < nums.length; j++) {

            if (nums[j] != j + 1) {
                return new int[]{nums[j], j + 1};
            }
        }
        return new int[]{-1, -1}; // Return -1, -1 if no corrupt pair is found
    }
}
