package com.practice.leetcode.cyclic.sort;

/**
 * We are given an array containing ‘n’ objects. Each object, when created, was assigned a uniquenumber
 * from 1 to ‘n’ based on their creation sequence. This means that the object withsequence
 * number ‘3’ was created just before the object with sequence number
 * ‘4’.Write a function to sort the objects in-place on their creation
 * sequence number in O(n) and without any extra space.
 */
public class CyclicSort {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 4, 2};
        sortInplace(nums);
        System.out.println("Sorted array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sortInplace(int[] nums) {
        int i = 0;
        while (i < nums.length) {

            int position = nums[i] - 1;
            if (nums[i] != nums[position]) {
                int tmp = nums[i];
                nums[i] = nums[position];
                nums[position] = tmp;
            } else {
                i++;
            }

        }
    }

}
