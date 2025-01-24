package com.practice.leetcode.two.pointers;

/**
 * 27. Remove Element
 * https://leetcode.com/problems/remove-element/function
 * Given an unsorted array of numbers and a target ‘key’, remove all instances of ‘key’ in-place
 * and return the new length of the array.
 */
public class RemoveElementInPlace {
    public static void main(String[] args) {

        int[] nums = {2, 11, 2, 2, 1};
        int key = 2;
        System.out.println(" Array length post removal:: " + removeElements(nums, key));
    }

    private static int removeElements(int[] nums, int key) {

        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != key) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
